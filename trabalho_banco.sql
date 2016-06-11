--Grupo: Bredon Santos, Homero Oliveira e Wagner Parnoff
-- CREATE
CREATE TABLE Equipamentos(
  cod_equipamento number,
  data_aquisicao date,
  descricao varchar(50),
  custo_diario decimal,
  tipo_equipamento varchar(11),--FIXO, MOVEL, USO EXTERNO
  em_manutencao INTEGER(1) check(em_manutencao in (1, 0)),
  constraint pk_cod_equipamento primary key(cod_equipamento),                        
  constraint chk_custo check (custo_diario >= 0)
)
/
CREATE SEQUENCE SEQ_EQUIPAMENTO;
/
CREATE TABLE Funcionarios(
     cod_matricula number,
     senha varchar(20),
     nome varchar(20),
     data_nascimento date,
     data_admissao date,
     sexo varchar(1),
     endereco varchar(20),
     salario_mensal decimal,
     constraint pk_cod_matricula primary key (cod_matricula),
     constraint chk_sexo check( sexo in ('M', 'F')),
	   constraint chk_salario check (salario_mensal >= 0)
 )
/
CREATE SEQUENCE SEQ_FUNCIONARIO;
/
CREATE TABLE Reservas(
cod_reserva number PRIMARY KEY,
cod_equipamento number,
cod_matricula number,
data_inicial date,
data_final date,
constraint fk_cod_equipamento foreign key (cod_equipamento) references Equipamentos(cod_equipamento),
constraint fk_cod_matricula foreign key (cod_matricula) references Funcionarios(cod_matricula),
constraint chk_datas check (data_inicial <= data_final)
);
/
CREATE SEQUENCE SEQ_RESERVA;
/

--INSERT
--Equipamentos
INSERT INTO Equipamentos(cod_equipamento, data_aquisicao, descricao, custo_diario, tipo_equipamento, em_manutencao)
VALUES (SEQ_EQUIPAMENTO.nextval, '20-12-2010', 'Máquina de Escrever Olivetti Rara', 50.00, 'FIXO', 0);
/
INSERT INTO Equipamentos(cod_equipamento, data_aquisicao, descricao, custo_diario, tipo_equipamento, em_manutencao)
VALUES (SEQ_EQUIPAMENTO.nextval, '20-12-1990', 'Foice Clássica de Roçar Mato', 10.50, 'MOVEL', 0);
/
INSERT INTO Equipamentos(cod_equipamento, data_aquisicao, descricao, custo_diario, tipo_equipamento, em_manutencao)
VALUES (SEQ_EQUIPAMENTO.nextval, '10-05-2016', 'Maca Cirurgica Comum', 100.00, 'USO EXTERNO', 0);
/
--Funcionarios
INSERT INTO Funcionarios(cod_matricula, senha, nome, data_nascimento, data_admissao, sexo, endereco, salario_mensal)
VALUES (SEQ_FUNCIONARIO.nextval, 'password1', 'Débora Santos', '11-03-1970', '03-11-1998', 'F', 'Rua do Otmo 01', 1200.00);
/ 
INSERT INTO Funcionarios(cod_matricula, senha, nome, data_nascimento, data_admissao, sexo, endereco, salario_mensal)
VALUES (SEQ_FUNCIONARIO.nextval, 'password2', 'Jailson Mendes', '11-04-1980', '20-03-2001', 'M', 'Rua Rio de Janeiro ', 2500.00);
/
INSERT INTO Funcionarios(cod_matricula, senha, nome, data_nascimento, data_admissao, sexo, endereco, salario_mensal)
VALUES (SEQ_FUNCIONARIO.nextval, 'password3', 'Filipe Smith', '11-07-1990', '20-12-1999', 'M', 'Rua da Samu 1618', 800.00);
/
--Reservas
INSERT INTO Reservas(cod_reserva, cod_equipamento, cod_matricula, data_inicial, data_final)
VALUES (SEQ_RESERVA.nextval,0001, 0001, '20-03-2014', '20-03-2015');
/
INSERT INTO Reservas(cod_reserva, cod_equipamento, cod_matricula, data_inicial, data_final)
VALUES (SEQ_RESERVA.nextval,0002, 0002, '12-02-2012', '24-04-2014');
/
INSERT INTO Reservas(cod_reserva, cod_equipamento, cod_matricula, data_inicial, data_final)
VALUES (SEQ_RESERVA.nextval,0003, 0003, '13-05-2016', '14-05-2016');

COMMIT;

--4
SELECT  r.cod_equipamento, r.cod_matricula ,f.NOME, e.descricao ,r.DATA_INICIAL, r.DATA_FINAL from reservas r
  join FUNCIONARIOS f on r.COD_MATRICULA = f.COD_MATRICULA
  join Equipamentos e on r.cod_equipamento = e.cod_equipamento
where r.DATA_INICIAL > sysdate;
--5
SELECT e.DESCRICAO, count(*) as quantidades_reservas,
    sum(e.CUSTO_DIARIO * (r.data_final - r.data_inicial + 1)) as soma_custo_diario
FROM RESERVAS R
  join EQUIPAMENTOS e on R.COD_EQUIPAMENTO = e.COD_EQUIPAMENTO
GROUP BY e.DESCRICAO;
--6
SELECT f.NOME, count(*), sum(e.CUSTO_DIARIO) as soma
FROM FUNCIONARIOS f
  JOIN RESERVAS r on f.COD_MATRICULA = r.COD_MATRICULA
  JOIN EQUIPAMENTOS e on r.COD_EQUIPAMENTO = e.COD_EQUIPAMENTO
GROUP BY f.NOME, e.DESCRICAO
ORDER BY soma DESC ;