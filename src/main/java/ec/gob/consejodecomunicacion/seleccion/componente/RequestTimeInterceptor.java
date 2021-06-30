/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.consejodecomunicacion.seleccion.componente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *
 * @author LEMAEDU
 */
@Component
//public class RequestTimeInterceptor extends HandlerInterceptorAdapter {
public class RequestTimeInterceptor implements HandlerInterceptor  {

    
    private static final Log LOG=LogFactory.getLog(RequestTimeInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime=(long) request.getAttribute("startTime");
        LOG.info("Url to:'"+request.getRequestURL().toString()+"' in '"+(System.currentTimeMillis()-startTime)+"ms");
    }

}