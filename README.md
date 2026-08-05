# TaskMaster - Collaborative Task Tracking System

TaskMaster is a professional-grade backend system designed to streamline team collaboration and task management. Built with a scalable architecture, it enables users to organize work into teams, manage task lifecycles with dynamic filtering, and communicate in real-time.

---

## 🚀 Core Features

### 🔐 Identity & Security
- **Stateless Authentication**: Implements JSON Web Tokens (JWT) for secure, scalable session management.
- **Cryptographic Hashing**: Uses BCrypt for industry-standard password encryption.
- **Role-Based Access**: Ensures users can only access teams and tasks they are authorized to view.
- **Profile Management**: Complete API for managing user identities and personal details.

### 📋 Advanced Task Management
- **Comprehensive Lifecycle**: Full CRUD operations for tasks with attributes for priority, status, and deadlines.
- **Dynamic Search Engine**: Powerful filtering system using JPA Specifications, allowing users to search tasks by title, description, assignee, or status.
- **Assignment Workflow**: Precise task assignment logic to maintain accountability within teams.

### 👥 Team Collaboration
- **Organization Units**: Logical grouping of users into teams to isolate projects and workloads.
- **Contextual Discussion**: A comment system allowing team members to maintain a history of decisions directly on the task.
- **Document Association**: Ability to attach critical files and resources to tasks for a single source of truth.

### 🌟 Engineering Extensions
- **Real-time Notification Engine**: WebSocket (STOMP) integration to push instant alerts to users when tasks are assigned or updated.
- **AI Integration**: An intelligent layer that leverages Large Language Models (LLMs) to automatically generate detailed task descriptions based on a short title.

---

## 🏗 Architecture Overview

TaskMaster follows a **Layered Architecture** pattern to ensure separation of concerns and maintainability:

1.  **Controller Layer**: REST Endpoints that handle incoming HTTP requests and validate input using DTOs.
2.  **Service Layer**: Contains the core business logic, security checks, and orchestration between different repositories.
3.  **Repository Layer**: Leverages Spring Data JPA for abstracting database interactions and providing high-performance queries.
4.  **Model Layer**: Defines the domain entities and their relational mappings (One-to-Many, Many-to-Many).

### 🛡 Security Workflow
`User Register` $\rightarrow$ `User Login` $\rightarrow$ `Server Validates Credentials` $\rightarrow$ `Server Issues JWT` $\rightarrow$ `Client Stores Token` $\rightarrow$ `Client Sends Token in Header` $\rightarrow$ `Server Validates JWT` $\rightarrow$ `Access Granted`

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

### Collaboration Tools
- `POST /api/tasks/{id}/comments` - Post a comment to a task.
- `GET /api/tasks/{id}/comments` - Retrieve task discussion history.
- `POST /api/tasks/{id}/attachments` - Upload a resource file to a task.
- `GET /api/tasks/{id}/attachments` - List all files attached to a task.

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

- **User Journey**:
    1. **Register**: `POST /api/auth/register` $\rightarrow$ Create account.
    2. **Login**: `POST /api/auth/login` $\rightarrow$ Copy the returned **JWT Token**.
    3. **Authorization**: In Postman, go to **Authorization** $\rightarrow$ **Bearer Token** $\rightarrow$ Paste the token.
    4. **Team Creation**: `POST /api/teams` $\rightarrow$ Create a team and copy the `id`.
    5. **Task Creation**: `POST /api/tasks` $\rightarrow$ Create a task using the Team ID.

### 2. Database Inspection (H2 Console)
Verify data persistence visually:
- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:taskmasterdb`
- **Driver**: `org.h2.Driver`
- **User**: `sa` | **Password**: (leave empty)
- **Queries to run**:
  - `SELECT * FROM USERS;`
  - `SELECT * FROM TEAMS;`
  - `SELECT * FROM TASKS;`

---

## 🗺️ Project Structure
```text
src/main/java/com/taskmaster/
├── controller/      # Request handling & API endpoints
├── service/          # Business logic & Orchestration
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
