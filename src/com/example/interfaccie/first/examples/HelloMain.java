package com.example.interfaccie.first.examples;

public interface HelloMain {

	/*
	 * metodo usato nelle interfaccie per creare staticamente una factory in java 8
	 * al posto di ex: Collection interface -> Collections [il quale si occupa anche
	 * di creare delle list (SyncronizedList)]
	 */
	public static void main(String[] args) throws InterruptedException {
		Greeter jacopo = Greeter.ofName("Jacopo");
		Greeter simona = Greeter.ofNameAnonymousClass("Simona");
		Greeter lorenzo = Greeter.ofNameWithAnonymousMethod("Lorenzo");
		Greeter lorenzo1 = Greeter.ofNameWithAnonymousMethod("Lorenzo");
		Greeter g = (() -> {
			return "Implemented";
		});

		jacopo.greet();
		lorenzo.greet();
		simona.greet();
		System.out.println(g.getName());
		System.out.println("Here belove memory address of objects:");

		for (int i = 0; i < 3; i++) {
			
				System.out.println("[" + i + "]");
				Thread.currentThread();
				Thread.sleep(1000);
			
			
		}
		System.out.println(jacopo);
		System.out.println(simona);
		System.out.println(lorenzo);
		System.out.println(lorenzo1);
		System.out.println(g);

	}

}
