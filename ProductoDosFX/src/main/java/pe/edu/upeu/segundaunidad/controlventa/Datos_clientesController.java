package pe.edu.upeu.segundaunidad.controlventa;

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
import pe.edu.upeu.segundaunidad.modeloventa.Cliente;
import pe.edu.upeu.segundaunidad.servicio.ClienteService;

import java.net.URL;
import java.util.ResourceBundle;
@Component
public class Datos_clientesController implements Initializable {

    private final ClienteService customerDAO = new ClienteService();
    private final ObservableList<Cliente.State> stateList = FXCollections.observableArrayList(Cliente.State.ACTIVE, Cliente.State.DISACTIVE);
    private static ObservableList<Cliente> customers=null;

    @FXML
    private TextField dni;
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private ComboBox<Cliente.State> cbState;
    @FXML
    private TableView<Cliente> tableCustomers;
    @FXML
    private TableColumn<Cliente,Integer> colId;
    @FXML
    private TableColumn<Cliente,String> colDni;
    @FXML
    private TableColumn<Cliente,String> colName;
    @FXML
    private TableColumn<Cliente,String> colAddress;
    @FXML
    private TableColumn<Cliente,Cliente.State> colState;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        initializeComboBox();
        initializeCustomerData();
    }
    private void initializeCustomerData() {
        tableCustomers.getItems().clear();
        if (customers == null) {
            customers = FXCollections.observableArrayList();
            customerDAO.save((pe.edu.upeu.segundaunidad.modelo.Cliente) customers);
            tableCustomers.setItems(customers);
        } else {
            tableCustomers.setItems(customers);
        }
    }
    private void initializeTable() {
        tableCustomers.setOnMouseClicked(mouseEvent -> {
            if (!tableCustomers.getSelectionModel().isEmpty() && mouseEvent.getClickCount() == 2) {
                pe.edu.upeu.segundaunidad.modelo.Cliente customer = tableCustomers.getSelectionModel().getSelectedItem();
                setCells((Cliente) customer);
            }
        });

        colId.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getIdCustomer()).asObject());
        colName.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getName()));
        colDni.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getDni()));
        colAddress.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getAddress()));
        colState.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getState()));
    }
    private void initializeComboBox() {
        cbState.setValue(Cliente.State.ACTIVE);
        cbState.setItems(stateList);
    }
    @FXML
    public void addCustomer(ActionEvent actionEvent) {
        Cliente customer = new Cliente(dni.getText(),name.getText(),address.getText(),cbState.getValue());
        if (customerDAO.listarCombobox()) {
            GUIMainFX.cleanCells(dni, name, address);
            updateTable();
        }
    }
    @FXML
    public void updateCustomer(ActionEvent actionEvent) {
        pe.edu.upeu.segundaunidad.modelo.Cliente customer = new Cliente(dni.getText(),name.getText(),address.getText(),cbState.getValue());
        if (customerDAO.update(customer)) {
            GUIMainFX.cleanCells(dni, name, address);
            updateTable();
        }
    }
    @FXML
    public void deleteCustomer(ActionEvent actionEvent) {
        if (customerDAO.delete(Long.valueOf(dni.getText()))){
            GUIMainFX.cleanCells(dni,name,address);
            updateTable();
        }
    }
    @FXML
    public void cleanCellsScreen(ActionEvent actionEvent) {
        GUIMainFX.cleanCells(dni,name,address);
    }
    private void setCells(Cliente customer){
        name.setText(customer.getName());
        dni.setText(customer.getDni());
        address.setText(customer.getAddress());
        cbState.setValue(customer.getState());
    }

    private void updateTable() {
        tableCustomers.getItems().clear();
        customerDAO.save((pe.edu.upeu.segundaunidad.modelo.Cliente) customers);
        tableCustomers.setItems(tableCustomers.getItems());
    }

}
