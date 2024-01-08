create database user_login;
use user_login;
create table user_data(
    user_id	bigint,
    name varchar(255),
    password	varchar(255),
    role ENUM('EMPLOYER','JOBSEEKER')
    );

create table job_list(
    deadline	date,
    job_id	bigint,
    user_id	bigint,description	varchar(255),
    requirements	varhcar(255),
    title	varchar(255)
);


insert into user_data values(3,'Shiny','675','JOBSEEKER');
src/main/resources/sql/JobListQueries.sql

SELECT * FROM job_list WHERE title = :title;

