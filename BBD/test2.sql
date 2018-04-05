CREATE OR REPLACE PROCEDURE switch_attente
(
 idpers NUMBER, 
 idsem NUMBER,
 datesem DATE
)   
IS
BEGIN 
	UPDATE reservations SET statut = 'CONFIRME' WHERE id_sem = idsem AND date_sem = datesem AND id_pers = idpers;
	DELETE attentes WHERE id_sem = idsem AND date_sem = datesem  AND id_pers = idpers;
END; 
/
show errors 

-- execute switch_attente(7,6,'01-JUN-2018');
