///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Server.java
// File:             SimpleQueue.java
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
 * An ordered collection of items, where items are added to the rear
 * and removed from the front.
 */
public class SimpleQueue<E> implements QueueADT<E> {
	// the head for the chain
	private Listnode<E> head;
	// a reference to the rear
	private Listnode<E> tail;
	// holds the size
	private int size;

	/**
	 * initializes the SimpleQueue object and sets size to 0
	 */
	public SimpleQueue() {
		size = 0;
	}

	/**
	 * Adds an item to the rear of the queue.
	 * @param item the item to add to the queue.
	 * @throws IllegalArgumentException if item is null.
	 */
	public void enqueue(E item) {
		if (item == null) throw new IllegalArgumentException();
		// if the list is empty it simply creates and adds the node
		if (head == null) {
			head = new Listnode<E>(item);
			tail = head;
			++size;
		} else { // otherwise it adds it to the beginning
			Listnode<E> addMe = new Listnode<E>(item);
			addMe.setNext(head);
			head.setPrev(addMe);
			head = addMe;
			++size;
		}
	}

	/**
	 * Removes an item from the front of the Queue and returns it.
	 * @return the front item in the queue.
	 * @throws EmptyQueueException if the queue is empty.
	 */
	public E dequeue() {
		if (this.isEmpty()) {
			// throws if queue is empty
			throw new EmptyQueueException();
		}
		E returnValue = tail.getData();
		tail = tail.getPrev();
		// prevents a NullPointerException
		if (tail != null) {
			tail.setNext(null);
		}
		// decrememts size
		--size;
		return returnValue;

	}

	/**
	 * Returns the item at front of the Queue without removing it.
	 * @return the front item in the queue.
	 * @throws EmptyQueueException if the queue is empty.
	 */
	public E peek() {
		if (head == null) throw new EmptyQueueException();
		
		return tail.getData();
	}

	/**
	 * Returns true iff the Queue is empty.
	 * @return true if queue is empty; otherwise false.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Removes all items in the queue leaving an empty queue.
	 */
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Returns the number of items in the Queue.
	 * @return the size of the queue.
	 */
	public int size() {
		return size;
	}
}