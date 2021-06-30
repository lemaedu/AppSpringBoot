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

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the rpm_general_catalogue database table.
 *
 */
@Setter
@Getter
@Entity
@Table(name = "tb_general_catalogue", schema = "public")
@NamedQuery(name = "Catalogo.findAll", query = "SELECT g FROM Catalogo g")

@AttributeOverrides({
    @AttributeOverride(name = "estado", column = @Column(name = "catalogue_status")),
    @AttributeOverride(name = "fechaCrea", column = @Column(name = "catalogue_create_date")),
    @AttributeOverride(name = "fechaActualiza", column = @Column(name = "catalogue_update_date")),
    @AttributeOverride(name = "usuarioCrea", column = @Column(name = "catalogue_create_user")),
    @AttributeOverride(name = "usuarioActualiza", column = @Column(name = "catalogue_update_user"))})
public class Catalogo extends EntidadAuditable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalogue_id")
    private Integer id;

    @Column(name = "catalogue_description", length = 100)
    private String descripcion;

    @Column(name = "catalogue_id_history")
    private Integer historial;

    @Column(name = "catalogue_name", length = 100, unique = true)
    private String nombre;

    @Column(name = "catalogue_order")
    private Integer orden;

    @Column(name = "catalogue_info", length = 100)
    private String info;

    // bi-directional many-to-one association to Catalogo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalogue_father_id")
    private Catalogo catalogoPadre;

    // bi-directional many-to-one association to Catalogo
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "catalogoPadre")
    private List<Catalogo> catalogoHijos;

    // bi-directional many-to-one association to Rol
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_typ_id")
    private TipoCatalogo tipoCatalogo;

    public Catalogo() {
    }

}
