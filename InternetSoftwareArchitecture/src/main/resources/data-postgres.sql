INSERT INTO public.authority (name) VALUES ('ROLE_CLIENT');
INSERT INTO public.authority (name) VALUES ('ROLE_COTTAGE_OWNER');
INSERT INTO public.authority (name) VALUES ('ROLE_SHIP_OWNER');
INSERT INTO public.authority (name) VALUES ('ROLE_FISHING_INSTRUCTOR');
INSERT INTO public.authority (name) VALUES ('ROLE_SYSTEM_ADMINISTRATOR');

INSERT INTO public.address (city, country, street, street_number) VALUES ('RandomCity', 'RandomCountry', 'RandomStreet', '5');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Novi Sad', 'Srbija', 'Jevrejska', '6');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Novi Sad', 'Srbija', 'Bulevar Oslobodjenja', '10');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Novi Sad', 'Srbija', 'Bulevar Oslobodjenja', '9');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Temerin', 'Srbija', 'Novosadska', '31');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Beograd', 'Srbija', 'Svetog Save', '10');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Novi Sad', 'Srbija', 'Bulevar Oslobodjenja', '99');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Beograd', 'Srbija', 'Krusedolska', '31');
INSERT INTO public.address (city, country, street, street_number) VALUES ('Beograd', 'Srbija', 'Kralja Milana', '101');

-- Admin 		Password: Admin123!
INSERT INTO public.user (dtype, user_id, email, enabled, first_name, last_name, password, phone_number, salt, user_type, first_login_change_password, address_id) VALUES ('SystemAdministrator', default, 'admin@email.com', true, 'Admin', 'Admin', '$2a$12$9bz1GJdLCG.Ttl/p5al5O.KZOkfhgWv.MhbivCtKvC0JXbp6vyi6W', '0615859999', 'AdbMbt4t1KLF-G0SX01WPL==', 'SYSTEM_ADMINISTRATOR', true, 1);
-- Client		Password: Client123! 
INSERT INTO public.user (dtype, user_id, email, enabled, first_name, last_name, password, phone_number, salt, user_type, address_id) VALUES ('Client', default, 'milan98@email.com', true, 'Milan', 'Zivkovic', '$2a$12$gMBetyilqUFKX9iv4UuNhOATalqMm9y/LcV44I6c/AooN.VL/geK6', '0645557771', 'G1pxvzLwtZujO+hVgIp2og==', 'CLIENT', 2);
-- CottageOwner1	Password: Owner123! 
INSERT INTO public.user (dtype, user_id, email, enabled, first_name, last_name, password, phone_number, salt, user_type, address_id) VALUES ('CottageOwner', default, 'milos98@email.com', true, 'Milos', 'Milosevic', '$2a$12$1hs/7XPSREmfh70Omj.Zee5W59JnOsCN41xidlyDv1fVHmizIy.qa', '0645554048', 'h96BMRi8MSnZYO/+7RrbYQ==', 'COTTAGE_OWNER', 3);
-- CottageOwner2	Password: Dada123# 
INSERT INTO public.user (dtype, user_id, email, enabled, first_name, last_name, password, phone_number, salt, user_type, address_id) VALUES ('CottageOwner', default, 'dada97@gmail.com', true, 'Dada', 'Mesaljic', '$2a$12$VXUN7e1SiFCOVJG7Jxxm1e0h9vesimb4zpumF9ovo.ZoNafiZCwoG', '0633362476', 'd9aBVDi5sNgZYI/-7RrbYa==', 'COTTAGE_OWNER', 4);
-- CottageOwner3	Password: Luka123! 
INSERT INTO public.user (dtype, user_id, email, enabled, first_name, last_name, password, phone_number, salt, user_type, address_id) VALUES ('CottageOwner', default, 'luka98@gmail.com', true, 'Luka', 'Lukic', '$2a$12$qUR6ohymUgD5pHKRwDKZMO5/9QpRWGuiRMkdw1QqTulY6MErZhAzS', '0612002476', 'l1oBGDi4sHgZxXz+7aVbBS==', 'COTTAGE_OWNER', 5);
-- add 2 x ship owner
-- add 2 x instructor

INSERT INTO public.user_authorities(user_user_id, authorities_authority_id) VALUES (1, 5);
INSERT INTO public.user_authorities(user_user_id, authorities_authority_id) VALUES (2, 1);
INSERT INTO public.user_authorities(user_user_id, authorities_authority_id) VALUES (3, 2);
INSERT INTO public.user_authorities(user_user_id, authorities_authority_id) VALUES (4, 2);
INSERT INTO public.user_authorities(user_user_id, authorities_authority_id) VALUES (5, 2);

INSERT INTO public.price_tag(price, service_name) VALUES ('1600', 'sauna');
INSERT INTO public.price_tag(price, service_name) VALUES ('3000', 'fitness center');
INSERT INTO public.price_tag(price, service_name) VALUES ('2000', 'swimming pool');
INSERT INTO public.price_tag(price, service_name) VALUES ('2500', 'spa');

INSERT INTO public.price_list(price_list_id) VALUES (default);
INSERT INTO public.price_list_price_tags(price_list_price_list_id, price_tags_price_tag_id) VALUES (1, 1);
INSERT INTO public.price_list_price_tags(price_list_price_list_id, price_tags_price_tag_id) VALUES (1, 3);
INSERT INTO public.price_list(price_list_id) VALUES (default);
INSERT INTO public.price_list_price_tags(price_list_price_list_id, price_tags_price_tag_id) VALUES (2, 2);
INSERT INTO public.price_list_price_tags(price_list_price_list_id, price_tags_price_tag_id) VALUES (2, 3);
INSERT INTO public.price_list(price_list_id) VALUES (default);
INSERT INTO public.price_list_price_tags(price_list_price_list_id, price_tags_price_tag_id) VALUES (3, 4);


INSERT INTO public.address (city, country, street, street_number) VALUES ('Bajina Basta', 'Srbija', 'Bjelusa', '7');

INSERT INTO public.cottage(availability_end, availability_start, description, name, number_of_beds_per_room, number_of_rooms, rating, address_id, cottage_owner_id, price_list_id)
	VALUES ('2021-11-07 00:00:00', '2022-12-01 00:00:00', 'Very nice cottage, overlooking the river. Vacation is guaranteed.', 'Cottage Zaovine', '4', '1', '4.6', '10', '3', '1');
	
INSERT INTO public.address (city, country, street, street_number, longitude, latitude) VALUES ('Bajina Basta', 'Srbija', 'Sekuliceva', '8', '19.432485', '43.890245');

INSERT INTO public.cottage(availability_end, availability_start, description, name, number_of_beds_per_room, number_of_rooms, rating, address_id, cottage_owner_id, price_list_id)
	VALUES ('2021-11-07 00:00:00', '2022-12-01 00:00:00', 'Excellent cottag...', 'Mountain hut Exclusive', '2', '2', '4.9', '11', '4', '2');
	
INSERT INTO public.address (city, country, street, street_number, longitude, latitude) VALUES ('Vrdnik', 'Srbija', 'Mikice Lesjaka', '20', '19.789300', '45.134670');

INSERT INTO public.cottage(availability_end, availability_start, description, name, number_of_beds_per_room, number_of_rooms, rating, address_id, cottage_owner_id, price_list_id)
	VALUES ('2021-11-07 00:00:00', '2022-12-01 00:00:00', 'This villa has a private pool, a garden, barbecue facilities, free WiFi and free private parking.', 'Mi Casa', '3', '2', '5.0', '12', '5', '3');

