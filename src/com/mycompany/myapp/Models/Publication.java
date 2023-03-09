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
    private String object,CodePub;
    private String object2,ContenuPub;
    private Date DatePub;
    private String object3,UrlImagePub;

    public Publication() {
    }
    
    
    
    
    
    
    

    public Publication(int id, String object, String CodePub, String object2, String ContenuPub, Date DatePub, String object3, String UrlImagePub) {
        this.id = id;
        this.object = object;
        this.CodePub = CodePub;
        this.object2 = object2;
        this.ContenuPub = ContenuPub;
        this.DatePub = DatePub;
        this.object3 = object3;
        this.UrlImagePub = UrlImagePub;
    }

    public Publication(String object, String CodePub, String object2, String ContenuPub, Date DatePub, String object3, String UrlImagePub) {
        this.object = object;
        this.CodePub = CodePub;
        this.object2 = object2;
        this.ContenuPub = ContenuPub;
        this.DatePub = DatePub;
        this.object3 = object3;
        this.UrlImagePub = UrlImagePub;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getCodePub() {
        return CodePub;
    }

    public void setCodePub(String CodePub) {
        this.CodePub = CodePub;
    }

    public String getObject2() {
        return object2;
    }

    public void setObject2(String object2) {
        this.object2 = object2;
    }

    public String getContenuPub() {
        return ContenuPub;
    }

    public void setContenuPub(String ContenuPub) {
        this.ContenuPub = ContenuPub;
    }

    public Date getDatePub() {
        return DatePub;
    }

    public void setDatePub(Date DatePub) {
        this.DatePub = DatePub;
    }

    public String getObject3() {
        return object3;
    }

    public void setObject3(String object3) {
        this.object3 = object3;
    }

    public String getUrlImagePub() {
        return UrlImagePub;
    }

    public void setUrlImagePub(String UrlImagePub) {
        this.UrlImagePub = UrlImagePub;
    }
    
    
    
}
