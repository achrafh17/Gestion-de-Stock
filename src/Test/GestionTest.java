package Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import org.junit.Test;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import models.ArticleObjet;

public class GestionTest {
    Dotenv dotenv = Dotenv.load();
    String user = dotenv.get("DB_USER");
    String password = dotenv.get("DB_PASSWORD");

    @Test
    // -------------------------CRUD TEST--------------------------

    public void testerInser() {
        MongoClient client = MongoClients.create("mongodb+srv://" + user + ":" + password
                + "@cluster0.lsxjh.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0");

        MongoDatabase GestionDB = client.getDatabase("Gestion_De_Stock");
        MongoCollection<Document> ArticleDB = GestionDB.getCollection("Article");
        ArticleObjet article = new ArticleObjet("DELL-LATITUDE", "DL-001", "Informatique", 50, 5, "DELL");
        ArticleDB.insertOne(article.toDocument());
        Document doc = ArticleDB.find(eq("ReferenceArticle", "DL-001")).first();
        assertNotNull(doc);
        assertEquals("DELL-LATITUDE", doc.getString("NomArticle"));
        client.close();
    }

    @Test
    // tester update
    public void testerUpdate() {
        MongoClient client = MongoClients.create("mongodb+srv://" + user + ":" + password
                + "@cluster0.lsxjh.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0");

        MongoDatabase GestionDB = client.getDatabase("Gestion_De_Stock");
        MongoCollection<Document> ArticleDB = GestionDB.getCollection("Article");
        ArticleObjet article = new ArticleObjet("DELL-LATITUDE", "DL-001", "Informatique", 50, 5, "DELL");
        ArticleDB.updateOne(eq("ReferenceArticle", "DL-001"), set("NomArticle", "DELL-XPS"));
        Document doc = ArticleDB.find(eq("ReferenceArticle", "DL-001")).first();
        assertEquals(doc.getString("NomArticle"), "DELL-XPS");

    }

    // tester supprimer
    @Test
    public void testerSupprimer() {
        MongoClient client = MongoClients.create("mongodb+srv://" + user + ":" + password
                + "@cluster0.lsxjh.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0");

        MongoDatabase GestionDB = client.getDatabase("Gestion_De_Stock");
        MongoCollection<Document> ArticleDB = GestionDB.getCollection("Article");

        ArticleDB.deleteOne(eq("ReferenceArticle", "DL-001"));
        Document doc = ArticleDB.find(eq("ReferenceArticle", "DL-001")).first();
        assertNull(doc);

    }

    @Test
    public void testerAlert() {
        List<ArticleObjet> articles = Arrays.asList(new ArticleObjet("a1", "r1", "c1", 10, 12, "f1"),
                new ArticleObjet("a2", "r2", "c2", 100, 10, "f2"));
        int count = 0;
        for (ArticleObjet a : articles) {
            if (a.getQuantite() < a.getSeuilAlerte()) {
                count++;
            } else {
                return;
            }
        }
        assertTrue(count >= 1);

    }

    @Test
    public void TesterMovement() {
        ArticleObjet article = new ArticleObjet("a", "r", "c", 10, 2, "f");
        article.setQuantite(article.getQuantite() + 5);
        assertEquals(15, article.getQuantite());
    }
}
