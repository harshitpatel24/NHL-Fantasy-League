package com.nhlFantasy.encryption;


import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * 
 * @author erulsal
 * 
 */
public class DESEncryptDecrypt {

    static String encryptionSchema = "";
    static String unicode_format = "UTF8";
    
    static {
        encryptionSchema = "DES";
        unicode_format = "UTF8";
    }

    private SecretKey generateKey() {

        SecretKey desKey = null;

        KeySpec myKeySpec;
        SecretKeyFactory mySecretKeyFactory;
        byte[] keyAsBytes;
        String myEncryptionKey;
        myEncryptionKey = "$$$SRFSecret@@@Encryption@@@Key$$$";

        try {
            keyAsBytes = myEncryptionKey.getBytes(unicode_format);
            myKeySpec = new DESKeySpec(keyAsBytes);
            mySecretKeyFactory = SecretKeyFactory.getInstance(encryptionSchema);

            desKey = mySecretKeyFactory.generateSecret(myKeySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return desKey;
    }

    public String decryptValue(String valueToDecrypt) {
        String decryptedValue = "";

        Cipher desCipher;
        try {
            SecretKey key = generateKey();
            desCipher = Cipher.getInstance(encryptionSchema);
            desCipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = DatatypeConverter.parseBase64Binary(valueToDecrypt);
            byte[] textDecrypted;
            textDecrypted = desCipher.doFinal(encryptedText);
            decryptedValue = new String(textDecrypted);
            return decryptedValue;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return decryptedValue;
    }
    
    public String encryptValue(String valueToEncrypt) throws Exception {
        SecretKey key = generateKey();
        Cipher desCipher;
        try {
            desCipher = Cipher.getInstance(encryptionSchema);
            desCipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] text = valueToEncrypt.getBytes();
            byte[] textEncrypted = desCipher.doFinal(text);

            return DatatypeConverter.printBase64Binary(textEncrypted);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw e;
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            throw e;
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw e;
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            throw e;
        } catch (BadPaddingException e) {
            e.printStackTrace();
            throw e;
        }
    }


//public static void main(String args[]) {
//	
//	try {
//		DESEncryptDecrypt des = new DESEncryptDecrypt();
//		des.generateKey();
//		String encrp1 = des.encryptValue("hello");
//		String encrp2 = des.encryptValue("hello");
//		String decrp1 = des.decryptValue(encrp1);
//		String decrp2 = des.decryptValue(encrp1);
//		System.out.println("encrypted valye =" + encrp1 + "    "+ encrp2 + "   decrypted val = " + decrp1 + "    " + decrp2 );
//	}catch(Exception e) {
//		e.printStackTrace();
//	}
//		
//	}
}
