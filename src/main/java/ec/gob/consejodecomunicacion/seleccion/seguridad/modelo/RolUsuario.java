package ec.gob.consejodecomunicacion.seleccion.seguridad.modelo;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import ec.gob.consejodecomunicacion.seleccion.generico.EntidadAuditable;
import javax.persistence.FetchType;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the tb_role_user database table.
 *
 */
@Setter
@Getter

@XmlRootElement
@Entity
@Table(name = "tb_role_user", schema = "public")
@NamedQuery(name = "RolUsuario.findAll", query = "SELECT r FROM RolUsuario r")
@AttributeOverrides({
    @AttributeOverride(name = "estado", column = @Column(name = "rous_status")),
    @AttributeOverride(name = "fechaCrea", column = @Column(name = "date_create")),
    @AttributeOverride(name = "fechaActualiza", column = @Column(name = "date_update")),
    @AttributeOverride(name = "usuarioCrea", column = @Column(name = "user_create")),
    @AttributeOverride(name = "usuarioActualiza", column = @Column(name = "user_update"))})

public class RolUsuario extends EntidadAuditable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rous_id")
    private Integer id;

    // bi-directional many-to-one association to Rol
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Rol rol;

    // bi-directional many-to-one association to Usuario
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    public RolUsuario() {
    }

}
