package com.classloader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ClassLoaderDynamicLoaderJar {

	// Insert here bin location
	public static final String LIB_PATH = "";

	public static void main(String[] args) {
		System.out.println("Dentro initJarMail");
		List<String> fileNames = new ArrayList<>();
		
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(LIB_PATH))) {
			System.out.println("Dentro inizializzazione");
			System.out.println("Path lib:" + LIB_PATH);
			
			for (Path path : directoryStream) {
				fileNames.add(path.toString());
			}

			for (String file : fileNames) {
				if (file.contains("mail.jar")) {
					System.out.println("mail.jar trovato");

					File myfile = new File(file);
					URL jarFile = new URL("jar", "file", myfile.getAbsolutePath() + "!/");

					System.out.println("Url jarFile è corretto? " + myfile.canExecute());



					URLClassLoader sysLoader = new URLClassLoader(new URL[] { jarFile },
							ClassLoader.getSystemClassLoader());
					System.out.println("URLClassLoader sysLoader caricato");

					Class<URLClassLoader> sysClass = URLClassLoader.class;

					Method sysMethod = sysClass.getDeclaredMethod("addURL", new Class[] { URL.class });
					System.out.println("Metodo sysMethod creato");

					sysMethod.setAccessible(true);
					System.out.println("sysMethod.setAccessible(true)");

					Object obj = sysMethod.invoke(sysLoader, new Object[] { jarFile });

					System.out.println("Metodo invocato con " + obj.getClass().getClassLoader().toString());
					System.out.println("Fine initJarMail");
				}
			}
		} catch (IOException | NoSuchMethodException | SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
