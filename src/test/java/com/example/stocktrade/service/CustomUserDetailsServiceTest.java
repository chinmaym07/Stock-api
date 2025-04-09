package com.example.stocktrade.service;

import com.example.stocktrade.beans.User;
import com.example.stocktrade.beans.UserPrinciple;
import com.example.stocktrade.repo.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomUserDetailsServiceTest {
    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private UserRepo userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JWTService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;
    @Before
    public void setUp() throws Exception {
        ReflectionTestUtils.setField(customUserDetailsService, "authenticationManager", authenticationManager);
    }

    @Test
    public void testLoadUserByUsername_UserFound() {
        User user = new User();
        user.setUsername("testUser");
        when(userRepo.findByUsername("testUser")).thenReturn(user);

        UserPrinciple userDetails = (UserPrinciple) customUserDetailsService.loadUserByUsername("testUser");

        assertNotNull(userDetails);
        assertEquals("testUser", userDetails.getUsername());
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        when(userRepo.findByUsername("invalidUser")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () ->
                customUserDetailsService.loadUserByUsername("invalidUser"));
    }

    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setUsername("newUser");
        user.setPassword("plainPassword");

        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");

        User savedUser = new User();
        savedUser.setUsername("newUser");
        savedUser.setPassword("encodedPassword");

        when(userRepo.save(any(User.class))).thenReturn(savedUser);

        User result = customUserDetailsService.register(user);

        assertNotNull(result);
        assertEquals("encodedPassword", result.getPassword());
        verify(userRepo, times(1)).save(any(User.class));
    }

    @Test
    public void testVerify_ValidCredentials() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPass");

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("testUser", "wrongPass", List.of(new SimpleGrantedAuthority("ROLE_USER")));

        when(authenticationManager.authenticate(any()))
                .thenReturn(authentication);

        when(jwtService.generateToken("testUser")).thenReturn("mocked.jwt.token");

        String token = customUserDetailsService.verify(user);

        assertEquals("mocked.jwt.token", token);
    }

    @Test
    public void testVerify_InvalidCredentials() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("wrongPass");
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("testUser", "wrongPass1");
        authentication.setAuthenticated(false);

        when(authenticationManager.authenticate(Mockito.any()))
                .thenReturn(authentication);

        String result = customUserDetailsService.verify(user);

        assertEquals("Fail", result);
    }
}
