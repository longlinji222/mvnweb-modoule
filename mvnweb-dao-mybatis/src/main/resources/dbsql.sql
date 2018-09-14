create table myuser(

id int not null,

status int not null,

userinfo varchar2(80) not null,

username varchar2(80) not null,

userpwd varchar2(80) not null,

createTime date

);
//files
create table files(

id varchar2(256) not null,

filesname varchar2(100) not null,

fileloc varchar2(1000) not null,

createTime date not null

);

insert into files values('101','dept.xls','E:\temp\mvnweb-modoule\gitCode\mvnweb-modoule\mvnweb-ssme\src\main\webapp\temp',sysdate);
