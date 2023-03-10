/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Views;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.BaseForm;
import com.mycompany.myapp.Models.Publication;
import com.mycompany.myapp.Services.PublicationService;
/**
 *
 * @author Omar
 */
public class ModifierPublicationForm extends BaseForm {
    
    Form current;
    public ModifierPublicationForm (Resources res , Publication p){
    
    super("Newsfeed",BoxLayout.y()); 
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("publication");
        getContentPane().setScrollVisible(false);
     
        
        super.addSideMenu(res);
        TextField CodePub = new TextField(p.getCodePub() , "CodePub" , 20 , TextField.ANY);
        TextField ContenuPub = new TextField(p.getContenuPub() , "ContenuPub" , 20 , TextField.ANY);
             CodePub.setUIID("NewsTopLine");
        ContenuPub.setUIID("NewsTopLine");
        
        
        CodePub.setSingleLineTextArea(true);
        ContenuPub.setSingleLineTextArea(true);
        
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       
       btnModifier.addPointerPressedListener(l ->   { 
           
           p.setCodePub(CodePub.getText());
           p.setContenuPub(ContenuPub.getText());
          
           
       
       
       if(PublicationService.getInstance().modifierPublication(p)) { 
           new ListPublicationsForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListPublicationsForm(res).show();
       });
       
        Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
       
       
       
       Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(CodePub),
                createLineSeparator(),
                new FloatingHint(ContenuPub),
                createLineSeparator(),
                
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
        
        
    }
        
    
}
