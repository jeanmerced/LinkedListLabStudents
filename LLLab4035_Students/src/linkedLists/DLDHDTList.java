package linkedLists;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

import linkedLists.AbstractSLList.SNode;

public class DLDHDTList<E> extends AbstractDLList<E> {
	private DNode<E> header, trailer; 
	private int length; 
	
	public DLDHDTList() { 
		header = new DNode<E>();
		trailer = new DNode<E>(null, header, null);
		header.setNext(trailer);
		length = 0;
	}
	
	public void addFirstNode(Node<E> nuevo) {
		addNodeAfter(header, nuevo); 
	}
	
	public void addLastNode(Node<E> nuevo) { 
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = trailer.getPrev();  
		nBefore.setNext(dnuevo); 
		trailer.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(trailer); 
		length++; 
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = (DNode<E>) target; 
		DNode<E> nAfter = nBefore.getNext(); 
		nBefore.setNext(dnuevo); 
		nAfter.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(nAfter); 
		length++; 
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		DNode<E> dnuevo = (DNode<E>) nuevo;
		DNode<E> nAfter = (DNode<E>) target;
		DNode<E> nBefore = nAfter.getPrev();
		nBefore.setNext(dnuevo); 
		nAfter.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(nAfter); 
		length++; 
	}

	public Node<E> createNewNode() {
		return new DNode<E>();
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return header.getNext();
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return trailer.getPrev();
	}

	public Node<E> getNodeAfter(Node<E> target)
			throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty.");
//		if(((DNode<E>) target).getNext() == trailer)
//			throw new NoSuchElementException("getNodeAfter(...): target is last node");
		return ((DNode<E>) target).getNext();
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty.");
//		if(((DNode<E>) target).getPrev() == header)
//			throw new NoSuchElementException("getNodeBefore(...): target is first node");
		return ((DNode<E>) target).getPrev();
	}

	public int length() {
		return length;
	}

	public void removeNode(Node<E> target) {
		DNode<E> dTarget = (DNode<E>) target;
		DNode<E> nAfter = dTarget.getNext();
		DNode<E> nBefore = dTarget.getPrev();
		nBefore.setNext(nAfter);
		nAfter.setPrev(nBefore);
		dTarget.clean();
		length--;
	}
	
	/**
	 * Prepares every node so that the garbage collector can free 
	 * its memory space, at least from the point of view of the
	 * list. This method is supposed to be used whenever the 
	 * list object is not going to be used anymore. Removes all
	 * physical nodes (data nodes and control nodes, if any)
	 * from the linked list
	 */
	private void destroy() {
		while (header != null) { 
			DNode<E> nnode = header.getNext(); 
			header.clean(); 
			header = nnode; 
		}
	}
	
	/**
	 * The execution of this method removes all the data nodes from
	 * the current instance of the list, leaving it as a valid empty
	 * doubly linked list with dummy header and dummy trailer nodes. 
	 */
	public void makeEmpty() { 
		// TODO
	}
		
	protected void finalize() throws Throwable {
	    try {
			this.destroy(); 
	    } finally {
	        super.finalize();
	    }
	}
	
	public Object[] toArray() { 
		Object[] array = new Object[this.length()]; 
		DNode<E> list = header.getNext();
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
	    
	    DNode<E> list = header.getNext();
	    for (int i = 0; i < length; i++) {
	    		array[i] = (T) list.getElement(); // It is assumed element[i] can be casted to T
	        list = list.getNext();
	    }
	    return array;	
	}
	
	public Object clone() {
		DLDHDTList<E> copyList = new DLDHDTList<>();
		DNode<E> copyNode = new DNode<>();
		DNode<E> currentNode = header.getNext();
		copyNode.setElement(currentNode.getElement());
		copyList.addFirstNode(copyNode);
		currentNode = currentNode.getNext();
		for(int i = 1; i < length; i++) {
			copyList.addNodeAfter(copyNode, new DNode<E>(currentNode.getElement()));
			currentNode = currentNode.getNext();
			copyNode = copyNode.getNext();
		}
		return copyList;
	}

}
