package com.unistar.app3;

import java.util.Arrays;

public class mainapp {
	public static void main( String[] ergs ) {
		System.out.println( "Hello World!" );

		MyStack<Integer> stack = new MyStack<Integer>(); 
		Integer[] arr1 = new Integer[]{ 1, 2, 3 };
		System.out.println("pushed : " + Arrays.toString(arr1));

		for (Integer ele: arr1) {
			stack.push(ele);
		}

		System.out.println("stack.size() : " + stack.size()); 
		while (!stack.isEmpty()){
			System.out.println( " " + stack.pop() );
		}
	}
}
