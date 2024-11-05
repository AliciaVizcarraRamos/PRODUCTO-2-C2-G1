package pe.edu.upeu.productodosfx.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.productodosfx.modelo.VentaDetalle;
@Repository
public interface VentaDetalleRepository extends JpaRepository<VentaDetalle,Long> {
}
