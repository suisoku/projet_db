Create or replace trigger contrainte_6
after update on reservations
DECLARE
idpers NUMBER(5);
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
