/*
 * Não precisa criar a table diretamente usando JPA, pois o autoconfig do spring
 * boot faz isso automaticamente.
create table person
(
  id integer not null,
  name varchar(255) not null,
  location varchar(255),
  birth_date timestamp,
  primary key(id)
);
*/

INSERT INTO PERSON
    (ID, NAME, LOCATION, BIRTH_DATE)
VALUES(10001, 'Guilherme', 'Campina Grande', sysdate());
INSERT INTO PERSON
    (ID, NAME, LOCATION, BIRTH_DATE)
VALUES(10002, 'Rique', 'Natal', sysdate());
INSERT INTO PERSON
    (ID, NAME, LOCATION, BIRTH_DATE)
VALUES(10003, 'Vitória', 'Campina Grande', sysdate());
INSERT INTO PERSON
    (ID, NAME, LOCATION, BIRTH_DATE)
VALUES(10004, 'Mário', 'Pombal', sysdate());
INSERT INTO PERSON
    (ID, NAME, LOCATION, BIRTH_DATE)
VALUES(10005, 'Maria', 'Natal', sysdate());
