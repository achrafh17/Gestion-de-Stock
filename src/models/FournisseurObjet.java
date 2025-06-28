package models;

import org.bson.Document;
import org.bson.types.ObjectId;

public class FournisseurObjet {
    private String nomFournisseur;
    private String emailFournisseur;
    private String id;
    private ObjectId objectId;

    public FournisseurObjet(String id, String nomFournisseur, String emailFournisseur) {
        this.id = id;
        this.nomFournisseur = nomFournisseur;
        this.emailFournisseur = emailFournisseur;
    }

    public Document toDocument() {
        return new Document("FournisseurNom", nomFournisseur)
                .append("EmailFournisseur", emailFournisseur)
                .append("id", id);
    }

    public ObjectId  getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
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