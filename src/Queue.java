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
    private final int DEFAULT_LENGTH=100;
    private E[] list;
    private int numItems=0;
    private int front=1;
    private int rear=0;

    public Queue()
    {
        list =(E[])new Object[DEFAULT_LENGTH];
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
        if (numItems == list.length){
            expandCapacity();
        }
        rear = incrementIndex(rear);
        numItems++;
        list[rear] = item;
    }



    private int incrementIndex(int input){

        if (input==list.length){
            input=-1;
        }
        return ++input;
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
        if (numItems==0){
            throw new EmptyQueueException();
        }
        E temp= list[front];
        front = incrementIndex(front);
        numItems--;
        return temp;
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

        return list[front];
    }

    /**
     * Returns true iff the Queue is empty.
     *
     * @return true if queue is empty; otherwise false.
     */
    public boolean isEmpty()
    {

        return numItems==0;
    }

    /**
     * Removes all items in the queue leaving an empty queue.
     */
    public void clear()
    {
        numItems=0;
        front=1;
        rear=0;
    }

    /**
     * Returns the number of items in the Queue.
     *
     * @return the size of the queue.
     */
    public int size()
    {
        return numItems;
    }

    private void expandCapacity()
    {
        //expanding should be done using the naive copy-all-elements approach
        E[] newList = (E[])new Object[2*numItems];
        System.arraycopy(list,front, newList,0, list.length-front);
        System.arraycopy(list,0,newList,list.length-front, front);
    }
}
