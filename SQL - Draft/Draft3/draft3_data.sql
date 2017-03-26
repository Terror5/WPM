userUSE project;

INSERT INTO `project`.`team`
(`title`,
`description`)
VALUES
('test',
'test');

INSERT INTO user
(email,firstName,lastName,pwHash,idTeam,WinUsername)
VALUES
('flounger@web.de','Florian','Unger',MD5('abc'),1,'funger');