///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Server.java
// File:             WAL.java
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

public class WAL {
	int rowIndex, colIndex, oldValue;

    public WAL(int rowIndex, int colIndex, int oldValue) {
       this.rowIndex = rowIndex;
       this.colIndex = colIndex;
       this.oldValue = oldValue;
    }

    public int getOldValue() {
        return oldValue;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

}