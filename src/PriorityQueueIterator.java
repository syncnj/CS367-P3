///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  StudentCenter.java
// Files:            PriorityQueueIterator.java
// Semester:         CS367 Spring 2016
//
// Author:           Yi Shen yshen59@wisc.edu
// CS Login:         sheny
// Lecturer's Name:  Jim Skretny
// Lab Section:      N/A
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Yifei Feng
// Email:            yfeng59@wisc.edu
// CS Login:         yifei
// Lecturer's Name:  Jim Skretny
// Lab Section:      N/A
//


import java.util.Iterator;
import java.util.NoSuchElementException;

public class PriorityQueueIterator<T> implements Iterator<PriorityQueueItem<T>>
	{

	private PriorityQueue<T> priorityQueue;

	/**
	 * Constructs a PriorityQueueIterator by utilizing a copy of the
	 * PriorityQueue. Hint : The local priorityQueue object need not be
	 * preserved, and hence you can use the dequeue() operation.
	 * 
	 * @param pq the original queue to iterate
	 */
	public PriorityQueueIterator(PriorityQueue<T> pq)
		{
		// This copies the contents of the passed parameter to the local object.
		// Hint : see copy constructor in PriorityQueue
			if (pq==null ) {
				throw new NullPointerException("NULL PriorityQueue passed in");
			}
			this.priorityQueue = new PriorityQueue<>(pq);
		}

	/**
	 * Returns true if the iteration has more elements.
	 * 
	 * @return true/false
	 */
	@Override
	public boolean hasNext()
		{
		return !this.priorityQueue.isEmpty();
		}

	/**
	 * Returns the next element in the iteration. The iterator should return the
	 * PriorityQueueItems in order of decreasing priority.
	 * 
	 * @return the next element in the iteration
	 * @throws NoSuchElementException
	 *             if the iteration has no more elements
	 */
	@Override
	public PriorityQueueItem<T> next()
		{
			if (this.priorityQueue.isEmpty()){
				throw new NoSuchElementException("Already traversed to the end");
			}
			return this.priorityQueue.dequeue();
		}

	/**
	 * Unsupported in this iterator for this assignment
	 */
	@Override
	public void remove()
		{
		// Do not implement
		throw new UnsupportedOperationException();
		}

	}
