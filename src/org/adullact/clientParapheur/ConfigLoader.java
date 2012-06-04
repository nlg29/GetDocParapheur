package org.adullact.clientParapheur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
	
	Properties cf;
	private static String type,status,outputPath,endPoint,user,pass,trustStorePass,trustStorePath,keyStorePath,keyStorePass,actionArchive;
	
	public ConfigLoader(String path){
		//Chargemenet de l'ensemble de la config 
		// TO DO .. Tester la bonne config & remonter erreur
		
		cf = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(path);
			cf.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Impossible de trouver le fichier "+System.getProperty("user.dir")+"/"+path);
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Problème lors de l'ouverture du fichier "+System.getProperty("user.dir")+"/"+path);
			System.exit(0);
		}
		
		endPoint 		= cf.getProperty("endPoint");
		user 			= cf.getProperty("user");
		pass 			= cf.getProperty("pass");
		trustStorePass 	= cf.getProperty("trustStorePass");
		trustStorePath 	= cf.getProperty("trustStorePath");
		keyStorePass 	= cf.getProperty("keyStorePass");
		keyStorePath 	= cf.getProperty("keyStorePath");
		type 			= cf.getProperty("type");
		status 			= cf.getProperty("status");
		outputPath 		= cf.getProperty("outputPath");
		actionArchive	= cf.getProperty("actionArchive");
		
		VerifPresenceFichier();
	}

	public void VerifPresenceFichier(){
		File f = new File("iparapheur.wsdl");
		if (!f.exists() ) {
			System.out.println("Impossible de trouver le fichier "+System.getProperty("user.dir")+"/iparapheur.wsdl");
			System.exit(0);
		}
		
		f = new File (trustStorePath);
		if (!f.exists() ) {
			System.out.println("Impossible de trouver le fichier "+ trustStorePath);
			System.exit(0);
		}
		
		f = new File (keyStorePath);
		if (!f.exists() ) {
			System.out.println("Impossible de trouver le fichier "+ keyStorePath);
			System.exit(0);
		}
	}
	
	public boolean doArchive(){
		if (actionArchive.equals("archiver"))
			return true;
		return false;
	}
	
	public String getActionArchive() {
		return actionArchive;
	}

	public String getType() {
		return type;
	}

	public  String getStatus() {
		return status;
	}

	public  String getOutputPath() {
		return outputPath;
	}

	public  String getendPoint() {
		return endPoint;
	}

	public  String getUser() {
		return user;
	}

	public  String getPass() {
		return pass;
	}

	public  String getTrustStorePass() {
		return trustStorePass;
	}

	public  String getTrustStorePath() {
		return trustStorePath;
	}

	public  String getKeyStorePath() {
		return keyStorePath;
	}

	public  String getKeyStorePass() {
		return keyStorePass;
	}

}
