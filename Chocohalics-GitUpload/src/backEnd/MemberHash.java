package backEnd;
import java.util.HashMap;
public class MemberHash {

	HashMap membersHash = new HashMap();
	
	/**
	 * 
	 * @param memberID
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
	
	public String addMemR(int memberID, Member member ){
		if(membersHash.get(memberID) == null){
			membersHash.put(memberID, member);
			return "added member";
		}
		return "member already exists";
	}
	
	
	public void deleteMem(int memberID){
		membersHash.put(memberID, null);
	}
	
	public String removeMemR(int memberID){
			membersHash.put(memberID, null);
		return "member deleted";
	}
	/**
	 * 
	 * @param memberID
	 * @return if member is found, calls and returns member class get status
	 */
	public String searchMem(int memberID){
		if(membersHash.get(memberID) == null){
			return "invalid";
		}
		else
		return ((Member)membersHash.get(memberID)).getStatus();
	}
	
	public String editMem(int memberID, Member member){
		if(membersHash.get(memberID) == null){
			return "invalid";
		}
		else
			membersHash.put(memberID, member);
		return "edited";
	}
	////////////////////////////////////////////////////////////

	@Override
	public void writeToDisk() {
		// TODO Auto-generated method stub
		
	}
	///////////////////////////////////////////////////////////
}
