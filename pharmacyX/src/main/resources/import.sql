insert into address (id, city, country, deleted, postal_code, street) values (1, 'Novi Sad', 'Serbia', false, '21000', 'Radnicka 5');
insert into address (id, city, country, deleted, postal_code, street) values (2, 'Novi Sad', 'Serbia', false, '21000', 'Radnicka 6');
insert into address (id, city, country, deleted, postal_code, street) values (3, 'Novi Sad', 'Serbia', false, '21000', 'Radnicka 7');
insert into address (id, city, country, deleted, postal_code, street) values (4, 'Novi Sad', 'Serbia', false, '21000', 'Strazilovska 1');
insert into address (id, city, country, deleted, postal_code, street) values (5, 'Novi Sad', 'Serbia', false, '21000', 'Strazilovska 5');
insert into address (id, city, country, deleted, postal_code, street) values (6, 'Novi Sad', 'Serbia', false, '21000', 'Strazilovska 10');
insert into address (id, city, country, deleted, postal_code, street) values (7, 'Novi Sad', 'Serbia', false, '21000', 'Pupinova 1');
insert into address (id, city, country, deleted, postal_code, street) values (8, 'Novi Sad', 'Serbia', false, '21000', 'Pupinova 55');
insert into address (id, city, country, deleted, postal_code, street) values (9, 'Novi Sad', 'Serbia', false, '21000', 'Pupinova 29');
insert into address (id, city, country, deleted, postal_code, street) values (10, 'Novi Sad', 'Serbia', false, '21000', 'Bulevar Oslobodjenja 12');

insert into user (id, first_name, last_name, email, password, phone, user_type, address_id, user_role, deleted) values (1, 'Nikola', 'Nikolic', 'nikola@gmail.com', '$2a$10$Bhbe5Azsb1wOUnTXJMfnrO8Ou2kCrBDI4AQDwTOEm5MxG/0YtmtzG', '111-222', 'pharmacist_user', 1, 2, false);
insert into user (id, first_name, last_name, email, password, phone, user_type, address_id, user_role, deleted) values (2, 'Nemanja', 'Nemanjic', 'nemanja@gmail.com', '$2a$10$Bhbe5Azsb1wOUnTXJMfnrO8Ou2kCrBDI4AQDwTOEm5MxG/0YtmtzG', '333-444', 'pharmacist_user', 2, 2, false);
insert into user (id, first_name, last_name, email, password, phone, user_type, address_id, user_role, deleted) values (3, 'Ana', 'Anic', 'ana@gmail.com', '$2a$10$Bhbe5Azsb1wOUnTXJMfnrO8Ou2kCrBDI4AQDwTOEm5MxG/0YtmtzG', '333-222', 'patient_user', 3, 0, false);
insert into user (id, first_name, last_name, email, password, phone, user_type, address_id, user_role, deleted) values (4, 'Mira', 'Miric', 'mira@gmail.com', '$2a$10$Bhbe5Azsb1wOUnTXJMfnrO8Ou2kCrBDI4AQDwTOEm5MxG/0YtmtzG', '111-555', 'patient_user', 4, 0, false);
insert into user (id, first_name, last_name, email, password, phone, user_type, address_id, user_role, deleted) values (5, 'Pera', 'Peric', 'pera@gmail.com', '$2a$10$Bhbe5Azsb1wOUnTXJMfnrO8Ou2kCrBDI4AQDwTOEm5MxG/0YtmtzG', '111-333', 'dermatologist_user', 5, 1, false);
insert into user (id, first_name, last_name, email, password, phone, user_type, address_id, user_role, deleted) values (6, 'Djura', 'Djuric', 'djura@gmail.com', '$2a$10$Bhbe5Azsb1wOUnTXJMfnrO8Ou2kCrBDI4AQDwTOEm5MxG/0YtmtzG', '111-444', 'dermatologist_user', 6, 1, false);
				

insert into pharmacy (id, name, description, deleted) values (1, 'Apoteka PharmacyX 1', 'Bulevar oslobodjenja 60', false);
insert into pharmacy (id, name, description, deleted) values (2, 'Apoteka PharmacyX 2', 'Somborski bulevar 50', false);
insert into pharmacy (id, name, description, deleted) values (3, 'Apoteka PharmacyX 3', 'Novo Naselje', false);
insert into pharmacy (id, name, description, deleted) values (4, 'Apoteka PharmacyX 4', 'Centar', false);

insert into pharmacy_pharmacists (pharmacy_id, pharmacist_id) values (1, 1);
insert into pharmacy_pharmacists (pharmacy_id, pharmacist_id) values (1, 2);
insert into pharmacy_dermatologists (pharmacy_id, dermatologist_id) values (1, 5);
insert into pharmacy_dermatologists (pharmacy_id, dermatologist_id) values (1, 6);

insert into drug_specification (id, contraindications, daily_recommendation, description, drug_form, drug_type, prescription, producer, structure, deleted) values (1, 'Glavobolja', 'Preporuceno 1 tableta dnevno', 'Lek protiv mucnine', 1, 0, true, 'Convol', 'Sadrzi 450mg necega', false);
insert into drug_specification (id, contraindications, daily_recommendation, description, drug_form, drug_type, prescription, producer, structure, deleted) values (2, 'Temperatura', '1 kesica dnevno', 'Lek protiv bola u misicima', 0, 3, true, 'Convol', 'Sadrzi 450mg necega', false);
insert into drug_specification (id, contraindications, daily_recommendation, description, drug_form, drug_type, prescription, producer, structure, deleted) values (3, 'Povracanje', 'Preporuceno 1 kapsula dnevno', 'Lek protiv upale grla', 1, 1, true, 'Tantum', 'Sadrzi 450mg necega', false);
insert into drug_specification (id, contraindications, daily_recommendation, description, drug_form, drug_type, prescription, producer, structure, deleted) values (4, 'Malaksalost, dijareja', 'Preporuceno 1 tableta na 4h', 'Brufen', 1, 1, true, 'Bayer', 'Sadrzi 200mg ibuprofena', false);
				
insert into drug (id, code, name, specification_id, deleted) values (1, 'SIFR111222333', 'Brufen 600mg', 3, false);
insert into drug (id, code, name, specification_id, deleted) values (2, 'SIFR111222444', 'Rapidol 200mg', 1, false);
insert into drug (id, code, name, specification_id, deleted) values (3, 'SIFR111222555', 'Enterofuril 200', 0, false);
insert into drug (id, code, name, specification_id, deleted) values (4, 'SIFR111222666', 'Tantum Verde Kapsule', 2, false);
				
insert into drug_specification_substitutes (drug_specification_id, substitute_drug_id) values (1, 3);

insert into appointment (id, appointment_type, date_time, price, therapy_description, diagnosis, pharmacist_opinion, patient_id, pharmacy_id, dermatologist_id, pharmacist_id) values (1, 'dermatologist_appointment', '2021-01-02 16:00:00', 1500.00, 'Therapy description...', 'Dermatologist diagnosis...', null, 3, 1, 5, null);
insert into appointment (id, appointment_type, date_time, price, therapy_description, diagnosis, pharmacist_opinion, patient_id, pharmacy_id, dermatologist_id, pharmacist_id) values (2, 'dermatologist_appointment', '2021-01-02 18:00:00', 1500.00, 'Therapy description2...', 'Dermatologist diagnosis2...', null, 4, 1, 5, null);

insert into appointment (id, appointment_type, date_time, price, therapy_description, diagnosis, pharmacist_opinion, patient_id, pharmacy_id, dermatologist_id, pharmacist_id) values (3, 'pharmacist_appointment', '2021-01-03 16:00:00', 1500.00, 'Therapy description...', null, null, null, 1, null, 1);
insert into appointment (id, appointment_type, date_time, price, therapy_description, diagnosis, pharmacist_opinion, patient_id, pharmacy_id, dermatologist_id, pharmacist_id) values (4, 'pharmacist_appointment', '2021-01-03 18:00:00', 1500.00, 'Therapy description2...', null, null, null, 1, null, 1);
			
					
