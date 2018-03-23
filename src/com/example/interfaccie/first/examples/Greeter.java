package com.example.helloworld.interfaccie.first.examples;

@FunctionalInterface
public interface Greeter {
	// metodo unico da implementare
	public abstract String getName();

	public default void greet() {
		System.out.printf("Hello %s%n", getName());
	}

	// Creazione metodo factory che dia un'implentazione di riferimento per la mia
	// interface Greeter
	// fuori interfaccia newName or createName dentro interfaccia java 8 ofName
	public static Greeter ofName(final String name) {

		// definisco una classe a livello di metodo ed è visibile solo all'interno dell
		// metodo
		class DefaultGreeter implements Greeter {

			// questo metodo ritorna la variabile name implicitamente final in java 8.
			@Override
			public String getName() {
				return name;
			}
		}
		return new DefaultGreeter();
	}

	// versione anonima
	// implicitamente final
	public static Greeter ofNameAnonymousClass(String name) {

		// oppure:
		// Greeter g = new Greeter() e return g;

		return new Greeter() {

			@Override
			public String getName() {
				return name;
			}
		};
	}

	//
	public static Greeter ofNameWithAnonymousMethod(String name) {

//		Interfaccia implicitamente instanziata (niente new Greeter())
		return () -> {
			return name;

		};
	}
}
