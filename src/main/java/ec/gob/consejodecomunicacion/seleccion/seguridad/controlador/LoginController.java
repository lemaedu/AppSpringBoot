/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.consejodecomunicacion.seleccion.seguridad.controlador;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ec.gob.consejodecomunicacion.seleccion.constantes.Vistas;
import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.Menu;
import ec.gob.consejodecomunicacion.seleccion.seguridad.servicio.MenuRolServicio;
import ec.gob.consejodecomunicacion.seleccion.seguridad.servicio.MenuServicio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.security.core.userdetails.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author Usuario
 */
@Slf4j
@Controller
public class LoginController {

    @Autowired
    MenuServicio menuServ;

    @Autowired
    MenuRolServicio menuRolServ;

    @GetMapping("/login")
    public String showLoginForm(Model model,
            @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "logout", required = false) String logout) {
        log.info("METHOD: showLoginForm() -- PARAMS: error=" + error + " logout=" + logout);
        model.addAttribute("error", error);
        model.addAttribute("logout", logout);
        //model.addAttribute("user", new Usuario());
        return Vistas.V_LOGIN;
    }

    @GetMapping({"/loginsuccess", "/"})
    public ModelAndView loginCheck() {
        ModelAndView view = new ModelAndView();

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<String> listarRoles = new ArrayList<>();
        user.getAuthorities().forEach((gr) -> {
            listarRoles.add(gr.getAuthority());
        });

        List<Menu> listaMenu = new ArrayList<>();
        listaMenu = menuServ.obtenerMenuRolPorRol(listarRoles);

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true); // true == allow create

        session.setAttribute("listaMenu", listaMenu);
        
        view.setViewName("home");
        return view;

    }

}
