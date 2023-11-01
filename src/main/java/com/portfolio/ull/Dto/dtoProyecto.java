package com.portfolio.ull.Dto;

import javax.validation.constraints.NotBlank;

public class dtoProyecto {
    @NotBlank
    private String nombreP;
    @NotBlank
    private String descripcionP;
    @NotBlank
    private String imgP;
    
    //Constructores

    public dtoProyecto() {
    }

    public dtoProyecto(String nombreP, String descripcionP, String imgP) {
        this.nombreP = nombreP;
        this.descripcionP = descripcionP;
        this.imgP = imgP;
    }
    //Getters & Setters

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getDescripcionP() {
        return descripcionP;
    }

    public void setDescripcionP(String descripcionP) {
        this.descripcionP = descripcionP;
    }

    public String getImgP() {
        return imgP;
    }

    public void setImgP(String imgP) {
        this.imgP = imgP;
    }

    
    
}