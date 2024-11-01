-- Insert Users
INSERT INTO "User" (login, password) VALUES
('user1', 'password1'),
('user2', 'password2'),
('user3', 'password3'),
('user4', 'password4'),
('user5', 'password5');

-- Insert Chatrooms
INSERT INTO Chatroom (name, owner_id) VALUES
('Chatroom A', 1),
('Chatroom B', 2),
('Chatroom C', 3),
('Chatroom D', 1),
('Chatroom E', 5);

-- Insert Messages
INSERT INTO Message (author_id, chatroom_id, text) VALUES
(1, 1, 'Hello everyone!'),
(2, 1, 'Welcome to Chatroom A!'),
(3, 2, 'This is a test message.'),
(4, 3, 'How is everyone doing?'),
(5, 4, 'Let’s talk about programming.');
