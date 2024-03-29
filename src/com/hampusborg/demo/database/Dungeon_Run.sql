DROP DATABASE IF EXISTS Dungeon_Run;

CREATE DATABASE IF NOT EXISTS Dungeon_Run;

USE Dungeon_Run;

CREATE TABLE Hero (
    heroID INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    heroClass VARCHAR(50),
    strength INT,
    intelligence INT,
    agility INT,
    health INT,
    experience INT,
    gold INT,
    heroLevel INT,
    monstersKilled INT,
    damage INT,
    CreationTime TIME
);

CREATE TABLE Monster (
    monsterID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    damage INT NOT NULL,
    health INT NOT NULL
);

CREATE TABLE Weapon (
    weaponID INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    damage INT NOT NULL,
    cost INT NOT NULL
);

CREATE TABLE HeroWeapon (
    heroID INT,
    weaponID INT,
    quantity INT NOT NULL,
    cost INT NOT NULL,
    FOREIGN KEY (weaponID) REFERENCES Weapon(weaponID),
    FOREIGN KEY (heroID) REFERENCES Hero(heroID)
);


CREATE TABLE CombatLog (
    fightID INT PRIMARY KEY AUTO_INCREMENT,
    heroID INT,
    monsterID INT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    heroInitialHealth INT,
    monsterInitialHealth INT,
    heroHealthLost INT,
    monsterHealthLost INT,
    battleWinner VARCHAR(50),
    heroName VARCHAR(50),
    monsterType VARCHAR(50),
    FOREIGN KEY (heroID) REFERENCES Hero(heroID) ON DELETE CASCADE,
    FOREIGN KEY (monsterID) REFERENCES Monster(monsterID) ON DELETE CASCADE
);

INSERT INTO Weapon (weaponID, name, damage, cost) VALUES (1, 'Cool bow', 4, 100);
INSERT INTO Weapon (weaponID, name, damage, cost) VALUES (2, 'Butter knife', 1, 50);
INSERT INTO Weapon (weaponID, name, damage, cost) VALUES (3, 'Sugar cane', 100, 1000);














    
