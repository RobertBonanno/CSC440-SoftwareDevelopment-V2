package backEnd;

import java.util.ArrayList;

public class MemberLog extends DataStore{

	ArrayList membersLog = new ArrayList();
	IDHolderComparator useMe2 = new IDHolderComparator();
	LogMethods<Member> util = new LogMethods(); 
	
	public void addMember(Member Member){
		if(util.listed(Member, membersLog, useMe2)){}
		else {
			membersLog.add(Member);
		}
	}
//////////////////////////////////////
	@Override
	public void sort() {
		// TODO Auto-generated method stub
		
	}
	
	public Member search(int uid) throws IndexOutOfBoundsException{
		//remove this code from testing
		Address temp = new Address("123 flossmore", "Delightberg", "Heaven", 77777);
		Member trial = new Member("Valid", 12345, "Paul", temp);
		
		
		if(trial.getIDNum()!=uid)
			throw new IndexOutOfBoundsException();
		
		return trial;
		
		
	}

	@Override
	public void writeToDisk() {
		// TODO Auto-generated method stub
		
	}
//////////////////////////////////////
	
	
	
	
	/*
	private boolean listed(member Member){
		boolean inList = false;
		int x = membersLog.size();
		
		if(x == 0) return inList;
		
		
		member temp = new member();
		
		for(int i = 0; i < x; i++){
			temp = (member) membersLog.get(i);
			if (useMe2.compare(temp, Member)>0){
				inList = true;
				break;
			}
		}
		
		return inList;
	}*/
}
