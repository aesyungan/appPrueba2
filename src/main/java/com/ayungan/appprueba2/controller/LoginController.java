/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayungan.appprueba2.controller;

import com.ayungan.appprueba2.ejb.UsuarioFacadeLocal;
import com.ayungan.appprueba2.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Alex
 */
@Named
@ViewScoped
public class LoginController implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    @Inject
    private Usuario usuario;

    @PostConstruct
    private void init() {

    }

    public String login() {
        String url = "";
        try {
            System.out.println("login...............");
            List<Usuario> lst = usuarioFacadeLocal.logear(usuario);
            if (!lst.isEmpty()) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", lst.get(0));
                url = "admin?faces-redirect=true";
// FacesContext.getCurrentInstance().getExternalContext().redirect("admin.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login", "usuario o clave incorecta"));

            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "" + e.getMessage()));
        }
        return url;
    }

    public UsuarioFacadeLocal getUsuarioFacadeLocal() {
        return usuarioFacadeLocal;
    }

    public void setUsuarioFacadeLocal(UsuarioFacadeLocal usuarioFacadeLocal) {
        this.usuarioFacadeLocal = usuarioFacadeLocal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
