package nataliatsi.med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import nataliatsi.med.voll.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
