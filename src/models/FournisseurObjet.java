package models;

public class FournisseurObjet {
    public String nomFournisseur;
    public String emailFournisseur;

    public FournisseurObjet(String nomFournisseur, String emailFournisseur) {
        this.nomFournisseur = nomFournisseur;
        this.emailFournisseur = emailFournisseur;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    };

    public String getEmailFournisseur() {
        return emailFournisseur;
    };
}