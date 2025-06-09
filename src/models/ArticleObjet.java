package models;

public class ArticleObjet {
    String nom;
    String reference;
    String Categorie;
    int quantite;
    int seuilAlerte;
    String fournisseurid;

    public ArticleObjet(String nom, String reference, String Categorie, int quantite, int seuilAlerte,
            String fournisseurid) {
        this.nom = nom;
        this.reference = reference;
        this.Categorie = Categorie;
        this.quantite = quantite;
        this.seuilAlerte = seuilAlerte;
        this.fournisseurid = fournisseurid;
    }

    public String getNom() {
        return nom;
    };

    public String getReference() {
        return reference;
    };

    public String getCategorie() {
        return Categorie;
    };

    public int getQuantite() {
        return quantite;
    };

    public String getFournisseurid() {
        return fournisseurid;
    }

    public void setFournisseurid(String fournisseurid) {
        this.fournisseurid = fournisseurid;
    }

    public int getSeuilAlerte() {
        return seuilAlerte;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setCategorie(String categorie) {
        this.Categorie = categorie;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setSeuilAlerte(int seuilAlerte) {
        this.seuilAlerte = seuilAlerte;
    }

}