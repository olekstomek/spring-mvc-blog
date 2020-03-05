package blog.services.interfaces;

import blog.models.Post;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.List;

public interface PostService {
    List<Post> findAll();

    List<Post> findLatest5();

    Post findById(Long id);

    Post create(Post post);

    Post edit(Post post);

    void deleteById(Long id);

    void loadPostsInSidebar(Model model);

    String showPostById(@PathVariable("id") Long id, Model model);

    String confirmDeletePostById(@PathVariable("id") Long id, Model model);

    String deletePostById(@PathVariable("id") Long id);

    String findPostById(@PathVariable("id") Long id, Model model);

    String validateUserEditPermissions(@Valid Post post, BindingResult bindingResult);

    String addNewPost(@Valid Post post);

    boolean validateAddingPost(@Valid Post post, BindingResult bindingResult);
}
