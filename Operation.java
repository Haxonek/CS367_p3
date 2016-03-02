///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Server.java
// File:             Operation.java
// Semester:         CS367 Fall 2015
//
// Author:           Thomas Hansen
// CS Login:         thansen
// Lecturer's Name:  Jim Skrentny
// Lab Section:      2
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     N/A
// Email:            N/A
// CS Login:         N/A
// Lecturer's Name:  N/A
// Lab Section:      N/A
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          none
//
// Online sources:   none
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This is where the data for Operation is kept and the enum OP is
 * 
 * @author Thomas Hansen
 *
 */
public class Operation {
	// Enumeration of operator type.
	public enum OP {
		SET, //set,row,col,const -> set [row,col] to const
		CLEAR, //clear,row,col -> set [row,col] to 0
		ADD, //add,row,col,const -> add [row,col] by const
		SUB, //sub,row,col,const -> sub [row,col] by const
		MUL, //mul,row,col,const -> mul [row,col] by const
		DIV, //div,row,col,const -> div [row,col] by const
		UNDO, //undo the last operation
		REDO //redo the last undo
	}

	// Each data point below relates directly to specifying the correct 
	// operation and where to apply it
	private String docName, userId;
	private OP op;
	private int rowIndex, colIndex, constant;
	private long timestamp;

	/**
	 * initializes the Operation object
	 * 
	 * @param docName the name of the document
	 * @param userId the users id
	 * @param op the choice of enumeration
	 * @param rowIndex the row at which the operation is occuring
	 * @param colIndex the column at which the operation is occuring
	 * @param constant the value modifying the column
	 * @param timestamp when the change occured
	 */
	public Operation(String docName, String userId, OP op, int rowIndex, int
			colIndex, int constant, long timestamp) {
		// we Check for null pointers
		if (docName == null || userId == null || op == null) {
			throw new IllegalArgumentException();
		}
		
		try { // check for null and such
			this.docName = docName;
			this.userId = userId;
			// try/catch mainly blocks against op not being in the enum
			this.op = op;
			this.rowIndex = rowIndex;
			this.colIndex = colIndex;
			this.constant = constant;
			this.timestamp = timestamp;
		} catch (Exception e) {
			// if anything goes wrong while assigning variables it's
			// caught here
			throw new IllegalArgumentException();
		}
	}

	/**
	 * initializes the Operation object
	 * 
	 * @param docName the name of the document
	 * @param userId the users id
	 * @param op the choice of enumeration
	 * @param rowIndex the row at which the operation is occuring
	 * @param colIndex the column at which the operation is occuring
	 * @param timestamp when the change occured
	 */
	public Operation(String docName, String userId, OP op, int rowIndex, int
			colIndex, long timestamp) {
		this(docName, userId, op, rowIndex, colIndex, -1, timestamp);
	}

	/**
	 * initializes the Operation object
	 * 
	 * @param docName the name of the document
	 * @param userId the users id
	 * @param op the choice of enumeration
	 * @param timestamp when the change occured
	 */
	public Operation(String docName, String userId, OP op, long timestamp) {
		this(docName, userId, op, -1, -1, -1, timestamp);
	}

	/**
	 * returns the document name
	 * 
	 * @return the document name
	 */
	public String getDocName() {
		return docName;
	}

	/**
	 * returns the user ID
	 * 
	 * @return user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * returns the enum OP chosen for this operation
	 * 
	 * @return the OP enum
	 */
	public OP getOp() {
		return op;
	}

	/**
	 * returns the row index
	 * 
	 * @return row index
	 */
	public int getRowIndex() {
		return rowIndex;
	}
	/**
	 * returns the col index
	 * 
	 * @return column index
	 */
	public int getColIndex() {
		return colIndex;
	}

	/**
	 * returns the timestamp (long)
	 * 
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * returns the constant that was used to change the data point
	 * 
	 * @return constant
	 */
	public int getConstant() {
		return constant;
	}

	/**
	 * returns a sting of the operation description
	 * 
	 * @return Operation description
	 */
	public String toString() {
		// returns the operation object as the correcly formated string
		switch (op.toString()) {
		case "ADD": case "SUB": case "MUL": case "DIV": case "SET":
			return timestamp + "\t" + docName + "\t" + userId + "\t" + 
				op.toString().toLowerCase() + "\t" + "[" + rowIndex + "," + 
				colIndex + "]" + "\t" + 
					constant;
		case "CLEAR":
			return timestamp + "\t" + docName + "\t" + userId + "\t" +
			op.toString().toLowerCase() + "\t" + "[" + rowIndex + "," + 
			colIndex + "]";
		case "UNDO": case "REDO":
			return timestamp + "\t" + docName + "\t" + userId + "\t" +
		op.toString().toLowerCase();
		default: 
			System.out.println("IllegealArgumentException should have "+
					"been called earlier while creating object");
			return null;
		}
	}
}