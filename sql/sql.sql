create table ti(a integer not null default '1',
b integer ,
c varchar(20));

insert into ti values(1,2,'4');

select *from ti;

insert into ti values(1,1,'这是程序写入');

delete from ti;

select count(*) from ti;

SELECT pg_file_read('pg_log/postgresql-2016-05-08_174432.log', 0, 50000);