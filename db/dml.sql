CREATE PROCEDURE sp_adduser (
    IN pGivenName VARCHAR(30), 
    IN pSurName VARCHAR(30), 
    IN pEmail VARCHAR(50),
    IN pPassword Binary(80),
    OUT rResult INT)
BEGIN
    DECLARE pUser INT DEFAULT NULL;
    IF(pGivenName IS NOT NULL) THEN
        IF(pSurName IS NOT NULL) THEN
            IF(pEmail IS NOT NULL) THEN
                IF(pPassword IS NOT NULL) THEN
                    SET pUser := (SELECT id FROM Users WHERE email = pEmail);
                    IF(pUser IS NULL) THEN
                        INSERT INTO Users (givenName, surName, email, password) VALUES (pGivenName, pSurName, pEmail, pPassword);
                        SELECT ROW_COUNT() INTO rResult;
                    ENDIF;
                ENDIF;
            ENDIF;
        ENDIF;
    ENDIF;
END;

CREATE PROCEDURE sp_login (
    IN pEmail VARCHAR(50),
    IN pPassword BINARY(80))
BEGIN
    DECLARE pUser INT DEFAULT NULL;
    SELECT id INTO pUser FROM Users WHERE email = pEmail AND password = pPassword
    IF(pUser IS NOT NULL) THEN
        UPDATE Users SET lastaccess = NOW WHERE id = pUser;
        SELECT * FROM Users WHERE id = pUser;
    ENDIF;
END