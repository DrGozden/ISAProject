	 insert into address (id, city, country, deleted, postal_code, street) values (701, 'Novi Sad', 'Serbia', false, '21000', 'Radnicka 5');
insert into address (id, city, country, deleted, postal_code, street) values (702, 'Novi Sad', 'Serbia', false, '21000', 'Radnicka 6');
insert into address (id, city, country, deleted, postal_code, street) values (703, 'Novi Sad', 'Serbia', false, '21000', 'Radnicka 7');
insert into address (id, city, country, deleted, postal_code, street) values (704, 'Novi Sad', 'Serbia', false, '21000', 'Strazilovska 1');
insert into address (id, city, country, deleted, postal_code, street) values (705, 'Novi Sad', 'Serbia', false, '21000', 'Strazilovska 5');
insert into address (id, city, country, deleted, postal_code, street) values (706, 'Novi Sad', 'Serbia', false, '21000', 'Strazilovska 10');
insert into address (id, city, country, deleted, postal_code, street) values (707, 'Novi Sad', 'Serbia', false, '21000', 'Somborski Bulevar 1');
insert into address (id, city, country, deleted, postal_code, street) values (708, 'Novi Sad', 'Serbia', false, '21000', 'Koste Racina 55');
insert into address (id, city, country, deleted, postal_code, street) values (709, 'Novi Sad', 'Serbia', false, '21000', 'Pupinova 29');
insert into address (id, city, country, deleted, postal_code, street) values (710, 'Novi Sad', 'Serbia', false, '21000', 'Bulevar Oslobodjenja 12');
insert into address (id, city, country, deleted, postal_code, street) values (888, 'Novi Sad', 'Serbia', false, '21000', 'Bulevar Oslobodjenja 13');
insert into address (id, city, country, deleted, postal_code, street) values (889, 'Novi Sad', 'Serbia', false, '21000', 'Bulevar Oslobodjenja 14');

insert into user (id, first_name, last_name, email, password, phone, user_type, address_id, user_role, deleted, user_status) values (711, 'Nikola', 'Nikolic', 'nikola@gmail.com', '$2a$10$Bhbe5Azsb1wOUnTXJMfnrO8Ou2kCrBDI4AQDwTOEm5MxG/0YtmtzG', '111-222', 'pharmacist_user', 701, 2, false, 0);
insert into user (id, first_name, last_name, email, password, phone, user_type, address_id, user_role, deleted, user_status) values (712, 'Nemanja', 'Nemanjic', 'nemanja@gmail.com', '$2a$10$Bhbe5Azsb1wOUnTXJMfnrO8Ou2kCrBDI4AQDwTOEm5MxG/0YtmtzG', '333-444', 'pharmacist_user', 702, 2, false, 0);
insert into user (id, first_name, last_name, email, password, phone, user_type, address_id, user_role, deleted, user_status) values (713, 'Ana', 'Anic', 'ana@gmail.com', '$2a$10$Bhbe5Azsb1wOUnTXJMfnrO8Ou2kCrBDI4AQDwTOEm5MxG/0YtmtzG', '333-222', 'patient_user', 703, 0, false, 0);
insert into user (id, first_name, last_name, email, password, phone, user_type, address_id, user_role, deleted, user_status) values (714, 'Mira', 'Miric', 'mira@gmail.com', '$2a$10$Bhbe5Azsb1wOUnTXJMfnrO8Ou2kCrBDI4AQDwTOEm5MxG/0YtmtzG', '111-555', 'patient_user', 704, 0, false, 0);
insert into user (id, first_name, last_name, email, password, phone, user_type, address_id, user_role, deleted, user_status) values (715, 'Pera', 'Peric', 'pera@gmail.com', '$2a$10$Bhbe5Azsb1wOUnTXJMfnrO8Ou2kCrBDI4AQDwTOEm5MxG/0YtmtzG', '111-333', 'dermatologist_user', 705, 1, false, 0);
insert into user (id, first_name, last_name, email, password, phone, user_type, address_id, user_role, deleted, user_status) values (716, 'Djura', 'Djuric', 'djura@gmail.com', '$2a$10$Bhbe5Azsb1wOUnTXJMfnrO8Ou2kCrBDI4AQDwTOEm5MxG/0YtmtzG', '111-444', 'dermatologist_user', 706, 1, false, 0);
insert into user (id, first_name, last_name, email, password, phone, user_type, address_id, user_role, deleted, user_status, pharmacy_id) values (878, 'Stefan', 'Stevic', 'stefan@gmail.com', '$2a$10$Bhbe5Azsb1wOUnTXJMfnrO8Ou2kCrBDI4AQDwTOEm5MxG/0YtmtzG', '111-444', 'pharmacy_admin_user', 888, 3, false, 3, 717);
insert into user (id, first_name, last_name, email, password, phone, user_type, address_id, user_role, deleted, user_status, pharmacy_id) values (879, 'Mira', 'Miric', 'mira@gmail.com', '$2a$10$Bhbe5Azsb1wOUnTXJMfnrO8Ou2kCrBDI4AQDwTOEm5MxG/0YtmtzG', '111-444', 'supplier_user', 889, 4, false, 3, 0);
				

insert into pharmacy (id, name, description, address_id, deleted) values (717, 'Apoteka PharmacyX 1', 'Bulevar', 710, false);
insert into pharmacy (id, name, description, address_id, deleted) values (718, 'Apoteka PharmacyX 2', 'Telep', 707, false);
insert into pharmacy (id, name, description, address_id, deleted) values (719, 'Apoteka PharmacyX 3', 'Novo Naselje', 708, false);
insert into pharmacy (id, name, description, address_id, deleted) values (720, 'Apoteka PharmacyX 4', 'Centar', 709, false);

insert into pharmacy_pharmacists (pharmacy_id, pharmacist_id) values (717, 711);
insert into pharmacy_pharmacists (pharmacy_id, pharmacist_id) values (717, 712);
insert into pharmacy_dermatologists (pharmacy_id, dermatologist_id) values (717, 715);
insert into pharmacy_dermatologists (pharmacy_id, dermatologist_id) values (717, 716);

insert into drug_specification (id, contraindications, daily_recommendation, description, drug_form, drug_type, prescription, producer, structure, deleted) values (731, 'Glavobolja', 'Preporuceno 1 tableta dnevno', 'Lek protiv mucnine', 1, 0, true, 'Convol', 'Sadrzi 450mg necega', false);
insert into drug_specification (id, contraindications, daily_recommendation, description, drug_form, drug_type, prescription, producer, structure, deleted) values (732, 'Temperatura', '1 kesica dnevno', 'Lek protiv bola u misicima', 0, 3, true, 'Convol', 'Sadrzi 450mg necega', false);
insert into drug_specification (id, contraindications, daily_recommendation, description, drug_form, drug_type, prescription, producer, structure, deleted) values (733, 'Povracanje', 'Preporuceno 1 kapsula dnevno', 'Lek protiv upale grla', 1, 1, true, 'Tantum', 'Sadrzi 450mg necega', false);
insert into drug_specification (id, contraindications, daily_recommendation, description, drug_form, drug_type, prescription, producer, structure, deleted) values (734, 'Malaksalost, dijareja', 'Preporuceno 1 tableta na 4h', 'Brufen', 1, 1, true, 'Bayer', 'Sadrzi 200mg ibuprofena', false);
insert into drug_specification (id, contraindications, daily_recommendation, description, drug_form, drug_type, prescription, producer, structure, deleted) values (735, 'Bol', 'Preporuceno 2 tableta na 4h', 'TestLek', 1, 1, true, 'Bayer', 'Sadrzi 200mg testLeka', false);
				
insert into drug (id, code, name, specification_id, deleted) values (741, 'SIFR111222333', 'Brufen 600mg', 733, false);
insert into drug (id, code, name, specification_id, deleted) values (742, 'SIFR111222444', 'Rapidol 200mg', 732, false);
insert into drug (id, code, name, specification_id, deleted) values (743, 'SIFR111222555', 'Enterofuril 200', 731, false);
insert into drug (id, code, name, specification_id, deleted) values (744, 'SIFR111222666', 'Tantum Verde Kapsule', 734, false);
insert into drug (id, code, name, specification_id, deleted) values (745, 'SIFR111222777', 'Novi test lek', 735, false);
				
insert into drug_specification_substitutes (drug_specification_id, substitute_drug_id) values (732, 744);
insert into drug_specification_substitutes (drug_specification_id, substitute_drug_id) values (734, 742);


insert into appointment (id, appointment_type, date_time, price, therapy_description, diagnosis, pharmacist_opinion, patient_id, pharmacy_id, dermatologist_id, pharmacist_id, deleted) values (761, 'dermatologist_appointment', '2021-02-02 16:00:00', 1500.00, 'Therapy description...', 'Dermatologist diagnosis...', null, 713, 717, 715, null, false);
insert into appointment (id, appointment_type, date_time, price, therapy_description, diagnosis, pharmacist_opinion, patient_id, pharmacy_id, dermatologist_id, pharmacist_id, deleted) values (762, 'dermatologist_appointment', '2019-09-09 18:00:00', 1500.00, 'Therapy description2...', 'Dermatologist diagnosis2...', null, 713, 717, 715, null, false);

insert into appointment (id, appointment_type, date_time, price, therapy_description, diagnosis, pharmacist_opinion, patient_id, pharmacy_id, dermatologist_id, pharmacist_id, deleted) values (777, 'dermatologist_appointment', '2021-05-02 18:00:00', 1500.00, 'Therapy description2...', 'Dermatologist diagnosis2...', null, null, 717, 715, null, false);
insert into appointment (id, appointment_type, date_time, price, therapy_description, diagnosis, pharmacist_opinion, patient_id, pharmacy_id, dermatologist_id, pharmacist_id, deleted) values (787, 'dermatologist_appointment', '2021-04-02 18:00:00', 1500.00, 'Therapy description2...', 'Dermatologist diagnosis2...', null, null, 717, 715, null, false);

insert into appointment (id, appointment_type, date_time, price, therapy_description, diagnosis, pharmacist_opinion, patient_id, pharmacy_id, dermatologist_id, pharmacist_id, deleted) values (765, 'pharmacist_appointment', '2021-03-03 18:00:00', 1500.00, 'Therapy description2...', 'Pharmacist diagnosis4...', null, 713, 717, null, 711, false);
insert into appointment (id, appointment_type, date_time, price, therapy_description, diagnosis, pharmacist_opinion, patient_id, pharmacy_id, dermatologist_id, pharmacist_id, deleted) values (766, 'pharmacist_appointment', '2021-01-02 18:00:00', 1500.00, 'Therapy description2...', 'Pharmacist diagnosis4...', null, 713, 717, null, 711, false);


insert into appointment (id, appointment_type, date_time, price, therapy_description, diagnosis, pharmacist_opinion, patient_id, pharmacy_id, dermatologist_id, pharmacist_id, deleted) values (763, 'pharmacist_appointment', '2021-01-03 16:00:00', 1500.00, 'Therapy description...', null, null, null, 717, null, 711, false);
insert into appointment (id, appointment_type, date_time, price, therapy_description, diagnosis, pharmacist_opinion, patient_id, pharmacy_id, dermatologist_id, pharmacist_id, deleted) values (764, 'pharmacist_appointment', '2021-01-03 18:00:00', 1500.00, 'Therapy description2...', null, null, null, 717, null, 711, false);
			
insert into user_allergies (patient_id, drug_id) values (713, 741);		
insert into user_allergies (patient_id, drug_id) values (713, 742);
insert into user_allergies (patient_id, drug_id) values (714, 743);

insert into price_list (id, start_date, end_date, deleted) values (771, '2021-01-01', '2021-08-01', false);
insert into price_list (id, start_date, end_date, deleted) values (772, '2021-08-02', '2022-01-01', false);

insert into pharmacy_price_list (pharmacy_id, price_list_id) values (717, 771);
insert into pharmacy_price_list (pharmacy_id, price_list_id) values (717, 772);

insert into pharmacy_pricelist_mapping (price_list_id, price, drug_id) values (771, 250.0, 741);
insert into pharmacy_pricelist_mapping (price_list_id, price, drug_id) values (771, 400.0, 742);
insert into pharmacy_pricelist_mapping (price_list_id, price, drug_id) values (771, 970.0, 743);
insert into pharmacy_pricelist_mapping (price_list_id, price, drug_id) values (771, 135.0, 744);

insert into pharmacy_pricelist_mapping (price_list_id, price, drug_id) values (772, 300.0, 741);
insert into pharmacy_pricelist_mapping (price_list_id, price, drug_id) values (772, 450.0, 742);
insert into pharmacy_pricelist_mapping (price_list_id, price, drug_id) values (772, 1000.0, 743);
insert into pharmacy_pricelist_mapping (price_list_id, price, drug_id) values (772, 200.0, 744);

insert into pharmacy_stock_mapping (pharmacy_id, quantity, drug_id) values (717, 30, 741);
insert into pharmacy_stock_mapping (pharmacy_id, quantity, drug_id) values (717, 52, 742);
insert into pharmacy_stock_mapping (pharmacy_id, quantity, drug_id) values (717, 3, 743);
insert into pharmacy_stock_mapping (pharmacy_id, quantity, drug_id) values (717, 20, 744);
insert into pharmacy_stock_mapping (pharmacy_id, quantity, drug_id) values (718, 20, 745);

insert into drug_reservation (id, code, deadline, deleted, drug_id, pharmacy_id) values (997, 'SIFR111222555', '2021-02-02', false, 743, 717);
insert into drug_reservation (id, code, deadline, deleted, drug_id, pharmacy_id) values (998, 'SIFR111222555', '2020-01-01', false, 743, 717);
			
insert into user_drug_reservations (patient_id, drug_reservation_id) values (713, 997);		
insert into user_drug_reservations (patient_id, drug_reservation_id) values (713, 998);

insert into user_appointment_history(patient_id, appointment_id) values (713, 761);		
insert into user_appointment_history(patient_id, appointment_id) values (713, 765);		
insert into user_appointment_history(patient_id, appointment_id) values (713, 766);	

insert into working_hours(id, day, employee_id, end_time, pharmacy_id, start_time) values (801, 0, 715, '21:00', 717, '08:00');
insert into working_hours(id, day, employee_id, end_time, pharmacy_id, start_time) values (802, 1, 715, '21:00', 717, '08:00');
insert into working_hours(id, day, employee_id, end_time, pharmacy_id, start_time) values (803, 2, 715, '21:00', 717, '08:00');
insert into working_hours(id, day, employee_id, end_time, pharmacy_id, start_time) values (804, 3, 715, '21:00', 717, '08:00');
insert into working_hours(id, day, employee_id, end_time, pharmacy_id, start_time) values (805, 4, 715, '21:00', 717, '08:00');
insert into working_hours(id, day, employee_id, end_time, pharmacy_id, start_time) values (806, 5, 715, '21:00', 717, '08:00');
insert into working_hours(id, day, employee_id, end_time, pharmacy_id, start_time) values (807, 6, 715, '21:00', 717, '08:00');

insert into user_working_hours(pharmacist_id, working_hours_id, dermatologist_id) values (-1, 801, 715);
insert into user_working_hours(pharmacist_id, working_hours_id, dermatologist_id) values (-1, 802, 715);
insert into user_working_hours(pharmacist_id, working_hours_id, dermatologist_id) values (-1, 803, 715);
insert into user_working_hours(pharmacist_id, working_hours_id, dermatologist_id) values (-1, 804, 715);

insert into user_working_hours(pharmacist_id, working_hours_id, dermatologist_id) values (711, 805, -1);
insert into user_working_hours(pharmacist_id, working_hours_id, dermatologist_id) values (711, 806, -1);
insert into user_working_hours(pharmacist_id, working_hours_id, dermatologist_id) values (711, 807, -1);

insert into vacation(id, accepted, deleted, end_date, reject_description, start_date, user_id) values (901, false, false, '2021-08-08', null, '2021-07-07', 715);
insert into vacation(id, accepted, deleted, end_date, reject_description, start_date, user_id) values (902, false, false, '2021-09-09', null, '2021-08-08', 711);
insert into vacation(id, accepted, deleted, end_date, reject_description, start_date, user_id) values (903, false, false, '2021-10-10', null, '2021-09-09', 712);

insert into supply_order(id, deadline, deleted, pharmacy_id) values (1101, '2021-10-10', false, 717);
insert into supply_order(id, deadline, deleted, pharmacy_id) values (1102, '2021-10-11', false, 717);

insert into supplier_offer(id, delivery_deadline, deleted, status, total_price, order_id, supplier_id) values (1201, '2021-10-11', false, 2, 15000, 1101, 879);

