package ec.gob.consejodecomunicacion.seleccion.seguridad.modelo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

/**
 * The persistent class for the tb_access_auditory database table.
 *
 */
@Data
@Entity
@Table(name = "tb_access_auditory", schema = "public")
@NamedQuery(name = "AditoriaAcceso.findAll", query = "SELECT a FROM AditoriaAcceso a")
public class AditoriaAcceso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "access_id")
    private Integer id;

    @Column(name = "access_active")
    private Boolean activo;

    @Column(name = "access_browser", length = 100)
    private String navegador;

    @Column(name = "access_ip", length = 50)
    private String ip;

    @Column(name = "access_login")
    private Timestamp acceso;

    @Column(name = "access_logout")
    private Timestamp cerrarSesion;

    @Column(name = "access_number_login")
    private Integer numberacceso;

    @Column(name = "access_session", length = 100)
    private String session;

    @Column(name = "access_status")
    private Boolean estado;

    @Column(name = "access_user", length = 20)
    private String usuario;

    @Column(name = "access_aplication", length = 100)
    private String aplicacion;

    public AditoriaAcceso() {
    }

}
