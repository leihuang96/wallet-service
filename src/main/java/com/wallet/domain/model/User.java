package com.wallet.domain.model;

import com.wallet.domain.valueobject.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long id;

    @NotBlank(message = "{NotBlank.user.username}")
    private String username;

    @NotBlank(message = "{NotBlank.user.password}")
    @Size(min = 6, message = "{Size.user.password}")
    private Password password; // 使用值对象表示密码

    @Email(message = "{Email.user.email}")
    @NotBlank(message = "{NotBlank.user.email}")
    private String email;

    public User(String username, Password password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
