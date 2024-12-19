package com.wallet.application.impl;

import com.wallet.application.UserService;
import com.wallet.domain.model.User;
import com.wallet.domain.repository.UserRepositoryPort;
import com.wallet.domain.valueobject.Password;
import com.wallet.dto.RegisterRequestDTO;
import com.wallet.dto.UpdateRequestDTO;
import com.wallet.dto.UserResponseDTO;
import com.wallet.infrastructure.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类，包含用户注册、登录、更新、删除等逻辑。
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositoryPort userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 用户注册
     *
     * @param registerRequest 包含注册信息的 DTO。
     * @return 用户注册结果的 DTO。
     */
    @Override
    public UserResponseDTO registerUser(RegisterRequestDTO registerRequest) {
        // 检查邮箱是否已被使用
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        // 加密密码
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        // 转换 DTO 为领域对象
        User user = new User(
                registerRequest.getUsername(),
                new Password(encodedPassword), // 包装加密后的密码
                registerRequest.getEmail()
        );

        // 保存用户
        user = userRepository.save(user);

        // 转换为响应 DTO 并返回
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail());
    }

    /**
     * 用户登录认证
     *
     * @param email    用户的邮箱。
     * @param password 用户的密码。
     * @return 成功登录后返回 JWT Token。
     */
    @Override
    public String authenticateUser(String email, String password) {
        // 根据邮箱查找用户
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 验证密码是否匹配
        if (!passwordEncoder.matches(password, user.getPassword().getValue())) {
            throw new RuntimeException("Invalid credentials");
        }

        // 生成并返回 JWT Token
        return jwtUtil.generateToken(email);
    }

    /**
     * 根据用户 ID 获取用户信息。
     *
     * @param id 用户 ID。
     * @return 包含用户信息的响应 DTO。
     */
    @Override
    public UserResponseDTO getUserById(Long id) {
        // 查找用户
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 转换为响应 DTO 并返回
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail());
    }

    /**
     * 更新用户信息
     *
     * @param id          用户 ID。
     * @param updateRequest 包含更新信息的 DTO。
     * @return 更新后的用户信息的 DTO。
     */
    @Override
    public UserResponseDTO updateUser(Long id, UpdateRequestDTO updateRequest) {
        // 查找用户
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 更新用户信息
        user.setUsername(updateRequest.getUsername());
        user.setEmail(updateRequest.getEmail());

        // 如果需要更新密码
        if (updateRequest.getPassword() != null && !updateRequest.getPassword().isBlank()) {
            String encodedPassword = passwordEncoder.encode(updateRequest.getPassword());
            user.setPassword(new Password(encodedPassword));
        }

        // 保存更新后的用户
        user = userRepository.save(user);

        // 转换为响应 DTO 并返回
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail());
    }

    /**
     * 根据用户 ID 删除用户。
     *
     * @param id 用户 ID。
     */
    @Override
    public void deleteUser(Long id) {
        // 检查用户是否存在
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        // 删除用户
        userRepository.deleteById(id);
    }
}
