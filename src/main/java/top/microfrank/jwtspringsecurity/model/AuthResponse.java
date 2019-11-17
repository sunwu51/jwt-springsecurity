package top.microfrank.jwtspringsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Frank
 * @date 2019/11/17 12:01
 */

/**
 * 登录接口的返回内容json
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse implements Serializable {
    private String jwt;
}
