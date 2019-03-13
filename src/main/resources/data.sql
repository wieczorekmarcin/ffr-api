INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, PARSEDATETIME('01-01-2016', 'dd-MM-yyyy'));
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, USER_TYPE, ENABLED, LASTPASSWORDRESETDATE) VALUES (2, 'wisnia@rentier.pl', '$2a$10$dNIq6GhfTDLT8lhHadPgs.ftHCjz4EJuxa2hjOqO0/KQFuxQ4L29u', 'Adrian', 'Wiśniewski', 'wisnia@rentier.pl',  0, 1, PARSEDATETIME('01-01-2018','dd-MM-yyyy'));
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, USER_TYPE, ENABLED, LASTPASSWORDRESETDATE) VALUES (3, 'wisnia@tenat.pl', '$2a$10$dNIq6GhfTDLT8lhHadPgs.ftHCjz4EJuxa2hjOqO0/KQFuxQ4L29u', 'Adrian', 'Wiśniewski', 'wisnia@tenat.pl',  1, 1, PARSEDATETIME('01-01-2018','dd-MM-yyyy'));
INSERT INTO USER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, USER_TYPE, ENABLED, LASTPASSWORDRESETDATE) VALUES (4, 'disabled', '$2a$10$sQbcsAN2Dp0SnAsa5uSC1ePytlV89srH7TbgBZly1kXwXICG9NBIu', 'user', 'user', 'disabled@user.com', 1,0, PARSEDATETIME('01-01-2018','dd-MM-yyyy'));

INSERT INTO AUTHORITY (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO AUTHORITY (ID, NAME) VALUES (2, 'ROLE_ADMIN');

INSERT INTO PROPERTY (ID, STREET, STREET_NUMBER, CITY, YARDAGE) VALUES (1, 'ul. Kwiatowa', '32', 'Poznań', '502m2');
INSERT INTO PROPERTY (ID, STREET, STREET_NUMBER, CITY, YARDAGE) VALUES (2, 'ul. Testowa', '18', 'Luboń', '612m2');

INSERT INTO FLAT (ID, FLAT_NUMBER, IMAGES, PRICE_PER_YARD, TITLE, DESCRIPTION, FOR_RENT) VALUES (1, '13', 'https://www.barrespropertytrading.com/images/proyectos-ini2.jpg, https://s3-us-west-2.amazonaws.com/g5-orion-clients/g5-c-1te982x2-place-properties/g5-cl-5527vd9fb-place-properties/uploads/place-properties-hero.jpg, https://im.proptiger.com/1/667963/6/khain-properties-nilaya-premium-elevation-627779.jpeg', '9000', 'testowy tytuł', 'testowy opis', true );
INSERT INTO FLAT (ID, FLAT_NUMBER, IMAGES, PRICE_PER_YARD, TITLE, DESCRIPTION, FOR_RENT) VALUES (2, '14', 'https://www.barrespropertytrading.com/images/proyectos-ini2.jpg, https://s3-us-west-2.amazonaws.com/g5-orion-clients/g5-c-1te982x2-place-properties/g5-cl-5527vd9fb-place-properties/uploads/place-properties-hero.jpg, https://im.proptiger.com/1/667963/6/khain-properties-nilaya-premium-elevation-627779.jpeg', '9000', 'testowy tytuł', 'testowy opis', true );
INSERT INTO FLAT (ID, FLAT_NUMBER, IMAGES, PRICE_PER_YARD, TITLE, DESCRIPTION, FOR_RENT) VALUES (3, '15', 'https://www.barrespropertytrading.com/images/proyectos-ini2.jpg, https://s3-us-west-2.amazonaws.com/g5-orion-clients/g5-c-1te982x2-place-properties/g5-cl-5527vd9fb-place-properties/uploads/place-properties-hero.jpg, https://im.proptiger.com/1/667963/6/khain-properties-nilaya-premium-elevation-627779.jpeg', '9000', 'testowy tytuł', 'testowy opis', true );
INSERT INTO FLAT (ID, FLAT_NUMBER, IMAGES, PRICE_PER_YARD, TITLE, DESCRIPTION, FOR_RENT) VALUES (4, '4', 'https://www.barrespropertytrading.com/images/proyectos-ini2.jpg, https://s3-us-west-2.amazonaws.com/g5-orion-clients/g5-c-1te982x2-place-properties/g5-cl-5527vd9fb-place-properties/uploads/place-properties-hero.jpg, https://im.proptiger.com/1/667963/6/khain-properties-nilaya-premium-elevation-627779.jpeg', '9000', 'testowy tytuł', 'testowy opis', true );
INSERT INTO FLAT (ID, FLAT_NUMBER, IMAGES, PRICE_PER_YARD, TITLE, DESCRIPTION, FOR_RENT) VALUES (5, '5', 'https://www.barrespropertytrading.com/images/proyectos-ini2.jpg, https://s3-us-west-2.amazonaws.com/g5-orion-clients/g5-c-1te982x2-place-properties/g5-cl-5527vd9fb-place-properties/uploads/place-properties-hero.jpg, https://im.proptiger.com/1/667963/6/khain-properties-nilaya-premium-elevation-627779.jpeg', '9000', 'testowy tytuł', 'testowy opis', true );
INSERT INTO FLAT (ID, FLAT_NUMBER, IMAGES, PRICE_PER_YARD, TITLE, DESCRIPTION, FOR_RENT) VALUES (6, '6', 'https://www.barrespropertytrading.com/images/proyectos-ini2.jpg, https://s3-us-west-2.amazonaws.com/g5-orion-clients/g5-c-1te982x2-place-properties/g5-cl-5527vd9fb-place-properties/uploads/place-properties-hero.jpg, https://im.proptiger.com/1/667963/6/khain-properties-nilaya-premium-elevation-627779.jpeg', '9000', 'testowy tytuł', 'testowy opis', true );


INSERT INTO PROPERTY_FLAT (PROPERTY_ID, FLAT_ID) VALUES (1, 1);
INSERT INTO PROPERTY_FLAT (PROPERTY_ID, FLAT_ID) VALUES (1, 2);
INSERT INTO PROPERTY_FLAT (PROPERTY_ID, FLAT_ID) VALUES (1, 3);
INSERT INTO PROPERTY_FLAT (PROPERTY_ID, FLAT_ID) VALUES (2, 4);
INSERT INTO PROPERTY_FLAT (PROPERTY_ID, FLAT_ID) VALUES (2, 5);
INSERT INTO PROPERTY_FLAT (PROPERTY_ID, FLAT_ID) VALUES (2, 6);

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (2, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (3, 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (4, 1);
