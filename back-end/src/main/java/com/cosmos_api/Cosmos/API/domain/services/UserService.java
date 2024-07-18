package com.cosmos_api.Cosmos.API.domain.services;

import com.cosmos_api.Cosmos.API.aplication.dto.usuario.DatosRegistroUsuario;
import com.cosmos_api.Cosmos.API.domain.entities.DetallesUsuario;
import com.cosmos_api.Cosmos.API.domain.entities.Usuario;
import com.cosmos_api.Cosmos.API.domain.repository.DetallesUsuarioRepository;
import com.cosmos_api.Cosmos.API.domain.repository.UsuarioRepository;
import com.cosmos_api.Cosmos.API.infraestructure.errores.excepciones.EmailAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DetallesUsuarioRepository detallesUsuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    
    public Usuario registrarUsuario (DatosRegistroUsuario datosRegistroUsuario) {
        if (usuarioRepository.findByEmail(datosRegistroUsuario.email()) != null) {
            throw new EmailAlreadyExistsException("El correo electrónico ya está en uso");
        }
        // Crear instancia de Usuario
        Usuario usuario = new Usuario();
        usuario.setEmail(datosRegistroUsuario.email());
        String passwordEncoded = passwordEncoder.encode(datosRegistroUsuario.password());
        usuario.setPassword(passwordEncoded);
        // Guardar usuario y obtener el ID generado
        usuario = usuarioRepository.save(usuario);

        // Crear instancia de DetalleUsuario
        DetallesUsuario detallesUsuario = new DetallesUsuario();
        detallesUsuario.setUsuarioId(usuario);
        detallesUsuario.setName(datosRegistroUsuario.name());
        detallesUsuario.setLastName(datosRegistroUsuario.lastName());
        detallesUsuario.setUserName(datosRegistroUsuario.userName());
        detallesUsuario.setPhoneNumber(datosRegistroUsuario.phoneNumber());
        detallesUsuario.setActive(true);

        detallesUsuarioRepository.save(detallesUsuario);
        return usuario;
    }
}
