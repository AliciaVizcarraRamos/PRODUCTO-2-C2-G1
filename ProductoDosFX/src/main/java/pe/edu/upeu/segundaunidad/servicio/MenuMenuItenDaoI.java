package pe.edu.upeu.segundaunidad.servicio;

import pe.edu.upeu.segundaunidad.dto.MenuMenuItenTO;

import java.util.List;
import java.util.Properties;

public interface MenuMenuItenDaoI {
    public List<MenuMenuItenTO> listaAccesos(String perfil, Properties
            idioma);

}
