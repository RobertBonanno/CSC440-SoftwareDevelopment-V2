package backEnd;

import java.util.ArrayList;

public abstract class DataStore<T> {

	ArrayList<T> summaryList;
	
	public DataStore(){
		summaryList = new ArrayList<T>();
	}
	
	
	/**
	 * Searches for 
	 * @param item
	 * @return
	 */
	public abstract void search(String identifier);
	
	public abstract void sort();
	
	/**
	 * Attempts to add the parameter item to summaryList
	 * @param item is added to summaryList without duplication
	 */
	public abstract void add(int identifier); 
	
	/**
	 * Removes the passed in item from the summary list. If the item is not in the list then nothing happens.
	 * @param item should be the same type as the dataStore class type.
	 * @return Returns the removed element from thesummaryList
	 */
	public abstract T remove(String identifier);
	
	/**
	 * Writes the contents of summaryList to a text file
	 */
	public abstract void writeToDisk();
	
	/**
	 * Returns all of the items in the summaryList
	 * 
	 * @return A string with each item separated by a new line
	 */
	public abstract String toString();
	
	
}