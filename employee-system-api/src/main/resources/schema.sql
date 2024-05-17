CREATE TABLE IF NOT EXISTS persons(
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table if not exists department (id bigint(20) NOT NULL AUTO_INCREMENT, department_name varchar(255), primary key (id)) engine=InnoDB;
create table if not exists employees (id bigint not null auto_increment, emp_name varchar(255), salary bigint not null, department_id bigint, primary key (id)) engine=InnoDB;

 

