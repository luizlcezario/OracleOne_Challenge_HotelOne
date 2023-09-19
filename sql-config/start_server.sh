#!/bin/sh
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
	CREATE TABLE hospedes (
      id SERIAL PRIMARY KEY,
      nome varchar(255) NOT NULL,
      sobrenome varchar(255) NOT NULL,
      datanascimento DATE NOT NULL,
      nascionalidade varchar(255) NOT NULL,
      telefone varchar(255) UNIQUE NOT NULL
  );
  CREATE TABLE reservas (
    id SERIAL PRIMARY KEY,
    numeroreserva SERIAL UNIQUE,
    dataentrada date NOT NULL,
    datasaida date NOT NULL,
    valor int NOT NULL,
    formapagamento int NOT NULL,
    fkidhospede integer
     REFERENCES hospedes(id)
  );
  INSERT INTO hospedes( nome, sobrenome, datanascimento, nascionalidade, telefone)
      values ('milena',  'carecho','1999-02-14','brasileira', '12982362731');
 INSERT INTO reservas(dataentrada, datasaida, valor, formapagamento,  fkidhospede) values(
      '2023-10-26',
      '2023-10-29',
      360,
      1,
      (SELECT id FROM Hospedes WHERE telefone = '12982362731')
    );
  INSERT INTO hospedes( nome, sobrenome, datanascimento, nascionalidade, telefone)
    values ('luiz',  'lima','2000-10-26','brasileira', '12982362344');
 INSERT INTO reservas(dataentrada, datasaida, valor, formapagamento,  fkidhospede) values(
    '2023-10-26',
    '2023-10-29',
    360,
    1,
    (SELECT id FROM Hospedes WHERE telefone = '12982362344')
  );
   INSERT INTO reservas(dataentrada, datasaida, valor, formapagamento,  fkidhospede) values(
      '2023-11-15',
      '2023-10-18',
      480,
      1,
      (SELECT id FROM Hospedes WHERE telefone = '12982362344')
    );
   INSERT INTO reservas(dataentrada, datasaida, valor, formapagamento,  fkidhospede) values(
      '2023-10-10',
      '2023-10-13',
      300,
      1,
      (SELECT id FROM Hospedes WHERE telefone = '12982362344')
    );
EOSQL