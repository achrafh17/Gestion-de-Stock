package Test;

import models.ArticleObjet;
import org.junit.Test;
import static org.junit.Assert.*;
import org.bson.Document;

public class ArticleObjetTest {
    @Test

    public void testGetters() {
        ArticleObjet article = new ArticleObjet("Thinkpad", "TP-001", "Informatique", 100, 10, "Electroplanet");

        // tester getters
        assertEquals("Thinkpad", article.getNom());
        assertEquals("TP-001", article.getReference());
        assertEquals("Informatique", article.getCategorie());
        assertEquals(100, article.getQuantite());
        assertEquals(10, article.getSeuilAlerte());
        assertEquals("Electroplanet", article.getFournisseurid());
    }

    @Test
    public void testSetters() {
        ArticleObjet article = new ArticleObjet("Thinkpad", "TP-001", "Informatique", 100, 10, "Electroplanet");

        article.setNom("MacBook");
        article.setReference("Mac-001");
        article.setCategorie("Informatique-MacOS");
        article.setQuantite(200);
        article.setSeuilAlerte(30);
        article.setFournisseurid("Apple");

        assertEquals("MacBook", article.getNom());
        assertEquals("Mac-001", article.getReference());
        assertEquals("Informatique-MacOS", article.getCategorie());
        assertEquals(200, article.getQuantite());
        assertEquals(30, article.getSeuilAlerte());
        assertEquals("Apple", article.getFournisseurid());

    }

    @Test

    public void testToDocument() {
        ArticleObjet article = new ArticleObjet("Thinkpad", "TP-001", "Informatique", 100, 10, "Electroplanet");
        assertEquals(new Document("NomArticle", "Thinkpad").append("ReferenceArticle", "TP-001")
                .append("CategorieArticle", "Informatique")
                .append("Quantite", 100)
                .append("SeuilAlert", 10)
                .append("IDFournisseur", "Electroplanet"), article.toDocument());
    }
}
