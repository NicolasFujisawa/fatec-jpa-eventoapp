create table users (
    user_id integer(11) auto_increment primary key,
    username varchar(255) not null,
    created_at datetime DEFAULT CURRENT_TIMESTAMP
);

create table events (
    event_id integer(11) auto_increment primary key,
    name varchar(255) not null,
    event_date datetime not null,
    created_at datetime DEFAULT CURRENT_TIMESTAMP
);

create table notices (
    notice_id integer(11) auto_increment primary key,
    title varchar(255) not null,
    description text not null,
    event_id integer(11) not null,
    created_at datetime DEFAULT CURRENT_TIMESTAMP
);

alter table notices add constraint fk_notice_event foreign key (event_id) references events (event_id);

create table event_participants (
    event_participant integer(11) auto_increment primary key,
    event_id integer(11) not null,
    user_id integer(11) not null,
    created_at datetime DEFAULT CURRENT_TIMESTAMP
);

alter table event_participants add constraint fk_event_participant_event foreign key (event_id) references events (event_id);
alter table event_participants add constraint fk_event_participant_user foreign key (user_id) references users (user_id);