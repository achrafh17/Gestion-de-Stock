package models;

public class FournisseurObjet {
    private String nomFournisseur;
    private String emailFournisseur;
    private String id;

    public FournisseurObjet(String id, String nomFournisseur, String emailFournisseur) {
        this.id = id;
        this.nomFournisseur = nomFournisseur;
        this.emailFournisseur = emailFournisseur;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailFournisseur() {
        return emailFournisseur;
    }

    public void setEmailFournisseur(String emailFournisseur) {
        this.emailFournisseur = emailFournisseur;
    }
}