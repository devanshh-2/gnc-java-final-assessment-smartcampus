# SmartCampus Management System (Java Project)

## 📌 Problem Statement

This project is developed as part of the final assessment for BTech 2nd Year at GNC College.
The goal is to build a Smart Campus Management System that helps manage students, courses, and enrollments efficiently.

---

## 🎯 Objective

* Manage student and course data
* Allow students to enroll in multiple courses
* Handle invalid inputs using exception handling
* Process enrollments using multithreading
* Store and retrieve data using file handling

---

## ⚙️ Features

### 👨‍🎓 Student Management

* Add new students
* Store student details (ID, Name, Email)

### 📚 Course Management

* Add new courses
* Store course details (Course ID, Name, Fee)

### 📝 Enrollment System

* Enroll a student in one or more courses
* Uses `HashMap` and `ArrayList` for efficient storage

### ⚠️ Exception Handling

* Custom exception (`InvalidFeeException`)
* Prevents invalid data like negative course fee

### ⚡ Multithreading

* Enrollment processing is done using threads
* Simulates real-time processing

### 💾 File Handling (Bonus)

* Save data to file (`data.ser`)
* Load previously saved data

---

## 🛠️ Technologies Used

* Java (Core Java)
* OOP Concepts (Classes, Objects, Inheritance)
* Collections Framework
* Exception Handling
* Multithreading
* File Handling

---

## ▶️ How to Run

1. Compile the program:

```
javac SmartCampus.java
```

2. Run the program:

```
java SmartCampus
```

---

## 📂 Project Structure

```
SmartCampus.java
README.md
data.ser (generated after saving data)
```

---

## Multiple Choice Questions.

1. A developer is implementing student enrollments where each student can enroll in multiple courses. The system should allow quick lookup of a student and their enrolled courses.
Which is the most optimal data structure?

ANSWER - B. HashMap<Student, ArrayList<Course>>

2. During enrollment, a student enters a negative course fee, causing incorrect processing. The developer wants to ensure invalid data is handled properly.
What is the best approach?

ANSWER - C. Throw a custom exception like InvalidFeeException

3. The system simulates enrollment processing using a thread. However, multiple threads are accessing the same enrollment list, causing inconsistent results.
What should be done?

ANSWER - B. Use synchronized block or thread-safe collection

4. A developer wants to enforce a rule that every type of course must implement a method calculateFee() but allow different implementations.
Which concept should be used?

ANSWER - B. Interface

## 👨‍💻 Author

* Name: Devansh Dhyani
* Course: BTech (2nd Year)

---
