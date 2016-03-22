import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent every Course in our Course Registration environment
 * 
 * @author CS367
 *
 */

public class Course
	{

	private String courseCode;
	private String name;

	// Number of students allowed in the course
	private int maxCapacity;
	// Number of students enrolled in the course
	private int classCount;

	// The PriorityQueue structure
	private PriorityQueue<Student> registrationQueue;

	// List of students who are finally enrolled in the course
	private List<Student> courseRoster;

	public Course(String classCode, String name, int maxCapacity)
		{
		// TODO initialize all parameters
			this.courseCode= classCode;
			this.name= name;
			this.maxCapacity= maxCapacity;
			this.registrationQueue = new PriorityQueue<>();
			this.courseRoster = new ArrayList<>();
		}


	/**
	 * Creates a new PriorityqueueItem - with appropriate priority(coins) and
	 * this student in the item's queue. Add this item to the registrationQueue.
	 * 
	 * @param student
	 *            the student
	 * @param coins
	 *            the number of coins the student has
	 */
	public void addStudent(Student student, int coins)
		{
		// This method is called from Studentcenter.java

		// Enqueue a newly created PQItem.
		// Checking if a PriorityQueueItem with the same priority already exists
		// is done in the enqueue method.

		// TODO : see function header
			PriorityQueueItem<Student> newStudent = new PriorityQueueItem<>(coins);
			newStudent.add(student);
			this.registrationQueue.enqueue(newStudent);
		}

	/**
	 * Populates the courseRoster from the registration list.
	 * Use the PriorityQueueIterator for this task.
	 */
	public void processRegistrationList()
		{
		// TODO : populate courseRoster from registrationQueue
		// Use the PriorityQueueIterator for this task.
			PriorityQueueIterator<Student> itr = new PriorityQueueIterator<>(this.registrationQueue);
			while (itr.hasNext()){
				Queue<Student> temp = itr.next().getList();
				while (!temp.isEmpty() && this.courseRoster.size()< this.maxCapacity){
					this.courseRoster.add(temp.dequeue());
				}
			}
		}

	public String getCourseName()
		{
		// TODO - done
		return this.name;
		}

	public String getCourseCode()
		{
		// TODO -done
		return this.courseCode;
		}

	public List<Student> getCourseRegister()
		{
		// TODO -done
		return this.courseRoster;
		}
	}
