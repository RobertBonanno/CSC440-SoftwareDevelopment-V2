package backEnd;
import java.util.*;
public class ServiceComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		Service s1 = (Service) o1;
		Service s2 = (Service) o2;
		
		if(s1.getServiceName() == s2.getServiceName())
			return 1;
		return 0;
	}

}
