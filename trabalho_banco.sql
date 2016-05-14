--Grupo: Bredon Santos, Homero Oliveira e Wagner Parnoff
-- CREATE
CREATE TABLE Equipamentos(
  cod_equipamento number,
  data_aquisicao date,
  descricao varchar(50),
  custo_diario decimal,
  tipo_equipamento varchar(11),--FIXO, MOVEL, USO EXTERNO
  em_manutencao char(1) check(em_manutencao in ('S', 'N')),
  constraint pk_cod_equipamento primary key(cod_equipamento),                        
  constraint chk_custo check (custo_diario > 0)
)
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
	   constraint chk_salario check (salario_mensal > 0)
 )
/
CREATE TABLE Reservas(
cod_equipamento number,
cod_matricula number,
data_inicial date,
data_final date,
constraint fk_cod_equipamento foreign key (cod_equipamento) references Equipamentos(cod_equipamento),
constraint fk_cod_matricula foreign key (cod_matricula) references Funcionarios(cod_matricula),
constraint chk_datas check (data_inicial <= data_final)
);
--INSERT
--Equipamentos
INSERT INTO Equipamentos(cod_equipamento, data_aquisicao, descricao, custo_diario, tipo_equipamento, em_manutencao)
VALUES (0001, '20-12-2010', 'Máquina de Escrever Olivetti Rara', 50.00, 'FIXO', 'N');
/
INSERT INTO Equipamentos(cod_equipamento, data_aquisicao, descricao, custo_diario, tipo_equipamento, em_manutencao)
VALUES (0002, '20-12-1990', 'Foice Clássica de Roçar Mato', 10.50, 'MOVEL', 'N');
/
INSERT INTO Equipamentos(cod_equipamento, data_aquisicao, descricao, custo_diario, tipo_equipamento, em_manutencao)
VALUES (0003, '10-05-2016', 'Maca Cirurgica Comum', 100.00, 'USO EXTERNO', 'N');
/
--Funcionarios
INSERT INTO Funcionarios(cod_matricula, senha, nome, data_nascimento, data_admissao, sexo, endereco, salario_mensal)
VALUES (0001, 'password1', 'Débora Santos', '11-03-1970', '03-11-1998', 'F', 'Rua do Otmo 01', 1200.00);
/ 
INSERT INTO Funcionarios(cod_matricula, senha, nome, data_nascimento, data_admissao, sexo, endereco, salario_mensal)
VALUES (0002, 'password2', 'Jailson Mendes', '11-04-1980', '20-03-2001', 'M', 'Rua Rio de Janeiro ', 2500.00);
/
INSERT INTO Funcionarios(cod_matricula, senha, nome, data_nascimento, data_admissao, sexo, endereco, salario_mensal)
VALUES (0003, 'password3', 'Filipe Smith', '11-07-1990', '20-12-1999', 'M', 'Rua da Samu 1618', 800.00);
/
--Reservas
INSERT INTO Reservas(cod_equipamento, cod_matricula, data_inicial, data_final)
VALUES (0001, 0001, '20-03-2014', '20-03-2015');
/
INSERT INTO Reservas(cod_equipamento, cod_matricula, data_inicial, data_final)
VALUES (0002, 0002, '12-02-2012', '24-04-2014');
/
INSERT INTO Reservas(cod_equipamento, cod_matricula, data_inicial, data_final)
VALUES (0003,0003, '13-05-2016', '14-05-2016');

COMMIT