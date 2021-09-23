package ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

import static modelos.datos.Util.calendarToSql;

public class Datos implements Initializable {

    @FXML
    DatePicker desde;

    @FXML
    DatePicker hasta;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnEmitir() {
        String fecdes = obtenerFecha(desde);
        String fechas = obtenerFecha(hasta);

        Acciones.prepararReporte(fecdes,fechas);

    }



    public String obtenerFecha(DatePicker fec) {

        LocalDate ld = fec.getValue();
        Calendar c =  Calendar.getInstance();
        c.set(ld.getYear(), ld.getMonthValue()-1, ld.getDayOfMonth());
        return calendarToSql(c);

    }



}
