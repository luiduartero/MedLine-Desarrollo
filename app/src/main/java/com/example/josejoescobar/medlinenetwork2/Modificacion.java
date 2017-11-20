package com.example.josejoescobar.medlinenetwork2;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by eclipse on 4/09/17.
 */

public class Modificacion {
    public boolean soloLetrasYNumeros(String input){
        return input.matches("[a-zA-Z0-9 ]*");
    }
    public boolean soloCorreo(String input){
        return input.matches("[a-zA-Z0-9@. ]*");
    }

    public String encryptPass(String pass){
        String strData="";
        String strKey = "MedlineNetwork123";
        try {
            SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
            Cipher cipher=Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
            byte[] encrypted=cipher.doFinal(pass.getBytes());
            strData=new String(encrypted);

        } catch (Exception e) {
            System.out.println("ERROR: Encriptando Contraseña (encryptPass)");
        }
        return strData;
    }

    public String soloLetrasYNumReplace(String input){
        if (input == null) {
            System.out.println("INTENTADO DE REPLACE NULL");
            return null;
        }
        String replaced = input.replaceAll("[a-zA-Z0-9]*", "");
        return replaced;
    }
    public String dejarComas(String input){
        if (input == null) {
            System.out.println("INTENTADO DE REPLACE NULL");
            return null;
        }
        String replaced = input.replaceAll("[,a-zA-Z0-9]*", "");
        return replaced;
    }
}
