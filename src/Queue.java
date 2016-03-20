/**
 * An ordered collection of items, where items are added to the rear and removed
 * from the front.
 */
public class Queue<E> implements QueueADT<E>
	{

	// TODO
	// You may use a naive expandable circular array or a chain of listnodes.
	// You may NOT use Java's predefined classes such as ArrayList or
	// LinkedList.

	public Queue()
		{
		}

	/**
	 * Adds an item to the rear of the queue.
	 * 
	 * @param item
	 *            the item to add to the queue.
	 * @throws IllegalArgumentException
	 *             if item is null.
	 */
	public void enqueue(E item)
		{

		}

	/**
	 * Removes an item from the front of the Queue and returns it.
	 * 
	 * @return the front item in the queue.
	 * @throws EmptyQueueException
	 *             if the queue is empty.
	 */
	public E dequeue()
		{

		return null;
		}

	/**
	 * Returns the item at front of the Queue without removing it.
	 * 
	 * @return the front item in the queue.
	 * @throws EmptyQueueException
	 *             if the queue is empty.
	 */
	public E peek()
		{

		return null;
		}

	/**
	 * Returns true iff the Queue is empty.
	 * 
	 * @return true if queue is empty; otherwise false.
	 */
	public boolean isEmpty()
		{

		return true;
		}

	/**
	 * Removes all items in the queue leaving an empty queue.
	 */
	public void clear()
		{

		}

	/**
	 * Returns the number of items in the Queue.
	 * 
	 * @return the size of the queue.
	 */
	public int size()
		{
		return 0;
		}

	private void expandCapacity()
		{
		//expanding should be done using the naive copy-all-elements approach

		}
	}
