package ec.gob.consejodecomunicacion.seleccion.seguridad.modelo;

import ec.gob.consejodecomunicacion.seleccion.generico.EntidadAuditable;
import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the tb_menu database table.
 *
 */
@Setter
@Getter

@Entity
@XmlRootElement
@Table(name = "tb_menu", schema = "public")
@NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m")
@AttributeOverrides({
    @AttributeOverride(name = "estado", column = @Column(name = "menu_status")),
    @AttributeOverride(name = "fechaCrea", column = @Column(name = "date_create")),
    @AttributeOverride(name = "fechaActualiza", column = @Column(name = "date_update")),
    @AttributeOverride(name = "usuarioCrea", column = @Column(name = "user_create")),
    @AttributeOverride(name = "usuarioActualiza", column = @Column(name = "user_update"))})
public class Menu extends EntidadAuditable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Integer id;

    @Column(name = "menu_action", length = 100)
    private String accion;

    @Column(name = "menu_end_node")
    private Boolean nodoFinal;

    @Column(name = "menu_ico", length = 100)
    private String icono;

    @Column(name = "menu_img", length = 100)
    private String imagen;

    @Column(name = "menu_name", length = 100)
    private String nombre;

    @Column(name = "menu_order")
    private Integer orden;

    @Column(name = "menu_url", length = 100)
    private String url;

    // bi-directional many-to-one association to Menu
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_father_id")
    private Menu menu;

    // bi-directional many-to-one association to Menu
    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    private List<Menu> menus;

    // bi-directional many-to-one association to MenuRol
    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    private List<MenuRol> menuRoles;

    public Menu() {
    }

}
