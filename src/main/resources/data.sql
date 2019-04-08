INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, PARSEDATETIME('01-01-2016', 'dd-MM-yyyy'));
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, USER_TYPE, ENABLED, LASTPASSWORDRESETDATE) VALUES (2, 'wisnia@rentier.pl', '$2a$10$dNIq6GhfTDLT8lhHadPgs.ftHCjz4EJuxa2hjOqO0/KQFuxQ4L29u', 'Adrian', 'Wiśniewski', 'wisnia@rentier.pl',  0, 1, PARSEDATETIME('01-01-2018','dd-MM-yyyy'));
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, USER_TYPE, ENABLED, LASTPASSWORDRESETDATE) VALUES (3, 'wisnia@tenat.pl', '$2a$10$dNIq6GhfTDLT8lhHadPgs.ftHCjz4EJuxa2hjOqO0/KQFuxQ4L29u', 'Adrian', 'Wiśniewski', 'wisnia@tenat.pl',  1, 1, PARSEDATETIME('01-01-2018','dd-MM-yyyy'));
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, USER_TYPE, ENABLED, LASTPASSWORDRESETDATE) VALUES (4, 'disabled', '$2a$10$sQbcsAN2Dp0SnAsa5uSC1ePytlV89srH7TbgBZly1kXwXICG9NBIu', 'user', 'user', 'disabled@user.com', 1,0, PARSEDATETIME('01-01-2018','dd-MM-yyyy'));

INSERT INTO AUTHORITY (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (ID, NAME) VALUES (2, 'ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (2, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (3, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (4, 1);

INSERT INTO FLAT(AVAILABLE_FROM,BAIL,BUILDING_MATERIAL,BUILDING_TYPE,CITY,DESCRIPTION,FLAT_STATUS,FLOOR,FLOORS_NUMBER,HEATING,POST_CODE,PRICE,ROOMS_NUMBER,STREET,SURFACE,TITLE,WINDOWS)
VALUES(GETDATE(),'900zł','cegła','szeregowiec','Poznań','opis','FOR_RENT','n/d','2','ogrzewanie jakieś tam','61-118','2100zł','4','ul. Kwiatowa 2','115m2','test tytuł','9');

INSERT INTO FLAT(AVAILABLE_FROM,BAIL,BUILDING_MATERIAL,BUILDING_TYPE,CITY,DESCRIPTION,FLAT_STATUS,FLOOR,FLOORS_NUMBER,HEATING,POST_CODE,PRICE,ROOMS_NUMBER,STREET,SURFACE,TITLE,WINDOWS)
VALUES(GETDATE(),'1100zł','cegła','mieszkanie','Poznań','opis','ACTIVE','n/d','1','ogrzewanie jakieś tam2 ','61-118','3200zł','2','ul. Testowa 8','75m2','test tytuł 2','5');

INSERT INTO FLAT(AVAILABLE_FROM,BAIL,BUILDING_MATERIAL,BUILDING_TYPE,CITY,DESCRIPTION,FLAT_STATUS,FLOOR,FLOORS_NUMBER,HEATING,POST_CODE,PRICE,ROOMS_NUMBER,STREET,SURFACE,TITLE,WINDOWS)
VALUES(GETDATE(),'500zł','cegła','mieszkanie','Poznań','opis','RENTED','n/d','1','ogrzewanie jakieś tam','61-118','1,550zł','1','ul. Łąkowa 1','49m2','test tytuł ','4');

INSERT INTO FLAT(AVAILABLE_FROM,BAIL,BUILDING_MATERIAL,BUILDING_TYPE,CITY,DESCRIPTION,FLAT_STATUS,FLOOR,FLOORS_NUMBER,HEATING,POST_CODE,PRICE,ROOMS_NUMBER,STREET,SURFACE,TITLE,WINDOWS)
VALUES(GETDATE(),'2500zł','cegła','dom','Poznań','opis','INACTIVE','n/d','3','ogrzewanie jakieś tam','61-118','3500zł','5','ul. Osiedlowa 15','164m2','test tytuł','13');

INSERT INTO FLAT(AVAILABLE_FROM,BAIL,BUILDING_MATERIAL,BUILDING_TYPE,CITY,DESCRIPTION,FLAT_STATUS,FLOOR,FLOORS_NUMBER,HEATING,POST_CODE,PRICE,ROOMS_NUMBER,STREET,SURFACE,TITLE,WINDOWS)
VALUES(GETDATE(),'900zł','cegła','szeregowiec','Poznań','opis','FOR_RENT','n/d','2','ogrzewanie jakieś tam','61-118','2100zł','4','ul. Kwiatowa 2','115m2','test tytuł','9');

INSERT INTO FLAT(AVAILABLE_FROM,BAIL,BUILDING_MATERIAL,BUILDING_TYPE,CITY,DESCRIPTION,FLAT_STATUS,FLOOR,FLOORS_NUMBER,HEATING,POST_CODE,PRICE,ROOMS_NUMBER,STREET,SURFACE,TITLE,WINDOWS)
VALUES(GETDATE(),'1100zł','cegła','mieszkanie','Poznań','opis','ACTIVE','n/d','1','ogrzewanie jakieś tam2 ','61-118','3200zł','2','ul. Testowa 8','75m2','test tytuł 2','5');

INSERT INTO FLAT(AVAILABLE_FROM,BAIL,BUILDING_MATERIAL,BUILDING_TYPE,CITY,DESCRIPTION,FLAT_STATUS,FLOOR,FLOORS_NUMBER,HEATING,POST_CODE,PRICE,ROOMS_NUMBER,STREET,SURFACE,TITLE,WINDOWS)
VALUES(GETDATE(),'500zł','cegła','mieszkanie','Poznań','opis','RENTED','n/d','1','ogrzewanie jakieś tam','61-118','1,550zł','1','ul. Łąkowa 1','49m2','test tytuł ','4');

INSERT INTO FLAT(AVAILABLE_FROM,BAIL,BUILDING_MATERIAL,BUILDING_TYPE,CITY,DESCRIPTION,FLAT_STATUS,FLOOR,FLOORS_NUMBER,HEATING,POST_CODE,PRICE,ROOMS_NUMBER,STREET,SURFACE,TITLE,WINDOWS)
VALUES(GETDATE(),'2500zł','cegła','dom','Poznań','opis','INACTIVE','n/d','3','ogrzewanie jakieś tam','61-118','3500zł','5','ul. Osiedlowa 15','164m2','test tytuł','13');

INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(1, 'meble');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(1, 'pralka');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(1, 'zmywarka');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(1, 'lodówka');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(1, 'kuchenka');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(1, 'telewizor');

INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(2, 'meble');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(2, 'pralka');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(2, 'telewizor');

INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(3, 'meble');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(3, 'pralka');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(3, 'zmywarka');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(3, 'lodówka');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(3, 'kuchenka');

INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(4, 'meble');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(4, 'pralka');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(4, 'zmywarka');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(4, 'lodówka');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(4, 'kuchenka');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(4, 'telewizor');

INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(5, 'lodówka');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(5, 'kuchenka');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(5, 'telewizor');

INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(6, 'meble');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(6, 'pralka');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(6, 'zmywarka');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(6, 'lodówka');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(6, 'kuchenka');
INSERT INTO FLAT_EQUIPMENT(FLAT_ID, EQUIPMENT) VALUES(6, 'telewizor');

INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(1, 'rolety antywłamaniowe');
INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(1, 'drzwi / okna antywłamaniowe');
INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(1, 'domofon / wideofon');
INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(1, 'monitoring / ochrona');
INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(1, 'system alarmowy');
INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(1, 'teren zamknięty');

INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(2, 'rolety antywłamaniowe');
INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(2, 'drzwi / okna antywłamaniowe');
INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(2, 'system alarmowy');
INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(2, 'teren zamknięty');

INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(3, 'teren zamknięty');

INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(4, 'domofon / wideofon');
INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(4, 'monitoring / ochrona');
INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(4, 'system alarmowy');
INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(4, 'teren zamknięty');

INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(5, 'rolety antywłamaniowe');
INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(5, 'teren zamknięty');

INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(6, 'rolety antywłamaniowe');
INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(6, 'drzwi / okna antywłamaniowe');
INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(6, 'domofon / wideofon');
INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(6, 'monitoring / ochrona');
INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(6, 'system alarmowy');
INSERT INTO FLAT_SECURITY(FLAT_ID, SECURITY) VALUES(6, 'teren zamknięty');

INSERT INTO FLAT_MEDIA(FLAT_ID, MEDIA) VALUES(1, 'internet');
INSERT INTO FLAT_MEDIA(FLAT_ID, MEDIA) VALUES(1, 'telewizja kablowa');
INSERT INTO FLAT_MEDIA(FLAT_ID, MEDIA) VALUES(1, 'telefon');

INSERT INTO FLAT_MEDIA(FLAT_ID, MEDIA) VALUES(2, 'internet');
INSERT INTO FLAT_MEDIA(FLAT_ID, MEDIA) VALUES(2, 'telefon');

INSERT INTO FLAT_MEDIA(FLAT_ID, MEDIA) VALUES(3, 'internet');
INSERT INTO FLAT_MEDIA(FLAT_ID, MEDIA) VALUES(3, 'telewizja kablowa');
INSERT INTO FLAT_MEDIA(FLAT_ID, MEDIA) VALUES(3, 'telefon');

INSERT INTO FLAT_MEDIA(FLAT_ID, MEDIA) VALUES(4, 'internet');

INSERT INTO FLAT_MEDIA(FLAT_ID, MEDIA) VALUES(5, 'telewizja kablowa');

INSERT INTO FLAT_MEDIA(FLAT_ID, MEDIA) VALUES(6, 'internet');
INSERT INTO FLAT_MEDIA(FLAT_ID, MEDIA) VALUES(6, 'telewizja kablowa');
INSERT INTO FLAT_MEDIA(FLAT_ID, MEDIA) VALUES(6, 'telefon');

INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(1, 'balkon');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(1, 'pom. użytkowe');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(1, 'garaż/miejsce parkingowe');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(1, 'piwnica');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(1, 'taras');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(1, 'winda');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(1, 'dwupoziomowe');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(1, 'oddzielna kuchnia');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(1, 'klimatyzacja');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(1, 'tylko dla niepalących');

INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(2, 'balkon');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(2, 'pom. użytkowe');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(2, 'dwupoziomowe');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(2, 'oddzielna kuchnia');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(2, 'klimatyzacja');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(2, 'tylko dla niepalących');

INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(3, 'balkon');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(3, 'tylko dla niepalących');

INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(4, 'winda');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(4, 'dwupoziomowe');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(4, 'oddzielna kuchnia');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(4, 'klimatyzacja');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(4, 'tylko dla niepalących');

INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(5, 'balkon');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(5, 'pom. użytkowe');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(5, 'tylko dla niepalących');

INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(6, 'balkon');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(6, 'pom. użytkowe');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(6, 'garaż/miejsce parkingowe');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(6, 'piwnica');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(6, 'taras');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(6, 'winda');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(6, 'dwupoziomowe');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(6, 'oddzielna kuchnia');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(6, 'klimatyzacja');
INSERT INTO FLAT_ADDITIONAL_INFORMATION(FLAT_ID, ADDITIONAL_INFORMATION) VALUES(6, 'tylko dla niepalących');

INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(1, 'https://picsum.photos/500/300/?image=308');
INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(1, 'https://picsum.photos/500/300/?image=308');
INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(1, 'https://picsum.photos/500/300/?image=308');
INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(1, 'https://picsum.photos/500/300/?image=308');

INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(2, 'https://picsum.photos/500/300/?image=308');
INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(2, 'https://picsum.photos/500/300/?image=308');
INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(2, 'https://picsum.photos/500/300/?image=308');
INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(2, 'https://picsum.photos/500/300/?image=308');

INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(3, 'https://picsum.photos/500/300/?image=308');
INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(3, 'https://picsum.photos/500/300/?image=308');
INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(3, 'https://picsum.photos/500/300/?image=308');
INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(3, 'https://picsum.photos/500/300/?image=308');

INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(4, 'https://picsum.photos/500/300/?image=308');
INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(4, 'https://picsum.photos/500/300/?image=308');
INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(4, 'https://picsum.photos/500/300/?image=308');
INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(4, 'https://picsum.photos/500/300/?image=308');

INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(5, 'https://picsum.photos/500/300/?image=308');
INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(5, 'https://picsum.photos/500/300/?image=308');
INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(5, 'https://picsum.photos/500/300/?image=308');
INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(5, 'https://picsum.photos/500/300/?image=308');

INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(6, 'https://picsum.photos/500/300/?image=308');
INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(6, 'https://picsum.photos/500/300/?image=308');
INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(6, 'https://picsum.photos/500/300/?image=308');
INSERT INTO FLAT_IMAGES_URLS(FLAT_ID, IMAGES_URLS) VALUES(6, 'https://picsum.photos/500/300/?image=308');