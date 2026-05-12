package com.kontron.qdw.ui.util;

import java.security.NoSuchAlgorithmException;

import com.kontron.util.cipher.PasswordGenerator;

import net.sourceforge.jbizmo.commons.crypto.HashGenerator;

public class PasswordHelper {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String password = PasswordGenerator.createPassword();
        String pwHash = HashGenerator.encryptSHA256(password);
        System.out.println("Passwort: " + password);
        System.out.println("pwHash:   " + pwHash);
    }

}
