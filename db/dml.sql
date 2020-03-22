DELIMITER $$
CREATE PROCEDURE sp_addaccount (IN pUid INT UNSIGNED, IN pName VARCHAR(30), IN pBalance DECIMAL(13,2), IN pNote VARCHAR(512)) BEGIN IF(pName NOT IN (SELECT name FROM Accounts WHERE userid = pUid)) THEN IF(pBalance IS NULL OR pBalance = '') THEN SET pBalance = 0.00; END IF; INSERT INTO Accounts (userid, name, balance, notes) VALUES (pUid, pName, pBalance, pNote); END IF; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_adduser (IN pGivenName VARCHAR(30),  IN pSurName VARCHAR(30),  IN pEmail VARCHAR(50),  IN pPassword Binary(80)) BEGIN DECLARE pUser INT DEFAULT NULL; IF(pGivenName IS NOT NULL) THEN IF(pSurName IS NOT NULL) THEN IF(pEmail IS NOT NULL AND pEmail NOT IN (SELECT email FROM Users)) THEN IF(pPassword IS NOT NULL) THEN SET pUser := (SELECT id FROM Users WHERE email = pEmail); IF(pUser IS NULL) THEN INSERT INTO Users (givenname, surname, email, password) VALUES (pGivenName, pSurName, pEmail, pPassword); CALL sp_addaccount((SELECT id FROM Users WHERE email = pEmail), 'Cash', '', ''); SELECT id, givenname, surname, email FROM Users WHERE email = pEmail; END IF; END IF; END IF; END IF; END IF; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_login (IN pEmail VARCHAR(50), IN pPassword BINARY(80)) BEGIN DECLARE pUser INT DEFAULT NULL; SELECT id INTO pUser FROM Users WHERE email = pEmail AND password = pPassword; IF(pUser IS NOT NULL) THEN UPDATE Users SET lastaccess = NOW() WHERE id = pUser; SELECT id, email, givenname, surname, lastaccess FROM Users WHERE id = pUser; END IF; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_getUserID (IN pid INT UNSIGNED) BEGIN SELECT id, givenname, surname, email FROM Users WHERE id = pid; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_getUserEmail (IN pEmail VARCHAR(50)) BEGIN SELECT id, givenname, surname, email, password FROM Users WHERE email = pemail; END$$ 
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_getAccounts (IN pid INT UNSIGNED) BEGIN SELECT id, name, balance, notes FROM Accounts WHERE userid = pid; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_getAccount (IN pUid INT UNSIGNED,  IN pId INT UNSIGNED) BEGIN SELECT id, name, balance, notes FROM Accounts WHERE userid = pUid and id = pId; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_editaccount (IN pid INT UNSIGNED, IN puid INT UNSIGNED, IN pname VARCHAR(30), IN pnotes VARCHAR(512)) BEGIN IF(pname != '') THEN IF(pnotes IS NOT NULL) THEN UPDATE Accounts SET name = pname, notes = pnotes WHERE id = pid and userid = puid; ELSE UPDATE Accounts SET name = pname WHERE id = pid and userid = puid; END IF; ELSEIF(pnotes IS NOT NULL) THEN UPDATE Accounts SET  notes = pnotes WHERE id = pid and userid = puid; END IF; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_logout (IN ptoken VARBINARY(300), IN puid INT UNSIGNED, IN pexp DATETIME ) BEGIN INSERT INTO Bokens VALUES (ptoken, puid, pexp); END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_lookupToken (IN ptoken VARBINARY(300) ) BEGIN SELECT * FROM Bokens WHERE token = ptoken; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_expireTokens () BEGIN DELETE FROM Bokens WHERE expdate < NOW(); END$$ DELIMITER ;
