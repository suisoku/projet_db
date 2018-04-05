drop sequence rang_seq;
drop sequence idsem_seq;
drop sequence idpers_seq;
drop sequence idact_seq;
drop sequence prest_seq;
drop table ATTENTES;
drop table RESERVATIONS;
drop table EVENEMENTS;
drop table CONFERENCES;
drop table SALLES;
drop table PRESTATAIRES;
drop table ACTIVITEES;
drop table CRENEAUX;
drop table SEMINAIRES;
drop table CONFERENCIERS;
drop table ANIMATEURS;
drop table PERSONNES;


CREATE TABLE PERSONNES(
	ID_PERS NUMBER(7),
	NOM VARCHAR(20) not null,
	PRENOM VARCHAR(20) not null,
	ADRESSE VARCHAR(100),
	TEL VARCHAR(10),
	MAIL VARCHAR(30),
	primary key (ID_PERS)
);

CREATE TABLE ANIMATEURS(
	ID_ANIM NUMBER(5),
	primary key (ID_ANIM),
	foreign key (ID_ANIM) references PERSONNES(ID_PERS)
);


CREATE TABLE CONFERENCIERS(
	ID_CONF NUMBER(5) ,
	primary key (ID_CONF),
	foreign key (ID_CONF) references PERSONNES(ID_PERS)
);

CREATE TABLE SEMINAIRES(
	ID_SEM NUMBER(5),
	THEME VARCHAR(30),
	DEJEUNER CHAR(5) ,
	ID_ANIM NUMBER(5),
	primary key (ID_SEM),
	foreign key (ID_ANIM) references ANIMATEURS(ID_ANIM),
	constraint dejeuner check (DEJEUNER in ('TRUE' , 'FALSE'))
);


CREATE TABLE CRENEAUX(
	ID_SEM NUMBER(5),
	MOMENT VARCHAR(5) not null ,
	primary key (ID_SEM,MOMENT),
	foreign key (ID_SEM) references SEMINAIRES(ID_SEM),
	constraint moment_tf check (MOMENT in ('MATIN','APREM'))
);

CREATE TABLE ACTIVITEES(
	ID_ACT NUMBER(5),
	NOM_ACT VARCHAR(30),
	ID_SEM NUMBER(5),
	MOMENT VARCHAR(5),
	primary key (ID_ACT),
	foreign key (ID_SEM, MOMENT) references CRENEAUX(ID_SEM,MOMENT)
);

CREATE TABLE PRESTATAIRES(
	ID_PRES NUMBER(5),
	NOM_P VARCHAR(50),
	ADRESSE VARCHAR(100),
	TARIF_P NUMBER(7,2),
	TARIF_D NUMBER(7,2) ,
	primary key (ID_PRES),
	constraint positif_p check (TARIF_P > 0),
	constraint positif_d check (TARIF_D > 0)
);

CREATE TABLE SALLES(
	NOM_S VARCHAR(30),
	ID_PRES NUMBER(5),
	TARIF_S NUMBER(7,2),
	NBPLACES NUMBER(5) not null,
	primary key (NOM_S, ID_PRES),
	foreign key (ID_PRES) references PRESTATAIRES(ID_PRES),
	constraint positif_s check (TARIF_S > 0),
	constraint  places_min check (NBPLACES > 2)
);


CREATE TABLE CONFERENCES(
	ID_CONF NUMBER(5),
	ID_SEM NUMBER(30),
	TITRE VARCHAR(30),
	MONTANT NUMBER(7,2),
	SUPPORT VARCHAR(5) not null,
	primary key (ID_CONF,ID_SEM),
	foreign key (ID_CONF) references CONFERENCIERS(ID_CONF),
	foreign key (ID_SEM) references SEMINAIRES(ID_SEM),
	constraint positif_montant check (MONTANT > 0),
	constraint support_enum check (SUPPORT IN ('TRUE' , 'FALSE'))
);



CREATE TABLE EVENEMENTS(
	ID_SEM NUMBER(5),
	DATE_SEM DATE,
	NOM_S VARCHAR(30),
	ID_PRES NUMBER(5),
	TARIF NUMBER(5,2) ,
	STATUT VARCHAR(10) not null,
	primary key (ID_SEM, DATE_SEM),
	foreign key (NOM_S, ID_PRES) references SALLES(NOM_S, ID_PRES),
	foreign key (ID_SEM) references SEMINAIRES(ID_SEM),
	constraint positif_sem check (TARIF > 0),
	constraint statut_enum1 check ( STATUT IN ('AVAILABLE', 'CLOS_30J' ,'CLOS_7J'))
);

CREATE TABLE RESERVATIONS(
	ID_PERS NUMBER(7),
        ID_SEM NUMBER(5),
	DATE_SEM DATE,
   	DATE_R DATE,
   	STATUT VARCHAR(10) not null,
	primary key (ID_PERS,ID_SEM, DATE_SEM),
  foreign key (ID_SEM, DATE_SEM) references EVENEMENTS (ID_SEM, DATE_SEM),
	foreign key (ID_PERS) references PERSONNES(ID_PERS),
	constraint statut_enum check (STATUT in ('CONFIRME', 'ANNULE' ,'ATTENTE'))
);


CREATE TABLE ATTENTES(
	RANG NUMBER(7),
	ID_PERS NUMBER(7),
  ID_SEM NUMBER(5),
	DATE_SEM DATE,
   	DATE_R DATE,
   	primary key(RANG),
	foreign key (ID_PERS , ID_SEM, DATE_SEM) references RESERVATIONS(ID_PERS, ID_SEM, DATE_SEM)
);

CREATE SEQUENCE rang_seq start with -1 increment by 1 minvalue -1 maxvalue 10000;
CREATE SEQUENCE idsem_seq start with -1 increment by 1 minvalue -1 maxvalue 10000;
CREATE SEQUENCE idpers_seq start with -1 increment by 1 minvalue -1 maxvalue 10000;
CREATE SEQUENCE idact_seq start with -1 increment by 1 minvalue -1 maxvalue 10000;
CREATE SEQUENCE prest_seq start with -1 increment by 1 minvalue -1 maxvalue 10000;
