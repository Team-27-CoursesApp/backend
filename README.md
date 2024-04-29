# Backend README

This repository contains the backend code for the Courses application, implemented using Java Spring.

## Setup Instructions

### 1. Clone the Repository
```
git clone https://github.com/Team-27-CoursesApp/backend.git
```

### 2. Configure PostgreSQL Database
Ensure Docker is installed, then use the provided Docker Compose configuration to set up a PostgreSQL database.

Docker Build and Run
```
docker-compose up -d
```
### 3. Build and Start the Application

### 4. Access the Application
The backend API will be available at http://localhost:9091

## Technologies Used

* Java Spring Boot
* Spring Data JPA
* Thymeleaf
* PostgreSQL
* Docker

## Demo JSON

```
1. http://localhost:9091/api/courses/all

[
    {
        "name": "TEST",
        "description": "TEST",
        "lecturer": 1,
        "students": [],
        "category": "AI"
    }
]

2. http://localhost:9091/api/lecturers/all

[
    {
        "fullname": "Test Testing",
        "email": "test@test.com",
        "description": "TEST",
        "teaches": []
    },
    {
        "fullname": "Pero Perovski",
        "email": "pero@pero.com",
        "description": "Dobar description",
        "teaches": [
            1
        ]
    }
]
```

License
This project is licensed under the MIT License.
