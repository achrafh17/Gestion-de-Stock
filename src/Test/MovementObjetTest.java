package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import models.MovementObjet;

import org.bson.Document;
import org.junit.Test;

public class MovementObjetTest {
    MovementObjet movement = new MovementObjet(20, "Entree(Reception)", "Achat", LocalDate.now(), "Mac-001");

    @Test
    // tester Getters
    public void testerGetters() {
        assertEquals(20, movement.getQuantite());
        assertTrue("Entree(Reception)".equals(movement.getType_movement()));
        assertTrue("Achat".equals(movement.getCommentaire()));
        assertTrue(LocalDate.now().equals(movement.getDate()));
        assertTrue("Mac-001".equals(movement.getArticle_reference()));
    }

    @Test
    // tester setters
    public void testerSetters() {
        movement.setArticle_reference("Lenevo-thinkpad001");
        movement.setType_movement("Sortie(vente)");
        movement.setDate(LocalDate.of(2025, 7, 20));
        movement.setCommentaire("Vente");
        movement.setQuantite(50);

        assertTrue(movement.getArticle_reference().equals("Lenevo-thinkpad001"));
        assertTrue(movement.getType_movement().equals("Sortie(vente)"));
        assertTrue(movement.getDate().equals(LocalDate.of(2025, 7, 20)));
        assertTrue(movement.getCommentaire().equals("Vente"));
        assertTrue(movement.getQuantite() == 50);
    }

    @Test
    // Tester to document
    public void testerToDocument() {
        assertEquals(movement.toDocument(),
                new Document("Date", LocalDate.of(2025, 7, 06)).append("quantite", 20)
                        .append("type", "Entree(Reception)")
                        .append("commentaire", "Achat").append("reference", "Mac-001"));
    }
}
