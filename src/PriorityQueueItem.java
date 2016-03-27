///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  StudentCenter.java
// Files:            Course.java; EmptyQueueException.java; PriorityQueue.java; PriorityQueueItem.java;
// 					 PriorityQueueIterator.java ; Queue.java; Student.java; StudentCenter.java
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





/**
 *
 * Class to represent object stored at every entry in the PriorityQueue. ie, The
 * internal node structure of {@link PriorityQueue}
 *
 * @author CS367
 *
 * @param <E>
 *            the generic type of the data content stored in the list
 */
public class PriorityQueueItem<E> implements Comparable<PriorityQueueItem<E>>
{

	private int priority;
	private Queue<E> queue;

	public PriorityQueueItem(int priority)
	{
		this.priority= priority;
		this.queue= new Queue<>();
	}

	public int getPriority()
	{
		return this.priority;
	}

	public Queue<E> getList()
	{
		return this.queue;
	}

	/**
	 * Add an item to the queue of this PriorityQueueItem object
	 *
	 * @param item
	 *            item to add to the queue
	 */
	public void add(E item)
	{
		this.queue.enqueue(item);
	}

	/**
	 * Compares this Node to another node on basis of priority
	 *
	 * @param o
	 *            other node to compare to
	 * @return -1 if this node's priority is lesser, +1 if this nodes priority
	 *         is higher after, else 0 if priorities are the same.
	 */
	@Override
	public int compareTo(PriorityQueueItem<E> o)
	{
		if (o.getPriority()>this.getPriority()){
			return -1;
		}
		else if (o.getPriority()< this.getPriority()){
			return 1;
		}
		else {
			return 0;
		}
	}
}
