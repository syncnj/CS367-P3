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
		// TODO write appropriate code
		// Check if array is full - double if necessary
		if (this.currentSize==this.array.length){
			doubleArray();
		}
		// Check all nodes and find if one with equal priority exists.
		// Add to the existing node's list if it does
		Boolean found=false;

		for (int i=1; i<=this.currentSize; i++){
			if (this.array[i].getPriority()== item.getPriority()){
				// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				found= true;
				this.array[i].add(item.getList().peek());
				System.out.print("existing node: "+ item.getPriority());
			}
		}
		if (!found){
			this.currentSize++;
			this.array[this.currentSize] = item;
			System.out.print("creating a new node at: "+ this.currentSize);
			percolateUp();

		}
		// Else create new node with value added to list and percolate it up
	}

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
				System.out.print("trying to swap nodes: " + parent + " & "+ child);
				child = parent;
			}
		}
	}

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
		// TODO write appropriate code
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
		// TODO write appropriate code - see PriortyQueueIterator constructor
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
		// TODO fill in appropriate code, replace default return statement
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
		// TODO
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
		// TODO write appropriate code
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
		// TODO
		Boolean done = false;
		int parent = hole;
		int child=-1;


		while (!done) {
			int child1 = parent*2;
			int child2 = parent*2+1;

			if (child1>this.currentSize){
				done = true;
			}
			else if (child2>this.currentSize){
				child = child1;
			}
			else {
				child = this.array[child1].compareTo(this.array[child2])>=0 ? child1: child2;
			}

			if (!done){
				if (child > this.currentSize) {
					done =true;
				}
				else if (this.array[parent].compareTo(this.array[child])>=0){
					done = true;
				}
				else {
					swapNodes(parent, child);
					System.out.print("Swapped: "+ parent + " & "+ child + "\n");
					parent = child;
				}
			}
		}
		System.out.println("Exit percolate down");
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
