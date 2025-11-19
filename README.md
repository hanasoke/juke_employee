🧩 Employee Management REST API

📋 Deskripsi Proyek
REST API sederhana untuk mengelola data karyawan di perusahaan Juke. Dibangun menggunakan Java 17 + Spring Boot dengan arsitektur MVC, dan dapat dijalankan menggunakan Docker.

🚀 Fitur yang Tersedia
✅ CRUD Operations lengkap untuk data karyawan
✅ Validasi input (email unik, salary > 0, dll)
✅ Global Exception Handling
✅ Dokumentasi API dengan Swagger UI
✅ Docker Support (Dockerfile + docker-compose)
✅ Struktur MVC yang rapi dan terorganisir

🏗️ Struktur Project

src/
├── main/
│   ├── java/com/juke/employee/
│   │   ├── controller/     # EmployeeController (REST endpoints)
│   │   ├── service/        # EmployeeService (business logic)
│   │   ├── repository/     # EmployeeRepository (data access)
│   │   ├── model/          # Employee (entity)
│   │   └── exception/      # GlobalExceptionHandler
│   └── resources/
│       └── application.properties
├── Dockerfile
├── docker-compose.yml
└── pom.xml

📊 Entity Employee
Field	    Type	        Keterangan
id	        Long	        Auto increment
name	    String	        Nama lengkap (wajib)
email	    String	        Email (unik & wajib)
position	String	        Jabatan karyawan (wajib)
salary	    Double	        Gaji karyawan (> 0)
createdAt	LocalDateTime	Waktu data dibuat (auto)

🔌 Endpoint API With Postman 
buat manual request ke endpoint yang tersedia

📖 Get All Employees
Method: GET
URL: /api/employees
Response: 200 OK
[
  {
    "id": 1,
    "name": "Saitama",
    "email": "saitama@gmail.com",
    "position": "PHP Developer",
    "salary": 50000.0,
    "createdAt": "2024-01-15T10:30:00"
  }
]

👤 Get Employee by ID
Method: GET
URL: /api/employees/{id}
Response: 200 OK atau 404 Not Found

➕ Create Employee
Method: POST
URL: /api/employees
Body:
{
  "name": "Saitama",
  "email": "saitama@gmail.com",
  "position": "PHP Developer",
  "salary": 560000
}
Response: 201 Created atau 400 Bad Request

✏️ Update Employee
Method: PUT
URL: /api/employees/{id}
Body: (sama seperti create)
Response: 200 OK atau 400 Bad Request atau 404 Not Found

🗑️ Delete Employee
Method: DELETE
URL: /api/employees/{id}
Response: 200 OK atau 400 Bad Request

🛠️ Teknologi yang Digunakan
Java 17
Spring Boot 3.2.0
Spring Data JPA
H2 Database (in-memory)
Spring Validation
SpringDoc OpenAPI (Swagger)
Docker
Maven

🚀 Cara Menjalankan
Prerequisites
Java 17
Maven 3.6+
Docker (opsional)

Menjalankan dengan Docker

# Build image Docker
docker build -t employee-management .

# Run container
docker run -p 8080:8080 employee-management

# Atau menggunakan docker-compose
docker-compose up --build

📚 Dokumentasi API
Setelah aplikasi berjalan, dokumentasi API dapat diakses melalui:

Swagger UI
http://localhost:8080/swagger-ui.html

OpenAPI JSON
http://localhost:8080/v3/api-docs

🧪 Testing API
Menggunakan cURL

Create Employee:
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Saitama",
    "email": "saitama@gmail.com",
    "position": "PHP Developer",
    "salary": 5000000
  }'

Get All Employees:
curl http://localhost:8080/api/employees

Get Employee by ID:
curl http://localhost:8080/api/employees/1

Update Employee:
curl -X PUT http://localhost:8080/api/employees/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Saitama",
    "email": "saitama@gmail.com",
    "position": "Senior PHP Developer",
    "salary": 6500000
  }'

Delete Employee:
curl -X DELETE http://localhost:8080/api/employees/1

Docker Configuration
Port: 8080
Base Image: OpenJDK 17
Database: H2 (in-memory)

👨‍💻 Developer
Nama: Hanas Bayu Pratama
Posisi: Candidate Developer Juke
Teknologi: Java, Spring Boot, Docker