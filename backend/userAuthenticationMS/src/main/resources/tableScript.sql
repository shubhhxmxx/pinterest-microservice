create database user;
use user;

create table user (
        user_id decimal(38,0) not null,
        email varchar(255),
        mobile_number decimal(38,0),
        password varchar(255),
        profile_pic varbinary(255),
        signup_date datetime(6),
        user_name varchar(255),
        primary key (user_id)
    );
alter table user add constraint unique (email);
alter table user add constraint unique(mobile_number);
alter table user add constraint unique(user_name);



create database content;
use content;

create table board (
        board_id decimal(38,0) not null,
        board_name varchar(255),
        board_pic varbinary(255),
        date_created datetime(6),
        last_updated_date datetime(6),
        user_id decimal(38,0),
        primary key (board_id)
   );

create table pin (
        pin_id decimal(38,0) not null,
        board_id decimal(38,0),
        date_created datetime(6),
        description varchar(255),
        is_private bit,
        src varbinary(255),
        title varchar(255),
        user_id decimal(38,0),
        primary key (pin_id)
    );

create table user (
        user_id decimal(38,0) not null,
        primary key (user_id)
    );

alter table board add constraint  foreign key (user_id) references user (user_id);

alter table pin add constraint foreign key (user_id) references user (user_id);

alter table pin add constraint foreign key (board_id) references board (board_id);


create database collaboration;
use collaboration;

create table collaboration (
        collaboration_id decimal(38,0) not null,
        board_id decimal(38,0),
        follower_id decimal(38,0),
        primary key (collaboration_id)
    );


 create table collaboration_request (
        request_id decimal(38,0) not null,
        board_id decimal(38,0),
        date_created datetime(6),
        follower_id decimal(38,0),
        user_id decimal(38,0),
        primary key (request_id)
    ) ;

create table follow_activity (
        follow_activity_id decimal(38,0) not null,
        date_created datetime(6),
        follower decimal(38,0),
        user_id decimal(38,0),
        primary key (follow_activity_id)
    );
