package pe.edu.upeu.sysalmacenfx.controlventa;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;
import pe.edu.upeu.sysalmacenfx.modelo.Cliente;
import pe.edu.upeu.sysalmacenfx.modelo.Proveedor;
import pe.edu.upeu.sysalmacenfx.servicio.ProveedorService;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
@Component
public class Datos_vendedorController implements Initializable {
    public ComboBox<Proveedor.State> getCbState() {
        return cbState;
    }

    private final ProveedorService sellerDAO = new ProveedorService();
    private final ObservableList<Proveedor.State> stateList = FXCollections.observableArrayList(Proveedor.State.ACTIVE, Vendedor.State.DISACTIVE);
    private static ObservableList<Proveedor> sellers = null;

    @FXML
    private TextField dni;
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private TextField user;
    @FXML
    private ComboBox<Proveedor.State> cbState;
    @FXML
    private TableView<Proveedor> tableSellers;
    @FXML
    private TableColumn<Proveedor,Integer> colId;
    @FXML
    private TableColumn<Proveedor,String> colDni;
    @FXML
    private TableColumn<Proveedor,String> colName;
    @FXML
    private TableColumn<Proveedor,String> colPhone;
    @FXML
    private TableColumn<Proveedor, Proveedor.State> colState;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        initializeComboBox();
        initializeSellerData();
    }

    private void initializeTable() {
        tableSellers.setOnMouseClicked(mouseEvent -> {
            if (!tableSellers.getSelectionModel().isEmpty() && mouseEvent.getClickCount() == 2) {
                Proveedor seller;
                seller = tableSellers.getSelectionModel().getSelectedItem();
                setCells(seller);
            }
        });

        colId.setCellValueFactory(p -> new SimpleIntegerProperty(Math.toIntExact(p.getValue().getIdProveedor())).asObject());
        colDni.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getDniRuc()));
        colName.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getNombresRaso()));
        colPhone.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getDireccion()));
        colState.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().get()));

    }

    private void initializeComboBox() {
        cbState.setValue(Cliente.State.ACTIVE);
        cbState.setItems(stateList);
    }

    private void initializeSellerData() {
        tableSellers.getItems().clear();
        if (sellers == null) {
            sellers = FXCollections.observableArrayList();
            sellerDAO.setTable(sellers);
        }
        tableSellers.setItems(sellers);
    }


    public void addSeller(ActionEvent actionEvent){
        Vendedor seller = new Vendedor(dni.getText(),name.getText(),phone.getText(), cbState.getValue(),user.getText());
        if (sellerDAO.create(seller)) {
            GUIMainFX.cleanCells(dni, name, phone, user);
            updateTable();
        }
    }
    public void updateSeller(ActionEvent actionEvent) {
        Proveedor seller = new Proveedor(dni.getText(),name.getText(),phone.getText(),(Proveedor.State) cbState.getValue(),user.getText());
        if (sellerDAO.update(seller)) {
            GUIMainFX.cleanCells(dni, name, phone, user);
            updateTable();
        } else {
            return;
        }
    }

    public void deleteSeller(ActionEvent actionEvent) {
        if (Objects.equals(dni.getText(), MainController.sellerLog.getIdUsuario().doubleValue())){
            GUIMainFX.setAlert(Alert.AlertType.ERROR,"Cannot delete the current seller");
            return;
        }
        if (sellerDAO.delete(Long.valueOf(dni.getText()))){
            GUIMainFX.cleanCells(dni,name,phone,user);
            updateTable();
        }
    }

    public void cleanCellsScreen(ActionEvent actionEvent) {
        GUIMainFX.cleanCells(dni,name,phone,user);
    }

    private void setCells(Proveedor seller){
        dni.setText(seller.getDniRuc());
        name.setText(seller.getNombresRaso());
        phone.setText(seller.getTipoDoc());
        user.setText(seller.getDireccion());
    }

    private void updateTable(){
        tableSellers.getItems().clear();
        sellerDAO.save((Proveedor) sellers);
        tableSellers.setItems(sellers);
    }


}
