USE project;

INSERT INTO team
(idTeam,title,description)
VALUES
(1,'Team2','Beschreibung');

INSERT INTO user
(email,firstName,lastName,pwHash,idTeam)
VALUES
('flounger@web.de','Florian','Unger',MD5('abc'),1);
