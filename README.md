# Library Management System API

This project is a Spring Boot application providing a RESTful API to manage a library's books, patrons, and borrowing records. It supports CRUD operations for all entities and is secured with basic authentication.

---

## Features

- **Books**: Add, update, retrieve, and delete book records.
- **Patrons**: Add, update, retrieve, and delete patron records.
- **Borrowing Records**: Track borrowed books and their returns.
- **Authentication**: Basic authentication.

---

## Prerequisites

1. **Java Development Kit (JDK)**: Version 17 or higher.
2. **Maven**: Version 3.6 or higher.
3. **Database**: H2 (default configuration).
4. **REST Client**: Postman, Curl, or similar tools.

---

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/BinaryCodeCrafter/library.git
   cd library
   ```


2. **Build and Run the Application**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. **Access the Application**:
   The API is accessible at `http://localhost:8080`.

---

## Authentication

The API uses basic authentication. Include the following credentials in the `Authorization` header, encoded in Base64:

- **Username**: `admin`
- **Password**: `admin`

Example for `Authorization` header:

Authorization: Basic YWRtaW46YWRtaW4=




---

## API Endpoints

### **Books API**

| Method | Endpoint            | Description                      | Request Body                                                                                                    | Response Example                                                                                   |
|--------|---------------------|----------------------------------|----------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------|
| GET    | `/api/books`        | Retrieve all books               | N/A                                                                                                            | `[ { "id": 1, "title": "Book Title", "author": "Author", "isbn": "123456789", "year": 2023 } ]`    |
| GET    | `/api/books/{id}`   | Retrieve a specific book by ID   | N/A                                                                                                            | `{ "id": 1, "title": "Book Title", "author": "Author", "isbn": "123456789", "year": 2023 }`        |
| POST   | `/api/books`        | Add a new book                   | `{ "title": "Book Title", "author": "Author", "isbn": "123456789", "year": 2023 }`                             | `{ "id": 2, "title": "Book Title", "author": "Author", "isbn": "123456789", "year": 2023 }`        |
| PUT    | `/api/books/{id}`   | Update a specific book by ID     | `{ "title": "Updated Title", "author": "New Author", "isbn": "987654321", "year": 2024 }`                      | `{ "id": 1, "title": "Updated Title", "author": "New Author", "isbn": "987654321", "year": 2024 }` |
| DELETE | `/api/books/{id}`   | Delete a book by ID              | N/A                                                                                                            | `Book with ID 1 deleted successfully.`                                                             |

---

### **Patrons API**

| Method | Endpoint             | Description                      | Request Body                                            | Response Example                              |
|--------|----------------------|----------------------------------|--------------------------------------------------------|----------------------------------------------|
| GET    | `/api/patrons`       | Retrieve all patrons             | N/A                                                    | `[ { "id": 1, "name": "John Doe", "contact": "123-456" } ]` |
| GET    | `/api/patrons/{id}`  | Retrieve a specific patron by ID | N/A                                                    | `{ "id": 1, "name": "John Doe", "contact": "123-456" }`    |
| POST   | `/api/patrons`       | Add a new patron                 | `{ "name": "Jane Doe", "contact": "456-789" }`         | `{ "id": 2, "name": "Jane Doe", "contact": "456-789" }`    |
| PUT    | `/api/patrons/{id}`  | Update a specific patron by ID   | `{ "name": "Updated Name", "contact": "999-999" }`     | `{ "id": 1, "name": "Updated Name", "contact": "999-999" }`|
| DELETE | `/api/patrons/{id}`  | Delete a patron by ID            | N/A                                                    | `Patron with ID 1 deleted successfully.`      |

---

### **Borrowing Records API**

| Method | Endpoint                                 | Description                               | Request Body | Response Example                           |
|--------|-----------------------------------------|-------------------------------------------|--------------|-------------------------------------------|
| POST   | `/api/borrow/{bookId}/patron/{patronId}`| Record a borrowing event                  | N/A          | `Borrowing record created successfully.`  |
| PUT    | `/api/return/{bookId}/patron/{patronId}`| Record the return of a borrowed book      | N/A          | `Book with ID 1 returned by Patron 1.`    |

---

## Expected HTTP Responses

- **200 OK**: Request was successful.
- **201 Created**: Resource was created successfully.
- **400 Bad Request**: Validation failed or input data is invalid.
- **404 Not Found**: Resource with the given ID was not found.
- **401 Unauthorized**: Authentication credentials are missing or incorrect.

---

## Example Usage

### **Retrieve All Books**
Request:
```http
GET /api/books
Authorization: Basic YWRtaW46YWRtaW4=
```

Response:
```json
[
  {
    "id": 1,
    "title": "Effective Java",
    "author": "Joshua Bloch",
    "isbn": "978-0134685991",
    "year": 2018
  }
]
```

### **Add a New Book**
Request:
```http
POST /api/books
Authorization: Basic YWRtaW46YWRtaW4=
Content-Type: application/json

{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "isbn": "978-0132350884",
  "year": 2008
}
```

Response:
```json
{
  "id": 2,
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "isbn": "978-0132350884",
  "year": 2008
}
```

---

## Testing

- Use **Postman** or similar REST clients to test the API endpoints.



