package metier;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;


public class Utilisateur extends Personne implements NommableUtilisateur{
	/** Identifiant de l'utilisateur est un nombre entier*/
	private Integer idUtilisateur;
	/** Attribut mot de passe*/
	private String pwd;
	/** Attribut du pseudonyme*/
	private String pseudonyme;
	/** Liste des livres empruntÚs par l'utilisateur*/
	protected ArrayList<EmpruntEnCours> emprunts = new ArrayList<EmpruntEnCours>();
	/** Variable du nombre d'emprunt*/
	private Integer nbEmpruntsEnCours=0;
	/** Archive des livres empruntÚs*/
	protected ArrayList<EmpruntArchive> archives = new ArrayList<EmpruntArchive>();
	
	public Utilisateur(String nom, String prenom, Date dateNaissance, String sexe,Integer idUtilisateur, String pwd, String pseudonyme) {
		super(nom, prenom, dateNaissance, sexe);
		this.idUtilisateur = idUtilisateur;
		this.pwd = pwd;
		this.pseudonyme = pseudonyme;
	}
	
	
	
	public Utilisateur(String nom, String prenom, Integer idUtilisateur, String pwd, String pseudonyme) {
		super(nom, prenom);
		this.idUtilisateur = idUtilisateur;
		this.pwd = pwd;
		this.pseudonyme = pseudonyme;
	}

	
	
	public Integer getNbEmpruntsEnCours() {
		return nbEmpruntsEnCours;
	}



	public void setNbEmpruntsEnCours(Integer nbEmpruntsEnCours) {
		this.nbEmpruntsEnCours = nbEmpruntsEnCours;
	}



	public Utilisateur(String nom, String prenom) {
		super(nom, prenom);
	}


	public ArrayList<EmpruntEnCours> getEmprunts() {
		return emprunts;
	}


	public void setEmprunts(ArrayList<EmpruntEnCours> emprunts) {
		this.emprunts = emprunts;
	}


	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}


	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getPseudonyme() {
		return pseudonyme;
	}


	public void setPseudonyme(String pseudonyme) {
		this.pseudonyme = pseudonyme;
	}
	
	
	
	public void addEmpruntEnCours(EmpruntEnCours ep) throws BiblioException  {
		if (ep.getEnumStatusExemplaire() == EnumStatusExemplaire.PRETE || ep.getEnumStatusExemplaire() == EnumStatusExemplaire.SUPPRIME){
			throw new BiblioException();
		} else {
			emprunts.add(ep);
			/*if (ep.getU()!=this) {
			ep.setU(this);
			}*/
			ep.setEnumStatusExemplaire(EnumStatusExemplaire.PRETE);
			nbEmpruntsEnCours++;
		}
	}
	
	public boolean containsExemplaire (Exemplaire ex) {
		if (ex.getEnumStatusExemplaire() == EnumStatusExemplaire.PRETE) {
			return true;
		} else {
			return false;
		}
	}
	
	public void removeEmprunts(EmpruntEnCours eep, Exemplaire ex, EmpruntArchive ea) {
		if (emprunts.size() < 1) {
			System.out.println("Erreur : Aucun livre empruntÚ");
		} else if (!containsExemplaire(ex)){
			System.out.println("Erreur : Livre non empruntÚ");
		} else {
			emprunts.remove(eep);
			archives.add(ea);
			ex.setEnumStatusExemplaire(EnumStatusExemplaire.DISPONIBLE);
			eep.setDateEmprunt(null);
			eep.setEx(null);
			eep.setU(null);
			nbEmpruntsEnCours--;
		}
	}
	
	@Override
	public String toString() {
		if (emprunts.size()>0) {
			return "Utilisateur [" + super.toString() + ", idUtilisateur = "
					+ idUtilisateur + ", pwd = " + pwd + ", pseudonyme = " + pseudonyme + ", \n emprunts en cours = \n" + emprunts.toString() + " ]";
		} else {
			return "Utilisateur [" + super.toString() + ", idUtilisateur = "
					+ idUtilisateur + ", pwd = " + pwd + ", pseudonyme = " + pseudonyme + "]";
		}
	}
	
	
	public static void main(String []args) throws BiblioException, ParseException  {
		
	}
			
}
