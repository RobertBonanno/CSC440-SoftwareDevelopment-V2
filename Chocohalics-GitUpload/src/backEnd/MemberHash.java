package backEnd;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
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
	 */

	@Override
	public void add(String name, Address address){
		Integer id = generateID(); 
		
		Member newMember = new Member(name,address,id.intValue());
		newMember.setStatus(1);
		membersHash.put(id, newMember); 
	}
	
	private Integer generateID(){
		Integer id; 
		while(true){
			id =(int) (Math.random()*1000.0); 
			if(membersHash.containsKey(id)){
				continue; 
			}
			break; 
		}
		return id;
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
		(membersHash.get(memberID)).setStatus(3); //1 = VALID 2 = SUSPENDED 3 = INVALID; limits typing errors in maintenance
	}

	/**
	 * 
	 * @param memberID: The numerical ID for a member. Typically 4-6 digits
	 * @return if member is found, calls and returns member class get status
	 */
	@Override
	public Object search(int memberID){
		if(membersHash.containsKey(memberID))
			return membersHash.get(memberID);
		else
			return null;
	}
	
	public String validate(int memberID){
		if(search(memberID).equals(null))
			return "INVALID";
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
		if(membersHash.containsKey(memberID)){
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
		Time time = new Time(System.currentTimeMillis());
		System.out.println(date);
		
		try {
			FileWriter writer = new FileWriter("ChocAn Member List "+date.toString()+" "+time.getHours()+"-"+time.getMinutes()+".txt");
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
	
	//Temporary method for testing
	public void add(Member mem) {
		membersHash.put(mem.getID(), mem);
		
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