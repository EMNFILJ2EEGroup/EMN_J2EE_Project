DROP TABLE app.participations; 
DROP TABLE app.events; 
DROP TABLE app.organizers;



CREATE TABLE app.organizers( 
id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
email VARCHAR(255) UNIQUE NOT NULL, 
password VARCHAR(255) NOT NULL,
CONSTRAINT primary_key_organizers PRIMARY KEY (id)
);

CREATE TABLE app.events( 
id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
name VARCHAR(100) NOT NULL, 
publication INT NOT NULL default 0, 
adresse VARCHAR(255), 
date_debut DATE NOT NULL, 
heure_debut TIME NOT NULL, 
date_fin DATE NOT NULL, 
heure_fin TIME NOT NULL, 
fk_organizer INT NOT NULL, 
FOREIGN KEY(fk_organizer) REFERENCES app.organizers(id),
CONSTRAINT primary_key_events PRIMARY KEY (id)
);

CREATE TABLE app.participations( 
id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
email VARCHAR(255) UNIQUE , 
nom VARCHAR(100) NOT NULL, 
prenom VARCHAR(100) NOT NULL, 
societe VARCHAR(255), 
fk_event INT NOT NULL, 
FOREIGN KEY(fk_event) REFERENCES app.events(id), 
CONSTRAINT primary_key_participations PRIMARY KEY (id)
);

INSERT INTO app.organizers (email, password) VALUES ('admin@a.fr', 'test')