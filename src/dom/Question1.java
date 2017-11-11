package dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Question1 {

	
	public static void main(String[] args) throws Exception{
		
		
		Element rac, e1, e2, e3, e4, p1, p2, p3, p4;
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document d = db.newDocument();
		
		rac = d.createElement("catalogue");
		
		(e1 = d.createElement("article")).setAttribute("nom","peigne");
		(e2 = ((Element) e1.cloneNode(true))).setAttribute("nom","brosse");
		(e3 = ((Element) e2.cloneNode(true))).setAttribute("nom","shampoing");
		(e4 = ((Element) e3.cloneNode(true))).setAttribute("nom","laque");
		
		e1.appendChild(p1 = d.createElement("prix"));
		p1.setAttribute("unite", "euro");
		p1.appendChild(d.createTextNode("10"));
		e2.appendChild(p2 = d.createElement("prix"));
		p2.setAttribute("unite", "dollar");
		p2.appendChild(d.createTextNode("5"));
		e3.appendChild(p3 = d.createElement("prix"));
		p3.appendChild(d.createTextNode("4"));
		e4.appendChild(p4 = d.createElement("prix"));
		p4.setAttribute("unite", "yen");
		p4.appendChild(d.createTextNode("9"));
		
		
		d.appendChild(rac).appendChild(e1); rac.appendChild(e2); rac.appendChild(e3); rac.appendChild(e4);
		XMLTools.ecrireXML(d, "/home/tarik/Bureau/question1.xml",null);

	}

}
