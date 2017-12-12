package backEnd;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class MemberHash extends DataStoreHash {

	HashMap<Integer, Member> membersHash;
	
	public MemberHash(){
		super();
		membersHash = new HashMap<Integer, Member>() ;
		map = new HashMap<Integer, Member>();
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
		membersHash.put(id, newMember); 
		map.put(id, newMember);
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

	@Override
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
	
	

	@Override
	public String getDataHashType() {
		return "Member";
	}

	@Override
	protected Node getXMLElement(Document doc, Integer i) {
		Element memberElement = doc.createElement("Member");
		Member member = membersHash.get(i.intValue());
		//set id attribute
		memberElement.setAttribute("id", member.getID()+"");
		
		//create name attribute
		memberElement.appendChild(super.getElementValue(doc, memberElement, "Name", member.getName()));
		
		//create status attribute
		memberElement.appendChild(super.getElementValue(doc, memberElement, "Status", member.getStatus()));
		
		//create address street name attribute	
		memberElement.appendChild(super.getElementValue(doc, memberElement, "Street", member.getAddress().getStreet() ));
		
		//create address city attribute
		memberElement.appendChild(super.getElementValue(doc, memberElement, "City" , member.getAddress().getCity()));
		
		//create address state attribute
		memberElement.appendChild(super.getElementValue(doc, memberElement, "State", member.getAddress().getState()));
		
		//create address ZIP attribute
		memberElement.appendChild(super.getElementValue(doc, memberElement, "ZIP", ""+member.getAddress().getZipCode()));
		
		return memberElement;
	}
	









	
}
