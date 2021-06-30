/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.consejodecomunicacion.seleccion.seguridad.servicio;

import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.RolUsuario;
import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.Usuario;
import ec.gob.consejodecomunicacion.seleccion.seguridad.repositorio.UsuarioRep;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service("usuarioServicio")
@Slf4j
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRep repository;

    /* - - - - - - - - - S E G U R I D A D  - - - - - - - - -*/
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(userName);
        List<GrantedAuthority> authorities = buildAutorities(usuario.getRolUsuarios());
        return buildUser(usuario, authorities);
    }

    //retorna User de tipo Spring
    private User buildUser(Usuario usuario, List<GrantedAuthority> authorities) {
        //alias,clave,activo,acountNoexpired,credentialNotExpired,acountNonLocked,
        return new User(usuario.getUsername(), usuario.getContrasenia(), usuario.getEstado(), true, true, true, authorities);
    }

    //convierte nuestros roles a GrantedAuthority
    private List<GrantedAuthority> buildAutorities(Set<RolUsuario> rolUsuarios) {
        Set<GrantedAuthority> auths = new HashSet<>();
        for (RolUsuario rolUsuario : rolUsuarios) {
            if (rolUsuario.getEstado()) {
                auths.add(new SimpleGrantedAuthority(rolUsuario.getRol().getNombre()));
            }
        }
        return new ArrayList<>(auths);
    }

    /* - - - - - - - - - F I N  S E G U R I D A D  - - - - - - - - -*/
    @Transactional
    public boolean crear(Usuario ent) {
        try {
            repository.save(ent);
            return true;
        } catch (Exception e) {
            log.debug("ERROR AL CREAR ACCESOS");
            return false;
        }
    }

    public Usuario actualizar(Usuario ent) {
        return repository.saveAndFlush(ent);
    }

    //Se implementa solo el borrado logino
    public Usuario eliminarLogico(Usuario ent) {
        ent.setEstado(Boolean.FALSE);
        return actualizar(ent);
    }

    //Se implementa solo el borrado logino
    public Usuario eliminarLogico(Integer id) {
        Usuario us = obtenerUno(id);
        us.setEstado(Boolean.FALSE);
        return actualizar(us);
    }

    public void eliminar(Usuario ent) {
        repository.delete(ent);
    }

    public List<Usuario> obtenerTodos() {
        return repository.findAll();
    }

    public Usuario obtenerUno(Integer id) {
        return repository.getOne(id);
    }

    public boolean eliminarPorId(Integer id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.debug("ERROR AL ELIMINAR ACCESO --Parameters id=" + id + "+ Method=deleteById" + "error:");
            return false;
        }
    }

}
