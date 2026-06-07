

-- Member 데이터
INSERT INTO member_tb (name, password, phone, email, role, status, created_at)
VALUES
    ('홍길동', '1234', '010-1234-5678', 'hong@naver.com', 'CLIENT', 'ACTIVE', NOW()),
    ('김철수', '1234', '010-2345-6789', 'kim@naver.com', 'EXPERT', 'ACTIVE', NOW()),
    ('관리자', '1234', '010-9999-0000', 'admin@naver.com', 'ADMIN', 'ACTIVE', NOW());



-- board 데이터
INSERT INTO board_tb (content, board_type, title, view_count, created_at, is_active, member_id) VALUES
('공지사항입니다. 서비스 이용 규칙을 꼭 확인해주세요.', 'NOTICE', '서비스 이용 공지', 0, NOW(), true, 1),
('공지사항입니다. 점검 일정을 안내드립니다.', 'NOTICE', '정기 점검 안내', 0, NOW(), true, 1),
('자유롭게 이야기 나눠요!', 'FREE', '오늘 날씨 너무 좋네요', 0, NOW(), true, 2),
('개발 공부하다가 지쳐서 글 남겨요.', 'FREE', '개발 공부 힘드신 분 계신가요?', 0, NOW(), true, 3),
('Spring Boot 추천 강의 있으면 알려주세요.', 'FREE', 'Spring 공부 시작했어요', 0, NOW(), true, 2),
('결제 관련 문의드립니다.', 'INQUIRY', '결제가 안 돼요', 0, NOW(), true, 3),
('비밀번호 변경은 어디서 하나요?', 'INQUIRY', '비밀번호 변경 방법 문의', 0, NOW(), true, 2);