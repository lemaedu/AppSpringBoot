/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.consejodecomunicacion.seleccion.seguridad.controlador;

import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.AditoriaAcceso;
import ec.gob.consejodecomunicacion.seleccion.seguridad.servicio.AditoriaAccesoServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Usuario
 */
@Controller
public class AuditoriaAccesoControlador {
    @Autowired
    private AditoriaAccesoServicio service;
    
    @GetMapping("/accesos")//Nombre del link 
    public String getAll(Model model) {
        List<AditoriaAcceso> accesos = service.obtenerTodos();
        model.addAttribute("accesos", accesos);//variable q se usa en la vista
        return "acceso";//Nombre de la vista(.html)
    }
    
}
