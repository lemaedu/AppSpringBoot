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

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the rpm_geograp database table.
 *
 */
@Setter
@Getter
@Entity
@Table(name = "tb_geograp", schema = "public")
@NamedQuery(name = "Geografia.findAll", query = "SELECT g FROM Geografia g")
@AttributeOverrides({
    @AttributeOverride(name = "estado", column = @Column(name = "gelo_status")),
    @AttributeOverride(name = "fechaCrea", column = @Column(name = "gelo_create_date")),
    @AttributeOverride(name = "fechaActualiza", column = @Column(name = "gelo_update_date")),
    @AttributeOverride(name = "usuarioCrea", column = @Column(name = "gelo_create_user")),
    @AttributeOverride(name = "usuarioActualiza", column = @Column(name = "gelo_update_user"))})
public class Geografia extends EntidadAuditable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gelo_id")
    private Integer id;

    @Column(name = "gelo_codification_inec",length = 100)
    private String codificationInec;

    @Column(name = "gelo_name")
    private String nombre;

    @Column(name = "gelo_observations")
    private String observacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gelo_parent_id")
    private Geografia geografia;

    @Column(name = "pglo_id")
    private Integer pgloId;

    @Column(name = "zone_id")
    private Integer zonaId;

    // bi-directional many-to-one association to Media
    public Geografia() {
    }

}
