///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Server.java
// File:             Document.java
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

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * This is the Document class; this is where we keep all data for each Doc
 * 
 * @author Thomas Hansen
 *
 */
public class Document {
	// Doc name
	private String docName;
	// The actual spread sheet holding the values
	private int[][] sheet;
	// The list of all the users
	private List<User> userList;
	
	public Document(String docName, int rowSize, int colSize, List<User>
	userList) {
		this.docName = docName;
		this.sheet = new int[rowSize][colSize];
		this.userList = userList;
	}

	/**
	 * returns all of the user ID's in a List<String>
	 * 
	 * @return a List of all User objects for this Doc
	 */
	public List<String> getAllUserIds() {
		List<String> userIDs = new LinkedList<String>();
		Iterator<User> itr = userList.iterator();
		while (itr.hasNext()) {
			userIDs.add(itr.next().getUserId());
		}
		return userIDs;
	}
	
	/**
	 * updates the document to the current operation submitted
	 * 
	 * @param operation the operation that will be used to update the Doc
	 */
	public void update(Operation operation) {
		// sets up the initial variables
		int row = operation.getRowIndex();
		int col = operation.getColIndex();
		int constant = operation.getConstant();
		// holds current values
		User curUser = getUserByUserId(operation.getUserId());
		// Stores the current WAL being used
		WAL curWAL;
		
		// switch block gets which OP is being used and performs the 
		// coresponding method
		switch(operation.getOp().toString()) {
		case "SET": 
			curWAL = new WAL(row, col, sheet[row][col]);
			curUser.pushWALForUndo(curWAL);
			curUser.clearAllRedoWAL();
			sheet[row][col] = constant;
			break;
		case "CLEAR": 
			curWAL = new WAL(row, col, sheet[row][col]);
			curUser.pushWALForUndo(curWAL);
			curUser.clearAllRedoWAL();
			sheet[row][col] = 0;
			break;
		case "ADD": 
			curWAL = new WAL(row, col, sheet[row][col]);
			curUser.pushWALForUndo(curWAL);
			curUser.clearAllRedoWAL();
			sheet[row][col] += constant;
			break;
		case "SUB": 
			curWAL = new WAL(row, col, sheet[row][col]);
			curUser.pushWALForUndo(curWAL);
			curUser.clearAllRedoWAL();
			sheet[row][col] -= constant;
			break;
		case "MUL": 
			curWAL = new WAL(row, col, sheet[row][col]);
			curUser.pushWALForUndo(curWAL);
			curUser.clearAllRedoWAL();
			sheet[row][col] *= constant;
			break;
		case "DIV": 
			curWAL = new WAL(row, col, sheet[row][col]);
			curUser.pushWALForUndo(curWAL);
			curUser.clearAllRedoWAL();
			sheet[row][col] /= constant;
			break;
		case "UNDO":
			curWAL = curUser.popWALForUndo();
			// sets up row/col to be used in new WAL
			row = curWAL.getRowIndex();
			col = curWAL.getColIndex();
			constant = curWAL.getOldValue();
			// adds to redo
			curUser.pushWALForRedo(new WAL(row, col, sheet[row][col]));
			// changes value officially
			sheet[row][col] = constant;
			break;
		case "REDO": 
			curWAL = curUser.popWALForRedo();
			// sets up row/col to be used in new WAL
			row = curWAL.getRowIndex();
			col = curWAL.getColIndex();
			constant = curWAL.getOldValue();
			// adds to redo
			curUser.pushWALForUndo(new WAL(row, col, sheet[row][col]));
			// changes value officially
			sheet[row][col] = constant;
			break;
		default:
			throw new IllegalArgumentException();
		}
	}

	/**
	 * returns the name of the document
	 * 
	 * @return the document name
	 */
	public String getDocName() {
		return docName;
	}

	/**
	 * finds the user based on the ID
	 * 
	 * @param userId the userID
	 * @return the User object
	 */
	public User getUserByUserId(String userId) {
		Iterator<User> itr = userList.iterator();
		User cur;
		// iterates through each user looking for the correct ID
		while (itr.hasNext()) {
			cur = itr.next();
			if (cur.getUserId().equals(userId)) {
				// leaves the method with correct User
				return cur;
			}
		}
		// no User in this document
		return null;
	}

	/**
	 * returns the value in the given cell
	 * 
	 * @param rowIndex position of the row, i.e. [r][c]
	 * @param colIndex position of the col, i.e. [r][c]
	 * @return the value (constant) in the cell
	 */
	public int getCellValue(int rowIndex, int colIndex){
		if (rowIndex >= 0 && rowIndex < sheet.length && colIndex >= 0 && 
				colIndex < sheet[rowIndex].length) {
			return sheet[rowIndex][colIndex];
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Returns a string as the document in it's printed format as well
	 * as a table containing all of the required values separated by tabs
	 * 
	 * @return document in proper format
	 */
	public String toString() {
		// where the proper format will be written to
		String docString = new String();
		
		docString = "Document Name: " + docName + "\t" + 
		"Size: ["+sheet.length + "," + sheet[0].length + "]\n";
		docString += "Table: \n";
		// Generates the table (i.e. sheets array)
		for (int r = 0; r < sheet.length; ++r) {
			for (int c = 0; c < sheet[0].length; ++c) {
				docString += sheet[r][c] + "\t";
			}
			docString += "\n";
		}
		
		return docString;
	}
}
