package top.microfrank.jwtspringsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Frank
 * @date 2019/11/17 12:00
 */

/**
 * 登录接口的传入json
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest implements Serializable {
    private String username;
    private String password;
}
