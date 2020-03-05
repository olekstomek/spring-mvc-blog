package blog.services.interfaces;

import blog.forms.RegisterForm;
import blog.models.User;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User create(User user);

    User edit(User user);

    void deleteById(Long id);

    User findByUsername(String userName);

    String registerSuccessful(@Valid RegisterForm registerForm);

    boolean checkExistingUserInDatabase(@Valid RegisterForm registerForm, BindingResult bindingResult);
}