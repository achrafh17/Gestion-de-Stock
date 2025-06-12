import java.util.ArrayList;

import javax.swing.border.LineBorder;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
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
        // ----------------ID FOURNISSEUR ---------------------------
        VBox IDfournisseur = new VBox();
        IDfournisseur.getStyleClass().add("form-group");
        Label labelIDfournisseureArticle = new Label("ID de Fournisseur");
        labelIDfournisseureArticle.getStyleClass().add("form-label");
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
        VBox Article = new VBox();
        Article.getStyleClass().add("form-container");
        Article.getChildren().addAll(TitleArticle, NomArticle,
                ReferenceArticle, Categorie, Quantite, SeuilAlerte, IDfournisseur, Bouttons_Ajouter_Anuller);

        // ==========AFFICHAGE DES ARTICLES========================================
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
        ColumnCategorieArticle.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        ColumnCategorieArticle.getStyleClass().add("table-column");

        TableColumn<ArticleObjet, String> ColumnSeuilArticle = new TableColumn<>("Seuil");
        ColumnSeuilArticle.setCellValueFactory(new PropertyValueFactory<>("seuilAlerte"));
        ColumnSeuilArticle.getStyleClass().add("table-column");

        // -----------------MODIFIER ARTICLE--------------------------------
        TableColumn<ArticleObjet, Void> ModifierArticle = new TableColumn<>("Modifier");
        ModifierArticle.setCellFactory(param -> new TableCell<ArticleObjet, Void>() {
            private final Button Modifier = new Button("Modifier");

            {
                Modifier.getStyleClass().add("modify-btn");
                ImageView iconModifier = new ImageView(
                        new Image("file:src/asserts/edit_24dp_FFFFFF_FILL0_wght400_GRAD0_opsz24.png"));
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
                        // Mettre a jour les attributs de l article avec les nouvelles valeurs
                        article.setNom(NomArticleModifierInput.getText());
                        article.setReference(ReferenceArticleModifierInput.getText());
                        article.setCategorie(CategorieArticleModifierInput.getText());
                        article.setQuantite(Integer.parseInt(QuantiteArticleModifierInput.getText()));
                        article.setSeuilAlerte(Integer.parseInt(SeuilArticleModifierInput.getText()));
                        article.setFournisseurid(FournisseurIDArticleModifierInput.getText());
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
                    setGraphic(Modifier);
                }
            }
        });

        tableArticle.setItems(observablesArticle);
        tableArticle.getColumns().addAll(ColumnNomArticle, ColumnReferenceArticle, ColumnIDFOURNISSEURArticle,
                ModifiedArticle, ColumnCategorieArticle, ColumnSeuilArticle, DeleteArticle, ModifierArticle);

        // -------------LIRE LES INPUTS ET CREER UN ARTICLE----------------
        AjouterBoutton.setOnAction(e -> {
            ArticleObjet articleobjet = new ArticleObjet(
                    ajouterNomArticle.getText(),
                    ajouterReferenceArticle.getText(),
                    CategorieArticle.getText(),
                    Integer.parseInt(QuantiteArticle.getText()), Integer.parseInt(SeuilAlertinput.getText()),
                    FournisseurIDArticle.getText());
            observablesArticle.add(articleobjet);
            Article.getChildren().add(tableArticle);
            ajouterNomArticle.setText("");
            ajouterReferenceArticle.setText("");
            CategorieArticle.setText("");
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

        // ---------------EMAIL FOURNISSEUR--------------------------------
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
        VBox Fournisseur = new VBox();
        Fournisseur.getStyleClass().add("form-container");
        Fournisseur.getChildren().addAll(FournisseurTitle, IDFournisseurContainer, NomFournisseurContainer,
                EmailFournisseurContainer,
                Ajouter_Anller_Bouttons);

        // -----------Lire les input de fournisseur -------------------------------
        ObservableList<FournisseurObjet> observablesFournisseur = FXCollections.observableArrayList();
        TableView<FournisseurObjet> tableFournisseur = new TableView<>();
        tableFournisseur.getStyleClass().add("table-view");

        TableColumn<FournisseurObjet, String> ColumnIDfournisseur = new TableColumn<>("ID");
        ColumnIDfournisseur.setCellValueFactory(new PropertyValueFactory("id"));

        TableColumn<FournisseurObjet, String> ColumnNomFournisseur = new TableColumn<>("Nom");
        ColumnNomFournisseur.setCellValueFactory(new PropertyValueFactory("nomFournisseur"));
        ColumnNomFournisseur.getStyleClass().add("table-column");

        TableColumn<FournisseurObjet, String> ColumnEmailFourniseur = new TableColumn<>("Email");
        ColumnEmailFourniseur.setCellValueFactory(new PropertyValueFactory("emailFournisseur"));
        ColumnEmailFourniseur.getStyleClass().add("table-column");

        // ========================supprimer un fournisseur=======================

        TableColumn<FournisseurObjet, Void> DeleteFournisseur = new TableColumn<>("Actions");
        DeleteFournisseur.getStyleClass().add("table-column");
        DeleteFournisseur.setCellFactory(params -> new TableCell<FournisseurObjet, Void>() {
            Image deleteIcon = new Image(
                    "file:src/asserts/delete_24dp_EA3323_FILL0_wght400_GRAD0_opsz24.png");
            ImageView imageview = new ImageView(deleteIcon);
            final Button deleteButton = new Button("", imageview);

            {
                deleteButton.getStyleClass().add("delete-btn");
                imageview.getStyleClass().add("delete-icon");

                deleteButton.setOnAction(e -> {
                    FournisseurObjet fournisseur = getTableView().getItems().get(getIndex());
                    getTableView().getItems().remove(fournisseur);
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
        // =============================MODIFIER UN FOURNISSEUR======================
        TableColumn<FournisseurObjet, Void> ModifierFournisseur = new TableColumn<>("Modifier");
        ModifierFournisseur.setCellFactory(param -> new TableCell<FournisseurObjet, Void>() {
            private final Button Modifier = new Button("Modifier");

            {
                Modifier.getStyleClass().add("modify-btn");
                ImageView iconModifier = new ImageView(
                        new Image("file:src/asserts/edit_24dp_FFFFFF_FILL0_wght400_GRAD0_opsz24.png"));
                Modifier.setGraphic(iconModifier);

                Modifier.setOnAction(e -> {
                    FournisseurObjet fournisseur = getTableView().getItems().get(getIndex());
                    VBox windowRootFournisseur = new VBox();
                    windowRootFournisseur.getStyleClass().addAll("modification-window", "form-container");
                    // les input sont deja remplis avec les anciens valeurs de l article
                    // Nom Fournisseur
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
                    IdFournisseurModifier.getChildren().addAll(IdFournisseurModifierLabel, IdFournisseurModifierInput);

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
                    setGraphic(Modifier);
                }
            }
        });

        tableFournisseur.setItems(observablesFournisseur);
        tableFournisseur.getColumns().addAll(ColumnIDfournisseur, ColumnNomFournisseur, ColumnEmailFourniseur,
                DeleteFournisseur, ModifierFournisseur);
        Fournisseur.getChildren().add(tableFournisseur);

        AjouterFournisseurButton.setOnAction(e -> {
            FournisseurObjet fournisseurobjet = new FournisseurObjet(IDFournisseurInput.getText(),
                    NomFournisseurInput.getText(),
                    EmailFournisseurInput.getText());
            observablesFournisseur.add(fournisseurobjet);
        });
        // ------------------TABLE DE BOARD SECTION-----------------
        // the main page has to be on the board section
        // ajouter le prix de chaque article
        VBox TableDeBoardMain = new VBox();
        TableDeBoardMain.getStyleClass().add("dashboard-main");

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

        Button TableDeBoard = new Button("Table de board");
        TableDeBoard.getStyleClass().add("sidebar-button");

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

        TableDeBoardMain.getChildren().addAll(FinalTotalArticles, FinalTotalQuantite);
        // ----------------DECLARATION DES GRANDES PARTIES----------
        Label mainTitle = new Label("Application Gestion de Stock");
        mainTitle.getStyleClass().add("main-title");

        // TO ARTICLE-----------------------------
        ImageView ArticleIcon = new ImageView(
                new Image("file:src/asserts/deployed_code_24dp_EA3323_FILL0_wght400_GRAD0_opsz24.png"));
        ArticleIcon.getStyleClass().add("sidebar-icon");

        Button toArticle = new Button(" Articles");
        toArticle.getStyleClass().add("sidebar-button");
        toArticle.setGraphic(ArticleIcon);

        // TO FOURNISSEUR ---------------------------
        Button toFournisseur = new Button("Fournisseur");
        toFournisseur.getStyleClass().add("sidebar-button");
        ImageView FournisseurIcon = new ImageView(
                new Image("file:src/asserts/local_shipping_24dp_EA3323_FILL0_wght400_GRAD0_opsz24.png"));
        FournisseurIcon.getStyleClass().add("sidebar-icon");
        toFournisseur.setGraphic(FournisseurIcon);
        // ---GESTION DE MOVEMENT ----------------------
        Button GestionDeMovemenet = new Button("Gestion de Movement");
        GestionDeMovemenet.getStyleClass().add("sidebar-button");

        // -----------------------
        VBox BarreLateralle = new VBox();
        BarreLateralle.getStyleClass().add("sidebar");
        BarreLateralle.getChildren().addAll(TableDeBoard, toArticle, toFournisseur, GestionDeMovemenet);

        // --------------------
        VBox main = new VBox();
        main.getStyleClass().add("content-area");

        TableDeBoard.setOnAction(param -> {
            int count = 0;
            for (ArticleObjet article : observablesArticle) {
                count += article.getQuantite();
            }
            TOtalArticlesValue.setText(String.valueOf(count));
            QuantiteTotalValue.setText(String.valueOf(count));
            main.getChildren().clear();
            main.getChildren().setAll(TableDeBoardMain);

            TableDeBoard.getStyleClass().add("active");
            toFournisseur.getStyleClass().remove("active");
            toArticle.getStyleClass().remove("active");

        });

        toArticle.setOnAction(e -> {
            main.getChildren().setAll(Article);
            toArticle.getStyleClass().add("active");
            toFournisseur.getStyleClass().remove("active");
            TableDeBoard.getStyleClass().remove("active");
        });

        toFournisseur.setOnAction(e -> {
            main.getChildren().setAll(Fournisseur);
            toFournisseur.getStyleClass().add("active");
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