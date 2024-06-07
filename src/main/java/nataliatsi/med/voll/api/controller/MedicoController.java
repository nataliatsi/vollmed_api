package nataliatsi.med.voll.api.controller;

import jakarta.validation.Valid;
import nataliatsi.med.voll.api.model.Medico;
import nataliatsi.med.voll.api.model.MedicoDTO;
import nataliatsi.med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid MedicoDTO dados){
        medicoRepository.save(new Medico(dados));
    }

    @GetMapping
    public List<Medico> getMedicos(){
        return medicoRepository.findAll();
    }

}
