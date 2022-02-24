package com.taogen.example.wecom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class WeComAccessTokenServiceTest {

    @Autowired
    private WeComAccessTokenService weComAccessTokenService;

    @Value("${app.wecom.tokenCache.expiredTimeInSecond}")
    private Long accessTokenExpiredTimeInSecond;

    @Test
    void getAccessToken() throws IOException {
        String accessToken = weComAccessTokenService.getAccessToken();
        assertNotNull(accessToken);
        String accessToken2 = weComAccessTokenService.getAccessToken();
        assertEquals(accessToken, accessToken2);
    }

    @Test
    void testCacheExpiredTime() throws InterruptedException, IOException {
//        String accessToken = weComAccessTokenService.getAccessToken();
//        Thread.sleep((accessTokenExpiredTimeInSecond - 1) * 1000);
//        String accessToken2 = weComAccessTokenService.getAccessToken();
    }

    @Test
    void isAccessTokenValid() throws IOException {
        weComAccessTokenService.removeAccessTokenFromCache();
        boolean valid = weComAccessTokenService.isAccessTokenValid();
        assertFalse(valid);
        weComAccessTokenService.getAccessToken();
        valid = weComAccessTokenService.isAccessTokenValid();
        assertTrue(valid);
    }

    @Test
    void removeAccessTokenFromCache() throws IOException {
        weComAccessTokenService.getAccessToken();
        boolean valid = weComAccessTokenService.isAccessTokenValid();
        assertTrue(valid);
        weComAccessTokenService.removeAccessTokenFromCache();
        valid = weComAccessTokenService.isAccessTokenValid();
        assertFalse(valid);
    }

}
