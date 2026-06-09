package FirstCloneCoding.demo.expertprofile;

import FirstCloneCoding.demo.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpertProfileService {
    private final ExpertProfileRepository expertProfileRepository;

    // 전문가 리스트 조회
    public List<ExpertProfileResponse.ListDTO> expertList(String role) {

        List<ExpertProfile> expertProfileList = expertProfileRepository.findAllWithMember();

        return expertProfileList.stream().map(ExpertProfileResponse.ListDTO::new).toList();

    }

    public ExpertProfileResponse.DetailDTO Detail(Long memberId) {

        ExpertProfile expertProfileEntity = expertProfileRepository.findByMemberId(memberId).orElseThrow(() ->
                    new NotFoundException("해당 회원을 찾을수 없습니다.")
                );
        return new ExpertProfileResponse.DetailDTO(expertProfileEntity);
    }
}
