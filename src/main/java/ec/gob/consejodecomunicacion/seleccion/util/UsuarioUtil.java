/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.consejodecomunicacion.seleccion.util;

import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.MenuRol;
import ec.gob.consejodecomunicacion.seleccion.seguridad.servicio.MenuRolServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.User;

public class UsuarioUtil {

    @Autowired
    MenuRolServicio menuRolServicio;

    public List<String> listarRoles = new ArrayList<>();
    public List< String> listaMenu = new ArrayList<>();
    public List< String> listaMenuSuperior = new ArrayList<>();
    List<MenuRol> listaSubMenu = new ArrayList<>();
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    public List<String> getRoles() {
        user.getAuthorities().forEach((gr) -> {
            listarRoles.add(gr.getAuthority());
        });
        return listarRoles;
    }

    public List<String> getMenu() {
        getSubmenu();
        listaSubMenu.forEach((op) -> {
            listaMenu.add(op.getMenu().getNombre());
        });
        listaMenuSuperior = listaMenu.stream().distinct().collect(Collectors.toList());
        return listaMenuSuperior;
    }

    public List<MenuRol> getSubmenu() {
        getRoles();
        listaSubMenu = menuRolServicio.obtenerMenuRolPorRol(listarRoles);
        return listaSubMenu;

    }

}
