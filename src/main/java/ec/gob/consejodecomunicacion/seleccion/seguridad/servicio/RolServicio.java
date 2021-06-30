/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.consejodecomunicacion.seleccion.seguridad.servicio;

import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.Rol;
import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.Usuario;
import ec.gob.consejodecomunicacion.seleccion.seguridad.repositorio.RolRep;
import java.util.List;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
@Slf4j
public class RolServicio {

    @Autowired
    private RolRep repository;

    //@Transactional
    public boolean crear(Rol ent) {
        try {
            repository.save(ent);
            return true;
        } catch (Exception e) {
            log.debug("ERROR AL CREAR ACCESOS");
            return false;
        }
    }

    public Rol actualizar(Rol ent) {
        return repository.saveAndFlush(ent);
    }

    //Se implementa solo el borrado logino
    public Rol eliminarLogico(Rol ent) {
        ent.setEstado(Boolean.FALSE);
        return actualizar(ent);
    }

    //Se implementa solo el borrado logino
    public Rol eliminarLogico(Integer id) {
        Rol rol = obtenerUno(id);
        rol.setEstado(Boolean.FALSE);
        return actualizar(rol);
    }

    public void eliminar(Rol ent) {
        repository.delete(ent);
    }

    public List<Rol> obtenerTodos() {
        return repository.findAll();
    }

    public Rol obtenerUno(Integer id) {
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
