package com.wallet.repository;

import com.wallet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// JpaRepository 提供了基本的增删改查操作。
public interface UserRepository extends JpaRepository<User, Long> {
    // findByUsername 是 Spring Data JPA 提供的 查询方法命名规则 的一部分。
    // findBy：表示查询操作，Spring Data JPA 会自动解析它为 SELECT 查询。
    // Username：是 User 实体类中的字段名称，表示查询条件。
    // Optional 是 Java 8 引入的一个容器类，用来处理 可能为 null 的值，可以避免出现 NullPointerException。
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
