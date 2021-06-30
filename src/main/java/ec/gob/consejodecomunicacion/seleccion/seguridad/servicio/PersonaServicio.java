/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.consejodecomunicacion.seleccion.seguridad.servicio;

import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.Persona;
import ec.gob.consejodecomunicacion.seleccion.seguridad.repositorio.PersonaRep;
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
public class PersonaServicio {

    @Autowired
    private PersonaRep repository;

    @Transactional
    public boolean crear(Persona ent) {
        try {
            repository.save(ent);
            return true;
        } catch (Exception e) {
            log.debug("ERROR AL CREAR ACCESOS");
            return false;
        }
    }

    public Persona actualizar(Persona ent) {
        return repository.saveAndFlush(ent);
    }

    //Se implementa solo el borrado logino
    public Persona eliminarLogico(Persona ent) {
        ent.setEstado(Boolean.FALSE);
        return actualizar(ent);
    }

    public void eliminar(Persona ent) {
        repository.delete(ent);
    }

    public List<Persona> obtenerTodos() {
        return repository.findAll();
    }

    public Persona obtenerUno(Integer id) {
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

    public Persona obtenerPorDocumento(String documento) {
        return repository.findByDocumento(documento);
    }

}
