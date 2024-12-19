package com.wallet.domain.valueobject;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable // 指示该类是 JPA 中的嵌入值对象，其字段会作为拥有者实体的列存储在同一表中。
@EqualsAndHashCode // 确保值对象的相等性基于其值，而不是内存地址。
public class Password {

    private String value;

    public Password(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be blank");
        }
        if (value.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
        this.value = value;
    }

    // 用于对比原始密码与存储的加密密码。
    public boolean matches(String rawPassword) {
        return this.value.equals(rawPassword);
    }

}
