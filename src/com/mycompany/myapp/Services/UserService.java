/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.Models.BackendResponse;
import com.mycompany.myapp.Models.LoginResponse;
import com.mycompany.myapp.Models.Utilisateur;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author NOUR
 */
public class UserService {

    public ArrayList<Utilisateur> utilisateurs;
    public Utilisateur utilisateur;
    public LoginResponse loginResponse;
    public BackendResponse backendResponse;

    public static Utilisateur connectedUser = new Utilisateur();

    public static UserService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public UserService() {
        req = new ConnectionRequest();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    //parse utilisateurs
    public ArrayList<Utilisateur> parseUsers(String jsonText) {
        try {
            utilisateurs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> UsersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) UsersListJson.get("root");

            //parsing json in individual users 
            for (Map<String, Object> obj : list) {
                Utilisateur utilisateur = new Utilisateur();

                float id = Float.parseFloat(obj.get("id").toString());
                utilisateur.setId((int) id);

                utilisateur.setNom(obj.get("nom").toString());
                utilisateur.setPrenom(obj.get("prenom").toString());
                utilisateur.setEmail(obj.get("email").toString());
                utilisateur.setPassword(obj.get("password").toString());
                utilisateur.setTelephone(obj.get("telephone").toString());

                utilisateur.setCin(obj.get("cin").toString());
                utilisateur.setImage(obj.get("image").toString());

                utilisateurs.add(utilisateur);

            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing utilisateurs ");
        }

        return utilisateurs;
    }

    //parse publication
    public Utilisateur parseUser(Map<String, Object> userMap) {
        Utilisateur user = new Utilisateur();

        float id = Float.parseFloat(userMap.get("id").toString());
        user.setId((int) id);
        user.setNom(userMap.get("nom").toString());
        user.setPrenom(userMap.get("prenom").toString());
        user.setEmail(userMap.get("email").toString());
        user.setPassword(userMap.get("password").toString());
        user.setTelephone(userMap.get("telephone").toString());
        user.setCin(userMap.get("cin").toString());

        float verified = Float.parseFloat(userMap.get("verified").toString());
        user.setVerified((int) verified);
        user.setImage(userMap.get("image").toString());

        return user;
    }

    //parse loginresponse
    public LoginResponse parseLoginResponse(String jsonText) {
        LoginResponse loginRes = new LoginResponse();
        try {
            JSONParser parser = new JSONParser();
            Map<String, Object> response = parser.parseJSON(new CharArrayReader(jsonText.toCharArray()));//convert jsonText of type string into real json

            loginRes.setStatus(response.get("status").toString());
            loginRes.setMessage(response.get("message").toString());

            if (response.containsKey("user")) {
                Map<String, Object> userMap = (Map<String, Object>) response.get("user");
                Utilisateur parsedUser = parseUser(userMap);
                loginRes.setUser(parsedUser);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing loginResponse ");
        }

        return loginRes;
    }

    //parse backendResponse
    public BackendResponse parseBackendResponse(String jsonText) {
        BackendResponse backendRes = new BackendResponse();
        try {
            JSONParser parser = new JSONParser();
            Map<String, Object> response = parser.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            backendRes.setStatus(response.get("status").toString());
            backendRes.setMessage(response.get("message").toString());

        } catch (IOException ex) {
            System.out.println("Exception in parsing backendResponse ");
        }

        return backendRes;
    }

    //consommation du webservice getUsers
    public ArrayList<Utilisateur> getUsers() {
        String url = Statics.BASE_URL + "api/users/getUsers";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                utilisateurs = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return utilisateurs;
    }

    //consommation du webservice login
    public LoginResponse login(String email, String password) {
        String url = Statics.BASE_URL + "api/login";

        req.setUrl(url);
        req.setPost(true);
        req.addArgument("email", email);
        req.addArgument("password", password);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String res = new String(req.getResponseData());
                loginResponse = parseLoginResponse(res);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req); //send request to symfony backend
        return loginResponse;
    }

    //consommation du webservice signup
    public BackendResponse signup(Utilisateur utilisateur) {
        String url = Statics.BASE_URL + "api/signup";

        req.setUrl(url);

        req.setPost(true);
        req.addArgument("email", utilisateur.getEmail());
        req.addArgument("password", utilisateur.getPassword());
        req.addArgument("nom", utilisateur.getNom());
        req.addArgument("prenom", utilisateur.getPrenom());
        req.addArgument("telephone", utilisateur.getTelephone());
        req.addArgument("cin", utilisateur.getCin());

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String res = new String(req.getResponseData());
                System.out.println(res);
                backendResponse = parseBackendResponse(res);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return backendResponse;
    }

    public void saveConnectedUser(Utilisateur user) {
        connectedUser = user;
    }

    public void logout() {
        connectedUser = new Utilisateur();
    }

    public BackendResponse sendVerificationCode(String email) {
        
        String url = Statics.BASE_URL + "api/sendVerificationCode/"+email;
        req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                backendResponse = parseBackendResponse(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return backendResponse;
    }

    public BackendResponse verifyCode(String code) {
        String url = Statics.BASE_URL + "api/verifyCode/"+connectedUser.getEmail()+"/"+code;
        req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                backendResponse = parseBackendResponse(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return backendResponse;
    }
}
