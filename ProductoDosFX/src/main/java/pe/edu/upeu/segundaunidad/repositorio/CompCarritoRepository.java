package pe.edu.upeu.segundaunidad.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.segundaunidad.modelo.CompCarrito;
@Repository
public interface CompCarritoRepository extends JpaRepository <CompCarrito,Long> {
}
