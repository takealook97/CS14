๐ฏ CS14. ๋ฐ์ดํฐ๋ฒ ์ด์ค ์ค์น
=

# DB ์ค์น

์ฐธ๊ณ  : https://poiemaweb.com/docker-mysql

### 1. Docker ์ค์น

- https://www.docker.com/products/docker-desktop/ ์ ์ ํ ์์ ์ OS์ ๋ง๋ Docker ์ค์น
- ๋์ปค ๋ฒ์  ์ถ๋ ฅํ๊ธฐ
  ```bash
  $ docker -v
  ```

### 2. MySQL Docker ์ด๋ฏธ์ง ๋ค์ด

- ๋ค์ ๋ช๋ น์ด๋ก MySQL Docker ์ด๋ฏธ์ง๋ฅผ ๋ค์ด๋ก๋ํ๋ค. ํ๊ทธ์ ๋ฒ์ ์ ์ง์ ํ์ง ์์ผ๋ฉด ์ต์  ๋ฒ์ ์ ๋ค์ด๋ก๋ ํ๋ค.
  ```bash
  $ docker pull my sql
  ```
- MySQL ๋ฒ์ ์ ์ง์ ํ๋ ค๋ฉด ํ๊ทธ์ ๋ฒ์ ์ ์ง์ ํ๋ค.   
  ๋ค์ด๋ก๋ํ  ์ ์๋ MySQL ๋ฒ์ ์ docker hub(https://hub.docker.com/_/mysql/?tab=tags)์์ ํ์ธํ  ์ ์๋ค.   
  ์๋ฅผ ๋ค์ด, MySQL 8.0.22 ๋ฒ์ ์ ๋ค์ด๋ก๋ํ๋ ค๋ฉด ๋ค์๊ณผ ๊ฐ์ด ํ๊ทธ์ ๋ฒ์ ์ ์ง์ ํ๋ค.
  ```bash
  $ docker pull mysql:5.7
  ```
- ์ค๋ฅ ๋ฐ์ : no matching manifest for linux/arm64/v8 in the manifest list entries
    - ๋์ปค ํ๋ธ์ MySQL ๊ณต์ ์ด๋ฏธ์ง ์๋ด์๋ ARM 64 ํ๊ทธ๊ฐ ๋ฌ๋ ค์๋ค.  
      ๊ทธ๋ผ์๋ ๋ถ๊ตฌํ๊ณ  ์ ์์ ์ผ๋ก ์ด๋ฏธ์ง๋ฅผ ๋น๊ฒจ ์ฌ ์ ์๊ธฐ ๋๋ฌธ์ ์ค๋ฅ๊ฐ ๋จ๋ ๊ฒ์ผ๋ก ๋ณด์ธ๋ค.
    - ํด๊ฒฐ๋ฒ : **' --platform linux/amd64 '** ๋ฅผ ๋ง๋ถ์ฌ ์ด๋ฏธ์ง๋ฅผ ๋น๊ฒจ์ค๋ ํ๋ซํผ์ด arm64 ํ๊ฒฝ์ด ์๋  
      amd64์ linux ํ๊ฒฝ์ด๋ผ๊ณ  ๋ช์ํจ์ผ๋ก์จ ์๋ฌ๋ฅผ ํผํด๊ฐ๋ ๋ฐฉ๋ฒ์ ์ฌ์ฉํ์ฌ ์ ์์ ์ผ๋ก ์ด๋ฏธ์ง ๋ค์ด
    - ์ฐธ๊ณ  : https://velog.io/@sujeongim/%EC%98%A4%EB%A5%98-%EC%B2%9C%EA%B5%AD-Docker%ED%8E%B8-Mac-M1-no-matching-manifest-for-linuxarm64v8
  ```bash
  $ docker pull --platform linux/amd64 mysql:5.7
  ```
- ์ดํ ๋ค์ ๋ช๋ น์ด๋ก ๋ค์ด๋ก๋ํ Docker ์ด๋ฏธ์ง๋ฅผ ํ์ธํ๋ค.

   ```bash
   $ docker images
   ```

- +์ญ์ ๋ฅผ ์ํ docker ๋ช๋ น์ด

    ```bash
    ์ปจํ์ด๋ ์ญ์  (์ปจํ์ด๋๊ฐ ๊ฐ๋๋์ด ์์ ๋๋ ๋ฐ๋ก ์ญ์ ๊ฐ ๋ถ๊ฐ๋ฅํ๋ค. ์ค์งํ๊ณ  ์ญ์ ํด์ผ ํ๋ค.)
    # ์คํ์ค์ธ ์ปจํ์ด๋ ํ์ธ
    $ docker ps 
  
    # ์ค์น๋ ๋ชจ๋  ์ปจํ์ด๋ ํ์ธ
    $ docker ps -a
  
    # ์ปจํ์ด๋ ์ญ์ 
    $ docker rm "์ปจํ์ด๋ ์์ด๋"
  
    # ๋ชจ๋  ์ปจํ์ด๋ ์ญ์ 
    $ docker rm 'docker ps -a -q'
  
    # docker 1.13.x ์ดํ ๋ฒ์ ์์๋ ์๋ ๋ช๋ น์ผ๋ก ๋ฉ์ถฐ์๋ ๋ชจ๋  ํ๋ก์ธ์ค๋ฅผ ์ ๊ฑฐํ  ์ ์๋ค.
    $ docker container prune
    ```
    ```bash
    ์ด๋ฏธ์ง ์ญ์ 
    # ํ์ฌ ์ด๋ฏธ์ง ํ์ธ
    $ docker images
  
    # ์ด๋ฏธ์ง ์ญ์ 
    $ docker rmi "์ด๋ฏธ์ง ์์ด๋"
  
    # ์ปจํ์ด๋ ์ญ์  ์  ์ด๋ฏธ์ง๋ฅผ ์ญ์ ํ  ๊ฒฝ์ฐ = -f๋ฅผ ๋ถ์ด๋ฉด ์ปจํ์ด๋๋ ํจ๊ป ๊ฐ์  ์ญ์ 
    $ docker rmi -f "์ด๋ฏธ์ง ์์ด๋"
    ```
  - ์ฐธ๊ณ  : https://velog.io/@frozenxnow/Mac%EC%97%90%EC%84%9C-docker%EB%A1%9C-%EC%9D%B4%EC%9A%A9%ED%95%98%EB%8D%98-MySQL-%EC%82%AD%EC%A0%9C%ED%95%98%EA%B8%B0

### 3. MySQL Docker ์ปจํ์ด๋ ์์ฑ ๋ฐ ์คํ

```bash
$ docker run --name <mysql-container> -e MYSQL_ROOT_PASSWORD=<password> -d -p 3306:3306 mysql:latest
```
```bash
$ docker run --platform linux/amd64 --name <mysql-container> -e MYSQL_ROOT_PASSWORD=<password> -d -p 3306:3306 mysql:5.7
```
- ์ต์ข ์๋ ฅ ์ฝ๋ -> ์ปจํ์ด๋ ์คํ ๋ฐ mysql ํ๊ฒฝ๋ณ์ ๋ณ๊ฒฝ ๋ช๋ น์ด ํฌํจ
```bash
$ docker run --platform linux/amd64 --name <mysql-container> -e MYSQL_ROOT_PASSWORD=<password> -d -p 3306:3306 mysql:5.7 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
```
### 4. MySQL Docker ์ปจํ์ด๋ ๋ฆฌ์คํธ ์ถ๋ ฅ

```bash
$ docker ps -a
```

### 5. MySQL Docker ์ปจํ์ด๋ ์์/์ค์ง/์ฌ์์

```bash
# MySQL Docker ์ปจํ์ด๋ ์ค์ง
$ docker stop mysql-container

# MySQL Docker ์ปจํ์ด๋ ์์
$ docker start mysql-container

# MySQL Docker ์ปจํ์ด๋ ์ฌ์์
$ docker restart mysql-container
```

### 6. MySQL Docker ์ปจํ์ด๋ ์ ์

```bash
$ docker exec -it mysql-container bash
```

### 7. MySQL Docker ์ฐ๊ฒฐ ํด์ 
```bash
$ exit
```

### 8. ๋์ปค(mysql) ์ปจํ์ด๋ ์คํ(bash ์ ์) ํ MySQL ์๋ฒ ์ ์

```bash
$ mysql -u root -p
```
- ์ดํ ๋น๋ฐ๋ฒํธ ์๋ ฅ

---

# utf-8 ์ค์ 
์ฐธ๊ณ  : https://velog.io/@1yangsh/%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88-MySQL-%ED%95%9C%EA%B8%80-%EC%9D%B8%EC%BD%94%EB%94%A9-%ED%95%B4%EA%B2%B0-%EB%B0%A9%EB%B2%95
- ์๋ฒ ์ ์ ํ 'status'๋ฅผ ํตํด ํ์ฌ ์ค์  ํ์ธ
- ์ปจํ์ด๋ ๋ด๋ถ๋ก ๋์๊ฐ vi editor ์ค์น ์ฌ๋ถ ํ์ธ
- ์๋ค๋ฉด vim ์ค์น (apt-get์ด ์๋๋ค๋ฉด 'yum' ์ฌ์ฉ)
```bash
$ apt-get update
$ apt-get install vim nano
```
- ์ค์น ํ ํ์ผ์ ์ ๊ทผ
```bash
$ vim /etc/mysql/my.cnf
```
- ์๋์ ๋ด์ฉ ์ถ๊ฐ
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
- ์ถ๊ฐ์ ์ผ๋ก Linux Vi editor (vi ํธ์ง๊ธฐ) ๋ช๋ น์ด๋ ์๋์ ๋งํฌ ์ฐธ๊ณ 
  - https://spidyweb.tistory.com/125
- ์ดํ ์๋ฒ์์ 'status' ํ์ธ  
![image](https://user-images.githubusercontent.com/118447769/220188108-457859d1-7de3-4026-9e6d-4473cae10c39.png)

---

# MySQL ๋ช๋ น์ด
์ฐธ๊ณ  : https://velog.io/@kimtaeeeny/mysql-%EB%AA%85%EB%A0%B9%EC%96%B4-%EB%AA%A8%EC%9D%8C

### 1. ์๋ฒ ๊ตฌ๋, ์ข๋ฃ
```bash
# ๊ตฌ๋
$ mysql.server start
# ์ข๋ฃ
$ mysql.server stop
```

### 2. mysql ์ ์, ๋๊ฐ๊ธฐ
```bash
# ์ ์ 
$ mysql -uroot -p
# ๋๊ฐ๊ธฐ
$ EXIT
```

### 3. ๋ฐ์ดํฐ๋ฒ ์ด์ค ์ด๋, ์์ฑ, ์ญ์ 
```bash
# ์ด๋
$ SHOW DATABASES;
# ์์ฑ
$ CREATE DATABASE ๋ฐ์ดํฐ๋ฒ ์ด์ค์ด๋ฆ;
# ์ญ์ 
$ DROP DATABASE ๋ฐ์ดํฐ๋ฒ ์ด์ค์ด๋ฆ;
```
- ๋ฐ์ดํฐ๋ฒ ์ด์ค ๋ฐ ์ผ๋ฐ ์ฌ์ฉ์ ์์ฑ
```bash
$ CREATE DATABASE mydb;
# ์์ด๋ ๋ฐ ํจ์ค์๋ ์ค์ 
$ CREATE USER 'myuserid'@'%' IDENTIFIED BY 'mypassword';
$ GRANT ALL ON mydb.* TO 'myuserid'@'%';
$ FLUSH PRIVILEGES;

# mydb: ๋ฐ์ดํฐ๋ฒ ์ด์ค ์ด๋ฆ
# myuserid: ์ฌ์ฉ์ id
# mypassword: ์ฌ์ฉ์ ํจ์ค์๋
```

### 4. ์ํํ์ธ
```bash
$ STATUS;
```

### 5. ์๊ฐ์ค์ 
```bash
$ mysql> set global time_zone='Asia/Seoul';
$ mysql> set time_zone='Asia/Seoul';

$ mysql> select @@global.time_zone, @@session.time_zone;
```

### 6. ๋น๋ฐ๋ฒํธ ๋ณ๊ฒฝ (mysql ์ ์ ๊ฐ๋ฅ ์)
```bash
$ ALTER USER 'root'@'localhost' IDENTIFIED WITH caching_sha2_password BY '1234';
```

### 7. ๋น๋ฐ๋ฒํธ ๋ณ๊ฒฝ (๋ถ์ค์)
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
- ๋ฃจํธ ์ฌ์ฉ์๋ก ๋ก๊ทธ์ธ ํ ์ผ๋ฐ ์ฌ์ฉ์ ํจ์ค์๋๋ ์ฝ๊ฒ ๋ณ๊ฒฝ ๊ฐ๋ฅ
```bash
$ SET PASSWORD FOR 'honux'@'%'='new_password';
$ FLUSH PRIVILEGES;
```

---

# ์ธ๋ถ์ ์ ํ์ฉํ๊ธฐ
ํ์ฌ ์ฌ์ฉ๋๋ ์๋ฒ์ MySQL์ ์ ์ํ๊ธฐ ์ํด์๋ ๋ชจ๋  IP์์ ์ ์์ ํ  ์ ์๋๋ก ์ค์ ํด์ผ ํ๋ค.

### 1. ์ค์  ํ์ธ
```bash
mysql> SELECT Host FROM mysql.user WHERE user='root';
```
- query๋ฌธ์ ํตํด HOST์ค์ ์ ํ์ธํ๋ค. %๋ ๋ชจ๋  IP์ ์ ์์ ์ค์ ํ๋ค๋ ๊ฒ์ ์๋ฏธํ๋ค.
- query๋ฌธ ํ์ธ ์ %๊ฐ ์ค์  ๋์ด ์์ ๊ฒฝ์ฐ ๋ค์ ์์๋ ๋์ด๊ฐ๋ ๋๋ค.

### 2. ๋ชจ๋  IP ์ ์ ํ์ฉ
```bash
mysql> INSERT INTO mysql.user (host,user,password) VALUES ('%','root',password('ํจ์ค์๋'));
mysql> GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';
mysql> FLUSH PRIVILEGES;
```
- ์์ query๋ฅผ ์ฐจ๋ก๋ก ์คํํ๋ค. ์ด ํ 1๋ฒ์ query๋ฅผ ์คํํ์ฌ Host์ %๊ฐ ์ถ๊ฐ ๋์๋์ง ํ์ธํ๋ค.

### 3. my.cnf ์์ 

- viํธ์ง๊ธฐ๋ฅผ ์ด์ฉํ์ฌ my.cnfํ์ผ์ ์ฝ๋๋ค.
  ```bash
  [root@root guru]vi /etc/my.cnf
  ```
- bind-address = 127.0.0.1 ์์ #์ ๋ถ์ฌ์ ์ฃผ์ ์ฒ๋ฆฌ (#์ ์ฃผ์์ ์๋ฏธ)

- mysql ์ฌ์์
  ```bash
  [root@root guru]service mysqld restart
  ```

### 4. ์ ์ ํ์ธ

- ์๋ ๋ช๋ น์ด๋ฅผ ํตํ์ฌ ์ ์์ด ์ ์์ ์ผ๋ก ๋๋์ง ํ์ธ
```bash
[root@root guru]mysql -u root -h 129.129.12.12 -p
```

---

# Mission. ๋ฐ์ดํฐ ์์ฑ
### ๋ฐ์ดํฐ ๋ฒ ์ด์ค ํ์ธ
![image](https://user-images.githubusercontent.com/118447769/220197902-9707ff47-c81a-40f7-b93d-47d4a8b0d87c.png)

### ๋ฐ์ดํฐ๋ฒ ์ด์ค ์ ํ
```bash
$ use mydb;
```

## user_log ํ์ด๋ธ ์์ฑ ๋ฐ ํ์ธ
![image](https://user-images.githubusercontent.com/118447769/220197742-31eb4538-d2f9-4a3f-ba13-9bb31d2b2767.png)  

![image](https://user-images.githubusercontent.com/118447769/220198160-1df13ee3-a215-48d5-90a7-bae6a71ea5f0.png)

- CHAR(M), VARCHAR(M) ์ฐจ์ด์ 
  - M : ์ ์ฅํ  ์ ์๋ ๋ฌธ์์ด์ ์ต๋๊ธธ์ด (CHAR๋ 0~255, VARCHAR๋ 0~65535)
  - CHAR(M)
    - ๊ณ ์  ๋ฌธ์์ผ ๋ ์ฌ์ฉ ex) ์ ํ๋ฒํธ
    - ์ค์ ํ ํฌ๊ธฐ๋ณด๋ค ์์ ๊ธธ์ด์ ๋ฌธ์์ด์ผ ๋, ๊ณต๋ฐฑ์ผ๋ก ์ฑ์ ๊ธธ์ด๋ฅผ M๋งํผ ์ฑ์
  - **VARCHAR(M)**
    - ๊ฐ๋ณ ๋ฌธ์์ผ ๋ ์ฌ์ฉ ex) ๊ฒ์ํ ์ ๋ชฉ
    - ์ค์  ์๋ ฅ๋ ๋ฌธ์์ด์ ๊ธธ์ด๋งํผ๋ง ์ ์ฅํ๊ณ  ์ฌ์ฉ
  - char์ ๊ฒ์์ด๋ ์ฑ๋ฅ์ด ๋ฐ์ด๋ ์ ํํ ์ผ๋ ์ฌ์ฉ๋๋ฉฐ, varchar๋ ๊ฐ๋ณ์ ์ผ๋ ์ฌ์ฉ๋๋ค.

- **DECIMAL(M,D)** : ๊ณ ์  ์์์  ํ์(fixed-point types)
  - M์ ์์ ๋ถ๋ถ์ ํฌํจํ ์ค์์ ์ด ์๋ฆฟ์๋ฅผ ๋ํ๋ด๋ฉฐ, ์ต๋๊ฐ์ 65์ด๋ค.
  - D๋ ์์ ๋ถ๋ถ์ ์๋ฆฟ์๋ฅผ ๋ํ๋ด๋ฉฐ, D๊ฐ 0์ด๋ฉด ์์ ๋ถ๋ถ์ ๊ฐ์ง์ง ์๋๋ค.
  - 
- **DATETIME** : DATETIME๋ ๋ ์ง์ ํจ๊ป ์๊ฐ๊น์ง ์ ์ฅํ  ์ ์๋ ํ์
  - ๊ธฐ๋ณธ ํ์์ 'YYYY-MM-DD HH:MM:SS'์ด๋ฉฐ, ์ด๋ ์ ์ฅํ  ์ ์๋ ๋ฒ์๋ '1000-01-01 00:00:00'๋ถํฐ '9999-12-31 23:59:59'๊น์ง์๋๋ค.

- ํ์ ์ฐธ๊ณ 
  - ์ซ์ ํ์ : http://www.tcpschool.com/mysql/mysql_datatype_numeric
  - ๋ฌธ์์ด ํ์ : http://www.tcpschool.com/mysql/mysql_datatype_string
  - ๋ ์ง์ ์๊ฐ ํ์ : http://www.tcpschool.com/mysql/mysql_datatype_dateTime

### ๋ฐ์ดํฐ๋ฒ ์ด์ค ์ฐ๊ฒฐ
์ฐธ๊ณ  : https://hihellosuah.tistory.com/91  

![image](https://user-images.githubusercontent.com/118447769/220204158-b6c31695-b12d-4463-9871-35f94f0fa214.png)  

![image](https://user-images.githubusercontent.com/118447769/220204445-63d16169-fbdb-40e3-b108-6a87b81935ce.png)  

---

### ๋ฐ์ดํฐ ์์ฑ
- nickname : '์์ด ๋จ์ด 100๊ฐ + ๋๋ค ๋ฌธ์์ด 3์๋ฆฌ + ๋๋ค ์ซ์ 4์๋ฆฌ' ๋ก ์์ฑ
  ```java
  StringBuilder randomString = new StringBuilder();
  for (int i = 0; i < 3; i++) {
      randomString.append((char) (rd.nextInt(26) + 97));//๋๋ค ๋ฌธ์์ด 3์๋ฆฌ
  }
  StringBuilder randomNumber = new StringBuilder();
  for (int i = 0; i < 4; i++) {
      randomNumber.append(rd.nextInt(10));//๋๋ค ์ซ์ 4์๋ฆฌ
  }
  nickname = words[rd.nextInt(100)] + randomString + randomNumber;
  ```
- money : 1 ~ 100,000 ์ฌ์ด์ ๊ฐ์ ์ ๋นํ๊ฒ ๋ถํฌํ๊ฒ ์์ฑ
  ```java
  money = rd.nextInt(100000) + 1;
  ```
- last_visit : ์ต๊ทผ ํ๋ฌ ์ฌ์ด ๋๋ค ์๊ฐ์ผ๋ก ์์ฑ
  ```java
  String[] dateTime = new String[6];//YYYY-MM-DD HH:MM:SS
  dateTime[0] = Integer.toString(2023);
  dateTime[1] = String.format("%02d", rd.nextInt(2) + 1);
  if (dateTime[1].equals("01")) {//1์์ผ ๊ฒฝ์ฐ
  dateTime[2] = Integer.toString(rd.nextInt(11) + 21);//21~31์ผ
  } else if (dateTime[1].equals("02")) {//2์์ผ ๊ฒฝ์ฐ
  dateTime[2] = String.format("%02d", rd.nextInt(21) + 1);//1~21์ผ
  }
  dateTime[3] = String.format("%02d", rd.nextInt(24));
  dateTime[4] = String.format("%02d", rd.nextInt(60));
  dateTime[5] = String.format("%02d", rd.nextInt(60));
  lastVisit = dateTime[0] + "-" + dateTime[1] + "-" + dateTime[2] + " " + dateTime[3] + ":" + dateTime[4] + ":" + dateTime[5];
  ```

## DB์ ์ ์ฅ
10000๊ฐ์ฉ ๋์ด์ ์ ์ฅ -> 10000๊ฐ ๋น ์ฝ 5~6์ด ์์ -> 1000000๊ฐ : 500~600์ด ์์
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

### ํ์ด๋ธ ๋ด ๋ฐ์ดํฐ ์ญ์ 

```bash
$ truncate <tableName>;
```

### ํ์ด๋ธ ๋ด ๋ฐ์ดํฐ ์กฐํ
```bash
$ select*from <tableName>;
```

### addBatch์ executeBatch๋ฅผ ์ด์ฉํ ๋์ฉ๋ ๋ฐ์ดํฐ ์ฒ๋ฆฌ
์ฐธ๊ณ  : https://fruitdev.tistory.com/111

---

## ๊ฒฐ๊ณผ

![image](https://user-images.githubusercontent.com/118447769/220266028-7024239f-b27b-4a34-8ff5-201028164c9f.png)  

![image](https://user-images.githubusercontent.com/118447769/220267114-594ae638-b74b-481b-9dd8-e1846c37331f.png)