package org.adullact.clientParapheur;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.adullact.spring_ws.iparapheur._1.DocumentExploiteur;
import org.adullact.spring_ws.iparapheur._1.LogDossier;
import org.adullact.spring_ws.iparapheur._1.RechercherDossiersResponse;

public class Client {

	/**
	 TODO Ranger un peu c'est déja le boxon
	 */
	
	static DocumentExploiteur documentExploiteur;
	static ProxyParapheur proxy;
	static String confPath = "conf.cf";
	static ConfigLoader configLoader;
	
	public static void main(String[] args) {

		// ##### Creation du log
		Logger logger;
		logger = Logger.getLogger("LogTout");
		
		try {
			FileHandler fh=new FileHandler("ClientParapheur.log");
			logger.addHandler(fh);
			logger.setUseParentHandlers(false);
			fh.setFormatter(new FormatterPerso());	
		} catch (SecurityException e) 	{	e.printStackTrace();}
		  catch (IOException e1) 		{	e1.printStackTrace();}
		
		// ##### Chargement de la configuration
		configLoader = new ConfigLoader(confPath);
		
		proxy = new ProxyParapheur(	configLoader.getendPoint(),
									configLoader.getUser(),
									configLoader.getPass(),
									configLoader.getTrustStorePath(),
									configLoader.getTrustStorePass(),
									configLoader.getKeyStorePath(),
									configLoader.getKeyStorePass());
		


		
		// ##### Recherche de l'ensemble des dossiers en fin de circuit
		RechercherDossiersResponse rechercheDossiersResponse = ProxyParapheur.appelRechercheDossier(configLoader.getType(),configLoader.getStatus());
		
		//Action principale : Si signé, alors copie sur FS - PUIS si demandé, suppression/archivage 
		if (rechercheDossiersResponse.getLogDossier() != null){		// #### Si des dossiers sont à traiter
			logger.log(Level.INFO, "Nombre de dossier en fin de circuit: "+rechercheDossiersResponse.getLogDossier().size() );
			for (LogDossier dossier : rechercheDossiersResponse.getLogDossier()){
				documentExploiteur = new DocumentExploiteur(ProxyParapheur.appelGetDossier(dossier.getNom()));
				
				if (documentExploiteur.isSigned()){				// #### Si des dossiers sont à signés
				
					logger.log(Level.INFO, " Le dossier : "+dossier.getNom() + " est signé ! \n-      Copie sur disque" );
					documentExploiteur.writePdf(configLoader.getOutputPath());
					
					if (configLoader.doArchive()){				// #### Si dans la conf, il faut archiver le dossier
						logger.log(Level.INFO, "  Copie effectuée, archivage / suppression du dossier : "+dossier.getNom() );
						ProxyParapheur.appelArchiverDossier(dossier.getNom());
					}
				}else{
					logger.log(Level.INFO, " Le dossier : "+dossier.getNom() + " n'est pas signé - On l'ignore" );
				}
			}
		}
	}
}