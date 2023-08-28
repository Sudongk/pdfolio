drop table if exists member cascade;
drop table if exists gather cascade;
drop table if exists gather_comment cascade;
drop table if exists gather_heart cascade;
drop table if exists gather_reply cascade;
drop table if exists gather_skill cascade;
drop table if exists login_token cascade;
drop table if exists member_skill cascade;
drop table if exists project cascade;
drop table if exists project_comment cascade;
drop table if exists project_heart cascade;
drop table if exists project_reply cascade;
drop table if exists project_skill cascade;

create table member
(
    is_deleted    bit          null,
    created_at    datetime(6)  null,
    deleted_at    datetime(6)  null,
    id            bigint auto_increment
        primary key,
    modified_at   datetime(6)  null,
    image_url     varchar(255) null,
    name          varchar(255) null,
    nick_name     varchar(255) null,
    provider_id   varchar(255) null,
    provider_name varchar(255) null
);

create table gather
(
    close_date  date                      null,
    heart_count int                       null,
    is_deleted  bit                       null,
    start_date  date                      null,
    view_count  int                       null,
    created_at  datetime(6)               null,
    deleted_at  datetime(6)               null,
    id          bigint auto_increment
        primary key,
    member_id   bigint                    not null,
    modified_at datetime(6)               null,
    team_size   bigint                    null,
    category    enum ('PROJECT', 'STUDY') null,
    contact     varchar(255)              null,
    content     text                      null,
    title       varchar(255)              null,
    constraint FKi9dl0p3lhuv40yw6c7qbrafae
        foreign key (member_id) references member (id)
);

create table gather_comment
(
    is_deleted  bit          null,
    created_at  datetime(6)  null,
    deleted_at  datetime(6)  null,
    gather_id   bigint       null,
    id          bigint auto_increment
        primary key,
    member_id   bigint       null,
    modified_at datetime(6)  null,
    content     varchar(255) null,
    constraint FKgq08y5iervebh64vnsgfs27u0
        foreign key (gather_id) references gather (id),
    constraint FKoj7dtscpl769o7ook7d76eytc
        foreign key (member_id) references member (id)
);

create table gather_heart
(
    gather_id bigint null,
    id        bigint auto_increment
        primary key,
    member_id bigint null,
    constraint FKjc9ikpvdp426fmkw834i5aer9
        foreign key (member_id) references member (id),
    constraint FKpdftx0o7n4bms6k08ox47t503
        foreign key (gather_id) references gather (id)
);

create table gather_reply
(
    is_deleted  bit          null,
    comment_id  bigint       null,
    created_at  datetime(6)  null,
    deleted_at  datetime(6)  null,
    id          bigint auto_increment
        primary key,
    member_id   bigint       null,
    modified_at datetime(6)  null,
    content     varchar(255) null,
    constraint FKdcar3riiu15frdc6pw351s7kp
        foreign key (member_id) references member (id),
    constraint FKtluvujqa7qqfmdpmvgqqysgpg
        foreign key (comment_id) references gather_comment (id)
);

create table gather_skill
(
    gather_id bigint                                                                                                                                                                                                          null,
    id        bigint auto_increment
        primary key,
    skill     enum ('AWS', 'DJANGO', 'DOCKER', 'EXPRESS', 'GIT', 'JAVA', 'JAVASCRIPT', 'KOTLIN', 'LARAVEL', 'MYSQL', 'NEST', 'NEXT', 'NUXT', 'ORACLE', 'PHP', 'POSTGRESQL', 'PYTHON', 'REACT', 'SPRING', 'TYPESCRIPT', 'VUE') null,
    constraint FKhd9yq2im026n2unukuhivhabt
        foreign key (gather_id) references gather (id)
);

create table login_token
(
    id            bigint auto_increment
        primary key,
    member_id     bigint       null,
    refresh_token varchar(255) null,
    constraint UK_orgkl9no22wp1g2tkfv9t4x8y
        unique (member_id),
    constraint FK9k7spiswxs3b68unkxr4dmala
        foreign key (member_id) references member (id)
);

create table member_skill
(
    id        bigint auto_increment
        primary key,
    member_id bigint                                                                                                                                                                                                          null,
    skill     enum ('AWS', 'DJANGO', 'DOCKER', 'EXPRESS', 'GIT', 'JAVA', 'JAVASCRIPT', 'KOTLIN', 'LARAVEL', 'MYSQL', 'NEST', 'NEXT', 'NUXT', 'ORACLE', 'PHP', 'POSTGRESQL', 'PYTHON', 'REACT', 'SPRING', 'TYPESCRIPT', 'VUE') null,
    constraint FKam4vlts2ujppc0xfd8y1mn4kt
        foreign key (member_id) references member (id)
);

create table project
(
    heart_count    int         null,
    is_deleted     bit         null,
    view_count     int         null,
    created_at     datetime(6) null,
    deleted_at     datetime(6) null,
    id             bigint auto_increment
        primary key,
    member_id      bigint      not null,
    modified_at    datetime(6) null,
    description    varchar(50) not null,
    title          varchar(50) not null,
    content        tinytext    not null,
    publish_url    tinytext    null,
    repository_url tinytext    not null,
    thumbnail_url  tinytext    not null,
    constraint FKf02mrsqr7qo2g4pi5oetixtf1
        foreign key (member_id) references member (id)
);

create table project_comment
(
    is_deleted  bit          null,
    created_at  datetime(6)  null,
    deleted_at  datetime(6)  null,
    id          bigint auto_increment
        primary key,
    member_id   bigint       not null,
    modified_at datetime(6)  null,
    project_id  bigint       not null,
    content     varchar(200) not null,
    constraint FKhy2fe6ijy5r12lam8wvq4cfn4
        foreign key (project_id) references project (id),
    constraint FKkqlobm6g7e67499j54yrc4goq
        foreign key (member_id) references member (id)
);

create table project_heart
(
    id         bigint auto_increment
        primary key,
    member_id  bigint null,
    project_id bigint null,
    constraint FK1gbhmlkuuyhs9h7umwyu47pyc
        foreign key (member_id) references member (id),
    constraint FKbimi6w5v8yq09lgycw7mly2wb
        foreign key (project_id) references project (id)
);

create table project_reply
(
    is_deleted  bit          null,
    comment_id  bigint       not null,
    created_at  datetime(6)  null,
    deleted_at  datetime(6)  null,
    id          bigint auto_increment
        primary key,
    member_id   bigint       not null,
    modified_at datetime(6)  null,
    content     varchar(200) not null,
    constraint FKl6x6f9a86xkx6k8k201529c84
        foreign key (member_id) references member (id),
    constraint FKlw8hjwfoq6footgqk0malgel7
        foreign key (comment_id) references project_comment (id)
);

create table project_skill
(
    id         bigint auto_increment
        primary key,
    project_id bigint                                                                                                                                                                                                          not null,
    skill      enum ('AWS', 'DJANGO', 'DOCKER', 'EXPRESS', 'GIT', 'JAVA', 'JAVASCRIPT', 'KOTLIN', 'LARAVEL', 'MYSQL', 'NEST', 'NEXT', 'NUXT', 'ORACLE', 'PHP', 'POSTGRESQL', 'PYTHON', 'REACT', 'SPRING', 'TYPESCRIPT', 'VUE') not null,
    constraint FKqufv40gmkhn3vuo9opfumexss
        foreign key (project_id) references project (id)
);

create index idx_project_skill_skill
    on project_skill (skill);



insert into member(
    is_deleted,
    created_at,
    deleted_at,
    id,
    modified_at,
    image_url,
    name,
    nick_name,
    provider_id,
    provider_name
)
values (false, NOW(), null, 1, NOW(), 'http://www.naver.com', 'name1', 'nick_name', 1, 'naver'),
       (false, NOW(), null, 2, NOW(), 'http://www.naver.com', 'name2', 'nick_name', 1, 'naver'),
       (false, NOW(), null, 3, NOW(), 'http://www.naver.com', 'name3', 'nick_name', 1, 'naver'),
       (false, NOW(), null, 4, NOW(), 'http://www.google.com', 'name4', 'nick_name4', 1, 'google'),
       (false, NOW(), null, 5, NOW(), 'http://www.google.com', 'name5', 'nick_name5', 1, 'google'),
       (false, NOW(), null, 6, NOW(), 'http://www.google.com', 'name6', 'nick_name6', 1, 'google'),
       (true, NOW(), null, 7, NOW(), 'http://www.google.com', 'name7', 'nick_name7', 1, 'google'),
       (true, NOW(), null, 8, NOW(), 'http://www.google.com', 'name8', 'nick_name8', 1, 'google'),
       (true, NOW(), null, 9, NOW(), 'http://www.google.com', 'name9', 'nick_name9', 1, 'google');


insert into member_skill(id, member_id, skill)
values (1, 1, 'JAVA'), (2, 1, 'PYTHON'), (3, 1, 'KOTLIN'),
       (4, 2, 'SPRING'), (5, 2, 'NEST'), (6, 2, 'REACT'),
       (7, 3, 'MYSQL'), (8, 3, 'ORACLE'), (9, 3, 'AWS'),
       (10, 4, 'JAVA'), (11, 4, 'SPRING'), (12, 4, 'REACT'),
       (13, 5, 'JAVA'), (14, 5, 'SPRING'), (15, 5, 'REACT'),
       (16, 6, 'JAVA'), (17, 6, 'SPRING'), (18, 6, 'REACT'),
       (19, 7, 'JAVA'), (20, 7, 'SPRING'), (21, 7, 'REACT'),
       (22, 8, 'JAVA'), (23, 8, 'SPRING'), (24, 8, 'REACT'),
       (25, 9, 'JAVA'), (26, 9, 'SPRING'), (27, 9, 'REACT');


insert into project(
    heart_count,
    is_deleted,
    view_count,
    created_at,
    deleted_at,
    id,
    member_id,
    modified_at,
    description,
    title,
    content,
    publish_url,
    repository_url,
    thumbnail_url
)
values (0, false, 0, NOW(), null, 1, 1, NOW(), 'description1', 'title1', 'content1', null, 'https://github.com', 'https://image.com'),
       (0, false, 0, NOW(), null, 2, 1, NOW(), 'description2', 'title2', 'content2', null, 'https://github.com', 'https://image.com'),
       (0, false, 0, NOW(), null, 3, 2, NOW(), 'description3', 'title3', 'content3', null, 'https://github.com', 'https://image.com'),
       (0, false, 0, NOW(), null, 4, 2, NOW(), 'description4', 'title4', 'content4', null, 'https://github.com', 'https://image.com'),
       (0, false, 0, NOW(), null, 5, 3, NOW(), 'description5', 'title5', 'content5', null, 'https://github.com', 'https://image.com'),
       (0, false, 0, NOW(), null, 6, 3, NOW(), 'description6', 'title6', 'content6', null, 'https://github.com', 'https://image.com'),
       (0, false, 0, NOW(), null, 7, 4, NOW(), 'description7', 'title7', 'content7', null, 'https://github.com', 'https://image.com'),
       (0, false, 0, NOW(), null, 8, 4, NOW(), 'description8', 'title8', 'content8', null, 'https://github.com', 'https://image.com'),
       (0, false, 0, NOW(), null, 9, 5, NOW(), 'description9', 'title9', 'content9', null, 'https://github.com', 'https://image.com'),
       (0, false, 0, NOW(), null, 10, 5, NOW(), 'description10', 'title10', 'content10', null, 'https://github.com', 'https://image.com'),
       (0, false, 0, NOW(), null, 11, 6, NOW(), 'description11', 'title11', 'content11', null, 'https://github.com', 'https://image.com'),
       (0, false, 0, NOW(), null, 12, 6, NOW(), 'description12', 'title12', 'content12', null, 'https://github.com', 'https://image.com'),
       (0, false, 0, NOW(), null, 13, 7, NOW(), 'description13', 'title13', 'content13', null, 'https://github.com', 'https://image.com'),
       (0, false, 0, NOW(), null, 14, 7, NOW(), 'description14', 'title14', 'content14', null, 'https://github.com', 'https://image.com'),
       (0, false, 0, NOW(), null, 15, 8, NOW(), 'description15', 'title15', 'content15', null, 'https://github.com', 'https://image.com'),
       (0, false, 0, NOW(), null, 16, 8, NOW(), 'description16', 'title16', 'content16', null, 'https://github.com', 'https://image.com'),
       (0, false, 0, NOW(), null, 17, 9, NOW(), 'description17', 'title17', 'content17', null, 'https://github.com', 'https://image.com'),
       (0, false, 0, NOW(), null, 18, 9, NOW(), 'description18', 'title18', 'content18', null, 'https://github.com', 'https://image.com');

insert into project_skill(
    id,
    project_id,
    skill
)
values (1, 1, 'JAVA'), (2, 1, 'SPRING'),
       (3, 2, 'JAVA'), (4, 2, 'DOCKER'),
       (5, 3, 'JAVASCRIPT'), (6, 3, 'EXPRESS'),
       (7, 4, 'TYPESCRIPT'), (8, 4, 'NEST'),
       (9, 5, 'PYTHON'), (10, 5, 'MYSQL'),
       (11, 6, 'PYTHON'), (12, 6, 'MYSQL'),
       (13, 7, 'REACT'), (14, 7, 'GIT'),
       (15, 8, 'GIT'), (16, 8, 'SPRING'),
       (17, 9, 'KOTLIN'), (18, 9, 'SPRING'),
       (19, 10, 'JAVA'), (20, 10, 'ORACLE'),
       (21, 11, 'JAVA'), (22, 11, 'AWS'),
       (23, 12, 'PYTHON'), (24, 12, 'DJANGO'),
       (25, 13, 'PHP'), (26, 13, 'DOCKER'),
       (27, 14, 'PHP'), (28, 14, 'DOCKER'),
       (29, 15, 'JAVA'), (30, 15, 'NEXT'),
       (31, 16, 'MYSQL'), (32, 16, 'VUE'),
       (33, 17, 'JAVASCRIPT'), (34, 17, 'NEXT'),
       (35, 18, 'POSTGRESQL'), (36, 18, 'SPRING');

insert into project_comment(
    is_deleted,
    created_at,
    deleted_at,
    id,
    member_id,
    modified_at,
    project_id,
    content
)
values (false, NOW(), null, 1, 1, NOW(), 1, 'content1'),
       (false, NOW(), null, 2, 2, NOW(), 1, 'content2'),
       (false, NOW(), null, 3, 3, NOW(), 2, 'content3'),
       (false, NOW(), null, 4, 4, NOW(), 2, 'content4'),
       (false, NOW(), null, 5, 5, NOW(), 3, 'content5'),
       (false, NOW(), null, 6, 6, NOW(), 3, 'content6'),
       (false, NOW(), null, 7, 7, NOW(), 4, 'content7'),
       (false, NOW(), null, 8, 8, NOW(), 4, 'content8'),
       (false, NOW(), null, 9, 9, NOW(), 5, 'content9'),
       (false, NOW(), null, 10, 1, NOW(), 5, 'content10'),
       (false, NOW(), null, 11, 2, NOW(), 6, 'content11'),
       (false, NOW(), null, 12, 3, NOW(), 6, 'content12'),
       (false, NOW(), null, 13, 4, NOW(), 7, 'content13'),
       (false, NOW(), null, 14, 5, NOW(), 7, 'content14'),
       (false, NOW(), null, 15, 6, NOW(), 8, 'content15'),
       (false, NOW(), null, 16, 7, NOW(), 8, 'content16'),
       (false, NOW(), null, 17, 8, NOW(), 9, 'content17'),
       (false, NOW(), null, 18, 9, NOW(), 9, 'content18'),
       (false, NOW(), null, 19, 1, NOW(), 10, 'content19'),
       (false, NOW(), null, 20, 2, NOW(), 10, 'content20'),
       (false, NOW(), null, 21, 3, NOW(), 11, 'content21'),
       (false, NOW(), null, 22, 4, NOW(), 11, 'content22'),
       (false, NOW(), null, 23, 5, NOW(), 12, 'content23'),
       (false, NOW(), null, 24, 6, NOW(), 12, 'content24'),
       (false, NOW(), null, 25, 7, NOW(), 13, 'content25'),
       (false, NOW(), null, 26, 8, NOW(), 13, 'content26'),
       (false, NOW(), null, 27, 9, NOW(), 14, 'content27'),
       (false, NOW(), null, 28, 1, NOW(), 14, 'content28'),
       (false, NOW(), null, 29, 2, NOW(), 15, 'content29'),
       (false, NOW(), null, 30, 3, NOW(), 15, 'content30'),
       (false, NOW(), null, 31, 4, NOW(), 16, 'content31'),
       (false, NOW(), null, 32, 5, NOW(), 16, 'content32'),
       (false, NOW(), null, 33, 6, NOW(), 17, 'content33'),
       (false, NOW(), null, 34, 7, NOW(), 17, 'content34'),
       (false, NOW(), null, 35, 8, NOW(), 18, 'content35'),
       (false, NOW(), null, 36, 9, NOW(), 18, 'content36');

insert into project_reply(
    is_deleted,
    comment_id,
    created_at,
    deleted_at,
    id,
    member_id,
    modified_at,
    content
)
values (false, 1, NOW(), null, 1, 1, null, 'content1'),
       (false, 2, NOW(), null, 2, 2, null, 'content2'),
       (false, 3, NOW(), null, 3, 3, null, 'content3'),
       (false, 4, NOW(), null, 4, 4, null, 'content4'),
       (false, 5, NOW(), null, 5, 5, null, 'content5'),
       (false, 6, NOW(), null, 6, 6, null, 'content6'),
       (false, 7, NOW(), null, 7, 7, null, 'content7'),
       (false, 8, NOW(), null, 8, 8, null, 'content8'),
       (false, 9, NOW(), null, 9, 9, null, 'content9'),
       (false, 10, NOW(), null, 10, 1, null, 'content10'),
       (false, 11, NOW(), null, 11, 2, null, 'content11'),
       (false, 12, NOW(), null, 12, 3, null, 'content12'),
       (false, 13, NOW(), null, 13, 4, null, 'content13'),
       (false, 14, NOW(), null, 14, 5, null, 'content14'),
       (false, 15, NOW(), null, 15, 6, null, 'content15'),
       (false, 16, NOW(), null, 16, 7, null, 'content16'),
       (false, 17, NOW(), null, 17, 8, null, 'content17'),
       (false, 18, NOW(), null, 18, 9, null, 'content18'),
       (false, 19, NOW(), null, 19, 1, null, 'content19'),
       (false, 20, NOW(), null, 20, 2, null, 'content20'),
       (false, 21, NOW(), null, 21, 3, null, 'content21'),
       (false, 22, NOW(), null, 22, 4, null, 'content22'),
       (false, 23, NOW(), null, 23, 5, null, 'content23'),
       (false, 24, NOW(), null, 24, 6, null, 'content24'),
       (false, 25, NOW(), null, 25, 7, null, 'content25'),
       (false, 26, NOW(), null, 26, 8, null, 'content26'),
       (false, 27, NOW(), null, 27, 9, null, 'content27'),
       (false, 28, NOW(), null, 28, 1, null, 'content28'),
       (false, 29, NOW(), null, 29, 2, null, 'content29'),
       (false, 30, NOW(), null, 30, 3, null, 'content30'),
       (false, 31, NOW(), null, 31, 4, null, 'content31'),
       (false, 32, NOW(), null, 32, 5, null, 'content32'),
       (false, 33, NOW(), null, 33, 6, null, 'content33'),
       (false, 34, NOW(), null, 34, 7, null, 'content34'),
       (false, 35, NOW(), null, 35, 8, null, 'content35'),
       (false, 36, NOW(), null, 36, 9, null, 'content36');