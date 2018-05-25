#@(#) script.ddl

DROP TABLE IF EXISTS Categories_Articles;
DROP TABLE IF EXISTS Groups_Articles;
DROP TABLE IF EXISTS Reports;
DROP TABLE IF EXISTS Notifications;
DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS User_read_categories;
DROP TABLE IF EXISTS User_achievements;
DROP TABLE IF EXISTS Group_Users;
DROP TABLE IF EXISTS Group_Invitations;
DROP TABLE IF EXISTS Articles;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Roles;
DROP TABLE IF EXISTS Report_State;
DROP TABLE IF EXISTS Invitation_State;
DROP TABLE IF EXISTS Gender;
DROP TABLE IF EXISTS Groups;
DROP TABLE IF EXISTS Categories;
DROP TABLE IF EXISTS Achievements;
DROP TABLE IF EXISTS hibernate_sequence;

CREATE TABLE Achievements
(
	title varchar (255),
	description varchar (255),
	id bigint,
	PRIMARY KEY(id)
);

CREATE TABLE Categories
(
	name varchar (255),
	description varchar (255),
	id bigint,
	PRIMARY KEY(id)
);

CREATE TABLE Groups
(
	title varchar (255),
	description varchar (255),
	creation_date date,
	id bigint,
	PRIMARY KEY(id)
);

CREATE TABLE Gender
(
	id_Gender integer,
	name char (6) NOT NULL,
	PRIMARY KEY(id_Gender)
);
INSERT INTO Gender(id_Gender, name) VALUES(0, 'Male');
INSERT INTO Gender(id_Gender, name) VALUES(1, 'Female');

CREATE TABLE Invitation_State
(
	id_Invitation_State integer,
	name char (8) NOT NULL,
	PRIMARY KEY(id_Invitation_State)
);
INSERT INTO Invitation_State(id_Invitation_State, name) VALUES(0, 'New');
INSERT INTO Invitation_State(id_Invitation_State, name) VALUES(1, 'Accepted');
INSERT INTO Invitation_State(id_Invitation_State, name) VALUES(2, 'Declined');

CREATE TABLE Report_State
(
	id_Report_State integer,
	name char (7) NOT NULL,
	PRIMARY KEY(id_Report_State)
);
INSERT INTO Report_State(id_Report_State, name) VALUES(0, 'Created');
INSERT INTO Report_State(id_Report_State, name) VALUES(1, 'Viewed');
INSERT INTO Report_State(id_Report_State, name) VALUES(2, 'Solved');

CREATE TABLE Roles
(
	id_Roles integer,
	name char (7) NOT NULL,
	PRIMARY KEY(id_Roles)
);
INSERT INTO Roles(id_Roles, name) VALUES(0, 'Admin');
INSERT INTO Roles(id_Roles, name) VALUES(1, 'Writer');
INSERT INTO Roles(id_Roles, name) VALUES(2, 'Regular');

CREATE TABLE Users
(
	username varchar (255),
	firstname varchar (255),
	lastname varchar (255),
	password varchar (255),
	email varchar (255),
	birthdate date,
	is_banned boolean,
	id bigint,
	gender integer,
	role integer,
	PRIMARY KEY(id),
	FOREIGN KEY(role) REFERENCES Roles (id_Roles),
	FOREIGN KEY(gender) REFERENCES Gender (id_Gender)
);

CREATE TABLE Articles
(
	in_main_group boolean,
	text mediumtext,
	title varchar (255),
	creation_date datetime,
	edit_date datetime,
	rating integer,
	views integer,
	publication_time datetime,
	picture mediumblob,
	id bigint,
	fk_Usersid bigint NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(fk_Usersid) REFERENCES Users (id)
);

CREATE TABLE Group_Invitations
(
	date datetime,
	id bigint,
	state integer,
	fk_Usersid bigint NOT NULL,
	fk_Groupsid bigint NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(state) REFERENCES Invitation_State (id_Invitation_State),
	FOREIGN KEY(fk_Usersid) REFERENCES Users (id),
	FOREIGN KEY(fk_Groupsid) REFERENCES Groups (id)
);

CREATE TABLE Group_Users
(
	date_joined date,
	id bigint,
	role integer,
	fk_Usersid bigint NOT NULL,
	fk_Groupsid bigint NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(role) REFERENCES Roles (id_Roles),
	FOREIGN KEY(fk_Usersid) REFERENCES Users (id),
	FOREIGN KEY(fk_Groupsid) REFERENCES Groups (id)
);

CREATE TABLE User_achievements
(
	date date,
	id bigint,
	fk_Usersid bigint NOT NULL,
	fk_Achievementsid bigint NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(fk_Usersid) REFERENCES Users (id),
	FOREIGN KEY(fk_Achievementsid) REFERENCES Achievements (id)
);

CREATE TABLE User_read_categories
(
	count integer,
	id bigint,
	fk_Usersid bigint NOT NULL,
	fk_Categoriesid bigint NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(fk_Usersid) REFERENCES Users (id),
	FOREIGN KEY(fk_Categoriesid) REFERENCES Categories (id)
);

CREATE TABLE Comments
(
	creation_date datetime,
	edit_date datetime,
	text varchar (255),
	id bigint,
	fk_Usersid bigint NOT NULL,
	fk_Articlesid bigint NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(fk_Usersid) REFERENCES Users (id),
	FOREIGN KEY(fk_Articlesid) REFERENCES Articles (id)
);

CREATE TABLE Notifications
(
	isRead boolean,
	description varchar (255),
	date datetime,
	id bigint,
	fk_Articlesid bigint NOT NULL,
	fk_Usersid bigint NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(fk_Articlesid) REFERENCES Articles (id),
	FOREIGN KEY(fk_Usersid) REFERENCES Users (id)
);

CREATE TABLE Reports
(
	date datetime,
	comment varchar (255),
	id bigint,
	state integer,
	fk_Articlesid bigint NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(state) REFERENCES Report_State (id_Report_State),
	FOREIGN KEY(fk_Articlesid) REFERENCES Articles (id)
);

CREATE TABLE Groups_Articles
(
	fk_Groupsid bigint,
	fk_Articlesid bigint,
	PRIMARY KEY(fk_Groupsid, fk_Articlesid),
	FOREIGN KEY(fk_Groupsid) REFERENCES Groups (id),
	FOREIGN KEY(fk_Articlesid) REFERENCES Articles (id)
);

CREATE TABLE Categories_Articles
(
	fk_Categoriesid bigint,
	fk_Articlesid bigint,
	PRIMARY KEY(fk_Categoriesid, fk_Articlesid),
	FOREIGN KEY(fk_Categoriesid) REFERENCES Categories (id),
	FOREIGN KEY(fk_Articlesid) REFERENCES Articles (id)
);

create table hibernate_sequence (next_val bigint) engine=MyISAM;
INSERT INTO `newsportaldb`.`hibernate_sequence` (`next_val`) VALUES (0);