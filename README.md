🎯 CS14. 데이터베이스 설치
=

# DB 설치

참고 : https://poiemaweb.com/docker-mysql

## 1. Docker 설치

- https://www.docker.com/products/docker-desktop/ 접속 후 자신의 OS에 맞는 Docker 설치
- 도커 버전 출력하기
  ```bash
  docker -v
  ```

## 2. MySQL Docker 이미지 다운

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

## 3. MySQL Docker 컨테이너 생성 및 실행

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
## 4. MySQL Docker 컨테이너 리스트 출력

```bash
$ docker ps -a
```

## 5. MySQL Docker 컨테이너 시작/중지/재시작

```bash
# MySQL Docker 컨테이너 중지
$ docker stop mysql-container

# MySQL Docker 컨테이너 시작
$ docker start mysql-container

# MySQL Docker 컨테이너 재시작
$ docker restart mysql-container
```

## 6. MySQL Docker 컨테이너 접속

```bash
$ docker exec -it mysql-container bash
```

## 7. MySQL Docker 연결 해제
```bash
$ exit
```

## 8. 도커(mysql) 컨테이너 실행(bash 접속) 후 MySQL 서버 접속

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
$ apt-get install vim
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



# MySQL 명령어
참고 : https://velog.io/@kimtaeeeny/mysql-%EB%AA%85%EB%A0%B9%EC%96%B4-%EB%AA%A8%EC%9D%8C
## 1. 서버 구동, 종료
```bash
# 구동
$ mysql.server start
# 종료
$ mysql.server stop
```

## 2. mysql 접속, 나가기
```bash
# 접속 
$ mysql -uroot -p
# 나가기
$ EXIT
```

## 3. 데이터베이스 열람, 생성, 삭제
```bash
# 열람
$ SHOW DATABASES;
# 생성
$ CREATE DATABASE 데이터베이스이름;
# 삭제
$ DROP DATABASE 데이터베이스이름;
```

## 4. 상태확인
```bash
$ STATUS;
```

## 5. 비밀번호 변경 (mysql 접속 가능 시)
```bash
$ ALTER USER 'root'@'localhost' IDENTIFIED WITH caching_sha2_password BY '1234';
```

## 6. 비밀번호 변경 (분실시)
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

