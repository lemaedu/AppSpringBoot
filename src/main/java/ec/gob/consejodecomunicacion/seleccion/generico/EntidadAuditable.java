/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.consejodecomunicacion.seleccion.generico;

import java.util.Date;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author Usuario
 */
@EntityListeners({ AuditingEntityListener.class })
@MappedSuperclass
@Data
public abstract class EntidadAuditable extends EntidadBase {

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected Date fechaCrea;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date fechaActualiza;

    @CreatedBy
    protected String usuarioCrea;

    @LastModifiedBy
    protected String usuarioActualiza;

}
