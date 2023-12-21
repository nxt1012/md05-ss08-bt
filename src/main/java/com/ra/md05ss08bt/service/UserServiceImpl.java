package com.ra.md05ss08bt.service;

import com.ra.md05ss08bt.model.Role;
import com.ra.md05ss08bt.model.User;
import com.ra.md05ss08bt.payload.request.LoginRequest;
import com.ra.md05ss08bt.payload.request.SignupRequest;
import com.ra.md05ss08bt.payload.response.UserInforResponse;
import com.ra.md05ss08bt.repository.RoleRepository;
import com.ra.md05ss08bt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public boolean login(LoginRequest user) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        } catch (UsernameNotFoundException e) {
            return false;
        }
    }

    @Override
    public UserInforResponse register(SignupRequest newUser) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById(3L).orElseThrow(() -> new RuntimeException("Không tìm thấy role đã chọn")));
        User user = User
                .builder()
                .username(newUser.getUsername())
                .email(newUser.getEmail())
                .password(passwordEncoder.encode(newUser.getPassword()))
                .roles(roles)
                .build();
        User savedUser = userRepository.save(user);
        return UserInforResponse
                .builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .roles(savedUser.getRoles())
                .build();
    }

}
