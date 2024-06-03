# SE_Term_Project
2024년 소프트웨어공학 01분반 TermProject Team 17

# 패키지 설명
- main.application - 어플리케이션 실행
- main.controller - MVC 패턴의 컨트롤러 클래스
- main.domain - 모델의 Domain 클래스
- main.domain.enumeration - Domain 클래스에 활용되는 Enum
- main.model - MVC 패턴의 모델 클래스
- main.repository - 모델의 Repo 클래스
- main.view - MVC 패턴의 뷰 클래스
- test.model - ModelTest 클래스(JUnit)
- test.repository -  MysqlRepoTest 클래스(JUnit)

# 실행 방법
사용하고자 하는 gui의 주석을 제거한다.

SE_Term_Project/SE_Term_Project/src/main/application/Run.java

```
// 1. Swing gui
// Application app = new SwingApplication();
// 2. Spring gui
// Application app = new SpringApp(args);
```

# 구현- Swing
## 로그인 화면(LoginView)
![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/801df267-60de-4ad2-84ff-a55cba5dc878)

아이디와 비밀번호를 입력하고 Login 버튼을 누르면, LoginModel의 tryLogin(id,pw)을 호출하여 입력한 정보가 DB에 존재하는지 검사한다. 만약 리턴값이 false라면 오류 팝업을 출력한다.

## 프로젝트 리스트 화면(ProjectListView)
![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/7f156030-e0e2-47a5-84ca-997f3d404acc)

사용자에게 할당된 프로젝트 리스트를 출력한다. 프로젝트 버튼을 클릭하면 프로젝트의 상세 정보(이슈 리스트)를 확인할 수 있다. 사용자가 Admin일 경우 Create Project, Create Account 버튼이 출력되어 기능에 접근할 수 있다.

![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/bb63335f-de63-4941-9af7-e000e32f053e)

계정 생성 화면(AccountCreationView)은 다음과 같다. 새로 생성할 계정의 ID, PW, 권한을 설정하여 Create 버튼을 누르면, AccountModel의 trySignup(id, pw, auth) 함수를 호출한다. 생성이 완료되면 안내 팝업을 출력한다. 만약 입력값이 유효하지 않으면(ex. 빈칸, 이미 있는 아이디) 오류 팝업을 출력한다.

![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/d7a51ef8-a432-4a8b-bace-0f20610d8d29)

프로젝트 생성 화면(ProjectCreationView)은 다음과 같다. 새로 생성할 프로젝트의 이름, PL과 Dev, Tester를 명단에서 선택하고 Create 버튼을 누르면, ProjectListModel의 tryCreate(name, pl, devs, testers) 함수를 호출한다. PL은 한 명, Dev와 Tester는 여러 명을 선택할 수 있다. 결과에 따라 팝업을 출력한다. 프로젝트를 생성하면 프로젝트 리스트 화면에 새로운 프로젝트 버튼이 추가된다.

## 이슈 리스트 화면(IssueListView)
![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/b55f4895-c027-40cf-8d17-da3009dcdbad)

이슈 리스트 화면은 다음과 같다. 사용자의 권한에 따라 시스템에서 미리 자동 필터링된 이슈 목록을 출력한다. 각 이슈 블록은 상세 정보를 확인할 수 있는 버튼, State, Priority, Reporter, Assignee, Fixer 순으로 정보를 보여준다. 사용자가 Tester일 경우 Create Issue 버튼이 출력되어 기능에 접근할 수 있다.

![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/55643104-979a-4279-af21-c60cc7142587)

Report 버튼 기능은 다음과 같다. 버튼을 클릭하면 팝업으로 ‘최근 일주일 간 생성된 이슈의 개수’와 ‘최근 일주일 간 해결된 이슈의 개수’를 출력한다.

![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/55fd32b7-7dca-4366-a22b-6f09c6349172)

이슈 생성 화면(IssueCreationView)은 다음과 같다. 이슈의 제목, Priority, 설명을 입력한 후 Create 버튼을 클릭하면 이슈가 생성된다. 이슈를 생성하면 이슈리스트 화면에 새로운 이슈 블록이 추가된다. 이슈를 생성하면 State는 자동으로 NEW로 설정된다.

![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/f414e483-d27b-41d1-bb4f-ec9380ea66f0)

이슈 필터링 화면(IssueFilterView)은 다음과 같다. State, Reporter, Assignee 설정을 선택하여 이슈를 자유롭게 필터링할 수 있다. Apply 버튼을 클릭하면 이슈리스트가 재설정된다.

## 이슈 상세페이지 화면(IssueDetailView)

![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/e9ebd313-6efb-44db-a1fd-a6f5bace6d84)

권한 및 조건에 따라 State, Priority, Assignee를 수정할 수 있으며, 수정 후 Save 버튼을 클릭하여 저장한다. Comment를 작성 후 ‘←’ 버튼을 클릭하여 코멘트를 추가할 수 있다. 코멘트는 오래된 순으로 정렬되어 가장 최신의 코멘트가 밑에 배치된다.

## 이슈 상태 설정
### NEW
Tester가 생성한 이슈는 자동으로 NEW로 설정된다.

### NEW→ASSIGNED
![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/d601ea8c-3d7b-4235-beac-2e79393b5c18)

PL은 NEW 상태의 이슈에 Assignee를 할당할 수 있다. 이때 Recommend 버튼이 활성화되는데, 이를 클릭하면 아래의 수식에 따라 Assignee를 추천한다. 수식은 이전에 이슈를 많이 해결한 사람을 추천하지만, 이슈를 해결 중인 개발자에게 업무가 과도하게 몰리지 않는 것을 목표로 하였다.

```
# dev1에 대한 추천 점수
- num_resolved = dev1이 assignee인 이슈 중 상태가 RESOLVED인 이슈의 수
- num_closed = dev1이 assignee인 이슈 중 상태가 CLOSED인 이슈의 수
- num_fixed = dev1이 assignee인 이슈 중 상태가 FIXED인 이슈의 수
- num_assigned = dev1이 assignee인 이슈 중 상태가 ASSIGNED인 이슈의 수 
- recommend_score = 2*num_closed + num_resolved - num_fixed - 2*num_assigned
- Assignee 할당 후 저장 버튼을 클릭하면 State가 자동으로 ASSIGNED로 설정된다.
```

### ASSIGNED→FIXED
![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/0a873809-b854-45cd-b478-70cdebb55c25)

Dev는 자신에게 할당된 이슈를 확인할 수 있으며, ASSIGNED 상태의 이슈를 FIXED로 수정할 수 있다. 드롭다운을 조작하여 수정할 수 있으며, ASSIGNED와 FIXED 외 다른 선택은 제한된다.
 
### FIXED→RESOLVED
![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/21ae50b2-4f03-4338-94dc-759c2b201f75)

Tester는 자신이 보고했던 이슈를 확인할 수 있으며, FIXED 상태의 이슈를 RESOLVED로 수정할 수 있다. 드롭다운을 조작하여 수정할 수 있으며, FIXED와 RESOLVED 외 다른 선택은 제한된다.

### RESOLVED→CLOSED
![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/fce41c07-c2bd-48e3-ba94-a8cc960048eb)

PL는 RESOLVED 상태의 이슈를 CLOSED로 수정할 수 있다. 드롭다운을 조작하여 수정할 수 있으며, RESOLVED와 CLOSED 외 다른 선택은 제한된다.

# 구현 - Spring
## 로그인
![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/9500decb-c8d5-4b6a-b211-a624d1c5a865)

accountID와 password를 입력받는다.사용자가 제공한 accountID, password가 DB의 값과 일치하면 프로젝트 리스트 페이지로 이동한다. 일치하지 않은 경우 로그인 페이지로 다시 이동한다.

## 프로젝트 리스트
![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/a3ae3f91-aa3f-4d89-a94b-9551390a1e31)
 
테이블에서 로그인한 계정이 속한 프로젝트 목록을 볼 수 있다. 테이블의 프로젝트를 클릭하면 이슈 리스트 페이지로 이동한다. admin으로 로그인한 경우 우측 상단에 CreateAccount 버튼과 CreateProject 버튼을 확인할 수 있다.

## 계정 생성
 ![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/cb2b90e9-bd0f-43ad-9368-2e35f127429a)

사용자로부터 accountID, password, authority를 입력받는다. authority는 select 형식으로 제공되어 선택할 수 있다. 사용자가 제공한 accountID가 DB에 존재하지 않으면 계정을 생성하고, 로그인 페이지로 이동한다. 사용자가 제공한 accountID, password, authority가 적절하지 않으면 계정 생성 페이지로 다시 이동한다.

## 프로젝트 생성
![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/21e54484-bb0f-427e-8db4-1234fb70b7b5)

사용자로부터 name, pl, devs, testers를 입력받는다. 사용자가 제공한 name, pl, devs, testers에 대한 프로젝트를 생성하고, 프로젝트 리스트 페이지로 이동한다. 사용자가 제공한 데이터가 적절하지 않으면 프로젝트 생성 페이지로 다시 이동한다.

## 이슈 리스트
![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/7d8c3168-68b4-4cf2-9b54-3390bb5e1da2)

테이블에서 사용자의 권한에 맞는 이슈들을 확인할 수 있다. 
- PL : 모든 이슈
- Tester : 본인이 등록한 이슈
- Dev : 본인이 assignee인 이슈
테이블의 이슈를 클릭하면 이슈 상세보기 페이지로 이동한다. tester로 로그인한 경우 우측 상단에 Create Issue 버튼을 확인할 수 있다. ‘Statics for the Last Week’ 블록에서 프로젝트에 대한 ‘지난 7일간 생성된 모든 이슈의 수’와 ‘지난 7일간 해결된 모든 이슈의 수’를 확인할 수 있다.

## 이슈 생성
![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/a94c7afe-9879-45d1-ad1e-06986c2a61d8)
 
사용자로부터 title, priority, description을 입력받는다. priority는 select 형식으로 제공되어 선택할 수 있다. 사용자가 제공한 title, priority, description에 대한 이슈를 생성하고, 이슈 리스트 페이지로 이동한다. 사용자가 제공한 데이터가 적절하지 않으면 이슈 생성 페이지로 다시 이동한다.

## 이슈 리스트 필터링
![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/1b3f0337-fcdb-4866-8756-42cb01c9b24a)

이슈 리스트 페이지에서 Filter 버튼을 클릭하면 다음과 같은 블럭이 나타난다. 사용자로부터 state, assignee, reporter를 입력받는다. 사용자가 제공한 state, assignee, reporter와 동일한 이슈를 이슈 리스트 페이지에 출력한다. 사용자가 제공한 state가 적절하지 않으면, state에 대한 필터링을 제외한다. assignee, reporter가 DB에 존재하지 않으면, assignee, reporter에 대한 필터링을 제외한다.

## 이슈 상세보기
![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/ab7ad553-0822-4f9a-8948-c10bdcb44cc6)

이슈에 대한 상세 내용을 확인할 수 있다. 기존에 작성된 코멘트를 확인할 수 있다.

## 이슈 상태 설정
사용자가 제공한 state, priority, assignee에 대한 이슈를 생성하고, 이슈 상세보기 페이지를 새로고침한다.

### Priority 상태 변경
사용자가 pl인 경우 이슈의 priority를 변경할 수 있다. PL이 아닌 계정은, 이슈의 priority를 수정할 수 없다.

### NEW→ASSIGNED 상태 변경
![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/f867fdc4-012a-45d1-b3b1-ae82192a07ed)

사용자가 pl이고, 이슈의 state가 NEW인 경우 이슈의 상태를 NEW→ASSIGNED로 설정할 수 있다. PL이 아닌 계정은, 이슈의 state가 NEW 경우 이슈의 상태를 수정할 수 없다. 이슈 상태가 ASSINGED로 변경되면, 선택한 assignee가 등록된다.

### ASSIGNED→FIXED 상태 변경
![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/125b48e4-16fa-47cc-9221-34552c2d08e0)

사용자가 assignee와 동일한 dev이고, 이슈의 state가 ASSIGNED인 경우 이슈의 상태를 ASSIGNED→FIXED로 설정할 수 있다. 사용자가 assignee가 아닌 계정은, 이슈의 state가 ASSIGNED인 경우 이슈의 상태를 수정할 수 없다. 이슈 상태가 FIXED로 변경되면, assignee가 fixer로 등록된다.

### FIXED→RESOLVED 상태 변경
 ![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/faf89171-6fac-4ed7-b9ad-03048a7fef55)

사용자가 reporter와 동일한 tester이고, 이슈의 state가 FIXED인 경우 이슈의 상태를 FIXED→RESOLVED로 설정할 수 있다. 사용자가 reporter가 아닌 계정은, 이슈의 state가 FIXED인 경우 이슈의 상태를 수정할 수 없다 이슈 상태가 RESOLVED로 변경되면, DB에 resolvedDate가 저장된다.

### RESOLVED→CLOSED 상태 변경
![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/16abdf8a-22a0-4ac7-bee8-b1a758392918)

사용자가 pl이고, 이슈의 state가 RESOLVED인 경우 이슈의 상태를 CLOSED로 설정할 수 있다. 사용자가 pl이 아닌 계정은, 이슈의 state가 RESOLVED인 경우 이슈의 상태를 수정할 수 없다.

## Assignee 자동 추천
![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/a6bc1ef6-fc51-4dbd-8444-d68ba4233c40)

사용자가 PL인 경우 Assignee 선택지 옆에 추천하는 dev 리스트가 제공된다.

```
#dev1에 대한 추천 점수
num_resolved = dev1이 assignee인 이슈 중 상태가 RESOLVED인 이슈의 수
num_closed = dev1이 assignee인 이슈 중 상태가 CLOSED인 이슈의 수
num_fixed = dev1이 assignee인 이슈 중 상태가 FIXED인 이슈의 수
num_assigned = dev1이 assignee인 이슈 중 상태가 ASSIGNED인 이슈의 수 
recommend_score = 2*num_closed + num_resolved - num_fixed - 2*num_assigned
```

- dev에 대한 assignee 추천 우선도는 위과 같은 계산을 통해 구해진다.
- 이전에 이슈를 많이 해결한 사람을 추천하지만, 이슈를 해결 중인 개발자에게 업무가 과도하게 몰리지 않는 것을 목표로 하였다.

## 코멘트 추가
![image](https://github.com/WickedFoxes/SE_Term_Project/assets/48846088/b9fa8f1a-e089-49fd-bd7f-1fc68310f349)

모든 사용자는 이슈 상세보기 페이지에서 코멘트를 작성할 수 있다. 사용자가 제공한 content에 대한 코멘트를 생성하고, 이슈 상세보기 페이지로 이동한다.
