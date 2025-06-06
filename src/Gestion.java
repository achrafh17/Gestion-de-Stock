import java.util.ArrayList;

import javax.swing.border.LineBorder;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
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
                ReferenceArticle, Categorie, Quantite, SeuilAlerte, Bouttons_Ajouter_Anuller);

        // =================AFFICHAGE DES
        // ARTICLES============================================
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

        TableColumn<ArticleObjet, Void> ModifiedArticle = new TableColumn<>("Quantite");
        ModifiedArticle.getStyleClass().add("table-column");

        ModifiedArticle.setCellFactory(param -> new TableCell<ArticleObjet, Void>() {

            private final HBox buttonsModifiedQuantity = new HBox();
            private final Button IncrementQuantity = new Button("+");
            private final Button DiscremenetQuantity = new Button("-");
            private final Label QuantityVariable = new Label();

            {
                buttonsModifiedQuantity.getStyleClass().add("quantity-controls");
                IncrementQuantity.getStyleClass().add("quantity-btn");
                IncrementQuantity.getStyleClass().add("increment-btn");
                DiscremenetQuantity.getStyleClass().add("quantity-btn");
                DiscremenetQuantity.getStyleClass().add("decrement-btn");
                QuantityVariable.getStyleClass().add("quantity-label");
                
                buttonsModifiedQuantity.getChildren().addAll(DiscremenetQuantity, QuantityVariable, IncrementQuantity);

                IncrementQuantity.setOnAction(e -> {
                    ArticleObjet article = getTableView().getItems().get(getIndex());
                    article.setQuantite(article.getQuantite() + 1);
                    QuantityVariable.setText(String.valueOf(article.getQuantite()));
                    tableArticle.refresh();
                });
                DiscremenetQuantity.setOnAction(e -> {
                    ArticleObjet article = getTableView().getItems().get(getIndex());
                    article.setQuantite(Math.max(0, article.getQuantite() - 1));
                    QuantityVariable.setText(String.valueOf(article.getQuantite()));
                    if (article.getQuantite() <= article.getSeuilAlerte()) {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Stock épuisé");
                        alert.setHeaderText("Attention : Stock faible");
                        alert.setContentText("La quantité de l'article \"" + article.getNom()
                                + "\" a atteint ou est inférieure au seuil d'alerte (" + article.getSeuilAlerte()
                                + "). Veuillez réapprovisionner le stock.");
                        alert.getDialogPane().getStyleClass().add("alert");
                        alert.getDialogPane().getStyleClass().add("warning-alert");
                        alert.showAndWait();
                        return;
                    }
                    tableArticle.refresh();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    ArticleObjet article = getTableView().getItems().get(getIndex());
                    QuantityVariable.setText(String.valueOf(article.getQuantite()));
                    setGraphic(buttonsModifiedQuantity);
                }
            }
        });
        
        TableColumn<ArticleObjet, Void> DeleteArticle = new TableColumn<>("Actions");
        DeleteArticle.getStyleClass().add("table-column");
        DeleteArticle.setCellFactory(params -> new TableCell<ArticleObjet, Void>() {
            Image deleteIcon = new Image(
                    "file:src/asserts/delete_24dp_EA3323_FILL0_wght400_GRAD0_opsz24.png");
            ImageView imageview = new ImageView(deleteIcon);
            final Button deleteButton = new Button("", imageview);

            {
                deleteButton.getStyleClass().add("delete-btn");
                imageview.getStyleClass().add("delete-icon");
                
                deleteButton.setOnAction(e -> {
                    ArticleObjet article = getTableView().getItems().get(getIndex());
                    getTableView().getItems().remove(article);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        TableColumn<ArticleObjet, String> ColumnCategorieArticle = new TableColumn<>("Categorie");
        ColumnCategorieArticle.setCellValueFactory(new PropertyValueFactory("categorie"));
        ColumnCategorieArticle.getStyleClass().add("table-column");

        TableColumn<ArticleObjet, String> ColumnSeuilArticle = new TableColumn<>("Seuil");
        ColumnSeuilArticle.setCellValueFactory(new PropertyValueFactory("seuilAlerte"));
        ColumnSeuilArticle.getStyleClass().add("table-column");

        tableArticle.setItems(observablesArticle);
        tableArticle.getColumns().addAll(ColumnNomArticle, ColumnReferenceArticle,
                ModifiedArticle,
                ColumnCategorieArticle, ColumnSeuilArticle, DeleteArticle);

        // -------------LIRE LES INPUTS ET CREER UN ARTICLE----------------
        AjouterBoutton.setOnAction(e -> {
            ArticleObjet articleobjet = new ArticleObjet(
                    ajouterNomArticle.getText(),
                    ajouterReferenceArticle.getText(),
                    CategorieArticle.getText(),
                    Integer.parseInt(QuantiteArticle.getText()), Integer.parseInt(SeuilAlertinput.getText()));
            observablesArticle.add(articleobjet);
            Article.getChildren().add(tableArticle);
            ajouterNomArticle.setText("");
            ajouterReferenceArticle.setText("");
            CategorieArticle.setText("");
        });

        // ------------------------------------------------------------------------------------
        // ------------------------------------------------------------------------------------
        // -------------NOM FOURNISSEUR-------------------------------------------
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
        tableFournisseur.getStyleClass().add("table-view");

        TableColumn<FournisseurObjet, String> ColumnNomFournisseur = new TableColumn<>("Nom");
        ColumnNomFournisseur.setCellValueFactory(new PropertyValueFactory("nomFournisseur"));
        ColumnNomFournisseur.getStyleClass().add("table-column");

        TableColumn<FournisseurObjet, String> ColumnEmailFourniseur = new TableColumn<>("Email");
        ColumnEmailFourniseur.setCellValueFactory(new PropertyValueFactory("emailFournisseur"));
        ColumnEmailFourniseur.getStyleClass().add("table-column");
        
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
        ArticleIcon.getStyleClass().add("sidebar-icon");

        Button toArticle = new Button(" Articles");
        toArticle.getStyleClass().add("sidebar-button");
        toArticle.setGraphic(ArticleIcon);

        // ------------------------------
        Button toFournisseur = new Button("Fournisseur");
        toFournisseur.getStyleClass().add("sidebar-button");
        ImageView FournisseurIcon = new ImageView(
                new Image("file:src/asserts/local_shipping_24dp_EA3323_FILL0_wght400_GRAD0_opsz24.png"));
        FournisseurIcon.getStyleClass().add("sidebar-icon");
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
            toArticle.getStyleClass().add("active");
            toFournisseur.getStyleClass().remove("active");
        });
        
        toFournisseur.setOnAction(e -> {
            main.getChildren().setAll(Fournisseur);
            toFournisseur.getStyleClass().add("active");
            toArticle.getStyleClass().remove("active");
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