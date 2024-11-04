package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.modelo.Usuario;
import pe.edu.upeu.sysalmacenfx.repositorio.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    static UsuarioRepository repo;


    public Usuario save(Usuario to) {
        return repo.save(to);


    }

    public List<Usuario> List() {
        return repo.findAll();

    }
    //U
    public Usuario update(Usuario to, Long id) {
        try {
            Usuario toe = repo.findById(id).get();
            toe.setIdUsuario(to.getIdUsuario());
            if (toe!=null){
                toe.setIdUsuario(to.getIdUsuario());

            }
            return repo.save(toe);
        }catch (Exception e){
            System.out.println("Error: "+ e.getMessage());

        }
        return null;


    }
    public static Usuario loginUsuario(String user, String clave) {
        return repo.loginUsuario(user, clave);
    }

}

