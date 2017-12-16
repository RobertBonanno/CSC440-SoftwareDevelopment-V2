package backEnd;
import java.util.*;
public class ServiceComparator implements Comparator<Service>{

	@Override
	public int compare(Service s1, Service s2) {
		if(s1.getName() == s2.getName()){
			System.out.println(s1.getName() +", "+ s2.getName());
			System.out.println(s1.getName() == s2.getName());
			return 1;
		}
			
		return 0;
	}

}
