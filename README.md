🎯 CS14. 데이터베이스 설치
=

# DB 설치

참고 : https://poiemaweb.com/docker-mysql

### 1. Docker 설치

- https://www.docker.com/products/docker-desktop/ 접속 후 자신의 OS에 맞는 Docker 설치
- 도커 버전 출력하기
  ```bash
  $ docker -v
  ```

### 2. MySQL Docker 이미지 다운

- 다음 명령어로 MySQL Docker 이미지를 다운로드한다. 태그에 버전을 지정하지 않으면 최신 버전을 다운로드 한다.
  ```bash
  $ docker pull my sql
  ```
- MySQL 버전을 지정하려면 태그에 버전을 지정한다.   
  다운로드할 수 있는 MySQL 버전은 docker hub(https://hub.docker.com/_/mysql/?tab=tags)에서 확인할 수 있다.   
  예를 들어, MySQL 8.0.22 버전을 다운로드하려면 다음과 같이 태그에 버전을 지정한다.
  ```bash
  $ docker pull mysql:5.7
  ```
- 오류 발생 : no matching manifest for linux/arm64/v8 in the manifest list entries
    - 도커 허브의 MySQL 공식 이미지 안내에는 ARM 64 태그가 달려있다.  
      그럼에도 불구하고 정상적으로 이미지를 당겨 올 수 없기 때문에 오류가 뜨는 것으로 보인다.
    - 해결법 : **' --platform linux/amd64 '** 를 덧붙여 이미지를 당겨오는 플랫폼이 arm64 환경이 아닌  
      amd64의 linux 환경이라고 명시함으로써 에러를 피해가는 방법을 사용하여 정상적으로 이미지 다운
    - 참고 : https://velog.io/@sujeongim/%EC%98%A4%EB%A5%98-%EC%B2%9C%EA%B5%AD-Docker%ED%8E%B8-Mac-M1-no-matching-manifest-for-linuxarm64v8
  ```bash
  $ docker pull --platform linux/amd64 mysql:5.7
  ```
- 이후 다음 명령어로 다운로드한 Docker 이미지를 확인한다.

   ```bash
   $ docker images
   ```

- +삭제를 위한 docker 명령어

    ```bash
    컨테이너 삭제 (컨테이너가 가동되어 있을 때는 바로 삭제가 불가능하다. 중지하고 삭제해야 한다.)
    # 실행중인 컨테이너 확인
    $ docker ps 
  
    # 설치된 모든 컨테이너 확인
    $ docker ps -a
  
    # 컨테이너 삭제
    $ docker rm "컨테이너 아이디"
  
    # 모든 컨테이너 삭제
    $ docker rm 'docker ps -a -q'
  
    # docker 1.13.x 이후 버전에서는 아래 명령으로 멈춰있는 모든 프로세스를 제거할 수 있다.
    $ docker container prune
    ```
    ```bash
    이미지 삭제
    # 현재 이미지 확인
    $ docker images
  
    # 이미지 삭제
    $ docker rmi "이미지 아이디"
  
    # 컨테이너 삭제 전 이미지를 삭제할 경우 = -f를 붙이면 컨테이너도 함께 강제 삭제
    $ docker rmi -f "이미지 아이디"
    ```
  - 참고 : https://velog.io/@frozenxnow/Mac%EC%97%90%EC%84%9C-docker%EB%A1%9C-%EC%9D%B4%EC%9A%A9%ED%95%98%EB%8D%98-MySQL-%EC%82%AD%EC%A0%9C%ED%95%98%EA%B8%B0

### 3. MySQL Docker 컨테이너 생성 및 실행

```bash
$ docker run --name <mysql-container> -e MYSQL_ROOT_PASSWORD=<password> -d -p 3306:3306 mysql:latest
```
```bash
$ docker run --platform linux/amd64 --name <mysql-container> -e MYSQL_ROOT_PASSWORD=<password> -d -p 3306:3306 mysql:5.7
```
- 최종 입력 코드 -> 컨테이너 실행 및 mysql 환경변수 변경 명령어 포함
```bash
$ docker run --platform linux/amd64 --name <mysql-container> -e MYSQL_ROOT_PASSWORD=<password> -d -p 3306:3306 mysql:5.7 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
```
### 4. MySQL Docker 컨테이너 리스트 출력

```bash
$ docker ps -a
```

### 5. MySQL Docker 컨테이너 시작/중지/재시작

```bash
# MySQL Docker 컨테이너 중지
$ docker stop mysql-container

# MySQL Docker 컨테이너 시작
$ docker start mysql-container

# MySQL Docker 컨테이너 재시작
$ docker restart mysql-container
```

### 6. MySQL Docker 컨테이너 접속

```bash
$ docker exec -it mysql-container bash
```

### 7. MySQL Docker 연결 해제
```bash
$ exit
```

### 8. 도커(mysql) 컨테이너 실행(bash 접속) 후 MySQL 서버 접속

```bash
$ mysql -u root -p
```
- 이후 비밀번호 입력

---

# utf-8 설정
참고 : https://velog.io/@1yangsh/%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88-MySQL-%ED%95%9C%EA%B8%80-%EC%9D%B8%EC%BD%94%EB%94%A9-%ED%95%B4%EA%B2%B0-%EB%B0%A9%EB%B2%95
- 서버 접속 후 'status'를 통해 현재 설정 확인
- 컨테이너 내부로 돌아가 vi editor 설치 여부 확인
- 없다면 vim 설치 (apt-get이 안된다면 'yum' 사용)
```bash
$ apt-get update
$ apt-get install vim nano
```
- 설치 후 파일에 접근
```bash
$ vim /etc/mysql/my.cnf
```
- 아래의 내용 추가
```bash
[client]
default-character-set=utf8

[mysql]
default-character-set=utf8

[mysqld]
collation-server = utf8_unicode_ci
init-connect='SET NAMES utf8'
character-set-server = utf8
```
- 추가적으로 Linux Vi editor (vi 편집기) 명령어는 아래의 링크 참고
  - https://spidyweb.tistory.com/125
- 이후 서버에서 'status' 확인  
![image](https://user-images.githubusercontent.com/118447769/220188108-457859d1-7de3-4026-9e6d-4473cae10c39.png)

---

# MySQL 명령어
참고 : https://velog.io/@kimtaeeeny/mysql-%EB%AA%85%EB%A0%B9%EC%96%B4-%EB%AA%A8%EC%9D%8C

### 1. 서버 구동, 종료
```bash
# 구동
$ mysql.server start
# 종료
$ mysql.server stop
```

### 2. mysql 접속, 나가기
```bash
# 접속 
$ mysql -uroot -p
# 나가기
$ EXIT
```

### 3. 데이터베이스 열람, 생성, 삭제
```bash
# 열람
$ SHOW DATABASES;
# 생성
$ CREATE DATABASE 데이터베이스이름;
# 삭제
$ DROP DATABASE 데이터베이스이름;
```
- 데이터베이스 및 일반 사용자 생성
```bash
$ CREATE DATABASE mydb;
# 아이디 및 패스워드 설정
$ CREATE USER 'myuserid'@'%' IDENTIFIED BY 'mypassword';
$ GRANT ALL ON mydb.* TO 'myuserid'@'%';
$ FLUSH PRIVILEGES;

# mydb: 데이터베이스 이름
# myuserid: 사용자 id
# mypassword: 사용자 패스워드
```

### 4. 상태확인
```bash
$ STATUS;
```

### 5. 시간설정
```bash
$ mysql> set global time_zone='Asia/Seoul';
$ mysql> set time_zone='Asia/Seoul';

$ mysql> select @@global.time_zone, @@session.time_zone;
```

### 6. 비밀번호 변경 (mysql 접속 가능 시)
```bash
$ ALTER USER 'root'@'localhost' IDENTIFIED WITH caching_sha2_password BY '1234';
```

### 7. 비밀번호 변경 (분실시)
```bash
$ mysql.server stop
$ mysql.server start --skip-grant-tables
$ mysql -u root
$ USE mysql;
$ UPDATE user SET authentication_string=null WHERE User='root';
$ FLUSH PRIVILEGES;
$ EXIT
$ mysql -u root
$ ALTER USER 'root'@'localhost' IDENTIFIED WITH caching_sha2_password BY '1234';
```
- 루트 사용자로 로그인 후 일반 사용자 패스워드는 쉽게 변경 가능
```bash
$ SET PASSWORD FOR 'honux'@'%'='new_password';
$ FLUSH PRIVILEGES;
```

---

# 외부접속 허용하기
현재 사용되는 서버의 MySQL에 접속하기 위해서는 모든 IP에서 접속을 할 수 있도록 설정해야 한다.

### 1. 설정 확인
```bash
mysql> SELECT Host FROM mysql.user WHERE user='root';
```
- query문을 통해 HOST설정을 확인한다. %는 모든 IP의 접속을 설정한다는 것을 의미한다.
- query문 확인 시 %가 설정 되어 있을 경우 다음 순서는 넘어가도 된다.

### 2. 모든 IP 접속 허용
```bash
mysql> INSERT INTO mysql.user (host,user,password) VALUES ('%','root',password('패스워드'));
mysql> GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';
mysql> FLUSH PRIVILEGES;
```
- 위에 query를 차례로 실행한다. 이 후 1번의 query를 실행하여 Host에 %가 추가 되었는지 확인한다.

### 3. my.cnf 수정

- vi편집기를 이용하여 my.cnf파일을 엽니다.
  ```bash
  [root@root guru]vi /etc/my.cnf
  ```
- bind-address = 127.0.0.1 앞에 #을 붙여서 주석 처리 (#은 주석을 의미)

- mysql 재시작
  ```bash
  [root@root guru]service mysqld restart
  ```

### 4. 접속 확인

- 아래 명령어를 통하여 접속이 정상적으로 되는지 확인
```bash
[root@root guru]mysql -u root -h 129.129.12.12 -p
```

---

# Mission. 데이터 생성
### 데이터 베이스 확인
![image](https://user-images.githubusercontent.com/118447769/220197902-9707ff47-c81a-40f7-b93d-47d4a8b0d87c.png)

### 데이터베이스 선택
```bash
$ use mydb;
```

## user_log 테이블 생성 및 확인
![image](https://user-images.githubusercontent.com/118447769/220197742-31eb4538-d2f9-4a3f-ba13-9bb31d2b2767.png)  

![image](https://user-images.githubusercontent.com/118447769/220198160-1df13ee3-a215-48d5-90a7-bae6a71ea5f0.png)

- CHAR(M), VARCHAR(M) 차이점
  - M : 저장할 수 있는 문자열의 최대길이 (CHAR는 0~255, VARCHAR는 0~65535)
  - CHAR(M)
    - 고정 문자일 때 사용 ex) 전화번호
    - 설정한 크기보다 작은 길이의 문자열일 때, 공백으로 채워 길이를 M만큼 채움
  - **VARCHAR(M)**
    - 가변 문자일 때 사용 ex) 게시판 제목
    - 실제 입력된 문자열의 길이만큼만 저장하고 사용
  - char은 검색이나 성능이 뛰어나 정형화 일때 사용되며, varchar는 가변적일때 사용된다.

- **DECIMAL(M,D)** : 고정 소수점 타입(fixed-point types)
  - M은 소수 부분을 포함한 실수의 총 자릿수를 나타내며, 최댓값은 65이다.
  - D는 소수 부분의 자릿수를 나타내며, D가 0이면 소수 부분을 가지지 않는다.
  - 
- **DATETIME** : DATETIME는 날짜와 함께 시간까지 저장할 수 있는 타입
  - 기본 형식은 'YYYY-MM-DD HH:MM:SS'이며, 이때 저장할 수 있는 범위는 '1000-01-01 00:00:00'부터 '9999-12-31 23:59:59'까지입니다.

- 타입 참고
  - 숫자 타입 : http://www.tcpschool.com/mysql/mysql_datatype_numeric
  - 문자열 타입 : http://www.tcpschool.com/mysql/mysql_datatype_string
  - 날짜와 시간 타입 : http://www.tcpschool.com/mysql/mysql_datatype_dateTime

### 데이터베이스 연결
참고 : https://hihellosuah.tistory.com/91  

![image](https://user-images.githubusercontent.com/118447769/220204158-b6c31695-b12d-4463-9871-35f94f0fa214.png)  

![image](https://user-images.githubusercontent.com/118447769/220204445-63d16169-fbdb-40e3-b108-6a87b81935ce.png)  

---

### 데이터 생성
- nickname : '영어 단어 100개 + 랜덤 문자열 3자리 + 랜덤 숫자 4자리' 로 생성
  ```java
  StringBuilder randomString = new StringBuilder();
  for (int i = 0; i < 3; i++) {
      randomString.append((char) (rd.nextInt(26) + 97));//랜덤 문자열 3자리
  }
  StringBuilder randomNumber = new StringBuilder();
  for (int i = 0; i < 4; i++) {
      randomNumber.append(rd.nextInt(10));//랜덤 숫자 4자리
  }
  nickname = words[rd.nextInt(100)] + randomString + randomNumber;
  ```
- money : 1 ~ 100,000 사이의 값을 적당하게 분포하게 생성
  ```java
  money = rd.nextInt(100000) + 1;
  ```
- last_visit : 최근 한달 사이 랜덤 시각으로 생성
  ```java
  String[] dateTime = new String[6];//YYYY-MM-DD HH:MM:SS
  dateTime[0] = Integer.toString(2023);
  dateTime[1] = String.format("%02d", rd.nextInt(2) + 1);
  if (dateTime[1].equals("01")) {//1월일 경우
  dateTime[2] = Integer.toString(rd.nextInt(11) + 21);//21~31일
  } else if (dateTime[1].equals("02")) {//2월일 경우
  dateTime[2] = String.format("%02d", rd.nextInt(21) + 1);//1~21일
  }
  dateTime[3] = String.format("%02d", rd.nextInt(24));
  dateTime[4] = String.format("%02d", rd.nextInt(60));
  dateTime[5] = String.format("%02d", rd.nextInt(60));
  lastVisit = dateTime[0] + "-" + dateTime[1] + "-" + dateTime[2] + " " + dateTime[3] + ":" + dateTime[4] + ":" + dateTime[5];
  ```

## DB에 저장
10000개씩 끊어서 저장 -> 10000개 당 약 5~6초 소요 -> 1000000개 : 500~600초 예상
```java
pst.setString(1, nickname);
pst.setString(2, String.valueOf(money));
pst.setString(3, lastVisit);
pst.addBatch();
pst.clearParameters();
if (count % 10000 == 0) {
    pst.executeBatch();
    pst.clearBatch();
    con.commit();
}
```

### 테이블 내 데이터 삭제

```bash
$ truncate <tableName>;
```

### 테이블 내 데이터 조회
```bash
$ select*from <tableName>;
```

### addBatch와 executeBatch를 이용한 대용량 데이터 처리
참고 : https://fruitdev.tistory.com/111

---

## 결과

![image](https://user-images.githubusercontent.com/118447769/220266028-7024239f-b27b-4a34-8ff5-201028164c9f.png)  

![image](https://user-images.githubusercontent.com/118447769/220267114-594ae638-b74b-481b-9dd8-e1846c37331f.png)