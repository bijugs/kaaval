package com.asquareb.kaaval;

/**
 * The function of this class is to encrypt a string using password based 
 * encryption. Requires some implementation of Base64 encoder/decoder like
 * org.apache.commons.codec package available in Apache commons-codec-x.x.jar
 */

import java.io.*;
import java.util.Random;
import java.net.InetAddress;
import java.net.NetworkInterface;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.*;

/**
 * Java class to create a key file during encryption. The key file is required to
 * decrypt the value in an encrypted form
 */
class MachineKey implements Serializable {

	private static final long serialVersionUID = 1L;

	protected SecretKey yek = null;

	protected String api = null;
	
	protected String macad = null;

	protected byte[] tlas = null;

	protected int eti;
}

/**
 * Class to encrypt and decrypt strings. Uses PBE based crypto to do encrypt and
 * decrypt. This can be run as a command line utility and the methods can be
 * individually called from a JAVA program
 * Need to have an implementation of Base64 CODEC in the class path. For e.g the 
 * commons-codec-1.5.jar file from Apache.
 */
public class ProtectedConfigFile {

	/**
	 * Variable to store the password entered by the user to encrypt the
	 * password string. It need to be converted into a Character array for to be
	 * used by the encrypt method
	 */
	private static char[] password;

	/**
	 * Byte array of length 8 to be used as SALT for PBE based encryption and
	 * decryption methods
	 */
	private static byte[] salt = new byte[8];

	/**
	 * Main method to execute as a command line utility. Provides the
	 * option to encrypt a string, decrypt a encrypted string or exit from the
	 * program
	 */
	public static void main(String[] args){
		String originalPassword = null;
		String encryptedPassword = null;
		try {
			final BufferedReader cin = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.print("Enter E - Encrypt,D - Decrypt,X - Exit :");
			final String choice = cin.readLine();
			final Random rand = new Random();
			String app = null;
			if (choice.equalsIgnoreCase("E")) {
				System.out.print("Enter the string to encrypt :");
				originalPassword = cin.readLine();
				System.out.println("Choose a phrase of min 8 chars in length ");
				System.out.println(" Also the password should include 2 Cap,");
				System.out.println(" 2 small letters,2 numeric and 2 non alpha numeric chars");
				System.out.print("Enter a password to encrypt :");
				password = cin.readLine().toCharArray();
				PasswordRules.verifyPassword(password);
				System.out.print("Enter application code :");
				app = cin.readLine();
				app += (rand.nextInt(10000));
				encryptedPassword = encrypt(originalPassword, app);
				System.out.println("Encrypted password :" + encryptedPassword);
				System.out.println("Key stored in :" + app);
				System.out.println("*** Provide the encrypted string to app team");
				System.out.println("*** Store the key file in a secure durectory");
				System.out.println("*** Inform the key file name and location to app team");
			} else if (choice.equalsIgnoreCase("D")) {
				System.out.print("Enter the string to Decrypt :");
				encryptedPassword = cin.readLine();
				System.out.print("Enter the name of key file :");
				app = cin.readLine();
				final String decryptedPassword = decrypt(encryptedPassword, app);
				System.out.print("Decrypted password :" + decryptedPassword);
			}
			return;
		} catch (KaavalException e) {
			e.PrintProtectException();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to encrypt a string. Accepts the string to be encrypted and the
	 * name of the file to store the key vale which can be used for decryption
	 */
	public static String encrypt(String property, String app)
			throws IOException, KaavalException {

		InetAddress ip = null;
		String ipAddress = null;
		ObjectOutputStream os = null;
		NetworkInterface macAddress = null;
		byte[] macId = null;
		Cipher pbeCipher = null;
		Random rand = new Random();
		rand.nextBytes(salt);
		try {
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("PBEWithMD5AndDES");
			SecretKey key = keyFactory.generateSecret(new PBEKeySpec(password));
			ip = InetAddress.getLocalHost();
			ipAddress = ip.getHostAddress();
			macAddress = NetworkInterface.getByInetAddress(ip);
			macId = macAddress.getHardwareAddress();
			MachineKey mKey = new MachineKey();
			mKey.api = ipAddress;
			mKey.macad = new String(macId);
			mKey.yek = key;
			mKey.tlas = salt;
			mKey.eti = rand.nextInt(1000);
			os = new ObjectOutputStream(new BufferedOutputStream(
					new FileOutputStream(app)));
			os.writeObject(mKey);
			os.close();
			pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
			pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(salt,
					mKey.eti));
			return base64Encode(pbeCipher.doFinal(property.getBytes()));
		} catch (IOException e) {
			throw new KaavalException(1, "Error in key file during encryption", e);
		} catch (Exception e) {
			throw new KaavalException(2, "Errors during encryption", e);
		} finally {
			if (os != null)
				os.close();
		}
	}
	/**
	 * Method to encode bytes to strings.
	 * Uses Apache implementation of Base 64 CODEC
	 */
	private static String base64Encode(byte[] bytes) {
		return Base64.encodeBase64String(bytes);
	}

	/**
	 * Method to decrypt a string. Accepts the string to be decrypted and the
	 * name of the file which stores the key values
	 */
	public static String decrypt(String property, String app)
			throws IOException, KaavalException {
		SecretKey key = null;
		ObjectInputStream is = null;
		MachineKey mKey = null;
		int eti = 0;
		Cipher pbeCipher = null;
		InetAddress ip = InetAddress.getLocalHost();
		NetworkInterface macAddress = NetworkInterface.getByInetAddress(ip);
		byte[] macId = macAddress.getHardwareAddress();
		try {
			is = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(app)));
			mKey = (MachineKey) is.readObject();
			key = mKey.yek;
			salt = mKey.tlas;
			eti = mKey.eti;
			String ipa = ip.getHostAddress();
			if (!ipa.equals(mKey.api) || !new String(macId).equals(mKey.macad))
				throw new KaavalException(5,"Key file is not for this machine");
			is.close();
			pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
			pbeCipher.init(Cipher.DECRYPT_MODE, key,
					new PBEParameterSpec(salt, eti));
			return new String(pbeCipher.doFinal(base64Decode(property)));
		} catch (IOException e) {
			throw new KaavalException(3, "Error in reading key file during decryption", e);
		} catch (KaavalException e) {
			throw e;
		} catch (Exception e) {
			throw new KaavalException(4, "Error during decryption", e);
		} finally {
			if (is != null)
				is.close();
		}
	}
	
	/**
	 * Method to decode string to bytes.
	 * Uses Apache implementation of Base 64 CODEC
	 */
	private static byte[] base64Decode(String property) throws IOException {
		return new Base64().decode(property);
	}
}