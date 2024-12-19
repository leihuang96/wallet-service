package com.wallet.infrastructure.repository;

import com.wallet.domain.model.User;
import com.wallet.domain.repository.UserRepositoryPort;
import com.wallet.infrastructure.persistence.UserEntity;
import com.wallet.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

/**
 * 实现 UserRepositoryPort 接口，通过 JPA 访问数据库。同时利用 UserMapper 在领域模型和实体模型之间进行转换。
 * 作为六边形架构中的适配器，将基础设施层与领域层解耦。
 */
@Repository
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository jpaUserRepository; // Spring Data JPA 仓库
    private final UserMapper userMapper;

    public UserRepositoryAdapter(JpaUserRepository jpaUserRepository, UserMapper userMapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        // 将领域对象转换为实体对象进行存储
        UserEntity entity = userMapper.toEntity(user);
        UserEntity savedEntity = jpaUserRepository.save(entity);
        // 将保存的实体对象转换为领域对象返回
        // 确保所有的对象转换都通过 UserMapper 实现，统一转换逻辑，避免直接操作实体和领域对象。
        return userMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        // 使用映射器将实体对象转换为领域对象
        return jpaUserRepository.findById(id)
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email)
                .map(userMapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        // 将实体列表映射为领域对象列表
        return userMapper.toDomainList(jpaUserRepository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        if (jpaUserRepository.existsById(id)) {
            jpaUserRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaUserRepository.existsById(id);
    }
}
