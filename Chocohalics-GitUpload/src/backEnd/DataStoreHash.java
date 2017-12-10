package backEnd;
import java.util.HashMap;
public abstract class DataStoreHash<T> {

	public abstract void add(String name, Address address);
		
	public abstract void remove(int ID);
	
	public abstract Object search(int ID);
	
	public abstract void writeToDisk();
}
