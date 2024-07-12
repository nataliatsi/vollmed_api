package nataliatsi.med.voll.api.domain.consulta.validacoes;

import nataliatsi.med.voll.api.domain.ValidacaoException;
import nataliatsi.med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioAntecedencia implements ValidadorAgendamentoConsultas {

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if(diferencaEmMinutos < 30){
            throw new ValidacaoException("A consulta deve ser agendada com, no mínimo, 30 minutos de antecedência.");
        }
    }
}
