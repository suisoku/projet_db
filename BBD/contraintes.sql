------------------------------------------------------ CLOTURE 30 JOURS SEMINAIRE----------------------------------------------------------
-- Contrainte creneaux seminaire double inclusion
Create or replace procedure p_creneaux
(
  idInput IN NUMBER
)
IS
        countC NUMBER(1);
BEGIN

select count(id_sem) into countC from creneaux where id_sem = idInput;

IF (countC < 1)
THEN Raise_application_error('-20101', 'erreur inclusion seminaire dans creneaux');
END IF;
END;
/
show errors

-- Contrainte activitées de cardinal 3 exactement
Create or replace procedure p_activite
(
  idInput IN NUMBER
)
IS
        countC NUMBER(1);
BEGIN
select count(id_sem) into countC from ACTIVITEES where id_sem = idInput;

IF (countC != 3)
THEN Raise_application_error('-20101', 'erreur il nya pas 3 activitées');
END IF;
END;
/
show errors


-- Contrainte sur conference cardinalité 1
Create or replace procedure p_conference
(
  idInput IN NUMBER
)
IS
        countC NUMBER(1);
BEGIN
select count(id_sem) into countC from CONFERENCES where id_sem = idInput;

IF (countC < 1)
THEN Raise_application_error('-20101', 'erreur il nya pas de conferences');
END IF;
END;
/
show errors
-- execute p_creneaux(0);
--------------------------------------------------------------------- CLOTURE 7 JOURS-----------------------------------------------------------------

-- Contrainte sur conference cardinalité 1
Create or replace procedure p_support
(
  idInput IN NUMBER
)
IS
        SUPPORT_E VARCHAR(10);
BEGIN
select SUPPORT into SUPPORT_E from CONFERENCES where id_sem = idInput;

IF (SUPPORT_E != 'TRUE')
THEN Raise_application_error('-20101', 'erreur le support nest pas fournis-');
END IF;
END;
/
show errors

--Annulation --------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE annul_eve
(
  idInput IN NUMBER,
  dateInput IN DATE
)   
IS 
   nb_dispo NUMBER(5);
   nb_reserve NUMBER(5);
BEGIN 
	nb_dispo := place_salle(idInput, dateInput);
	nb_reserve := place_reserve(idInput, dateInput); 
	
	IF ((nb_reserve / nb_dispo) <= 0.5) and (  dateInput - sysdate)  <=7  THEN
		DELETE reservations WHERE id_sem = idInput AND date_sem = dateInput;
		DELETE evenements WHERE id_sem = idInput AND date_sem = dateInput;
	END IF;
END; 
/
show errors

