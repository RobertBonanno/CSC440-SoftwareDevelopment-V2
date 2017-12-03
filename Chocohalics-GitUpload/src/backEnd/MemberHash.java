package backEnd;
import java.util.HashMap;

public class MemberHash extends DataStoreHash {

	HashMap membersHash = new HashMap();
	
	/**
	 * 
	 * @param memberID
	 * @param member
	 * @return if mem is not added, notifies the caller to delete duplicate. possible to duplicate members if accidentally given different id numbers.
	 */
	@Override
	public boolean add(int memberID, Object member ){
	
		if(membersHash.get(memberID) == null){
			membersHash.put(memberID, member);
			return true;
		}
		return false;
	}
	@Override
	public void remove(int memberID){
		membersHash.put(memberID, null);
	}

	/**
	 * 
	 * @param memberID
	 * @return if member is found, calls and returns member class get status
	 */
	@Override
	public Object search(int memberID){
		if(membersHash.get(memberID) == null){
			return "invalid";
		}
		else
		return membersHash.get(memberID);
		//return ((Member)membersHash.get(memberID)).getStatus();
	}
	
	public String editMem(int memberID, Member member){
		if(membersHash.get(memberID) == null){
			return "invalid";
		}
		else
			membersHash.put(memberID, member);
		return "edited";
	}

	@Override
	public void writeToDisk() {
		
		
	}









	/**
	 *  this method only serves for the test case, sould be replaced with a read from disc for security reasons
	 * @param memberID
	 * @param member
	 */
	public void put(int memberID, Object member ){
		membersHash.put(memberID, member);
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