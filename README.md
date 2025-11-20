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

Berikut ini cara melakukan Endpoint API Dengan Postman bisa manual request ke endpoint yang tersedia

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

Dokumentasi Update: Docker Compose dengan Database H2

Siang Mas,
Sudah saya tambahkan database H2 di Docker Compose seperti yang diminta. Sekarang tinggal run and test saja - tidak perlu setup manual apapun! 🚀

Yang Sudah Ditambahkan

Docker Compose Lengkap dengan Database

services:
  employee-db:     # ✅ Database H2
  employee-api:    # ✅ Aplikasi Spring Boot

Fitur yang Tersedia:

✅ Database H2 dalam container terpisah

✅ Data persistence - data tersimpan di Docker volume

✅ Health check endpoint untuk monitoring

✅ Error handling yang comprehensive

Hanya 1 Perintah Ini

docker-compose up --build

H2 Database Console - http://localhost:8082

Username: sa

Password: password

JDBC URL: jdbc:h2:tcp://localhost:1521/~/employeedb

👨‍💻 Developer

Nama: Hanas Bayu Pratama

Posisi: Kadidat Developer Juke

Teknologi: Java, Spring Boot, Docker
