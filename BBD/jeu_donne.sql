
/* TEST 1 , Ajouter un seminaire */
  Theme = "Sante"
Animateur id = 5
DEj O
Creneaux = "MATIN"
Act1 = "Le bio"
Act2 = "La permaculture"
Act3 = "Cuisine"

Resulat :
select * from seminaires;


/* TEst 2 ajouter un EVENEMENTS */
Seminaire = 12
date_evenemnet ="15/05/2018"
prest = 1
nom _salle ="PETITESALLE"
select * from evenements where id_sem = 12;




select * from evenements where id_sem = 2;

/* test confirmer un evenement 30J */
id_sem = 12
date_evenemnet ="15/05/2018"


/* test ajouter conference */

num sem = 12
num conf = 17
nom = Agriculture
montant = 50
support = O

select * form conferences where id_sem = 12;

/* TEST Confirmation 7 jours J */

idsem = 2
date_evenement = 30/06/2019

select * from evenements where id_sem = 2;


/*                     TEST ATTENTE       */
id client = 15
id _sem = 6
date even = 01/06/2019

select * from attentes;

/*  TEST annulation   */
select * from reservations where id_sem = 6;
select * from attentes;

id_client = 7
id _sem = 6
date even = 01/06/2019


/* hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh */

Select depense (1, '11-JUL-2019') from dual ;

Select recettes(1, '11-JUL-2019') from dual ;

Select benefice(1, '11-JUL-2019') from dual ;

Select tarif_sem(1, '11-JUL-2019') from dual ;
