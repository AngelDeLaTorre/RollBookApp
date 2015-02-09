package classes;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

import interfaces.ReverseIterator;
import interfaces.SortedList;


public class SortedDoublyList<E extends Comparable<E>> implements SortedList<E>, Serializable{


	private int currentSize;
	private Node header;
	private Node tail;

	private class Node implements Serializable {
		
		private E value;
		private Node next;
		private Node previous;

		public E getValue() {
			return value;
		}
		public void setValue(E value) {
			this.value = value;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		public Node getPrevious() {
			return previous;
		}
		public void setPrevious(Node previous) {
			this.previous = previous;
		}	
	}

	public SortedDoublyList(){
		this.header = new Node();
		this.tail = new Node();
		this.header.setNext(this.tail);
		this.tail.setPrevious(this.header);
		this.currentSize = 0;
	}


	private class ListIterator implements Iterator<E>{

		private Node nextNode;

		public ListIterator(){
			this.nextNode = header.getNext();
		}
		
		public ListIterator(int index){
			int counter=0;
			Node temp;
			for(temp=header.getNext();counter<index;temp = temp.getNext(), counter++);

			this.nextNode = temp;
		}
		
		

		@Override
		public boolean hasNext() {
			return nextNode != tail;
		}

		@Override
		public E next() {
			if (hasNext()){
				E result = this.nextNode.getValue();
				this.nextNode = this.nextNode.getNext();
				return result;
			}
			else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();

		}

	}


	private class ReverseListIterator implements ReverseIterator<E>{

		private Node prevNode;

		public ReverseListIterator(){
			this.prevNode = tail.getPrevious();
		}


		@Override
		public boolean hasPrevious() {
			return prevNode != header;
		}

		@Override
		public E previous() {
			if (hasPrevious()){
				E result = this.prevNode.getValue();
				this.prevNode = prevNode.getPrevious();
				return result;
			}
			else {
				throw new NoSuchElementException();

			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();

		}


	}


	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	@Override
	public Iterator<E> iterator(int index) {
		return new ListIterator(index);
	}

	public ReverseIterator<E> reverseIterator() {
		return new ReverseListIterator();

	}

	@Override
	public void add(E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Parameter cannot be null.");
		}

		if(this.isEmpty())
		{
			Node newNode = new Node();
			newNode.setValue(obj);
			this.tail.getPrevious().setNext(newNode);
			newNode.setPrevious(this.tail.getPrevious());
			this.tail.setPrevious(newNode);
			newNode.setNext(this.tail);
			this.currentSize++;
		}
		else{
			sortMyList(obj);
			this.currentSize++;
		}

	}

	private void sortMyList(E obj) {
		Node temp=this.header.getNext();
		
		for(temp= this.header.getNext() ; temp!=tail   ; temp= temp.getNext())
		{
			int counter =  obj.compareTo(temp.getValue());

			if(counter<0)
			{
				Node newNode = new Node();
				newNode.setValue(obj);
				newNode.setPrevious(temp.getPrevious());
				temp.getPrevious().setNext(newNode);
				newNode.setNext(temp);
				temp.setPrevious(newNode);
				return;
			}
		}
		Node newNode = new Node();
		newNode.setValue(obj);
		newNode.setPrevious(temp.getPrevious());
		temp.getPrevious().setNext(newNode);
		newNode.setNext(temp);
		temp.setPrevious(newNode);
	}

	@Override
	public int size() {

		return this.currentSize;
	}

	@Override
	public boolean remove(E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Parameter cannot be null.");
		}
		Node temp = null;
		for (temp = this.header.getNext(); temp != tail; temp = temp.getNext()){
			if (temp.getValue().equals(obj)){
				// found first copy
				if (temp.getNext() != null){
					temp.getNext().setPrevious(temp.getPrevious());
				}
				temp.getPrevious().setNext(temp.getNext());
				temp.setValue(null);
				temp.setNext(null);
				temp.setPrevious(null);
				this.currentSize--;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean remove(int index) {
		if ((index < 0) || (index >= this.size())){
			throw new IndexOutOfBoundsException();
		}

		Node temp = null;
		int counter = 0;
		for (temp = this.header.getNext(); counter < index; temp = temp.getNext(), counter++);
		if (temp.getNext() != null){
			temp.getNext().setPrevious(temp.getPrevious()); // null if temp is the last one
		}
		temp.getPrevious().setNext(temp.getNext());
		temp.setValue(null);
		temp.setNext(null);
		temp.setPrevious(null);
		this.currentSize--;
		return true;
	}

	@Override
	public int removeAll(E obj) {
		int counter = 0;
		while(this.remove(obj)){
			counter++;
		}
		return counter;
	}

	@Override
	public E first() {
		if (this.isEmpty()){
			return null;
		}
		else {
			return this.header.getNext().getValue();
		}
	}

	@Override
	public E last() {
		if (this.isEmpty()){
			return null;
		}
		else {
			return this.tail.getPrevious().getValue();
		}
	}

	@Override
	public E get(int index) {
		if ((index < 0) || (index >= this.size())){
			throw new IndexOutOfBoundsException();
		}
		else {
			int counter = 0;
			Node temp = null;
			for (temp = this.header.getNext(); counter < index; temp = temp.getNext(), counter++);
			return temp.getValue();
		}
	}

	@Override
	public E set(int index, E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Parameter cannot be null.");
		}

		if ((index < 0) || (index >= this.size())){
			throw new IndexOutOfBoundsException();
		}
		else {
			int counter = 0;
			Node temp = null;
			for (temp = this.header.getNext(); counter < index; temp = temp.getNext(), counter++);
			E result = temp.getValue();
			temp.setValue(obj);
			return result;
		}
	}

	@Override
	public void clear() {
		while (!this.isEmpty()){
			this.remove(0);
		}

	}

	@Override
	public boolean contains(E obj) {
		return this.firstIndex(obj) >= 0;
	}

	@Override
	public boolean isEmpty() {
		return this.size()==0;
	}

	@Override
	public int firstIndex(E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Parameter cannot be null.");
		}
		else {
			int counter = 0;
			Node temp = null;
			for (temp = this.header.getNext(); temp != tail; temp = temp.getNext(), counter++){
				if (temp.getValue().equals(obj)){
					return counter;
				}
			}
			return -1;
		}
	}

	@Override
	public int lastIndex(E obj) {
		if (obj == null){
			throw new IllegalArgumentException("Parameter cannot be null.");
		}
		else {
			int counter =0, lastSeen = -1;
			Node temp = null;
			for (temp = this.header.getNext(); temp != tail; temp = temp.getNext(), counter++){
				if (temp.getValue().equals(obj)){
					lastSeen = counter;
				}
			}
			return lastSeen;
		}
	}

}

