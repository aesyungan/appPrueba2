/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayungan.appprueba2.controller;

import com.ayungan.appprueba2.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
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
public class TemplateController implements Serializable {

    @Inject
    private Usuario usuario;

    @PostConstruct
    private void init() {
    }

    public void verificarLogin() {
        try {
            usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if (usuario == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            }
        } catch (Exception e) {
            System.out.println("com.ayungan.appprueba1.controller.TemplateController.verificarLogin()");
            System.out.println("" + e.getMessage());
        }
    }

    public String cerrar() {
        String url = "";
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
            url = "index?faces-redirect=true";
            //   FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");

        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        return url;
    }

}
