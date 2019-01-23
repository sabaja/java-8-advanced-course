package com.example.maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MapComputeIfAbsentMain {

	public static void main(String[] args) {
		Map<String, List<String>> map = new HashMap<>();
		try (Scanner sc = new Scanner(System.in)) {
			do {
				String line = sc.nextLine();
				if (line.isEmpty())
					break;

				//come chiave uso la prima riga della linea
				String key = line.substring(0, 1);
				//se la lista è null creo l'istanza new ArrayList il key è ignorato
				map.computeIfAbsent(key, (k) -> new ArrayList<>()).add(line);
			} while (true);
		}

		map.forEach((k, v) -> System.out.printf("%s: %s%n", k, v));

	}

}
