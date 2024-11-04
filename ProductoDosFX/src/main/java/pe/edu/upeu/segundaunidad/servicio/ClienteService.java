package pe.edu.upeu.segundaunidad.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.segundaunidad.dto.ComboBoxOption;
import pe.edu.upeu.segundaunidad.modelo.Cliente;
import pe.edu.upeu.segundaunidad.repositorio.ClienteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repo;

    public Cliente save(Cliente to) {
        return repo.save(to);


    }

    public List<Cliente> List() {
        return repo.findAll();

    }
    //U
    public Cliente update(Cliente to, Long id) {
        try {
            Cliente toe = repo.findById(id).get();
            toe.setNombres(to.getNombres());
            if (toe!=null){
                toe.setNombres(to.getNombres());

            }
            return repo.save(toe);
        }catch (Exception e){
            System.out.println("Error: "+ e.getMessage());

        }
        return null;


    }
    public boolean update(Cliente to){
        return  repo.save(to);
    }
    //D
    public boolean delete(Long id){
        repo.deleteById(id);
        return (false);
    }
    public  Cliente buscarId(Long id){
        return  repo.findById(id).get();
    }
    public boolean listarCombobox(){
        List<ComboBoxOption> listar =new ArrayList<>();
        for (Cliente cate : repo.findAll()) {
            listar.add(new ComboBoxOption(String
                    .valueOf(cate.getDniruc()),
                    cate.getDniruc()
            ));

        }
        return listar
                ;

    }

}

