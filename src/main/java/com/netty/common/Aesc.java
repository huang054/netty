package com.netty.common;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
/**
 *
 * @author Administrator
 *
 */
public class Aesc {

    // 加密
    public static String Encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
       if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        //byte[] raw = sKey.getBytes("utf-8");
        //SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

        return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    // 解密
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
        if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
        }
            
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            //SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new Base64().decode(sSrc);//new Hex().decode(sSrc.getBytes("utf-8"));//先用base64解密
            		
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        /*
         * 此处使用AES-128-ECB加密模式，key需要为16位。
         */
    	System.out.println(new BigDecimal(6).divide(new BigDecimal(8), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).intValue());
    	System.out.println((6/8));
        String cKey = "1234567890123456";
        // 需要加密的字串
        String cSrc = "{\r\n" + 
        		"   “opConn”: ”0”," + 
        		"   “opCode”: ”10014”," + 
        		"   “data”:{\r\n" + 
        		"         \"deviceId\":\"\",\r\n" + 
        		"         \"userId\":\"\",\r\n" + 
        		"       “baiduId”:””,\r\n" + 
        		"       “time”:””\r\n" + 
        		"}\r\n" + 
        		"}";
        System.out.println(cSrc);
        // 加密
        String enString = Aesc.Encrypt(cSrc, cKey);
        System.out.println("加密后的字串是：" + enString);
        String DeString1 = Aesc.Decrypt(enString, cKey);
        System.out.println("解密后的字串是：" + DeString1);
        // 解密
        String DeString = Aesc.Decrypt("IDxvnhTzxOHgcvTZ9Sd+P2glIGSLGRtL0czgzF9MBpwgPG+eFPPE4eBy9Nn1J34/h1FnnZGoJkfBdb1pfllkhg==", "1234567891234560");
        System.out.println("解密后的字串是：" + DeString);
    }
}

//源代码片段来自云代码http://yuncode.net