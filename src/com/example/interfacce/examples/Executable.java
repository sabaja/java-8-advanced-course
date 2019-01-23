package com.example.interfacce.examples;

import java.util.Arrays;
import java.util.List;

public interface Executable {
	public static void main(String[] args) {
		FunctionalImplementable<Integer> functionalImplementable = (l) -> {
			l.forEach(System.out::println);
		};
		List<Integer> ll = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
		functionalImplementable.method(ll);
		functionalImplementable.add(ll, Integer.valueOf(12));

	}
}

@FunctionalInterface
interface FunctionalImplementable<T> {
	public abstract void method(List<T> list);

	public default boolean add(List<T> list, T e) {
		return list.add(e);
	}
}