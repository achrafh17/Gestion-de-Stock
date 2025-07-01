import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Filters;

import javax.swing.border.LineBorder;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import io.github.cdimascio.dotenv.Dotenv;

//---------Appeler les packages Fournisseurs et Articles
import models.ArticleObjet;
import models.FournisseurObjet;
import models.MovementObjet;
import java.util.Timer;
import java.util.TimerTask;

//-----------------------------------------------------------
public class Gestion extends Application {
    @Override
    public void start(Stage stage) {
        // ---------BASE DE DONNNS---------------------------
      
            Dotenv dotenv = Dotenv.load();
            String user = dotenv.get("DB_USER");
            String password = dotenv.get("DB_PASSWORD");
            String url = "mongodb+srv://" + user + ":" + password
                    + "@cluster0.lsxjh.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
            MongoClient mongoclient = MongoClients.create(url);
            MongoDatabase database = mongoclient.getDatabase("Gestion_De_Stock");
            MongoCollection<Document> ArticleMongo = database.getCollection("Article");
            MongoCollection<Document> FourisseurMongo = database.getCollection("Fournisseur");
            MongoCollection<Document> MovementMongo = database.getCollection("Movement");
            System.out.println("Base de donne connectee");

        // --------------------------------------------------

        Label label = new Label();
        Label TitleArticle = new Label("Ajouter un nouvel Article");
        TitleArticle.getStyleClass().add("section-title");

        // -----------ARRAY DES ARTICLES-----------------------
        // ----------------------Nom Article---------------------
        VBox NomArticle = new VBox();
        NomArticle.getStyleClass().add("form-group");
        Label labelNomArticle = new Label("Nom de l' Article");
        labelNomArticle.getStyleClass().add("form-label");
        TextField ajouterNomArticle = new TextField();
        ajouterNomArticle.getStyleClass().add("form-textfield");
        ajouterNomArticle.setPromptText("Nom de l' Article");
        NomArticle.getChildren().addAll(labelNomArticle, ajouterNomArticle);

        // ----------------------Reference Article---------------------
        VBox ReferenceArticle = new VBox();
        ReferenceArticle.getStyleClass().add("form-group");
        Label labelReferenceArticle = new Label("Reference de l' Article");
        labelReferenceArticle.getStyleClass().add("form-label");
        TextField ajouterReferenceArticle = new TextField();
        ajouterReferenceArticle.getStyleClass().add("form-textfield");
        ajouterReferenceArticle.setPromptText("Refernce de Article");
        ReferenceArticle.getChildren().addAll(labelReferenceArticle, ajouterReferenceArticle);

        // ----------------------Categorie Article---------------------
        VBox Categorie = new VBox();
        Categorie.getStyleClass().add("form-group");
        Label labelCategorieArticle = new Label("Categorie de l' Article");
        labelCategorieArticle.getStyleClass().add("form-label");
        TextField CategorieArticle = new TextField();
        CategorieArticle.getStyleClass().add("form-textfield");
        CategorieArticle.setPromptText("Categorie d Article");
        Categorie.getChildren().addAll(labelCategorieArticle, CategorieArticle);

        // ----------------------Quantite Article---------------------
        VBox Quantite = new VBox();
        Quantite.getStyleClass().add("form-group");
        Label labelQuantiteArticle = new Label("Quantite de l' Article");
        labelQuantiteArticle.getStyleClass().add("form-label");
        TextField QuantiteArticle = new TextField();
        QuantiteArticle.textProperty().addListener((observable, oldvalue, newvalue) -> {

            if (!newvalue.matches("\\d+")) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Erreur de saisie");
                alert.setContentText("Veuillez entrer uniquement des chiffres pour la quantité.");
                alert.getDialogPane().getStyleClass().add("alert");
                alert.showAndWait();
            }

        });
        QuantiteArticle.getStyleClass().add("form-textfield");
        QuantiteArticle.setPromptText("Quantite d Article");
        Quantite.getChildren().addAll(labelQuantiteArticle, QuantiteArticle);

        // ---------------------SEUIL ALERTE---------------------------
        VBox SeuilAlerte = new VBox();
        SeuilAlerte.getStyleClass().add("form-group");
        Label labelSeuilAlert = new Label("Seuil d'alerte de produit");
        labelSeuilAlert.getStyleClass().add("form-label");
        TextField SeuilAlertinput = new TextField();
        SeuilAlertinput.textProperty().addListener((observable, oldvalue, newvalue) -> {

            if (!newvalue.matches("\\d+")) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Erreur de saisie");
                alert.setHeaderText("Erreur de saisie");
                alert.setContentText("Veuillez entrer uniquement des chiffres pour le seuil.");
                alert.getDialogPane().getStyleClass().add("alert");
                alert.showAndWait();
            }

        });
        SeuilAlertinput.getStyleClass().add("form-textfield");
        SeuilAlertinput.setPromptText("Seuil d'Alerte");
        SeuilAlerte.getChildren().addAll(labelSeuilAlert, SeuilAlertinput);
        // ----------------ID FOURNISSEUR ---------------------------
        VBox IDfournisseur = new VBox();
        IDfournisseur.getStyleClass().add("form-group");
        Label labelIDfournisseureArticle = new Label("ID de Fournisseur");
        labelIDfournisseureArticle.getStyleClass().add(" form-label");
        TextField FournisseurIDArticle = new TextField();
        FournisseurIDArticle.getStyleClass().add("form-textfield");
        FournisseurIDArticle.setPromptText("ID de Fournisseur");
        IDfournisseur.getChildren().addAll(labelIDfournisseureArticle, FournisseurIDArticle);

        // --------------------------------BOUTTON AJOUTER ANULLER Articles
        // --------------------
        HBox Bouttons_Ajouter_Anuller = new HBox();
        Bouttons_Ajouter_Anuller.getStyleClass().add("button-container");
        Button AjouterBoutton = new Button("Ajouter");
        AjouterBoutton.getStyleClass().add("btn-primary");
        Button AnullerBoutton = new Button("Annuler");
        ImageView iconCANCEL = new ImageView(
                new Image("file:src/asserts/close_24dp_367AF3_FILL0_wght400_GRAD0_opsz24.png"));
        AnullerBoutton.setGraphic(iconCANCEL);
        AnullerBoutton.getStyleClass().add("btn-secondary");
        Bouttons_Ajouter_Anuller.getChildren().addAll(AnullerBoutton, AjouterBoutton);

        // -------------------------------------------------

        VBox ArticleFinal = new VBox(20);
        ArticleFinal.getStyleClass().add("content-container");

        ImageView AjouterArticleIcon = new ImageView(new Image("file:src/asserts/plus.png"));
        AjouterArticleIcon.setFitHeight(15);
        AjouterArticleIcon.setFitWidth(15);
        Label AjouterArticleButtonLabel = new Label("Ajouter Article");
        AjouterArticleButtonLabel.getStyleClass().add("button-label");
        HBox AjouterArticleButtonItems = new HBox(8);
        AjouterArticleButtonItems.getChildren().addAll(AjouterArticleIcon, AjouterArticleButtonLabel);
        AjouterArticleButtonItems.setAlignment(Pos.CENTER);
        Button AjouterArticle = new Button();
        AjouterArticle.setGraphic(AjouterArticleButtonItems);
        AjouterArticle.getStyleClass().add("button-ajouter");

        VBox Article = new VBox();

        ArticleFinal.getChildren().addAll(AjouterArticle);
        Article.getStyleClass().add("form-container");
        Article.getChildren().addAll(TitleArticle, NomArticle,
                ReferenceArticle, Categorie, Quantite, SeuilAlerte, IDfournisseur, Bouttons_Ajouter_Anuller);
        Scene ArticleScene = new Scene(Article);
        Stage ArticleStage = new Stage();

        AjouterArticle.setOnAction(e -> {
            ArticleScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            ArticleStage.setTitle("Ajouter Article");
            ArticleStage.setScene(ArticleScene);
            ArticleStage.show();

        });
        AnullerBoutton.setOnAction(param -> {
            ArticleStage.close();

        });
        // ==========AFFICHAGE DES ARTICLES=======================================
        // -------CONSTRUIRE UN
        // TABLEAU-------------------------------------------------------
        ObservableList<ArticleObjet> observablesArticle = FXCollections.observableArrayList();
        TableView<ArticleObjet> tableArticle = new TableView<>();
        tableArticle.getStyleClass().add("table-view");

        TableColumn<ArticleObjet, String> ColumnNomArticle = new TableColumn<>("Nom");
        ColumnNomArticle.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ColumnNomArticle.getStyleClass().add("table-column");

        TableColumn<ArticleObjet, String> ColumnReferenceArticle = new TableColumn<>("Reference");
        ColumnReferenceArticle.setCellValueFactory(new PropertyValueFactory<>("reference"));
        ColumnReferenceArticle.getStyleClass().add("table-column");

        TableColumn<ArticleObjet, String> ColumnIDFOURNISSEURArticle = new TableColumn<>("ID_Fourniseur");
        ColumnIDFOURNISSEURArticle.setCellValueFactory(new PropertyValueFactory<>("fournisseurid"));
        ColumnIDFOURNISSEURArticle.getStyleClass().add("table-column");

        TableColumn<ArticleObjet, Void> QuantiteArticleColumn = new TableColumn<>("Quantite");
        QuantiteArticleColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        QuantiteArticleColumn.getStyleClass().add("table-column");

        TableColumn<ArticleObjet, Void> DeleteArticle = new TableColumn<>("Actions");
        DeleteArticle.getStyleClass().add("table-column");
        DeleteArticle.setCellFactory(params -> new TableCell<ArticleObjet, Void>() {

            ImageView deleteIcon = new ImageView(new Image("file:src/asserts/trash.png"));
            final Button deleteButton = new Button("", deleteIcon);

            final Button Modifier = new Button();
            HBox ActionsButtons = new HBox(Modifier, deleteButton);

            {
                ActionsButtons.alignmentProperty().set(Pos.CENTER);
                deleteButton.getStyleClass().add("delete-btn");
                deleteIcon.getStyleClass().add("delete-icon");

                deleteButton.setOnAction(e -> {

                    ArticleObjet article = getTableView().getItems().get(getIndex());
                    getTableView().getItems().remove(article);
                    Bson filter = Filters.eq("_id", article.getId());
                    ArticleMongo.deleteOne(filter);

                });
                Modifier.getStyleClass().add("modify-btn");
                ImageView iconModifier = new ImageView(
                        new Image("file:src/asserts/edit.png"));
                Modifier.setGraphic(iconModifier);

                Modifier.setOnAction(e -> {
                    Modifier.getStyleClass().add("modify-btn");

                    ArticleObjet article = getTableView().getItems().get(getIndex());

                    VBox windowRootArticle = new VBox();
                    windowRootArticle.getStyleClass().addAll("modification-window", "form-container");
                    // les input sont deja remplis avec les anciens valeurs de l article

                    // Nom Article
                    VBox NomArticleModifier = new VBox();
                    NomArticleModifier.getStyleClass().add("form-group");
                    Label NomArticleModifierLabel = new Label("Nom de l'article");
                    NomArticleModifierLabel.getStyleClass().add("form-label");
                    TextField NomArticleModifierInput = new TextField(article.getNom());
                    NomArticleModifierInput.getStyleClass().add("form-textfield");
                    NomArticleModifierInput.setPromptText("Entrez le nouveau nom de l'article");
                    NomArticleModifier.getChildren().addAll(NomArticleModifierLabel, NomArticleModifierInput);

                    // Reference Article
                    VBox ReferenceArticleModifier = new VBox();
                    ReferenceArticleModifier.getStyleClass().add("form-group");
                    Label ReferenceArticleModifierLabel = new Label("Référence de l'article");
                    ReferenceArticleModifierLabel.getStyleClass().add("form-label");
                    TextField ReferenceArticleModifierInput = new TextField(article.getReference());
                    ReferenceArticleModifierInput.getStyleClass().add("form-textfield");
                    ReferenceArticleModifierInput.setPromptText("Entrez la nouvelle référence de l'article");
                    ReferenceArticleModifier.getChildren().addAll(ReferenceArticleModifierLabel,
                            ReferenceArticleModifierInput);

                    // Categorie Article
                    VBox CategorieArticleModifier = new VBox();
                    CategorieArticleModifier.getStyleClass().add("form-group");
                    Label CategorieArticleModifierLabel = new Label("Catégorie de l'article");
                    CategorieArticleModifierLabel.getStyleClass().add("form-label");
                    TextField CategorieArticleModifierInput = new TextField(article.getCategorie());
                    CategorieArticleModifierInput.getStyleClass().add("form-textfield");
                    CategorieArticleModifierInput.setPromptText("Entrez la nouvelle catégorie de l'article");
                    CategorieArticleModifier.getChildren().addAll(CategorieArticleModifierLabel,
                            CategorieArticleModifierInput);

                    // Quantite Article
                    VBox QuantiteArticleModifier = new VBox();
                    QuantiteArticleModifier.getStyleClass().add("form-group");
                    Label QuantiteArticleModifierLabel = new Label("Quantité de l'article");
                    QuantiteArticleModifierLabel.getStyleClass().add("form-label");
                    TextField QuantiteArticleModifierInput = new TextField(String.valueOf(article.getQuantite()));
                    QuantiteArticleModifierInput.getStyleClass().add("form-textfield");
                    QuantiteArticleModifierInput.setPromptText("Entrez la nouvelle quantité de l'article");
                    QuantiteArticleModifier.getChildren().addAll(QuantiteArticleModifierLabel,
                            QuantiteArticleModifierInput);

                    // Seuil d'alerte
                    VBox SeuilArticleModifier = new VBox();
                    SeuilArticleModifier.getStyleClass().add("form-group");
                    Label SeuilArticleModifierLabel = new Label("Seuil d'alerte");
                    SeuilArticleModifierLabel.getStyleClass().add("form-label");
                    TextField SeuilArticleModifierInput = new TextField(String.valueOf(article.getSeuilAlerte()));
                    SeuilArticleModifierInput.getStyleClass().add("form-textfield");
                    SeuilArticleModifierInput.setPromptText("Entrez le nouveau seuil d'alerte");
                    SeuilArticleModifier.getChildren().addAll(SeuilArticleModifierLabel, SeuilArticleModifierInput);

                    // ID Fournisseur
                    VBox FournisseurIDArticleModifier = new VBox();
                    FournisseurIDArticleModifier.getStyleClass().add("form-group");
                    Label FournisseurIDArticleModifierLabel = new Label("ID du fournisseur");
                    FournisseurIDArticleModifierLabel.getStyleClass().add("form-label");
                    TextField FournisseurIDArticleModifierInput = new TextField(article.getFournisseurid());
                    FournisseurIDArticleModifierInput.getStyleClass().add("form-textfield");
                    FournisseurIDArticleModifierInput.setPromptText("Entrez le nouvel ID du fournisseur");
                    FournisseurIDArticleModifier.getChildren().addAll(FournisseurIDArticleModifierLabel,
                            FournisseurIDArticleModifierInput);

                    HBox EnregistrerAnullerModifierArticle = new HBox();
                    ImageView iconSAVE = new ImageView(
                            new Image("file:src/asserts/save_24dp_FFFFFF_FILL0_wght400_GRAD0_opsz24.png"));
                    // boutton pour enregistrer les modifications
                    Button EnregistrerFenetre = new Button("Enregistre");
                    EnregistrerFenetre.setGraphic(iconSAVE);

                    EnregistrerFenetre.getStyleClass().add("btn-primary");
                    ImageView iconCANCEL = new ImageView(
                            new Image("file:src/asserts/close_24dp_367AF3_FILL0_wght400_GRAD0_opsz24.png"));
                    Button AnullerFenetreArticle = new Button("Anuler");
                    AnullerFenetreArticle.setGraphic(iconCANCEL);

                    AnullerFenetreArticle.getStyleClass().add("btn-secondary");

                    EnregistrerAnullerModifierArticle.getChildren().addAll(AnullerFenetreArticle, EnregistrerFenetre);
                    EnregistrerAnullerModifierArticle.getStyleClass().add("button-container");
                    windowRootArticle.getChildren().addAll(NomArticleModifier, ReferenceArticleModifier,
                            CategorieArticleModifier, QuantiteArticleModifier, SeuilArticleModifier,
                            FournisseurIDArticleModifier, EnregistrerAnullerModifierArticle);

                    Stage modifierFenetre = new Stage();
                    modifierFenetre.setTitle("Modifier l'article");
                    Scene testt = new Scene(windowRootArticle);
                    testt.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

                    modifierFenetre.setScene(testt);
                    modifierFenetre.show();
                    EnregistrerFenetre.setOnAction(event -> {

                        article.setNom(NomArticleModifierInput.getText());
                        article.setReference(ReferenceArticleModifierInput.getText());
                        article.setCategorie(CategorieArticleModifierInput.getText());
                        article.setQuantite(Integer.parseInt(QuantiteArticleModifierInput.getText()));
                        article.setSeuilAlerte(Integer.parseInt(SeuilArticleModifierInput.getText()));
                        article.setFournisseurid(FournisseurIDArticleModifierInput.getText());

                        Bson filter = Filters.eq("_id", article.getId());

                        Document updateArticle = new Document("$set", article.toDocument());
                        ArticleMongo.updateOne(filter, updateArticle);

                        tableArticle.refresh();
                        modifierFenetre.close();

                    });
                    AnullerFenetreArticle.setOnAction(event -> {
                        tableArticle.refresh();
                        modifierFenetre.close();
                    });

                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(ActionsButtons);
                }
            }
        });

        TableColumn<ArticleObjet, String> ColumnCategorieArticle = new TableColumn<>("Categorie");
        ColumnCategorieArticle.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        ColumnCategorieArticle.getStyleClass().add("table-column");

        TableColumn<ArticleObjet, String> ColumnSeuilArticle = new TableColumn<>("Seuil");
        ColumnSeuilArticle.setCellValueFactory(new PropertyValueFactory<>("seuilAlerte"));
        ColumnSeuilArticle.getStyleClass().add("table-column");

        // -----------------MODIFIER ARTICLE--------------------------------

        tableArticle.setItems(observablesArticle);
        tableArticle.getColumns().addAll(ColumnNomArticle, ColumnReferenceArticle, ColumnIDFOURNISSEURArticle,
                QuantiteArticleColumn, ColumnCategorieArticle, ColumnSeuilArticle, DeleteArticle);

        for (Document doc : ArticleMongo.find()) {
            ArticleObjet articleobjet = new ArticleObjet(doc.getString("NomArticle"),
                    doc.getString("ReferenceArticle"),
                    doc.getString("CategorieArticle"),
                    doc.getInteger("Quantite", 0),
                    doc.getInteger("SeuilAlert", 0),
                    doc.getString("IDFournisseur"));
            articleobjet.setId(doc.getObjectId("_id"));
            observablesArticle.add(articleobjet);
            tableArticle.refresh();
        }

        // -------------LIRE LES INPUTS ET CREER UN ARTICLE----------------
        ArticleFinal.getChildren().add(tableArticle);

        AjouterBoutton.setOnAction(e -> {

            ArticleObjet ArticleInsert = new ArticleObjet(ajouterNomArticle.getText(),
                    ajouterReferenceArticle.getText(),
                    CategorieArticle.getText(),
                    Integer.parseInt(QuantiteArticle.getText()), Integer.parseInt(SeuilAlertinput.getText()),
                    FournisseurIDArticle.getText());

            Document docArticle = ArticleInsert.toDocument();

            ArticleMongo.insertOne(docArticle);

            observablesArticle.add(ArticleInsert);
            ajouterNomArticle.setText("");
            ajouterReferenceArticle.setText("");
            CategorieArticle.setText("");

            // -------------

            ArticleStage.close();

        });

        // ------------------------------------------------------------------------------------
        // -------------------------ID FOURNISSUER
        // -------------------------------------------------

        VBox IDFournisseurContainer = new VBox();
        IDFournisseurContainer.getStyleClass().add("form-group");
        Label labelIDFournisseur = new Label("ID Fournisseur");
        labelIDFournisseur.getStyleClass().add("form-label");
        TextField IDFournisseurInput = new TextField();
        IDFournisseurInput.getStyleClass().add("form-textfield");
        IDFournisseurInput.setPromptText("Entrer ID de fournisseur");
        IDFournisseurContainer.getChildren().addAll(labelIDFournisseur, IDFournisseurInput);

        // -------------NOM FOURNISSEUR---------------------------------------------
        Label FournisseurTitle = new Label("Ajouter un nouveau Fournisseur");
        FournisseurTitle.getStyleClass().add("section-title");

        VBox NomFournisseurContainer = new VBox();
        NomFournisseurContainer.getStyleClass().add("form-group");
        Label labelNomFournisseur = new Label("Nom Fournisseur");
        labelNomFournisseur.getStyleClass().add("form-label");
        TextField NomFournisseurInput = new TextField();
        NomFournisseurInput.getStyleClass().add("form-textfield");
        NomFournisseurInput.setPromptText("Entrer le nom de forunisseur");
        NomFournisseurContainer.getChildren().addAll(labelNomFournisseur, NomFournisseurInput);

        // ---------------EMAIL FOURNISSEUR--------------------------------
        VBox EmailFournisseurContainer = new VBox();
        EmailFournisseurContainer.getStyleClass().add("form-group");
        Label labelEmailFournisseur = new Label("Email Fournisseur");
        labelEmailFournisseur.getStyleClass().add("form-label");
        TextField EmailFournisseurInput = new TextField();
        EmailFournisseurInput.getStyleClass().add("form-textfield");
        EmailFournisseurInput.setPromptText("Entrer email de fournisseur");
        EmailFournisseurContainer.getChildren().addAll(labelEmailFournisseur, EmailFournisseurInput);

        // ---------------DELARER LES BOUTTONS AJOUTER ANNULER FOURNISSEUR-------------
        HBox Ajouter_Anller_Bouttons = new HBox();
        Ajouter_Anller_Bouttons.getStyleClass().add("button-container");
        Ajouter_Anller_Bouttons.setAlignment(Pos.CENTER);
        Button AjouterFournisseurButton = new Button("Ajouter");
        AjouterFournisseurButton.getStyleClass().add("btn-primary");
        Button AnuulerFournisseurButton = new Button("Anuller");
        ImageView iconCANCELl = new ImageView(
                new Image("file:src/asserts/close_24dp_367AF3_FILL0_wght400_GRAD0_opsz24.png"));
        AnuulerFournisseurButton.setGraphic(iconCANCELl);
        AnuulerFournisseurButton.getStyleClass().add("btn-secondary");
        Ajouter_Anller_Bouttons.getChildren().addAll(AnuulerFournisseurButton, AjouterFournisseurButton);

        // -----------------------------------------------------------------------------
        VBox FinalFournisseur = new VBox(20);
        FinalFournisseur.getStyleClass().add("content-container");
        ImageView AjouterFournisseurIcon = new ImageView(new Image("file:src/asserts/plus.png"));
        AjouterFournisseurIcon.setFitHeight(15);
        AjouterFournisseurIcon.setFitWidth(15);
        Label AjouterFournisseurButtonLabel = new Label("Ajouter Fournisseur");
        AjouterFournisseurButtonLabel.getStyleClass().add("button-label");
        HBox AjouterFournisseureButtonItems = new HBox(8);
        AjouterFournisseureButtonItems.getChildren().addAll(AjouterFournisseurIcon, AjouterFournisseurButtonLabel);
        AjouterFournisseureButtonItems.setAlignment(Pos.CENTER);
        Button AjouterFourrnisseur = new Button();
        AjouterFourrnisseur.setGraphic(AjouterFournisseureButtonItems);
        AjouterFourrnisseur.getStyleClass().add("button-ajouter");
        VBox Fournisseur = new VBox();

        Fournisseur.getStyleClass().add("form-container");
        Fournisseur.getChildren().addAll(FournisseurTitle, IDFournisseurContainer, NomFournisseurContainer,
                EmailFournisseurContainer,
                Ajouter_Anller_Bouttons);
        FinalFournisseur.getChildren().add(AjouterFourrnisseur);
        Scene FournisseurScene = new Scene(Fournisseur);
        Stage FournisseurStage = new Stage();
        AjouterFourrnisseur.setOnAction(e -> {

            FournisseurScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            FournisseurStage.setTitle("Ajouter Fournisseur");
            FournisseurStage.setScene(FournisseurScene);
            FournisseurStage.show();

        });
        AnuulerFournisseurButton.setOnAction(param -> {
            FournisseurStage.close();

        });
        // -----------Lire les input de fournisseur -------------------------------
        ObservableList<FournisseurObjet> observablesFournisseur = FXCollections.observableArrayList();
        TableView<FournisseurObjet> tableFournisseur = new TableView<>();
        tableFournisseur.getStyleClass().add("table-view");

        TableColumn<FournisseurObjet, String> ColumnIDfournisseur = new TableColumn<>("ID");
        ColumnIDfournisseur.setCellValueFactory(new PropertyValueFactory("id"));
        ColumnIDfournisseur.setStyle("-fx-alignment: CENTER;");

        TableColumn<FournisseurObjet, String> ColumnNomFournisseur = new TableColumn<>("Nom");
        ColumnNomFournisseur.setCellValueFactory(new PropertyValueFactory("nomFournisseur"));
        ColumnNomFournisseur.setStyle("-fx-alignment: CENTER;");

        TableColumn<FournisseurObjet, String> ColumnEmailFourniseur = new TableColumn<>("Email");
        ColumnEmailFourniseur.setCellValueFactory(new PropertyValueFactory("emailFournisseur"));
        ColumnEmailFourniseur.getStyleClass().add("table-column");
        ColumnEmailFourniseur.setStyle("-fx-alignment: CENTER;");

        // ========================supprimer un fournisseur=======================

        TableColumn<FournisseurObjet, Void> DeleteFournisseur = new TableColumn<>("Actions");
        DeleteFournisseur.setStyle("-fx-alignment: CENTER;");
        ColumnEmailFourniseur.getStyleClass().add("table-column");

        DeleteFournisseur.setCellFactory(params -> new TableCell<FournisseurObjet, Void>() {
            Image deleteIcon = new Image(
                    "file:src/asserts/delete_24dp_EA3323_FILL0_wght400_GRAD0_opsz24.png");
            ImageView imageview = new ImageView(deleteIcon);
            Button deleteButton = new Button("", imageview);
            Button Modifier = new Button();
            HBox ActionsFournisseur = new HBox();

            {
                ActionsFournisseur.getChildren().addAll(Modifier, deleteButton);
                ActionsFournisseur.alignmentProperty().set(Pos.CENTER);

                deleteButton.getStyleClass().add("delete-btn");
                imageview.getStyleClass().add("delete-icon");

                deleteButton.setOnAction(e -> {
                    FournisseurObjet fournisseur = getTableView().getItems().get(getIndex());
                    Bson filter = Filters.eq("_id", fournisseur.getObjectId());
                    FourisseurMongo.deleteOne(filter);
                    getTableView().getItems().remove(fournisseur);

                });
                Modifier.getStyleClass().add("modify-btn");
                ImageView iconModifier = new ImageView(
                        new Image("file:src/asserts/edit.png"));
                Modifier.setGraphic(iconModifier);

                Modifier.setOnAction(e -> {

                    FournisseurObjet fournisseur = getTableView().getItems().get(getIndex());
                    VBox windowRootFournisseur = new VBox();
                    windowRootFournisseur.getStyleClass().addAll("modification-window", "form-container");

                    VBox NomFournisseurModifier = new VBox();
                    NomFournisseurModifier.getStyleClass().add("form-group");
                    Label NomFournisseurModifierLabel = new Label("Nom de Fournisseur");
                    NomFournisseurModifierLabel.getStyleClass().add("form-label");
                    TextField NomFournisseurModifierInput = new TextField(fournisseur.getNomFournisseur());
                    NomFournisseurModifierInput.getStyleClass().add("form-textfield");
                    NomFournisseurModifierInput.setPromptText("Entrez le nouveau nom de Fournisseur");
                    NomFournisseurModifier.getChildren().addAll(NomFournisseurModifierLabel,
                            NomFournisseurModifierInput);
                    // Email Fournisseur
                    VBox EmailFournisseurModifier = new VBox();
                    EmailFournisseurModifier.getStyleClass().add("form-group");
                    Label EmailFournisseurModifierLabel = new Label("Email de Fournisseur");
                    EmailFournisseurModifierLabel.getStyleClass().add("form-label");
                    TextField EmailFournisseurModifierInput = new TextField(fournisseur.getEmailFournisseur());
                    EmailFournisseurModifierInput.getStyleClass().add("form-textfield");
                    EmailFournisseurModifierInput.setPromptText("Entrez le nouvelle mail de Fournisseur");
                    EmailFournisseurModifier.getChildren().addAll(EmailFournisseurModifierLabel,
                            EmailFournisseurModifierInput);
                    // ID Fournisseur
                    VBox IdFournisseurModifier = new VBox();
                    IdFournisseurModifier.getStyleClass().add("form-group");
                    Label IdFournisseurModifierLabel = new Label("ID de Fournisseur");
                    IdFournisseurModifierLabel.getStyleClass().add("form-label");
                    TextField IdFournisseurModifierInput = new TextField(fournisseur.getId());
                    IdFournisseurModifierInput.getStyleClass().add("form-textfield");
                    IdFournisseurModifierInput.setPromptText("Entrez la nouvelle id de fournisseur");
                    IdFournisseurModifier.getChildren().addAll(IdFournisseurModifierLabel,
                            IdFournisseurModifierInput);

                    HBox EnregistrerAnullerModifierFournisseur = new HBox();

                    ImageView iconSAVE = new ImageView(
                            new Image("file:src/asserts/save_24dp_FFFFFF_FILL0_wght400_GRAD0_opsz24.png"));
                    Button EnregistrerFenetre = new Button("Enregistre");
                    EnregistrerFenetre.setGraphic(iconSAVE);
                    EnregistrerFenetre.getStyleClass().add("btn-primary");

                    ImageView iconCANCEL = new ImageView(
                            new Image("file:src/asserts/close_24dp_367AF3_FILL0_wght400_GRAD0_opsz24.png"));
                    Button AnullerFenetre = new Button("Anuler");
                    AnullerFenetre.setGraphic(iconCANCEL);
                    AnullerFenetre.getStyleClass().add("btn-secondary");

                    EnregistrerAnullerModifierFournisseur.getChildren().addAll(AnullerFenetre, EnregistrerFenetre);
                    EnregistrerAnullerModifierFournisseur.getStyleClass().add("button-container");

                    windowRootFournisseur.getChildren().addAll(IdFournisseurModifier, NomFournisseurModifier,
                            EmailFournisseurModifier, EnregistrerAnullerModifierFournisseur);

                    Stage modifierFenetre = new Stage();
                    modifierFenetre.setTitle("Modifier le fournisseur");
                    Scene testt = new Scene(windowRootFournisseur);
                    testt.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

                    modifierFenetre.setScene(testt);
                    modifierFenetre.show();

                    EnregistrerFenetre.setOnAction(event -> {

                        fournisseur.setNomFournisseur(NomFournisseurModifierInput.getText());
                        fournisseur.setEmailFournisseur(EmailFournisseurModifierInput.getText());
                        fournisseur.setId(IdFournisseurModifierInput.getText());
                        tableFournisseur.refresh();
                        modifierFenetre.close();

                        Bson filter = Filters.eq("_id", fournisseur.getObjectId());
                        Document updateFournisseur = new Document("$set", fournisseur.toDocument());
                        FourisseurMongo.updateOne(filter, updateFournisseur);

                    });
                    AnullerFenetre.setOnAction(event -> {
                        tableFournisseur.refresh();
                        modifierFenetre.close();
                    });

                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(ActionsFournisseur);
                }
            }
        });
        // =============================MODIFIER UN FOURNISSEUR======================

        tableFournisseur.setItems(observablesFournisseur);
        tableFournisseur.getColumns().addAll(ColumnIDfournisseur, ColumnNomFournisseur, ColumnEmailFourniseur,
                DeleteFournisseur);
        FinalFournisseur.getChildren().add(tableFournisseur);

        for (Document doc : FourisseurMongo.find()) {
            FournisseurObjet f = new FournisseurObjet(doc.getString("id"), doc.getString("FournisseurNom"),
                    doc.getString("EmailFournisseur"));
            f.setObjectId(doc.getObjectId("_id"));
            observablesFournisseur.add(f);
        }

        AjouterFournisseurButton.setOnAction(e -> {

            FournisseurObjet fournisseurobjet = new FournisseurObjet(IDFournisseurInput.getText(),
                    NomFournisseurInput.getText(),
                    EmailFournisseurInput.getText());
            Document FournisseurInsert = fournisseurobjet.toDocument();
            FourisseurMongo.insertOne(FournisseurInsert);
            observablesFournisseur.add(fournisseurobjet);
            FournisseurStage.close();

        });

        // ------------------TABLE DE BOARD SECTION-----------------

        VBox TableDeBoardMain = new VBox(20);
        TableDeBoardMain.getStyleClass().add("dashboard-main");
        // Article total
        VBox TOtalArticles = new VBox();
        Label TOtalArticlesLabel = new Label("Nombre total d'articles");
        TOtalArticlesLabel.getStyleClass().add("stats-number-white");
        Label TOtalArticlesValue = new Label();
        TOtalArticlesValue.getStyleClass().add("stats-value");
        TOtalArticles.getChildren().addAll(TOtalArticlesLabel, TOtalArticlesValue);
        ImageView iconTotalArticles = new ImageView(
                new Image("file:src/asserts/package.png"));
        iconTotalArticles.setFitWidth(50);
        iconTotalArticles.setFitHeight(50);
        HBox FinalTotalArticles = new HBox();
        FinalTotalArticles.getStyleClass().addAll("stats-card", "stats-card-primary");
        FinalTotalArticles.getChildren().addAll(iconTotalArticles, TOtalArticles);

        // Qunatite total
        VBox QuantiteTotal = new VBox();
        Label QuantiteTotalLabel = new Label("Quantité totale d'articles");
        QuantiteTotalLabel.getStyleClass().add("stats-number-white");
        Label QuantiteTotalValue = new Label();
        QuantiteTotalValue.getStyleClass().add("stats-value");
        QuantiteTotal.getChildren().addAll(QuantiteTotalLabel, QuantiteTotalValue);
        HBox FinalTotalQuantite = new HBox();
        FinalTotalQuantite.getStyleClass().addAll("stats-card", "stats-card-primary");
        ImageView iconQuantiteTotal = new ImageView(
                new Image("file:src/asserts/trending-up.png"));
        iconQuantiteTotal.setFitWidth(50);
        iconQuantiteTotal.setFitHeight(50);
        FinalTotalQuantite.getChildren().addAll(iconQuantiteTotal, QuantiteTotal);
        // alert tableau de board
        Label AlertLabel = new Label("Alertes de Stock");
        AlertLabel.getStyleClass().add("stats-number-white");

        Label AlertValue = new Label();
        AlertValue.getStyleClass().add("stats-value");

        VBox AlertContainer = new VBox();
        AlertContainer.getChildren().addAll(AlertLabel, AlertValue);
        ImageView AlertIcon = new ImageView(
                new Image("file:src/asserts/alert-triangle.png"));
        AlertIcon.setFitWidth(50);
        AlertIcon.setFitHeight(50);
        HBox finalAlert = new HBox();
        finalAlert.getStyleClass().addAll("stats-card", "stats-card-primary");

        finalAlert.getChildren().addAll(AlertIcon, AlertContainer);

        ImageView TableDeBoardIcon = new ImageView(new Image("file:src/asserts/dashboard-alt.png"));
        TableDeBoardIcon.getStyleClass().add("sidebar-icon");

        HBox TableDeBoardButtonItems = new HBox(8);
        TableDeBoardButtonItems.getChildren().addAll(TableDeBoardIcon, new Label("Table de board"));

        ObservableList<ArticleObjet> ListArticleEnAlerte = FXCollections.observableArrayList();

        // ARTICLES EN ALERTES
        VBox AlerteEnStockMain = new VBox();
        AlerteEnStockMain.getStyleClass().addAll("stats-card", "stats-card-primary");

        HBox ArticleEnAlerteTitleContainer = new HBox(new Label("Article en alerte"));
        ArticleEnAlerteTitleContainer.getStyleClass().add("stats-number-white");

        Button TableDeBoard = new Button();
        TableDeBoard.setGraphic(TableDeBoardButtonItems);
        TableDeBoard.getStyleClass().add("sidebar-button");

        VBox AlerteContainer = new VBox();
        AlerteContainer.getStyleClass().add("alerte-container");
        AlerteEnStockMain.getChildren().addAll(ArticleEnAlerteTitleContainer, AlerteContainer);

        TableDeBoardMain.getChildren().addAll(FinalTotalArticles, FinalTotalQuantite, finalAlert,
                AlerteEnStockMain);

        // TO ARTICLE-----------------------------
        ImageView ArticleIcon = new ImageView(
                new Image("file:src/asserts/deployed_code_24dp_EA3323_FILL0_wght400_GRAD0_opsz24.png"));
        ArticleIcon.getStyleClass().add("sidebar-icon");

        Button toArticle = new Button(" Articles");
        toArticle.getStyleClass().add("sidebar-button");
        toArticle.setAlignment(Pos.CENTER_LEFT);
        toArticle.setGraphic(ArticleIcon);

        // TO FOURNISSEUR ---------------------------
        Button toFournisseur = new Button("Fournisseur");
        toFournisseur.getStyleClass().add("sidebar-button");
        ImageView FournisseurIcon = new ImageView(
                new Image("file:src/asserts/local_shipping_22dp_06C11C_FILL0_wght400_GRAD0_opsz24.png"));
        FournisseurIcon.getStyleClass().add("sidebar-icon");
        toFournisseur.setGraphic(FournisseurIcon);
        toFournisseur.setAlignment(Pos.CENTER_LEFT);

        // ---GESTION DE MOVEMENT ----------------------
        Button toGestionDeMovemenet = new Button("Gestion de Movement");
        toGestionDeMovemenet.getStyleClass().add("sidebar-button");
        ImageView GestionDeMovementIcon = new ImageView(
                new Image("file:src/asserts/warehouse_22dp_0000F5_FILL0_wght400_GRAD0_opsz24.png"));
        toGestionDeMovemenet.setGraphic(GestionDeMovementIcon);
        toGestionDeMovemenet.setAlignment(Pos.CENTER_LEFT);
        // -----------------GESTION DE MOVEMENT-------------------------
        VBox GestionDeMovemenetMain = new VBox(20);

        Button AjouterMovement = new Button();
        ImageView AjouterMovementIcon = new ImageView(new Image("file:src/asserts/plus.png"));
        AjouterMovementIcon.setFitHeight(15);
        AjouterMovementIcon.setFitWidth(15);
        Label AjouterMovementButtonLabel = new Label("Ajouter Movement");
        AjouterMovementButtonLabel.getStyleClass().add("button-label");
        HBox AjouterMovementButtonItems = new HBox(8);
        AjouterMovementButtonItems.getChildren().addAll(AjouterMovementIcon, AjouterMovementButtonLabel);
        AjouterMovementButtonItems.setAlignment(Pos.CENTER);

        AjouterMovement.setGraphic(AjouterMovementButtonItems);
        AjouterMovement.getStyleClass().add("button-ajouter");

        GestionDeMovemenetMain.getChildren().add(AjouterMovement);
        VBox Movement = new VBox();
        Movement.getStyleClass().add("form-container");

        // Ajouter les input et les labels de gestion de movement
        VBox CategorieArticleMovement = new VBox();
        CategorieArticleMovement.getStyleClass().add("form-group");
        Label labelCategorieMovement = new Label("Article");// just pour peciser article souhaite pour mo=vement
        labelCategorieMovement.getStyleClass().add("form-label");
        ComboBox<String> ajouterCategorieArticleMovement = new ComboBox<>();

        ajouterCategorieArticleMovement.getStyleClass().add("form-textfield");
        ajouterCategorieArticleMovement.setPromptText("Selectioner un article");
        CategorieArticleMovement.getChildren().addAll(labelCategorieMovement, ajouterCategorieArticleMovement);

        // ----------------------TYPE DE MOVEMENT---------------------
        VBox TypeMovement = new VBox();
        TypeMovement.getStyleClass().add("form-group");
        Label labelTypeMovement = new Label("Type de movement ");
        labelTypeMovement.getStyleClass().add("form-label");
        ComboBox<String> AjouterTypeDeMovementInput = new ComboBox<>();
        AjouterTypeDeMovementInput.getItems().addAll("Entree(Reception)", "Sortie(vente)");
        AjouterTypeDeMovementInput.getStyleClass().add("form-textfield");
        AjouterTypeDeMovementInput.setPromptText("Selectioner Type de movement");
        TypeMovement.getChildren().addAll(labelTypeMovement, AjouterTypeDeMovementInput);

        // ----------------------Quantite de Movement---------------------
        VBox QuantiteMovement = new VBox();
        QuantiteMovement.getStyleClass().add("form-group");
        Label LabelQuantiteMovement = new Label("Quantite");
        LabelQuantiteMovement.getStyleClass().add("form-label");
        TextField QuantiteMovementInput = new TextField();
        QuantiteMovementInput.getStyleClass().add("form-textfield");
        QuantiteMovementInput.setPromptText("Entrer Qunatite");
        QuantiteMovement.getChildren().addAll(LabelQuantiteMovement, QuantiteMovementInput);
        // ----------------------Commentaire ---------------------
        VBox CommentaireMovement = new VBox();
        CommentaireMovement.getStyleClass().add("form-group");
        Label LabelCommentaireMovement = new Label("Commentaire");
        LabelCommentaireMovement.getStyleClass().add("form-label");
        TextField CommentaireMovemnetInput = new TextField();
        CommentaireMovemnetInput.getStyleClass().add("form-textfield");
        CommentaireMovemnetInput.setPromptText("Motif de Movemnet");
        CommentaireMovement.getChildren().addAll(LabelCommentaireMovement, CommentaireMovemnetInput);
        // ----------bouttons ajouter/annuler
        HBox Bouttons_Ajouter_AnullerMovemnet = new HBox();
        Bouttons_Ajouter_AnullerMovemnet.getStyleClass().add("button-container");
        Button AjouterBouttonMovement = new Button("Ajouter");
        AjouterBouttonMovement.getStyleClass().add("btn-primary");
        Button AnullerBouttonMovement = new Button("Annuler");
        ImageView iconCANCEMovemnet = new ImageView(
                new Image("file:src/asserts/close_24dp_367AF3_FILL0_wght400_GRAD0_opsz24.png"));
        AnullerBouttonMovement.setGraphic(iconCANCEMovemnet);
        AnullerBouttonMovement.getStyleClass().add("btn-secondary");
        Bouttons_Ajouter_AnullerMovemnet.getChildren().addAll(AnullerBouttonMovement, AjouterBouttonMovement);

        Movement.getChildren().addAll(CategorieArticleMovement, TypeMovement, QuantiteMovement, CommentaireMovement,
                Bouttons_Ajouter_AnullerMovemnet);

        Scene MovementScene = new Scene(Movement);
        Stage MovementStage = new Stage();
        // c est boutton de fentre
        ObservableList<MovementObjet> ListMovement = FXCollections.observableArrayList();

        AjouterBouttonMovement.setOnAction(e -> {

            String articlereference = ajouterCategorieArticleMovement.getValue();
            MovementObjet movementobjet = new MovementObjet(Integer.parseInt(QuantiteMovementInput.getText()),
                    AjouterTypeDeMovementInput.getValue(), CommentaireMovemnetInput.getText(), LocalDate.now(),
                    articlereference);
            for (Document article : ArticleMongo.find()) {
                String Reference_Nom = article.getString("NomArticle") + "-"
                        + article.getString("ReferenceArticle");
                if (Reference_Nom.equals(ajouterCategorieArticleMovement.getValue())) {

                    if (AjouterTypeDeMovementInput.getValue().equals("Sortie(vente)")) {
                        if (article.getInteger("Quantite") >= Integer.parseInt(QuantiteMovementInput.getText())) {
                            ObjectId id = article.getObjectId("_id");
                            Bson f = Filters.eq("_id", id);
                            Document update = new Document("$inc",
                                    new Document("Quantite", -Integer.parseInt(QuantiteMovementInput.getText())));
                            ArticleMongo.updateOne(f, update);
                            for (ArticleObjet art : observablesArticle) {
                                if (art.getReference().equals(article.getString("ReferenceArticle"))) {
                                    art.setQuantite(
                                            art.getQuantite() - Integer.parseInt(QuantiteMovementInput.getText()));

                                }
                            }
                            tableArticle.refresh();
                            ListMovement.add(movementobjet);
                            Document doc = movementobjet.toDocument();
                            MovementMongo.insertOne(doc);

                            MovementStage.close();

                        } else {
                            Alert QuantiteInsufisante = new Alert(AlertType.INFORMATION);
                            QuantiteInsufisante.setTitle("ERROR");
                            QuantiteInsufisante.setContentText("Quantite Insufisante");
                            QuantiteInsufisante.setHeaderText("Error");
                            QuantiteInsufisante.showAndWait();
                        }
                    } else if ((AjouterTypeDeMovementInput.getValue().equals("Entree(Reception)"))) {

                        ObjectId id = article.getObjectId("_id");
                        Bson f = Filters.eq("_id", id);
                        Document update = new Document("$inc",
                                new Document("Quantite", Integer.parseInt(QuantiteMovementInput.getText())));
                        ArticleMongo.updateOne(f, update);
                        for (ArticleObjet art : observablesArticle) {
                            if (art.getReference().equals(article.getString("ReferenceArticle"))) {
                                art.setQuantite(
                                        art.getQuantite() + Integer.parseInt(QuantiteMovementInput.getText()));

                            }
                        }
                        tableArticle.refresh();
                        ListMovement.add(movementobjet);
                        Document doc = movementobjet.toDocument();
                        MovementMongo.insertOne(doc);
                        MovementStage.close();

                    }
                }

            }

        });
        AnullerBouttonMovement.setOnAction(e -> {
            MovementStage.close();
        });

        AjouterMovement.setOnAction(e -> {

            if (observablesArticle.size() != 0) {
                for (Document article : ArticleMongo.find()) {
                    if (!ajouterCategorieArticleMovement.getItems()
                            .contains(
                                    article.getString("NomArticle") + "-" + article.getString("ReferenceArticle")))

                    {
                        ajouterCategorieArticleMovement.getItems()
                                .add(article.getString("NomArticle") + "-" + article.getString("ReferenceArticle"));
                    }
                }
                MovementScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
                MovementStage.setTitle("Nouveau Movemnet de Stock");
                MovementStage.setScene(MovementScene);
                MovementStage.show();
            } else {
                Alert EmptyArticle = new Alert(AlertType.INFORMATION);
                EmptyArticle.setTitle("Alert");
                EmptyArticle.setHeaderText("error");
                EmptyArticle.setContentText("Vous devez ajouter des Articles en premier!!");
                EmptyArticle.showAndWait();
            }

        });

        TableView<MovementObjet> TableMovement = new TableView<>();
        TableMovement.getStyleClass().add("table-view");

        TableColumn<MovementObjet, String> dateMovement = new TableColumn<>("DATE");
        dateMovement.setCellValueFactory(new PropertyValueFactory("date"));
        dateMovement.setStyle("-fx-alignment: CENTER;");

        TableColumn<MovementObjet, String> article_movement = new TableColumn<>("ARTICLE");
        article_movement.setCellValueFactory(new PropertyValueFactory("article_reference"));
        article_movement.setStyle("-fx-alignment: CENTER;");

        TableColumn<MovementObjet, String> TypeMovementColumn = new TableColumn<>("TYPE");
        TypeMovementColumn.setCellValueFactory(new PropertyValueFactory("type_movement"));
        TypeMovementColumn.setCellFactory(param -> new TableCell<MovementObjet, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    HBox typeSign = new HBox();
                    Label typeLabel = new Label(item);

                    if (item.equals("Entree(Reception)")) {

                        typeLabel.setStyle("-fx-text-fill: green;");
                    } else if (item.equals("Sortie(vente)")) {

                        typeLabel.setStyle("-fx-text-fill: red;");

                    }

                    typeSign.getChildren().addAll(typeLabel);
                    typeSign.setAlignment(Pos.CENTER);
                    setGraphic(typeSign);
                    setAlignment(Pos.CENTER);
                }
            }
        });
        TypeMovementColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<MovementObjet, Integer> QuntiteMovementColumn = new TableColumn<>("QUANTITE");
        QuntiteMovementColumn.setCellValueFactory(new PropertyValueFactory("quantite"));
        QuntiteMovementColumn.setStyle("-fx-alignment: CENTER;");
        QuntiteMovementColumn.setCellFactory(param -> new TableCell<MovementObjet, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    HBox typeSign = new HBox();
                    Label signLabel = new Label();
                    Label typeLabel = new Label(String.valueOf(item));
                    MovementObjet mov = getTableView().getItems().get(getIndex());
                    if (mov.getType_movement().equals("Entree(Reception)")) {
                        signLabel.setText("+");
                        signLabel.setStyle("-fx-text-fill: green;");
                        typeLabel.setStyle("-fx-text-fill: green;");
                    } else if (mov.getType_movement().equals("Sortie(vente)")) {
                        signLabel.setText("-");
                        signLabel.setStyle("-fx-text-fill: red;");
                        typeLabel.setStyle("-fx-text-fill: red;");

                    }

                    typeSign.getChildren().addAll(signLabel, typeLabel);
                    typeSign.setAlignment(Pos.CENTER);
                    setGraphic(typeSign);
                    setAlignment(Pos.CENTER);
                }
            }
        });

        TableColumn<MovementObjet, String> CommentaireMovementColumn = new TableColumn<>("COMMENTAIRE");
        CommentaireMovementColumn.setCellValueFactory(new PropertyValueFactory("commentaire"));
        CommentaireMovementColumn.setStyle("-fx-alignment: CENTER;");

        TableMovement.setItems(ListMovement);
        TableMovement.getColumns().addAll(dateMovement, article_movement, TypeMovementColumn, QuntiteMovementColumn,
                CommentaireMovementColumn);

        for (Document doc : MovementMongo.find()) {
            String dateStr = doc.getString("date");
            LocalDate movementDate;

            if (dateStr != null && !dateStr.isEmpty()) {
                try {
                    movementDate = LocalDate.parse(dateStr);
                } catch (DateTimeParseException e) {
                    movementDate = LocalDate.now();
                }
            } else {
                movementDate = LocalDate.now();
            }

            MovementObjet movement = new MovementObjet(
                    doc.getInteger("quantite"),
                    doc.getString("type"),
                    doc.getString("commentaire"),
                    movementDate,
                    doc.getString("reference"));
            movement.setObjectId(doc.getObjectId("_id"));
            ListMovement.add(movement);
        }
        TableMovement.refresh();

        GestionDeMovemenetMain.getChildren().add(TableMovement);

        // -----------------------BARRRE LATERRALLE------------------------------
        VBox BarreLateralle = new VBox();
        BarreLateralle.getStyleClass().add("sidebar");
        BarreLateralle.getChildren().addAll(TableDeBoard, toArticle, toFournisseur, toGestionDeMovemenet);
        BarreLateralle.setAlignment(Pos.TOP_LEFT);
        // ----------------DECLARATION DES GRANDES PARTIES----------
        Label mainTitle = new Label("Application Gestion de Stock");
        mainTitle.getStyleClass().add("main-title");
        // --------------------
        VBox main = new VBox();
        main.getStyleClass().add("content-area");

        TableDeBoard.setOnAction(param -> {
            AlerteContainer.getChildren().clear(); // Vider le conteneur
            ListArticleEnAlerte.clear();
            int count = 0;
            for (ArticleObjet article : observablesArticle) {
                count += article.getQuantite();
            }
            TOtalArticlesValue.setText(String.valueOf(observablesArticle.size()));
            QuantiteTotalValue.setText(String.valueOf(count));
            main.getChildren().clear();
            main.getChildren().setAll(TableDeBoardMain);

            int AlertCount = 0;
            for (ArticleObjet article : observablesArticle) {
                if (article.getQuantite() < article.getSeuilAlerte())
                    AlertCount++;
            }
            AlertValue.setText(String.valueOf(AlertCount));

            TableDeBoard.getStyleClass().add("active");
            toFournisseur.getStyleClass().remove("active");
            toArticle.getStyleClass().remove("active");
            toGestionDeMovemenet.getStyleClass().remove("active");

            for (ArticleObjet article : observablesArticle) {
                if (article.getQuantite() < article.getSeuilAlerte()) {
                    ListArticleEnAlerte.add(article);
                }
            }
            for (ArticleObjet article : ListArticleEnAlerte) {

                VBox ArticleBoxAlert = new VBox();// box qui va cintient nom quantite en stock et seuil
                ArticleBoxAlert.getStyleClass().add("alerte-item");

                Label NomArticleAlert = new Label("hheloo");// Nom d article
                NomArticleAlert.getStyleClass().add("alerte-titre");

                Label Stock_SeuilArticleAlerte = new Label();
                Stock_SeuilArticleAlerte.getStyleClass().add("alerte-stock");

                Label critiqueLabel = new Label("Critique");
                critiqueLabel.getStyleClass().add("alerte-critique");

                ArticleBoxAlert.getChildren().addAll(NomArticleAlert, Stock_SeuilArticleAlerte, critiqueLabel);
                NomArticleAlert.setText(article.getNom());
                Stock_SeuilArticleAlerte
                        .setText("Stock" + article.getQuantite() + "" + "/" + "Seuil" + article.getSeuilAlerte());

                AlerteContainer.getChildren().add(ArticleBoxAlert);

            }

        });

        toArticle.setOnAction(e -> {
            main.getChildren().setAll(ArticleFinal);
            toArticle.getStyleClass().add("active");
            toFournisseur.getStyleClass().remove("active");
            TableDeBoard.getStyleClass().remove("active");
            toGestionDeMovemenet.getStyleClass().remove("active");

        });

        toFournisseur.setOnAction(e -> {
            main.getChildren().setAll(FinalFournisseur);
            toFournisseur.getStyleClass().add("active");
            toArticle.getStyleClass().remove("active");
            TableDeBoard.getStyleClass().remove("active");
            toGestionDeMovemenet.getStyleClass().remove("active");
        });
        toGestionDeMovemenet.setOnAction(e -> {
            main.getChildren().setAll(GestionDeMovemenetMain);
            toGestionDeMovemenet.getStyleClass().add("active");
            toFournisseur.getStyleClass().remove("active");
            toArticle.getStyleClass().remove("active");
            TableDeBoard.getStyleClass().remove("active");
        });
        // -------------------------------
        HBox root = new HBox();
        root.getStyleClass().add("main-container");
        root.getChildren().addAll(BarreLateralle, main);

        ScrollPane scroll = new ScrollPane();
        scroll.getStyleClass().add("scroll-pane");
        scroll.setContent(root);
        scroll.setFitToWidth(true);

        Scene scene = new Scene(scroll);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Application Gestion de Stock");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}