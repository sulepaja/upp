#EDITORS #password e
insert into user(id, city, email, lastname, firstname, password, country, role, username) values ('1','Novi Sad','marica50@ptt.yu','Zoric','Marica','$2a$10$BQRyiN/k2eUCQWxPL3htkesrDAVw/11wuxFG4zv5Evp/0l9jMU2ay','Srbija','EDITOR','maricaZoric');
insert into user(id, city, email, lastname, firstname, password, country, role, username) values ('2','Novi Sad','a@a.com','Lupic','Aleksandar','$2a$10$BQRyiN/k2eUCQWxPL3htkesrDAVw/11wuxFG4zv5Evp/0l9jMU2ay','Srbija','EDITOR','alexLupic');
insert into user(id, city, email, lastname, firstname, password, country, role, username) values ('10','Novi Sad','ee','Lupic','Aleksandar','$2a$10$BQRyiN/k2eUCQWxPL3htkesrDAVw/11wuxFG4zv5Evp/0l9jMU2ay','Srbija','EDITOR','alexLupic2');

#AUTHORS
insert into user(id, city, email, lastname, firstname, password, country, role, username) values ('3','Novi Sad','e@e.com','Vojvodic','Elenora','$2a$10$BQRyiN/k2eUCQWxPL3htkesrDAVw/11wuxFG4zv5Evp/0l9jMU2ay','Srbija','USER','elenora95');
insert into user(id, city, email, lastname, firstname, password, country, role, username) values ('11','Novi Sad','e','Vojvodic','Elenora','$2a$10$BQRyiN/k2eUCQWxPL3htkesrDAVw/11wuxFG4zv5Evp/0l9jMU2ay','Srbija','USER','elenora951');

#SECEDITORS
insert into user(id, city, email, lastname, firstname, password, country, role, username) values ('12','Novi Sad','seditor1@editor.com','Asad','Jana','$2a$10$BQRyiN/k2eUCQWxPL3htkesrDAVw/11wuxFG4zv5Evp/0l9jMU2ay','Srbija','SECEDITOR','jana_as');
insert into user(id, city, email, lastname, firstname, password, country, role, username) values ('13','Novi Sad','seditor2@editor.com','Rocevic','Miroslav','$2a$10$BQRyiN/k2eUCQWxPL3htkesrDAVw/11wuxFG4zv5Evp/0l9jMU2ay','Srbija','SECEDITOR','ronule123');



#REVIEWERS
insert into user(id, city, email, lastname, firstname, password, country, role, username, reviewer_magazine_id) values ('4','Novi Sad','r1@r.com','Miskov','Milos','$2a$10$BQRyiN/k2eUCQWxPL3htkesrDAVw/11wuxFG4zv5Evp/0l9jMU2ay','Srbija','REVIEWER','miskec', '1');
insert into user(id, city, email, lastname, firstname, password, country, role, username, reviewer_magazine_id) values ('5','Novi Sad','r2@r.com','Milutin','Nikola','$2a$10$BQRyiN/k2eUCQWxPL3htkesrDAVw/11wuxFG4zv5Evp/0l9jMU2ay','Srbija','REVIEWER','nikolaMilutin', '1');
insert into user(id, city, email, lastname, firstname, password, country, role, username, reviewer_magazine_id) values ('6','Novi Sad','r3@r.com','Golocorbin','Stanko','$2a$10$BQRyiN/k2eUCQWxPL3htkesrDAVw/11wuxFG4zv5Evp/0l9jMU2ay','Srbija','REVIEWER','stankoGol','2');
insert into user(id, city, email, lastname, firstname, password, country, role, username, reviewer_magazine_id) values ('7','Novi Sad','r4@r.com','Cubric','Jovica','$2a$10$BQRyiN/k2eUCQWxPL3htkesrDAVw/11wuxFG4zv5Evp/0l9jMU2ay','Srbija','REVIEWER','eLeMiRaC', '2');

# $2y$12$QiNu1WMxQxzn.xzce8FKCOu8LXaaQB.wZVNW/uxrrs1tg9QpJ9Oaa
# $2a$10$BQRyiN/k2eUCQWxPL3htkesrDAVw/11wuxFG4zv5Evp/0l9jMU2ay
#--SCIENTIFIC FIELDS
insert into scientific_field(id, name) values ('1', 'Information Technology');
insert into scientific_field(id, name) values ('2', 'Electrical Engineering');
insert into scientific_field(id, name) values ('3', 'Civil Engineering');
insert into scientific_field(id, name) values ('4', 'Mechanical Engineering');
insert into scientific_field(id, name) values ('5', 'Physics');
insert into scientific_field(id, name) values ('6', 'Mathemathics');
insert into scientific_field(id, name) values ('7', 'Chemistry');
insert into scientific_field(id, name) values ('8', 'Biology');
insert into scientific_field(id, name) values ('9', 'Geography');
insert into scientific_field(id, name) values ('10', 'Medicine');

#--USER SCIENTIFIC FIELD
insert into user_scientific_field(user_id, scientific_field_id) values (12, 1);
insert into user_scientific_field(user_id, scientific_field_id) values (13, 2);

#--MAGAZINES
insert into magazine(id, issn, name, scientific_fields_id, price, editor_id) values ('1','1234-4321','Engineering Monthly', '1', '0.00','1');
insert into magazine(id, issn, name, scientific_fields_id, price, editor_id) values ('2','4321-1234','This Month in Physics and Maths', '2', '0.00','2');

#--MAGAZINE_FIELDS
# insert into magazine_fields(mag_id, field_id) values ('1','2');
# insert into magazine_fields(mag_id, field_id) values ('1','3');
# insert into magazine_fields(mag_id, field_id) values ('1','4');
#
# insert into magazine_fields(mag_id, field_id) values ('2','5');
#insert into magazine_fields(mag_id, field_id) values ('2','6');

#--SCIENCE PAPERS
# --insert into science_paper(id, name, keywords, abbstract, scientific_field_id, science_magazine_id) values ('1','A thermodynamic framework for the modeling of crystallizable triple shape memory polymers','thermodynamic,modeling,polymers','Triple shape memory polymers (TSMPs) can be programed to remember and switch be- tween three distinct shapes with the use of external stimuli','2','1');
# --insert into science_paper(id, name, keywords, abbstract, scientific_field_id, science_magazine_id) values ('2','Theoretical framework for percolation threshold, tortuosity and transport properties of porous materials containing 3D non-spherical pores','percolation,tortuosity,transport,3D,pores','Understanding the effects of porous network characteristics including the percolation and tortuosity on transport properties of porous materials is of great importance for the design and optimization of such materials','2','1');
# --insert into science_paper(id, name, keywords, abbstract, scientific_field_id, science_magazine_id) values ('3','Finalised Marketing','engineering,merketing,instructions','The information in this publication may be reproduced to support SQA qualifications only on a non-commercial basis. If it is reproduced, SQA should be clearly acknowledged as the source.','2','1');
# --insert into science_paper(id, name, keywords, abbstract, scientific_field_id, science_magazine_id) values ('4','Physical pendulum experiment re-investigated with an accelerometer','pendulum,accelerometer,sensor','The physical pendulum experiment is the typicalone to introduce the physics of oscillating systems.','5','2');
#
#

