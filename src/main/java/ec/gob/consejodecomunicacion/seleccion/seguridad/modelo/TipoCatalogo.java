package ec.gob.consejodecomunicacion.seleccion.seguridad.modelo;

import ec.gob.consejodecomunicacion.seleccion.generico.EntidadAuditable;
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

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the rpm_general_catalogue database table.
 *
 */
@Setter
@Getter
@Entity
@Table(name = "tb_catalogue_types", schema = "public")
@NamedQuery(name = "TipoCatalogo.findAll", query = "SELECT tc FROM TipoCatalogo tc")

@AttributeOverrides({
    @AttributeOverride(name = "estado", column = @Column(name = "cat_typ_status")),
    @AttributeOverride(name = "fechaCrea", column = @Column(name = "cat_typ_create_date")),
    @AttributeOverride(name = "fechaActualiza", column = @Column(name = "cat_typ_update_date")),
    @AttributeOverride(name = "usuarioCrea", column = @Column(name = "cat_typ_create_user")),
    @AttributeOverride(name = "usuarioActualiza", column = @Column(name = "cat_typ_update_user"))})
public class TipoCatalogo extends EntidadAuditable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_typ_id")
    private Integer id;

    @Column(name = "cat_typ_name", length = 100, unique = true)
    private String nombre;

    @Column(name = "cat_typ_description")
    private String descripcion;

    @Column(name = "cat_typ_order")
    private Integer orden;

    public TipoCatalogo() {
    }

}
