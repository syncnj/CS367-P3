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
		// Use the PriorityQueueIterator for this task.
			PriorityQueueIterator<Student> itr = new PriorityQueueIterator<>(this.registrationQueue);

			boolean courseIsFull=false;
			//Signal course full for faster course processing

			while (itr.hasNext() && !courseIsFull){
				Queue<Student> studentList = itr.next().getList();
				if (this.classCount>= this.maxCapacity){
					courseIsFull=true;
				}
				while (!studentList.isEmpty() && !courseIsFull){
					this.courseRoster.add(studentList.dequeue());
					this.classCount++;
				}
			}
		}

	public String getCourseName()
		{
		return this.name;
		}

	public String getCourseCode()
		{
		return this.courseCode;
		}

	public List<Student> getCourseRegister()
		{
		return this.courseRoster;
		}
	}
