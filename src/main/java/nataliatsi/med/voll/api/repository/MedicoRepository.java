package nataliatsi.med.voll.api.repository;

import nataliatsi.med.voll.api.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
