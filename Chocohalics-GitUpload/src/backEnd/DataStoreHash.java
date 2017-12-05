package backEnd;
import java.util.HashMap;
public abstract class DataStoreHash<T> {

	public abstract boolean add(int ID, Object obj);
		
	public abstract void remove(int ID);
	
	public abstract Object search(int ID);
	
	public abstract void writeToDisk();
}
