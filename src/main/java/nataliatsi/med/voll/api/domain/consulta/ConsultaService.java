package nataliatsi.med.voll.api.domain.consulta;

import nataliatsi.med.voll.api.domain.ValidacaoException;
import nataliatsi.med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoConsultas;
import nataliatsi.med.voll.api.domain.medico.Medico;
import nataliatsi.med.voll.api.domain.paciente.PacienteRepository;
import nataliatsi.med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoConsultas> validadores;


    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){

        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe.");
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do médico informado não existe.");
        }

        validadores.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);

        if(medico == null){
            throw new ValidacaoException("Não existe médico disponível nessa data.");
        }
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());

        var consulta = new Consulta(null, medico, paciente, dados.data());
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);

    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null){
            throw  new ValidacaoException("Especialidade é obrigatória quando o médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioDisponivel(dados.especialidade(), dados.data());
    }
}
