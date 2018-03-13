package linkedLists;
/**
 * Singly linked list with references to its first and its
 * last node. 
 * 
 * @author pirvos
 *
 */

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

import linkedLists.LinkedList;
import linkedLists.AbstractSLList.SNode;

public class SLFLList<E> extends SLList<E>
{
	private SNode<E> first, last;   // reference to the first node and to the last node
	int length; 
	
	public SLFLList() {       // to create an empty list instance
		first = last = null; 
		length = 0; 
	}
	
	
	public void addFirstNode(Node<E> nuevo) {
		//pre: nuevo is not a node in the list
		((SNode<E>) nuevo).setNext(first);
		first = (SNode<E>) nuevo; 
		length++; 
		if(length == 1)
			last = first;
	}
	
	public void addLastNode(Node<E> nuevo) {
		((SNode<E>) nuevo).setNext(null);
		if (length == 0)
			first = (SNode<E>) nuevo;
		else {   // find current last node and add the new one after that last node
			last.setNext((SNode<E>) nuevo);
		}
		last = (SNode<E>) nuevo;
		length++; 
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		//pre: target is a node in the list
		//pre: nuevo is not a node in the list
		if(target == last) {
			this.addLastNode(nuevo);
			last = (SNode<E>) nuevo;
		}
		else {
			((SNode<E>) nuevo).setNext(((SNode<E>) target).getNext());
			((SNode<E>) target).setNext((SNode<E>) nuevo);
			length++;
		}
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		//pre: target is a node in the list
		//pre: nuevo is not a node in the list
		if(target == first)
			this.addFirstNode(nuevo);
		else {
			Node<E> prevNode = getNodeBefore(target);  
			this.addNodeAfter(prevNode, nuevo); 
			length++;
		}	
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if(first == null)
			throw new NoSuchElementException("getFirstNode() : linked list is empty..."); 
		
		// the linked list is not empty....
		return first;
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if(last == null)
			throw new NoSuchElementException("getLastNode() : linked list is empty..."); 
		
		// the linked list is not empty....
		return last;
	}

	public Node<E> getNodeAfter(Node<E> target) throws NoSuchElementException {
		//pre: target is a node in the list
		if (target == last)  
			throw new NoSuchElementException("getNodeLast(...) : target is the last node."); 
		return ((SNode<E>) target).getNext();
	}

	public Node<E> getNodeBefore(Node<E> target) throws NoSuchElementException {
		//pre: target is a node in the list
		if (target == first)  
			throw new NoSuchElementException("getNodeBefore(...) : target is the first node."); 
		else {
			SNode<E> prev = first; 
			while (prev != null && prev.getNext() != target) 
				prev = prev.getNext(); 
			return prev; 
		}
	}

	public int length() {
		return this.length;
	}

	public void removeNode(Node<E> target) {
		// Pre: target is a node in the list; hence, the list is not empty
		
		if (target == first) 
			first = first.getNext(); 
		else if(target == last) 
			last = (SNode<E>) this.getNodeBefore(target);
		else{ 
			SNode<E> prevNode = (SNode<E>) this.getNodeBefore(target); 
			prevNode.setNext(((SNode<E>) target).getNext()); 
		}
		((SNode<E>) target).clean();   // clear all references from target
		length--; 
		if(length == 1)
			first = last;
	}
	
	public Node<E> createNewNode() {
		return new SNode<E>();
	}
	
	public Object[] toArray() { 
		Object[] array = new Object[this.length()]; 
		SNode<E> list = first;
	    for (int i = 0; i < length; i++) {
	        array[i] = list.getElement();
	        	list = list.getNext();
	    }
	    return array;	
	}
	
	public <T> T[] toArray(T[] array) { 
	    if (array.length < length) { 
	    // if array.length < size, allocate a new array of the same 
	    	// type as array (components of the new array are set to be of equal
	    // runtime type as components of the original array array) 
	    	// and big enough to hold all the elements in this set. For 
	    	// this, we need to use the Array class....
	    	array = (T[]) Array.newInstance(array.getClass().getComponentType(), length);
	    } else if (array.length > length) 
	    	// Set to null all those positions of array that won't be used. 
	    	for (int j = length; j < array.length; j++) 
	    		array[j] = null;
	    
	    SNode<E> list = first;
	    for (int i = 0; i < length; i++) {
	    		array[i] = (T) list.getElement(); // It is assumed element[i] can be casted to T
	        list = list.getNext();
	    }
	    return array;	
	}
	
	public Object clone() {
		SLFLList<E> copyList = new SLFLList<>();
		SNode<E> copyNode = new SNode<>();
		SNode<E> currentNode = first;
		copyNode.setElement(currentNode.getElement());
		copyList.addFirstNode(copyNode);
		currentNode = currentNode.getNext();
		for(int i = 1; i < length; i++) {
			copyList.addNodeAfter(copyNode, new SNode<E>(currentNode.getElement()));
			currentNode = currentNode.getNext();
			copyNode = copyNode.getNext();
		}
		return copyList;
	}
}
