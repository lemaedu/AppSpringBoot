/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.consejodecomunicacion.seleccion.seguridad.repositorio;

import ec.gob.consejodecomunicacion.seleccion.seguridad.modelo.TipoCatalogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository
public interface TipoCatalogoRep extends JpaRepository<TipoCatalogo, Integer> {

}
