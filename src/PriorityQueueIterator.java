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
	 * @param pq
	 */
	public PriorityQueueIterator(PriorityQueue<T> pq)
		{
		// TODO
		// This copies the contents of the passed parameter to the local object.
		// Hint : see copy constructor in PriorityQueue
		}

	/**
	 * Returns true if the iteration has more elements.
	 * 
	 * @return true/false
	 */
	@Override
	public boolean hasNext()
		{
		// TODO
		return false;
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
		// TODO
		return null;
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
