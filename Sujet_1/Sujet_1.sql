CREATE TABLE user
(
    user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(15) NOT NULL,
    prenom VARCHAR(15) NOT NULL,
    email VARCHAR(30) NOT NULL,
    password VARCHAR(20) NOT NULL,
    poste VARCHAR(40) NOT NULL,
    biographie VARCHAR(2000)
);

CREATE TABLE passion
(
    user_id int not null,
    constraint user_user_id_passion_fk foreign key (user_id)
    references user (user_id),
    titre VARCHAR(30) NOT NULL,
    description VARCHAR(1000)
);

CREATE TABLE competence
(
    user_id int not null,
    constraint user_user_id_competence_fk foreign key (user_id)
    references user (user_id),
    titre VARCHAR(30) NOT NULL,
    description VARCHAR(1000)
);

INSERT INTO user
(email, password, nom, prenom, poste, biographie)
VALUES
('john@example.com', '123', 'Smith', 'John', 'Project Manager', 'Il travaille bien et mange beaucoup.');

INSERT INTO `competence` (`user_id`, `titre`, `description`) VALUES
(1, 'Word', 'Matriser bien Word 2003.'),
(1, 'Englais', 'Bon niveau d''anglais.'),
(1, 'Math', 'Très fort en comptabilité.'),
(1, 'Piano', 'Jouer très bien de piano.');
