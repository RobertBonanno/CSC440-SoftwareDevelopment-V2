package backEnd;

import java.util.ArrayList;
import java.util.Comparator;

public class LogMethods<T> {

	public boolean listed(T user, ArrayList userLog, Comparator compareBase){
		boolean inList = false;
		int x = userLog.size();
		
		if(x == 0) return inList;
		
		
		T temp = (T)new Object();
		
		for(int i = 0; i < x; i++){
			temp = (T) userLog.get(i);
			if (compareBase.compare(temp, user)>0){
				inList = true;
				break;
			}
		}
		
		return inList;
	}
	
	
	public int locate(T user, ArrayList userLog, Comparator compareBase){
		boolean trigger = false;
		int location = 0;
		int x = userLog.size();
		
		if(x == 0) return -1;
		
		
		T temp = (T)new Object();
		
		while(location < x ){
			
			temp = (T) userLog.get(location);
			if (compareBase.compare(temp, user)>0){
				trigger = true;
				break;
			}
			location++;
		}
		if(!trigger) return -1;
		
		return location;
	}
}
