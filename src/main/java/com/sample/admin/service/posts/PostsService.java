package com.sample.admin.service.posts;

import com.sample.admin.domain.posts.Posts;
import com.sample.admin.domain.posts.PostsRepository;
import com.sample.admin.web.dto.PostsListResponseDto;
import com.sample.admin.web.dto.PostsResponseDto;
import com.sample.admin.web.dto.PostsSaveRequestDto;
import com.sample.admin.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/* 스프링에서 @Autowired 권장 ㄴㄴ
 * 생성자로 Bean 객체를 받도록 함여 동일한 효과
 * 이를 위해 생성자를 여기서 생성한다.
 * 의존성 관계가 변경될때마다 생성자를 계속 수정하는 번거로움을 해결
 * 새로운 변경사항이 있어도 수정하지 않아도 됨.
 */
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional(readOnly = true) /* 트랜잭션범위유지 -> 조회속도 개선 (등록, 수정, 삭제 없는 메소드에서 사용 추천) */
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) /* 동일표현 : .map(posts -> new PostsListResponseDto(posts)) / posts 스트림을 map을 통해 List로 변환*/
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다.")); /* 존재하는지 확인 */
        postsRepository.delete(posts);  /* postsRepository.deleteById(id); => id 또는 엔티티로 삭제가 가능하다. */
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }
}
