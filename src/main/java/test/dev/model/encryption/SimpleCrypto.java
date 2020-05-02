package test.dev.model.encryption;

import java.security.Key;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class SimpleCrypto implements Crypto {
	
	private static final String ALGORITHME = "AES";
	private String keyValue;
	private String salt;
	
	public SimpleCrypto(String key, String salt) {
		this.keyValue = key;
		this.salt = salt;
	}

	@Override
	public String encrypt(String data) throws Exception{
	 try
	    {
		 	byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		 	IvParameterSpec ivspec = new IvParameterSpec(iv);         
		 	SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		 	KeySpec spec = new PBEKeySpec(keyValue.toCharArray(), salt.getBytes(), 65536, 256);
		 	SecretKey tmp = factory.generateSecret(spec);
		 	SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");         
		 	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		 	cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
		 	return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
	    } catch (Exception e) {
	    	System.out.println("Error while encrypting: " + e.toString());
	    }
	    return null;
	}

	@Override
	public String decrypt(String data) throws Exception{
		 try
		    {
			 	byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		        IvParameterSpec ivspec = new IvParameterSpec(iv);
		         
		        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		        KeySpec spec = new PBEKeySpec(keyValue.toCharArray(), salt.getBytes(), 65536, 256);
		        SecretKey tmp = factory.generateSecret(spec);
		        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
		         
		        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
		        return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
		    } 
		    catch (Exception e) {
		        System.out.println("Error while decrypting: " + e.toString());
		    }
		    return null;
	}

}
