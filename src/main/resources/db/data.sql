INSERT INTO member_tb (name, password, phone, email, role, status, created_at)
VALUES
    ('홍길동', '1234', '010-1234-5678', 'hong@naver.com', 'CLIENT', 'ACTIVE', NOW()),
    ('김철수', '1234', '010-2345-6789', 'kim@naver.com', 'EXPERT', 'ACTIVE', NOW()),
    ('관리자', '1234', '010-9999-0000', 'admin@naver.com', 'ADMIN', 'ACTIVE', NOW());