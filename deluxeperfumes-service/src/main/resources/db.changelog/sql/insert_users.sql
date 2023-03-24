--liquibase formatted sql
--changeset myname:create-multiple-tables splitStatements:true endDelimiter:;

INSERT INTO user_entity VALUES (1,"2022-08-24","5aa21472-bea0-44c0-aa02-71f88c895c57",null,"$2a$10$IDC1DJPoXUB4TWdUBcLs8.cLPqwFr/Y8x2Pxu/wMsgbyv5geMsoeO","user");
INSERT INTO user_entity VALUES (2,"2022-08-24","800361b3-41c1-4729-af3f-ec4c045d0b8a",null,"$2a$10$nJ6xwG7x2dRNyrGEYT4Xae2jH4xwHzY4OXtp1ls42xGl/0VH8VY3K","admin");

INSERT INTO user_entity_roles VALUES(1,"ROLE_USER");
INSERT INTO user_entity_roles VALUES(2,"ROLE_ADMIN");
INSERT INTO user_entity_roles VALUES(2,"ROLE_USER");