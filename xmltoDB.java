package jdbcfinal;
import java.io.File;
import java.sql.*; 
import java.util.regex.Pattern;
import java.util.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

public class xmltoDB 
{ 
	static private String getAttrValue(Node node,String attrName) {
	    if ( ! node.hasAttributes() ) return "";
	    NamedNodeMap nmap = node.getAttributes();
	    if ( nmap == null ) return "";
	    Node n = nmap.getNamedItem(attrName);
	    if ( n == null ) return "";
	    return n.getNodeValue();
	}

	static private String getTextContent(Node parentNode,String childName) {
	    NodeList nlist = parentNode.getChildNodes();
	    for (int i = 0 ; i < nlist.getLength() ; i++) {
	    Node n = nlist.item(i);
	    String name = n.getNodeName();
	    if ( name != null && name.equals(childName) )
	        return n.getTextContent();
	    }
	    return "";
	}
	public static void main(String args[]) 
	{ 
		try
		{ 
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			Scanner sc=new Scanner(System.in);
			// Establishing Connection 
			Connection con = DriverManager.getConnection( 
			"jdbc:oracle:thin:@localhost:1521/XE", "system", "9848"); 
			if (con != null)			 
				System.out.println("Connected");			 
			else			
				System.out.println("Not Connected"); 
			
			
			File file = new File("src/books.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlDoc = builder.parse(file);
			
			
			
			NodeList booksLength = xmlDoc.getElementsByTagName("book");
			
			
			//this gives the number of records in the xml file
			System.out.println(booksLength.getLength());

			
			
			XPath xpath = XPathFactory.newInstance().newXPath();
			Object res = xpath.evaluate("/catalog/book",
			                 xmlDoc,
			                 XPathConstants.NODESET);
			
			
PreparedStatement stmt = con
    .prepareStatement("insert into books values(?,?,?,?,?,?)");



			for (int i = 0 ; i < booksLength.getLength() ; i++) {
			    Node node = booksLength.item(i);
			    if(getAttrValue(node, "id")!=null) {
			    List<String> columns = Arrays
			    			.asList(getAttrValue(node, "id"),
			    	        getTextContent(node, "author"),
			    	        getTextContent(node, "title"),
			    	        getTextContent(node, "genre"),
			    	        getTextContent(node, "price"),
			    	        getTextContent(node, "description"));
			    for (int n = 0 ; n < columns.size() ; n++) {
			    	//System.out.println(columns.get(n));
			    stmt.setString(n+1, columns.get(n));
			    }
			   stmt.execute();
			    }
			}
			
		} 
		catch(Exception e) 
		{ 
			System.out.println(e); 
		} 
	} 
} 
