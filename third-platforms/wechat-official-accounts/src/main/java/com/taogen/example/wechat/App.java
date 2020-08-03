package com.taogen.example.wechat;

import com.taogen.example.wechat.utils.EncryptUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Hello world!
 */
@SpringBootApplication
@RestController
public class App {

    private static final Logger logger = LogManager.getLogger();

    @Value("${wechat.token}")
    private String token;

    @Value("${wechat.appId}")
    private String appId;

    @Value("${wechat.appSecret}")
    private String appSecret;

    @Value("${wechat.testAppId}")
    private String testAppId;

    @Value("${wechat.testAppSecret}")
    private String testAppSecret;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        logger.debug("Access /hello");
        return "hello " + new Date();
    }

    /**
     * Validate Token
     *
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * @param timestamp 时间戳
     * @param nonce     随机数
     * @param echostr   随机字符串
     * @return 若确认此次GET请求来自微信服务器，请原样返回echostr参数内容
     */
    @GetMapping("/wx")
    public String validateToken(@RequestParam("signature") String signature,
                                @RequestParam("timestamp") String timestamp,
                                @RequestParam("nonce") String nonce,
                                @RequestParam("echostr") String echostr) {
        logger.debug("signature: {}, timestamp: {}, nonce: {}, echostr: {}",
                signature, timestamp, nonce, echostr);
        // 开发者通过检验signature对请求进行校验（下面有校验方式）。
        // 1）将token、timestamp、nonce三个参数进行字典序排序
        // 2）将三个参数字符串拼接成一个字符串进行sha1加密
        // 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        String sortedParams = getSortedParams(timestamp, nonce);
        String encryptedParams = getEncryptedParams(sortedParams);
        if (encryptedParams.equals(signature)) {
            return echostr;
        } else {
            return "error";
        }
    }

    private String getSortedParams(String timestamp, String nonce) {
        List<String> params = new ArrayList<>();
        params.add(this.token);
        params.add(timestamp);
        params.add(nonce);
        Collections.sort(params, Comparator.naturalOrder());
        StringBuilder validateString = new StringBuilder();
        for (String param : params) {
            validateString.append(param);
        }
        return validateString.toString();
    }

    private String getEncryptedParams(String sortedParams) {
        return EncryptUtils.toSHA1(sortedParams);
    }


    @GetMapping("/get_access_token")
    public String getAccessToken() {
        return null;
    }

}
