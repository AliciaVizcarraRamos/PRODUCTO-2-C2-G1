package pe.edu.upeu.sysalmacenfx.controlventa;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;
import pe.edu.upeu.sysalmacenfx.modelo.Producto;
import pe.edu.upeu.sysalmacenfx.servicio.ProductoService;


import java.net.URL;
import java.util.ResourceBundle;
@Component
public class Datos_productoController implements Initializable {

    private final ProductoService productDAO = new ProductoService();

    private final ObservableList<Producto.State> stateList = FXCollections.observableArrayList(Producto.State.ACTIVE, Producto.State.DISACTIVE);

    private static ObservableList<Producto> products =null;
    @FXML
    private ComboBox<Producto.State> cbState;
    @FXML
    private TextField name;
    @FXML
    private TextField price;
    @FXML
    private TextField stock;
    @FXML
    private TableView<Producto> tableProducts;
    @FXML
    private TableColumn<Producto,Integer> colId;
    @FXML
    private TableColumn<Producto,String> colName;
    @FXML
    private TableColumn<Producto,Double> colPrice;
    @FXML
    private TableColumn<Producto,Integer> colStock;
    @FXML
    private TableColumn<Producto,Producto.State> colState;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        initializeComboBox();
        initializeProductData();
    }

    private void initializeTable() {
        tableProducts.setOnMouseClicked(mouseEvent -> {
            if (!tableProducts.getSelectionModel().isEmpty() && mouseEvent.getClickCount() == 2) {
                Producto product = tableProducts.getSelectionModel().getSelectedItem();
                setCells(product);
            }
        });

        colId.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getIdProduct()).asObject());
        colName.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getName()));
        colPrice.setCellValueFactory(p -> new SimpleDoubleProperty(p.getValue().getPrice()).asObject());
        colStock.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getStock()).asObject());
        colState.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getState()));

    }

    private void initializeComboBox() {
        cbState.setValue(Producto.State.ACTIVE);
        cbState.setItems(stateList);
    }

    private void initializeProductData() {
        tableProducts.getItems().clear();
        if (products == null) {
            products = FXCollections.observableArrayList();
            productDAO.setTable(products);
        }
        tableProducts.setItems(products);
    }

    public void addProduct(ActionEvent actionEvent) {
        Producto product = new Producto(name.getText(),Double.parseDouble(price.getText()),
                Integer.parseInt(stock.getText()), cbState.getValue());
        if (productDAO.update(product)) {
            GUIMainFX.cleanCells(name,price,stock);
            updateTable();
        }
    }

    public void updateProduct(ActionEvent actionEvent) {
        Producto product = new Producto(name.getText(),Double.parseDouble(price.getText()),
                Integer.parseInt(stock.getText()), cbState.getValue());
        if (productDAO.update(product)) {
            GUIMainFX.cleanCells(name,price,stock);
            updateTable();
        }
    }

    public void deleteProduct(ActionEvent actionEvent) {
        if (productDAO.delete(name.getText())) {
            GUIMainFX.cleanCells(name,price,stock);
            updateTable();
        }
    }

    public void cleanCellsScreen(ActionEvent actionEvent) {
        GUIMainFX.cleanCells(name,price,stock);
    }

    private void setCells(Producto product) {
        name.setText(product.getName()); // Cambiar a getName()
        price.setText(String.valueOf(product.getPrice())); // Cambiar a getPrice()
        stock.setText(String.valueOf(product.getStock())); // Cambiar a getStock()
        cbState.setValue(product.getState()); // Cambiar a getState()
    }


    private void updateTable() {
        tableProducts.getItems().clear();
        productDAO.setTable(products);
        tableProducts.setItems(products);
    }


}
