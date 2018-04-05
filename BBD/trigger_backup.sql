Create or replace trigger changedate
Before insert on LesRepresentations
FOR EACH ROW
Begin
	If sysdate > :new.dateRep
		Then Raise_application_error('-20101', 'Data insere inf');
	End if;
End;
/

/*
Create or replace trigger sommetest
before update or insert on LESRESERVATIONS
FOR EACH ROW
DECLARE
	sommeP NUMBER(10);
	nbPlaceMax NUMBER(10);
BEGIN

SELECT SUM(nbplaces)  INTO sommeP 
FROM LesReservations WHERE numS = :new.numS AND dateRep = :new.dateRep;

SELECT nbplacespossibles INTO nbPlaceMax 
FROM LesRepresentations WHERE numS = :new.numS AND  dateRep = :new.dateRep; 

if nbPlaceMax - sommeP <  :new.nbplaces
THEN Raise_application_error('-20101', 'Somme des places réservées < disponibles');
END IF;

END;
/

Create or replace trigger inclusion
BEFORE insert on LESSPECTACLES
FOR EACH ROW
DECLARE
	countNums;
Begin
	select count(numS) into countNums from LESSPECTACLES where numS = :new.numS
	not in (select numS from LESREPRESENTATIONS);

	if countNums > 0
	then Raise_application_error('-20101', 'erreur inclusion'); 
	end if;
End;
/
*/

Create or replace trigger constatSomme
after update or insert on LESRESERVATIONS
DECLARE
	countFire NUMBER(10);
BEGIN
select count(*) into countFire from (
	select  nums from LESreservations natural join 		LESrepresentations 
	group by numS, daterep, nbplacespossibles having sum(nbplaces) > nbplacespossibles);


if countFire > 0
THEN Raise_application_error('-20101', 'Somme des places réservées < disponibles');
END IF;

END;
/


Create or replace trigger constatInc
after update or insert on LESRESERVATIONS
DECLARE
	countFire NUMBER(10);
BEGIN

select count(*)  into countFire from LesRepresentations
where ( nums, daterep) not in
(select nums , daterep from LESreservations); 


if countFire > 0
THEN Raise_application_error('-20101', 'erreur inclusion representation spectacle');
END IF;

END;
/

Create or replace trigger constatInc2
after update or insert on LESSPECTACLES
DECLARE
	countFire NUMBER(10);
BEGIN

select count(*)  into countFire from LESSPECTACLES
where ( nums) not in
(select nums from LESrepresentations); 


if countFire > 0
THEN Raise_application_error('-20101', 'erreur inclusion representation spectacle');
END IF;

END;
/
////////////////////////////


