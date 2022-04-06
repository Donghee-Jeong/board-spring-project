package donghee.board.repository;

import donghee.board.domain.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemoryBoardRepositoryTest {

    final private MemoryBoardRepository memoryBoardRepository = new MemoryBoardRepository();

    @AfterEach
    void afterEach(){
        memoryBoardRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Post post = new Post("title", "content");

        //when
        Post savedPost = memoryBoardRepository.save(post);

        //then
        assertThat(savedPost).isEqualTo(post);
    }

    @Test
    void findById() {
        //given
        Post post = new Post("title", "content");
        Post savedPost = memoryBoardRepository.save(post);

        //when
        Post findPost = memoryBoardRepository.findById(savedPost.getId());

        //then
        assertThat(findPost).isEqualTo(post);
    }

    @Test
    void findByTitle() {
        //given
        Post post1 = new Post("title1", "content1");
        Post post2 = new Post("title2", "content2");

        //when
        memoryBoardRepository.save(post1);
        memoryBoardRepository.save(post2);

        //then
        List<Post> findByTitlePost1 = memoryBoardRepository.findByTitle("title");
        assertThat(findByTitlePost1.size()).isEqualTo(2);
        assertThat(findByTitlePost1).contains(post1, post2);

        List<Post> findByTitlePost2 = memoryBoardRepository.findByTitle("2");
        assertThat(findByTitlePost2.size()).isEqualTo(1);
        assertThat(findByTitlePost2).contains(post2);
    }

    @Test
    void findByContent() {
        Post post1 = new Post("title1", "content1");
        Post post2 = new Post("title2", "content2");

        //when
        memoryBoardRepository.save(post1);
        memoryBoardRepository.save(post2);

        //then
        List<Post> findByContentPost1 = memoryBoardRepository.findByContent("content");
        assertThat(findByContentPost1.size()).isEqualTo(2);
        assertThat(findByContentPost1).contains(post1, post2);


        List<Post> findByContentPost2 = memoryBoardRepository.findByContent("2");
        assertThat(findByContentPost2.size()).isEqualTo(1);
        assertThat(findByContentPost2).contains(post2);

    }

    @Test
    void findAll() {
        Post post1 = new Post("title1", "content1");
        Post post2 = new Post("title2", "content2");

        //when
        memoryBoardRepository.save(post1);
        memoryBoardRepository.save(post2);

        //then
        assertThat(memoryBoardRepository.findAll().size()).isEqualTo(2);
        assertThat(memoryBoardRepository.findAll()).contains(post1, post2);

    }
}