#!/bin/sh
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
	CREATE TABLE Hospedes (
      id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
      nome varchar(255) NOT NULL,
      sobreNome varchar(255) NOT NULL,
      dataNascimento DATE NOT NULL,
      nascionalidade varchar(255) NOT NULL,
      telefone varchar(255) NOT NULL,
  );
  CREATE TABLE Reservas (
    id uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
    numberoReserva int UNIQUE NOT NULL,
    dataEntrada date NOT NULL,
    dataSaida date NOT NULL,
    valor int NOT NULL,
    formaPagamento int NOT NULL,
    fkIdHospede uuid FOREIGN KEY REFERENCES Hospedes(id)
  );
EOSQL