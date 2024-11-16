# Employee Management System - Backend

Welcome to the **Employee Management System Backend** built with **Spring Boot**. This backend is the backbone of the Employee Management system, providing APIs and business logic for user authentication, timesheet management, and reporting.

---

## 🚀 Features
- 🛡️ **Authentication & Authorization**:
  - User login with Spring Security.
  - Role-based access control (Admin, Collaborator).
- 📊 **Timesheet Management**:
  - Create, update, and manage timesheets for collaborators.
  - Track hours worked on projects, releases, and activities.
- 📑 **User Management**:
  - Admins can add, edit, and delete users.
  - Assign roles and manage permissions.

---

## 🛠️ Technologies Used
- **Spring Boot**: Backend framework.
- **Hibernate**: ORM for database interactions.
- **MySQL**: Database for data persistence.
- **Maven**: Dependency management.
- **Docker**: For containerizing the backend application.

---


## 🔧 Setup Instructions
1. **Clone the repository**:
   ```bash
   git clone https://github.com/oumaymamessaoudi/EmployeeManagementBack.git
   cd EmployeeManagementBack
2.Run MySQL in Docker: Start a MySQL container with the following command:
docker run --name mysql-employee-management -e MYSQL_ROOT_PASSWORD=your-password -e MYSQL_DATAB
3.Run the application:
mvn spring-boot:run

🤝 Contribution
Feel free to fork this repository and create a pull request with your contributions. All ideas are welcome!

📧 Contact
Author: Oumayma Messaoudi
Email: oumayma.messaoudi@esprit.tn

---

If you'd like additional help with Docker integration details (e.g., Docker Compose for both the backend and database), let me know! 😊
