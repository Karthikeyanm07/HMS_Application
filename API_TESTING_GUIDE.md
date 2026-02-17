# Mock Data for HMS API Testing

This guide provides test data and `curl` commands to test all endpoints.

## 1. Authentication & Users

### 1.1 Create Admin User (Optional / If not seeded)
*Endpoint*: `POST /api/users`
*Payload*:
```json
{
  "username": "admin",
  "password": "adminpassword",
  "role": "ADMIN"
}
```

### 1.2 Login (Get Token)
*Endpoint*: `POST /api/auth/login`
*Payload*:
```json
{
  "username": "admin",
  "password": "adminpassword"
}
```
*Response*:
```json
{
  "token": "eyJhbGciPH..."
}
```
**Note**: Use this token in the `Authorization` header for subsequent requests: `Bearer <token>`.

### 1.3 Create Doctor User
*Endpoint*: `POST /api/users`
*Payload*:
```json
{
  "username": "doctor_john",
  "password": "password123",
  "role": "DOCTOR"
}
```
*Response*: Note the `userId` (e.g., `2`).

### 1.4 Create Patient User
*Endpoint*: `POST /api/users`
*Payload*:
```json
{
  "username": "patient_alice",
  "password": "password123",
  "role": "PATIENT"
}
```
*Response*: Note the `userId` (e.g., `3`).

---

## 2. Doctor Management

### 2.1 Create Doctor Profile
*Endpoint*: `POST /api/doctors/{userId}` (e.g., `/api/doctors/2`)
*Payload*:
```json
{
  "name": "Dr. John Doe",
  "specialization": "Cardiologist",
  "experience": 10
}
```

### 2.2 Get All Doctors
*Endpoint*: `GET /api/doctors`

---

## 3. Patient Management

### 3.1 Create Patient Profile
*Endpoint*: `POST /api/patients/{userId}` (e.g., `/api/patients/3`)
*Payload*:
```json
{
  "name": "Alice Smith",
  "age": 30,
  "gender": "Female",
  "phone": "1234567890",
  "email": "alice@example.com"
}
```

### 3.2 Get All Patients
*Endpoint*: `GET /api/patients`

---

## 4. Appointments

### 4.1 Book Appointment
*Endpoint*: `POST /api/appointments/book`
*Params*:
- `patientId`: 1 (Alice's Patient ID, not User ID)
- `doctorId`: 1 (John's Doctor ID, not User ID)
- `date`: 2026-03-01
- `time`: 10:00:00

*Example URL*:
`http://localhost:8080/api/appointments/book?patientId=1&doctorId=1&date=2026-03-01&time=10:00:00`

### 4.2 Get Appointments
*Endpoint*: `GET /api/appointments`

---

## 5. Billing

### 5.1 Generate Bill
*Endpoint*: `POST /api/bills/generate`
*Params*:
- `appointmentId`: 1
- `amount`: 500.00

*Example URL*:
`http://localhost:8080/api/bills/generate?appointmentId=1&amount=500.00`

---

## 6. Payments

### 6.1 Make Payment
*Endpoint*: `POST /api/payments/pay`
*Params*:
- `billId`: 1
- `method`: CARD

*Example URL*:
`http://localhost:8080/api/payments/pay?billId=1&method=CARD`

---

## Testing Workflow
1.  **Register Admin** -> Login -> Get Token.
2.  **Register Doctor User** -> Create Doctor Profile.
3.  **Register Patient User** -> Create Patient Profile.
4.  **Book Appointment** (using patientId and doctorId returned from profile creation).
5.  **Generate Bill** (using appointmentId).
6.  **Pay Bill** (using billId).
