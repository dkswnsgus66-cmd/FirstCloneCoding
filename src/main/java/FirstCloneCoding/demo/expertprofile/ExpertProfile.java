package FirstCloneCoding.demo.expertprofile;


import FirstCloneCoding.demo.member.Member;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "expert_tb")
@Entity
@Data
@NoArgsConstructor
public class ExpertProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // expertId

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    @Column(nullable = true)
    private String profileImage; // 프로필 이미지

    @Column(nullable = true,columnDefinition = "TEXT") //columnDefinition DB 에 어떤타입으로 넣을지 정할수 있다.
    private String intro; // 소개글

    @Column(nullable = true,columnDefinition = "TEXT")
    private String career; // 커리어

    @Column(nullable = true)
    private String githubUrl; // 깃허브 링크(프로젝트 URL)

    @Column(nullable = true)
    private String contactEmail; // 이메일

    @Column(nullable = false)
    private Double avgRating; // 평균 별점

    @Column(nullable = false)
    private int totalReviews; // 총 리뷰

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExpertRole expertRole; // 전문가 개발영역

    @Column(nullable = false)
    private boolean isCertified; // 전문가 인증여부
}
