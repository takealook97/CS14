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
- 오류 발생 : no matching manifest for linux/arm64/v8 in the manifest list entrie
    - 도커 허브의 MySQL 공식 이미지 안내에는 ARM 64 태그가 달려있다.  
      그럼에도 불구하고 정상적으로 이미지를 당겨 올 수 없기 때문에 오류가 뜨는 것으로 보인다.
    - 해결법 : **' --platform linux/amd64 '** 를 덧붙여 이미지를 당겨오는 플랫폼이 arm64 환경이 아닌 amd64의 linux 환경이라고 명시함으로써  
      에러를 피해가는 방법을 사용하여 정상적으로 이미지 다운
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
    컨테이너 삭제
    # 실행중인 컨테이너 확인
    $ docker ps 
  
    # 설치된 모든 컨테이너 확인
    $ docker ps -a
  
    # 컨테이너 삭제
    $ docker rm "컨테이너 아이디"
  
    # 모든 컨테이너 삭제
    $ docker rm 'docker ps -a -q'
    ```
    ```bash
    이미지 삭제
    # 현재 이미지 확인
    $ docker image
  
    # 이미지 삭제
    $ docker rmi "이미지 아이디"
  
    # 컨테이너 삭제 전 이미지를 삭제할 경우 = -f를 붙이면 컨테이너도 함께 강제 삭제
    $ docker rmi -f "이미지 아이디"
    ```

## 3. MySQL Docker 컨테이너 생성 및 실행
```bash
$ docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=<password> -d -p 3306:3306 mysql:latest
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