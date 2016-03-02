///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Server.java
// File:             Database.java
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
 * Holds all the documents and allows for an easy search for them/access to
 * them
 * 
 * @author Thomas Hansen
 *
 */
public class Database {
	private List<Document> documents;

	/**
	 * Creates a new Database object
	 * 
	 */
	public Database() {
		documents = new LinkedList<Document>();
	}

	/**
	 * adds a Document object to the List of Objects
	 * 
	 * @param doc
	 */
	public void addDocument(Document doc) {
		documents.add(doc);
	}

	/**
	 * Returns the List ADT of all the documents
	 * 
	 * @return all of the Document Objects
	 */
	public List<Document> getDocumentList() {
		return documents;
	}

	/**
	 * Updates a document given the operation passed through
	 * 
	 * @return A string of the new updated document
	 */
	public String update(Operation operation) {
		// so find the Document, then pass through to it's own update method
		
		String docName = operation.getDocName();
		Iterator<Document> itr = documents.iterator();
		while (itr.hasNext()) {
			Document cur = itr.next();
			if (cur.getDocName().equals(docName)) {
				cur.update(operation);
				// TODO I assume this is what we return....
				return cur.toString();
			}
		}
		// if Doc is not in the database
		throw new IllegalArgumentException();
	}

	/**
	 * Grabs the doc with the given name
	 * 
	 * @param docName Name of the Document wanted
	 * @return the Document object
	 */
	public Document getDocumentByDocumentName(String docName) {
		Iterator<Document> itr = documents.iterator();
		Document cur;
		while (itr.hasNext()) {
			cur = itr.next();
			if (cur.getDocName().equals(docName)) {
				return cur;
			}
		}
		return null;
	}

}