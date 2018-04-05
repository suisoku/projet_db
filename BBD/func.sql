-- nbplacesalle () le nombre de place disponible dans une salle ---------------------------------------------
CREATE OR REPLACE FUNCTION place_salle
(
  idInput IN NUMBER,
  dateInput IN DATE
)  
RETURN
	NUMBER
IS 
   nb_salle NUMBER(5);
BEGIN 
  select nbplaces into nb_salle from evenements natural join salles where (id_sem = idInput AND date_sem = dateInput);
  RETURN (nb_salle);
  EXCEPTION
    WHEN OTHERS THEN
    RETURN 0; 
END; 
/
show errors
--Select place_salle(1, '11-JUL-2018') from dual ; ----------------------------------------------


--nbplacesReserve()---------------------------------------------
CREATE OR REPLACE FUNCTION place_reserve
(
  idInput IN NUMBER,
  dateInput IN DATE
)  
RETURN 
  NUMBER 
IS 
   
  nb_reserve NUMBER(5) := 0;
BEGIN 
  select count(*) into nb_reserve from reservations group by (id_sem, date_sem, statut) having (id_sem = idInput AND date_sem = dateInput and statut = 'CONFIRME'); 
  return (nb_reserve);
  EXCEPTION
    WHEN OTHERS THEN
    RETURN 0;
END; 
/
show errors
-- select count(*) from reservations group by (id_sem, date_sem, statut) having (id_sem = 9 AND date_sem = '02-10-2018' and statut = 'CONFIRME');
-- select place_reserve(9 ,'02-OCT-2018') from dual;
-- select place_salle(9 ,'02-OCT-2018') from dual;
-- select place_dispo(9 ,'02-OCT-2018') from dual;
-- g-----------------------------------------------------




--nbPlaceDispo() le nombre de place disponible par evenement (seminaires a une date donnée et  un lieu donnée) ----------
CREATE OR REPLACE FUNCTION place_dispo
(
  idInput IN NUMBER,
  dateInput IN DATE
)  
RETURN 
  NUMBER 
IS 
   nb_dispo NUMBER(5);
   nb_reserve NUMBER(5);
	nb_final NUMBER(5);
BEGIN 
	nb_dispo := place_salle(idInput, dateInput);
	nb_reserve := place_reserve(idInput, dateInput); 
	nb_final := nb_dispo - nb_reserve;

	RETURN (nb_final); 

EXCEPTION
    WHEN OTHERS THEN
    RETURN 0;
END;
/
show errors
--Select place_dispo(1, '11-JUL-2018') from dual -----------------------


 

-- execute annul_eve(2, '30-JUN-2018');
-- execute annul_eve(3, '30-JUN-2018'); --------------------------------------------------------------------





--depensesem -----------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION depense 
(
  idInput IN NUMBER,
  dateInput IN DATE
)  
RETURN
	NUMBER
IS
	 tarif_salle NUMBER(7,2)  :=0;
	 montant_c NUMBER (7,2) :=0;
	 prix_P NUMBER(7,2):=0;
	 prix_D NUMBER(7,2):=0;
	 dej VARCHAR(5);
	 nb_places NUMBER(5) := 0;

BEGIN
	nb_places := place_salle(idInput, dateInput);
	
	select tarif_s into tarif_salle from salles natural join evenements where ( id_sem = idInput AND date_sem = dateInput ); --taruf_salle
	select sum(montant) into montant_c from conferences where (id_sem = idInput ) ;  --montant conf

	select  tarif_p into prix_P from evenements natural join prestataires  where  (id_sem = idInput) ; -- pause
	select tarif_d into prix_D from evenements natural join prestataires where (id_sem = idInput) ; -- dejeuner

	select dejeuner into dej from seminaires natural join evenements  where ( id_sem = idInput AND date_sem = dateInput );

	IF ( dej is NULL OR dej = 'FALSE' ) THEN 
	 	prix_D :=0;
 	END IF; 

	RETURN ( tarif_salle +  ((prix_p + prix_D )* nb_places) + montant_c );
END;
/
show errors 


--Select depense (1, '11-JUL-2018') from dual ;-------------------------------------------------------------------------------------------------------------------------------------------



--Tarif seminaires ------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION tarif_sem  
(
  idInput IN NUMBER,
  dateInput IN DATE
)  
RETURN
	NUMBER
IS

	nb_places NUMBER(7,2) ;
	montant_D NUMBER(7,2) ; 

BEGIN

	nb_places := place_salle(idInput, dateInput);
	montant_D := depense(idInput, dateInput);
RETURN ( montant_D/( nb_places * 0.5) ) ;
END;
/
show errors 

--Select tarif_sem(1, '11-JUL-2018') from dual ;----------------------------------------------------------------------------------------------------------------



--recettes --------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION recettes   
(
  idInput IN NUMBER,
  dateInput IN DATE
)  
RETURN
	NUMBER
IS

	nb_reserve NUMBER(7,2) ;
	montant_sem NUMBER(7,2) ; 

BEGIN

	nb_reserve := place_reserve(idInput, dateInput); 
	montant_sem := tarif_sem(idInput, dateInput);
RETURN ( (montant_sem*nb_reserve) ) ;
END;
/
show errors 

--Select recettes(1, '11-JUL-2018') from dual ;-------------------------------------------------------------------------------------------------------------------------



-- bénéfice ----------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION benefice   
(
  idInput IN NUMBER,
  dateInput IN DATE
)  
RETURN
	NUMBER
IS

	 montant_R NUMBER(7,2) ;
	 montant_D NUMBER(7,2) ; 

BEGIN

	montant_R := recettes(idInput, dateInput); 
	montant_D := depense(idInput, dateInput);
RETURN ( montant_R-montant_D ) ;
END;
/
show errors 

--Select benefice(1, '11-JUL-2018') from dual ; -----------------------------------------------------------------------------------


