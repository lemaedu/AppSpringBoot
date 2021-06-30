/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.consejodecomunicacion.seleccion.seguridad.controlador;

import ec.gob.consejodecomunicacion.seleccion.constantes.Vistas;
import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.Menu;
import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.Rol;
import ec.gob.consejodecomunicacion.seleccion.seguridad.servicio.MenuServicio;
import java.util.List;
import javax.validation.Valid;
import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Usuario
 */
@Controller
public class MenuController {

    @Autowired
    private MenuServicio service;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/menus")//Nombre del link 
    public ModelAndView getAll() {

        ModelAndView view = new ModelAndView();
        view.setViewName(Vistas.V_MENU);
        view.addObject("menu", new Menu());
        return view.addObject("listaMenu", service.obtenerTodos());//variable

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addMenu")
    public ModelAndView addMenu(@Valid @ModelAttribute("menu") Menu menu, BindingResult result) {
        ModelAndView view = new ModelAndView();
        if (result.hasErrors()) {
            LOGGER.info("METHOD: 'home' --PARAMS:" + menu.getNombre() + "'");
            view.setViewName(Vistas.V_FORM_MENU);
        } else {
            view.setViewName(Vistas.V_MENU);
            if (!menu.getNombre().isEmpty()) {
                service.crear(menu);

                LOGGER.info("TEMPLATE: '" + Vistas.V_MENU + "' --DATA:" + menu.getNombre() + "'");
                view.addObject("menu", menu);
                view.addObject("listaMenu", service.obtenerTodos());//variable q se usa en la vista                
            }
        }
        return view;
    }

    @GetMapping("/menu/showForm")
    public String mostrarFormulario(
            @RequestParam(name = "id", required = false, defaultValue = "0") Integer id,
            Model model) {
        Menu menu = new Menu();
        if (id != 0) {
            menu = service.obtenerUno(id);
        }
        model.addAttribute("menu", menu);
        return Vistas.V_FORM_MENU;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/eliminaMenu")
    public ModelAndView removeMenu(@RequestParam(name = "id", required = true, defaultValue = "0") int id) {
        service.eliminarLogico(id);
        return this.getAll();
    }
}
