package nataliatsi.med.voll.api.model.medico;

import jakarta.validation.constraints.NotNull;
import nataliatsi.med.voll.api.model.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
