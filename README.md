
# 🐾 Veterinary Management System

A RESTful API application designed to manage the daily operations of a veterinary clinic. This system allows recording, viewing, and filtering of pet owners, animals, doctors, appointments, and vaccination records.

---

## 🎯 Project Goal

The main goal of this project is to simplify data management for veterinary staff, prevent errors, and allow fast queries. The system enables:  

- Management of pet owners and their animals.  
- Tracking of doctors' working days and availability.  
- Controlled recording of appointments and vaccinations.  
- Fast and accurate filtering and listing of records.  

---

## 🏗️ Architecture & Layers

# UML DIAGRAM
<img width="1180" height="673" alt="uml" src="https://github.com/user-attachments/assets/cf2f13ca-d0e3-42df-aa2c-59a3884c3ae1" />

The project follows a **layered architecture**:

- **Entity Layer:** Defines database tables and relationships. For example, the relationship between animals and vaccines is modeled using `@OneToMany` / `@ManyToOne`.  
- **Repository (DAO) Layer:** Handles database operations using Spring Data JPA. Custom queries are also defined here.  
- **Service (Business) Layer:** Implements business rules. For example, it prevents adding a vaccine to the same animal if an active vaccine with the same code already exists.  
- **Controller (API) Layer:** Provides HTTP endpoints for accessing data and returns responses in JSON format.  
- **Core Layer:** Contains shared utilities such as `ResultData`, `ResultHelper` for standardized API responses, and `ValidationException` for error handling.  

## Postman Documantation
You can download the Postman collection for this project here: 
[📥 Download Postman Collection](https://github.com/yarenYLDZ/Veterinary-Management-System/blob/master/YarenYildiz.postman_collection.json)

## 🌟 Key Features

### Pet Owner & Animal Management
- Add, edit, and manage customers and their animals.  
- Filter pet owners and animals by name.  
- List all animals belonging to a single customer.  

### Doctor & Appointment Management
- Add doctors and their available working days.  
- Validate appointments against doctor availability and existing bookings.  
- Filter appointments by date, animal, or doctor.  

### Vaccination Management
- Record vaccinations for animals.  
- Prevent adding duplicate or overlapping active vaccines for the same animal.  
- Filter vaccinations by protection end date and include associated animal information.  
- Retrieve all vaccination records for a specific animal.  

### Code Quality & Error Handling
- Clear and readable variable and method naming.  
- Exception handling for invalid data entry.  
- Proper HTTP status codes used (`200`, `201`, `400`, `404`).  
- DTOs used for controlled data transfer.  

---

## 📦 Technologies Used
- **Java 21**  
- **Spring Boot 3**  
- **Spring Data JPA / Hibernate**  
- **PostgreSQL** (or any relational database)  
- **Lombok**  
- **Maven**  

---

## 🚀 Getting Started

1. Clone the repository:
```bash
git clone https://github.com/username/veterinary-management-system.git
