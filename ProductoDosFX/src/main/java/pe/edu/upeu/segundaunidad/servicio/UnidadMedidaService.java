package pe.edu.upeu.segundaunidad.servicio;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.segundaunidad.dto.ComboBoxOption;
import pe.edu.upeu.segundaunidad.modelo.UnidadMedida;
import pe.edu.upeu.segundaunidad.repositorio.UnidadMedidaRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnidadMedidaService {

    @Autowired
    UnidadMedidaRepository  repo;
    //-no :CategoriaRepository repo=new CategoriaRepository()

    public UnidadMedida save(UnidadMedida to) {
        return repo.save(to);


    }

    public List<UnidadMedida> List() {
        return repo.findAll();

    }
    //U
    public UnidadMedida update(UnidadMedida to, Long id) {
        try {
            UnidadMedida toe = repo.findById(id).get();
            toe.setNombreMedida(to.getNombreMedida());
            if (toe!=null){
                toe.setNombreMedida(to.getNombreMedida());

            }
            return repo.save(toe);
        }catch (Exception e){
            System.out.println("Error: "+ e.getMessage());

        }
        return null;


    }
    public UnidadMedida update(UnidadMedida to){
        return  repo.save(to);
    }
    //D
    public void delete(Long id){
        repo.deleteById(id);
    }
    public  UnidadMedida buscarId(Long id){
        return  repo.findById(id).get();
    }
    public List<ComboBoxOption> listarCombobox(){
        List<ComboBoxOption> listar =new ArrayList<>();
        for (UnidadMedida cate : repo.findAll()) {
            listar.add(new ComboBoxOption(String
                    .valueOf(cate.getIdUnidad()),
                    cate.getNombreMedida()
            ));

        }
        return listar
                ;

    }
}
