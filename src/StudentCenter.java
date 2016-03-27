///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Student Center
// Files:            Course.java; EmptyQueueException.java; PriorityQueue.java; PriorityQueueItem.java;
// 					 PriorityQueueIterator.java ; Queue.java; Student.java; StudentCenter.java
// Semester:         CS367 Spring 2016
//
// Author:           Yi Shen; Yifei Feng
// Email:            yshen59@wisc.edu; yfeng59@wisc.edu
// CS Login:         sheny; yifei
// Lecturer's Name:  Jim Skretny
// Lab Section:      N/A
//


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * Student Center abstraction for our simulation. Execution starts here.
 * 
 * @author CS367
 *
 */
public class StudentCenter
	{

	private static int DEFAULT_POINTS = 100;
	// Global lists of all courses and students
	private static List<Course> courseList = new ArrayList<Course>();
	private static List<Student> studentList = new ArrayList<Student>();

	public static void main(String[] args)
		{
		if(args.length != 3)
			{
			System.err.println("Bad invocation! Correct usage: " + "java StudentCentre <StudentCourseData file>" + "<CourseRosters File> + <StudentCourseAssignments File>");
			System.exit(1);
			}

		boolean didInitialize = readData(args[0]);

		if(!didInitialize)
			{
			System.err.println("Failed to initialize the application!");
			System.exit(1);
			}
		generateAndWriteResults(args[1], args[2]);
		}

	/**
	 * 
	 * @param fileName
	 *            - input file. Has 3 sections - #Points/Student, #Courses, and
	 *            multiple #Student sections. See P3 page for more details.
	 * @return true on success, false on failure.
	 * 
	 */
	public static boolean readData(String fileName)
		{
		try
			{
			// make sure to call course.addStudent() appropriately.

				File file = new File (fileName);
				Scanner scnr = new Scanner(file);

				//currentType: 0: N/A, 1:Points/Student, 2:Courses, 3: Student name, 4:Stu ID, 5: Course choice
				int currentType =0;

				String delim = " ";

				//Allocate variables for new Student
				String newStudentName= null;
				Student newStudent= null;
				String newStudentID;

				while (scnr.hasNextLine()){
					String temp = scnr.nextLine().trim();
					if (temp.contains("#")){

						if (temp.contains("Points")){
							currentType=1;
						}else if (temp.contains("Courses")){
							currentType=2;
						}else if (temp.contains("Student")){
							currentType=3;
						}else {
							currentType=0; //This should not happen!!!
							System.out.print("Invalid Input file content");
						}
					}
					else {
						if (currentType==1){
							DEFAULT_POINTS = Integer.parseInt(temp);
						}
						else if (currentType ==2){
							String tokens[] =temp.split(delim);
							Course newCourse = new Course(tokens[0],tokens[1],Integer.parseInt(tokens[2]));
							courseList.add(newCourse);

						}
						else if (currentType==3){
							newStudentName= temp;
							currentType=4;
							//Jump to ID processing for student in next line
						}

						else if (currentType==4){
							newStudentID = temp;
							currentType=5;
							//Jump to add classes in next line
							newStudent = new Student(newStudentName,newStudentID, DEFAULT_POINTS);
							studentList.add(newStudent);
						}
						else if (currentType==5){
							String tokens[] =temp.split(delim);
							int coins = Integer.parseInt(tokens[1]);
							Course courseToAdd = getCourseFromCourseList(tokens[0]);

							//check if class is valid:
							if (courseToAdd== null){
								System.out.print(tokens[0]+ " Doesn't exist");
								//System.exit(100);

							}
							//Class is found and is valid:
							else {
								if (newStudent.deductCoins(coins)) {
									courseToAdd.addStudent(newStudent, coins);
									newStudent.addToCart(courseToAdd);
								}
							}
//
						}
						else  {

							//This should not happen if input file is valid
							System.out.print("Input file has invalid format in this line:" + temp);
						}
					}
				}





			}
		catch(Exception e)
			{
			e.printStackTrace();
			System.out.println("File Parse Error");
			return false;
			}
		return true;
		}

	/**
	 * 
	 * @param fileName1
	 *            - output file with a line for each course
	 * @param fileName2
	 *            - output file with a line for each student
	 */
	public static void generateAndWriteResults(String fileName1, String fileName2)
		{
		try
			{
			// List Students enrolled in each course
			PrintWriter writer = new PrintWriter(new File(fileName1));
			for (Course c : courseList)
				{
				writer.println("-----" + c.getCourseCode() + " " + c.getCourseName() + "-----");

				// Core functionality
				c.processRegistrationList();

				// List students enrolled in each course
				int count = 1;
				for (Student s : c.getCourseRegister())
					{
					writer.println(count + ". " + s.getid() + "\t" + s.getName());
					s.enrollCourse(c);
					count++;
					}
				writer.println();
				}
			writer.close();

			// List courses each student gets
			writer = new PrintWriter(new File(fileName2));
			for (Student s : studentList)
				{
				writer.println("-----" + s.getid() + " " + s.getName() + "-----");
				int count = 1;
				for (Course c : s.getEnrolledCourses())
					{
					writer.println(count + ". " + c.getCourseCode() + "\t" + c.getCourseName());
					count++;
					}
				writer.println();
				}
			writer.close();
			}
		catch(FileNotFoundException e)
			{
			e.printStackTrace();
			}
		}

	/**
	 * Look up Course from classCode
	 * 
	 * @param courseCode
	 * @return Course object
	 */
	private static Course getCourseFromCourseList(String courseCode)
		{
		for (Course c : courseList)
			{
			if(c.getCourseCode().equals(courseCode))
				{
				return c;
				}
			}
		return null;
		}
	}