# MyAuthServer

A comprehensive OAuth2 Authorization Server built with Spring Boot, providing secure authentication and authorization services for web applications.

## 🚀 Features

- **OAuth2 Authorization Server** with OpenID Connect (OIDC) support
- **User Registration** API endpoint
- **JWT Token Management** with RSA encryption
- **Secure Password Encoding** using BCrypt
- **CORS Configuration** for cross-origin requests
- **H2 Database** for development (configurable for production)
- **Docker Support** for easy deployment
- **Spring Security Integration** with role-based access control

## 📋 Prerequisites

- Java 17 or higher
- Gradle 7+ (or use the included Gradle Wrapper)
- Docker (for containerized deployment)

## 🛠️ Installation

### Local Development Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/henrisco82/myAuthServer.git
   cd myAuthServer
   ```

2. **Build the application:**
   ```bash
   ./gradlew build
   ```

3. **Run the application:**
   ```bash
   ./gradlew bootRun
   ```

The server will start on `http://localhost:9000`

### Docker Deployment

1. **Build the Docker image:**
   ```bash
   docker build -t myauthserver .
   ```

2. **Run with Docker:**
   ```bash
   docker run -p 9000:9000 myauthserver
   ```

### Deploy to Render

1. **Create a PostgreSQL Database:**
   - In Render dashboard, create a new PostgreSQL database
   - Name it `myauthserver-db` (or update `render.yaml` accordingly)
   - Choose your plan (Starter is fine for development)

2. **Create the Web Service:**
   - Connect your GitHub repository to Render
   - Create a new Web Service
   - Select "Docker" as the runtime
   - Set the port to `9000`
   - The database connection will be automatically configured via `render.yaml`

3. **Deploy!**
   - Render will automatically link the database and set environment variables

## 📖 API Documentation

### Authentication Endpoints

#### User Registration
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "johndoe",
  "email": "john@example.com",
  "password": "securepassword"
}
```

**Response (Success - 201 Created):**
```json
{
  "message": "User registered successfully",
  "username": "johndoe"
}
```

**Response (Error - 400 Bad Request):**
```json
{
  "error": "Username already exists"
}
```

### OAuth2 Endpoints

The server provides standard OAuth2 endpoints:

- **Authorization Endpoint:** `/oauth2/authorize`
- **Token Endpoint:** `/oauth2/token`
- **JWKS Endpoint:** `/oauth2/jwks`
- **UserInfo Endpoint:** `/userinfo`
- **Logout Endpoint:** `/connect/logout`

### Default OAuth2 Client Configuration

- **Client ID:** `client`
- **Redirect URI:** `http://localhost:3000`
- **Scopes:** `openid`, `profile`, `read`, `write`
- **Grant Types:** `authorization_code`, `refresh_token`

## 🗄️ Database Configuration

### Development (H2)
The application uses H2 database by default for local development:
- **URL:** `jdbc:h2:file:./data/authdb`
- **Console:** Available at `http://localhost:9000/h2-console`
- **Username:** `sa`
- **Password:** *(empty)*

**Note:** H2 is for development only. Data is stored in a local file and will be lost on application restarts.

### Production (PostgreSQL)
For production deployment, the application automatically switches to PostgreSQL when `SPRING_PROFILES_ACTIVE=production`:

- **Database:** PostgreSQL (managed by Render)
- **Configuration:** Via `application-production.yml`
- **Connection:** Automatically configured through Render environment variables

**Database Setup on Render:**
1. Create a PostgreSQL database in Render
2. Name it `myauthserver-db` (matches `render.yaml`)
3. The connection details are automatically injected as environment variables

**Manual Configuration** (if not using Render):
```yaml
spring:
  datasource:
    url: jdbc:postgresql://your-db-host:5432/authdb
    username: your-username
    password: your-password
  jpa:
    hibernate:
      ddl-auto: validate
```

## 🔧 Configuration

### Application Properties

Key configuration options in `application.yml`:

```yaml
server:
  port: 9000

spring:
  application:
    name: auth-server
  datasource:
    url: jdbc:h2:file:./data/authdb
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

logging:
  level:
    org.springframework.security: DEBUG
```

### Environment Variables

For production deployment:

- `SPRING_PROFILES_ACTIVE`: Set to `production`
- `DATABASE_URL`: Database connection URL
- `DATABASE_USERNAME`: Database username
- `DATABASE_PASSWORD`: Database password
- `CORS_ALLOWED_ORIGINS`: Comma-separated list of allowed CORS origins (default: `http://localhost:3000`)
- `OAUTH2_REDIRECT_URI`: OAuth2 redirect URI for authorization code flow (default: `http://localhost:3000`)
- `OAUTH2_POST_LOGOUT_REDIRECT_URI`: OAuth2 post-logout redirect URI (default: `http://localhost:3000`)
- `OAUTH2_CLIENT_ID`: OAuth2 client identifier (default: `client`)

## 🐳 Docker Configuration

### Dockerfile Features

- **Multi-stage build** for optimized image size
- **Java 17** runtime environment
- **Gradle wrapper** for consistent builds
- **Non-root user** for security

### Build Context

The `.dockerignore` file excludes:
- Build artifacts (`build/`)
- IDE files (`.idea/`, `.vscode/`)
- OS files (`.DS_Store`)
- Logs and temporary files

## 🔐 Security Features

- **BCrypt Password Encoding**
- **JWT Tokens** with RSA key pairs
- **CORS Configuration** for frontend integration
- **CSRF Protection** (disabled for API endpoints)
- **Role-based Access Control**
- **OAuth2 Security Standards**

## 🧪 Testing

Run the test suite:

```bash
./gradlew test
```

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/henry/myauthserver/
│   │   ├── config/
│   │   │   ├── CorsConfig.java
│   │   │   └── SecurityConfig.java
│   │   ├── controller/
│   │   │   └── AuthController.java
│   │   ├── dto/
│   │   │   ├── RegistrationRequest.java
│   │   │   └── RegistrationResponse.java
│   │   ├── entity/
│   │   │   └── AppUser.java
│   │   ├── repository/
│   │   │   └── UserRepository.java
│   │   ├── service/
│   │   │   └── UserService.java
│   │   └── MyAuthServerApplication.java
│   └── resources/
│       └── application.yml
└── test/
    └── java/com/henry/myauthserver/
        └── MyAuthServerApplicationTests.java
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Support

If you encounter any issues or have questions:

1. Check the [Issues](https://github.com/henrisco82/myAuthServer/issues) page
2. Create a new issue with detailed information
3. Include your environment details and error logs

## 🔄 Future Enhancements

- [ ] Multi-factor authentication (MFA)
- [ ] Social login integration
- [ ] Account verification via email
- [ ] Password reset functionality
- [ ] Admin dashboard
- [ ] API rate limiting
- [ ] Audit logging
