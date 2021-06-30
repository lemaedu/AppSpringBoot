/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.consejodecomunicacion.seleccion.seguridad.repositorio;

import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.Menu;
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
public interface MenuRep extends JpaRepository<Menu, Integer> {

    @Query("SELECT mr.menu FROM MenuRol mr WHERE mr.rol.nombre in :roles")
    public List<Menu> obtenerMenuRolPorRol(@Param("roles") List<String> roles);

}
