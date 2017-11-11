package dom;

import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
public class Question2 {

	public static void main(String[] args) throws Exception
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(new File("/home/tarik/Bureau/question1.xml"));


		NodeList l = document.getElementsByTagName("article");
		for (int i=0;i<l.getLength();i++){
			Element e = (Element) l.item(i);
			//modification d’un attribut unite="euro" a l'article shampoing
			if (e.getAttributeNode("nom").getValue().equals("shampoing"))
				((Element)e.getFirstChild()).setAttribute("unite", "euro");
			//suppresion de l'article laque
			if (e.getAttributeNode("nom").getValue().equals("laque"))
				e.getParentNode().removeChild(l.item(i));
			//ajout d'un article perruque à 90 euros entre peigne et brosse
			if (e.getAttributeNode("nom").getValue().equals("brosse")){
				Element perruque = document.createElement("article");
				Element prix = document.createElement("prix");
				Text montant = document.createTextNode("90");
				Node brosse = document.getElementsByTagName("article").item(1);
				System.out.println(((Element)brosse).getAttributeNode("nom").getValue());
				
				
				(brosse.getParentNode()).insertBefore(perruque,brosse);
				
				perruque.setAttribute("nom", "perruque");
				perruque.appendChild(prix);
				prix.setAttribute("unite", "euro");
				prix.appendChild(montant);
			}
		}
		// outils fourni
		XMLTools.ecrireXML(document,"/home/tarik/Bureau/question2.xml",null);}
}
