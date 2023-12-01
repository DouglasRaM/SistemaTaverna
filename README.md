# SistemaTaverna

##Banco de dados que estamos trabalhando com
>CREATE database Taverna;
>USE Taverna;
>
>create table Quartos(
>ID INT PRIMARY KEY AUTO_INCREMENT, 
>NOME CHAR(45), 
>TIPO CHAR(45), 
>CAMAS INT, 
>DIARIA DOUBLE,
>OCUPADO boolean
>);
>
>insert into quartos (NOME, TIPO, CAMAS, DIARIA, OCUPADO)
>values
>('RECANTO DAS FADAS','VIP',2,569,true),
>('PESTE NEGRA','NORMAL',5, 66.66,true),
>('BURACO DO OGRO','NORMAL',5,30,true),
>('RECINTO DA POBREZA','NORMAL',5,30,false),
>('GANGRENA ABERTA','MISERÁVEL', 10, 2,false);
