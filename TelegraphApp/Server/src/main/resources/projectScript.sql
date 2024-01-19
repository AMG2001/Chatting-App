/* ------------------------Create All Tables------------------------------*/
CREATE TABLE IF NOT EXISTS User (
    phone_number VARCHAR(15) PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255) NOT NULL,
    dob DATE,
    country VARCHAR(100),
    gender ENUM('MALE', 'FEMALE'),
    bio TEXT,
    status ENUM('ONLINE','OFFLINE','AWAY','BUSY'),
    picture VARCHAR(255)
);


CREATE TABLE IF NOT EXISTS Contact (
    user_phone VARCHAR(15),
    contact_phone VARCHAR(15),
    add_date DATETIME,
    PRIMARY KEY (user_phone, contact_phone),
    FOREIGN KEY (user_phone) REFERENCES User(phone_number),
    FOREIGN KEY (contact_phone) REFERENCES User(phone_number)
);

CREATE TABLE IF NOT EXISTS Contact_Request (
    request_id INT PRIMARY KEY,
    sender_phone VARCHAR(15),
    receiver_phone VARCHAR(15),
    status ENUM('PENDING', 'ACCEPTED', 'DENIED') DEFAULT 'PENDING',
    responded_at DATETIME,
    FOREIGN KEY (sender_phone) REFERENCES User(phone_number),
    FOREIGN KEY (receiver_phone) REFERENCES User(phone_number)
);

CREATE TABLE IF NOT EXISTS Conversation (
	conversation_id INT PRIMARY KEY,
    conversation_img VARCHAR(255),
    conversation_name VARCHAR(255),
    type ENUM('INDIVIDUAL','GROUP') NOT NULL
);

CREATE TABLE IF NOT EXISTS User_Conversation (
    phone_number VARCHAR(15),
    conversation_id INT,
    join_date DATETIME,
    PRIMARY KEY (phone_number, conversation_id),
    FOREIGN KEY (phone_number) REFERENCES User(phone_number),
    FOREIGN KEY (conversation_id) REFERENCES Conversation(conversation_id)
);

CREATE TABLE IF NOT EXISTS Attachment(
	attachment_id INT PRIMARY KEY,
    attachment_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Message (
    message_id INT PRIMARY KEY,
    conversation_id INT,
    sender_phone VARCHAR(15),
    attachment_id INT,
    message_body TEXT,
    timestamp DATETIME ,
    FOREIGN KEY (conversation_id) REFERENCES Conversation(conversation_id),
    FOREIGN KEY (sender_phone) REFERENCES User(phone_number),
    FOREIGN KEY (attachment_id) REFERENCES Attachment(attachment_id)
);

CREATE TABLE IF NOT EXISTS Notification(
	notification_id INT PRIMARY KEY,
    notification_body TEXT,
    timestamp DATETIME,
    type ENUM('FRIEND','REQUEST','MESSAGE','SYSTEM')
);

CREATE TABLE IF NOT EXISTS User_Notification(
	recipient_phone VARCHAR(15),
    notification_id INT,
    PRIMARY KEY (recipient_phone, notification_id),
    FOREIGN KEY (recipient_phone) REFERENCES User(phone_number),
    FOREIGN KEY (notification_id) REFERENCES Notification(notification_id)
);
/* -----------------------------------------------------------------------*/
/*----------------------------------------------------------------------------------------------*/

/*-----------------------------------------insert some data--------------------------------------------*/
INSERT INTO User (phone_number, name, email, password, dob, country, gender, bio, picture)
VALUES
('123456789', 'Marwan', 'Marwan@example.com', 'password123', '1997-01-01', 'Egypt', 'MALE', 'Hello, I am Marwan!', 'Marwan.jpg'),
('987654321', 'Amgad', 'Amgad@example.com', 'password456', '2001-05-15', 'Egypt','MALE', 'Nice to meet you!', 'Amgad.jpg'),
('555555555', 'Yousef', 'Yousef@example.com', 'password789', '1998-08-20', 'Egypt',  'MALE', 'Hello from Yousef!', 'Yousef.jpg');


INSERT INTO Contact_Request (request_id, sender_phone, receiver_phone, status, responded_at)
VALUES
(1, '123456789', '555555555', 'ACCEPTED', NOW()),  -- Marwan sends a contact request to Yousef and Youssef accepted it
(2, '555555555', '987654321', 'ACCEPTED', NOW()),   -- Yousef sends a contact request to Amgad and ACCEPTED accepted it
(3, '987654321', '123456789', 'ACCEPTED', NOW()); -- Amgad sends a contact request to Marwan and ACCEPTED accepted it


INSERT INTO Contact (user_phone, contact_phone, add_date)
VALUES
('123456789', '987654321', NOW()),  -- Marwan has Amgad as a contact
('987654321', '123456789', NOW()),  -- Amgad has Marwan as a contact

('123456789', '555555555', NOW()),  -- Marwan has Yousef as a contact
('555555555', '123456789', NOW()),  -- Yousef has Marwan as a contact

('987654321', '555555555', NOW()),  -- Amgad has Yousef as a contact
('555555555', '987654321', NOW());  -- Yousef has Amgad as a contact


INSERT INTO Conversation (conversation_id, conversation_img,conversation_name, type)
VALUES
(1, null,null, 'INDIVIDUAL'),
(2, null,null, 'INDIVIDUAL'),
(3, null,null, 'INDIVIDUAL'),
(4, 'team_project.jpg','Group1','GROUP');


INSERT INTO User_Conversation (phone_number, conversation_id, conversation_name, join_date)
VALUES
-- Marwan and Amgad are in a Individual conversation
('123456789', 1, NOW()),
('987654321', 1, NOW()),
-- Marwan and Yousef are in a Individual conversation
('123456789', 2, NOW()),
('555555555', 2, NOW()),
-- Amagd and Yousef are in a Individual conversation
('987654321', 3, NOW()),
('555555555', 3, NOW()),
-- Marwan, Amgad and Yousef are in Group conversation
('123456789', 4, NOW()),
('987654321', 4, NOW()),
('555555555', 4, NOW());


INSERT INTO Attachment (attachment_id, attachment_name)
VALUES
(1, 'image1.jpg'),
(2, 'document.pdf');


INSERT INTO Message (message_id, conversation_id, sender_phone, attachment_id, message_body, timestamp)
VALUES
(1, 1, '123456789', NULL, 'Hi Amgad!', NOW()), -- Marwan sends a message to Amgad
(2, 1, '987654321', 1, 'Hello Marwan!', NOW()), -- Amgad sends a message to Marwan

(3, 4, '123456789', 2, 'we are late ya shabab', NOW()),  -- Marwan sends a message to the group
(4, 4, '987654321', NULL, 'sexy awe', NOW()),            -- Amgad sends a message to the group
(5, 4, '555555555', NULL, 'what is doa ?????', NOW());     -- Yousef sends a message to the group



INSERT INTO Notification (notification_id, notification_body, timestamp, type)
VALUES
(1, 'Amagd went offline', NOW(), 'FRIEND'),
(2, 'message from the admin', NOW(), 'SYSTEM'),
(3, 'you have a message from Marwan', NOW(), 'FRIEND');


INSERT INTO User_Notification (recipient_phone, notification_id)
VALUES
-- Marwan and Yousef received a notification that Amagd went offline.
('123456789', 1),
('555555555', 1),
-- Amagd, Marwan and Yousef received a notification from the admin
('123456789', 2),
('987654321', 2),
('555555555', 2),
-- Amagd received a notification that Marwan sent a message to him
('987654321', 3);

/*----------------------------------Drop All Tables------------------------------------*/
DROP TABLE User_Notification;
DROP TABLE Notification;
DROP TABLE Message;
DROP TABLE Attachment;
DROP TABLE User_Conversation;
DROP TABLE Conversation;
DROP TABLE Contact;
DROP TABLE Contact_Request;
DROP TABLE User;
/*----------------------------------------------------------------------------------------------*/
