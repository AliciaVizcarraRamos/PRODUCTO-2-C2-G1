package pe.edu.upeu.sysalmacenfx.controlventa;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;
import pe.edu.upeu.sysalmacenfx.modelo.CompCarrito;
import pe.edu.upeu.sysalmacenfx.servicio.VentaService;

import java.net.URL;
import java.util.ResourceBundle;
@Component
public class Detalles_ventasController implements Initializable {
    private static final VentaService salesDAO = new VentaService();
    private static ObservableList<CompCarrito> productsDetails;
    private static int idSale;

    @FXML
    private   TableView<CompCarrito> tableSale;
    @FXML
    private TableColumn<CompCarrito,Integer> colNro;
    @FXML
    private TableColumn<CompCarrito,String> colCod;
    @FXML
    private TableColumn<CompCarrito,String> colProduct;
    @FXML
    private TableColumn<CompCarrito, Integer> colQuantity;
    @FXML
    private TableColumn<CompCarrito, Double> colPrice;
    @FXML
    private TableColumn<CompCarrito,Double> colTotal;
    @FXML
    private TextField totalSale;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureTableColumns();
        clearTableItems();
        loadProductsDetails();
        displayTotal();
        displayProductsDetails();
    }

    private void configureTableColumns() {
        colNro.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getNr()).asObject());
        colCod.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getCod()));
        colProduct.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getProduct()));
        colQuantity.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getQuantity()).asObject());
        colPrice.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getProveedor()).asObject());
        colTotal.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getTotal()).asObject());

    }

    private void clearTableItems() {
        tableSale.getItems().clear();
    }

    private void loadProductsDetails() {
        productsDetails = FXCollections.observableArrayList();
        salesDAO.setTableDetails(productsDetails,
                idSale);
    }

    private void displayTotal() {
        double sumTotal = productsDetails.stream()
                .mapToDouble(CompCarrito::getTotal)
                .sum();
        totalSale.setText(String.format("%.2f", sumTotal));
    }

    private void displayProductsDetails() {
        tableSale.setItems(productsDetails);
    }


    public static void setIdSale(int idSale) {
        Detalles_ventasController.idSale = idSale;
    }


}
