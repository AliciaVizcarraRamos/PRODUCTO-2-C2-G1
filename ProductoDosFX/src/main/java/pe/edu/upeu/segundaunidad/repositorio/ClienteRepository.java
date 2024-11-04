package pe.edu.upeu.segundaunidad.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.segundaunidad.modelo.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
