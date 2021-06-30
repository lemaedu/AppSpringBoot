/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.consejodecomunicacion.seleccion;

import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.Menu;
import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.MenuRol;
import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.Persona;
import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.Rol;
import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.RolUsuario;
import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.Usuario;
import ec.gob.consejodecomunicacion.seleccion.seguridad.servicio.MenuRolServicio;
import ec.gob.consejodecomunicacion.seleccion.seguridad.servicio.MenuServicio;
import ec.gob.consejodecomunicacion.seleccion.seguridad.servicio.PersonaServicio;
import ec.gob.consejodecomunicacion.seleccion.seguridad.servicio.RolServicio;
import ec.gob.consejodecomunicacion.seleccion.seguridad.servicio.RolUsuarioServicio;
import ec.gob.consejodecomunicacion.seleccion.seguridad.servicio.UsuarioServicio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

/**
 *
 * @author llema
 */
@Controller
public class InicializaBD {

    @Autowired
    UsuarioServicio us;

    @Autowired
    RolServicio rs;

    @Autowired
    PersonaServicio ps;

    @Autowired
    RolUsuarioServicio rus;

    @Autowired
    MenuServicio ms;

    @Autowired
    MenuRolServicio mrs;

    private BCryptPasswordEncoder pe;

    @lombok.Setter
    @lombok.Getter
    private Usuario u;

    @lombok.Setter
    @lombok.Getter
    private Persona p;

    @lombok.Setter
    @lombok.Getter
    private Rol r;

    @lombok.Setter
    @lombok.Getter
    private RolUsuario ru;

    @lombok.Setter
    @lombok.Getter
    private Menu m;

    @lombok.Setter
    @lombok.Getter
    private MenuRol mr;

    public void inicializa() {

        /*---------CREA ROL---------*/
        r = new Rol();
        r.setNombre("ROLE_ADMIN");
        r.setDescripcion("Menu");
        rs.crear(r);
        r = rs.obtenerUno(1);
        /*---------CREA PERSONA---------*/
        p = new Persona();
        p.setDocumento("1234567890");
        p.setEstado(Boolean.TRUE);
        p.setNombre("Administrador");
        ps.crear(p);
        p = ps.obtenerPorDocumento(p.getDocumento());
        /*---------CREA USUARIO---------*/
        u = new Usuario();
        if (p != null) {
            u.setPersonsa(p);
        }
        u.setUsername("admin");
        u.setEstado(Boolean.TRUE);
        u.setContrasenia(pe.encode("admin"));

        us.crear(u);

        u = us.obtenerUno(1);

        /*---------CREA ROL USUARIO---------*/
        ru = new RolUsuario();
        ru.setEstado(Boolean.TRUE);
        ru.setRol(r);
        ru.setUsuario(u);
        rus.crear(ru);

        /*---------CREA MENU---------*/
        Menu menuPrincipal = new Menu();
        menuPrincipal.setEstado(Boolean.TRUE);
        menuPrincipal.setNombre("MENU_APP");
        menuPrincipal.setMenu(null);
        menuPrincipal.setNodoFinal(Boolean.FALSE);
        menuPrincipal.setOrden(0);
        menuPrincipal = ms.crear(menuPrincipal);

        Menu mArchivo = new Menu();
        mArchivo.setEstado(Boolean.TRUE);
        mArchivo.setNombre("ARCHIVO");
        mArchivo.setMenu(menuPrincipal);
        mArchivo.setNodoFinal(Boolean.FALSE);
        mArchivo.setOrden(0);
        mArchivo = ms.crear(mArchivo);

        Menu mDoc = new Menu();
        mDoc.setEstado(Boolean.TRUE);
        mDoc.setNombre("Documento");
        mDoc.setMenu(mArchivo);
        mDoc.setNodoFinal(Boolean.TRUE);
        mDoc.setOrden(0);
        mDoc = ms.crear(mDoc);

        Menu mFile = new Menu();
        mFile.setEstado(Boolean.TRUE);
        mFile.setNombre("Documento");
        mFile.setMenu(mArchivo);
        mFile.setNodoFinal(Boolean.TRUE);
        mFile.setOrden(1);
        mFile = ms.crear(mFile);
        /*----*/
        Menu mAdmin = new Menu();
        mAdmin.setEstado(Boolean.TRUE);
        mAdmin.setNombre("ADMINISTRAR");
        mAdmin.setMenu(menuPrincipal);
        mAdmin.setNodoFinal(Boolean.FALSE);
        mAdmin.setOrden(1);
        mAdmin = ms.crear(mAdmin);

        Menu mUsu = new Menu();
        mUsu.setEstado(Boolean.TRUE);
        mUsu.setNombre("Usuario");
        mUsu.setUrl("/usuarios");
        mUsu.setMenu(mAdmin);
        mUsu.setNodoFinal(Boolean.TRUE);
        mUsu.setOrden(0);
        mUsu = ms.crear(mUsu);

        Menu mRol = new Menu();
        mRol.setEstado(Boolean.TRUE);
        mRol.setUrl("/roles");
        mRol.setNombre("Rol");
        mRol.setMenu(mAdmin);
        mRol.setNodoFinal(Boolean.TRUE);
        mRol.setOrden(1);
        mRol = ms.crear(mRol);

        /*CREA MENU ROL*/
        mr = new MenuRol();
        mr.setEstado(Boolean.TRUE);
        mr.setRol(r);
        mr.setMenu(menuPrincipal);
        mrs.crear(mr);

        mr = new MenuRol();
        mr.setEstado(Boolean.TRUE);
        mr.setRol(r);
        mr.setMenu(mArchivo);
        mrs.crear(mr);

        mr = new MenuRol();
        mr.setEstado(Boolean.TRUE);
        mr.setRol(r);
        mr.setMenu(mDoc);
        mrs.crear(mr);

        mr = new MenuRol();
        mr.setEstado(Boolean.TRUE);
        mr.setRol(r);
        mr.setMenu(mFile);
        mrs.crear(mr);

        mr = new MenuRol();
        mr.setEstado(Boolean.TRUE);
        mr.setRol(r);
        mr.setMenu(mAdmin);
        mrs.crear(mr);

        mr = new MenuRol();
        mr.setEstado(Boolean.TRUE);
        mr.setRol(r);
        mr.setMenu(mUsu);
        mrs.crear(mr);

        mr = new MenuRol();
        mr.setEstado(Boolean.TRUE);
        mr.setRol(r);
        mr.setMenu(mRol);
        mrs.crear(mr);

    }

}
