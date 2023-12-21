package com.ra.md05ss08bt.service;

import com.ra.md05ss08bt.model.User;
import com.ra.md05ss08bt.payload.request.LoginRequest;
import com.ra.md05ss08bt.payload.request.SignupRequest;
import com.ra.md05ss08bt.payload.response.UserInforResponse;

public interface UserService {
    boolean login(LoginRequest user);

    UserInforResponse register(SignupRequest newUser);
}
