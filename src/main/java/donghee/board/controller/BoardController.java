package donghee.board.controller;

import donghee.board.domain.Post;
import donghee.board.repository.BoardRepository;
import donghee.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    final private BoardService boardService;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("postList", boardService.findAll());
        return "main";
    }

    @GetMapping("/create")
    public String createForm() {
        return "create";
    }

    @PostMapping("/create")
    public String createPost(Post post){
        boardService.create(post);
        return "redirect:/";
    }
}
