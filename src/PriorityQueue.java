///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  StudentCenter.java
// Files:            PriorityQueue.java
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


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * PriorityQueue implemented as a Binary Heap backed by an array. Implements
 * QueueADT. Each entry in the PriorityQueue is of type PriorityQueueNode<E>.
 * First element is array[1]
 *
 * @author CS367
 *
 * @param <E>
 */
public class PriorityQueue<E> implements QueueADT<PriorityQueueItem<E>>
{
	private final int DEFAULT_CAPACITY = 100;

	// Number of elements in heap
	private int currentSize;

	/**
	 * The heap array. First element is array[1]
	 */
	private PriorityQueueItem<E>[] array;

	/**
	 * Construct an empty PriorityQueue.
	 */
	public PriorityQueue()
	{
		currentSize = 0;
		// the below code initializes the array.. similar code used for
		// expanding.
		array = (PriorityQueueItem<E>[]) Array.newInstance(PriorityQueueItem.class, DEFAULT_CAPACITY + 1);
	}

	/**
	 * Copy constructor
	 *
	 * @param pq
	 *            PriorityQueue object to be copied
	 */
	public PriorityQueue(PriorityQueue<E> pq)
	{
		if (pq == null){
			throw new NullPointerException("PriorityQueue to be copied is NULL");
		}
		this.currentSize = pq.currentSize;
		this.array = Arrays.copyOf(pq.array, currentSize + 1);
	}

	/**
	 * Adds an item to this PriorityQueue. If array is full, double the array
	 * size.
	 *
	 * @param item
	 *            object of type PriorityQueueItem<E>.
	 */
	@Override
	public void enqueue(PriorityQueueItem<E> item)
	{
		// Check if array is full - double if necessary
		if (this.currentSize==this.array.length-1){
			doubleArray();
		}
		// Check all nodes and find if one with equal priority exists.
		// Add to the existing node's list if it does
		if (item == null){
			throw new NullPointerException("PQitem passed in is NULL");
		}


		Boolean found=false;

		for (int i=1; i<=this.currentSize; i++){
			if (this.array[i].getPriority()== item.getPriority()){
				found= true;

				//Add everything in the item to the list
				Queue<E> itemQueue = item.getList();
				while (!itemQueue.isEmpty()){
					this.array[i].add(item.getList().dequeue());
				}

			}
		}

		// Else create new node with value added to list and percolate it up
		if (!found){
			this.currentSize++;
			this.array[this.currentSize] = item;
			percolateUp();

		}

	}
	/**
	 *
	 * PercolateUp the Priority Queue, make sure the items in the upper level has a higher priority than the lower level.
	 *

	 */
	private void percolateUp(){
		Boolean done = false;
		int child = this.currentSize;
		while (!done){
			int parent= child/2;
			if (parent ==0){
				done =true;
			}
			else if (this.array[child].compareTo(this.array[parent])<=0){
				done =true;
			}
			else {
				swapNodes(parent, child);
				child = parent;
			}
		}
	}
	/**
	 * Swap two nodes in the priority queue
	 *
	 * @param (parent) (the first item)
	 * @param (child) (the second item)
	 */
	private void swapNodes (int parent, int child){
		PriorityQueueItem<E> temp = this.array[parent];
		this.array[parent]= this.array[child];
		this.array[child]= temp;
	}


	/**
	 * Returns the number of items in this PriorityQueue.
	 *
	 * @return the number of items in this PriorityQueue.
	 */
	public int size()
	{
		return currentSize;
	}

	/**
	 * Returns a PriorityQueueIterator. The iterator should return the
	 * PriorityQueueItems in order of decreasing priority.
	 *
	 * @return iterator over the elements in this PriorityQueue
	 */
	public Iterator<PriorityQueueItem<E>> iterator()
	{
		return new PriorityQueueIterator<>(this);
	}

	/**
	 * Returns the largest item in the priority queue.
	 *
	 * @return the largest priority item.
	 * @throws NoSuchElementException
	 *             if empty.
	 */
	@Override
	public PriorityQueueItem<E> peek()
	{
		if (this.currentSize==0){
			throw new NoSuchElementException();
		}
		return this.array[1];
	}

	/**
	 * Removes and returns the largest item in the priority queue. Switch last
	 * element with removed element, and percolate down.
	 *
	 * @return the largest item.
	 * @throws NoSuchElementException
	 *             if empty.
	 */
	@Override
	public PriorityQueueItem<E> dequeue()
	{
		if (this.currentSize==0){
			throw new NoSuchElementException();
		}
		// Remove first element
		PriorityQueueItem<E> returnItem = this.array[1];
		// Replace with last element, percolate down
		this.array[1]= this.array[this.currentSize];
		this.currentSize--;
		this.percolateDown(1);
		return returnItem;
	}

	/**
	 * Heapify Establish heap order property from an arbitrary arrangement of
	 * items. ie, initial array that does not satisfy heap property. Runs in
	 * linear time.
	 */
	private void buildHeap()
	{
		for (int i = currentSize / 2;i > 0;i--)
			percolateDown(i);
	}

	/**
	 * Make this PriorityQueue empty.
	 */
	public void clear()
	{
		this.currentSize=0;
	}

	/**
	 * Internal method to percolate down in the heap. <a
	 * href="https://en.wikipedia.org/wiki/Binary_heap#Extract">Wiki</a>}
	 *
	 * @param hole
	 *            the index at which the percolate begins.
	 */
	private void percolateDown(int hole)
	{

		boolean done = false;
		while(!done){
			if((hole*2) > currentSize) done = true;
			else{
				int largestChildIndex = getLargestChildIndex(hole);
				PriorityQueueItem<E> current = array[hole];
				if(current.compareTo(array[largestChildIndex])<0){    //current item is lesser than its child
					swapNodes(hole,largestChildIndex);
					hole = largestChildIndex;
				}
				else {
					done = true;
				}
			}
		}

	}

	/**
	 * (Write a succinct description of this method here.  If necessary,
	 * additional paragraphs should be preceded by <p>, the html tag for
	 * a new paragraph.)
	 *
	 * @param (index) the index to find the largest child item
	 * @return  the index of the largest child item
	 */
	private int getLargestChildIndex(int index){
		if (2*index+1 > this.currentSize) return 2*index;//check if right exist
		PriorityQueueItem<E> left = array[2*index];
		PriorityQueueItem<E> right = array[2*index +1];

		if(left.getPriority()>right.getPriority()) return 2*index;
		else{
			return 2*index+1;
		}
	}






	/**
	 * Internal method to expand array.
	 */
	private void doubleArray()
	{
		PriorityQueueItem<E>[] newArray;

		newArray = (PriorityQueueItem<E>[]) Array.newInstance(PriorityQueueItem.class, array.length * 2);

		for (int i = 0;i < array.length;i++)
			newArray[i] = array[i];
		array = newArray;
	}

	@Override
	public boolean isEmpty()
	{
		if(currentSize == 0)
			return true;
		return false;
	}
}
