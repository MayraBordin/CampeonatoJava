-- Criação da tabela 'times'
CREATE TABLE times (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    grito_de_guerra VARCHAR(255),
    data_fundacao DATE
);

-- Criação da tabela 'campeonatos'
CREATE TABLE campeonatos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_inicio DATE,
    data_fim DATE
);

-- Criação da tabela 'partidas'
CREATE TABLE partidas (
    id SERIAL PRIMARY KEY,
    id_time_a INT NOT NULL REFERENCES times(id),
    id_time_b INT NOT NULL REFERENCES times(id),
    data_partida DATE,
    resultado VARCHAR(50)
);

-- Criação da tabela 'fases'
CREATE TABLE fases (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    id_campeonato INT NOT NULL REFERENCES campeonatos(id)
);

-- Criação da tabela 'partidas_fases' para associar partidas às fases
CREATE TABLE partidas_fases (
    id SERIAL PRIMARY KEY,
    id_partida INT NOT NULL REFERENCES partidas(id),
    id_fase INT NOT NULL REFERENCES fases(id)
);
