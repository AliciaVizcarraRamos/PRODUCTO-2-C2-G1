package pe.edu.upeu.productodosfx.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.productodosfx.modelo.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
