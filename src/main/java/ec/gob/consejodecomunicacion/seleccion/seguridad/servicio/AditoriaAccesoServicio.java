/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.consejodecomunicacion.seleccion.seguridad.servicio;

import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.AditoriaAcceso;
import ec.gob.consejodecomunicacion.seleccion.seguridad.repositorio.AuditoriaAccesoRep;
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
public class AditoriaAccesoServicio {

    @Autowired
    private AuditoriaAccesoRep repository;

    @Transactional
    public boolean crear(AditoriaAcceso ent) {
        try {
            repository.save(ent);
            return true;
        } catch (Exception e) {
            log.debug("ERROR AL CREAR ACCESOS");
            return false;
        }
    }

    public AditoriaAcceso actualizar(AditoriaAcceso ent) {
        return repository.saveAndFlush(ent);
    }

    //Se implementa solo el borrado logino
    public AditoriaAcceso eliminarLogico(AditoriaAcceso ent) {
        ent.setEstado(Boolean.FALSE);
        return actualizar(ent);
    }

    public void eliminar(AditoriaAcceso ent) {
        repository.delete(ent);
    }

    public List<AditoriaAcceso> obtenerTodos() {
        return repository.findAll();
    }

    public AditoriaAcceso obtenerUno(Integer id) {
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
