package pe.edu.upeu.productodosfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.productodosfx.dto.ComboBoxOption;
import pe.edu.upeu.productodosfx.modelo.Emisor;
import pe.edu.upeu.productodosfx.repositorio.EmisorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmisorService {

    @Autowired
    EmisorRepository repo;

    public Emisor save(Emisor to) {
        return repo.save(to);


    }

    public List<Emisor> List() {
        return repo.findAll();

    }
    //U
    public Emisor update(Emisor to, Long id) {
        try {
            Emisor toe = repo.findById(id).get();
            toe.setIdEmisor(to.getIdEmisor());
            if (toe!=null){
                toe.setIdEmisor(to.getIdEmisor());

            }
            return repo.save(toe);
        }catch (Exception e){
            System.out.println("Error: "+ e.getMessage());

        }
        return null;


    }
    public Emisor update(Emisor to){
        return  repo.save(to);
    }
    //D
    public void delete(Long id){
        repo.deleteById(id);
    }
    public  Emisor buscarId(Long id){
        return  repo.findById(id).get();
    }
    public List<ComboBoxOption> listarCombobox(){
        List<ComboBoxOption> listar =new ArrayList<>();
        for (Emisor cate : repo.findAll()) {
            listar.add(new ComboBoxOption(String
                    .valueOf(cate.getIdEmisor()),
                    cate.getRuc()
            ));

        }
        return listar
                ;

    }
}
