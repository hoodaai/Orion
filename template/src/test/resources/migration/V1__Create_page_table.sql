create table pages (
    id int not null auto_increment primary key,
    page_id varchar(100) not null,
    about  varchar(100) not null,
    access_token  varchar(100) not null,
    ad_campaign  varchar(100) not null,
    category  varchar(100) not null
);