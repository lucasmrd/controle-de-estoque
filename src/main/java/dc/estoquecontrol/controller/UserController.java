package dc.estoquecontrol.controller;

import dc.estoquecontrol.dto.request.EfetuarLoginRequest;
import dc.estoquecontrol.entity.User;
import dc.estoquecontrol.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class UserController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid EfetuarLoginRequest dtoLogin) {
        var token = new UsernamePasswordAuthenticationToken(dtoLogin.login(), dtoLogin.senha());
        var authentication = manager.authenticate(token);

        var jwt = tokenService.gerarToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(jwt);
    }
}
