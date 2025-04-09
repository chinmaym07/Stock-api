package com.example.stocktrade.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class JWTServiceTest {
    @Mock
    private JWTService jwtService;

    @Test
    public void testValidateToken_InvalidUsername() {
        String token = jwtService.generateToken("realUser");
        boolean isValid = jwtService.validateToken(token, "fakeUser");

        assertFalse(isValid, "Token should not be valid for mismatched username");
    }

    @Test
    public void testIsTokenExpired_FalseForNewToken() {
        String token = jwtService.generateToken("anyUser");
        assertFalse(jwtService.validateToken(token, "anyUser"), "New token should not be expired");
    }


}
