package donghee.board.controller;

import donghee.board.domain.Post;
import donghee.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    final private BoardService boardService;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("postList", boardService.findAll());
        return "main";
    }

    @GetMapping("/post/add")
    public String addForm() {
        log.info("Add Form");

        return "/post/addForm";
    }

    @PostMapping("/post/add")
    public String addPost(Post post, RedirectAttributes redirectAttributes) {
        log.info("Add Post={}", post);
        boardService.add(post);
        redirectAttributes.addAttribute("postId", post.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/post/{postId}";
    }

    @GetMapping("/post/{postId}")
    public String showPost(@PathVariable("postId") Long postId, Model model) {
        Post post = boardService.findById(postId);
        log.info("Show Post={}", post);
        model.addAttribute("post", post);
        return "/post/post";
    }

    @GetMapping("/post/{postId}/edit")
    public String editForm(@PathVariable("postId") Long postId, Model model) {
        Post post = boardService.findById(postId);
        log.info("Edit Form");
        model.addAttribute("post", post);
        return "/post/editForm";
    }

    @PostMapping("/post/{postId}/edit")
    public String editPost(@ModelAttribute Post editedPost, RedirectAttributes redirectAttributes) {
        Post post = boardService.edit(editedPost);
        log.info("Edit Post={}", post);
        redirectAttributes.addAttribute("postId", post.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/post/{postId}";
    }
}
