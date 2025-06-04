package models;
public class ArticleObjet {
    String nom;
    String reference;
    String Categorie;
    int quantite;

    public ArticleObjet(String nom, String reference, String Categorie, int quantite) {
        this.nom = nom;
        this.reference = reference;
        this.Categorie = Categorie;
        this.quantite = quantite;
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

}