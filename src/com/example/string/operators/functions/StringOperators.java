package com.example.string.operators.functions;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class StringOperators {

	public static UnaryOperator<String> loweCaser() {
		// applay() method
		return (p) -> {
			return p.toLowerCase();
		};

	}

	public static UnaryOperator<String> upperCaser() {
		// applay() method
		return (p) -> {
			return p.toUpperCase();
		};

	}

	public static UnaryOperator<String> trimmer() {
		//body con unica riga e return sottinteso
		return p -> p.trim();
	}

	public static UnaryOperator<String> prefixer(final String prefix) {

		//ricordiamoci che la verifica formale dei paramentri sempre nei metodi public
		//nuova classa
		Objects.requireNonNull(prefix,"Messaggio per nullpointerException");
		return (p) -> {
			return prefix.concat(p);
		};

	}
	
	public static Predicate<String> removeIf(String charSequence){
		return p -> p.contains(charSequence);
	}
	

}
