/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.consejodecomunicacion.seleccion.seguridad.controlador;

import ec.gob.consejodecomunicacion.seleccion.constantes.Vistas;
import static ec.gob.consejodecomunicacion.seleccion.constantes.Vistas.V_ROL;
import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.Rol;
import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.Usuario;
import ec.gob.consejodecomunicacion.seleccion.seguridad.servicio.RolServicio;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class RolController {

    @Autowired
    private RolServicio service;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("/roles")//Nombre del link 
    public ModelAndView getAll() {
        ModelAndView view = new ModelAndView();
        view.setViewName(Vistas.V_ROL);
        view.addObject("rol", new Rol());
        return view.addObject("listaRol", service.obtenerTodos());//variable
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addRol")
    public ModelAndView addRol(@Valid @ModelAttribute("rol") Rol rol, BindingResult result) {
        ModelAndView view = new ModelAndView();
        if (result.hasErrors()) {
            LOGGER.info("METHOD: 'home' --PARAMS:" + rol.getNombre() + "'");
            view.setViewName(Vistas.V_FORM_ROL);
        } else {
            view.setViewName(Vistas.V_ROL);
            if (!rol.getNombre().isEmpty()) {
                service.crear(rol);

                LOGGER.info("TEMPLATE: '" + Vistas.V_ROL + "' --DATA:" + rol.getNombre() + "'");
                view.addObject("rol", rol);
                view.addObject("listaRol", service.obtenerTodos());//variable q se usa en la vista                
            }
        }
        return view;
    }

    @GetMapping("/rol/showForm")
    public String mostrarFormulario(
            @RequestParam(name = "id", required = false, defaultValue = "0") Integer id,
            Model model) {
        Rol rol = new Rol();
        if (id != 0) {
            rol = service.obtenerUno(id);
        }
        model.addAttribute("rol", rol);
        return Vistas.V_FORM_ROL;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/eliminaRol")
    public ModelAndView removeRol(@RequestParam(name = "id", required = true, defaultValue = "0") int id) {
        service.eliminarLogico(id);
        return this.getAll();
    }

}
