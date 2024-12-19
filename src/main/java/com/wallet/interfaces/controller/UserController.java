package com.wallet.interfaces.controller;

import com.wallet.application.UserService;
import com.wallet.dto.LoginRequestDTO;
import com.wallet.dto.RegisterRequestDTO;
import com.wallet.dto.UpdateRequestDTO;
import com.wallet.dto.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 调用 UserService，对外提供 REST API 接口。
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册接口
     * @param registerRequest 用户注册请求 DTO
     * @return 注册成功的用户响应 DTO
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody RegisterRequestDTO registerRequest) {
        UserResponseDTO response = userService.registerUser(registerRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * 用户登录接口
     * @param loginRequest 用户登录请求 DTO
     * @return JWT Token
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        String token = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(token);
    }

    /**
     * 获取用户详情
     * @param id 用户 ID
     * @return 用户响应 DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        UserResponseDTO response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * 更新用户信息
     * @param id 用户 ID
     * @param updateRequest 更新的用户请求 DTO
     * @return 更新后的用户响应 DTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,
                                                      @Valid @RequestBody UpdateRequestDTO updateRequest) {
        UserResponseDTO response = userService.updateUser(id, updateRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * 删除用户
     * @param id 用户 ID
     * @return HTTP 204 无内容状态
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
