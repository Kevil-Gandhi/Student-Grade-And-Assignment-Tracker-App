<?php
$studentName = isset($_GET['student_name']) ? htmlspecialchars($_GET['student_name']) : '';
$semester = isset($_GET['semester']) ? htmlspecialchars($_GET['semester']) : '';

// Handle file upload
if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_FILES['file'])) {
    $studentName = htmlspecialchars($_POST['student_name']);
    $studentNameClean = str_replace(' ', '_', $studentName);    

    $semester = htmlspecialchars($_POST['semester']);
    $assignmentName = htmlspecialchars($_POST['assignment_name']);

    $uploadDir = 'student_submissions/';
    $fileExtension = pathinfo($_FILES['file']['name'], PATHINFO_EXTENSION);
    $fileName = $assignmentName . "_" . $studentNameClean . "." . $fileExtension;
    $filePath = $uploadDir . $fileName;

    if (move_uploaded_file($_FILES['file']['tmp_name'], $filePath)) {
        echo "<div class='alert alert-success text-center'>Assignment uploaded successfully!</div>";
        echo "<script>
            if (typeof AndroidInterface !== 'undefined') {
                AndroidInterface.onAssignmentUploaded();
            }
        </script>";
    } else {
        echo "<div class='alert alert-danger text-center'>Error uploading file.</div>";
    }
}

// Get all uploaded assignments
$allFiles = array_diff(scandir('uploads/'), array('.', '..'));

// Filter: Match assignments that start with Sem{semester}:
$filteredFiles = array_filter($allFiles, function ($file) use ($semester) {
    return strpos($file, "Sem$semester:") === 0;
});

// Load assignment metadata (due dates and teacher names)
$meta = [];
$metaFile = 'assignment_meta.json';
if (file_exists($metaFile)) {
    $metaContent = file_get_contents($metaFile);
    $meta = json_decode($metaContent, true);
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Semester Assignments</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        const assignmentMeta = <?php echo json_encode($meta); ?>;

        function updateDueDate() {
            const select = document.getElementById('assignment_select');
            const dueDateField = document.getElementById('due_date_field');
            const instructionField = document.getElementById('instruction_field');
            const selectedValue = select.value;

            if (assignmentMeta[selectedValue]) {
                const dueDate = assignmentMeta[selectedValue].due_date || '';
                const instruction = assignmentMeta[selectedValue].content || '';

                dueDateField.value = dueDate;
                instructionField.value = instruction;

                const today = new Date().toISOString().split('T')[0];
                const uploadBtn = document.querySelector('button[type="submit"]');

                if (dueDate < today) {
                    uploadBtn.disabled = true;
                    uploadBtn.textContent = "Submission Closed";
                    uploadBtn.classList.remove('btn-success');
                    uploadBtn.classList.add('btn-secondary');
                } else {
                    uploadBtn.disabled = false;
                    uploadBtn.textContent = "Upload Assignment";
                    uploadBtn.classList.remove('btn-secondary');
                    uploadBtn.classList.add('btn-success');
                }
            } else {
                dueDateField.value = '';
                instructionField.value = '';
            }
        }
    </script>
</head>

<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">Assignments for Semester <?php echo $semester; ?></h2>

    <!-- Assignment List -->
    <div class="row row-cols-1 g-3 mb-5">
        <?php if (empty($filteredFiles)): ?>
            <div class="text-center text-danger">No assignments found for this semester.</div>
        <?php else: ?>
            <?php foreach ($filteredFiles as $file): ?>
                <?php 
                    $nameOnly = trim(pathinfo($file, PATHINFO_FILENAME)); // e.g., Sem5:Subject1_Assignment3
                    $teacherName = 'Unknown Teacher';
                    foreach ($meta as $key => $data) {
                        if (trim($key) === $nameOnly) {
                            $teacherName = $data['teacher'];
                            break;
                        }
                    }
                ?>
                <div class="col">
                    <div class="card shadow-sm p-3">
                        <h5 class="card-title"><?php echo htmlspecialchars($file); ?></h5>
                        <p class="mb-1 text-muted">Teacher: <?php echo htmlspecialchars($teacherName); ?></p>
                        <a href="uploads/<?php echo urlencode($file); ?>" target="_blank" class="btn btn-primary mt-2">Download</a>
                    </div>
                </div>
            <?php endforeach; ?>
        <?php endif; ?>
    </div>

    <!-- Upload Form -->
    <h3 class="text-center mb-4">Submit Assignment</h3>
    <form method="post" enctype="multipart/form-data" class="border p-4 rounded shadow bg-light">
        <div class="mb-3">
            <label>Your Name:</label>
            <input type="text" name="student_name" class="form-control" value="<?php echo $studentName; ?>" readonly>
        </div>

        <div class="mb-3">
            <label>Semester:</label>
            <input type="text" name="semester" class="form-control" value="<?php echo $semester; ?>" readonly>
        </div>

        <div class="mb-3">
            <label>Select Assignment:</label>
            <select name="assignment_name" class="form-control" id="assignment_select" onchange="updateDueDate()" required>
                <option value="">-- Select Assignment --</option>
                <?php foreach ($filteredFiles as $file): ?>
                    <?php $nameOnly = pathinfo($file, PATHINFO_FILENAME); ?>
                    <option value="<?php echo htmlspecialchars($nameOnly); ?>">
                        <?php echo htmlspecialchars($nameOnly); ?>
                    </option>
                <?php endforeach; ?>
            </select>
        </div>

        <div class="mb-3">
            <label>Due Date:</label>
            <input type="text" id="due_date_field" class="form-control" readonly placeholder="Due date will appear here">
        </div>

        <div class="mb-3">
            <label>Assignment Instructions:</label>
            <textarea id="instruction_field" class="form-control" rows="4" readonly placeholder="Instructions will appear here"></textarea>
        </div>

        <div class="mb-3">
            <label>Upload PDF:</label>
            <input type="file" name="file" class="form-control" accept=".pdf" required>
        </div>

        <div class="text-center">
            <button type="submit" class="btn btn-success">Upload Assignment</button>
        </div>
    </form>
</div>
</body>
</html>
