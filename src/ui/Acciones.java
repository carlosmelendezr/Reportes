package ui;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelos.datos.Constantes;
import net.sf.jasperreports.engine.*;
import org.springframework.util.ResourceUtils;
import sample.Connect;
import sample.JasperViewerFX;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Acciones {


    public static void ventanaPrincipal() {

        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(
                    Controller.class.getResource("pantallaPrincipal.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("PosWeb");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            System.out.println("Error:"+e.getMessage());
            e.printStackTrace();
        }

    }




    public static boolean dialogoConfirmar(String titulo, String texto) {
        boolean respuesta = false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(titulo);
        alert.setContentText(texto);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            respuesta = true;
        }
        return respuesta;
    }

    public static void dialogoAlerta(String titulo, String texto) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alerta");
        alert.setHeaderText(titulo);
        alert.setContentText(texto);

        alert.showAndWait();

    }

    public static void prepararReporte(String desde, String hasta ) {
        if (!Contexto.reporteSeleccionado.isEmpty()) {
            Map<String, Object> map = new HashMap<>();
            map.put("FechaDesde", desde);
            map.put("FechaHasta", hasta);
            cargarReporte(Contexto.reporteSeleccionado, map);
        }
    }



        public static void cargarReporte(String nombre,Map param){

            try {
                Connection con = Connect.connect("posweb.db");
                JasperReport jreport=null;
                try {
                    jreport = getJasperReport(nombre);
                } catch (Exception e) {
                    e.printStackTrace();

                }
                if (jreport!=null) {
                    JasperPrint jprint = JasperFillManager.fillReport(jreport, param, con);
                    new JasperViewerFX().viewReport("PosWeb - Reportes", jprint);
                } else {
                    System.out.println("No existe el reporte");
                }
                con.close();
            } catch (JRException | SQLException e) {
                e.printStackTrace();
            }

        }

        private static JasperReport getJasperReport(String nombre) throws FileNotFoundException, JRException {
            String fullName = Constantes.dirRep+nombre;
            File template = ResourceUtils.getFile(fullName);
            return JasperCompileManager.compileReport(template.getAbsolutePath());
        }





}
