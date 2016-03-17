package it.uniroma3.crypt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;

/* Class AesCrypt
 * Func: Crypt a password to protect sensible data, like db passwords
 * Input: Configuration file, config.in [LAYOUT : IV \n KEY \n PASSWORD]
 * Output: Encrypted or decrypted password from the config file*/

public class AesCrypt {
	private String iv;
	private String key;
	private IvParameterSpec ivs;
	private SecretKeySpec sk;
	private Cipher aes;
	private String path = "C:/Users/Marco/git/Crypt/SIW - JDBC exercise 1/src/it/uniroma3/crypt/config.in";
	private String value1;
	
	final static Logger log = Logger.getLogger(AesCrypt.class);
	
	public AesCrypt(){
		log.debug("> CreatingCrypterInstance");
		read(this.path);
		try{
			this.ivs = new IvParameterSpec(iv.getBytes("UTF-8"));
			this.sk = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			this.aes = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			log.debug("	> CreateCryptClass = " + this);
		}catch (Exception e){
			log.error(e);
			throw new RuntimeException("AES-Setup Error");
		}
	}

	private void read(String path) {
		try {
			BufferedReader file = new BufferedReader(new FileReader(path));
			iv = file.readLine();
			key = file.readLine();
			value1 = file.readLine();
			file.close();
			log.debug("	> ReadCryptConfig = " + file);
		} catch (FileNotFoundException e) {
			log.error(e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		}
	}

	public String encrypt(String input){
		try{
			this.aes.init(Cipher.ENCRYPT_MODE, this.sk, this.ivs);	
			byte[] res = aes.doFinal(input.getBytes());
			log.debug("	> Encrypt = " + this.aes);
			return DatatypeConverter.printBase64Binary(res);
		}catch (Exception e){
			log.error(e);
			throw new RuntimeException("AES-Encrypt Error");
		}
	}
	
	public String decrypt(String input){
		try{
			this.aes.init(Cipher.DECRYPT_MODE, this.sk, this.ivs);
			byte[] res = aes.doFinal(DatatypeConverter.parseBase64Binary(input));
			log.debug("	> Decrypt = " + this.aes);
			return new String(res);
		}
		catch (Exception e){
			log.error(e);
			throw new RuntimeException("AES-Decrypt Error");
		}
	}

	/* Return the encrypted value */
	public String getValue1() {
		return decrypt(value1);
	}
}
