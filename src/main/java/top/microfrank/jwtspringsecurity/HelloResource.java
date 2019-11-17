package top.microfrank.jwtspringsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.microfrank.jwtspringsecurity.model.AuthRequest;
import top.microfrank.jwtspringsecurity.model.AuthResponse;
import top.microfrank.jwtspringsecurity.util.JwtUtil;

/**
 * @author Frank
 * @date 2019/11/17 11:28
 */
@RestController
public class HelloResource {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    MyUserDetailsService userDetailsService;
    @Autowired
    JwtUtil jwtUtil;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> auth(@RequestBody AuthRequest authRequest) {
        // authenticationManager最终调用的是MyUserDetailsService中的loadUserByUsername
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        String jwt = jwtUtil.generateToken(authRequest.getUsername());
        return ResponseEntity.ok(new AuthResponse(jwt));

    }
}
