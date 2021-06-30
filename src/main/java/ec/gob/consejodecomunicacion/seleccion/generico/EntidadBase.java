/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.consejodecomunicacion.seleccion.generico;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.FilterDef;

/**
 * POJO Entidad base de la que heredan todas las entidades del negocio
 *
 * @author Luis Lema
 *
 */
@MappedSuperclass
@FilterDef(name = EntidadBase.FILTER_ACTIVE)
public abstract class EntidadBase implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public static final String FILTER_ACTIVE = "filterActive";

    @Getter
    @Setter
    protected Boolean estado = true;

    @Getter
    @Setter
    @Transient
    private boolean seleccionado;

    public abstract Integer getId();

    public abstract void setId(Integer id);

    public boolean isPersisted() {
        return getId() != null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        EntidadBase base = (EntidadBase) obj;
        if (this.getId() == null && base.getId() == null) {
            return super.equals(obj);
        }
        if (this.getId() == null || base.getId() == null) {
            return false;
        }
        return this.getId().equals(base.getId());
    }

}
