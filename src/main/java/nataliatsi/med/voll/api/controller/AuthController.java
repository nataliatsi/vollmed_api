package nataliatsi.med.voll.api.controller;

import jakarta.validation.Valid;
import nataliatsi.med.voll.api.infra.security.DadosTokenJWT;
import nataliatsi.med.voll.api.infra.security.TokenService;
import nataliatsi.med.voll.api.domain.DadosAuth;
import nataliatsi.med.voll.api.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAuth dados){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var auth = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return  ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
