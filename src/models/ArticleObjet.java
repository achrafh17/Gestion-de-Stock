package models;

import org.bson.Document;
import org.bson.types.ObjectId;

public class ArticleObjet {
    String nom;
    String reference;
    String Categorie;
    int quantite;
    int seuilAlerte;
    String fournisseurid;
    double prix;
    ObjectId id = new ObjectId();

    public ArticleObjet(String nom, String reference, String Categorie, int quantite, int seuilAlerte,
            String fournisseurid, double prix) {
        this.nom = nom;
        this.reference = reference;
        this.Categorie = Categorie;
        this.quantite = quantite;
        this.seuilAlerte = seuilAlerte;
        this.fournisseurid = fournisseurid;
        this.prix = prix;

    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    // pour faciliter l enregesitrement des objet dans mongodb
    public Document toDocument() {
        return new Document("NomArticle", nom).append("ReferenceArticle", reference)
                .append("CategorieArticle", Categorie)
                .append("Quantite", quantite)
                .append("SeuilAlert", seuilAlerte)
                .append("IDFournisseur", fournisseurid)
                .append("Prix", prix);
    }

}