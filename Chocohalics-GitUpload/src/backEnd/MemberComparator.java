package backEnd;
import java.util.*;
public class MemberComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		Member m1 = (Member) o1;
		Member m2 = (Member) o2;
		
		if(m1.getName() == m2.getName())
			return 1;
		return 0;
	}
}
