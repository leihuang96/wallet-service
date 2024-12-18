package com.wallet.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

// 将一个 Java 类声明为 JPA 实体，表示这个类会被映射到数据库中的一张表。
@Entity
// 指定当前实体类对应的数据库表名。
@Table(name = "users")
// 自动生成字段的 Getter 和 Setter 方法，减少代码编写量。
@Getter
@Setter
// 生成一个无参构造函数。JPA 实体类需要一个无参构造函数用于反射创建对象。
@NoArgsConstructor
//生成一个全参构造函数，包括所有字段。便于对象初始化时传入所有字段值。
@AllArgsConstructor

public class User {
// 标识当前字段是数据库表的主键。
    @Id
    // 用于主键生成策略，表示主键的值会自动生成。
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 用于映射数据库表的列。
    @Column(nullable = false, unique = true)
    @NotBlank(message = "{NotBlank.user.username}") // 确保用户名不为空或只包含空格
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "{NotBlank.user.password}") // 确保密码不为空
    @Size(min = 6, message = "{Size.user.password}") // 密码至少6个字符
    private String password;

    @Column(nullable = false, unique = false)
    @NotBlank(message = "{NotBlank.user.email}") // 确保邮箱不为空
    @Email(message = "{Email.user.email}") // 校验邮箱格式
    private String email;
}