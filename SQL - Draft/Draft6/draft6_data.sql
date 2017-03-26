INSERT INTO `project`.`team`
(`idTeam`,`title`,`description`)
VALUES
(1,'Team 1','WS13/14'),
(2,'Team 2','WS13/14');

INSERT INTO `project`.`user`
(`idUser`,`WinUsername`,`eMail`,`firstName`,`lastName`,`pwHash`,`active`,`idTeam`,`prefProject`)
VALUES
(1,'funger','flounger@web.de','Florian','Unger','funger',1,1,NULL),
(2,'admin','admin@wpm.de','admin','admin','admin',1,NULL,NULL),
(3,'user','user@wpm.de','user','user','user',1,NULL,NULL),
(4,'pman','pman@wpm.de','pman','pman','pman',1,NULL,NULL);


INSERT INTO `project`.`role`
(`idRole`,`title`)
VALUES
('ADMIN','Administrator'),
('PMAN','Projektmanager'),
('USER','Benutzer');

INSERT INTO `project`.`user_role`
(`idRole`,`idUser`)
VALUES
('ADMIN',1),('PMAN',1),('USER',1),('ADMIN',2),('PMAN',2),('USER',2),('USER',3),('PMAN',4),('USER',4);

