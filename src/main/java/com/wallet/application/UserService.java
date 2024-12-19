package com.wallet.application;

import com.wallet.domain.model.User;
import com.wallet.dto.RegisterRequestDTO;
import com.wallet.dto.UpdateRequestDTO;
import com.wallet.dto.UserResponseDTO;

/**
 * 应用服务接口，用于定义用户相关的应用层逻辑。
 * 应用层负责编排领域逻辑，并对外暴露接口。
 */
public interface UserService {

    /**
     * 注册用户。
     *
     * @param registerRequest 包含用户注册所需信息的 DTO。
     * @return 包含用户注册结果的响应 DTO。
     */
    UserResponseDTO registerUser(RegisterRequestDTO registerRequest);

    /**
     * 用户登录验证。
     *
     * @param email    用户的邮箱。
     * @param password 用户的密码。
     * @return 登录成功后返回 JWT Token，供后续认证使用。
     */
    String authenticateUser(String email, String password);

    /**
     * 根据用户 ID 获取用户信息。
     *
     * @param id 用户 ID。
     * @return 包含用户信息的响应 DTO。
     */
    UserResponseDTO getUserById(Long id);

    /**
     * 更新用户信息。
     *
     * @param id          用户 ID。
     * @param updateRequest 包含更新信息的 DTO。
     * @return 包含更新后用户信息的响应 DTO。
     */
    UserResponseDTO updateUser(Long id, UpdateRequestDTO updateRequest);

    /**
     * 根据用户 ID 删除用户。
     *
     * @param id 用户 ID。
     */
    void deleteUser(Long id);
}
