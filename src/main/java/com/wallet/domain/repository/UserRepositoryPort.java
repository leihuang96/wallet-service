package com.wallet.domain.repository;

import com.wallet.domain.model.User;

import java.util.List;
import java.util.Optional;

/**
 * 作为领域层的端口，定义数据访问相关的方法，不关心具体技术实现。
 * 这遵循了依赖倒置原则（DIP），使得领域层与基础设施实现解耦。
 */
public interface UserRepositoryPort {

    /**
     * 保存用户信息。
     * @param user 要保存的用户。
     * @return 保存后的用户。
     */
    User save(User user);

    /**
     * 根据 ID 查找用户。
     * @param id 用户的唯一标识。
     * @return 包含用户信息的 Optional，如果用户不存在则返回空 Optional。
     */
    Optional<User> findById(Long id);

    /**
     * 根据邮箱查找用户。
     * @param email 用户的邮箱。
     * @return 包含用户信息的 Optional，如果用户不存在则返回空 Optional。
     */
    Optional<User> findByEmail(String email);

    /**
     * 查找所有用户。
     * @return 用户列表。
     */
    List<User> findAll();

    /**
     * 根据 ID 删除用户。
     * @param id 用户的唯一标识。
     */
    void deleteById(Long id);

    /**
     * 检查是否存在给定邮箱的用户。
     * @param email 用户的邮箱。
     * @return 如果存在返回 true，否则返回 false。
     */
    boolean existsByEmail(String email);

    /**
     * 检查是否存在给定 ID 的用户。
     * @param id 用户的唯一标识。
     * @return 如果存在返回 true，否则返回 false。
     */
    boolean existsById(Long id);
}
