package com.unistar.app3;

import java.util.EmptyStackException;

/**
 * demo for TDD (Test Driven Development):
 * implement Stack with only basic data type, not use Array : instead using inner class Node
 * @param <E>
 */
public class MyStack<E> {
	private int length = 0;
	private Node header = null;
	class Node{
		private E data;
		private Node next;
	}

	public boolean isEmpty(){
		return (this.length == 0 || header == null);
	}

	public void push(E element){
		Node temp = new Node();
		temp.data = element;
		temp.next = header;
		this.length++;
		header = temp;
	}

	public E peek(){
		if(this.isEmpty()) {
			return null;
		}

		return this.header.data;
	}

	public E pop(){
		if(this.isEmpty()) {
			throw new EmptyStackException();
		}

		E temp = this.header.data;
		this.header = this.header.next;
		this.length--;
		return temp;
	}

	public int size(){
		return this.length;
	}
}
