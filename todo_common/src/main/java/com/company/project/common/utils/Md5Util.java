package com.company.project.common.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;

public class Md5Util {
	public static String md5(String txt) {
		Digester md5 = new Digester(DigestAlgorithm.MD5);
		return md5.digestHex(txt);
	}

	public static String md5(String txt, String salt) {
		byte[] key = salt.getBytes();
		HMac mac = new HMac(HmacAlgorithm.HmacMD5, key);
		return mac.digestHex(txt);
	}

	public static String md5(byte[] txt) {
		Digester md5 = new Digester(DigestAlgorithm.MD5);
		return md5.digestHex(txt);
	}

	public static void main(String[] args) {
		Digester sha256 = new Digester(DigestAlgorithm.SHA1);
		System.out.println(sha256.digestHex("kepqmf7zm9qloev8k5mpsqceayyybbgw"));
		System.out.println(md5("123456", "admin_szx"));
	}
}
