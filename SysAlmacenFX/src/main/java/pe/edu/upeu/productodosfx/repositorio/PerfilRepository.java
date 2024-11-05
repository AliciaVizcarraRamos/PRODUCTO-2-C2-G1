package pe.edu.upeu.productodosfx.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.productodosfx.modelo.Perfil;
@Repository
public interface PerfilRepository extends JpaRepository<Perfil,Long> {
}
