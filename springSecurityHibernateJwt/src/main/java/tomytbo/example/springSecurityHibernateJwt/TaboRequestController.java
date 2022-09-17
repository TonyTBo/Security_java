package tomytbo.example.springSecurityHibernateJwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tomytbo.example.springSecurityHibernateJwt.jwt.JwtTokenProvider;
import tomytbo.example.springSecurityHibernateJwt.payload.LoginRequest;
import tomytbo.example.springSecurityHibernateJwt.payload.LoginResponse;
import tomytbo.example.springSecurityHibernateJwt.payload.RandomStuff;
import tomytbo.example.springSecurityHibernateJwt.user.CustomUserDetails;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class TaboRequestController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        //invalid from username and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }

    @GetMapping("/random")
    public RandomStuff randomStuff() {
        return new RandomStuff("JWT valid can new see this message");
    }
}
