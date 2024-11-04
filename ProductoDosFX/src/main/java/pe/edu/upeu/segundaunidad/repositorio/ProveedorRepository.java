package pe.edu.upeu.segundaunidad.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.segundaunidad.modelo.Proveedor;
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Long> {
}
