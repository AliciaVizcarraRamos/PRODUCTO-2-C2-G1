package pe.edu.upeu.productodosfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.productodosfx.dto.ComboBoxOption;
import pe.edu.upeu.productodosfx.modelo.Cliente;
import pe.edu.upeu.productodosfx.repositorio.ClienteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repo;
    private Long id;

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
    public Cliente update(Cliente to){
        return  repo.save(to);
    }
    //D
    public void delete(Long id){
        repo.deleteById(id);
    }
    public  Cliente buscarId(){
        return  repo.findById(id).get();
    }
    public List<ComboBoxOption> listarCombobox(){
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

