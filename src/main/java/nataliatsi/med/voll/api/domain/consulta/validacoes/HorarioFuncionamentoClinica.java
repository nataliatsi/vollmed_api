package nataliatsi.med.voll.api.domain.consulta.validacoes;

import nataliatsi.med.voll.api.domain.ValidacaoException;
import nataliatsi.med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioFuncionamentoClinica implements ValidadorAgendamentoConsultas {

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDeAbrir = dataConsulta.getHour() < 7;
        var depoisDeFechar = dataConsulta.getHour() > 18;

        if(domingo || antesDeAbrir || depoisDeFechar){
            throw new ValidacaoException("Consulta fora do horário da clínica.");
        }
    }
}
