package blog.controllers;

import blog.models.Post;
import blog.services.interfaces.NotificationService;
import blog.services.interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class PostsController {

    @Autowired
    private PostService postService;

    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/posts/view/{id}")
    public String view(@PathVariable("id") Long id,
                       Model model) {
        return postService.showPostById(id, model);
    }

    @RequestMapping("/posts")
    public String getAllPosts(Model model) {
        model.addAttribute("posts", postService.findAll());

        return "posts";
    }

    @RequestMapping("/posts/delete/{id}")
    public String getElementToDelete(@PathVariable("id") Long id,
                                     Model model) {
        return postService.confirmDeletePostById(id, model);
    }

    @RequestMapping(value = "/posts/delete/{id}", method = RequestMethod.DELETE)
    public String deletePage(@PathVariable("id") Long id) {

        return postService.deletePostById(id);
    }

    @RequestMapping("/posts/edit/{id}")
    public String getElementToEdit(@PathVariable("id") Long id,
                                   Model model) {
        return postService.findPostById(id, model);
    }

    @RequestMapping(value = "edit", method = RequestMethod.PATCH)
    public String editPage(@Valid Post post, BindingResult bindingResult) {
        return postService.validateUserEditPermissions(post, bindingResult);
    }

    @RequestMapping("/posts/create")
    public String create(Post post) {
        return "posts/create";
    }

    @RequestMapping(value = "/posts/create", method = RequestMethod.POST)
    public String createPage(@Valid Post post, BindingResult bindingResult) {
        if (postService.validateAddingPost(post, bindingResult)) return "/posts/create";

        return postService.addNewPost(post);
    }
}