/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Services;


import com.codename1.io.ConnectionRequest;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.Models.Publication;
import com.mycompany.myapp.utils.Statics;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Omar
 */
public class PublicationService {
    
    
    public static PublicationService instance=null;
    
    private ConnectionRequest req;
    
    public static PublicationService getInstance(){
       if(instance == null)
       instance = new PublicationService();
       return instance;
    }

public PublicationService(){
   req = new ConnectionRequest();
}

  
     //ajout 
    public void ajoutReclamation(Publication publication) {
        
        String url =Statics.BASE_URL+"/addpub?objet="+publication.getObject()+"&contenupub"+publication.getObject2()+"&UrlImagePub"+publication.getObject3(); 
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    
    public ArrayList<Publication>affichagePublications() {
        ArrayList<Publication> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/allpub";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapPublications = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapPublications.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Publication re = new Publication();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String CodePub = obj.get("codePub").toString();
                        
                        String ContenuPub = obj.get("ContenuPub").toString();
                       
                        
                        re.setId((int)id);
                        re.setCodePub(CodePub);
                        re.setContenuPub(ContenuPub);
                        //insert data into ArrayList result
                        result.add(re);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    
    
    

}
