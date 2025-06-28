package models;

import java.time.LocalDate;
import org.bson.Document;

import org.bson.Document;
import org.bson.types.ObjectId;

public class MovementObjet {
    int quantite;
    String type_movement;
    String commentaire;
    LocalDate date;
    ObjectId objectId;
    String article_reference;

    public MovementObjet(int quantite, String type_movement, String commentaire, LocalDate date,
            String article_reference) {
        this.date = date;
        this.quantite = quantite;
        this.type_movement = type_movement;
        this.article_reference = article_reference;
        this.commentaire = commentaire;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getArticle_reference() {
        return article_reference;
    }

    public void setArticle_reference(String article_reference) {
        this.article_reference = article_reference;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getType_movement() {
        return type_movement;
    }

    public void setType_movement(String type_movement) {
        this.type_movement = type_movement;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public Document toDocument() {
        return new Document("Date", date).append("quantite", quantite).append("type", type_movement)
                .append("commentaire", commentaire).append("reference", article_reference);
    }
}
