/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.consejodecomunicacion.seleccion.seguridad.servicio;

import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.Geografia;
import ec.gob.consejodecomunicacion.seleccion.seguridad.repositorio.GeografiaRep;
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
public class GeografiaServicio {

    @Autowired
    private GeografiaRep repository;

    @Transactional
    public boolean crear(Geografia ent) {
        try {
            repository.save(ent);
            return true;
        } catch (Exception e) {
            log.debug("ERROR AL CREAR ACCESOS");
            return false;
        }
    }

    public Geografia actualizar(Geografia ent) {
        return repository.saveAndFlush(ent);
    }

    //Se implementa solo el borrado logino
    public Geografia eliminarLogico(Geografia ent) {
        ent.setEstado(Boolean.FALSE);
        return actualizar(ent);
    }

    public void eliminar(Geografia ent) {
        repository.delete(ent);
    }

    public List<Geografia> obtenerTodos() {
        return repository.findAll();
    }

    public Geografia obtenerUno(Integer id) {
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
