DELIMITER $$
CREATE PROCEDURE sp_addaccount (IN pUid INT UNSIGNED, IN pName VARCHAR(30), IN pBalance DECIMAL(13,2), IN pNote VARCHAR(512)) BEGIN IF(pName NOT IN (SELECT name FROM Accounts WHERE userid = puid);) THEN IF(pBalance IS NULL OR pBalance = '') THEN SET pBalance = 0.00; END IF; INSERT INTO Accounts (userid, name, balance, notes) VALUES (pUid, pName, pBalance, pNote); END IF; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_getaccounts (IN pid INT UNSIGNED) BEGIN SELECT id, name, balance, notes FROM Accounts WHERE userid = pid; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_getaccount (IN pUid INT UNSIGNED,  IN pId INT UNSIGNED) BEGIN SELECT id, name, balance, notes FROM Accounts WHERE userid = pUid AND id = pId; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_editaccount (IN pid INT UNSIGNED, IN puid INT UNSIGNED, IN pname VARCHAR(30), IN pnotes VARCHAR(512)) BEGIN IF(pname != '') THEN IF(pnotes IS NOT NULL) THEN UPDATE Accounts SET name = pname, notes = pnotes WHERE id = pid AND userid = puid; ELSE UPDATE Accounts SET name = pname WHERE id = pid AND userid = puid; END IF; ELSEIF(pnotes IS NOT NULL) THEN UPDATE Accounts SET  notes = pnotes WHERE id = pid AND userid = puid; END IF; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_adduser (IN pGivenName VARCHAR(30),  IN pSurName VARCHAR(30),  IN pEmail VARCHAR(50),  IN pPassword Binary(80)) BEGIN IF(pEmail NOT IN (SELECT email FROM Users)) THEN INSERT INTO Users (givenname, surname, email, password) VALUES (pGivenName, pSurName, pEmail, pPassword); CALL sp_addaccount((SELECT id FROM Users WHERE email = pEmail), 'Cash', 0, ''); END IF; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_login (IN pEmail VARCHAR(50), IN pPassword BINARY(80)) BEGIN DECLARE pUser INT DEFAULT NULL; SELECT id INTO pUser FROM Users WHERE email = pEmail AND password = pPassword; IF(pUser IS NOT NULL) THEN UPDATE Users SET lastaccess = NOW() WHERE id = pUser; SELECT id, email, givenname, surname, lastaccess FROM Users WHERE id = pUser; END IF; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_getuserid (IN pid INT UNSIGNED) BEGIN SELECT id, givenname, surname, email FROM Users WHERE id = pid; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_getuseremail (IN pEmail VARCHAR(50)) BEGIN SELECT id, givenname, surname, email, password FROM Users WHERE email = pemail; END$$ 
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_logout (IN ptoken VARBINARY(300), IN puid INT UNSIGNED, IN pexp DATETIME ) BEGIN INSERT INTO Bokens VALUES (ptoken, puid, pexp); END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_lookuptoken (IN ptoken VARBINARY(300) ) BEGIN SELECT * FROM Bokens WHERE token = ptoken; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_expiretokens () BEGIN DELETE FROM Bokens WHERE expdate < NOW(); END$$ 
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_gettransactions ( IN puid INT UNSIGNED) BEGIN SELECT T.id, A.name AS "accountname", paiddate, payee, amount, category FROM Transactions AS T JOIN Accounts AS A ON T.accountid = A.id WHERE userid = puid; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_getrange (IN puid INT UNSIGNED, IN pstart DATETIME, IN pstop DATETIME) BEGIN SELECT T.id, A.name AS "accountname", paiddate, payee, amount, category FROM Transactions AS T JOIN Accounts AS A ON T.accountid = A.id WHERE userid = puid AND paiddate BETWEEN pstart AND pstop; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_gettransaction (IN puid INT UNSIGNED, IN pid INT UNSIGNED) BEGIN SELECT T.id, A.name AS "accountname", paiddate, payee, amount, category FROM Transactions AS T JOIN Accounts AS A ON T.accountid = A.id WHERE userid = puid AND T.id = pid; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_getcattransactions (IN puid INT UNSIGNED, IN pcat VARCHAR(255)) BEGIN SELECT T.id, A.name AS "accountname", paiddate, payee, amount, category FROM Transactions AS T JOIN Accounts AS A ON T.accountid = A.id WHERE userid = puid AND category = pcat; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_getcattransactionrange (IN puid INT UNSIGNED, IN pcat VARCHAR(255), IN pstart DATETIME, IN pstop DATETIME) BEGIN SELECT T.id, A.name AS "accountname", paiddate, payee, amount, category FROM Transactions AS T JOIN Accounts AS A ON T.accountid = A.id WHERE userid = puid AND category = pcat AND paiddate BETWEEN pstart AND pstop; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_getpaytransactions (IN puid INT UNSIGNED, IN ppayee VARCHAR(100)) BEGIN SELECT T.id, A.name AS "accountname", paiddate, payee, amount, category FROM Transactions AS T JOIN Accounts AS A ON T.accountid = A.id WHERE userid = puid AND payee = ppayee; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_getpaytransactionrange (IN puid INT UNSIGNED, IN ppayee VARCHAR(100), IN pstart DATETIME, IN pstop DATETIME) BEGIN SELECT T.id, A.name AS "accountname", paiddate, payee, amount, category FROM Transactions AS T JOIN Accounts AS A ON T.accountid = A.id WHERE userid = puid AND payee = ppayee AND paiddate BETWEEN pstart AND pstop; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_getaccounttransactions (IN puid INT UNSIGNED, IN paid INT UNSIGNED) BEGIN SELECT T.id, A.name AS "accountname", paiddate, payee, amount, category FROM Transactions AS T JOIN Accounts AS A ON T.accountid = A.id WHERE userid = puid AND accountid = paid; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_getaccounttransactionrange (IN puid INT UNSIGNED, IN paid INT UNSIGNED, IN pstart DATETIME, IN pstop DATETIME) BEGIN SELECT T.id, A.name AS "accountname", paiddate, payee, amount, category FROM Transactions AS T JOIN Accounts AS A ON T.accountid = A.id WHERE userid = puid AND accountid = paid AND paiddate BETWEEN pstart AND pstop; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_addtransaction (IN puid INT UNSIGNED, IN paid INT UNSIGNED, IN pammount DECIMAL(13,2), IN pdate DATETIME, IN ppayee VARCHAR(100), IN pcat VARCHAR(255)) BEGIN INSERT INTO Transactions (accountid, payerid, paiddate, payee, amount, category) VALUES (paid, puid, pdate,  ppayee, pammount, pcat); END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_edittransaction (IN puid INT UNSIGNED, IN pid INT UNSIGNED, IN paid INT UNSIGNED, IN pammount DECIMAL(13,2), IN pdate DATETIME, IN ppayee VARCHAR(100), IN pcat VARCHAR(255)) BEGIN IF(paid IS NULL OR paid = '')THEN SELECT accountid INTO paid FROM Transactions WHERE id = pid AND payerid = puid; END IF; IF(pdate IS NULL OR pdate = '')THEN SELECT paiddate INTO pdate FROM Transactions WHERE id = pid AND payerid = puid; END IF; IF(ppayee IS NULL OR ppayee = '')THEN SELECT payee INTO ppayee FROM Transactions WHERE id = pid AND payerid = puid; END IF; IF(pammount IS NULL OR pammount = '' OR pammount = 0)THEN SELECT amount INTO pammount FROM Transactions WHERE id = pid AND payerid = puid; END IF; IF(pcat IS NULL OR pcat = '')THEN SELECT category INTO pcat FROM Transactions WHERE id = pid AND payerid = puid; END IF; UPDATE Transactions SET accountid = paid, paiddate = pdate, payee = ppayee, amount = pammount, category = pcat WHERE id = pid AND payerid = puid; END$$ 
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_getcategories (IN puid INT UNSIGNED) BEGIN SELECT DISTINCT category FROM Transactions WHERE payerid = puid ORDER BY category DESC; END $$
DELIMITER ;
