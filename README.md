# TaskMaster - Collaborative Task Tracking System

TaskMaster is a robust backend system designed to facilitate team collaboration and task management. It allows users to organize work into teams, create and assign tasks, and collaborate through a real-time communication layer.

## 🚀 Features

### User Management & Security
- **Secure Authentication**: JWT-based authentication with stateless session management.
- **Password Security**: Industry-standard BCrypt hashing for all user passwords.
- **Profile Management**: Endpoints to manage and update personal user information.

### Task Management
- **Full CRUD**: Create, Read, Update, and Delete tasks with detailed attributes (title, description, due date, priority).
- **Advanced Querying**: Filter tasks by status or assignee, and search by title or description using dynamic JPA specifications.
- **Task Assignment**: Assign tasks to team members to ensure clear ownership.

### Team Collaboration
- **Project/Team Organization**: Create teams and invite members to collaborate on shared sets of tasks.
- **Discussion Layer**: Add comments to tasks to keep all context in one place.
- **File Attachments**: Upload and associate documents or images with specific tasks.

### 🌟 Advanced Extensions
- **Real-time Notifications**: Integrated WebSocket support to notify users immediately when a task is assigned to them.
- **AI-Powered Descriptions**: Generative AI integration to automatically suggest detailed task descriptions based on the task title.

## 🛠 Tech Stack

- **Language**: Java 17
- **Framework**: Spring Boot 3.3.2
- **Database**: H2 (In-memory for development)
- **Security**: Spring Security, JJWT
- **Build Tool**: Maven
- **Other**: Lombok, Spring Data JPA

## 🛣 API Endpoints

### Authentication
- `POST /api/auth/register` - Register a new account.
- `POST /api/auth/login` - Authenticate and receive a JWT.

### Users
- `GET /api/users/me` - View current user profile.
- `PUT /api/users/me` - Update profile information.

### Teams
- `POST /api/teams` - Create a new team.
- `POST /api/teams/{id}/join` - Join an existing team.
- `GET /api/teams` - List all teams the user belongs to.
- `GET /api/teams/{id}` - Get team details and members.

### Tasks
- `POST /api/tasks` - Create a task within a team.
- `GET /api/tasks` - List tasks (supports `assigneeId`, `status`, `teamId`, and `search` params).
- `GET /api/tasks/{id}` - Get detailed task info.
- `PUT /api/tasks/{id}` - Update task details.
- `DELETE /api/tasks/{id}` - Remove a task.
- `PATCH /api/tasks/{id}/assign` - Assign a user to the task.
- `PATCH /api/tasks/{id}/generate-description` - Generate AI description.

### Collaboration
- `POST /api/tasks/{id}/comments` - Add a comment.
- `GET /api/tasks/{id}/comments` - Fetch all comments.
- `POST /api/tasks/{id}/attachments` - Upload a file.
- `GET /api/tasks/{id}/attachments` - Fetch all attachments.

## ⚙️ Setup and Installation

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd TaskMaster
   ```
2. **Build the project**:
   ```bash
   mvn clean install
   ```
3. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```
4. **H2 Console**:
   Access the database at `http://localhost:8080/h2-console` with JDBC URL `jdbc:h2:mem:taskmasterdb`, username `sa`, and no password.

## 🧪 Testing
Use the provided Postman collection in the `/postman` directory to test the full API flow.
