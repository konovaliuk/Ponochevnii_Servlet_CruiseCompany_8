package ua.epam.poject.cruise.util;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class PasswordEncryptor {

	private static final Logger LOGGER = Logger.getLogger(PasswordEncryptor.class);

	private static final String ALGORITHM = "SHA-1";
	private static final String CHARSET = "UTF-8";

	public String encode(String password){
		try {
			MessageDigest md = MessageDigest.getInstance(ALGORITHM);
			md.reset();
			byte[] result = md.digest(password.getBytes(CHARSET));
			StringBuffer sb = new StringBuffer(40);
			for (int i = 0; i < result.length; i++) {
				sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			LOGGER.error("Failed to encrypt password ", e);
		}
		return "";
	}

}
