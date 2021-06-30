/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.consejodecomunicacion.seleccion.seguridad.controlador;

import ec.gob.consejodecomunicacion.seleccion.constantes.Vistas;
import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.Usuario;
import ec.gob.consejodecomunicacion.seleccion.seguridad.servicio.UsuarioServicio;
import java.util.Date;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class UsuarioController {

    private static final Log LOGGER = LogFactory.getLog(UsuarioController.class);

    @Autowired
    private UsuarioServicio service;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("/usuarios")//Nombre del link 
    public ModelAndView getAll() {
        ModelAndView view = new ModelAndView();
        view.setViewName(Vistas.V_USUARIO);
        view.addObject("user", new Usuario());
        return view.addObject("users", service.obtenerTodos());//variable q se usa en la vista        
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addUser")
    public ModelAndView addUser(@Valid @ModelAttribute("user") Usuario user, BindingResult bindingResult) {
        ModelAndView view = new ModelAndView();
        if (bindingResult.hasErrors()) {
            LOGGER.info("METHOD: 'home' --PARAMS:" + user.getContrasenia() + "'");
            view.setViewName(Vistas.V_FRM_USUARIO);
        } else {
            view.setViewName(Vistas.V_USUARIO);
            if (!user.getUsername().isEmpty() && !user.getContrasenia().isEmpty()) {
                BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
                user.setContrasenia(pe.encode(user.getContrasenia()));
                //user.setEstado(Boolean.TRUE);
                user.setFechaCrea(new Date());
                service.crear(user);

                LOGGER.info("TEMPLATE: '" + Vistas.V_USUARIO + "' --DATA:" + user.getUsername() + "'");
                view.addObject("user", user);
                view.addObject("users", service.obtenerTodos());//variable q se usa en la vista                
            }
        }
        return view;
    }

    @GetMapping("/showForm")
    public String mostrarFormulario(
            @RequestParam(name = "id", required = false, defaultValue = "0") Integer id,
            Model model) {
        Usuario user = new Usuario();
        if (id != 0) {
            user = service.obtenerUno(id);
        }
        model.addAttribute("user", user);
        return Vistas.V_FRM_USUARIO;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/removeUser")
    public ModelAndView removeUser(@RequestParam(name = "id", required = true, defaultValue = "0") int id) {
        service.eliminarLogico(id);
        
        return this.getAll();
    }
}
