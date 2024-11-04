package pe.edu.upeu.segundaunidad.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.segundaunidad.modelo.Producto;
@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
