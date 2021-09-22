package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MainSample extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{


        /*Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();*/

        cargarReporte(primaryStage);


    }

    public void cargarReporte(Stage primaryStage){

        try {
            Connection con = Connect.connect("posweb.db");
            JasperReport jreport=null;

            Map<String, Object> map = new HashMap<>();
            map.put("FechaDesde", "2021-10-01");
            map.put("FechaHasta", "2021-10-30");
            try {
                jreport = getJasperReport();
            } catch (Exception e) {
                e.printStackTrace();

            }
            if (jreport!=null) {
                JasperPrint jprint = JasperFillManager.fillReport(jreport, map, con);
                new JasperViewerFX().viewReport("Simple report", jprint);
            } else {
                System.out.println("No existe el reporte");
            }
            con.close();
        } catch (JRException | SQLException e) {
            e.printStackTrace();
        }

    }

    private static JasperReport getJasperReport() throws FileNotFoundException, JRException {
        File template = ResourceUtils.getFile("c:/posweb/rep/Ventas_Dolares_Contribuyente.jrxml");
        return JasperCompileManager.compileReport(template.getAbsolutePath());
    }


    public static void main(String[] args) {


        launch(args);
    }
}
