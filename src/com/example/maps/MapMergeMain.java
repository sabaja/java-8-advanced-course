package com.example.maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MapMergeMain {

	/**
	 * per testarlo scrivere in 
	 * ciao 
	 * ciao 2 
	 * ciao 3 
	 * quando uno 
	 * quando 2 
	 * tre 
	 * tre 4 
	 * tra
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		try (Scanner sc = new Scanner(System.in)) {
			do {
				String line = sc.nextLine();
				if (line.isEmpty())
					break;

				// come chiave uso la prima riga della linea
				String key = line.substring(0, 1);
				// Concateno i valori con gli spazi
				map.merge(key, line, (oldV, newV) -> oldV + ", " + newV);

			} while (true);
		}

		map.forEach((k, v) -> System.out.printf("%s: %s%n", k, v));

	}

}
