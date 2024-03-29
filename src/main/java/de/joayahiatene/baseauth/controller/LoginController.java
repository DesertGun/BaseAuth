package de.joayahiatene.baseauth.controller;

import de.joayahiatene.baseauth.domain.security.JWTRequest;
import de.joayahiatene.baseauth.domain.security.JWTToken;
import de.joayahiatene.baseauth.domain.user.UserService;
import de.joayahiatene.baseauth.response.JWTResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JWTToken jwtToken;
    private final UserService userService;
    Logger logger = LoggerFactory.getLogger(LoginController.class);

    public LoginController(AuthenticationManager authenticationManager, JWTToken jwtToken, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtToken = jwtToken;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JWTRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        } catch (DisabledException e) {
            String error = "User not found!";
            logger.warn(error);
            return ResponseEntity.ok(new JWTResponse(null, error));
        } catch (BadCredentialsException e) {
            String error = "Username or Password incorrect!";
            logger.warn(error);
            return ResponseEntity.ok(new JWTResponse(null, error));
        }
        final UserDetails userDetails = userService.getUserByUsername(authenticationRequest.getUsername());
        final String token = jwtToken.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token, null));
    }

}
