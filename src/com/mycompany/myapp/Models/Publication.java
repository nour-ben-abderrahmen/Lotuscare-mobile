/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Models;

import java.util.Date;

/**
 *
 * @author Omar
 */
public class Publication {
    
    private int id;
    private String CodePub;
    private String ContenuPub;
    private String UrlImagePub;
    private Date DatePub;
    public Publication() {
    }

    public Publication(String CodePub, String ContenuPub) {
        this.CodePub = CodePub;
        this.ContenuPub = ContenuPub;
    }

    public Publication(int id, String CodePub, String ContenuPub) {
        this.id = id;
        this.CodePub = CodePub;
        this.ContenuPub = ContenuPub;
    }

    
    public Publication(int id, String CodePub, String ContenuPub, String UrlImagePub, Date DatePub) {
        this.id = id;
        this.CodePub = CodePub;
        this.ContenuPub = ContenuPub;
        this.UrlImagePub = UrlImagePub;
        this.DatePub = DatePub;
    }

    public Publication(String CodePub, String ContenuPub, String UrlImagePub, Date DatePub) {
        this.CodePub = CodePub;
        this.ContenuPub = ContenuPub;
        this.UrlImagePub = UrlImagePub;
        this.DatePub = DatePub;
    }

    public Publication(String CodePub, String ContenuPub, String UrlImagePub) {
        this.CodePub = CodePub;
        this.ContenuPub = ContenuPub;
        this.UrlImagePub = UrlImagePub;
    }

    public Publication(int id, String CodePub, String ContenuPub, String UrlImagePub) {
        this.id = id;
        this.CodePub = CodePub;
        this.ContenuPub = ContenuPub;
        this.UrlImagePub = UrlImagePub;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodePub() {
        return CodePub;
    }

    public void setCodePub(String CodePub) {
        this.CodePub = CodePub;
    }

    public String getContenuPub() {
        return ContenuPub;
    }

    public void setContenuPub(String ContenuPub) {
        this.ContenuPub = ContenuPub;
    }

    public String getUrlImagePub() {
        return UrlImagePub;
    }

    public void setUrlImagePub(String UrlImagePub) {
        this.UrlImagePub = UrlImagePub;
    }

    public Date getDatePub() {
        return DatePub;
    }

    public void setDatePub(Date DatePub) {
        this.DatePub = DatePub;
    }

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", CodePub=" + CodePub + ", ContenuPub=" + ContenuPub + ", UrlImagePub=" + UrlImagePub + ", DatePub=" + DatePub + '}';
    }

    
    
}
