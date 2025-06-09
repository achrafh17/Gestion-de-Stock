package models;

public class FournisseurObjet {
    public String nomFournisseur;
    public String emailFournisseur;
    public String id;

    public FournisseurObjet(String id, String nomFournisseur, String emailFournisseur) {
        this.id = id;
        this.nomFournisseur = nomFournisseur;
        this.emailFournisseur = emailFournisseur;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    };

    public String getId() {
        return id;
    }

    public String getEmailFournisseur() {
        return emailFournisseur;
    };
}