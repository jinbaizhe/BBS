/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/5/30 10:21:52                           */
/*==============================================================*/


drop database bbs;

create database bbs;

use bbs;

drop table if exists collection;

drop table if exists followpost;

drop table if exists game;

drop table if exists game_link;

drop table if exists log;

drop table if exists main_forum;

drop table if exists message;

drop table if exists picture;

drop table if exists post;

drop table if exists sub_forum;

drop table if exists user;

drop table if exists role;

drop table if exists user_role;

drop table if exists operation;

drop table if exists role_operation;


/*==============================================================*/
/* Table: collection                                            */
/*==============================================================*/
create table collection
(
   user_id              int not null  comment '',
   post_id              int not null  comment '',
   star_time                 datetime  comment '',
   primary key (user_id, post_id)
);

/*==============================================================*/
/* Table: followpost                                            */
/*==============================================================*/
create table followpost
(
   id                   int not null auto_increment  comment '',
   post_id              int  comment '',
   user_id              int  comment '',
   content              text  comment '',
   send_time            datetime  comment '',
   update_time          datetime  comment '',
   primary key (id)
);

/*==============================================================*/
/* Table: game                                                  */
/*==============================================================*/
create table game
(
   id                   int not null auto_increment  comment '',
   home                 char(20)  comment '',
   away                 char(20)  comment '',
   date                 date  comment '',
   url                  varchar(100)  comment '',
   note                 varchar(50)  comment '',
   primary key (id)
);

/*==============================================================*/
/* Table: game_link                                             */
/*==============================================================*/
create table game_link
(
   id                   int not null auto_increment  comment '',
   game_id              int  comment '',
   info                 text  comment '',
   order_num            int  comment '',
   note                 varchar(50)  comment '',
   primary key (id)
);

/*==============================================================*/
/* Table: log                                                   */
/*==============================================================*/
create table log
(
   id                   int not null auto_increment  comment '',
   user_id              int  comment '',
   type                 int  comment '',
   operation_type       varchar(20)  comment '',
   operation_log        varchar(100)  comment '',
   time                 datetime  comment '',
   primary key (id)
);

/*==============================================================*/
/* Table: main_forum                                            */
/*==============================================================*/
create table main_forum
(
   id                   int not null auto_increment  comment '',
   name                 varchar(30)  comment '',
   info                 varchar(100)  comment '',
   create_time          datetime  comment '',
   primary key (id)
);

/*==============================================================*/
/* Table: message                                               */
/*==============================================================*/
create table message
(
   id                   int not null auto_increment  comment '',
   user_id              int  comment '',
   parent_id            int  comment '',
   content              text  comment '',
   time                 datetime  comment '',
   primary key (id)
);

/*==============================================================*/
/* Table: picture                                               */
/*==============================================================*/
create table picture
(
   id                   int not null auto_increment  comment '',
   name              	char(30)  comment '',
   user_id				int,
   upload_time          datetime  comment '',
   primary key (id)
);

/*==============================================================*/
/* Table: post                                                  */
/*==============================================================*/
create table post
(
   id                   int not null auto_increment  comment '',
   user_id              int  comment '',
   sub_forum_id         int  comment '',
   title                varchar(50)  comment '',
   content              text  comment '',
   send_time            datetime  comment '',
   update_time          datetime  comment '',
   type                 int  comment '',
   top                  int  comment '',
   view_num             int  comment '',
   primary key (id)
);

/*==============================================================*/
/* Table: sub_forum                                             */
/*==============================================================*/
create table sub_forum
(
   id                   int not null auto_increment  comment '',
   main_forum_id        int  comment '',
   name                 varchar(30)  comment '',
   info                 varchar(100)  comment '',
   create_time          datetime  comment '',
   primary key (id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   int not null auto_increment  comment '',
   avatar_id            int  comment '',
   username             varchar(20)  comment '',
   password             varchar(20)  comment '',
   info                 varchar(50)  comment '',
   sex                  char(4)  comment '',
   email                varchar(30)  comment '',
   status               int  comment '',
   level                int  comment '',
   register_time        datetime  comment '',
   active_key           char(20)  comment '',
   primary key (id),
   unique key AK_Key_2 (username)
);

create table role
(
  id 					int primary key auto_increment,
  name varchar(20)
);
create table user_role
(
  user_id 				int references user(id),
  role_id 				int references role(id),
  primary key (user_id, role_id)
);

create table operation
(
  id 					int primary key auto_increment,
  name 					varchar(20)
);

create table role_operation
(
  role_id 				int references role(id),
  operation_id 			int references operation(id),
  primary key (role_id, operation_id)
);
grant all privileges on bbs.* to 'test'@'%' identified by 'parker123456';

alter table collection add constraint FK_COLLECTI_RELATIONS_USER foreign key (user_id)
      references user (id) on delete cascade on update cascade;

alter table collection add constraint FK_COLLECTI_RELATIONS_POST foreign key (post_id)
      references post (id) on delete cascade on update cascade;

alter table followpost add constraint FK_FOLLOWPO_RELATIONS_POST foreign key (post_id)
      references post (id) on delete cascade on update cascade;

alter table followpost add constraint FK_FOLLOWPO_RELATIONS_USER foreign key (user_id)
      references user (id) on delete cascade on update cascade;

alter table game_link add constraint FK_GAME_LIN_RELATIONS_GAME foreign key (game_id)
      references game (id) on delete cascade on update cascade;

alter table log add constraint FK_LOG_RELATIONS_USER foreign key (user_id)
      references user (id) on delete cascade on update cascade;

alter table message add constraint FK_MESSAGE_RELATIONS_USER foreign key (user_id)
      references user (id) on delete cascade on update cascade;

alter table picture add constraint FK_PICTURE_RELATIONS_USER foreign key (user_id)
      references user (id) on delete cascade on update cascade;	  

alter table post add constraint FK_POST_RELATIONS_USER foreign key (user_id)
      references user (id) on delete cascade on update cascade;

alter table post add constraint FK_POST_RELATIONS_SUB_FORU foreign key (sub_forum_id)
      references sub_forum (id) on delete cascade on update cascade;

alter table sub_forum add constraint FK_SUB_FORU_RELATIONS_MAIN_FOR foreign key (main_forum_id)
      references main_forum (id) on delete cascade on update cascade;

alter table user_role add constraint FK_USER_ROLE_RELATIONS_USER foreign key (user_id)
      references user (id) on delete cascade on update cascade;
	  
alter table user_role add constraint FK_USER_ROLE_RELATIONS_ROLE foreign key (role_id)
      references role (id) on update cascade;
	  
alter table role_operation add constraint FK_ROLE_OPERATION_RELATIONS_ROLE foreign key (role_id)
      references role (id) on delete cascade on update cascade;
	  
alter table role_operation add constraint FK_ROLE_OPERATION_RELATIONS_PERATION foreign key (operation_id)
      references operation (id) on update cascade;