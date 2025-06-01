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
}
