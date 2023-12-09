// 노드 & 그와 연결된 관계 삭제
match (a:Developer) optional match (a)-[r]-() delete a, r;

create (d:Developer {name:'robin', age:33, country:'Korea'}) return d;
create (d:Developer {name:'dodo', age:42, country:'Germany'}) return d;
create (d:Developer {name:'amud', age:25, country:'Korea'}) return d;
create (d:Developer {name:'cindy', age:15, country:'USA'}) return d;
create (d:Developer {name:'hana', age:20, country:'Canada'}) return d;
create (d:Developer {name:'jessica', age:22, country:'Germany'}) return d;

MATCH (a:Developer) WHERE a.name = "dodo"
MATCH (b:Developer) WHERE b.name="robin"
CREATE (a) -[r:IS_FRIEND]->(b) RETURN r;

MATCH (a:Developer) WHERE a.name = "amud"
MATCH (b:Developer) WHERE b.name="robin"
CREATE (a) -[r:IS_FRIEND]->(b) RETURN r;

MATCH (a:Developer) WHERE a.name = "cindy"
MATCH (b:Developer) WHERE b.name="dodo"
CREATE (a) -[r:IS_FRIEND]->(b) RETURN r;

MATCH (a:Developer) WHERE a.name = "hana"
MATCH (b:Developer) WHERE b.name="dodo"
CREATE (a) -[r:IS_FRIEND]->(b) RETURN r;

MATCH (a:Developer) WHERE a.name = "hana"
MATCH (b:Developer) WHERE b.name="amud"
CREATE (a) -[r:IS_FRIEND]->(b) RETURN r;

MATCH (a:Developer) WHERE a.name = "jessica"
MATCH (b:Developer) WHERE b.name="cindy"
CREATE (a) -[r:IS_FRIEND]->(b) RETURN r;


// 순환오류 테스트용 데이터 추가
create (d:designer {name:'lala', age:20, country:'Canada'}) return d;
create (d:designer {name:'tete', age:22, country:'Germany'}) return d;
create (d:designer {name:'bruno', age:33, country:'Korea'}) return d;

MATCH (a:designer) WHERE a.name = "tete"
MATCH (b:designer) WHERE b.name="lala"
CREATE (a) -[r:IS_FRIEND]->(b) RETURN r;

MATCH (a:designer) WHERE a.name = "lala"
MATCH (b:designer) WHERE b.name="tete"
CREATE (a) -[r:IS_FRIEND]->(b) RETURN r;

MATCH (a:designer) WHERE a.name = "bruno"
MATCH (b:designer) WHERE b.name="lala"
CREATE (a) -[r:IS_FRIEND]->(b) RETURN r;
