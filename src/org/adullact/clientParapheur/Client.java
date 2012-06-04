package org.adullact.clientParapheur;

import org.adullact.spring_ws.iparapheur._1.DocumentExploiteur;
import org.adullact.spring_ws.iparapheur._1.LogDossier;
import org.adullact.spring_ws.iparapheur._1.RechercherDossiersResponse;

public class Client {

	/**
	 * @param args
	 */
	static DocumentExploiteur documentExploiteur;
	static ProxyParapheur proxy;
	static String confPath = "conf.cf";
	static ConfigLoader configLoader;
	
	public static void main(String[] args) {

		//Chargement de la configuration 
		configLoader = new ConfigLoader(confPath);
		
		proxy = new ProxyParapheur(	configLoader.getendPoint(),
									configLoader.getUser(),
									configLoader.getPass(),
									configLoader.getTrustStorePath(),
									configLoader.getTrustStorePass(),
									configLoader.getKeyStorePath(),
									configLoader.getKeyStorePass());
		

		/* Récupération tout document en fin de circuit
		 * Création PDF
		 * Archivage du Dossier
		 */
		
		RechercherDossiersResponse rechercheDossiersResponse = ProxyParapheur.appelRechercheDossier(configLoader.getType(),configLoader.getStatus());
		if (rechercheDossiersResponse.getLogDossier() != null)
			for (LogDossier dossier : rechercheDossiersResponse.getLogDossier()){
				documentExploiteur = new DocumentExploiteur(ProxyParapheur.appelGetDossier(dossier.getNom()));
				if (documentExploiteur.isSigned()){
					documentExploiteur.writePdf(configLoader.getOutputPath());
					if (configLoader.doArchive())
						ProxyParapheur.appelArchiverDossier(dossier.getNom());
				}
			}
	}
}
