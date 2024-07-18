package com.cosmos_api.Cosmos.API.domain.services;

import com.cosmos_api.Cosmos.API.domain.repository.UsuarioRepository;
import com.cosmos_api.Cosmos.API.infraestructure.errores.excepciones.UsuarioNoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticationService implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var userMail = usuarioRepository.findByEmail(username);
       if (userMail != null) {
           return userMail;
       }
       throw new UsuarioNoEncontradoException("Usuario inexistente");
    }
}
