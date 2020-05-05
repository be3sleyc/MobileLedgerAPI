DELIMITER $$
CREATE PROCEDURE sp_addaccount (IN pUid INT UNSIGNED, IN pName VARCHAR(30), IN ptype VARCHAR(20), IN pBalance DECIMAL(13,2), IN pNote VARCHAR(512)) BEGIN IF(pName NOT IN (SELECT name FROM Accounts WHERE userid = puid)) THEN IF(pBalance IS NULL OR pBalance = '') THEN SET pBalance = 0.00; END IF; INSERT INTO Accounts (userid, name, type, balance, notes) VALUES (pUid, pName, ptype, pBalance, pNote); END IF; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_getaccounts (IN pid INT UNSIGNED) BEGIN SELECT id, name, type, balance, notes, isdeleted FROM Accounts WHERE userid = pid; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_editaccount (IN pid INT UNSIGNED, IN puid INT UNSIGNED, IN pname VARCHAR(30), IN ptype VARCHAR(20), IN pnotes VARCHAR(512)) BEGIN IF(pname = '' OR pname IS NULL) THEN SELECT name INTO pname FROM Accounts WHERE id = pid AND userid = puid; END IF; IF(ptype = '' OR ptype IS NULL) THEN SELECT type INTO ptype FROM Accounts WHERE id = pid AND userid = puid; END IF; IF(pnotes = '' OR pnotes IS NULL) THEN SELECT notes INTO pnotes FROM Accounts WHERE id = pid AND userid = puid; END IF; UPDATE Accounts SET name = pname, type = ptype, notes = pnotes WHERE id = pid AND userid = puid; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_closeaccount (IN pid INT UNSIGNED, IN puid INT UNSIGNED) BEGIN UPDATE Accounts SET isdeleted = 1 WHERE id = pid AND userid = puid; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_adduser (IN pGivenName VARCHAR(30),  IN pSurName VARCHAR(30),  IN pEmail VARCHAR(50),  IN pPassword Binary(80)) BEGIN IF(pEmail NOT IN (SELECT email FROM Users)) THEN INSERT INTO Users (givenname, surname, email, password) VALUES (pGivenName, pSurName, pEmail, pPassword); CALL sp_addaccount((SELECT id FROM Users WHERE email = pEmail), 'Cash','cash', 0, ''); END IF; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_login (IN pEmail VARCHAR(50), IN pPassword BINARY(80)) BEGIN DECLARE pUser INT DEFAULT NULL; SELECT id INTO pUser FROM Users WHERE email = pEmail AND password = pPassword; IF(pUser IS NOT NULL) THEN UPDATE Users SET lastaccess = NOW() WHERE id = pUser; SELECT id, email, givenname, surname, lastaccess FROM Users WHERE id = pUser; END IF; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_getuserid (IN pid INT UNSIGNED) BEGIN SELECT id, email, givenname, surname, lastaccess FROM Users WHERE id = pid; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_getuseremail (IN pEmail VARCHAR(50)) BEGIN SELECT id, email, givenname, surname, lastaccess, password FROM Users WHERE email = pemail; END$$ 
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_logout (IN ptoken VARBINARY(300), IN puid INT UNSIGNED, IN pexp DATETIME ) BEGIN call sp_expiretokens(); INSERT INTO Bokens VALUES (ptoken, puid, pexp); END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_lookuptoken (IN ptoken VARBINARY(300) ) BEGIN SELECT * FROM Bokens WHERE token = ptoken; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_gettransactions ( IN puid INT UNSIGNED) BEGIN SELECT T.id, A.name AS "accountname", paiddate, payee, description, amount, category FROM Transactions AS T JOIN Accounts AS A ON T.accountid = A.id WHERE userid = puid; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_addtransaction (IN puid INT UNSIGNED, IN paid INT UNSIGNED, IN pamount DECIMAL(13,2), IN pdate DATETIME, IN ppayee VARCHAR(100), IN pdescription VARCHAR(100), IN pcat VARCHAR(255)) BEGIN IF(pcat IS NULL OR pcat = '') THEN INSERT INTO Transactions (accountid, payerid, paiddate, payee, description, amount) VALUES (paid, puid, pdate,  ppayee, pdescription, pamount); ELSE INSERT INTO Transactions (accountid, payerid, paiddate, payee, description, amount, category) VALUES (paid, puid, pdate,  ppayee, pdescription, pamount, pcat); END IF; END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_edittransaction (IN pid INT UNSIGNED, IN puid INT UNSIGNED, IN paid INT UNSIGNED, IN pdate DATETIME, IN ppayee VARCHAR(100), IN pdescription VARCHAR(100), IN pamount DECIMAL(13,2), IN pcat VARCHAR(255)) BEGIN DECLARE old_amount DECIMAL(13,2) DEFAULT NULL; IF(paid IS NULL OR paid = '')THEN SELECT accountid INTO paid FROM Transactions WHERE id = pid AND payerid = puid; END IF; IF(pdate IS NULL OR pdate = '')THEN SELECT paiddate INTO pdate FROM Transactions WHERE id = pid AND payerid = puid; END IF; IF(ppayee IS NULL OR ppayee = '')THEN SELECT payee INTO ppayee FROM Transactions WHERE id = pid AND payerid = puid; END IF; IF(pdescription IS NULL OR pdescription = '')THEN SELECT description INTO pdescription FROM Transactions WHERE id = pid AND payerid = puid; END IF; IF(pamount IS NULL OR pamount = '' OR pamount = 0)THEN SELECT amount INTO pamount FROM Transactions WHERE id = pid AND payerid = puid; ELSE SELECT amount INTO old_amount FROM Transactions WHERE id = pid AND payerid = puid; END IF; IF(pcat IS NULL OR pcat = '')THEN SELECT category INTO pcat FROM Transactions WHERE id = pid AND payerid = puid; END IF; UPDATE Transactions SET accountid = paid, paiddate = pdate, payee = ppayee, description = pdescription, amount = pamount, category = pcat WHERE id = pid AND payerid = puid; END$$ 
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_deletetransaction (puid INT UNSIGNED, IN pid INT UNSIGNED) BEGIN DELETE FROM Transactions WHERE id = pid AND payerid = puid; END $$
DELIMITER ;

CREATE DEFINER=`root`@`localhost` TRIGGER tr_resetaccountbalance
AFTER DELETE 
    ON Transactions FOR EACH ROW
    UPDATE Accounts SET balance = balance - Old.amount WHERE id = Old.accountid;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` TRIGGER tr_updateaccountbalance
AFTER UPDATE 
    ON Transactions FOR EACH ROW
    BEGIN
    UPDATE Accounts SET balance = balance - Old.amount WHERE id = Old.accountid;
    UPDATE Accounts SET balance = balance + New.amount WHERE id = New.accountid;
    END $$
DELIMITER ;

CREATE DEFINER=`root`@`localhost` TRIGGER tr_incrementaccountbalance
AFTER INSERT 
    ON Transactions FOR EACH ROW
    UPDATE Accounts SET balance = balance + New.amount WHERE id = New.accountid;

CREATE DEFINER=`root`@`localhost` TRIGGER tr_incrementaccountbalance
AFTER INSERT 
    ON Bokens FOR EACH ROW
    DELETE FROM Bokens WHERE expdate < NOW() - INTERVAL 14 DAY;