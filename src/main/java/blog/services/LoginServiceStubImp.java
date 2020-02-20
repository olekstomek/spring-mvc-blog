package blog.services;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceStubImp implements LoginService {

    @Override
    public boolean authentice(String username, String password) {
        return Objects.equals(username, password);
    }
}
