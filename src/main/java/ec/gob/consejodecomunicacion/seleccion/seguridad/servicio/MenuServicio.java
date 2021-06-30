/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.consejodecomunicacion.seleccion.seguridad.servicio;

import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.Menu;
import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.Rol;
import ec.gob.consejodecomunicacion.seleccion.seguridad.repositorio.MenuRep;
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
public class MenuServicio {

    @Autowired
    private MenuRep repository;

    @Transactional
    public Menu crear(Menu ent) {
        try {
            return repository.save(ent);
        } catch (Exception e) {
            log.debug("ERROR AL CREAR ACCESOS");
            return null;
        }
    }

    public Menu actualizar(Menu ent) {
        return repository.saveAndFlush(ent);
    }

    //Se implementa solo el borrado logino
    public Menu eliminarLogico(Menu ent) {
        ent.setEstado(Boolean.FALSE);
        return actualizar(ent);
    }

    public void eliminar(Menu ent) {
        repository.delete(ent);
    }

    public List<Menu> obtenerTodos() {
        return repository.findAll();
    }

    public Menu obtenerUno(Integer id) {
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

    public List<Menu> obtenerMenuRolPorRol(List<String> roles) {
        return repository.obtenerMenuRolPorRol(roles);
    }

    //Se implementa solo el borrado logino
    public Menu eliminarLogico(Integer id) {
        Menu menu = obtenerUno(id);
        menu.setEstado(Boolean.FALSE);
        return actualizar(menu);
    }

}
