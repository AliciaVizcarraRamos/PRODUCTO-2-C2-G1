package pe.edu.upeu.productodosfx.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.productodosfx.modelo.Compra;
@Repository
public interface CompraRepository extends JpaRepository<Compra,Long> {
}
