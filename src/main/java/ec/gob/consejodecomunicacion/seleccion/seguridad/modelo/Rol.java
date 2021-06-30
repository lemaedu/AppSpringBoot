package ec.gob.consejodecomunicacion.seleccion.seguridad.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import ec.gob.consejodecomunicacion.seleccion.generico.EntidadAuditable;
import javax.persistence.FetchType;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the tb_role database table.
 *
 */
@XmlRootElement
@Setter
@Getter

@Entity
@Table(name = "tb_role", schema = "public")
@NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r")
@AttributeOverrides({
    @AttributeOverride(name = "estado", column = @Column(name = "role_status")),
    @AttributeOverride(name = "fechaCrea", column = @Column(name = "date_create")),
    @AttributeOverride(name = "fechaActualiza", column = @Column(name = "date_update")),
    @AttributeOverride(name = "usuarioCrea", column = @Column(name = "user_create")),
    @AttributeOverride(name = "usuarioActualiza", column = @Column(name = "user_update"))})

public class Rol extends EntidadAuditable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;

    @Column(name = "role_description",length = 100)
    private String descripcion;

    @Column(name = "role_name",length = 100,unique = true)
    private String nombre;

    // bi-directional many-to-one association to MenuRol
    @OneToMany(mappedBy = "rol",fetch = FetchType.LAZY)
    private List<MenuRol> menuRoles;

    // bi-directional many-to-one association to RolUsuario
    @OneToMany(mappedBy = "rol",fetch = FetchType.LAZY)
    private List<RolUsuario> rolUsuarios;

    public Rol() {
    }

}
