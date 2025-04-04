# CallendarDevelop

필수
- Lv.1 일정 CRUD
  
ㄴ CRUD 기능이 정상적으로 구동되는가?✅

ㄴ 일정 엔티티에 '작성 유저명, 할일 제목, 내용, 작성일' 필드가 포함되었는가? ✅

- Lv.2 유저 CRUD(연관관계) 

ㄴ CRUD 기능이 정상적으로 구동되는가? ✅

ㄴ 유저 엔티티에 '유저명, 이메일, 작성일, 수정일' 필드가 포함되었는가? ㄴ 일정 엔티티가 작성 유저명 대신 유저 고유 식별자 필드를 포함하고 있는가?✅

필수

- Lv.3 회원가입

ㄴ 유저 엔티티에 비밀번호 필드가 추가되었는가?✅

필수

- Lv.4 로그인(인증) 

ㄴ 이메일과 비밀번호를 사용하여 로그인 기능이 정상적으로 작동하는가?✅

ㄴ 필터를 통해 요청 전후에 인증 처리가 잘 이루어지고 있는가?✅

ㄴ 회원가입과 로그인 요청은 인증 처리에서 제외되고 있는가?✅

ㄴ 로그인 실패 시 401 정확하게 반환되는가?✅

도전

- Lv.5 다양한 예외처리 적용하기 

ㄴ @Valid 어노테이션을 사용하여 객체의 제약 조건을 검증하는 기능이 구현되었는가?✅

ㄴ 다양한 제약조건 검증 어노테이션(@NotNull, @Size 등)을 활용하였는가? ✅ 

도전

- Lv.6 비밀번호 암호화

ㄴ 비밀번호가 성공적으로 암호화되어 저장되는가?✅

ㄴ PasswordEncoder 클래스를 구현하고 활용했는가?✅

도전

- Lv.7 댓글 CRUD 

ㄴ CRUD 기능이 정상적으로 구동되는가?✅

ㄴ 댓글 엔티티에 '댓글 내용, 작성일, 수정일, 작성 유저명' 필드가 포함되었는가?✅

ㄴ 댓글과 일정 간의 연관관계가 설정되었는가?✅

도전

- Lv.8 일정 페이징 조회 

ㄴ Spring Data JPA의 Pageable과 Page 인터페이스를 활용하여 페이징이 구현하였는가?✅

ㄴ 페이지 번호와 페이지 크기를 쿼리 파라미터로 받는 기능이 정상적으로 동작하는가?✅

ㄴ 지정된 필드(할일 제목, 할일 내용, 댓글 개수, 일정 작성일, 수정일, 작성 유저명)가 조회되는가? ✅

ㄴ 디폴트 페이지 크기가 10으로 설정되어 있는가?✅

ㄴ 일정의 수정일을 기준으로 내림차순 정렬이 잘 이루어지고 있는가?✅

# Update ScheduleAPI 명세서

## Authors

| 기능                     | 메서드    | 엔드포인트                        | 요청 예시                                                                     | 응답                                        |
| ---------------------- | ------ | ---------------------------- | ------------------------------------------------------------------------- | ----------------------------------------- |
| Create Author          | POST   | `/authors`                   | `{ "authorName": "name", "email": "email@gmail.com", "password": "123" }` | 201 Created, 400 Bad Request              |
| Get Author by Email    | GET    | `/authors?email=email@gmail` | -                                                                         | 200 OK, 404 Not Found                     |
| Update Author Name     | PATCH  | `/authors/{id}/name`         | `{ "authorName": "name2" }`                                               | 200 OK, 400 Bad Request, 404 Not Found    |
| Update Author Password | PATCH  | `/authors/{id}/password`     | `{ "oldPassword": "123", "newPassword": "1234" }`                         | 200 OK, 400 Bad Request, 401 Unauthorized |
| Delete Author          | DELETE | `/authors/{id}`              |                                                                           | 200 OK, 404 Not Found                     |

## Authentication

| 기능     | 메서드  | 엔드포인트          | 요청 예시                                               | 응답                       |
| ------ | ---- | -------------- | --------------------------------------------------- | ------------------------ |
| Login  | POST | `/home/login`  | `{ "email": "email@gmail.com", "password": "123" }` | 200 OK, 401 Unauthorized |
| Logout | POST | `/home/logout` | -                                                   | 200 OK                   |

## Schedules

| 기능                 | 메서드  | 엔드포인트                          | 요청 예시                                         | 응답                       
| ------------------ | ---- | ------------------------------ | --------------------------------------------- | -------------------------------------- | 
| Create Schedule    | POST | `/schedules`                   | `{ "id": "1", "task": "할일", "title": "제목2" }` | 201 Created                        |    
| Get All Schedules  | GET  | `/schedules`                   | -                                             | 200 OK                                 |   
| Get Schedule by ID | GET  | `/schedules/{id}`              | -                                             | 200 OK, 404 Not Found                  |   
| Update Schedule    | PUT  | `/schedules/{scheduleId}/{id}` | `{ "task": "할일3" }`                           | 200 OK, 400 Bad Request, 404 Not Found |   

## Comments

| 기능                | 메서드  | 엔드포인트            | 요청 예시                                           | 응답                    |   
| ----------------- | ---- | ---------------- | ----------------------------------------------- | --------------------- | 
| Create Comment    | POST | `/comment`       | `{ "schedule_id": "1", "comment": "좋은 글이예요2" }` | 201 Created           | 
| Get Comment by ID | GET  | `/comments/{id}` | -                                               | 200 OK, 404 Not Found |   
| Put Comment       | PUT  | /comments/{id}   | { "schedule\_id": "1", "comment": "좋은 글이예요2" }  | 200 OK, 404 Not Found |  
| Delete Comment | DELETE | `/comments/{id}` | - | 200 OK, 404 Not Found | 
| -------------- | ------ | ---------------- | - | --------------------- | 

ERD
https://www.erdcloud.com/d/wnxoLDw8kcQnumPfQ




