package backEnd;
import java.util.*;
public class IDHolderComparator implements Comparator<IDHolder>{

	@Override
	public int compare(IDHolder arg0, IDHolder arg1) {
		return arg0.getName().compareTo(arg1.getName());
	}
}
