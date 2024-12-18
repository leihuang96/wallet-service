package com.wallet.service;

import com.wallet.entity.User;

// 定义用户服务层的业务逻辑，例如用户创建、查询等操作。接口方法仅声明功能，不提供具体实现，由实现类提供实现。
public interface UserService {
    void registerUser(User user); // 创建用户
    User authenticateUser(String email, String password);
    User getUserById(Long id); // 根据用户 ID 获取用户信息。
    void updateUser(Long id, User user); // 根据用户 ID 更新用户信息。
    void deleteUser(Long id);         // 根据用户 ID 删除用户。

}
