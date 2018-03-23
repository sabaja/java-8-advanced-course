package com.example.interfaccie.first.examples;

public interface HelloMain {

	/*
	 * metodo usato nelle interfaccie per creare staticamente una factory in java 8
	 * al posto di ex: Collection interface -> Collections [il quale si occupa anche
	 * di creare delle list (SyncronizedList)]
	 */
	public static void main(String[] args) {
		Greeter jacopo = Greeter.ofName("Jacopo");
		Greeter simona = Greeter.ofNameAnonymousClass("Simona");
		Greeter lorenzo = Greeter.ofNameWithAnonymousMethod("Lorenzo");
		Greeter lorenzo1 = Greeter.ofNameWithAnonymousMethod("Lorenzo");

		jacopo.greet();
		lorenzo.greet();
		simona.greet();
		System.out.println(jacopo);
		System.out.println(simona);
		System.out.println(lorenzo);
		System.out.println(lorenzo1);
		Greeter g =  (() -> {
			return "Implemented";
		});
		
	}		

	public default void factorymethod() {
	}
}
