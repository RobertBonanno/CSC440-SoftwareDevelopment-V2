package backEnd;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map.Entry;

public class MemberReceiptComparator implements Comparator<Entry<Integer, ArrayList<Receipt>>> {

	public int compare(Entry<Integer, ArrayList<Receipt>> o1, Entry<Integer, ArrayList<Receipt>> o2) {
		return o1.getValue().get(0).compareTo(o2.getValue().get(0));
	}
	
}
