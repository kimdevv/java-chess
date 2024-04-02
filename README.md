# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

## docker 실행

```commandline
docker-compose -p chess up -d
```

## piece 테이블 생성

```mysql-sql
USE chess;

CREATE TABLE piece (
    id BIGINT NOT NULL AUTO_INCREMENT,
    chess_room_id BIGINT NOT NULL,
    position VARCHAR(2) NOT NULL,
    type VARCHAR(30) NOT NULL,
    team VARCHAR(10) NOT NULL,

    PRIMARY KEY (id)
);
```

## chess_room 테이블 생성

```mysql-sql
USE chess;

CREATE TABLE chess_room (
    id BIGINT NOT NULL,
    turn VARCHAR(10) NOT NULL,

    PRIMARY KEY (id)
);
```
