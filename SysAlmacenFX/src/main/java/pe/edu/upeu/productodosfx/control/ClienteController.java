package pe.edu.upeu.productodosfx.control;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upeu.productodosfx.dto.ComboBoxOption;
import pe.edu.upeu.productodosfx.modelo.Cliente;
import pe.edu.upeu.productodosfx.servicio.ClienteService;

import java.util.List;

@Component
public class ClienteController {

    @FXML
    private TextField txtDniRuc; // Campo para DNI o RUC
    @FXML
    private TextField txtNombres; // Campo para nombres del cliente
    @FXML
    private TextField txtRepLegal; // Campo para representante legal
    @FXML
    private ComboBox<ComboBoxOption> cbxTipoDocumento; // ComboBox para seleccionar tipo de documento
    @FXML
    private TableView<Cliente> tableView; // Tabla para mostrar la lista de clientes
    @FXML
    private Label lblMensaje; // Label para mostrar mensajes al usuario
    @FXML
    private AnchorPane contenedor; // Contenedor principal

    @Autowired
    private ClienteService clienteService; // Servicio para manejar la lógica de cliente

    private Cliente clienteSeleccionado; // Cliente actualmente seleccionado

    @FXML
    public void initialize() {
        cargarClientes(); // Carga la lista de clientes al iniciar
        cbxTipoDocumento.getItems().addAll(clienteService.listarCombobox()); // Rellena el ComboBox
    }

    public void cargarClientes() {
        Object id;
        List<Cliente> clientes = (List<Cliente>) clienteService.buscarId(); // Obtiene la lista de clientes
        tableView.getItems().clear(); // Limpia la tabla
        tableView.getItems().addAll(clientes); // Añade la lista de clientes a la tabla
    }

    @FXML
    public void guardarCliente() {
        Cliente cliente = new Cliente(); // Crea un nuevo cliente
        cliente.setDniruc(txtDniRuc.getText()); // Establece el DNI o RUC
        cliente.setNombres(txtNombres.getText()); // Establece los nombres
        cliente.setRepLegal(txtRepLegal.getText()); // Establece el representante legal
        cliente.setTipoDocumento(cbxTipoDocumento.getSelectionModel().getSelectedItem().getKey()); // Establece el tipo de documento

        clienteService.save(cliente); // Guarda el cliente
        cargarClientes(); // Recarga la lista de clientes
        clearForm(); // Limpia el formulario
        lblMensaje.setText("Cliente guardado exitosamente"); // Muestra mensaje de éxito
    }

    @FXML
    public void seleccionarCliente() {
        clienteSeleccionado = tableView.getSelectionModel().getSelectedItem(); // Obtiene el cliente seleccionado
        if (clienteSeleccionado != null) {
            txtDniRuc.setText(clienteSeleccionado.getDniruc()); // Carga el DNI o RUC
            txtNombres.setText(clienteSeleccionado.getNombres()); // Carga los nombres
            txtRepLegal.setText(clienteSeleccionado.getRepLegal()); // Carga el representante legal
            // Establece el tipo de documento seleccionado
            cbxTipoDocumento.getSelectionModel().select(
                    cbxTipoDocumento.getItems().stream()
                            .filter(option -> option.getKey().equals(clienteSeleccionado.getTipoDocumento()))
                            .findFirst()
                            .orElse(null)
            );
        }
    }

    @FXML
    public void actualizarCliente() {
        if (clienteSeleccionado != null) {
            clienteSeleccionado.setNombres(txtNombres.getText()); // Actualiza los nombres
            clienteSeleccionado.setRepLegal(txtRepLegal.getText()); // Actualiza el representante legal
            clienteSeleccionado.setTipoDocumento(cbxTipoDocumento.getSelectionModel().getSelectedItem().getKey()); // Actualiza el tipo de documento

            clienteService.update(clienteSeleccionado); // Actualiza el cliente en el servicio
            cargarClientes(); // Recarga la lista de clientes
            clearForm(); // Limpia el formulario
            lblMensaje.setText("Cliente actualizado exitosamente"); // Muestra mensaje de éxito
        }
    }

    @FXML
    public void eliminarCliente() {
        if (clienteSeleccionado != null) {
            clienteService.delete(Long.valueOf(clienteSeleccionado.getDniruc())); // Elimina el cliente usando su DNI o RUC
            cargarClientes(); // Recarga la lista de clientes
            clearForm(); // Limpia el formulario
            lblMensaje.setText("Cliente eliminado exitosamente"); // Muestra mensaje de éxito
        }
    }

    public void clearForm() {
        txtDniRuc.clear(); // Limpia el campo DNI o RUC
        txtNombres.clear(); // Limpia el campo nombres
        txtRepLegal.clear(); // Limpia el campo representante legal
        cbxTipoDocumento.getSelectionModel().clearSelection(); // Limpia la selección del ComboBox
        clienteSeleccionado = null; // Resetea la selección
    }
}
