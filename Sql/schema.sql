create table category
(
    id   serial8,
    name varchar not null,
    primary key (id)
);

create table product
(
    id          serial8,
    name        varchar not null,
    category_id int8,
    price       int8,
    primary key (id),
    foreign key (category_id) references category (id)
);

create table specifications
(
    id   serial8,
    name varchar not null,
    category_id int8,
    primary key (id),
    foreign key (category_id) references category(id)
);

create table spec_values
(
    id               serial8,
    value            varchar not null,
    product_id       int8,
    specifications_id int8,
    primary key (id),
    foreign key (product_id) references product (id),
    foreign key (specifications_id) references specifications (id)
);

