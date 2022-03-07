CREATE USER APP_RECEITAS IDENTIFIED BY oracle;
GRANT CONNECT TO APP_RECEITAS;
GRANT CONNECT, RESOURCE, DBA TO APP_RECEITAS;
GRANT CREATE SESSION TO APP_RECEITAS;
GRANT DBA TO APP_RECEITAS;
GRANT CREATE VIEW, CREATE PROCEDURE, CREATE SEQUENCE to APP_RECEITAS;
GRANT UNLIMITED TABLESPACE TO APP_RECEITAS;
GRANT CREATE MATERIALIZED VIEW TO APP_RECEITAS;
GRANT CREATE TABLE TO APP_RECEITAS;
GRANT GLOBAL QUERY REWRITE TO APP_RECEITAS;
GRANT SELECT ANY TABLE TO APP_RECEITAS;
CREATE TABLE APP_RECEITAS.USUARIO(
    ID_USUARIO      NUMBER (38,0) PRIMARY KEY NOT NULL,
    NOME_USUARIO    VARCHAR2(20) NOT NULL,
    SENHA           VARCHAR2(8)  NOT NULL,
    NASCIMENTO      DATE NOT NULL,
    EMAIL           VARCHAR2(100) NOT NULL
);
CREATE TABLE APP_RECEITAS.RECEITA(
	ID_RECEITA 		NUMBER (38,0) PRIMARY KEY NOT NULL,
	ID_USUARIO      NUMBER (38,0) NOT NULL,
	NOME_RECEITA 	VARCHAR2(100) NOT NULL,
	TIPO_RECEITA 	VARCHAR2 (40) NOT NULL,
	CALORIAS 		NUMBER (5,0) NOT NULL,
	MODO_PREPARO 	VARCHAR (2000) NOT NULL,
	TEMPO_PREPARO	NUMBER (5,0) NOT NULL,
	MEDIA_PRECO		NUMBER (10,2) NOT NULL,
	TIPO_REFEICAO	VARCHAR2(40) NOT NULL,
    CONSTRAINT FK_RECEITA_USUARIO_CID_USUARIO FOREIGN KEY (ID_USUARIO) REFERENCES APP_RECEITAS.USUARIO (ID_USUARIO)
);
CREATE TABLE APP_RECEITAS.INGREDIENTE(
	ID_INGREDIENTE 	NUMBER (38,0) PRIMARY KEY NOT NULL,
	ID_RECEITA 		NUMBER (38,0) NOT NULL,
	NOME 			VARCHAR2(100) NOT NULL,
	QUANTIDADE		NUMBER(4,0) NOT NULL,
	CONSTRAINT FK_ING_RECEITA_CID_RECEITA FOREIGN KEY (ID_RECEITA) REFERENCES APP_RECEITAS.RECEITA (ID_RECEITA)
);
CREATE TABLE APP_RECEITAS.COMENTARIO (
    ID_COMENTARIO   NUMBER (38,0) PRIMARY KEY NOT NULL,
    ID_RECEITA      NUMBER (38,0)  NOT NULL,
    ID_USUCOMENT    NUMBER (38,0) NOT NULL,
    COMENTARIO      VARCHAR2 (2000) NOT NULL,
    CONSTRAINT FK_COMENT_RECEITA_CID_RECEITA FOREIGN KEY (ID_RECEITA) REFERENCES APP_RECEITAS.RECEITA (ID_RECEITA),
    CONSTRAINT FK_COMENT_USUARIO_CID_USUARIO FOREIGN KEY (ID_USUCOMENT) REFERENCES APP_RECEITAS.USUARIO (ID_USUARIO)
);
CREATE TABLE APP_RECEITAS.NOTA (
    ID_NOTA         NUMBER (38,0) PRIMARY KEY NOT NULL,
    ID_RECEITA      NUMBER (38,0) NOT NULL,
    ID_USUNOTA      NUMBER (38,0) NOT NULL,
    CLASSIFICACAO   NUMBER (1)  NOT NULL,
    CONSTRAINT FK_NOTA_RECEITA_CID_RECEITA FOREIGN KEY (ID_RECEITA) REFERENCES APP_RECEITAS.RECEITA (ID_RECEITA),
    CONSTRAINT FK_NOTA_USUARIO_CID_USUARIO FOREIGN KEY (ID_USUNOTA) REFERENCES APP_RECEITAS.USUARIO (ID_USUARIO)
);
CREATE SEQUENCE APP_RECEITAS.SEQ_USUARIO
    START WITH 1
    INCREMENT BY 1
    NOCACHE NOCYCLE;
CREATE SEQUENCE APP_RECEITAS.SEQ_RECEITA
    START WITH 1
    INCREMENT BY 1
    NOCACHE NOCYCLE;
CREATE SEQUENCE APP_RECEITAS.SEQ_INGREDIENTE
    START WITH 1
    INCREMENT BY 1
    NOCACHE NOCYCLE;
CREATE SEQUENCE APP_RECEITAS.SEQ_COMENTARIO
    START WITH 1
    INCREMENT BY 1
    NOCACHE NOCYCLE;
CREATE SEQUENCE APP_RECEITAS.SEQ_NOTA
    START WITH 1
    INCREMENT BY 1
    NOCACHE NOCYCLE;