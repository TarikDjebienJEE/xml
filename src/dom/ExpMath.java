package dom;
import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

public class ExpMath {
	public static double evaluate(Element e, Element rac){
		double v = 0.0;
		NodeList lesCst = rac.getElementsByTagName("const");
		// si on a une balise var on recupere directement sa valeur dans la constante declaré de meme nom
		if (e.getTagName().equals("var"))
		{
			for (int i=0;i<lesCst.getLength();i++){
				Element courant = (Element)lesCst.item(i);
				if ((courant.getAttributeNode("nom").getValue().equals(e.getAttributeNode("nom").getValue())))
					return v = Double.valueOf(courant.getAttributeNode("valeur").getValue());
			}

		}
		// sinon on a une balise op
		else if (e.getTagName().equals("op")){
			// on recupere l'operateur de op
			String operateur = e.getAttributeNode("symbole").getValue();
			// on recupere la valeur de l'operande de gauche 
			double valG = Double.valueOf(ExpMath.evaluate((Element)e.getFirstChild(), rac));
			// on recupere la valeur de l'operande de droite 
			double valD = Double.valueOf(ExpMath.evaluate((Element)e.getLastChild(), rac));
			// on retourne le resultat de l'opération souhaitée
			if (operateur.equals("+")){
				return valG + valD;
			}
			else if (operateur.equals("-")){
				return valG - valD;
			}else if (operateur.equals("*")){
				return valG * valD;
			}else
				return valG / valD;
				 
		}
		else
		System.out.println("erreur balise inconnu de la DTD");
		return v;
	}
	public static void main(String[] args) throws Exception{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setCoalescing(false);
		dbf.setIgnoringElementContentWhitespace(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(new File("/home/tarik/Documents/Ressources_Pedagogiques/Informatique/Langage de Programmation/Java/DomXMLJava/src/dom/Expressions.xml"));

		// Rac est l’élément racine du document
		Element rac = document.getDocumentElement();
		NodeList lesEx = rac.getElementsByTagName("exp");
		for (int i=0; i<lesEx.getLength(); i++) {
			String n = ((Element)lesEx.item(i)).getAttribute("nom");
			Element ex = (Element) lesEx.item(i).getFirstChild();
			System.out.println(n+":="+evaluate(ex, rac));
		}
	}
}
