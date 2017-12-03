package backEnd;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

public class MemberHash {

	HashMap<Integer, Member> membersHash;
	
	public MemberHash(){
		super();
		membersHash = new HashMap<Integer, Member>() ;
	}
	
	/**
	 * 
	 * @param memberID: The numerical ID for a member. Typically 4-6 digits
	 * @param member
	 * @return if mem is not added, notifies the caller to delete duplicate. possible to duplicate members if accidentally given different id numbers.
	 */
 	public boolean addMem(int memberID, Member member ){
		if(membersHash.get(memberID) == null){
			membersHash.put(memberID, member);
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param memberID: The numerical ID for a member. Typically 4-6 digits.
	 */
	public void deleteMem(int memberID){
		membersHash.put(memberID, null);
	}
	
	
	/**
	 * 
	 * @param memberID: The numerical ID for a member. Typically 4-6 digits
	 * @return if member is found, calls and returns member class get status
	 */
	public String searchMem(int memberID){
		if(membersHash.get(memberID) == null){
			return "invalid";
		}
		else
		return (membersHash.get(memberID)).getStatus();
	}
	
	/**
	 * 
	 * @param memberID
	 * @param member
	 * @return
	 */
	public String editMem(int memberID, Member member){
		if(membersHash.get(memberID) == null){
			return "invalid";
		}
		else
			membersHash.put(memberID, member);
		return "edited";
	}

	
	/**
	 * Writes the content of the Member hash map to a file.
	 * FileName is typically of the form: "ChocAn Member List yyyy-mm-dd"
	 * 
	 */
	//@Override
	public void writeToDisk() {
		
		Date date = new Date(System.currentTimeMillis());
		System.out.println(date);
		
		try {
			FileWriter writer = new FileWriter("ChocAn Member List"+date.toString());
			ArrayList<String> memberNames = new ArrayList<String>();
			
			
			for(int i : membersHash.keySet()){
				memberNames.add(membersHash.get(i).getName());
			}
			
			memberNames.sort(new IDHolderComparator());
					
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
