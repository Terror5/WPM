INSERT INTO `project`.`user`
(`idUser`,`WinUsername`,`eMail`,`firstName`,`lastName`,`pwHash`,`active`,`idTeam`,`prefProject`)
VALUES
(1,'funger','flounger@web.de','Florian','Unger','funger',1,1,NULL),
(2,'admin','admin@wpm.de','admin','admin','admin',1,NULL,NULL),
(3,'user','user@wpm.de','user','user','user',1,NULL,NULL),
(4,'pman','pman@wpm.de','pman','pman','pman',1,NULL,NULL);


INSERT INTO `project`.`permission`
(`idPermission`,`title`,`createProject`,`modifyProject`,`deleteProject`,`super`)
VALUES
('ADMIN','Administrator',1,1,1,1),
('PMAN','Projektmanager',1,1,1,0),
('USER','Benutzer',0,1,0,0);

INSERT INTO `project`.`role`
(`idPermission`,`idUser`)
VALUES
('ADMIN',1),('PMAN',1),('USER',1),('ADMIN',2),('PMAN',2),('USER',2),('USER',3),('PMAN',4),('USER',4);

INSERT INTO `project`.`team`
(`idTeam`,`title`,`description`)
VALUES
(1,'Team 1','WS13/14'),
(2,'Team 2','WS13/14');