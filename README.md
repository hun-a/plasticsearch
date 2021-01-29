# Plasticsearch

## 의미
- plastic: 온순한, 모양을 이루는, 성형의
- search: 검색
- 사실은 [elasticsearch](https://www.elastic.co/elasticsearch/) 모조품

## 정의
- [Apache Lucene](https://lucene.apache.org/) 기반의 **매우 간단**하게 구현된 검색 API 서버

## APIs
* 인덱스 생성

  |Endpoint|Example|
  |--------|-------|
  |PUT /{indexName}|curl -XPUT http://localhost:8080/book|

* Document 생성

  |Endpoint|Request Body|Example|
  |--------|------------|-------|
  |POST /{indexName}/{id}|JSON|curl -XPOST http://localhost:8080/book/1 -H 'Content-Type: application/json' -d '{"name":"토비의 스프링 3", "author":"토비"}'|

* Document 수정

  |Endpoint|Request Body|Example|
  |--------|------------|-------|
  |POST /{indexName}/_update/{id}|JSON|curl -XPOST http://localhost:8080/book/_update/1 -H 'Content-Type: application/json' -d '{"name":"토비의 스프링 3.1", "author":"토비"}'|

* Document 삭제

  |Endpoint|Example|
  |--------|-------|
  |DELETE /{indexName}/_doc/{id}|curl -XDELETE http://localhost:8080/book/_doc/1|
  |DELETE /{indexName}/_doc/all|curl -XDELETE http://localhost:8080/book/_doc/all|

* 검색

  |Endpoint|Query Parameter|Example|
  |--------|---------------|-------|
  |GET /{indexName}/_search|`q`: 검색어<br>`field`: 검색할 필드명<br>`size`: 검색될 문서 갯수|curl -XGET http://localhost:8080/book/_search?q=토비&field=name&size=10|
  |GET /{indexName}/_search/all| |curl -XGET http://localhost:8080/book/_search/all|

# 참고
- [Lucene 8.7.0 core API](https://lucene.apache.org/core/8_7_0/core/overview-summary.html#overview.description)
- [Tutorials point - Lucene](https://www.tutorialspoint.com/lucene/index.htm)