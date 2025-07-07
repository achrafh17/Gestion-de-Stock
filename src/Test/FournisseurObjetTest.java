package Test;

import models.FournisseurObjet;
import org.junit.Test;
import static org.junit.Assert.*;
import org.bson.Document;

public class FournisseurObjetTest {
    @Test
    // tester getters
    public void testerGetters() {
        FournisseurObjet fournisseur = new FournisseurObjet("Electroplanet-001", "Electroplanet",
                "Electroplanet@gmail.ma");

        assertEquals("Electroplanet-001", fournisseur.getId());
    }

    @Test
    public void testSetters() {
        FournisseurObjet fournisseur = new FournisseurObjet("Electroplanet-001", "Electroplanet",
                "Electroplanet@gmail.ma");

        fournisseur.setId("elctroCasablanca-001");
        fournisseur.setNomFournisseur("electrocasablanca");
        fournisseur.setEmailFournisseur("electrocasablanca@gmail.ma");

        assertEquals("elctroCasablanca-001", fournisseur.getId());
        assertEquals("electrocasablanca", fournisseur.getNomFournisseur());
        assertEquals("electrocasablanca@gmail.ma", fournisseur.getEmailFournisseur());

    }

    @Test
    // tester fonction to docuement
    public void testToDocument() {
        FournisseurObjet fournisseur = new FournisseurObjet("Electroplanet-001", "Electroplanet",
                "Electroplanet@gmail.ma");

        assertEquals(new Document("FournisseurNom", "Electroplanet")
                .append("EmailFournisseur", "Electroplanet@gmail.ma").append("id", "Electroplanet-001"),
                fournisseur.toDocument());
    }
}