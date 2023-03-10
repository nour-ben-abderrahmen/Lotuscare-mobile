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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.Models.Publication;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
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
    public static boolean resultOk = true;
    private ConnectionRequest req;
    
    public static PublicationService getInstance(){
       if(instance == null)
       instance = new PublicationService();
       return instance;
    }

public PublicationService(){
   req = new ConnectionRequest();
}

  

     
    public void ajoutPublication(Publication publication) {
        
        String url =Statics.BASE_URL+"ajouterpublicationjson?code_pub="+publication.getCodePub()+"&contenupub="+publication.getContenuPub();
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    
    
    
     public boolean deletePublication(int id ) {
        String url = Statics.BASE_URL +"deletepubjson/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
   public ArrayList<Publication>affichagePublications() {
        ArrayList<Publication> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"allpub";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try{
                    Map<String,Object>mapActivite = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                   List<Map<String,Object>> listM = (List<Map<String,Object>>) mapActivite.get("root");
                   
                   for(Map<String, Object> obj : listM){
                       Publication a = new Publication();
                       float id = Float.parseFloat(obj.get("id").toString());
                       String CodePub = obj.get("CodePub").toString();
                      String ContenuPub = obj.get("ContenuPub").toString();
                       
                       
                       a.setId((int)id);
                       a.setCodePub(CodePub); 
                       a.setContenuPub(ContenuPub);
                       result.add(a);
                   }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
             });
       NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result; 
   }
   
   
   
    public boolean modifierPublication(Publication publication) {
        String url = Statics.BASE_URL +"updatepubjson/"+publication.getId()+"?code_pub="+publication.getCodePub()+"&contenupub="+publication.getContenuPub();
        
       // http://127.0.0.1:8000/updatepubjson/80?code_pub=655999&contenupub=student.name@email.tn
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk;
        
    }
   
   
   
}
              
 
