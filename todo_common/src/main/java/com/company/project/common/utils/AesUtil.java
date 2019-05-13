package com.company.project.common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AesUtil {

	private static String KEY = "yeetong201904248";
	private static String IV = "zRosqvbxloXdlDuH";

	/**
	 * 加密方法
	 *
	 * @param data
	 *            要加密的数据
	 * @param key
	 *            加密key
	 * @param iv
	 *            加密iv
	 * @return 加密的结果
	 * @throws Exception
	 */
	public static String encrypt(String data, String key, String iv) {
		try {
			// "算法/模式/补码方式"
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			int blockSize = cipher.getBlockSize();
			byte[] dataBytes = data.getBytes();
			int plaintextLength = dataBytes.length;
			if (plaintextLength % blockSize != 0) {
				plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
			}
			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
			SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
			IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
			byte[] encrypted = cipher.doFinal(plaintext);
			return new Base64().encodeToString(encrypted).replaceAll("[+]", "^").replaceAll("\n", "").replaceAll("\r", "");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解密方法
	 *
	 * @param data
	 *            要解密的数据
	 * @param key
	 *            解密key
	 * @param iv
	 *            解密iv
	 * @return 解密的结果
	 * @throws Exception
	 */
	public static String desEncrypt(String data, String key, String iv) {
		try {
			byte[] encrypted1 = new Base64().decode(data.replaceAll("[\\^]", "+"));
			// byte[] encrypted1 = new Base64().decode(URLDecoder.decode(data, "utf-8"));
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
			IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original);
			return originalString;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 使用默认的key和iv加密
	 *
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data) {
		return encrypt(data, KEY, IV);
	}

	/**
	 * 使用默认的key和iv解密
	 *
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String desEncrypt(String data) {
		return desEncrypt(data, KEY, IV);
	}

	/**
	 * 测试
	 */
	public static void main(String args[]) {
		// 测试字符串
		String test = "dasdasdasdasdasdasda";

		// 加密方法
		String data = encrypt(test);
		// 解密方法
		System.out.println(data.length());
		System.out.println(data);
		System.out.println(desEncrypt(data));
	}

}