-- RACUN PODACI

INSERT INTO "racun"("id", "datum", "nacin_placanja")
VALUES (nextval('racun_seq'), to_date('01.02.2021.', 'dd.mm.yyyy.'), 'kartica');
INSERT INTO "racun"("id", "datum", "nacin_placanja")
VALUES (nextval('racun_seq'), to_date('07.03.2021.', 'dd.mm.yyyy.'), 'gotovina');
INSERT INTO "racun"("id", "datum", "nacin_placanja")
VALUES (nextval('racun_seq'), to_date('10.08.2020.', 'dd.mm.yyyy.'), 'gotovina');
INSERT INTO "racun"("id", "datum", "nacin_placanja")
VALUES (nextval('racun_seq'), to_date('28.12.2020.', 'dd.mm.yyyy.'), 'gotovina');
INSERT INTO "racun"("id", "datum", "nacin_placanja")
VALUES (nextval('racun_seq'), to_date('12.01.2021.', 'dd.mm.yyyy.'), 'kartica');
INSERT INTO "racun"("id", "datum", "nacin_placanja")
VALUES (nextval('racun_seq'), to_date('18.07.2020.', 'dd.mm.yyyy.'), 'gotovina');

-- PROIZVODJAC PODACI

INSERT INTO "proizvodjac"("id", "naziv", "adresa", "kontakt")
VALUES (nextval('proizvodjac_seq'), 'Swisslion d.o.o', 'Vaska Pope 4, Beograd', '+381112069300');
INSERT INTO "proizvodjac"("id", "naziv", "adresa", "kontakt")
VALUES (nextval('proizvodjac_seq'), 'Jadar pak d.o.o', 'Karadjordjeva 127, Osecina', '+38114451623');
INSERT INTO "proizvodjac"("id", "naziv", "adresa", "kontakt")
VALUES (nextval('proizvodjac_seq'), 'DANUBIUS d.o.o', 'Kanalska 1, Novi Sad', '+381214808900');
INSERT INTO "proizvodjac"("id", "naziv", "adresa", "kontakt")
VALUES (nextval('proizvodjac_seq'), 'Neoplanta DOO Novi Sad', 'Primorska 90, Novi Sad', '+381214873882');

-- PROIZVOD PODACI

INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'SL KAKAO KREM 200G', 1);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'YOYO COKOLADA SA RIZOM 150G', 1);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'EUROCREM FAMILLY 500G', 1);

INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'WELTON Jabuka sa cimetom', 2);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'WELTON Aronija', 2);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'WELTON Sumsko voce', 2);

INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'PSENICNO BELO BRASNO TIP 500 25KG', 3);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'CIPKASTI REZANan 400g', 3);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'MAKARONA KOSO SECENA 400g', 3);

INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'Patelina tuna 60g', 4);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'Dimljena pecenica', 4);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'Kulen', 4);

-- STAVKA RACUNA PODACI

INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 1, 2, 'komad', 140, 1, 1);
INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 2, 1, 'komad', 100, 1, 2);
INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 3, 1, 'komad', 300, 1, 3);

INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 1, 2, 'komad', 100, 2, 4);
INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 2, 1, 'komad', 100, 2, 5);
INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 3, 1, 'komad', 100, 2, 6);

INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 1, 1, 'komad', 800, 3, 7);
INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 2, 4, 'komad', 240, 3, 8);
INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 3, 4, 'komad', 160, 3, 9);

INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 1, 6, 'komad', 300, 4, 10);
INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 2, 1, 'kg', 650, 4, 11);
INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 3, 1, 'komad', 800, 4, 12);

INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 1, 3, 'komad', 210, 5, 1);
INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 2, 1, 'komad', 100, 5, 2);

INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 1, 1, 'komad', 100, 6, 4);
INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 2, 1, 'komad', 100, 6, 5);

-- Test podaci
INSERT INTO "racun"("id", "datum", "nacin_placanja")
VALUES (-100, to_date('01.01.2021.', 'dd.mm.yyyy.'), 'TestPlacanje');
INSERT INTO "proizvodjac"("id", "naziv", "adresa", "kontakt")
VALUES (-100, 'TestNaziv', 'TestAdresa', '+38121111111');
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (-100, 'TestNaziv', 1);
INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (-100, 1, 1, 'komad', 100, 1, 1);
INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (20, 1, 1, 'komad', 100, -100, 1);