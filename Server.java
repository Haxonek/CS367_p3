///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            p3
// Files:            Database.java, Document.java, EmptyStackException.java, 
//					 Listnode.java, Operation.java, QueueADT.java, 
//					 Server.java, SimpleQueue.java, SimpleStack.java
//					 StackADT.java, User.java, WAL.java
// Semester:         CS367 Fall 2015
//
// Author:           Thomas Hansen
// Email:            thansen8@wisc.edu
// CS Login:         thansen
// Lecturer's Name:  Jim Skrentny
// Lab Section:      2
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     N/A
// Email:            N/A
// CS Login:         N/A
// Lecturer's Name:  N/A
// Lab Section:      N/A
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   must fully acknowledge and credit those sources of help.
//                   Instructors and TAs do not have to be credited here,
//                   but tutors, roommates, relatives, strangers, etc do.
//
// Persons:          none
//
// Online sources:   none
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;
import java.io.File;
import java.io.PrintWriter;

/**
 * This is where we keep the main method and where the files are written in 
 * and out, as well as the Doc's are pared/made
 * 
 * @author Thomas Hansen
 *
 */
public class Server {
	// holds a reference to all of the documents within this object
	private Database docData;
	// Stores all the operations that are yet to be run
	private SimpleQueue<Operation> operations = new SimpleQueue<Operation>();
	// Points to each of the files used
	private File inputFile, outputFile;

	/**
	 * sets up the private data types
	 * 
	 * @param inputFileName The name of the file with the data
	 * @param outputFileName The name of the file where everything will
	 * be written
	 */
	public Server(String inputFileName, String outputFileName) {
		// initialize queue, created class variables (File)
		docData = new Database();
		inputFile = new File(inputFileName);
		outputFile = new File(outputFileName);
	}

	/**
	 * runs the initialize and process methods
	 */
	public void run(){
		initialize();
		process();
	}

	/**
	 * Reads the input file
	 */
	public void initialize() {
		int numDocs;
		Scanner in;
		try {
			in = new Scanner(inputFile);
		} catch (Exception e) {
			throw new RuntimeException("File Not Found");
		}
		String docLine;
		
		numDocs = in.nextInt();
		
		// Adds all the documents
		for (int i = 0; i < numDocs; ++i) {
			docLine = in.nextLine();
			// checks against the \n after reading only the int
			if (docLine.trim().equals("")) {
				docLine = in.nextLine();
			}
			docData.addDocument(makeDoc(docLine));
		}
		// Adds all the operations
		while (in.hasNextLine()) {
			docLine = in.nextLine();
			operations.enqueue(makeOp(docLine));
		}
		
		in.close();
	}

	/**
	 * writes to the output file and prints to the command line
	 */
	public void process() {	
		try {
			PrintWriter out = new PrintWriter(outputFile);
	
			while (!operations.isEmpty()) {
				// holds temporary data for each loop
				String output = new String();
				
				Operation cur = operations.dequeue();
				System.out.println("----------Update Database----------");
				out.write("----------Update Database----------\n");
				System.out.println(cur.toString() + "\n");
				out.write(cur.toString() + "\n\n");
				output = docData.update(cur) + "\n";
				System.out.print(output);
				out.write(output);
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			// Although due to the nature of how the file is set up
			// This shouldn't be possible
			System.out.println("File Could not be found");
		}
	}

	/**
	 * Runs the main server class and reads in files from the command line
	 * 
	 * @param args the file(2) names
	 */
	public static void main(String[] args){
		if(args.length != 2){
			System.out.println("Usage: java Server [input.txt] [output.txt]");
			System.exit(0);
		}
		Server server = new Server(args[0], args[1]);
		
		server.run();
	}
	
	/**
	 * This method is used to format the doc string
	 * 
	 * @param line
	 * @return
	 */
	private Document makeDoc(String line) {
		String docName;
		int rowNum;
		int colNum;
		List<User> users = new LinkedList<User>();
		
		Scanner parseLine = new Scanner(line);
		parseLine.useDelimiter(",");
		docName = parseLine.next();
		rowNum = parseLine.nextInt();
		colNum = parseLine.nextInt();
		
		while (parseLine.hasNext()) {
			users.add(new User(parseLine.next()));
		}
		parseLine.close();
		return new Document(docName, rowNum, colNum, users);
	}
	
	/**
	 * creates the Operation object from the given data
	 * 
	 * @param line the line of text with the data
	 * @return the data converted to the object
	 */
	private Operation makeOp(String line) {
		// data points that will be sent to Operation 
		String docName, userId;
		String op;
		int rowIndex, colIndex, constant;
		long timestamp;

		// reads in the initial static data that's the same amung all correctly
		// formated inputs
		Scanner parseLine = new Scanner(line);
		parseLine.useDelimiter(",");
		timestamp = parseLine.nextLong();
		userId = parseLine.next();
		docName = parseLine.next();
		op = parseLine.next();

		// switches between each of the enums
		switch(op) {
		case "set":
			rowIndex = parseLine.nextInt();
			colIndex = parseLine.nextInt();
			constant = parseLine.nextInt();
			parseLine.close();
			return new Operation(docName, userId, Operation.OP.SET, rowIndex,
					colIndex, constant, timestamp);
		case "clear":
			rowIndex = parseLine.nextInt();
			colIndex = parseLine.nextInt();
			parseLine.close();
			return new Operation(docName, userId, Operation.OP.CLEAR, rowIndex,
					colIndex, timestamp);
		case "add":
			rowIndex = parseLine.nextInt();
			colIndex = parseLine.nextInt();
			constant = parseLine.nextInt();
			parseLine.close();
			return new Operation(docName, userId, Operation.OP.ADD, rowIndex,
					colIndex, constant, timestamp);
		case "sub":
			parseLine.close();
			return new Operation(docName, userId, Operation.OP.SUB, timestamp);
		case "mul":
			parseLine.close();
			return new Operation(docName, userId, Operation.OP.MUL, timestamp);
		case "div":
			parseLine.close();
			return new Operation(docName, userId, Operation.OP.DIV, timestamp);
		case "undo":
			parseLine.close();
			return new Operation(docName, userId, Operation.OP.UNDO,timestamp);
		case "redo":
			parseLine.close();
			return new Operation(docName, userId, Operation.OP.REDO,timestamp);
		default:
			parseLine.close();
			throw new IllegalArgumentException();
//			return null;	
		}
	}
}