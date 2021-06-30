package ec.gob.consejodecomunicacion.seleccion.seguridad.modelo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import ec.gob.consejodecomunicacion.seleccion.generico.EntidadAuditable;
import ec.gob.consejodecomunicacion.seleccion.generico.EntidadBase;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Filter;
import org.hibernate.validator.constraints.Email;

/**
 * The persistent class for the tb_user database table.
 *
 */
@Log4j2
@Setter
@Getter
@XmlRootElement
@Entity
@Table(name = "tb_user", schema = "public")
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")

@AttributeOverrides({
    @AttributeOverride(name = "estado", column = @Column(name = "user_status")),
    @AttributeOverride(name = "fechaCrea", column = @Column(name = "date_create")),
    @AttributeOverride(name = "fechaActualiza", column = @Column(name = "date_update")),
    @AttributeOverride(name = "usuarioCrea", column = @Column(name = "user_create")),
    @AttributeOverride(name = "usuarioActualiza", column = @Column(name = "user_update"))})

@Filter(name = EntidadBase.FILTER_ACTIVE, condition = "user_status = 'TRUE'")
public class Usuario extends EntidadAuditable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "user_date_expiration")
    private Timestamp usuarioFechaExpira;

    @Column(name = "user_docu_id")
    private String docuId;

    @Column(name = "user_link")
    private String link;

    @NotNull //Campo no puede ser vacio
    @Email(message = "Ingrese una dirección de email valida")
    @Column(name = "user_mail", length = 100)
    private String correo;

    @NotNull
    @Column(name = "user_name", length = 10, unique = true)
    private String username;

    @NotNull
    @Column(name = "user_password")
    private String contrasenia;

    @Column(name = "user_password_temp")
    private String contraseniaTemp;

    @Column(name = "user_permanent")
    private Boolean permanente;

    // bi-directional many-to-one association to RolUsuario
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private Set<RolUsuario> rolUsuarios = new HashSet<RolUsuario>();

    // bi-directional many-to-one association to Persona
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "peop_id", referencedColumnName = "peop_id", nullable = false)
    private Persona personsa;

    public Usuario() {
    }

    public static boolean isUserInRole(Usuario usuario, String roleName) {
        try {
            Set<RolUsuario> roles = usuario.getRolUsuarios();
            if (roles != null) {
                for (RolUsuario rolUsuario : roles) {
                    if (rolUsuario.getRol().getNombre()
                            .equalsIgnoreCase(roleName)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error al recuperar el rol", e);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Rol)) {
            return false;
        }
        Usuario other = (Usuario) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

}
