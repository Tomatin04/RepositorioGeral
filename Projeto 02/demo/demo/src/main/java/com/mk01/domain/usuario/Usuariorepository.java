package com.mk01.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface Usuariorepository extends JpaRepository<Usuario, Long> {

    UserDetails findByLogin(String login);
}
