package backEnd;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

public class MemberHash extends DataStoreHash {

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

	@Override
	public boolean add(int memberID, Object member ){

		if(membersHash.get(memberID) == null){
			membersHash.put(memberID, (Member) member);
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
	
	

	@Override
	/*public void remove(int memberID){
		membersHash.put(memberID, null);
	}*/
	public void remove(int memberID){
		((Member)membersHash.get(memberID)).setStatus(3); //1 = VALID 2 = SUSPENDED 3 = INVALID; limits typing errors in maintenance
	}

	/**
	 * 
	 * @param memberID: The numerical ID for a member. Typically 4-6 digits
	 * @return if member is found, calls and returns member class get status
	 */
	@Override
	public Object search(int memberID){
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
			FileWriter writer = new FileWriter("ChocAn Member List "+date.toString()+".txt");
			ArrayList<Member> members = new ArrayList<Member>();
			
			
			for(int i : membersHash.keySet()){
				members.add(membersHash.get(i));
			}
			
			Collections.sort(members, new IDHolderComparator());
			
			for(Member m : members){
				writer.write(m.toString()+System.getProperty("line.separator"));
			}
			
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}









	/**
	 *  this method only serves for the test case, sould be replaced with a read from disc for security reasons
	 * @param memberID
	 * @param member
	 */
	public void put(int memberID, Object member ){
		membersHash.put(memberID, (Member) member);
	}
}


/*
 * 
 * 	public boolean add(int memberID, Object member ){
		if(membersHash.get(memberID) == null){
			membersHash.put(memberID, member);
			return true;
		}
		return false;
	}
 * 
 * 	public String addMemR(int memberID, Member member ){
 
if(membersHash.get(memberID) == null){
	membersHash.put(memberID, member);
	return "added member";
}
return "member already exists";
}
	public String removeMemR(int memberID){
			membersHash.put(memberID, null);
		return "member deleted";
	}
*
*/