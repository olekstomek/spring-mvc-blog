package blog.services;

import blog.forms.RegisterForm;
import blog.models.User;
import blog.repositories.UserRepository;
import blog.services.interfaces.NotificationService;
import blog.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserServiceJpaImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private NotificationService notificationService;

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public User create(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User edit(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.delete(id);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public String registerSuccessful(RegisterForm registerForm) {
        User user = new User();
        user.setUsername(registerForm.getUsername());
        user.setPasswordHash(bCryptPasswordEncoder.encode(registerForm.getPassword()));
        user.setFullName(registerForm.getFullName());
        create(user);

        notificationService.addInfoMessage("Register successful");
        return "redirect:/";
    }

    @Override
    public boolean checkExistingUserInDatabase(RegisterForm registerForm, BindingResult bindingResult) {
        Optional<User> userExistInDataBase = Optional.ofNullable(findByUsername(registerForm.getUsername()));
        if (bindingResult.hasErrors() || userExistInDataBase.isPresent()) {
            notificationService.addErrorMessage("Error: user registration failed!");
            return true;
        }
        return false;
    }

}