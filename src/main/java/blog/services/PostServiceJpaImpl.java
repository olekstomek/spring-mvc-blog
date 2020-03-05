package blog.services;

import blog.models.Post;
import blog.models.User;
import blog.repositories.PostRepository;
import blog.services.interfaces.NotificationService;
import blog.services.interfaces.PostService;
import blog.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PostServiceJpaImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return this.postRepository.findAll();
    }

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    String nameLoggedInUser;

    @Override
    public List<Post> findLatest5() {
        return this.postRepository.findLatest5Posts(new PageRequest(0, 5));
    }

    @Override
    public Post findById(Long id) {
        return this.postRepository.findOne(id);
    }

    @Override
    public Post create(Post post) {
        return this.postRepository.save(post);
    }

    @Override
    public Post edit(Post post) {
        return this.postRepository.save(post);
    }

    @Override
    public void deleteById(Long id) {
        this.postRepository.delete(id);
    }

    @Override
    public void loadPostsInSidebar(Model model) {
        List<Post> latest5Posts = findLatest5();
        model.addAttribute("latest5posts", latest5Posts);

        List<Post> latest3Posts = latest5Posts.stream()
                .limit(3).collect(toList());
        model.addAttribute("latest3posts", latest3Posts);
    }

    @Override
    public String showPostById(Long id, Model model) {
        Post post = findById(id);

        if (post == null) {
            notificationService.addErrorMessage(
                    "Cannot find post: " + id);
            return "redirect:/";
        }

        model.addAttribute("post", post);
        return "/posts/view";
    }

    @Override
    public String confirmDeletePostById(Long id, Model model) {
        Post post = findById(id);

        if (post == null) {
            notificationService.addErrorMessage(
                    "Cannot find post: " + id);
            return "redirect:/posts";
        }

        model.addAttribute("post", post);
        return "/posts/delete";
    }

    @Override
    public String deletePostById(Long id) {
        nameLoggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();

        if (findById(id).getAuthor().getUsername().equals(nameLoggedInUser)) {
            deleteById(id);
            notificationService.addInfoMessage("Delete successful");
            return "redirect:/posts";
        }
        notificationService.addErrorMessage("Delete without successful! It's not your post.");

        return "redirect:/posts";
    }

    @Override
    public String findPostById(Long id, Model model) {
        Post post = findById(id);

        if (post == null) {
            notificationService.addErrorMessage(
                    "Cannot find post: " + id);
            return "redirect:/posts";
        }

        model.addAttribute("post", post);
        return "/posts/edit";
    }

    @Override
    public String validateUserEditPermissions(Post post, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            notificationService.addInfoMessage("Edit without successful");
            return "/posts/edit";
        }

        if (post.getTitle().isEmpty() || post.getBody().isEmpty()) {
            notificationService.addErrorMessage("All fields are required!");
            return "/posts/edit";
        }

        nameLoggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();

        if (post.getAuthor().getUsername().equals(nameLoggedInUser)) {
            edit(post);
            notificationService.addInfoMessage("Edit successful");
            return "redirect:/posts";
        }
        notificationService.addErrorMessage("Edit without successful! It's not your post.");

        return "redirect:/posts";
    }

    @Override
    public String addNewPost(Post post) {
        String nameLoggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userService.findByUsername(nameLoggedInUser);
        post.setAuthor(user);

        create(post);
        notificationService.addInfoMessage("Post created.");

        return "redirect:/posts";
    }

    @Override
    public boolean validateAddingPost(Post post, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return true;
        }

        if (post.getTitle().isEmpty() || post.getBody().isEmpty()) {
            notificationService.addErrorMessage("All fields are required!");
            return true;
        }
        return false;
    }

}