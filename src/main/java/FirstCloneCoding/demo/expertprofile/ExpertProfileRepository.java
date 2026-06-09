package FirstCloneCoding.demo.expertprofile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExpertProfileRepository extends JpaRepository<ExpertProfile ,Long> {

    @Query("""
    SELECT e FROM ExpertProfile e JOIN FETCH e.member
    """)
    List<ExpertProfile> findAllWithMember();

    @Query("""
    SELECT e FROM ExpertProfile e JOIN FETCH e.member WHERE e.member.id = :memberId
    """)
    Optional<ExpertProfile> findByMemberId(Long memberId);

}
