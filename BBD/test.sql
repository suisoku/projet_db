Create or replace trigger trigger_clos
after update on evenements
for each row
DECLARE
   nb_dispo NUMBER(5);
   nb_reserve NUMBER(5);
BEGIN
	IF (:NEW.statut = 'CLOS_30J') THEN
		p_creneaux(:NEW.id_sem);
		p_activite(:NEW.id_sem);
		p_conference(:NEW.id_sem);
	END IF;
	
	IF (:NEW.statut = 'CLOS_7J') THEN
		p_support(:NEW.id_sem);
	END IF;
END;
/
show errors
-- UPDATE evenements SET statut ='CLOS_7J' WHERE id_sem =2 AND date_sem='30-JUN-19';
-- anu

