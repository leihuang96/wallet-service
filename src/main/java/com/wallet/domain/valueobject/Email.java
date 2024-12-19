package com.wallet.domain.valueobject;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {

    private final String value;

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    // 构造函数，确保只接受有效的邮箱格式
    public Email(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be blank");
        }
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.value = value.trim();
    }

    // 获取邮箱值
    public String getValue() {
        return value;
    }

    // 重写 equals 方法，确保值对象比较基于值而非内存地址
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(value, email.value);
    }

    // 重写 hashCode 方法，确保对象可以在哈希集合中使用
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
