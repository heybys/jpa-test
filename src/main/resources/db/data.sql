INSERT INTO master.SHOP (shop_id, name, open)
VALUES (1, 'BBQ', true),
       (2, '김밥나라', true),
       (3, '교촌치킨', true),
       (4, '맥도날드', false),
       (5, '피자나라치킨공주', true),
       (6, '롯데리아', true),
       (7, '스시야', false),
       (8, '신선', true);


INSERT INTO master.MENU (menu_id, name, description, price, shop_id)
VALUES (1, '양념치킨', '달콤짭짤 양념치킨', 15000, 1),
       (2, '후라이드', '바삭한 후라이드', 14000, 1),
       (3, '치즈볼', '동글동글 치즈볼', 5000, 1),
       (4, '김밥', '맛나 김밥', 2500, 2),
       (5, '라볶이', '라면 더하기 떡볶이는 라볶이', 6500, 2),
       (6, '허니콤보', '달콤한 허니 소스로 버무린 윙봉 콤보', 18000, 3);

INSERT INTO master.OPTION_GROUP (option_group_id, name, exclusive, menu_id)
VALUES (1, '뼈/순살 선택', true, 1),
       (2, '뼈/순살 선택', true, 2),
       (3, '토핑 추가', false, 4),
       (4, '맵기', true, 5),
       (5, '사이드 추가', false, 6);

INSERT INTO master.OPTIONS (option_id, name, price, option_group_id)
VALUES (1, '뼈', 0, 1),
       (2, '순살', 1000, 1),
       (3, '뼈', 0, 2),
       (4, '순살', 1000, 2),
       (5, '햄 추가', 800, 3),
       (6, '치즈 추가', 500, 3),
       (7, '참치 추가', 1000, 3),
       (8, '순한맛', 0, 4),
       (9, '매웃맛', 500, 4),
       (10, '콜라 1.25L', 2500, 5),
       (11, '사이다 1.5L', 2500, 5),
       (12, '제로콜라 1.25L', 3000, 5),
       (13, '제로사이다 1.5L', 3000, 5);

INSERT INTO master.user (user_id, created_at, created_by, modified_at, modified_by, address, email,
                         password, phone_number, username)
VALUES (1, '2023-01-14 21:19:21', null, '2023-01-14 21:19:21', null, 'Seoul City', null, 'admin',
        '01049051923', 'admin');
