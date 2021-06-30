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
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * The persistent class for the tb_people database table.
 *
 */
@Setter
@Getter

@XmlRootElement
@Entity
@Table(name = "tb_people", schema = "public")
@NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")
@AttributeOverrides({
    @AttributeOverride(name = "estado", column = @Column(name = "peop_status")),
    @AttributeOverride(name = "fechaCrea", column = @Column(name = "date_create")),
    @AttributeOverride(name = "fechaActualiza", column = @Column(name = "date_update")),
    @AttributeOverride(name = "usuarioCrea", column = @Column(name = "user_create")),
    @AttributeOverride(name = "usuarioActualiza", column = @Column(name = "user_update"))})
public class Persona extends EntidadAuditable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "peop_id")
    private Integer id;

    @Column(name = "peop_name")
    private String nombre;

    @Column(name = "peop_id_document", length = 25, unique = true)
    private String documento;

    @ManyToOne
    @JoinColumn(name = "gelo_id")
    private Geografia geografia;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Organizacion> organizaciones;

    // bi-directional many-to-one association to Usuario
    @OneToOne(mappedBy = "personsa", fetch = FetchType.LAZY)
    private Usuario usuario;

    public Persona() {
    }

}
