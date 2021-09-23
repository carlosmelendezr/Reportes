package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PantallaPrincipalCtl implements Initializable {

    @FXML
    private VBox pnItems = null;
    @FXML
    private Button btnOverview;

    @FXML
    private Button btnVentasDivisa;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnImportarProducto;

    @FXML
    private Button btnVentasBolivares;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private Pane pnlCustomer;


    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlMenus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("Item.fxml"));

                //give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #02030A");
                });
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

    }


    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnCustomers) {
            pnlCustomer.setStyle("-fx-background-color : #deeaee");
            pnlCustomer.toFront();
        }
        if (actionEvent.getSource() == btnImportarProducto) {
            pnlMenus.setStyle("-fx-background-color : #deeaee");
            pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
            pnlOverview.setStyle("-fx-background-color : #deeaee");
            pnlOverview.toFront();
        }
        if(actionEvent.getSource()==btnVentasDivisa)
        {
            pnlCustomer.setStyle("-fx-background-color : #deeaee");
            pnlCustomer.toFront();
            Contexto.reporteSeleccionado = Contexto.VENTAS_DOLARES_CONTRIBUYENTE;
        }

        if(actionEvent.getSource()==btnVentasBolivares)
        {
            pnlCustomer.setStyle("-fx-background-color : #deeaee");
            pnlCustomer.toFront();
            Contexto.reporteSeleccionado = Contexto.VENTAS_DOLARES_CONTRIBUYENTE_BS;
        }

        cargarParametros();
    }

    public void cargarParametros() {
        Node nodo=null;
        try {
            nodo = FXMLLoader.load(getClass().getResource("Datos.fxml"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        pnlCustomer.getChildren().add(nodo);


    }
}


