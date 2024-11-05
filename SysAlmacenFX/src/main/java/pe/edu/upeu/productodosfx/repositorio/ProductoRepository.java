package pe.edu.upeu.productodosfx.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.productodosfx.modelo.Producto;
@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
