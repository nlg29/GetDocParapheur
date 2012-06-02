package org.adullact.clientParapheur;

import java.util.Map;
import javax.xml.ws.BindingProvider;
import org.adullact.spring_ws.iparapheur._1.*;


public class ProxyParapheur {

	static InterfaceParapheur service;
	
	public ProxyParapheur(String endpoint, String username, String password, String trustStorePath, String trustStorePass, String keyStorePath, String keyStorePass){
		//Initialisation du contexte et du service d'acces
		System.setProperty("javax.net.ssl.trustStore", trustStorePath);
		System.setProperty("javax.net.ssl.trustStorePassword", trustStorePass);
		System.setProperty("javax.net.ssl.keyStore", keyStorePath);
		System.setProperty("javax.net.ssl.keyStorePassword", keyStorePass);
    
		InterfaceParapheurService serviceLocator = new InterfaceParapheurService();
		service = serviceLocator.getInterfaceParapheurSoap11();
		Map<String, Object> requestContext = ((BindingProvider) service).getRequestContext();
	
		requestContext.put(BindingProvider.SESSION_MAINTAIN_PROPERTY, Boolean.TRUE);
		requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
		requestContext.put(BindingProvider.USERNAME_PROPERTY, username);
		requestContext.put(BindingProvider.PASSWORD_PROPERTY, password);
	}

	public static GetListeTypesResponse appelGetTypes(){
		GetListeTypesResponse getListeTypesResponse = service.getListeTypes("");
		return getListeTypesResponse;
	}

	public static GetListeSousTypesResponse appelGetSousTypes(String stype){
		return service.getListeSousTypes(stype);
	}

	public static String appelEcho(String str){
		return service.echo(str);
	}
	
	public static GetDossierResponse appelGetDossier(String id){
		return(service.getDossier(id));
	}
	
	public static RechercherDossiersResponse appelRechercheDossier(String type, String status){
		RechercherDossiersRequest rechercherDossiersRequest = new RechercherDossiersRequest();
		rechercherDossiersRequest.setTypeTechnique(type);
		rechercherDossiersRequest.setStatus(status);
		return service.rechercherDossiers(rechercherDossiersRequest);
	}
	
	public static void appelArchiverDossier(String id){
		ArchiverDossierRequest archiverDossierRequest = new ArchiverDossierRequest();
		archiverDossierRequest.setArchivageAction(ArchivageAction.ARCHIVER);
		archiverDossierRequest.setDossierID(id);
		service.archiverDossier(archiverDossierRequest);
	}
}
