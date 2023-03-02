package com.twbat.blog.common.util.util.encrypt;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;
import com.twbat.blog.common.util.util.DateUtils;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/1/7 - 11:06
 * @desciption 登录密码加密解密工具
 */
public class AesUtil {

    private static final Logger AES_UTIL_LOGGER = LoggerFactory.getLogger(AesUtil.class);

    private static final String ALGORITHMS = "AES/ECB/PKCS5Padding";

    /**
     * 加密
     *
     * @param content  内容
     * @param password 密码
     * @return 加密后的结果
     */
    public static String encrypt(String content, String password) {
        try {
            // 获取密码字节数组
            byte[] raw = password.getBytes();
            // 根据密码生成AES密钥
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "res");
            // 根据指定算法ALGORITHMS自成密码器
            Cipher cipher = Cipher.getInstance(ALGORITHMS);
            // 初始化密码器,第一个参数为加密(ENCRYPT_MODE)或者解密(DECRYPT_MODE)操作,第二个参数为生成的AES密钥
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            // 获取加密内容的字节数组(设置为utf-8) 不然内容如果由中文和英文混合中文就会解密为乱码
            byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
            // 密码器加密数据
            byte[] encodeContent = cipher.doFinal(bytes);
            return Base64.encodeBase64String(encodeContent);

        } catch (Exception e) {
            AES_UTIL_LOGGER.error("{}-登录密码加密失败,errorCause: {}", DateUtils.getTime(), e);
            return null;
        }
    }

    /**
     * 解密
     *
     * @param encryptStr 需要解密的内容
     * @param decryptKey 解密key
     * @return 解密后的结果
     */
    public static String decrypt(String encryptStr, String decryptKey) {
        try {
            // 获取密码字节数组
            byte[] raw = decryptKey.getBytes();
            // 根据密码生成AES密钥
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "res");
            // 根据指定算法ALGORITHMS自成密码器
            Cipher cipher = Cipher.getInstance(ALGORITHMS);
            // 初始化密码器,第一个参数为加密(ENCRYPT_MODE)或者解密(DECRYPT_MODE)操作,第二个参数为生成的AES密钥
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            // 把密文字符串转回自问字节数组
            byte[] encodeBytes = Base64.decodeBase64(encryptStr);
            // 密码器解密数据
            byte[] bytes = cipher.doFinal(encodeBytes);
            // 将解密后的数据转换为字符串返回
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            AES_UTIL_LOGGER.error("{}-登录密码解密失败,errorCause: {}", DateUtils.getTime(), e);
            return null;
        }
    }

    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }


    public static void main(String[] args) throws NoSuchAlgorithmException {
        SecretKey key = generateKey();
        String decrypt = AesUtil.encrypt(key.toString(), "qer");
        System.out.println(decrypt);
    }
}
