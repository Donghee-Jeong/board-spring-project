package donghee.board.repository;

import donghee.board.domain.Post;

import java.util.List;

public interface BoardRepository {
    Post save(Post post);
    Post findById(Long id);
    List<Post> findByTitle(String title);
    List<Post> findByContent(String content);
    List<Post> findAll();
    Post update(Post editedPost);
    Post remove(Post post);
}
