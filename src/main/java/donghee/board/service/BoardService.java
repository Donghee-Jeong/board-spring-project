package donghee.board.service;

import donghee.board.domain.Post;

import java.util.List;

public interface BoardService {
    Post create(Post post);
    Post findById(Long id);
    List<Post> findByTitle(String title);
    List<Post> findByContent(String content);
    List<Post> findAll();
}
