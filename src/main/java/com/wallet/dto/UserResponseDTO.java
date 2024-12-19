package com.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据传输对象，用于返回用户相关信息给客户端。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {

    private Long id;        // 用户唯一标识符
    private String username; // 用户名
    private String email;    // 用户邮箱
}
