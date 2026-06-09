

-- Member 데이터
INSERT INTO member_tb (name, password, phone, email, role, status, created_at)
VALUES ('김민준', '1234', '010-1234-5678', 'devkim@gmail.com', 'EXPERT', 'ACTIVE', NOW());

INSERT INTO member_tb (name, password, phone, email, role, status, created_at)
VALUES ('최수현', '1234', '010-2345-6789', 'fullchoi@gmail.com', 'EXPERT', 'ACTIVE', NOW());

INSERT INTO member_tb (name, password, phone, email, role, status, created_at)
VALUES ('박지훈', '1234', '010-3456-7890', 'backpark@gmail.com', 'EXPERT', 'ACTIVE', NOW());

INSERT INTO member_tb (name, password, phone, email, role, status, created_at)
VALUES ('임서연', '1234', '010-4567-8901', 'backlim@naver.com', 'EXPERT', 'ACTIVE', NOW());

INSERT INTO member_tb (name, password, phone, email, role, status, created_at)
VALUES ('이준호', '1234', '010-5678-9012', 'frontlee@naver.com', 'EXPERT', 'ACTIVE', NOW());

INSERT INTO member_tb (name, password, phone, email, role, status, created_at)
VALUES ('윤채원', '1234', '010-6789-0123', 'frontyoon@gmail.com', 'CLIENT', 'ACTIVE', NOW());

INSERT INTO member_tb (name, password, phone, email, role, status, created_at)
VALUES ('장태양', '1234', '010-7890-1234', 'appjang@gmail.com', 'CLIENT', 'ACTIVE', NOW());

INSERT INTO member_tb (name, password, phone, email, role, status, created_at)
VALUES ('강다은', '1234', '010-8901-2345', 'flutterkang@naver.com', 'CLIENT', 'PENDING', NOW());

INSERT INTO member_tb (name, password, phone, email, role, status, created_at)
VALUES ('서하늘', '1234', '010-9012-3456', 'uiseo@gmail.com', 'CLIENT', 'ACTIVE', NOW());

INSERT INTO member_tb (name, password, phone, email, role, status, created_at)
VALUES ('오민서', '1234', '010-0123-4567', 'uioh@gmail.com', 'CLIENT', 'SUSPENDED', NOW());



-- board 데이터
INSERT INTO board_tb (content, board_type, title, view_count, created_at, is_active, member_id) VALUES
('공지사항입니다. 서비스 이용 규칙을 꼭 확인해주세요.', 'NOTICE', '서비스 이용 공지', 0, NOW(), true, 1),
('공지사항입니다. 점검 일정을 안내드립니다.', 'NOTICE', '정기 점검 안내', 0, NOW(), true, 1),
('자유롭게 이야기 나눠요!', 'FREE', '오늘 날씨 너무 좋네요', 0, NOW(), true, 2),
('개발 공부하다가 지쳐서 글 남겨요.', 'FREE', '개발 공부 힘드신 분 계신가요?', 0, NOW(), true, 3),
('Spring Boot 추천 강의 있으면 알려주세요.', 'FREE', 'Spring 공부 시작했어요', 0, NOW(), true, 2),
('결제 관련 문의드립니다.', 'INQUIRY', '결제가 안 돼요', 0, NOW(), true, 3),
('비밀번호 변경은 어디서 하나요?', 'INQUIRY', '비밀번호 변경 방법 문의', 0, NOW(), true, 2);


-- 댓글 데이터
INSERT INTO comment_tb (board_id, member_id, content, created_at) VALUES
(3, 1, '정말 유익한 글이네요!', NOW()),
(3, 2, '저도 같은 생각입니다.', NOW()),
(4, 1, '좋은 정보 감사합니다.', NOW()),
(5, 3, '궁금한 점이 있는데 질문해도 될까요?', NOW()),
(3, 2, '이 부분은 저도 겪었던 문제예요.', NOW());


-- 전문가 데이터

INSERT INTO expert_tb (member_id, profile_image, intro, career, github_url, contact_email, avg_rating, total_reviews, expert_role, is_certified)
VALUES (1, 'https://picsum.photos/200', '10년차 풀스택 개발자입니다. 스타트업부터 대기업까지 다양한 경험을 보유하고 있습니다.', 'kakao 3년, 네이버 4년, 현재 프리랜서 3년차', 'https://github.com/devkim', 'devkim@gmail.com', 4.8, 23, 'FULLSTACK', true);

INSERT INTO expert_tb (member_id, profile_image, intro, career, github_url, contact_email, avg_rating, total_reviews, expert_role, is_certified)
VALUES (2, 'https://picsum.photos/202', 'Spring Boot, JPA 백엔드 전문입니다. 대용량 트래픽 처리 경험 있습니다.', '삼성SDS 5년, 프리랜서 2년', 'https://github.com/backpark', 'backpark@gmail.com', 4.2, 8, 'BACKEND', true);

INSERT INTO expert_tb (member_id, profile_image, intro, career, github_url, contact_email, avg_rating, total_reviews, expert_role, is_certified)
VALUES (3, 'https://picsum.photos/204', 'React, Vue 전문 프론트엔드 개발자입니다. 디자인 시스템 구축 경험 보유.', 'line 2년, 토스 3년', 'https://github.com/frontlee', 'frontlee@naver.com', 4.5, 11, 'FRONTEND', true);

INSERT INTO expert_tb (member_id, profile_image, intro, career, github_url, contact_email, avg_rating, total_reviews, expert_role, is_certified)
VALUES (4, 'https://picsum.photos/206', 'iOS/Android 네이티브 앱 개발 전문입니다. 출시 앱 10개 이상 보유.', '카카오 3년, 라인 2년', 'https://github.com/appjang', 'appjang@gmail.com', 4.7, 19, 'APP', true);

INSERT INTO expert_tb (member_id, profile_image, intro, career, github_url, contact_email, avg_rating, total_reviews, expert_role, is_certified)
VALUES (5, 'https://picsum.photos/208', 'Figma 전문 UI/UX 디자이너입니다. 사용자 리서치부터 프로토타입까지 담당합니다.', '무신사 3년, 오늘의집 2년', 'https://github.com/uiseo', 'uiseo@gmail.com', 4.9, 42, 'UIUX', true);