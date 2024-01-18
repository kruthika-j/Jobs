create database user_login;
use user_login;
create table user_data(
    user_id	bigint PRIMARY KEY,
    name varchar(255),
    password varchar(255),
    role ENUM('EMPLOYER','JOBSEEKER')
    );

create table job_list(
    deadline date,
    job_id	bigint PRIMARY KEY,
    user_id	bigint,
    description	varchar(255),
    requirements varhcar(255),
    title varchar(255),
    FOREIGN KEY (user_id) REFERENCES user_data(user_id)
);

create table resume_management(
    resume_id bigint PRIMARY KEY,
    user_id bigint,
    file_path varchar(255),
    updation_date date,
    FOREIGN KEY (user_id) REFERENCES user_data(user_id)
);

create table job_application(
    application_id bigint PRIMARY KEY,
    application_date date,
    job_id bigint,
    user_id bigint 
    FOREIGN KEY (user_id) REFERENCES user_data(user_id),
    FOREIGN KEY (job_id) REFERENCES job_list(job_id)
)

insert into user_data values(1,'Alice','123','JOBSEEKER');
insert into user_data values(2,'Bob','456','EMPLOYER');
insert into user_data values(3,'Mathew','3565','EMPLOYER');
insert into user_data values(4,'Christy','4545','JOBSEEKER');

insert into job_list values('2024-01-01',1,2,'developing project','SpringBoot','Developer');
insert into job_list values('2024-01-01',2,2,'Teaching','Physics','Teacher');

insert into resume_management values('2024-01-01',1,1,'home\resume.doc');
insert into resume_management values('2024-01-01',2,1,'home\MyResume.doc');

insert into job_application (application_id,application_date,job_id,user_id) values(1,'2024-01-01',1,1);

select * from user_data;
select * from job_list;
select * from resume_management;
select * from job_application;

