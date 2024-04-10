insert into user (user_name, password, email)
values
('user1', 'password1', 'user1@abc.com'),
('user2', 'password2', 'user2@abc.com'),
('user3', 'password3', 'user3@abc.com');

insert into address (user_id, city, street)
values
(1, 'city1', 'street1'),
(1, 'city2', 'street2'),
(1, 'city3', 'street3'),
(2, 'city4', 'street4'),
(2, 'city5', 'street5'),
(3, 'city6', 'street6');
