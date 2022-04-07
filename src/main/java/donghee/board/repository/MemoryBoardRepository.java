package donghee.board.repository;

import donghee.board.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class MemoryBoardRepository implements BoardRepository{
    private static Long id = 0L;
    final private static Map<Long, Post> store = new HashMap<>();

    @Override
    public Post save(Post post) {
        post.setId(++id);
        store.put(post.getId(), post);
        return post;
    }

    @Override
    public Post findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Post> findByTitle(String title) {
        return store.values().stream().filter(post -> post.getTitle().contains(title)).collect(Collectors.toList());
    }

    @Override
    public List<Post> findByContent(String content) {
        return store.values().stream().filter(post -> post.getContent().contains(content)).collect(Collectors.toList());
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Post update(Post editedPost) {
        Post post = store.get(editedPost.getId());
        post.editPost(editedPost);
        return post;
    }

    public void clearStore(){
        store.clear();
    }
}
