INSERT INTO public.authority (name) VALUES ('ROLE_CLIENT');
INSERT INTO public.authority (name) VALUES ('ROLE_COTTAGE_OWNER');
INSERT INTO public.authority (name) VALUES ('ROLE_SHIP_OWNER');
INSERT INTO public.authority (name) VALUES ('ROLE_FISHING_INSTRUCTOR');
INSERT INTO public.authority (name) VALUES ('ROLE_SYSTEM_ADMINISTRATOR');

INSERT INTO public.address (city, country, street, street_number) VALUES ('RandomCity', 'RandomCountry', 'RandomStreet', '5');

INSERT INTO public.address (city, country, street, street_number) VALUES ('Novi Sad', 'Srbija', 'Novosadska', '6');

-- Admin 		Password: Admin123!
INSERT INTO public.user (dtype, user_id, email, enabled, first_name, last_name, password, phone_number, salt, user_type, first_login_change_password, address_id) VALUES ('SystemAdministrator', default, 'admin@email.com', true, 'Admin', 'Admin', '$2a$12$9bz1GJdLCG.Ttl/p5al5O.KZOkfhgWv.MhbivCtKvC0JXbp6vyi6W', '123123123', 'AdbMbt4t1KLF-G0SX01WPL==', 'SYSTEM_ADMINISTRATOR', true, 1);
-- Client		Password: Client123! 
INSERT INTO public.user (dtype, user_id, email, enabled, first_name, last_name, password, phone_number, salt, user_type, address_id) VALUES ('Client', default, 'milan98@email.com', true, 'Milan', 'Zivkovic', '$2a$12$Wok9ITG1sj2H5RnigyVvhuHvqaX0k5Ok.lXFpjNrS/ZbBvTb1qWsK', '064555777', 'A10cLt4tiKLF-E0SXn1T-8==', 'CLIENT', 2);

INSERT INTO public.user_authorities(user_user_id, authorities_authority_id) VALUES (1, 5);

INSERT INTO public.user_authorities(user_user_id, authorities_authority_id) VALUES (2, 1);