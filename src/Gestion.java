import java.util.ArrayList;

import javax.swing.border.LineBorder;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
//---------Appeler les packages Fournisseurs et Articles
import models.ArticleObjet;
import models.FournisseurObjet;

//-----------------------------------------------------------
public class Gestion extends Application {
    @Override
    public void start(Stage stage) {
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
        QuantiteArticle.getStyleClass().add("form-textfield");
        QuantiteArticle.setPromptText("Quantite d Article");
        Quantite.getChildren().addAll(labelQuantiteArticle, QuantiteArticle);
        // --------------------------------BOUTTON AJOUTER ANULLER Articles
        // --------------------
        HBox Bouttons_Ajouter_Anuller = new HBox();
        Bouttons_Ajouter_Anuller.getStyleClass().add("button-container");
        Button AjouterBoutton = new Button("Ajouter");
        AjouterBoutton.getStyleClass().add("btn-primary");
        Button AnullerBoutton = new Button("Annuler");
        AnullerBoutton.getStyleClass().add("btn-secondary");
        Bouttons_Ajouter_Anuller.getChildren().addAll(AnullerBoutton, AjouterBoutton);
        // -------------------------------------------------
        VBox Article = new VBox();
        Article.getStyleClass().add("form-container");
        Article.getChildren().addAll(TitleArticle, NomArticle,
                ReferenceArticle, Categorie, Quantite, Bouttons_Ajouter_Anuller);

        // =================AFFICHAGE DES
        // ARTICLES============================================
        // -------CONSTRUIRE UN
        // TABLEAU-------------------------------------------------------
        ObservableList<ArticleObjet> observablesArticle = FXCollections.observableArrayList();
        TableView<ArticleObjet> tableArticle = new TableView<>();

        TableColumn<ArticleObjet, String> ColumnNomArticle = new TableColumn<>("Nom");
        ColumnNomArticle.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<ArticleObjet, String> ColumnReferenceArticle = new TableColumn<>("Reference");
        ColumnReferenceArticle.setCellValueFactory(new PropertyValueFactory<>("reference"));

        TableColumn<ArticleObjet, String> ColumnQuantiteArticle = new TableColumn<>("Quantite");
        ColumnQuantiteArticle.setCellValueFactory(new PropertyValueFactory("quantite"));

        TableColumn<ArticleObjet, String> ColumnCategorieArticle = new TableColumn<>("Categorie");
        ColumnCategorieArticle.setCellValueFactory(new PropertyValueFactory("categorie"));

        tableArticle.setItems(observablesArticle);
        tableArticle.getColumns().addAll(ColumnNomArticle, ColumnReferenceArticle, ColumnQuantiteArticle,
                ColumnCategorieArticle);

        // -------------LIRE LES INPUTS ET CREER UN ARTICLE----------------
        AjouterBoutton.setOnAction(e -> {
            ArticleObjet articleobjet = new ArticleObjet(
                    ajouterNomArticle.getText(),
                    ajouterReferenceArticle.getText(),
                    CategorieArticle.getText(),
                    Integer.parseInt(QuantiteArticle.getText()));
            observablesArticle.add(articleobjet);
            Article.getChildren().add(tableArticle);
        });

        // ------------------------------------------------------------------------------------
        // ------------------------------------------------------------------------------------
        // -----------------------NOM
        // FOURNISSEUR-------------------------------------------
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
        // -----------------------------------------------------------------------------------------
        VBox EmailFournisseurContainer = new VBox();
        EmailFournisseurContainer.getStyleClass().add("form-group");
        Label labelEmailFournisseur = new Label("Email Fournisseur");
        labelEmailFournisseur.getStyleClass().add("form-label");
        TextField EmailFournisseurInput = new TextField();
        EmailFournisseurInput.getStyleClass().add("form-textfield");
        EmailFournisseurInput.setPromptText("Entrer email de fournisseur");
        EmailFournisseurContainer.getChildren().addAll(labelEmailFournisseur, EmailFournisseurInput);

        // ---------------DELARER LES BOUTTONS AJOUTER ANNULER FOURNISSEUR------------
        HBox Ajouter_Anller_Bouttons = new HBox();
        Ajouter_Anller_Bouttons.getStyleClass().add("button-container");
        Button AjouterFournisseurButton = new Button("Ajouter");
        AjouterFournisseurButton.getStyleClass().add("btn-primary");
        Button AnuulerFournisseurButton = new Button("Anuller");
        AnuulerFournisseurButton.getStyleClass().add("btn-secondary");
        Ajouter_Anller_Bouttons.getChildren().addAll(AnuulerFournisseurButton, AjouterFournisseurButton);
        // -----------------------------------------------------------------------------
        VBox Fournisseur = new VBox();
        Fournisseur.getStyleClass().add("form-container");
        Fournisseur.getChildren().addAll(FournisseurTitle, NomFournisseurContainer, EmailFournisseurContainer,
                Ajouter_Anller_Bouttons);

        // -----------Lire les input de fournisseur -------------------------------

        ObservableList<FournisseurObjet> observablesFournisseur = FXCollections.observableArrayList();
        TableView<FournisseurObjet> tableFournisseur = new TableView<>();

        TableColumn<FournisseurObjet, String> ColumnNomFournisseur = new TableColumn<>("Nom");
        ColumnNomFournisseur.setCellValueFactory(new PropertyValueFactory("nomFournisseur"));

        TableColumn<FournisseurObjet, String> ColumnEmailFourniseur = new TableColumn<>("Email");
        ColumnEmailFourniseur.setCellValueFactory(new PropertyValueFactory("emailFournisseur"));
        tableFournisseur.setItems(observablesFournisseur);
        tableFournisseur.getColumns().addAll(ColumnNomFournisseur, ColumnEmailFourniseur);
        Fournisseur.getChildren().add(tableFournisseur);

        AjouterFournisseurButton.setOnAction(e -> {
            FournisseurObjet fournisseurobjet = new FournisseurObjet(NomFournisseurInput.getText(),
                    EmailFournisseurInput.getText());
            observablesFournisseur.add(fournisseurobjet);

        });
        // ----------------DECLARATION DES GRANDES PARTIES----------

        Label mainTitle = new Label("Application Gestion de Stock");
        mainTitle.getStyleClass().add("main-title");

        ImageView ArticleIcon = new ImageView(
                new Image("file:src/asserts/deployed_code_24dp_EA3323_FILL0_wght400_GRAD0_opsz24.png"));

        Button toArticle = new Button(" Articles");
        toArticle.getStyleClass().add("sidebar-button");
        toArticle.setGraphic(ArticleIcon);

        // ------------------------------
        Button toFournisseur = new Button("Fournisseur");
        toFournisseur.getStyleClass().add("sidebar-button");
        ImageView FournisseurIcon = new ImageView(
                new Image("file:src/asserts/local_shipping_24dp_EA3323_FILL0_wght400_GRAD0_opsz24.png"));
        toFournisseur.setGraphic(FournisseurIcon);
        // -----------------------
        VBox BarreLateralle = new VBox();
        BarreLateralle.getStyleClass().add("sidebar");
        BarreLateralle.getChildren().addAll(toArticle, toFournisseur);
        // --------------------
        VBox main = new VBox();
        main.getStyleClass().add("content-area");
        toArticle.setOnAction(e -> {
            main.getChildren().setAll(Article);
        });
        toFournisseur.setOnAction(e -> {
            main.getChildren().setAll(Fournisseur);
        });
        // -------------------------------
        HBox root = new HBox();
        root.getStyleClass().add("main-container");
        root.getChildren().addAll(BarreLateralle, main);
        ScrollPane scroll = new ScrollPane();
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