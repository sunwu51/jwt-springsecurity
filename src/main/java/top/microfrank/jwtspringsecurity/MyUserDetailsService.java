package top.microfrank.jwtspringsecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Frank
 * @date 2019/11/17 11:31
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    // 用map模拟数据库的用户信息
    Map<String,String> userPassList = new HashMap<>();
    {
        userPassList.put("user1","pass1");
        userPassList.put("user2","pass2");
        userPassList.put("user3","pass3");

    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 正式使用的时候 应该将下面换成数据库访问
        if(userPassList.containsKey(userName)){
            return new User(userName,userPassList.get(userName),new ArrayList<>());
        }
        return null;
    }
}
