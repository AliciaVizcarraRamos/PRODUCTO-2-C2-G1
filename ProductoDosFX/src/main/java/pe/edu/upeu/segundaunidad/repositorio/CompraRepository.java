package pe.edu.upeu.segundaunidad.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.segundaunidad.modelo.Compra;
@Repository
public interface CompraRepository extends JpaRepository<Compra,Long> {
}
