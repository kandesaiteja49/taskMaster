# TaskMaster - Collaborative Task Tracking System

TaskMaster is a professional-grade backend system designed to streamline team collaboration and task management. Built with a scalable architecture, it ensures strict data isolation and security to prevent unauthorized access to sensitive team data.

---

## 🚀 Core Features

### 🔐 Identity & Security
- **Stateless Authentication**: Implements JSON Web Tokens (JWT) for secure, scalable session management.
- **Cryptographic Hashing**: Uses BCrypt for industry-standard password encryption.
- **Strict Ownership Guards**: Implements object-level authorization to ensure users can only access, edit, or delete tasks within their own teams.
- **Secure Secret Management**: Supports environment-variable-based configuration for sensitive keys (JWT Secret).

### 📋 Advanced Task Management
- **Comprehensive Lifecycle**: Full CRUD operations for tasks with attributes for priority, status, and deadlines.
- **Secure Dynamic Search**: A powerful filtering system using JPA Specifications that automatically restricts results to the user's authorized teams.
- **Validated Assignment**: Ensures tasks can only be assigned to users who are verified members of the task's associated team.

### 👥 Team Collaboration
- **Organization Units**: Logical grouping of users into teams to isolate projects and workloads.
- **Contextual Discussion**: A comment system allowing team members to maintain a history of decisions directly on the task.
- **Document Association**: Ability to attach critical files and resources to tasks for a single source of truth.

### 🌟 Engineering Extensions
- **Real-time Notification Engine**: WebSocket (STOMP) integration to push instant alerts to users when tasks are assigned or updated.
- **AI Integration**: An intelligent layer that leverages Large Language Models (LLMs) to automatically generate detailed task descriptions based on a short title.

---

## 🏗 Architecture & Security Design

TaskMaster follows a **Layered Architecture** pattern to ensure separation of concerns:

1.  **Controller Layer**: REST Endpoints that handle incoming HTTP requests and validate input using DTOs.
2.  **Service Layer**: Contains the core business logic and **Security Guards** (verifying team membership before every sensitive operation).
3.  **Repository Layer**: Leverages Spring Data JPA for abstracting database interactions.
4.  **Model Layer**: Defines domain entities and relational mappings.

### 🛡 Security Implementation (Anti-BOLA)
To prevent **Broken Object Level Authorization (BOLA)**, the system implements the following guards:
- **Filter-Level Isolation**: `getAllTasks` now uses a mandatory join on the `User` entity to ensure no tasks from external teams are leaked.
- **Ownership Verification**: Every call to `getTaskById`, `updateTask`, and `deleteTask` triggers a `verifyTeamMembership` check.
- **Assignment Validation**: The `assignTask` logic verifies the assignee's membership in the team before updating the record.

---

## 🛠 Tech Stack

- **Language**: Java 17 (JDK 17)
- **Framework**: Spring Boot 3.3.2
- **Database**: H2 In-Memory Database (Development Mode)
- **Security**: Spring Security, JJWT (JSON Web Token)
- **Build Tool**: Apache Maven
- **Communication**: REST API, WebSockets (STOMP)
- **ORM**: Spring Data JPA (Hibernate)

---

## 🛣 API Reference

### Authentication
- `POST /api/auth/register` - Create a new user account.
- `POST /api/auth/login` - Authenticate and receive a JWT token.

### User Profile
- `GET /api/users/me` - Retrieve current user details.
- `PUT /api/users/me` - Update user profile information.

### Team Management
- `POST /api/teams` - Initialize a new team.
- `POST /api/teams/{id}/join` - Join an existing collaborative team.
- `GET /api/teams` - Retrieve all teams associated with the user.
- `GET /api/teams/{id}` - Get detailed team metadata and member list.

### Task Operations
- `POST /api/tasks` - Create a task within a specific team.
- `GET /api/tasks` - List tasks (Supports `assigneeId`, `status`, `teamId`, and `search` query params).
- `GET /api/tasks/{id}` - Retrieve full task details.
- `PUT /api/tasks/{id}` - Update task properties.
- `DELETE /api/tasks/{id}` - Remove a task from the system.
- `PATCH /api/tasks/{id}/assign` - Change the task assignee.
- `PATCH /api/tasks/{id}/generate-description` - Use AI to expand a task title into a description.

---

## ⚙️ Installation & Execution

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd TaskMaster
   ```
2. **Build the Application**:
   ```bash
   mvn clean install
   ```
3. **Launch the Server**:
   ```bash
   mvn spring-boot:run
   ```
4. **Health Check**:
   Visit `http://localhost:8080/api/status` to verify the backend is online.

---

## 🧪 Testing & Verification Guide

### 1. Functional API Testing (Postman)
**Crucial Setup**: Set **Body** $\rightarrow$ **raw** $\rightarrow$ **JSON** for all POST/PUT requests.

- **Basic Journey**: Register $\rightarrow$ Login (Copy Token) $\rightarrow$ Set Bearer Token $\rightarrow$ Create Team $\rightarrow$ Create Task.

### 2. Security Verification (Anti-BOLA Tests)
To verify the security guards, perform these tests:
- **Privacy Test**: Login as User A. Call `GET /api/tasks`. Verify you **cannot** see tasks created by User B in a different team.
- **Ownership Test**: Use User A's token to call `GET /api/tasks/{id}` using a Task ID from User B's team. Expected: **403 Forbidden**.
- **Assignment Test**: Try to assign a task to a user who is not a member of that task's team. Expected: **Error/Bad Request**.

### 3. Database Inspection (H2 Console)
- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:taskmasterdb`
- **Driver**: `org.h2.Driver`
- **User**: `sa` | **Password**: (empty)

---

## 🗺️ Project Structure
```text
src/main/java/com/taskmaster/
├── controller/      # Request handling & API endpoints
├── service/          # Business logic & Security Guards
├── repository/       # Data access layer (Spring Data JPA)
├── model/           # Domain entities (User, Task, Team, etc.)
├── security/        # JWT, SecurityConfig, UserDetailsService
├── dto/             # Data Transfer Objects (Request/Response)
└── exception/       # Global error handling logic
```

---

## 🚀 Future Roadmap
- [ ] **Persistent Database**: Migrate from H2 to PostgreSQL for production data storage.
- [ ] **Advanced AI**: Implement AI-driven task priority estimation and deadline suggestions.
- [ ] **OAuth2 Integration**: Support "Login with Google/GitHub" for faster onboarding.
- [ ] **Frontend Integration**: Build a React/Angular dashboard to replace Postman testing.
