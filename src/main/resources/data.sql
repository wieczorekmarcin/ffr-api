INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, PARSEDATETIME('01-01-2016', 'dd-MM-yyyy'));
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, USER_TYPE, ENABLED, LASTPASSWORDRESETDATE) VALUES (2, 'wisnia@rentier.pl', '$2a$10$dNIq6GhfTDLT8lhHadPgs.ftHCjz4EJuxa2hjOqO0/KQFuxQ4L29u', 'Adrian', 'Wiśniewski', 'wisnia@rentier.pl',  0, 1, PARSEDATETIME('01-01-2018','dd-MM-yyyy'));
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, USER_TYPE, ENABLED, LASTPASSWORDRESETDATE) VALUES (3, 'wisnia@tenat.pl', '$2a$10$dNIq6GhfTDLT8lhHadPgs.ftHCjz4EJuxa2hjOqO0/KQFuxQ4L29u', 'Adrian', 'Wiśniewski', 'wisnia@tenat.pl',  1, 1, PARSEDATETIME('01-01-2018','dd-MM-yyyy'));
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, USER_TYPE, ENABLED, LASTPASSWORDRESETDATE) VALUES (4, 'disabled', '$2a$10$sQbcsAN2Dp0SnAsa5uSC1ePytlV89srH7TbgBZly1kXwXICG9NBIu', 'user', 'user', 'disabled@user.com', 1,0, PARSEDATETIME('01-01-2018','dd-MM-yyyy'));

INSERT INTO AUTHORITY (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (ID, NAME) VALUES (2, 'ROLE_ADMIN');


INSERT INTO IMAGE (ID, URL) VALUES (1, 'https://www.barrespropertytrading.com/images/proyectos-ini2.jpg');
INSERT INTO IMAGE (ID, URL) VALUES (2, 'https://s3-us-west-2.amazonaws.com/g5-orion-clients/g5-c-1te982x2-place-properties/g5-cl-5527vd9fb-place-properties/uploads/place-properties-hero.jpg');
INSERT INTO IMAGE (ID, URL) VALUES (3, 'https://im.proptiger.com/1/667963/6/khain-properties-nilaya-premium-elevation-627779.jpeg');

INSERT INTO TEST (ID, VALUE) VALUES (1, 'asddsadasda');
INSERT INTO TEST (ID, VALUE) VALUES (2, 'dsdsadsa');


/*INSERT INTO FLAT_IMAGE (FLAT_ID, IMAGE_ID) VALUES (1, 1);
INSERT INTO FLAT_IMAGE (FLAT_ID, IMAGE_ID) VALUES (1, 2);
INSERT INTO FLAT_IMAGE (FLAT_ID, IMAGE_ID) VALUES (1, 3);
INSERT INTO FLAT_IMAGE (FLAT_ID, IMAGE_ID) VALUES (2, 1);
INSERT INTO FLAT_IMAGE (FLAT_ID, IMAGE_ID) VALUES (2, 2);
INSERT INTO FLAT_IMAGE (FLAT_ID, IMAGE_ID) VALUES (2, 3);
INSERT INTO FLAT_IMAGE (FLAT_ID, IMAGE_ID) VALUES (3, 1);
INSERT INTO FLAT_IMAGE (FLAT_ID, IMAGE_ID) VALUES (3, 2);
INSERT INTO FLAT_IMAGE (FLAT_ID, IMAGE_ID) VALUES (3, 3);
INSERT INTO FLAT_IMAGE (FLAT_ID, IMAGE_ID) VALUES (4, 1);
INSERT INTO FLAT_IMAGE (FLAT_ID, IMAGE_ID) VALUES (4, 2);
INSERT INTO FLAT_IMAGE (FLAT_ID, IMAGE_ID) VALUES (4, 3);
INSERT INTO FLAT_IMAGE (FLAT_ID, IMAGE_ID) VALUES (5, 1);
INSERT INTO FLAT_IMAGE (FLAT_ID, IMAGE_ID) VALUES (5, 2);
INSERT INTO FLAT_IMAGE (FLAT_ID, IMAGE_ID) VALUES (5, 3);
INSERT INTO FLAT_IMAGE (FLAT_ID, IMAGE_ID) VALUES (6, 1);
INSERT INTO FLAT_IMAGE (FLAT_ID, IMAGE_ID) VALUES (6, 2);
INSERT INTO FLAT_IMAGE (FLAT_ID, IMAGE_ID) VALUES (6, 3);
*/

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (2, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (3, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (4, 1);

INSERT INTO OFFER (ID, TITLE, SHORT_DESCRIPTION, DESCRIPTION) VALUES (1, 'Testowy tytuł 1', 'testowy krótki opis 1', 'testowy długi opis 1');
INSERT INTO OFFER (ID, TITLE, SHORT_DESCRIPTION, DESCRIPTION) VALUES (2, 'Testowy tytuł 2', 'testowy krótki opis 2', 'testowy długi opis 1');

/*INSERT INTO OFFER_FLAT (OFFER_ID, FLAT_ID) VALUES (1, 1);
INSERT INTO OFFER_FLAT (OFFER_ID, FLAT_ID) VALUES (2, 2);*/