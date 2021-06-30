package ec.gob.consejodecomunicacion.seleccion.seguridad.modelo;

import ec.gob.consejodecomunicacion.seleccion.generico.EntidadAuditable;
import java.io.Serializable;

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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the tb_menu_role database table.
 *
 */
@Setter
@Getter

@Entity
@XmlRootElement
@Table(name = "tb_menu_role", schema = "public")
@NamedQuery(name = "MenuRol.findAll", query = "SELECT m FROM MenuRol m")
@AttributeOverrides({
    @AttributeOverride(name = "estado", column = @Column(name = "mero_status")),
    @AttributeOverride(name = "fechaCrea", column = @Column(name = "date_create")),
    @AttributeOverride(name = "fechaActualiza", column = @Column(name = "date_update")),
    @AttributeOverride(name = "usuarioCrea", column = @Column(name = "user_create")),
    @AttributeOverride(name = "usuarioActualiza", column = @Column(name = "user_update"))})
public class MenuRol extends EntidadAuditable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mero_id")
    private Integer id;

    // bi-directional many-to-one association to Menu
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    // bi-directional many-to-one association to Rol
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Rol rol;

    public MenuRol() {
    }

}
