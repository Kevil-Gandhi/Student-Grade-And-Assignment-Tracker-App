<?php
$subjectList = isset($_GET['subjects']) ? $_GET['subjects'] : "";
$subjects = array_filter(explode(" ", $subjectList)); // Multiple subjects: "Sem3:Maths Sem3:Science"

$teacherName = isset($_GET['teacher']) ? urldecode($_GET['teacher']) : ""; // Decode name like "John Doe"
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Upload Assignment</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        * { box-sizing: border-box; }

        body {
            font-family: Arial, sans-serif;
            background: #f0f2f5;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
        }

        .container {
            width: 90%;
            max-width: 500px;
            background: #ffffff;
            margin-top: 30px;
            padding: 20px;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }

        select, textarea, input[type="submit"], input[type="file"], input[type="date"] {
            width: 100%;
            padding: 10px;
            margin-top: 8px;
            border-radius: 6px;
            border: 1px solid #ccc;
            font-size: 16px;
        }

        textarea {
            resize: vertical;
        }

        input[type="submit"] {
            background: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            margin-top: 20px;
            transition: background 0.3s ease;
        }

        input[type="submit"]:hover {
            background: #45a049;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Upload Assignment</h2>

    <form action="" method="POST" enctype="multipart/form-data">
        <!-- Hidden teacher name input -->
        <input type="hidden" name="teacher_name" value="<?php echo htmlspecialchars($teacherName); ?>">

        <label for="selected_subject">Select Subject:</label>
        <select name="selected_subject" id="selected_subject" required>
            <?php foreach ($subjects as $subject): ?>
                <option value="<?php echo htmlspecialchars($subject); ?>">
                    <?php echo htmlspecialchars($subject); ?>
                </option>
            <?php endforeach; ?>
        </select>

        <label for="assignment_no">Assignment No.:</label>
        <select name="assignment_no" id="assignment_no" required>
            <option value="1">Assignment 1</option>
            <option value="2">Assignment 2</option>
            <option value="3">Assignment 3</option>
            <option value="4">Assignment 4</option>
        </select>

        <label for="due_date">Due Date:</label>
        <input type="date" name="due_date" id="due_date" required>

        <label for="assignment_content">Assignment Content:</label>
        <textarea name="assignment_content" id="assignment_content" rows="5" placeholder="Write assignment instructions..." required></textarea>

        <label for="assignment_pdf">Upload Assignment PDF:</label>
        <input type="file" name="assignment_pdf" id="assignment_pdf" accept="application/pdf" required>

        <input type="submit" value="Upload Assignment">
    </form>
</div>

<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $subject = $_POST['selected_subject'];
    $assignmentNo = $_POST['assignment_no'];
    $dueDate = $_POST['due_date'];
    $content = htmlspecialchars($_POST['assignment_content']);
    $teacher = htmlspecialchars($_POST['teacher_name']);

    // Ensure uploads directory exists
    $uploadDir = 'uploads/';
    if (!is_dir($uploadDir)) {
        mkdir($uploadDir, 0777, true);
    }

    $assignmentName = $subject . "_Assignment" . $assignmentNo;
    $fileExtension = pathinfo($_FILES['assignment_pdf']['name'], PATHINFO_EXTENSION);
    $fileName = $assignmentName . "." . $fileExtension;
    $targetPath = $uploadDir . $fileName;

    if (move_uploaded_file($_FILES['assignment_pdf']['tmp_name'], $targetPath)) {
        // Save metadata in assignment_meta.json
        $metaFile = 'assignment_meta.json';
        $metaData = [];

        if (file_exists($metaFile)) {
            $metaData = json_decode(file_get_contents($metaFile), true);
        }

        $metaData[$assignmentName] = [
            'due_date' => $dueDate,
            'content' => $content,
            'teacher' => $teacher
        ];

        file_put_contents($metaFile, json_encode($metaData, JSON_PRETTY_PRINT));

        echo "<script>alert('Assignment uploaded successfully!');</script>";
    } else {
        echo "<script>alert('Failed to upload file.');</script>";
    }
}
?>

<script>
    window.addEventListener('DOMContentLoaded', () => {
        const dateInput = document.getElementById('due_date');
        const today = new Date().toISOString().split('T')[0];
        dateInput.min = today;
    });
</script>

</body>
</html>
