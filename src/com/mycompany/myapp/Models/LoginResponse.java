/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.Models;

/**
 *
 * @author NOUR
 */
public class LoginResponse {     

    private String status;
    private String message;
    private Utilisateur user;
    
    public LoginResponse() {
        user = new Utilisateur();
    }

    public LoginResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LoginResponse{" + "status=" + status + ",\n message=" + message + ",\n user=" + user + '}';
    }

    

    
    
    
}
