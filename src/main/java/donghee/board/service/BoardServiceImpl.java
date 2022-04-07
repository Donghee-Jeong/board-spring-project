package donghee.board.service;

import donghee.board.domain.Post;
import donghee.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    final private BoardRepository boardRepository;

    @Override
    public Post add(Post post) {
        return boardRepository.save(post);
    }

    @Override
    public Post findById(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    public List<Post> findByTitle(String title) {
        return boardRepository.findByTitle(title);
    }

    @Override
    public List<Post> findByContent(String content) {
        return boardRepository.findByContent(content);
    }

    @Override
    public List<Post> findAll() {
        return boardRepository.findAll();
    }

    @Override
    public Post edit(Post editedPost) {
        return boardRepository.update(editedPost);
    }

    @Override
    public Post delete(Post post) {
        return boardRepository.remove(post);
    }
}
