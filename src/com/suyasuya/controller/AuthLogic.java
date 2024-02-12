package com.suyasuya.controller;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.suyasuya.dao.UserDao;
import com.suyasuya.model.User;

public class AuthLogic {
	public String checkLogin(String id, String password) {
		if (id.isBlank() || password.isBlank()) {
			return "IDまたはパスワードが未入力です。";
		}
		
		
		
		try {
            UserDao userDao = new UserDao();
            User user = null;
            
            try {
            	user = userDao.findOne(id);
            } catch (ClassNotFoundException e) {
    			e.printStackTrace();
    			return "システムに異常が発生しています。システム管理者に連絡してください。";
    		} catch (SQLException e) {
    			e.printStackTrace();
    			return "データベースに異常が発生しています。システム管理者に連絡してください。";
    		}
            
            if(user == null) {
    			return "IDまたはパスワードが不正です。";
    		}
            
            // ハッシュ化されたパスワードをDBから取得したハッシュ化済みパスワードと比較する
            if(!Arrays.equals(generateHashPasswordBytes(password, user.getSalt()), user.getPassword())) {
            	return "IDまたはパスワードが不正です。";
            }         
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return "システムに異常が発生しています。";
        }
		return null;
    }

    private static byte[] generateSalt() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstanceStrong();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    private static byte[] generateHashPasswordBytes(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 600000; // ストレッチングの回数 FIPS-140準拠

        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return hash;
    }
}
