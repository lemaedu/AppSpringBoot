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
@Table(name = "tb_organization", schema = "public")
@NamedQuery(name = "Organizacion.findAll", query = "SELECT o FROM Organizacion o")
@AttributeOverrides({
    @AttributeOverride(name = "estado", column = @Column(name = "org_status")),
    @AttributeOverride(name = "fechaCrea", column = @Column(name = "org_date_create")),
    @AttributeOverride(name = "fechaActualiza", column = @Column(name = "org_date_update")),
    @AttributeOverride(name = "usuarioCrea", column = @Column(name = "org_user_create")),
    @AttributeOverride(name = "usuarioActualiza", column = @Column(name = "org_user_update"))})
public class Organizacion extends EntidadAuditable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_id")
    private Integer id;

    @Getter
    @Setter
    @Column(name = "org_ruc", length = 13, unique = true)
    private String ruc;

    @Column(name = "org_name_comercial")
    private String nombreComercial;

    @Column(name = "org_name_social")
    private String razonSocial;

    @Column(name = "org_charge_legal_representative")
    private String cargoRepresentante;

    @Column(name = "type_peop_id")
    private Integer typePeopId;

    @Getter
    @Setter
    @Column(name = "gelo_id")
    private Integer idUbicacionGeografica;

    // bi-directional many-to-one association to Usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_typ_id")
    private TipoOrganizacion tipoOrganizacion;

    @ManyToOne
    @JoinColumn(name = "peop_id")
    private Persona persona;

    public Organizacion() {
    }

}
