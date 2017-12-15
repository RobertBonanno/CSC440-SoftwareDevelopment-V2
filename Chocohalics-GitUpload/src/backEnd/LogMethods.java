package backEnd;

import java.util.ArrayList;
import java.util.Comparator;

public class LogMethods<T> {

	public boolean listed(T user, ArrayList<T> userLog, Comparator<T> comparator){
		int logSize = userLog.size();
		
		if(logSize != 0) {
			T temp;
			for(int i = 0; i < logSize; i++){
				temp = userLog.get(i);
				if (comparator.compare(temp, user)>0){
					return true;
				}
			}
		} 
		return false;
	}
}
