package dc.estoquecontrol.controller;

import dc.estoquecontrol.dto.request.EfetuarLoginRequest;
import dc.estoquecontrol.dto.response.UserInfoResponse;
import dc.estoquecontrol.entity.User;
import dc.estoquecontrol.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid EfetuarLoginRequest dtoLogin) {
        var token = new UsernamePasswordAuthenticationToken(dtoLogin.login(), dtoLogin.senha());
        var authentication = manager.authenticate(token);

        var jwt = tokenService.gerarToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/me")
    public ResponseEntity me(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(new UserInfoResponse(user));
    }
}
