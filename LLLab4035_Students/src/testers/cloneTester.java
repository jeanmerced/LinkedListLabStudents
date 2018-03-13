package testers;

import indexList.IndexList;
import indexList.LLIndexList;
import linkedLists.DLDHDTList;
import linkedLists.Node;
import linkedLists.SLFLList;
import linkedLists.SLList;

public class cloneTester {

	public static void main(String[] args) {	
		
		/** Testing SLFLList clone method **/
		System.out.println("Testing the clone method for SLFLList: ");
		SLFLList<Integer> theList = new SLFLList<>();
		Node<Integer> node1 = theList.createNewNode();
		node1.setElement(40);
		theList.addFirstNode(node1);
		Node<Integer> node2 = theList.createNewNode();
		node2.setElement(41);
		theList.addNodeAfter(node1, node2);
		Node<Integer> node3 = theList.createNewNode();
		node3.setElement(42);
		theList.addNodeAfter(node2, node3);
		Node<Integer> node4 = theList.createNewNode();
		node4.setElement(43);
		theList.addNodeAfter(node3, node4);
		
		Node<Integer> forNode = theList.getFirstNode();
		try {
			for(int i = 0; i < theList.length(); i++) {
				System.out.println("theList: element = " 
					+ forNode.getElement() + " size = " + theList.length());
				forNode = theList.getNodeAfter(forNode);
			}
		}
		catch(Exception e) {// do nothing
		}
		
		System.out.println();
		
		SLFLList<Integer> copyList = (SLFLList<Integer>) theList.clone();
		Node<Integer> forNodeCopy = copyList.getFirstNode();
		try {
			for(int i = 0; i < theList.length(); i++) {
				System.out.println("copyList: element = " 
					+ forNodeCopy.getElement() + " size = " + copyList.length());
				forNodeCopy = copyList.getNodeAfter(forNodeCopy);
			}
		}
		catch(Exception e) {// do nothing
		}
		
		System.out.println();
		
		/** Testing DLDHDTList clone method **/
		System.out.println("Testing the clone method for DLDHDTList: ");
		DLDHDTList<Integer> theDList = new DLDHDTList<>();
		Node<Integer> dnode1 = theDList.createNewNode();
		dnode1.setElement(112);
		theDList.addFirstNode(dnode1);
		Node<Integer> dnode2 = theDList.createNewNode();
		dnode2.setElement(113);
		theDList.addNodeAfter(dnode1, dnode2);
		Node<Integer> dnode3 = theDList.createNewNode();
		dnode3.setElement(114);
		theDList.addNodeAfter(dnode2, dnode3);
		Node<Integer> dnode4 = theDList.createNewNode();
		dnode4.setElement(115);
		theDList.addNodeAfter(dnode3, dnode4);
		
		Node<Integer> forNode2 = theDList.getFirstNode();
		try {
			for(int i = 0; i < theDList.length(); i++) {
				System.out.println("theList: element = " 
					+ forNode2.getElement() + " size = " + theDList.length());
				forNode2 = theDList.getNodeAfter(forNode2);
			}
		}
		catch(Exception e) {// do nothing
		}
		
		System.out.println();
		
		DLDHDTList<Integer> copyDList = (DLDHDTList<Integer>) theDList.clone();
		Node<Integer> forNodeCopy2 = copyDList.getFirstNode();
		try {
			for(int i = 0; i < theList.length(); i++) {
				System.out.println("copyDList: element = " 
					+ forNodeCopy2.getElement() + " size = " + copyDList.length());
				forNodeCopy2 = copyDList.getNodeAfter(forNodeCopy2);
			}
		}
		catch(Exception e) {
			// do nothing
		}
	}
}
