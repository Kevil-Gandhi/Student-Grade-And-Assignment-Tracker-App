# 📚 Student Assignment & Grading System

A android-based application that allows **teachers** to upload assignments and assign grades, while **students** can view, download, submit assignments, and check grades — all from a clean, simple interface.

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


---

## 📦 Setup Instructions

1. Clone this repo:
   ```bash
   git clone https://github.com/YOUR_USERNAME/assignment-grading-system.git
