/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.mycompany.myapp.Views;

import com.codename1.components.FloatingHint;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.ActivateForm;
import com.mycompany.myapp.BaseForm;
import com.mycompany.myapp.Models.BackendResponse;
import com.mycompany.myapp.Models.LoginResponse;
import com.mycompany.myapp.Models.Utilisateur;
import com.mycompany.myapp.NewsfeedForm;
import com.mycompany.myapp.Services.UserService;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {

    UserService userService;

    public SignUpForm(Resources res) {
        super(new BorderLayout());
        userService = new UserService();

        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");

        TextField nom = new TextField("", "Nom", 20, TextField.ANY);
        TextField prenom = new TextField("", "Prenom", 20, TextField.ANY);
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField telephone = new TextField("", "Telephone", 20, TextField.ANY);
        TextField cin = new TextField("", "CIN", 20, TextField.ANY);

        nom.setSingleLineTextArea(false);
        prenom.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        telephone.setSingleLineTextArea(false);
        cin.setSingleLineTextArea(false);

        Button signUp = new Button("Sign Up");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");

        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(prenom),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(telephone),
                createLineSeparator(),
                new FloatingHint(cin),
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                signUp,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        signUp.requestFocus();
        signUp.addActionListener(e -> {

            Utilisateur user = new Utilisateur(
                    0, nom.getText(), prenom.getText(),
                    email.getText(), password.getText(),
                    telephone.getText(), cin.getText(),
                    ""
            );

            String message = controleSaisie(user);

            if (!message.equals("valid")) {
                ToastBar.showMessage(message, FontImage.MATERIAL_INFO);
                return;
            }

            BackendResponse response = userService.signup(user);
            if (response.getStatus().equals("error")) {
                ToastBar.showMessage(response.getMessage(), FontImage.MATERIAL_INFO);
            } else {
                ToastBar.showMessage(response.getMessage(), FontImage.MATERIAL_INFO);
                new SignInForm(res).show();
            }
        });
    }

    public boolean isEmailValid(String email) {
        if (email != null && email.length() > 0) {
            int atIndex = email.indexOf('@');
            int dotIndex = email.lastIndexOf('.');
            if (atIndex > 0 && dotIndex > atIndex && dotIndex < email.length() - 1) {

                return true;
            }
            return false;
        }
        return false;
    }

    public String controleSaisie(Utilisateur user) {

        if (user.getNom().isEmpty()) {
            return "nom est obligatoire";
        }
        if (user.getPrenom().isEmpty()) {
            return "prenom est obligatoire";
        }
        if (user.getEmail().isEmpty()) {
            return "email est obligatoire";
        }
        if (!isEmailValid(user.getEmail())) {
            return "email n'est pas valide";
        }

        if (user.getPassword().isEmpty()) {
            return "mot de passe est obligatoire";
        }

        if (user.getTelephone().isEmpty()) {
            return "telephone est obligatoire";
        }

        if (user.getCin().isEmpty()) {
            return "cin est obligatoire";
        }

        if (user.getCin().length() > 8) {
            return "CIN est compos?? de 8 chiffres";
        }

        return "valid";

    }

}
