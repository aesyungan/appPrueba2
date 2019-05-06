/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ayungan.appprueba2.controller;

import com.ayungan.appprueba2.ejb.RolFacadeLocal;
import com.ayungan.appprueba2.ejb.UsuarioFacadeLocal;
import com.ayungan.appprueba2.model.Rol;
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
import org.primefaces.PrimeFaces;

/**
 *
 * @author Alex
 */
@Named
@ViewScoped
public class UsuarioController implements Serializable {

    @EJB
    private RolFacadeLocal rolFacadeLocal;
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    @Inject
    private Usuario usuario;
    private List<Usuario> LstUsuario;
    private List<Rol> LstRol;
    @Inject
    private Rol rol;
    private boolean isEdit;

    @PostConstruct
    private void init() {
        LstRol = rolFacadeLocal.findAll();
        LstUsuario = usuarioFacadeLocal.findAll();
    }

    public RolFacadeLocal getRolFacadeLocal() {
        return rolFacadeLocal;
    }

    public void setRolFacadeLocal(RolFacadeLocal rolFacadeLocal) {
        this.rolFacadeLocal = rolFacadeLocal;
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

    public List<Usuario> getLstUsuario() {
        return LstUsuario;
    }

    public void setLstUsuario(List<Usuario> LstUsuario) {
        this.LstUsuario = LstUsuario;
    }

    public List<Rol> getLstRol() {
        return LstRol;
    }

    public void setLstRol(List<Rol> LstRol) {
        this.LstRol = LstRol;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public boolean isIsEdit() {
        return isEdit;
    }

    public void setIsEdit(boolean isEdit) {
        this.isEdit = isEdit;
    }

    public void openModalEdit(Usuario u) {
        usuario = u;
        isEdit = true;
        rol = new Rol();
        rol.setCodigo(u.getRol().getCodigo());
        PrimeFaces.current().executeScript("PF('nuevo').show()");
    }

    public void openModalNuevo() {
        usuario = new Usuario();
        isEdit = false;
        PrimeFaces.current().executeScript("PF('nuevo').show()");

    }

    public void nuevo() {
        try {
            usuario.setRol(rol);
            if (isEdit) {
                usuarioFacadeLocal.edit(usuario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Update", "Correcto"));

            } else {
                usuarioFacadeLocal.create(usuario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Insert", "Correcto"));
            }
            usuario = new Usuario();
            LstUsuario = usuarioFacadeLocal.findAll();
//init();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insert", "" + e.getMessage()));
            System.out.println("com.ayungan.appprueba2.controller.UsuarioController.nuevo()");
        }
    }

}
