package pe.edu.upeu.productodosfx.servicio;

import pe.edu.upeu.productodosfx.dto.MenuMenuItenTO;

import java.util.List;
import java.util.Properties;

public interface MenuMenuItenDaoI {
    public List<MenuMenuItenTO> listaAccesos(String perfil, Properties
            idioma);

}
