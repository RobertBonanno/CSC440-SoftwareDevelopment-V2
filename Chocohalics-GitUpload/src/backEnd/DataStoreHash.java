package backEnd;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public abstract class DataStoreHash<T> {

	//
		
	public abstract void remove(int ID);
	
	public abstract T search(int ID);
	
	public abstract String getDataHashType();
	
	public abstract void writeToXML();
	
	protected Node getElementValue(Document doc, Element element, String name, String value){
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}
	
	public abstract void readFromXML(String FileName);

	
	
}
