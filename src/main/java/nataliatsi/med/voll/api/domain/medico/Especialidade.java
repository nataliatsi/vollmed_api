package nataliatsi.med.voll.api.domain.medico;

import lombok.Getter;

@Getter
public enum Especialidade {
    ORTOPEDIA ("ortopedia"),
    CARDIOLOGIA ("cardiologia"),
    GINECOLOGIA ("ginecologia"),
    DERMATOLOGIA ("dermatologia");

    private final String especialidade;

    Especialidade(String especialidade){
        this.especialidade = especialidade;
    }

    public static Especialidade fromString(String especialidade) {
        for (Especialidade e : Especialidade.values()) {
            if (e.especialidade.equalsIgnoreCase(especialidade)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Especialidade não reconhecida: " + especialidade);
    }

}

