insert into category (name)
values ('car'),
       ('phone');

insert into product (name, category_id, price)
values ('pontiac', 1, 87000000),
       ('nokia', 2, 25000);

insert into specifications (name, category_id)
values ('тип кузова', 1),
       ('объём', 1),
       ('привод',1),
       ('память', 2),
       ('диагональ', 2),
       ('камера',2);

insert into spec_values (value, product_id, specifications_id)
values ('sedan', 1, 1),
       ('3,5', 1, 2),
       ('awd', 1,3),
       ('128gb', 2, 4),
       ('4.1', 2,5),
       ('12Mp', 2, 6);
