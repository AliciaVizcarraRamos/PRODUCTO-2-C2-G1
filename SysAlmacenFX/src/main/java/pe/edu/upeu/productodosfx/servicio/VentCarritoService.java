package pe.edu.upeu.productodosfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.productodosfx.dto.ComboBoxOption;
import pe.edu.upeu.productodosfx.modelo.VentCarrito;
import pe.edu.upeu.productodosfx.repositorio.VentCarritoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentCarritoService {

    @Autowired
    VentCarritoRepository repo;

    public VentCarrito save(VentCarrito to) {
        return repo.save(to);


    }

    public List<VentCarrito> List() {
        return repo.findAll();

    }
    //U
    public VentCarrito update(VentCarrito to, Long id) {
        try {
            VentCarrito toe = repo.findById(id).get();
            toe.setIdCarrito(to.getIdCarrito());
            if (toe!=null){
                toe.setIdCarrito(to.getIdCarrito());

            }
            return repo.save(toe);
        }catch (Exception e){
            System.out.println("Error: "+ e.getMessage());

        }
        return null;


    }
    public VentCarrito update(VentCarrito to){
        return  repo.save(to);
    }
    //D
    public void delete(Long id){
        repo.deleteById(id);
    }
    public  VentCarrito buscarId(Long id){
        return  repo.findById(id).get();
    }
    public List<ComboBoxOption> listarCombobox(){
        List<ComboBoxOption> listar =new ArrayList<>();
        for (VentCarrito cate : repo.findAll()) {
            listar.add(new ComboBoxOption(String
                    .valueOf(cate.getIdCarrito()),
                    cate.getDniruc()
            ));

        }
        return listar
                ;

    }
}

