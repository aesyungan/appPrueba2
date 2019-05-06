/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayungan.appprueba2.ejb;

import com.ayungan.appprueba2.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alex
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "persistencia")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public List<Usuario> logear(Usuario u) {
        List<Usuario> lst = new ArrayList<>();
        try {
            String qString = "SELECT u FROM Usuario u WHERE u.correo=?1 AND u.clave=?2";
            Query query = em.createQuery(qString);
            query.setParameter(1, u.getCorreo());
            query.setParameter(2, u.getClave());
            lst = query.getResultList();
        } catch (Exception e) {
            System.out.println("com.ayungan.appprueba2.ejb.UsuarioFacade.logear()");
            System.out.println("error->" + e.getMessage());
        }
        return lst;
    }

}
