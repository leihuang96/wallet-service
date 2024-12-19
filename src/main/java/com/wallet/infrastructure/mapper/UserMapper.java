package com.wallet.infrastructure.mapper;

import com.wallet.domain.model.User;
import com.wallet.domain.valueobject.Password;
import com.wallet.infrastructure.persistence.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

/**
 * 使用 MapStruct 进行对象映射，用于在领域模型 (User) 和持久化实体 (UserEntity) 之间进行转换。
 * 确保 UserMapper 的唯一职责是处理 User 和 UserEntity 之间的映射
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * 将领域对象 User 转换为持久化实体 UserEntity。
     * @param user 领域对象。
     * @return 持久化实体。
     */
    //表示将源对象的 password 字段通过 passwordToString 转换后映射到目标对象的 password 字段。
    @Mapping(target = "password", source = "password", qualifiedByName = "passwordToString")
    UserEntity toEntity(User user);

    /**
     * 将持久化实体 UserEntity 转换为领域对象 User。
     * @param userEntity 持久化实体。
     * @return 领域对象。
     */
    @Mapping(target = "password", source = "password", qualifiedByName = "stringToPassword")
    User toDomain(UserEntity userEntity);

    /**
     * 将密码对象 (Password) 转换为字符串 (String)。
     * @param password Password 值对象。
     * @return 转换后的字符串。
     */
    @Named("passwordToString")
    default String passwordToString(Password password) {
        return password != null ? password.getValue() : null;
    }

    /**
     * 将字符串 (String) 转换为密码对象 (Password)。
     * @param password 字符串。
     * @return 转换后的 Password 值对象。
     */
    @Named("stringToPassword")
    default Password stringToPassword(String password) {
        return password != null ? new Password(password) : null;
    }

    /**
     * 将持久化实体列表转换为领域对象列表。
     * @param all 持久化实体列表。
     * @return 领域对象列表。
     */
    List<User> toDomainList(List<UserEntity> all);
}
