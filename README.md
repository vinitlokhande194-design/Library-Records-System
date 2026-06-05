# Library Records System

## Project Overview

Library Records System is a Java-based application developed using JDBC, MySQL, DAO Design Pattern, and Object-Oriented Programming concepts. The system manages books, library members, book issue transactions, and fines.

The project demonstrates concepts such as:

- Encapsulation
- Inheritance
- Abstraction
- Runtime Polymorphism
- DAO Design Pattern
- JDBC Connectivity
- MySQL Database Integration

---

## Technologies Used

- Java
- JDBC
- MySQL
- NetBeans IDE
- DAO Pattern
- OOP Concepts

---

## Project Structure

src/java

├── dao

├── daoimpl

├── model

├── util

└── main

### Packages

#### model

Contains entity classes:

- Book
- Member
- StudentMember
- FacultyMember
- BookIssue
- Fine

#### dao

Contains interfaces for database operations.

- BookDao
- MemberDao
- BookIssueDao
- FineDao

#### daoimpl

Contains JDBC implementation of DAO interfaces.

- BookDaoImpl
- MemberDaoImpl
- BookIssueDaoImpl
- FineDaoImpl

#### util

Contains database connection utility.

- DBConnection

---

## Features

### Book Management

- Add Book
- Update Book
- Delete Book
- Search Book
- View Books

### Member Management

- Add Member
- Update Member
- Delete Member
- View Members

### Book Issue Management

- Issue Book
- Return Book
- Track Due Dates
- Track Issue Status

### Fine Management

- Calculate Fine
- Maintain Fine Records

---

## DAO Design Pattern

The project follows DAO architecture to separate business logic from database logic.

```text
Main Program
      ↓
DAO Interface
      ↓
DAO Implementation
      ↓
JDBC
      ↓
MySQL
```

Advantages:

- Better maintainability
- Reusable code
- Separation of concerns

---

## Database Tables

### Book

- book_id
- title
- author
- category
- isbn
- quantity
- available_quantity

### Member

- member_id
- member_name
- email
- phone
- member_type
- registration_date

### BookIssue

- issue_id
- member_id
- book_id
- issue_date
- due_date
- return_date
- status

### Fine

- fine_id
- issue_id
- member_id
- amount
- status

---

## Team Contributions

### Bhushan

- Book Class
- Member Class
- DAO Layer
- JDBC Integration
- Architecture Design
- README Documentation

### Vinit

- StudentMember Class
- FacultyMember Class
- BookIssue Class
- Runtime Polymorphism Implementation

---

## Future Enhancements

- GUI Interface
- Web-based Version
- Authentication System
- Fine Payment Module
- Report Generation
