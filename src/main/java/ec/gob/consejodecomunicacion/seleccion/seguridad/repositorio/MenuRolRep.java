/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.consejodecomunicacion.seleccion.seguridad.repositorio;

import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.MenuRol;
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
public interface MenuRolRep extends JpaRepository<MenuRol, Integer> {

    @Query(value = "SELECT r FROM tb_rol r "
            + "WHERE r.nombre in ('Administrador','Usuario') ", nativeQuery = true)
    public List<MenuRol> findMenu(@Param("roles") String roles);

    @Query("SELECT mr FROM MenuRol mr WHERE mr.rol.nombre in :roles")
    public List<MenuRol> obtenerMenuRolPorRol(@Param("roles") List<String> roles);

}
