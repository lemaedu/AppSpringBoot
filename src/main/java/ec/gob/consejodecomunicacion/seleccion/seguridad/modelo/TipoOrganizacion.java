package ec.gob.consejodecomunicacion.seleccion.seguridad.modelo;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import ec.gob.consejodecomunicacion.seleccion.generico.EntidadAuditable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the tb_people database table.
 *
 */
@Setter
@Getter

@XmlRootElement
@Entity
@Table(name = "tb_organization_type", schema = "public")
@NamedQuery(name = "TipoOrganizacion.findAll", query = "SELECT to FROM TipoOrganizacion to")
@AttributeOverrides({
    @AttributeOverride(name = "estado", column = @Column(name = "org_typ_status")),
    @AttributeOverride(name = "fechaCrea", column = @Column(name = "date_create")),
    @AttributeOverride(name = "fechaActualiza", column = @Column(name = "ate_update")),
    @AttributeOverride(name = "usuarioCrea", column = @Column(name = "user_create")),
    @AttributeOverride(name = "usuarioActualiza", column = @Column(name = "user_update"))})
public class TipoOrganizacion extends EntidadAuditable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_typ_id")
    private Integer id;

    @Column(name = "org_typ_name",unique = true)
    private String nombre;

    @Column(name = "org_typ_description")
    private String descripcion;

    // bi-directional many-to-one association to Menu
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_typ_father_id")
    private TipoOrganizacion tipoOrganizacion;

    public TipoOrganizacion() {
    }

}
