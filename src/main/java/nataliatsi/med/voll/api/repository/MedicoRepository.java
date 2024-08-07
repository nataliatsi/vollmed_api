package nataliatsi.med.voll.api.repository;

import nataliatsi.med.voll.api.domain.medico.Especialidade;
import nataliatsi.med.voll.api.domain.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable pageable);

//    @Query("""
//            SELECT m FROM Medico m
//            WHERE
//            m.ativo = true
//            AND
//            m.especialidade = :especialidade
//            AND
//            m.id NOT IN(SELECT c.medico FROM Consulta c WHERE c.data = :data)
//            ORDER BY RAND()
//            LIMIT 1
//            """)

    @Query(value = """
        SELECT m.* FROM medicos m
        WHERE
        m.ativo = true
        AND
        m.especialidade = :especialidade
        AND
        m.id NOT IN(SELECT c.medico_id FROM consultas c WHERE c.data = :data)
        ORDER BY RAND()
        LIMIT 1
        """, nativeQuery = true)
    Medico escolherMedicoAleatorioDisponivel(Especialidade especialidade, LocalDateTime data);
}
