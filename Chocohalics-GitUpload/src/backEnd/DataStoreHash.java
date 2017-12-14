package backEnd;
import java.util.HashMap;

import java.io.File;
import java.sql.Date;
import java.sql.Time;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

public abstract class DataStoreHash<T> {

	public abstract void add(String name, Address address);
		
	public abstract void remove(int ID);
	
	public abstract Object search(int ID);
	
	public abstract String getDataHashType();
	
	public abstract void writeToXML();
	
	protected abstract Node getXMLElement(Document doc, Integer i);
	
	protected Node getElementValue(Document doc, Element element, String name, String value){
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}
	
	public abstract void readFromXML(String FileName);

	
	
}
