///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Server.java
// File:             User.java
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
 * This is where we keep all the WALs for each doc and the data for each user
 * 
 * @author Thomas Hansen
 *
 */
public class User {
	private String userID;
	private SimpleStack<WAL> redo = new SimpleStack<WAL>();
	private SimpleStack<WAL> undo = new SimpleStack<WAL>();

	/**
	 * Creates a User. Throws IllegalArgumentException is userId is invalid.
	 * 
	 * @param userId the specific adress for the user
	 * @throws IllegalArgumentException
	 */
	public User(String userId) {
		if (userId == null || userId.equals("")) {
			throw new IllegalArgumentException();
		}
		this.userID = userId;
	}

	/**
	 * Returns the top WAL for undo. If there is no such WAL, returns null.
	 * 
	 * @return the top WAL
	 */
	public WAL popWALForUndo() {
		if (!undo.isEmpty()) {
			return undo.pop();
		} else {
			throw new EmptyStackException();
		}
	}

	/**
	 * Returns the top WAL for redo. If there is no such WAL, returns null.
	 * 
	 * @return the top WAL
	 */
	public WAL popWALForRedo() {
		if (!redo.isEmpty()) {
			return redo.pop();
		} else {
			return null;
		}
	}

	/**
	 * Pushes the WAL into undo stack. Throws IllegalArgumentException 
	 * if WAL is null.
	 * 
	 * @param trans the last WAL
	 */
	public void pushWALForUndo(WAL trans) {
		undo.push(trans);
	}

	/**
	 * Pushes the WAL into redo stack. Throws IllegalArgumentException 
	 * if WAL is null.
	 * 
	 * @param trans the last WAL
	 */
	public void pushWALForRedo(WAL trans) {
		redo.push(trans);
	}

	/**
	 * Clear all redo WALs.
	 * 
	 */
	public void clearAllRedoWAL() {
		redo.clear();
	}

	/**
	 * Clear all undo WALs.
	 * 
	 */
	public void clearAllUndoWAL() {
		undo.clear();
	}

	/**
	 * Returns the user id
	 * 
	 * @return the specific user ID for the user
	 */
	public String getUserId() {
		return userID;
	}
}