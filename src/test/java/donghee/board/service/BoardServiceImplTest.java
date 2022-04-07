package donghee.board.service;

import donghee.board.domain.Post;
import donghee.board.repository.BoardRepository;
import donghee.board.repository.MemoryBoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BoardServiceImplTest {

    final private MemoryBoardRepository boardRepository = new MemoryBoardRepository();
    final private BoardService boardService = new BoardServiceImpl(boardRepository);

    @AfterEach
    void afterEach(){
        boardRepository.clearStore();
    }

    @Test
    void create() {
        Post post = new Post("title", "content");

        Post createdPost = boardService.add(post);

        assertThat(createdPost).isEqualTo(post);
    }

    @Test
    void findById() {
        Post post = new Post("title", "content");

        Post createdPost = boardService.add(post);

        assertThat(createdPost.getId()).isEqualTo(post.getId());
    }

    @Test
    void findByTitle() {
        Post post1 = new Post("title1", "content1");
        Post post2 = new Post("title2", "content2");

        boardService.add(post1);
        boardService.add(post2);

        List<Post> findByTitlePost1 = boardService.findByTitle("title");
        List<Post> findByTitlePost2 = boardService.findByTitle("2");

        assertThat(findByTitlePost1.size()).isEqualTo(2);
        assertThat(findByTitlePost1).contains(post1, post2);

        assertThat(findByTitlePost2.size()).isEqualTo(1);
        assertThat(findByTitlePost2).doesNotContain(post1);
    }

    @Test
    void findByContent() {
        Post post1 = new Post("title1", "content1");
        Post post2 = new Post("title2", "content2");

        boardService.add(post1);
        boardService.add(post2);

        List<Post> findByContent1 = boardService.findByContent("content");
        List<Post> findByContent2 = boardService.findByContent("2");

        assertThat(findByContent1.size()).isEqualTo(2);
        assertThat(findByContent1).contains(post1, post2);

        assertThat(findByContent2.size()).isEqualTo(1);
        assertThat(findByContent2).doesNotContain(post1);
    }

    @Test
    void findAll() {
        Post post1 = new Post("title1", "content1");
        Post post2 = new Post("title2", "content2");

        boardService.add(post1);
        boardService.add(post2);

        List<Post> Posts = boardService.findAll();
        assertThat(Posts.size()).isEqualTo(2);
        assertThat(Posts).contains(post1, post2);
    }
}