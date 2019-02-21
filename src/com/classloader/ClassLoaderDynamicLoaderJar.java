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
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ClassLoaderDynamicLoaderJar {

	// Insert here bin location
	public static final String LIB_PATH = "C:\\Users\\sabatinija\\Desktop\\Devspace\\JAR";

	public static void main(String[] args) {
		System.out.println("Dentro initJarMail");
		List<String> fileNames = new ArrayList<>();

		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(LIB_PATH))) {
			System.out.println("Dentro inizializzazione");
			System.out.println("Path lib:" + LIB_PATH);

			for (Path path : directoryStream) {
				fileNames.add(path.toString());
			}

			boolean find = false;
			long start = System.nanoTime();
			for (String file : fileNames) {
				if (find = file.contains("mail.jar")) {

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
			long end = System.nanoTime();
			long elapsedTime = end - start;
			System.out.println("Start: " + start);
			System.out.println("End: " + end);
			if (find) {
				System.out
						.println("mail.jar trovato/tempo impiegato in nanos: " + (double) elapsedTime / 1_000_000_000);
			} else {
				System.out
						.println("mail.jar non trovato/tempo impiegato nanos: " + (double) elapsedTime / 1_000_000_000);
			}
		} catch (IOException | NoSuchMethodException | SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
