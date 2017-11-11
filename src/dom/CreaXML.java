package dom;
import org.w3c.dom.*;
import javax.xml.parsers.*;


public class CreaXML {

	public static void main(String[] args) throws Exception
	{
	Attr a; Element e, e1, l ;
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	dbf.setIgnoringElementContentWhitespace(true);
	DocumentBuilder db = dbf.newDocumentBuilder();
	Document d = db.newDocument();
	Element rac = d.createElement("Mandats");
	rac.setAttribute("etablissement","petite ville");
	rac.setAttributeNode(a= d.createAttribute("exercice"));
	a.setValue("2005");
	(e = d.createElement("Mandat")).setAttribute("numero","101");
	(e1 = ((Element) e.cloneNode(true))).setAttribute("numero","102");
	e.appendChild(l = d.createElement("ligne")).appendChild(d.createElement("libelle")).
	appendChild(d.createTextNode("Cle USB"));
	l.appendChild(d.createElement("montant")).appendChild(d.createTextNode("60"));
	d.appendChild(rac).appendChild(e); rac.appendChild(e1);
	XMLTools.ecrireXML(d, "/home/tarik/Bureau/test.xml",null);
	}

}
