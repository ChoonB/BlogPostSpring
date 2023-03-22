# BlogPostSpring
## Lv.4+5 (230314)
BlogPostSpring은 아래의 LV.3과제에 Spring Security를 적용하고 게시글과 댓글에 좋아요 기능을 추가한 항해 블로그 백엔드 서버 실습과제입니다.

### ERD

![ERD 3차](https://user-images.githubusercontent.com/97417978/225267235-b0650fe9-bca3-4f5e-b1ab-5d368eef2a67.JPG)

API명세서 주소 : <https://documenter.getpostman.com/view/26067913/2s93Juu3ic>

230314 현재 Lv.3의 모든 기능 구현 완료 상태에서 추가적으로 수정 진행하였으며, 게시글과 댓글에 좋아요 기능 구현하고,
Spring Security 적용했습니다. 현재 H2 DB에 연결중입니다. 
230315 현재 대댓글 기능과 회원탈퇴 기능 추가했습니다. 230316 전체게시글조회시 페이징 기능 구현했습니다.
230322 jasypt 로 DB정보 암호화하고 application.properties를 .yml로 변경했습니다.

-----------------------------------

## Lv.3 (230308)
BlogPostSpring은 회원가입, 로그인, 댓글 작성/조회/수정/삭제 기능이 추가된 나만의 항해 블로그 백엔드 서버 실습과제 입니다.

### ERD

![ERD Lv 3](https://user-images.githubusercontent.com/97417978/223611253-ce160c86-976c-40a4-a205-2d644fc9a02f.JPG)

API 명세서 주소 : <https://documenter.getpostman.com/view/26067913/2s93JqRjR1>

230308 현재 LV.2의 모든 기능 구현 완료 상태에서 추가적으로 수정진행하였으며, 댓글 CRUD를 구현하고 예외처리도 새롭게 했습니다.
현재 AWS RDS로 DB 연결하고 배포까지 완료했습니다.

--------------------------------

## Lv.2 (230306)

BlogPostSpring은 회원가입, 로그인 기능이 추가된 나만의 항해 블로그 백엔드 서버 실습과제 입니다.

### ERD

![image](https://user-images.githubusercontent.com/97417978/223117738-884ce2a9-1ec9-4763-8fb6-bb3d39b2108c.png)

API 명세서 주소 : <https://documenter.getpostman.com/view/26067913/2s93JnW7br>

230306 현재 로그인기능 구현하고 게시글 CRUD도 그에 맞게 다시 구현했습니다.

-----------------------

## Lv.1 (230228)

BlogPostSpring은 로그인 기능이 없는 블로그 백엔드 실습 과제입니다.

### Use Case

![SpringPostUseCase](https://user-images.githubusercontent.com/97417978/221760664-f5ed5ee9-1192-425c-aac7-4309d9f838b9.png)

230228 현재 게시글 작성, 전체게시글 조회, 선택 게시글 조회까지는 구현에 성공했습니다.

선택 게시글 수정은 선택 게시글 수정과 선택 게시글 삭제도 구현 완료했습니다.

230301 모든 기능 구현 및 정상 실행 확인 완료했습니다.

