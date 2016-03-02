///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Server.java
// File:             SimpleStack.java
// Semester:         CS367 Fall 2015
//
// Author:           Thomas Hansen
// CS Login:         thansen
// Lecturer's Name:  Jim Skrentny
// Lab Section:      2
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     N/A
// Email:            N/A
// CS Login:         N/A
// Lecturer's Name:  N/A
// Lab Section:      N/A
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          none
//
// Online sources:   none
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * An ordered collection of items, where items are both added
 * and removed from the top.
 * @author CS367
 */
public class SimpleStack<E> implements StackADT<E> {

	// Listnode class allows for doubly linked lists, but we'll only
	// be using 'next' for simplicity, and the same class for consistency.
	private Listnode<E> head;
	private int size;

	/**
	 * Constructs the SimpleStack object and initializes to 0
	 */
	public SimpleStack() {
		size = 0;
	}

	/**
	 * Adds item to the top of the stack.
	 * @param item the item to add to the stack.
	 * @throws IllegalArgumentException if item is null.
	 */
	public void push(E item) {
		if (item == null) throw new IllegalArgumentException();
		
		Listnode<E> addMe = new Listnode<E>(item);
		if (head != null) {
			addMe.setNext(head);
		}
		++size;
		head = addMe;
	}

	/**
	 * Removes the item on the top of the Stack and returns it.
	 * 
	 * @return the top item in the stack.
	 * @throws EmptyStackException if the stack is empty.
	 */
	public E pop() {
		try {
			E returnValue = head.getData();
			head = head.getNext();
			--size;
			return returnValue;
		} catch (Exception e) {
			throw new EmptyStackException();
		}
	}

	/**
	 * Returns the item on top of the Stack without removing it.
	 * 
	 * @return the top item in the stack.
	 * @throws EmptyStackException if the stack is empty.
	 */
	public E peek() {
		return head.getData();
	}

	/**
	 * Returns true iff the Stack is empty.
	 * @return true if stack is empty; otherwise false.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Removes all items on the stack leaving an empty Stack. 
	 */
	public void clear() {
		head = null;
		size = 0;
	}

	/**
	 * Returns the number of items in the Stack.
	 * @return the size of the stack.
	 */
	public int size() {
		return size;
	}
}