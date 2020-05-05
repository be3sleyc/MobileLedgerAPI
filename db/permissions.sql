GRANT USAGE ON *.* TO 'gary'@'%' IDENTIFIED BY PASSWORD '*26CF6C0B199517ED06F9C618898D91B3E7ABB3A0';
GRANT USAGE ON *.* TO 'gary'@'localhost' IDENTIFIED BY PASSWORD '*26CF6C0B199517ED06F9C618898D91B3E7ABB3A0';
GRANT USAGE ON *.* TO 'gary'@'192.168.1.124' IDENTIFIED BY PASSWORD '*26CF6C0B199517ED06F9C618898D91B3E7ABB3A0';
GRANT USAGE ON *.* TO 'gary'@'192.168.1.7' IDENTIFIED BY PASSWORD '*26CF6C0B199517ED06F9C618898D91B3E7ABB3A0';

GRANT SELECT, INSERT, UPDATE, DELETE ON `MobileLedger`.* TO 'gary'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON `MobileLedger`.* TO 'gary'@'192.168.1.124';
GRANT SELECT, INSERT, UPDATE, DELETE ON `MobileLedger`.* TO 'gary'@'192.168.1.7';

GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_adduser` TO 'gary'@'localhost';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_adduser` TO 'gary'@'192.168.1.124';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_adduser` TO 'gary'@'192.168.1.7';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_login` TO 'gary'@'localhost';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_login` TO 'gary'@'192.168.1.124';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_login` TO 'gary'@'192.168.1.7';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_getuseremail` TO 'gary'@'localhost';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_getuseremail` TO 'gary'@'192.168.1.124';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_getuseremail` TO 'gary'@'192.168.1.7';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_getuserid` TO 'gary'@'localhost';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_getuserid` TO 'gary'@'192.168.1.124';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_getuserid` TO 'gary'@'192.168.1.7';

GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_addaccount` TO 'gary'@'localhost';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_addaccount` TO 'gary'@'192.168.1.124';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_addaccount` TO 'gary'@'192.168.1.7';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_editaccount` TO 'gary'@'localhost';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_editaccount` TO 'gary'@'192.168.1.124';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_editaccount` TO 'gary'@'192.168.1.7';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_getaccounts` TO 'gary'@'localhost';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_getaccounts` TO 'gary'@'192.168.1.124';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_getaccounts` TO 'gary'@'192.168.1.7';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_closeaccount` TO 'gary'@'localhost';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_closeaccount` TO 'gary'@'192.168.1.124';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_closeaccount` TO 'gary'@'192.168.1.7';

GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_logout` TO 'gary'@'localhost';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_logout` TO 'gary'@'192.168.1.124';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_logout` TO 'gary'@'192.168.1.7';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_lookuptoken` TO 'gary'@'localhost';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_lookuptoken` TO 'gary'@'192.168.1.124';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_lookuptoken` TO 'gary'@'192.168.1.7';

GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_gettransactions` TO 'gary'@'localhost';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_gettransactions` TO 'gary'@'192.168.1.124';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_gettransactions` TO 'gary'@'192.168.1.7';
Grant Execute ON PROCEDURE `MobileLedger`.`sp_addtransaction` TO 'gary'@'localhost';
Grant Execute ON PROCEDURE `MobileLedger`.`sp_addtransaction` TO 'gary'@'192.168.1.124';
Grant Execute ON PROCEDURE `MobileLedger`.`sp_addtransaction` TO 'gary'@'192.168.1.7';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_edittransaction` TO 'gary'@'localhost';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_edittransaction` TO 'gary'@'192.168.1.124';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_edittransaction` TO 'gary'@'192.168.1.7';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_deletetransaction` TO 'gary'@'localhost';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_deletetransaction` TO 'gary'@'192.168.1.124';
GRANT EXECUTE ON PROCEDURE `MobileLedger`.`sp_deletetransaction` TO 'gary'@'192.168.1.7';