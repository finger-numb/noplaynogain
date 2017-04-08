# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table btuser (
  id                            bigint not null,
  username                      varchar(255),
  password                      varchar(255),
  first_name                    varchar(255),
  last_name                     varchar(255),
  email                         varchar(255),
  constraint uq_btuser_username unique (username),
  constraint uq_btuser_email unique (email),
  constraint pk_btuser primary key (id)
);
create sequence btuser_seq;


# --- !Downs

drop table if exists btuser cascade;
drop sequence if exists btuser_seq;

