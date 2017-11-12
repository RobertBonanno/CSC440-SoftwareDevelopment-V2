package backEnd;
import java.util.ArrayList;
public class ReceiptLog extends DataStore {

	ArrayList receiptList; 
	
	public void writeToDisk() {
		
		
	}
	
	/**
	 * Constructor for the ReceiptLog. 
	 * Calls the DataStore constructor
	 * then instantiates the recieptList ArrayList. 
	 */
	public ReceiptLog() {
		super();
		receiptList = new ArrayList(); 
		
	}
	
	/**
	 * Sorts the receipt List by the date of the service. 
	 */
	public void sort() {	
		
	}
	
	/**
	 * Clears the out-dated receipt log. 
	 * Every Friday, the log is written to disk and then cleared.
	 * All of the data from the previous week is deleted.  
	 */
	public void clear(){
		
	}
	
}