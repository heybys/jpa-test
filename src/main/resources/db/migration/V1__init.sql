create table test
(
    test_id      bigint auto_increment
        primary key,
    created_at   varchar(255) null,
    created_by   varchar(255) null,
    modified_at  varchar(255) null,
    modified_by  varchar(255) null,
    address      varchar(255) null,
    email        varchar(255) null,
    password     varchar(255) not null,
    phone_number varchar(255) null,
    role         varchar(255) not null,
    username     varchar(255) not null,
    constraint UK_username
        unique (username)
);

