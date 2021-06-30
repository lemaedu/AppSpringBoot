/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.consejodecomunicacion.seleccion.seguridad.repositorio;

import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository
public interface UsuarioRep extends JpaRepository<Usuario, Integer> {

    @Query("SELECT t FROM Usuario t WHERE t.username = :username AND t.contrasenia=:contrasenia AND t.estado=true ")
    public List<Usuario> findByUsernameAndContrasenia(@Param("username") String username, @Param("contrasenia") String contrasenia);

    public abstract Usuario findByUsername(String username);

}
