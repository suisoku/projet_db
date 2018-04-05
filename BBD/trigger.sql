-- [TRIGGER 1] trigger qui met a jour le dejeuner en fonction des creneaux
Create or replace trigger t_dejeuner
after update or insert on CRENEAUX
DECLARE
	dej VARCHAR(5);
BEGIN
    FOR t IN (SELECT id_sem , count(id_sem) as id_c FROM CRENEAUX group by id_sem)
    LOOP
		select dejeuner into dej from seminaires where id_sem = t.id_sem;
		IF (dej is NULL or dej = 'FALSE') THEN
      		IF (t.id_c = 2) THEN
        		UPDATE SEMINAIRES SET dejeuner = 'TRUE' WHERE id_sem = t.id_sem;
      		END IF;
			IF (t.id_c = 1) THEN
        		UPDATE SEMINAIRES SET dejeuner = 'FALSE' WHERE id_sem = t.id_sem;
      		END IF;
		END IF;
    END LOOP;
END;
/
show errors


-- [TRIGGER 3] Lentreprise ne peut organiser plus de 3 seminaires le meme jour.
Create or replace trigger t_seminaires_max
after update or insert on evenements
DECLARE
	nbsem NUMBER(5);
BEGIN
select max(count(date_sem)) into nbsem from evenements group by date_sem;

IF  nbsem >3 THEN
  Raise_application_error('-20101', 'plus de 3 seminaires le meme jour ');
END IF;

END;
/
show errors



-- [TRIGGER 5] trigger qui stock en attente si pas assez de place
Create or replace trigger t_attente
before insert on reservations

FOR EACH ROW
DECLARE
idpers NUMBER(5) ;
idsem NUMBER(5) ;
datesem DATE  ;
dateR DATE ;
nb_dispo NUMBER(5) ;

BEGIN
	idpers := :NEW.id_pers;
	idsem := :NEW.id_sem;
	datesem := :NEW.date_sem;
	dateR := :NEW.date_r;
    	nb_dispo := place_dispo(idsem, datesem);

	IF (nb_dispo <= 0) THEN
		INSERT INTO ATTENTES VALUES ( rang_seq.nextval , idpers , idsem , datesem , dateR);
		:NEW.statut := 'ATTENTE';
	END IF;

END;
/
show errors

-- [TRIGGER 6] suprime la reservation annulee puis enclanche une echange avec la liste d'attente
Create or replace trigger t_att_annule
after update on reservations
DECLARE
idpers NUMBER(5);
idsem NUMBER(5);
datesem DATE;
EXECUTE NUMBER(5);
BEGIN

	BEGIN
		select count(statut) into EXECUTE from reservations where statut = 'ANNULE';
	EXCEPTION
	WHEN NO_DATA_FOUND THEN
		EXECUTE := 0;
	END;

	IF ( EXECUTE > 0) THEN
		select id_pers into idpers from reservations where STATUT ='ANNULE';
		select id_sem into idsem from reservations where STATUT ='ANNULE'; -- on cherche l'evenement annulé (on suppose que la table n'a q'une valeur annulé)
		select date_sem into datesem from reservations where STATUT ='ANNULE';

		DELETE reservations WHERE statut ='ANNULE';
		DELETE attentes WHERE rang =  (SELECT min(rang) FROM attentes WHERE id_sem = idsem AND date_sem = datesem);
	END IF;
END;
/
show errors

--[TRIGGER 7] Declench� a partir du trigger 6 elle perme de confirmer la reservation en attente dans le tableau reservations
Create or replace trigger t_att_confirme
after delete on attentes
FOR EACH ROW
DECLARE
BEGIN
	UPDATE reservations SET statut ='CONFIRME' WHERE id_pers = :OLD.id_pers AND id_sem = :OLD.id_sem AND date_sem = :OLD.date_sem;
END;
/
show errors


--------------------------- TRIGGER D'INSERTIONS -----------------------------


-- CONTRAINTE: INSERTION EVENEMENTS 30 J MAx
Create or replace trigger t_cont_insert_eve
before insert or update on  evenements
FOR EACH ROW
WHEN((new.date_sem - sysdate) < 30)
BEGIN
	Raise_application_error('-20101', 'Erreur , date seminaire inferieur a 30 jours');
END;
/
show errors



-- CONTRAINTE: INSERTION/UPDATE RESERVATION 7 MAX
Create or replace trigger t_insert_reserv
before insert or update on reservations
FOR EACH ROW
WHEN((new.date_sem - sysdate) < 7)
BEGIN
	Raise_application_error('-20101', 'Erreur , Reservation a 7 j du seminaire');
END;
/
show errors

