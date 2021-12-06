INSERT INTO public.authority (name) VALUES ('ROLE_CLIENT');
INSERT INTO public.authority (name) VALUES ('ROLE_COTTAGE_OWNER');
INSERT INTO public.authority (name) VALUES ('ROLE_SHIP_OWNER');
INSERT INTO public.authority (name) VALUES ('ROLE_FISHING_INSTRUCTOR');
INSERT INTO public.authority (name) VALUES ('ROLE_SYSTEM_ADMINISTRATOR');

INSERT INTO public.address (city, country, street, street_number) VALUES ('RandomCity', 'RandomCountry', 'RandomStreet', '5');

INSERT INTO public.address (city, country, street, street_number) VALUES ('Novi Sad', 'Srbija', 'Novosadska', '6');

INSERT INTO public.address (city, country, street, street_number) VALUES ('Novi Sad', 'Srbija', 'Bulevar Oslobodjenja', '10');

-- Admin 		Password: Admin123!
INSERT INTO public.user (dtype, user_id, email, enabled, first_name, last_name, password, phone_number, salt, user_type, first_login_change_password, address_id) VALUES ('SystemAdministrator', default, 'admin@email.com', true, 'Admin', 'Admin', '$2a$12$9bz1GJdLCG.Ttl/p5al5O.KZOkfhgWv.MhbivCtKvC0JXbp6vyi6W', '123123123', 'AdbMbt4t1KLF-G0SX01WPL==', 'SYSTEM_ADMINISTRATOR', true, 1);
-- Client		Password: Client123! 
INSERT INTO public.user (dtype, user_id, email, enabled, first_name, last_name, password, phone_number, salt, user_type, address_id) VALUES ('Client', default, 'milan98@email.com', true, 'Milan', 'Zivkovic', '$2a$12$gMBetyilqUFKX9iv4UuNhOATalqMm9y/LcV44I6c/AooN.VL/geK6', '064555777', 'G1pxvzLwtZujO+hVgIp2og==', 'CLIENT', 2);

-- CottageOwner	Password: Owner123! 
INSERT INTO public.user (dtype, user_id, email, enabled, first_name, last_name, password, phone_number, salt, user_type, address_id) VALUES ('CottageOwner', default, 'milos98@email.com', true, 'Milos', 'Milosevic', '$2a$12$1hs/7XPSREmfh70Omj.Zee5W59JnOsCN41xidlyDv1fVHmizIy.qa', '064555444', 'h96BMRi8MSnZYO/+7RrbYQ==', 'COTTAGE_OWNER', 3);

INSERT INTO public.user_authorities(user_user_id, authorities_authority_id) VALUES (1, 5);

INSERT INTO public.user_authorities(user_user_id, authorities_authority_id) VALUES (2, 1);

INSERT INTO public.user_authorities(user_user_id, authorities_authority_id) VALUES (3, 2);