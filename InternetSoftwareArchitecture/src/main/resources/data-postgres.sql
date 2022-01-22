INSERT INTO public.authority (name) VALUES ('ROLE_CLIENT');
INSERT INTO public.authority (name) VALUES ('ROLE_COTTAGE_OWNER');
INSERT INTO public.authority (name) VALUES ('ROLE_SHIP_OWNER');
INSERT INTO public.authority (name) VALUES ('ROLE_FISHING_INSTRUCTOR');
INSERT INTO public.authority (name) VALUES ('ROLE_SYSTEM_ADMINISTRATOR');


INSERT INTO public.address(city, country, street, street_number) VALUES ('RandomCity', 'Serbia', 'RandomStreet', '1');
INSERT INTO public.address(city, country, street, street_number) VALUES ('Novi Sad', 'Serbia', 'Jevrejska', '6');
INSERT INTO public.address(city, country, street, street_number) VALUES ('Novi Sad', 'Serbia', 'Bulevar Oslobodjenja', '10');
INSERT INTO public.address(city, country, street, street_number) VALUES ('Novi Sad', 'Serbia', 'Bulevar Oslobodjenja', '9');
INSERT INTO public.address(city, country, street, street_number) VALUES ('Temerin', 'Serbia', 'Novosadska', '31');
INSERT INTO public.address(city, country, street, street_number) VALUES ('Beograd', 'Serbia', 'Svetog Save', '10');
INSERT INTO public.address(city, country, street, street_number) VALUES ('Novi Sad', 'Serbia', 'Bulevar Oslobodjenja', '99');
INSERT INTO public.address(city, country, street, street_number) VALUES ('Beograd', 'Serbia', 'Krusedolska', '31');
INSERT INTO public.address(city, country, street, street_number) VALUES ('Zajecar', 'Serbia', 'Kotorska', '11');
INSERT INTO public.address(city, country, street, street_number) VALUES ('Beograd', 'Serbia', 'Karadjordjeva', '9');

-- Admin 		Password: Admin123!
INSERT INTO public.user (dtype, user_id, email, enabled, first_name, last_name, password, phone_number, salt, user_type, first_login_change_password, address_id) VALUES ('SystemAdministrator', default, 'admin@email.com', true, 'Admin', 'Admin', '$2a$12$9bz1GJdLCG.Ttl/p5al5O.KZOkfhgWv.MhbivCtKvC0JXbp6vyi6W', '0615859999', 'AdbMbt4t1KLF-G0SX01WPL==', 'SYSTEM_ADMINISTRATOR', true, '1');
-- Client1		Password: Client123!     email for test: your email address 1    
INSERT INTO public.user (dtype, user_id, email, enabled, first_name, last_name, password, phone_number, salt, user_type, address_id) VALUES ('Client', default, 'milan98@email.com', true, 'Milan', 'Zivkovic', '$2a$12$gMBetyilqUFKX9iv4UuNhOATalqMm9y/LcV44I6c/AooN.VL/geK6', '0645557771', 'G1pxvzLwtZujO+hVgIp2og==', 'CLIENT', '2');
-- CottageOwner1	Password: Owner123!  email for test: your email address 2 
INSERT INTO public.user (dtype, user_id, email, enabled, first_name, last_name, password, phone_number, salt, user_type, rating, address_id) VALUES ('CottageOwner', default, 'milos98@email.com', true, 'Milos', 'Milosevic', '$2a$12$1hs/7XPSREmfh70Omj.Zee5W59JnOsCN41xidlyDv1fVHmizIy.qa', '0645554048', 'h96BMRi8MSnZYO/+7RrbYQ==', 'COTTAGE_OWNER', '4.1', '3');
-- CottageOwner2	Password: Dada123# 
INSERT INTO public.user (dtype, user_id, email, enabled, first_name, last_name, password, phone_number, salt, user_type, rating, address_id) VALUES ('CottageOwner', default, 'dada97@email.com', true, 'Dada', 'Mesaljic', '$2a$12$VXUN7e1SiFCOVJG7Jxxm1e0h9vesimb4zpumF9ovo.ZoNafiZCwoG', '0633362476', 'd9aBVDi5sNgZYI/-7RrbYa==', 'COTTAGE_OWNER', '5.0', '4');
-- CottageOwner3	Password: Luka123! 
INSERT INTO public.user (dtype, user_id, email, enabled, first_name, last_name, password, phone_number, salt, user_type, rating, address_id) VALUES ('CottageOwner', default, 'luka98@email.com', true, 'Luka', 'Lukic', '$2a$12$qUR6ohymUgD5pHKRwDKZMO5/9QpRWGuiRMkdw1QqTulY6MErZhAzS', '0612002476', 'l1oBGDi4sHgZxXz+7aVbBS==', 'COTTAGE_OWNER', '4.9', '5');
-- ShipOwner1		Password: Owner123$
INSERT INTO public.user (dtype, user_id, email, enabled, first_name, last_name, password, phone_number, salt, user_type, rating, address_id) VALUES ('ShipOwner', default, 'nikola9@email.com', true, 'Nikola', 'Savic', '$2a$12$6FzwVE5D3b/CXpkHaJcqtOUG.x4aGVZwuvVhvDWrnJ0QysPcPffd6', '0692002010', 'PlbLG1i4S5gTNik+7WvMBw==', 'SHIP_OWNER', '4.6', '6');
-- ShipOwner2		Password: Tara123! 
INSERT INTO public.user (dtype, user_id, email, enabled, first_name, last_name, password, phone_number, salt, user_type, rating, address_id) VALUES ('ShipOwner', default, 'tara10@email.com', true, 'Tara', 'Gligoric', '$2a$12$KzV2OVuhv4iUhQLhs/CeOOXKBHXGAlmR3W/XAsgaAyFaLHUipbZEW', '0633003022', 'ByCrDgSvdhj1vT-5J9aVbS==', 'SHIP_OWNER', '4.7', '7');
-- Instructor1		Password: Jan3456!
INSERT INTO public.user (dtype, user_id, email, enabled, first_name, last_name, password, phone_number, salt, user_type, rating, address_id) VALUES ('FishingInstructor', default, 'jan@email.com', true, 'Jan', 'Jankovic', '$2a$12$nb21qrNM7.0IvUrBtqMIDOcE5qSVk.CikFQVIXlpDVY7OJg0zdhuS', '066106108', 'FibSH1iGhhvJ2n7JkVyMfL==', 'FISHING_INSTRUCTOR', '5.0', '8');
-- Instructor2		Password: Sale456!
INSERT INTO public.user (dtype, user_id, email, enabled, first_name, last_name, password, phone_number, salt, user_type, rating, address_id) VALUES ('FishingInstructor', default, 'sale@email.com', true, 'Sale', 'Zagorac', '$2a$12$eASTw9wd4MW8i9DOs6dKR.9cdp5kmwIDU80IsVLYqQz.lAktWSUy2', '0634001030', 'Al3sj7R4S5tT6iNc7W3mOK==', 'FISHING_INSTRUCTOR', '4.4', '9');
-- Client2		Password: Milika123! 
INSERT INTO public.user (dtype, user_id, email, enabled, first_name, last_name, password, phone_number, salt, user_type, address_id) VALUES ('Client', default, 'milika@email.com', true, 'Milica', 'Djordjevic', '$2a$12$pTODZJjxRDcvM1I12TOfpOe6XiSTHgYJ5AtPi8oAw6fj9ilPBfTgC', '0649993031', 'K+IsDJLi43-jlR+Fiap1mG==', 'CLIENT', '10');

INSERT INTO public.user_authorities(user_user_id, authorities_authority_id) VALUES (1, 5);
INSERT INTO public.user_authorities(user_user_id, authorities_authority_id) VALUES (2, 1);
INSERT INTO public.user_authorities(user_user_id, authorities_authority_id) VALUES (3, 2);
INSERT INTO public.user_authorities(user_user_id, authorities_authority_id) VALUES (4, 2);
INSERT INTO public.user_authorities(user_user_id, authorities_authority_id) VALUES (5, 2);
INSERT INTO public.user_authorities(user_user_id, authorities_authority_id) VALUES (6, 3);
INSERT INTO public.user_authorities(user_user_id, authorities_authority_id) VALUES (7, 3);
INSERT INTO public.user_authorities(user_user_id, authorities_authority_id) VALUES (8, 4);
INSERT INTO public.user_authorities(user_user_id, authorities_authority_id) VALUES (9, 4);
INSERT INTO public.user_authorities(user_user_id, authorities_authority_id) VALUES (10, 1);


INSERT INTO public.price_tag(price, service_name) VALUES ('2200', 'rafting');
INSERT INTO public.price_tag(price, service_name) VALUES ('3000', 'fitness center');
INSERT INTO public.price_tag(price, service_name) VALUES ('2000', 'swimming pool');
INSERT INTO public.price_tag(price, service_name) VALUES ('2500', 'spa');

INSERT INTO public.price_list(price_list_id) VALUES (default);
INSERT INTO public.price_list_price_tags(price_list_price_list_id, price_tags_price_tag_id) VALUES (1, 1);
INSERT INTO public.price_list(price_list_id) VALUES (default);
INSERT INTO public.price_list_price_tags(price_list_price_list_id, price_tags_price_tag_id) VALUES (2, 2);
INSERT INTO public.price_list_price_tags(price_list_price_list_id, price_tags_price_tag_id) VALUES (2, 3);
INSERT INTO public.price_list(price_list_id) VALUES (default);
INSERT INTO public.price_list_price_tags(price_list_price_list_id, price_tags_price_tag_id) VALUES (3, 3);
INSERT INTO public.price_list_price_tags(price_list_price_list_id, price_tags_price_tag_id) VALUES (3, 4);


INSERT INTO public.image(name) VALUES ('1638982456862cottage1img1');
INSERT INTO public.image(name) VALUES ('1638982456888cottage1img2');
INSERT INTO public.image(name) VALUES ('1638982456902cottage1img3');
INSERT INTO public.image(name) VALUES ('1638982456912cottage1img4');
INSERT INTO public.image(name) VALUES ('1638982456921cottage1img5');
INSERT INTO public.image(name) VALUES ('1638982456933cottage1img6');


INSERT INTO public.rule(rule_id, description) VALUES (default, 'No making loud noise');

INSERT INTO public.address(city, country, street, street_number, longitude, latitude) VALUES ('Bajina Basta', 'Serbia', 'Perucac', '8', '19.478483', '43.955651');

INSERT INTO public.cottage(availability_start, availability_end, description, name, number_of_beds_per_room, number_of_rooms, rating, address_id, cottage_owner_id, price_per_day, price_list_id, status)
	VALUES ('2021-10-31 00:00:00', '2022-05-01 00:00:00', 'Very nice cottage, overlooking the river. Vacation is guaranteed.', 'Wooden cottage on Drina', '2', '2', '4.0', '11', '3', '6500', '1', 'ACTIVE');

INSERT INTO public.cottage_cottage_rules(
	cottage_cottage_id, cottage_rules_rule_id)
	VALUES ('1', '1');	
	
INSERT INTO public.cottage_images(cottage_cottage_id, images_image_id) VALUES (1, 1);
INSERT INTO public.cottage_images(cottage_cottage_id, images_image_id) VALUES (1, 2);
INSERT INTO public.cottage_images(cottage_cottage_id, images_image_id) VALUES (1, 3);
INSERT INTO public.cottage_images(cottage_cottage_id, images_image_id) VALUES (1, 4);
INSERT INTO public.cottage_images(cottage_cottage_id, images_image_id) VALUES (1, 5);
INSERT INTO public.cottage_images(cottage_cottage_id, images_image_id) VALUES (1, 6);

INSERT INTO public.image(name) VALUES ('1638983374310cottage2img1');
INSERT INTO public.image(name) VALUES ('1638983374329cottage2img2');
INSERT INTO public.image(name) VALUES ('1638983374337cottage2img3');
INSERT INTO public.image(name) VALUES ('1638983374343cottage2img4');
INSERT INTO public.image(name) VALUES ('1638983374352cottage2img5');
INSERT INTO public.image(name) VALUES ('1638983374358cottage2img6');
	
INSERT INTO public.address(city, country, street, street_number, longitude, latitude) VALUES ('Vrdnik', 'Serbia', 'Mikice Lesjaka', '20', '19.789300', '45.134670');

INSERT INTO public.cottage(availability_start, availability_end, description, name, number_of_beds_per_room, number_of_rooms, rating, address_id, cottage_owner_id, price_per_day, price_list_id, status)
	VALUES ('2021-10-20 00:00:00', '2022-12-01 00:00:00', 'This villa has a private pool, a garden, barbecue facilities, free WiFi and free private parking.', 'Mi Casa', '3', '2', '5.0', '12', '5', '6750', '2', 'ACTIVE');

INSERT INTO public.cottage_cottage_rules(
	cottage_cottage_id, cottage_rules_rule_id)
	VALUES ('2', '1');	

INSERT INTO public.cottage_images(cottage_cottage_id, images_image_id) VALUES (2, 7);
INSERT INTO public.cottage_images(cottage_cottage_id, images_image_id) VALUES (2, 8);
INSERT INTO public.cottage_images(cottage_cottage_id, images_image_id) VALUES (2, 9);
INSERT INTO public.cottage_images(cottage_cottage_id, images_image_id) VALUES (2, 10);
INSERT INTO public.cottage_images(cottage_cottage_id, images_image_id) VALUES (2, 11);
INSERT INTO public.cottage_images(cottage_cottage_id, images_image_id) VALUES (2, 12);

INSERT INTO public.image(name) VALUES ('1638984512089cottage3img1');
INSERT INTO public.image(name) VALUES ('1638984512234cottage3img2');
INSERT INTO public.image(name) VALUES ('1638984512259cottage3img3');

INSERT INTO public.address(city, country, street, street_number, longitude, latitude) VALUES ('Bajina Basta', 'Serbia', 'Sekuliceva', '8', '19.432485', '43.890245');

INSERT INTO public.cottage(availability_start, availability_end, description, name, number_of_beds_per_room, number_of_rooms, rating, address_id, cottage_owner_id, price_per_day, price_list_id, status)
	VALUES ('2021-12-01 00:00:00', '2022-12-01 00:00:00', 'Excellent cottag...', 'Mountain cottage Exclusive', '2', '2', '4.9', '13', '4', '9000', '3', 'ACTIVE');
	
INSERT INTO public.cottage_cottage_rules(
	cottage_cottage_id, cottage_rules_rule_id)
	VALUES ('3', '1');
	
INSERT INTO public.cottage_images(cottage_cottage_id, images_image_id) VALUES (3, 13);
INSERT INTO public.cottage_images(cottage_cottage_id, images_image_id) VALUES (3, 14);
INSERT INTO public.cottage_images(cottage_cottage_id, images_image_id) VALUES (3, 15);

INSERT INTO public.price_list(price_list_id) VALUES (default);

INSERT INTO public.image(name) VALUES ('ship1img1');
INSERT INTO public.image(name) VALUES ('ship1img2');
INSERT INTO public.image(name) VALUES ('ship1img3');

INSERT INTO public.address (city, country, street, street_number, longitude, latitude) VALUES ('Beograd', 'Serbia', 'Zemunski Kej', '67', '20.424374184658642', '44.826341557721506');

INSERT INTO public.ship(availability_start, availability_end, capacity, description, engine_number, engine_power, length, max_speed, name, rating, ship_type, address_id, price_list_id, price_per_hour, ship_owner_id, status)
	VALUES ('2021-11-07 00:00:00', '2022-12-01 00:00:00', '6', 'Excellent cruiser, ample storage under the sun island and helm station assure that everything finds a place.', '1', '320', '8.6', '88', 'Monterey 275 SY', '4.7', 'YACHT', '14', '4', '16250', '6', 'ACTIVE');

INSERT INTO public.ship_images(ship_ship_id, images_image_id) VALUES (1, 16);
INSERT INTO public.ship_images(ship_ship_id, images_image_id) VALUES (1, 17);
INSERT INTO public.ship_images(ship_ship_id, images_image_id) VALUES (1, 18);


INSERT INTO public.price_list(price_list_id) VALUES (default);

INSERT INTO public.image(name) VALUES ('ship2img1');
INSERT INTO public.image(name) VALUES ('ship2img2');
INSERT INTO public.image(name) VALUES ('ship2img3');

INSERT INTO public.address (city, country, street, street_number, longitude, latitude) VALUES ('Novi Sad', 'Serbia', 'Bulevar despota Stefana', '7', '19.83781344455965', '45.23437796115102');

INSERT INTO public.ship(availability_start, availability_end, capacity, description, engine_number, engine_power, length, max_speed, name, rating, ship_type, address_id, price_list_id, price_per_hour, ship_owner_id, status)
	VALUES ('2021-11-07 00:00:00', '2022-12-01 00:00:00', '10', 'Speed boat, aggressive appearance and broken tubes. It is adorned with a deep "V" riverbed that is designed to overcome larger waves on the high seas and cross long distances on the high seas.', '2', '250', '8.5', '102', 'Advance G800', '5.0', 'SPEED_BOAT', '15', '5', '15000', '7', 'ACTIVE');

INSERT INTO public.ship_images(ship_ship_id, images_image_id) VALUES (2, 19);
INSERT INTO public.ship_images(ship_ship_id, images_image_id) VALUES (2, 20);
INSERT INTO public.ship_images(ship_ship_id, images_image_id) VALUES (2, 21);


INSERT INTO public.price_list(price_list_id) VALUES (default);

INSERT INTO public.image(name) VALUES ('instructor1img1');
INSERT INTO public.image(name) VALUES ('instructor1img2');
INSERT INTO public.image(name) VALUES ('instructor1img3');

INSERT INTO public.address (city, country, street, street_number, longitude, latitude) VALUES ('Beograd', 'Serbia', 'Obrenovacki drum', '78', '20.390636875888177', '44.782640133457626');

INSERT INTO public.adventure(availability_start, availability_end, description, instructor_biography, max_number_of_persons, name, rating, address_id, fishing_instructor_id, price_list_id, price_per_hour, status)
	VALUES ('2021-11-07 00:00:00', '2022-12-01 00:00:00', ' The first steps of fly fishing', 'Biography fishing instructor...', '4', 'Fly fishing adventure', '5.0', '16', '8', '6', '1400', 'ACTIVE');

INSERT INTO public.adventure_images(adventure_adventure_id, images_image_id) VALUES (1, 22);
INSERT INTO public.adventure_images(adventure_adventure_id, images_image_id) VALUES (1, 23);
INSERT INTO public.adventure_images(adventure_adventure_id, images_image_id) VALUES (1, 24);


INSERT INTO public.price_list(price_list_id) VALUES (default);

INSERT INTO public.image(name) VALUES ('instructor2img1');
INSERT INTO public.image(name) VALUES ('instructor2img2');
INSERT INTO public.image(name) VALUES ('instructor2img3');

--- Google maps 44.09465613202866, 22.010111173842667

INSERT INTO public.address (city, country, street, street_number, longitude, latitude) VALUES ('Brestovac', 'Serbia', 'Kamp ulica', '105', '22.010111173842667', '44.09465613202866');

INSERT INTO public.adventure(availability_start, availability_end, description, instructor_biography, max_number_of_persons, name, rating, address_id, fishing_instructor_id, price_list_id, price_per_hour, status)
	VALUES ('2021-11-07 00:00:00', '2022-12-01 00:00:00', ' Fishing adventure', 'Short Biography', '6', 'Lake adventure', '4.6', '17', '9', '7', '1000', 'ACTIVE');

INSERT INTO public.adventure_images(adventure_adventure_id, images_image_id) VALUES (2, 25);
INSERT INTO public.adventure_images(adventure_adventure_id, images_image_id) VALUES (2, 26);
INSERT INTO public.adventure_images(adventure_adventure_id, images_image_id) VALUES (2, 27);

-- COTTAGE ADDITIONAL SERVICES

INSERT INTO public.cottage_additional_service(cottage_additional_service_id, cottage_additional_service_type) VALUES (default, 'WIFI');
INSERT INTO public.cottage_additional_service(cottage_additional_service_id, cottage_additional_service_type) VALUES (default, 'PARKING');
INSERT INTO public.cottage_additional_service(cottage_additional_service_id, cottage_additional_service_type) VALUES (default, 'PET_FRIENDLY');

INSERT INTO public.reservation_cottage(reservation_cottage_id, date_and_time, duration, max_number_of_persons, price, reservation_status, client_id, cottage_id) VALUES (default, '2021-11-07 00:00:00', 5, 3, 5000, 'WAITING', 2, 1);
INSERT INTO public.reservation_cottage(reservation_cottage_id, date_and_time, duration, max_number_of_persons, price, reservation_status, client_id, cottage_id) VALUES (default, '2022-10-04 00:00:00', 4, 3, 4000, 'WAITING', 2, 1);
INSERT INTO public.reservation_cottage(reservation_cottage_id, date_and_time, duration, max_number_of_persons, price, reservation_status, client_id, cottage_id) VALUES (default, '2021-05-05 00:00:00', 3, 2, 3000, 'STARTED', 2, 1);
INSERT INTO public.reservation_cottage(reservation_cottage_id, date_and_time, duration, max_number_of_persons, price, reservation_status, client_id, cottage_id) VALUES (default, '2021-04-02 00:00:00', 5, 3, 5500, 'FINISHED', 2, 1);

-- FINISHED RESERVATION CLIENT TEST
	
INSERT INTO public.reservation_ship(date_and_time, duration, max_number_of_persons, price, reservation_status, ship_reservation_cancel_term, client_id, ship_id)
	VALUES ('2021-12-01 13:00:00', '4', '2', '60000', 'FINISHED', 'FREE', '2', '2');
	
INSERT INTO public.reservation_ship(date_and_time, duration, max_number_of_persons, price, reservation_status, ship_reservation_cancel_term, client_id, ship_id)
	VALUES ('2021-12-09 10:00:00', '3', '3', '39000', 'FINISHED', 'FREE', '2', '2');

INSERT INTO public.reservation_cottage(date_and_time, duration, max_number_of_persons, price, reservation_status, client_id, cottage_id)
	VALUES ('2021-10-03 11:00:00', '5', '4', '26000', 'FINISHED', '2', '1');

INSERT INTO public.reservation_cottage(date_and_time, duration, max_number_of_persons, price, reservation_status, client_id, cottage_id)
	VALUES ('2021-11-07 11:00:00', '2', '2', '20000', 'FINISHED', '2', '2');
	
INSERT INTO public.reservation_cottage(date_and_time, duration, max_number_of_persons, price, reservation_status, client_id, cottage_id)
	VALUES ('2021-10-31 18:00:00', '3', '4', '30000', 'FINISHED', '2', '3');

INSERT INTO public.reservation_adventure(adventure_reservation_cancel_term, date_and_time, duration, max_number_of_persons, price, reservation_status, adventure_id, client_id)
	VALUES ('FREE','2021-09-15 08:00:00', '2', '2', '1800', 'FINISHED', '1', '2');
	
INSERT INTO public.reservation_adventure(adventure_reservation_cancel_term, date_and_time, duration, max_number_of_persons, price, reservation_status, adventure_id, client_id)
	VALUES ('FREE','2021-10-10 14:00:00', '3', '4', '4000', 'FINISHED', '2', '2');
	
INSERT INTO public.reservation_adventure(adventure_reservation_cancel_term, date_and_time, duration, max_number_of_persons, price, reservation_status, adventure_id, client_id)
	VALUES ('FREE','2021-11-10 15:00:00', '4', '4', '3000', 'FINISHED', '2', '2');
	
-- SCHEDULED RESERVATION CLIENT TEST

INSERT INTO public.reservation_cottage(date_and_time, duration, max_number_of_persons, price, reservation_status, client_id, cottage_id)
	VALUES ('2022-01-27 11:00:00', '5', '4', '26000', 'SCHEDULED', '2', '1');

INSERT INTO public.reservation_cottage(date_and_time, duration, max_number_of_persons, price, reservation_status, client_id, cottage_id)
	VALUES ('2022-01-22 11:00:00', '2', '2', '20000', 'SCHEDULED', '2', '2');
	
INSERT INTO public.reservation_cottage(date_and_time, duration, max_number_of_persons, price, reservation_status, client_id, cottage_id)
	VALUES ('2022-02-14 18:00:00', '3', '4', '30000', 'SCHEDULED', '2', '3');
	
INSERT INTO public.reservation_ship(date_and_time, duration, max_number_of_persons, price, reservation_status, ship_reservation_cancel_term, client_id, ship_id)
	VALUES ('2022-02-15 12:00:00', '1', '2', '16000', 'SCHEDULED', 'FREE', '2', '1');
	
INSERT INTO public.reservation_ship(date_and_time, duration, max_number_of_persons, price, reservation_status, ship_reservation_cancel_term, client_id, ship_id)
	VALUES ('2022-01-24 13:00:00', '4', '2', '60000', 'SCHEDULED', 'FREE', '2', '2');

INSERT INTO public.reservation_adventure(adventure_reservation_cancel_term, date_and_time, duration, max_number_of_persons, price, reservation_status, adventure_id, client_id)
	VALUES ('FREE','2022-04-11 14:00:00', '1', '2', '1000', 'SCHEDULED', '2', '2');

INSERT INTO public.reservation_ship(date_and_time, duration, max_number_of_persons, price, reservation_status, ship_reservation_cancel_term, client_id, ship_id)
	VALUES ('2021-11-11 12:00:00', '1', '2', '16000', 'SCHEDULED', 'FREE', '2', '1'); --- SCHEDULED worker test
	
-- CLIENT SUBSCRIPTIONS 

INSERT INTO public.user_cottage_subscriptions(client_user_id, cottage_subscriptions_cottage_id)
	VALUES (2, 1);
	
INSERT INTO public.user_cottage_subscriptions(client_user_id, cottage_subscriptions_cottage_id)
	VALUES (2, 2);

INSERT INTO public.user_cottage_subscriptions(client_user_id, cottage_subscriptions_cottage_id)
	VALUES (2, 3);
	
INSERT INTO public.user_ship_subscriptions(client_user_id, ship_subscriptions_ship_id)
	VALUES (2, 1);
	
INSERT INTO public.user_ship_subscriptions(client_user_id, ship_subscriptions_ship_id)
	VALUES (2, 2);

INSERT INTO public.user_fishing_instructor_subscriptions(client_user_id, fishing_instructor_subscriptions_user_id)
	VALUES (2, 8);

INSERT INTO public.user_fishing_instructor_subscriptions(client_user_id, fishing_instructor_subscriptions_user_id)
	VALUES (2, 9);
	
-- CLIENT DELETE PROFILE

INSERT INTO public.user_delete_account(reason, user_delete_account_status, user_id) 
	VALUES ('I am interested in other things e.g. books.', 'WAITING', '10');
	
-- CLIENT COMPLAINTS

-- Cottage
INSERT INTO public.complaint(comment, complaint_status, client_id, cottage_id)
	VALUES ('There is not enough space to park the car.', 'WAITING', '2', '1');

-- Ship
INSERT INTO public.complaint(comment, complaint_status, client_id, ship_id)
	VALUES ('Noisy engines at high revs.', 'WAITING', '2', '1');

-- Cottage Owner
INSERT INTO public.complaint(comment, complaint_status, client_id, user_id)
	VALUES ('Milos was late for a meeting to hand over the key to the cottage.', 'WAITING', '2', '3');

-- Ship Owner
INSERT INTO public.complaint(comment, complaint_status, client_id, user_id)
	VALUES ('The driver drove us for an hour and a half and the deal was 2 hours.', 'WAITING', '2', '6');

-- Fishing Instructor
INSERT INTO public.complaint(comment, complaint_status, client_id, user_id)
	VALUES ('Sale forgot the pike baits.', 'WAITING', '2', '9');
	
-- CLIENT REVISION

-- Fishing Instructor
INSERT INTO public.revision(comment, rating, revision_status, client_id, user_id)
	VALUES ('Greetings for the best fly fishing instructor!', '5.0', 'WAITING', '2', '8');

-- Fishing Instructor	
INSERT INTO public.revision(comment, rating, revision_status, client_id, user_id)
	VALUES ('The guy is trying to teach us and he also recently started fly fishing.', '4.6', 'WAITING', '2', '9');
	
-- Ship owner	
INSERT INTO public.revision(comment, rating, revision_status, client_id, user_id)
	VALUES ('Kind ship owner Tara, all recommendations.', '5.0', 'WAITING', '2', '7');
	
-- Cottage owner
INSERT INTO public.revision(comment, rating, revision_status, client_id, user_id)
	VALUES ('All recommendations for Dada.', '5.0', 'DECLINED', '2', '4');

-- Cottage
INSERT INTO public.revision(comment, rating, revision_status, client_id, cottage_id)
	VALUES ('Great cottage near Novi Sad.', '4.8', 'WAITING', '2', '2');
	
-- Cottage
INSERT INTO public.revision(comment, rating, revision_status, client_id, cottage_id)
	VALUES ('This is excellent cottage for parents with young children.', '5.0', 'WAITING', '2', '3');
	
-- Ship
INSERT INTO public.revision(comment, rating, revision_status, client_id, ship_id)
	VALUES ('This is excellent speed boat.', '5.0', 'WAITING', '2', '2');
	
-- Ship
INSERT INTO public.revision(comment, rating, revision_status, client_id, ship_id)
	VALUES ('Expensive yacht with noisy engines.', '3.5', 'WAITING', '2', '1');
	
-- Adventure
INSERT INTO public.revision(comment, rating, revision_status, client_id, adventure_id)
	VALUES ('Very nice adventure.', '4.8', 'WAITING', '2', '2');

-- Adventure
INSERT INTO public.revision(comment, rating, revision_status, client_id, adventure_id)
	VALUES ('Nice fishing adventure!', '5.0', 'WAITING', '2', '1');
	
-- COTTAGE FAST RESERVATION

-- Wooden Cottage
INSERT INTO public.reservation_cottage(date_and_time, duration, max_number_of_persons, price, discount_percentage, reservation_status, cottage_id)
	VALUES ('2022-04-08 17:00:00', '4', '4', '26000', '20', 'WAITING', '1');

-- Wooden Cottage additional services
INSERT INTO public.reservation_cottage_cottage_additional_services(
	reservation_cottage_reservation_cottage_id, cottage_additional_services_cottage_additional_service_id)
	VALUES ('11', '1');
	
INSERT INTO public.reservation_cottage_cottage_additional_services(
	reservation_cottage_reservation_cottage_id, cottage_additional_services_cottage_additional_service_id)
	VALUES ('11', '2');
	
INSERT INTO public.reservation_cottage_cottage_additional_services(
	reservation_cottage_reservation_cottage_id, cottage_additional_services_cottage_additional_service_id)
	VALUES ('11', '3');
	
-- Mountain cottage Exclusive
INSERT INTO public.reservation_cottage(date_and_time, duration, max_number_of_persons, price, discount_percentage, reservation_status, cottage_id)
	VALUES ('2022-04-26 10:30:00', '4', '4', '36000', '20', 'WAITING', '3');

-- Exclusive Cottage additional services
INSERT INTO public.reservation_cottage_cottage_additional_services(
	reservation_cottage_reservation_cottage_id, cottage_additional_services_cottage_additional_service_id)
	VALUES ('12', '1');
	
INSERT INTO public.reservation_cottage_cottage_additional_services(
	reservation_cottage_reservation_cottage_id, cottage_additional_services_cottage_additional_service_id)
	VALUES ('12', '2');
	
	
-- Mountain cottage Mi Casa
INSERT INTO public.reservation_cottage(date_and_time, duration, max_number_of_persons, price, discount_percentage, reservation_status, cottage_id)
	VALUES ('2022-03-20 10:30:00', '4', '2', '27000', '10', 'WAITING', '2');

-- Mi Casa Cottage additional services
INSERT INTO public.reservation_cottage_cottage_additional_services(
	reservation_cottage_reservation_cottage_id, cottage_additional_services_cottage_additional_service_id)
	VALUES ('13', '1');
	
INSERT INTO public.reservation_cottage_cottage_additional_services(
	reservation_cottage_reservation_cottage_id, cottage_additional_services_cottage_additional_service_id)
	VALUES ('13', '2');
	
INSERT INTO public.reservation_cottage_cottage_additional_services(
	reservation_cottage_reservation_cottage_id, cottage_additional_services_cottage_additional_service_id)
	VALUES ('13', '3');

-- SHIP ADDITIONAL SERVICES

INSERT INTO public.ship_additional_service(ship_additional_service_id, ship_additional_service_type)
	VALUES (default, 'WIFI');
INSERT INTO public.ship_additional_service(ship_additional_service_id, ship_additional_service_type)
	VALUES (default, 'MINIBAR');
INSERT INTO public.ship_additional_service(ship_additional_service_id, ship_additional_service_type)
	VALUES (default, 'PET_FRIENDLY');

-- FISHING EQUIPMENT FOR SHIP

INSERT INTO public.fishing_equipment_for_ship(
	fishing_equipment_for_ship_id, fishing_equipment_for_ship_type)
	VALUES (default, 'FISHING_ROD');
	
INSERT INTO public.fishing_equipment_for_ship(
	fishing_equipment_for_ship_id, fishing_equipment_for_ship_type)
	VALUES (default, 'FISHING_NET');

-- SHIP FAST RESERVATION

INSERT INTO public.reservation_ship(date_and_time, duration, max_number_of_persons, price, discount_percentage, reservation_status, ship_reservation_cancel_term, ship_id)
	VALUES ('2022-04-05 15:00:00', '4', '6', '60000', '10', 'WAITING', 'FREE', '2');	

INSERT INTO public.reservation_ship_ship_additional_services(
	reservation_ship_reservation_ship_id, ship_additional_services_ship_additional_service_id)
	VALUES ('6', '1');

INSERT INTO public.reservation_ship_ship_additional_services(
	reservation_ship_reservation_ship_id, ship_additional_services_ship_additional_service_id)
	VALUES ('6', '2');

INSERT INTO public.reservation_ship_fishing_equipment_for_ship(
	reservation_ship_reservation_ship_id, fishing_equipment_for_ship_fishing_equipment_for_ship_id)
	VALUES ('6', '1');
	

INSERT INTO public.reservation_ship_fishing_equipment_for_ship(
	reservation_ship_reservation_ship_id, fishing_equipment_for_ship_fishing_equipment_for_ship_id)
	VALUES ('6', '2');	

	
INSERT INTO public.reservation_ship(date_and_time, duration, max_number_of_persons, price, discount_percentage, reservation_status, ship_reservation_cancel_term, ship_id)
	VALUES ('2022-03-28 12:00:00', '4', '6', '65000', '30', 'WAITING', 'FREE', '1');	

INSERT INTO public.reservation_ship_ship_additional_services(
	reservation_ship_reservation_ship_id, ship_additional_services_ship_additional_service_id)
	VALUES ('7', '1');	
	
INSERT INTO public.reservation_ship_ship_additional_services(
	reservation_ship_reservation_ship_id, ship_additional_services_ship_additional_service_id)
	VALUES ('7', '2');

INSERT INTO public.reservation_ship_ship_additional_services(
	reservation_ship_reservation_ship_id, ship_additional_services_ship_additional_service_id)
	VALUES ('7', '3');

INSERT INTO public.reservation_ship_fishing_equipment_for_ship(
	reservation_ship_reservation_ship_id, fishing_equipment_for_ship_fishing_equipment_for_ship_id)
	VALUES ('7', '1');
	
INSERT INTO public.reservation_ship_fishing_equipment_for_ship(
	reservation_ship_reservation_ship_id, fishing_equipment_for_ship_fishing_equipment_for_ship_id)
	VALUES ('7', '2');	
	
-- ADVENTURE ADDITIONAL SERVICE

INSERT INTO public.adventure_additional_service(
	adventure_additional_service_id, adventure_additional_service_type)
	VALUES (default, 'INSTRUCTOR_HAS_SHIP');
	
-- FISHING EQUIPMENT FOR ADVENTURE
	
INSERT INTO public.fishing_equipment_for_adventure(
	fishing_equipment_for_adventure_id, fishing_equipment_for_adventure_type)
	VALUES (default, 'FISHING_ROD');
	
INSERT INTO public.fishing_equipment_for_adventure(
	fishing_equipment_for_adventure_id, fishing_equipment_for_adventure_type)
	VALUES (default, 'FISHING_NET');

-- ADVENTURE FAST RESERVATION

INSERT INTO public.reservation_adventure(adventure_reservation_cancel_term, date_and_time, duration, max_number_of_persons, price, discount_percentage, reservation_status, adventure_id)
	VALUES ('FREE', '2022-03-10 10:00:00', '2', '2', '2800', '40', 'WAITING', '1');

INSERT INTO public.reservation_adventure_adventure_additional_service(
	reservation_adventure_reservation_adventure_id, adventure_additional_service_adventure_additional_service_id)
	VALUES ('5', '1');
	
INSERT INTO public.reservation_adventure_fishing_equipment_for_adventure(
	reservation_adventure_reservation_adventure_id, fishing_equipment_for_adventure_fishing_equipment_for_adventure)
	VALUES ('5', '1');
	
INSERT INTO public.reservation_adventure_fishing_equipment_for_adventure(
	reservation_adventure_reservation_adventure_id, fishing_equipment_for_adventure_fishing_equipment_for_adventure)
	VALUES ('5', '2');
	
	
INSERT INTO public.reservation_adventure(adventure_reservation_cancel_term, date_and_time, duration, max_number_of_persons, price, discount_percentage, reservation_status, adventure_id)
	VALUES ('FREE', '2022-03-17 15:30:00', '3', '5', '3000', '15', 'WAITING', '2');
	
INSERT INTO public.reservation_adventure_fishing_equipment_for_adventure(
	reservation_adventure_reservation_adventure_id, fishing_equipment_for_adventure_fishing_equipment_for_adventure)
	VALUES ('6', '1');
	
INSERT INTO public.reservation_adventure_fishing_equipment_for_adventure(
	reservation_adventure_reservation_adventure_id, fishing_equipment_for_adventure_fishing_equipment_for_adventure)
	VALUES ('6', '2');
