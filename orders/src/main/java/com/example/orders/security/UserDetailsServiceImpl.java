package com.example.orders.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = getById(username);
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        return User
                .withUsername(username)
                .password(usuario.password())
                .roles(usuario.roles().toArray(new String[0]))
                .build();
    }

    public record Usuario(String username, String password, Set<String> roles) {}

    public static Usuario getById(String username) {
        var password = "$2a$10$SCVP2gPTSttAL.xz9vTMnuEbrqSZz3eEEVUmx0Ze.1p.YHDN27QHe"; //

        Usuario pitu = new
                Usuario("pitu",
                password,
                Set.of("USER"));

        Usuario nahu = new
                Usuario("nahu",
                password,
                Set.of("ADMIN"));

        var usuarios = List.of(pitu, nahu);

        return usuarios.stream()
                .filter(e -> e.username().equals(username))
                .findFirst()
                .orElse(null);
    }
}
