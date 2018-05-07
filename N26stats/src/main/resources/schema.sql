create table if not exists transaction
(
id INT auto_increment,
amount DOUBLE not null,
time BIGINT,
 primary key(id)
);