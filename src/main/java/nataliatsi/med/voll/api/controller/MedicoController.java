package nataliatsi.med.voll.api.controller;

import jakarta.validation.Valid;
import nataliatsi.med.voll.api.model.DadosListagemMedicos;
import nataliatsi.med.voll.api.model.Medico;
import nataliatsi.med.voll.api.model.DadosCadastroMedico;
import nataliatsi.med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        medicoRepository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedicos> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        return medicoRepository.findAll(pageable).map(DadosListagemMedicos::new);
    }


}
