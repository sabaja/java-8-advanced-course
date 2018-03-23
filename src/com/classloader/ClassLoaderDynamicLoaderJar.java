package com.classloader;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;


public class ClassLoaderDynamicLoaderJar {
	public static void main(String[] args) 
		{
//			File mailJar = new File("\\\\labswincrm101.si.it\\pt8.53\\webserv\\TEST\\applications\\peoplesoft\\PSIGW.war\\WEB-INF\\lib");
			
			final Class<?> referenceClass = ClassLoaderDynamicLoaderJar.class;
			final URL url =
			    referenceClass.getProtectionDomain().getCodeSource().getLocation();

			try{
			    final File jarPath = new File(url.toURI()).getParentFile();
			    System.out.println("path: " + jarPath.getPath() + " path: " + jarPath.getAbsolutePath()); // this is the path you want
			    
			} catch(final URISyntaxException e){
			    // etc.
			}

		}
			
		
}
