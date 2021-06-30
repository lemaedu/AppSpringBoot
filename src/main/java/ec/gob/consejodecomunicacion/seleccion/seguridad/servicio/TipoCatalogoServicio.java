/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.consejodecomunicacion.seleccion.seguridad.servicio;

import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.TipoCatalogo;
import ec.gob.consejodecomunicacion.seleccion.seguridad.repositorio.TipoCatalogoRep;
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
public class TipoCatalogoServicio {

    @Autowired
    private TipoCatalogoRep repository;

    @Transactional
    public boolean crear(TipoCatalogo ent) {
        try {
            repository.save(ent);
            return true;
        } catch (Exception e) {
            log.debug("ERROR AL CREAR ACCESOS");
            return false;
        }
    }

    public TipoCatalogo actualizar(TipoCatalogo ent) {
        return repository.saveAndFlush(ent);
    }

    //Se implementa solo el borrado logino
    public TipoCatalogo eliminarLogico(TipoCatalogo ent) {
        ent.setEstado(Boolean.FALSE);
        return actualizar(ent);
    }

    public void eliminar(TipoCatalogo ent) {
        repository.delete(ent);
    }

    public List<TipoCatalogo> obtenerTodos() {
        return repository.findAll();
    }

    public TipoCatalogo obtenerUno(Integer id) {
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
