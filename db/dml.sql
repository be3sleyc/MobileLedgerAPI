DELIMITER $$
CREATE PROCEDURE sp_addaccount (
    IN pEmail VARCHAR(50), 
    IN pName VARCHAR(30), 
    IN pBalance DECIMAL(13,2),
    IN pNote VARCHAR(200))
BEGIN
IF(pName NOT IN (SELECT name FROM Accounts WHERE userid IN (SELECT id FROM Users WHERE email = pEmail))) THEN
    IF(pBalance IS NULL OR pBalance = '') THEN
        SET pBalance = 0.00;
    END IF;
    INSERT INTO Accounts (userid, name, balance, notes) VALUES ((SELECT id FROM Users WHERE email = pEmail), pName, pBalance, pNote);
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_adduser (
    IN pGivenName VARCHAR(30), 
    IN pSurName VARCHAR(30), 
    IN pEmail VARCHAR(50), 
    IN pPassword Binary(80))
BEGIN
DECLARE pUser INT DEFAULT NULL;
IF(pGivenName IS NOT NULL) THEN
    IF(pSurName IS NOT NULL) THEN
        IF(pEmail IS NOT NULL AND pEmail NOT IN (SELECT email FROM Users)) THEN
            IF(pPassword IS NOT NULL) THEN
                SET pUser := (SELECT id FROM Users WHERE email = pEmail);
                IF(pUser IS NULL) THEN
                    INSERT INTO Users (givenname, surname, email, password) VALUES (pGivenName, pSurName, pEmail, pPassword);
                    CALL sp_addaccount(pEmail, 'Cash', '', '');
                    SELECT givenname, surname, email FROM Users WHERE email = pEmail;
                END IF;
            END IF;
        END IF;
    END IF;
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_listaccounts (
    IN pEmail VARCHAR(50)
)
BEGIN
    SELECT name, balance, notes FROM Accounts WHERE userid in (SELECT id FROM Users WHERE email = pEmail);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_login (
    IN pEmail VARCHAR(50),
    IN pPassword BINARY(80))
BEGIN
    DECLARE pUser INT DEFAULT NULL;
    SELECT id INTO pUser FROM Users WHERE email = pEmail AND password = pPassword;
    IF(pUser IS NOT NULL) THEN
        SELECT email, givenname, surname, lastaccess FROM Users WHERE id = pUser;
        UPDATE Users SET lastaccess = NOW() WHERE id = pUser;
        CALL sp_listaccounts(pEmail);
    END IF;
END$$
DELIMITER ;