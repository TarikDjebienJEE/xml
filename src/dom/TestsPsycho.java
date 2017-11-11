package dom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TestsPsycho {



	// le titre du questionnaire
	private String titre;

	// les interpretations possibles
	private int nbreInterpretation;
	private Map<String,String> lesInterpretations;

	// les Questions du questionnaire
	private int nbreQuestions;
	private Map<Integer,Question> lesQuestions;



	/**
	 * @return the titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * @param titre the titre to set
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @return the nbreInterpretation
	 */
	public int getNbreInterpretation() {
		return nbreInterpretation;
	}

	/**
	 * @param nbreInterpretation the nbreInterpretation to set
	 */
	public void setNbreInterpretation(int nbreInterpretation) {
		this.nbreInterpretation = nbreInterpretation;
	}

	/**
	 * @return the lesInterpretations
	 */
	public Map<String, String> getLesInterpretations() {
		return lesInterpretations;
	}

	/**
	 * @param lesInterpretations the lesInterpretations to set
	 */
	public void setLesInterpretations(Map<String, String> lesInterpretations) {
		this.lesInterpretations = lesInterpretations;
	}

	/**
	 * @return the nbreQuestions
	 */
	public int getNbreQuestions() {
		return nbreQuestions;
	}

	/**
	 * @param nbreQuestions the nbreQuestions to set
	 */
	public void setNbreQuestions(int nbreQuestions) {
		this.nbreQuestions = nbreQuestions;
	}

	/**
	 * @return the lesQuestions
	 */
	public Map<Integer, Question> getLesQuestions() {
		return lesQuestions;
	}

	/**
	 * @param lesQuestions the lesQuestions to set
	 */
	public void setLesQuestions(Map<Integer, Question> lesQuestions) {
		this.lesQuestions = lesQuestions;
	}


	// Constructeur
	public TestsPsycho(String titre,int nbI ,int nbQ ) {
		this.titre = titre;
		this.nbreInterpretation = nbI;
		this.lesInterpretations = new HashMap<String,String>(nbI);
		this.nbreQuestions = nbQ;
		this.lesQuestions = new HashMap<Integer,Question>(nbQ);
	}




	private class Question{
		private String enonce;
		private Map<String, List<Reponse>> lesPropositions;
		public Question(String s,List<Reponse> l){
			this.enonce = s;
			this.lesPropositions = new HashMap<String,List<Reponse>>();
			this.lesPropositions.put(s, l);
		}

		/**
		 * @return the enonce
		 */
		public String getEnonce() {
			return enonce;
		}
		/**
		 * @return the lesPropositions
		 */
		public Map<String, List<Reponse>> getLesPropositions() {
			return lesPropositions;
		}

	}

	private class Reponse{
		private String libelle;
		private String idref;
		/**
		 * @return the libelle
		 */
		public String getLibelle() {
			return libelle;
		}
		public Reponse(String l,String idref){
			this.libelle=l;
			this.idref=idref;
		}
		/**
		 * @return the idref
		 */
		public String getIdref() {
			return idref;
		}
	}

	public static void main(String[] args) throws ParserConfigurationException{
		/* Création du document XML */
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setIgnoringElementContentWhitespace(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document docXML = db.newDocument();
		/* Création de la racine du DOM XML */
		Element racine = docXML.createElement("questionnaires");

		/* Saisie des informations sur le questionnaire */
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir le titre du Test : ");
		String titreQuestionnaire = sc.nextLine(); 
		System.out.println("Veuillez saisir le nombre de caractères étudiés : ");
		int nbreCaractères = sc.nextInt();
		System.out.println("Veuillez saisir le nombre de questions qui le composent : ");
		int nbQuestions = sc.nextInt();
		/* Instanciation du Test psychologique */
		TestsPsycho monTest = new TestsPsycho(titreQuestionnaire,nbreCaractères,nbQuestions);
		/* On nourrit les interprétations possibles */
		for(int i=0;i<monTest.getNbreInterpretation();i++){
			Scanner sc2 = new Scanner(System.in);
			System.out.println("Entrez l'indicateur numero "+i+". Exemple : triangle,rond,carre");
			
			String indicCourant = sc2.nextLine();
			System.out.println("Veuillez saisir le commentaire associé à ce caractère : ");
			String commCourant = sc2.nextLine();
			monTest.getLesInterpretations().put(indicCourant, commCourant);
		}
		/* On nourrit les questions possibles */
		for(int i=0;i<monTest.getNbreQuestions();i++){
			Scanner sc3 = new Scanner(System.in);
			// On recupere l'enonce de la question
			System.out.println("Entrez l'enoncé de la question "+i);
			String enonceCourant = sc3.nextLine();
			// On creer la liste des reponses possibles pour la questions
			List<Reponse> l = new ArrayList<Reponse>();
			// On recupere les reponses possible pour chaque question
			for(int j=0;j<monTest.getNbreInterpretation();j++){
				Scanner sc4 = new Scanner(System.in);
				System.out.println("Entrez la reponse possible numero "+j+" pour la question "+i);
				String reponseCourante = sc4.nextLine();
				// on demande quel caractere est associé à la reponse en verifiant qu'il appartient bien au indicateur possible
				boolean appartient = false;
				String carCourant;
				do{	
					Scanner sc5 = new Scanner(System.in);
					System.out.println("Entrez le caractère associé à la reponse "+j+" : "+reponseCourante);
					carCourant = sc5.nextLine();
					if (monTest.getLesInterpretations().keySet().contains(carCourant)) appartient = true; 
				}while(appartient != true);
				// on creer la reponse 
				Reponse repCourante = monTest.new Reponse(reponseCourante,carCourant);
				// et on l'ajoute dans la liste possibles
				l.add(repCourante);
			}
			// On construit la question
			Question questionCourante = monTest.new Question(enonceCourant,l);
			// que l'on ajoute au questionnaire 
			monTest.getLesQuestions().put(i, questionCourante);
		}

		/* Construction du document */
		Element questionnaire,titre,description,interpretation,questions,q,enonce,propositions,reponse;
		racine.appendChild(questionnaire = docXML.createElement("questionnaire"));
		questionnaire.appendChild(titre = docXML.createElement("titre"));
		titre.appendChild(docXML.createTextNode(monTest.getTitre()));
		questionnaire.appendChild(description = docXML.createElement("description"));
		Iterator<String> it = monTest.getLesInterpretations().keySet().iterator();
		for (int i = 0;i< monTest.getNbreInterpretation();i++){
			String s = (String)it.next();
			description.appendChild(interpretation= docXML.createElement("interpretation"));
			interpretation.setAttribute("indicateur", s);
			interpretation.appendChild(docXML.createTextNode(monTest.getLesInterpretations().get(s)));
		}
		questionnaire.appendChild(questions = docXML.createElement("questions"));
		for(int i = 0;i< monTest.getNbreQuestions();i++){
			questions.appendChild(q = docXML.createElement("q"));
			q.appendChild(enonce = docXML.createElement("enonce"));
			String e = monTest.getLesQuestions().get(i).getEnonce();
			enonce.appendChild(docXML.createTextNode(e));
			q.appendChild(propositions= docXML.createElement("propositions"));
			for(int j=0;j<monTest.getNbreInterpretation();j++){
				propositions.appendChild(reponse = docXML.createElement("reponse"));
				reponse.setAttribute("idref", monTest.getLesQuestions().get(i).getLesPropositions().get(e).get(j).getIdref());
				reponse.appendChild(docXML.createTextNode(monTest.getLesQuestions().get(i).getLesPropositions().get(e).get(j).getLibelle()));
			}
		}
		
		docXML.appendChild(racine);
		


		/* Ecriture du fichier XML */
		XMLTools.ecrireXML(docXML, "/home/tarik/Bureau/testPsycho.xml",null);
	}

}