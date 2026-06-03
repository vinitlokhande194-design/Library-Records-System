use librarydb;

CREATE TABLE book (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    isbn VARCHAR(20) UNIQUE,
    quantity INT NOT NULL,
    available_quantity INT NOT NULL
);

CREATE TABLE member (
    member_id INT PRIMARY KEY AUTO_INCREMENT,
    member_name VARCHAR(100) NOT NULL,
    email VARCHAR(30),
    phone VARCHAR(100),
    member_type ENUM('STUDENT','FACULTY') NOT NULL,
    registration_date DATE
);

CREATE TABLE book_issue (
    issue_id INT PRIMARY KEY AUTO_INCREMENT,
    
    member_id INT NOT NULL,
    book_id INT NOT NULL,

    issue_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,

    status ENUM('ISSUED','RETURNED') DEFAULT 'ISSUED',

    FOREIGN KEY (member_id)
        REFERENCES member(member_id),

    FOREIGN KEY (book_id)
        REFERENCES book(book_id)
);

CREATE TABLE fine (
    fine_id INT PRIMARY KEY AUTO_INCREMENT,

    issue_id INT NOT NULL,

    fine_amount DECIMAL(10,2) DEFAULT 0,

    paid_status ENUM('PAID','UNPAID') DEFAULT 'UNPAID',

    FOREIGN KEY (issue_id)
        REFERENCES book_issue(issue_id)
);

INSERT INTO book
(title, author, category, isbn, quantity, available_quantity)
VALUES
('Java Programming', 'Herbert Schildt', 'Programming', 'ISBN001', 10, 10),
('Effective Java', 'Joshua Bloch', 'Programming', 'ISBN002', 8, 8),
('Head First Java', 'Kathy Sierra', 'Programming', 'ISBN003', 12, 12),
('Clean Code', 'Robert C. Martin', 'Programming', 'ISBN004', 15, 15),
('Data Structures in Java', 'Narasimha Karumanchi', 'Programming', 'ISBN005', 7, 7),
('Database System Concepts', 'Abraham Silberschatz', 'Database', 'ISBN006', 9, 9),
('Operating System Concepts', 'Abraham Silberschatz', 'Operating System', 'ISBN007', 6, 6),
('Computer Networks', 'Andrew S. Tanenbaum', 'Networking', 'ISBN008', 10, 10),
('Introduction to Algorithms', 'Thomas H. Cormen', 'Algorithms', 'ISBN009', 5, 5),
('Artificial Intelligence', 'Stuart Russell', 'AI', 'ISBN010', 8, 8),
('Python Crash Course', 'Eric Matthes', 'Programming', 'ISBN011', 14, 14),
('The Pragmatic Programmer', 'Andrew Hunt', 'Programming', 'ISBN012', 11, 11),
('Software Engineering', 'Ian Sommerville', 'Software Engineering', 'ISBN013', 7, 7),
('Design Patterns', 'Erich Gamma', 'Programming', 'ISBN014', 9, 9),
('Machine Learning', 'Tom M. Mitchell', 'AI', 'ISBN015', 10, 10);

INSERT INTO member
(member_name, email, phone, member_type, registration_date)
VALUES
('Rahul Sharma', 'rahul@gmail.com', '9876543210', 'STUDENT', '2025-01-10'),
('Priya Verma', 'priya@gmail.com', '9876543211', 'STUDENT', '2025-01-12'),
('Amit Kumar', 'amit@gmail.com', '9876543212', 'STUDENT', '2025-01-15'),
('Sneha Patel', 'sneha@gmail.com', '9876543213', 'STUDENT', '2025-01-18'),
('Dr. Rajesh Gupta', 'rajesh@college.edu', '9876543214', 'FACULTY', '2025-01-05'),
('Prof. Anjali Singh', 'anjali@college.edu', '9876543215', 'FACULTY', '2025-01-08'),
('Dr. Vikram Mehta', 'vikram@college.edu', '9876543216', 'FACULTY', '2025-01-11'),
('Prof. Neha Kapoor', 'neha@college.edu', '9876543217', 'FACULTY', '2025-01-14');