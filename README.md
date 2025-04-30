# 📚 Student Assignment & Grading System


A android-based application that allows **teachers** to upload assignments and assign grades, while **students** can view, download, submit assignments, and check grades — all from a clean, simple interface.

---


## ![image](https://github.com/user-attachments/assets/137bcfc0-fda8-4f4f-9cb9-9015d0b89ae6)
 Download


> [![Download APK](https://img.shields.io/badge/📦%20Download%20EduTracker-blue?style=for-the-badge&logo=android)](https://github.com/Kevil-Gandhi/Student-Grade-And-Assignment-Tracker-App/raw/master/EduTracker.apk)


---

## 🚀 Features

### 👨‍🏫 Teacher Module
- Upload assignments with due dates (PDF format).
- Add assignment guidelines (TXT format).
- View all student submissions by subject & assignment.
- Assign grades via dropdown.
- See status: Graded / Not Graded.

### 👩‍🎓 Student Module
- View list of available assignments by subject & semester.
- See assignment guidelines and due dates.
- Upload submissions (PDF format).
- View submission status and grades.

### 📊 Analytics
- Pie charts to visualize:
  - Submitted vs. Not Submitted
  - Graded vs. Not Graded (per subject)

---

## 🛠️ Tech Stack

| Technology       | Description                       |
|------------------|-----------------------------------|
| 📱 Kotlin        | Android app development           |
| 🐘 PHP           | Server-side scripting             |
| 🔥 Firebase      | User data and real-time syncing   |
| 📁 JSON          | Store metadata and grades         |
| 📂 WebView       | Used in Android app integration   |
| 🎨 HTML/CSS/JS   | Responsive frontend UI            |
| 📊 Chart.js      | Visual data representation        |

---

## 🧩 Folder Structure

```
├── teacher.php               # Upload & grade interface
├── student.php               # View & submit assignments
├── student_submissions/      # All uploaded assignment PDFs
├── assignment_meta.json      # Stores assignment details
├── grades.json               # Stores grades by subject & assignment
├── assets/
│   ├── js/                   # JavaScript files
│   ├── css/                  # Stylesheets
│   └── icons/                # Icons and images
└── android-app/              # Kotlin-based Android modules
```

---

## 📷 Screenshots

> ![SC_001](https://github.com/user-attachments/assets/3fe1de1e-d26b-4b60-872b-6e98e268e1eb)
> ![SC_002](https://github.com/user-attachments/assets/cac60fa2-ba24-41ad-8015-a22e8fc592a8)
> ![SC_006](https://github.com/user-attachments/assets/36fea55b-bce8-4219-9732-7356f1ae4d16)
> ![SC_007](https://github.com/user-attachments/assets/814046c3-3f48-4ccb-8831-6e91b14efee1)
> ![SC_008](https://github.com/user-attachments/assets/c3c7f06d-a36d-4223-9d4e-f5fcccd8aaf5)
> ![SC_010](https://github.com/user-attachments/assets/83d0343c-988b-4a4d-9734-224bfd35b84f)
> ![SC_011](https://github.com/user-attachments/assets/aaf57e8c-b82a-4c9e-9e2e-3aae4a664525)
> ![SC_012](https://github.com/user-attachments/assets/eeecd52b-a227-4324-b2e4-b2db8c979f3f)
> ![SC_014](https://github.com/user-attachments/assets/38192a40-ce84-4f2e-be54-cbe9f17ef594)
> ![SC_015](https://github.com/user-attachments/assets/d901b575-48ae-4b20-af28-e5997164306a)
> ![SC_016](https://github.com/user-attachments/assets/222cfeae-6b8d-424d-bfd8-ded4894030bb)
> ![SC_019](https://github.com/user-attachments/assets/1c732ab4-2de6-419e-8140-e3f32703c8e3)
> ![SC_025](https://github.com/user-attachments/assets/014b15b3-f1fa-4534-9c77-f9995ee3f740)
> ![SC_023](https://github.com/user-attachments/assets/3812da1b-9025-40ed-b078-032d7ad5aefe)
> ![SC_024](https://github.com/user-attachments/assets/dc656b98-e879-42f4-9549-b5d108c4bf9d)



---

## 📦 Setup Instructions

1. Clone this repo:
   ```bash
   git clone https://github.com/Kevil-Gandhi/Student-Grade-And-Assignment-Tracker-App.git
