<?php
$submissionsDir = 'student_submissions/';
$submittedFiles = array_diff(scandir($submissionsDir), ['.', '..']);

$allSubjects = isset($_GET['subjects']) ? explode(' ', $_GET['subjects']) : [];
$selectedSubject = isset($_GET['selected']) ? $_GET['selected'] : '';

$assignmentMap = []; // subject => assignment => [students]
$filteredSubmittedFiles = [];

foreach ($submittedFiles as $file) {
    if (pathinfo($file, PATHINFO_EXTENSION) !== 'pdf') continue;

    $parts = explode('_', pathinfo($file, PATHINFO_FILENAME));
    if (count($parts) < 3) continue;

    $fileSubject = $parts[0];
    if (!in_array($fileSubject, $allSubjects)) continue; // only teacher's subjects

    $assignment = $parts[1];
    $student = implode('_', array_slice($parts, 2));

    if (!isset($assignmentMap[$fileSubject])) {
        $assignmentMap[$fileSubject] = [];
    }
    if (!isset($assignmentMap[$fileSubject][$assignment])) {
        $assignmentMap[$fileSubject][$assignment] = [];
    }
    $assignmentMap[$fileSubject][$assignment][] = $student;

    $filteredSubmittedFiles[] = $file;
}

$gradesFile = 'grades.json';
$grades = file_exists($gradesFile) ? json_decode(file_get_contents($gradesFile), true) : [];

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $selectedSubject = $_POST['subject'];
    $assignment = $_POST['assignment'];
    $student = $_POST['student'];
    $grade = $_POST['grade'];

    if (!isset($grades[$selectedSubject])) $grades[$selectedSubject] = [];
    if (!isset($grades[$selectedSubject][$assignment])) $grades[$selectedSubject][$assignment] = [];
    $grades[$selectedSubject][$assignment][$student] = $grade;

    file_put_contents($gradesFile, json_encode($grades, JSON_PRETTY_PRINT));
    echo "<div class='alert alert-success text-center'>Grade '$grade' assigned to $student for $assignment</div>";
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Grade Assignment</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        const dataMap = <?php echo json_encode($assignmentMap); ?>;

        function updateAssignments() {
            const subject = document.getElementById('subject').value;
            const assignmentSelect = document.getElementById('assignment');
            assignmentSelect.innerHTML = '<option value="">Select Assignment</option>';
            if (dataMap[subject]) {
                Object.keys(dataMap[subject]).forEach(assignment => {
                    const opt = document.createElement('option');
                    opt.value = assignment;
                    opt.textContent = assignment;
                    assignmentSelect.appendChild(opt);
                });
            }
            updateStudents();
        }

        function updateStudents() {
            const subject = document.getElementById('subject').value;
            const assignment = document.getElementById('assignment').value;
            const studentSelect = document.getElementById('student');
            studentSelect.innerHTML = '<option value="">Select Student</option>';
            if (dataMap[subject] && dataMap[subject][assignment]) {
                dataMap[subject][assignment].forEach(student => {
                    const opt = document.createElement('option');
                    opt.value = student;
                    opt.textContent = student.replace(/_/g, ' ');
                    studentSelect.appendChild(opt);
                });
            }
        }

        window.onload = function () {
            updateAssignments();
        };
    </script>
</head>
<body>
<div class="container mt-4">

    <h2 class="text-center mb-4">All Submitted Assignment PDFs</h2>
    <div class="row row-cols-1 row-cols-md-2 g-4 mb-5">
        <?php
        if (empty($filteredSubmittedFiles)) {
            echo "<div class='col'><div class='alert alert-danger text-center'>No submissions found.</div></div>";
        } else {
            foreach ($filteredSubmittedFiles as $file) {
                echo '<div class="col">
                        <div class="card shadow-sm p-3">
                            <h6>' . htmlspecialchars($file) . '</h6>
                            <a class="btn btn-primary mt-2" href="' . $submissionsDir . urlencode($file) . '" target="_blank">View PDF</a>
                        </div>
                    </div>';
            }
        }
        ?>
    </div>

    <h2 class="text-center mb-4">Assign Grades</h2>

    <?php if (empty($selectedSubject)): ?>
        <!-- Ask teacher to choose subject -->
        <form method="get" class="border p-4 rounded shadow bg-light mb-5">
            <input type="hidden" name="subject" value="<?php echo htmlspecialchars($_GET['subject'] ?? ''); ?>">
            <input type="hidden" name="subjects" value="<?php echo htmlspecialchars($_GET['subjects'] ?? ''); ?>">
            <div class="mb-3">
                <label for="selected" class="form-label">Select Subject</label>
                <select name="selected" id="selected" class="form-control" required>
                    <option value="">-- Select --</option>
                    <?php
                    foreach ($allSubjects as $subject) {
                        $subject = trim($subject);
                        if (!empty($subject)) {
                            echo "<option value=\"$subject\">$subject</option>";
                        }
                    }
                    ?>
                </select>
            </div>
            <div class="text-center">
                <button class="btn btn-success">Load Assignments</button>
            </div>
        </form>
    <?php else: ?>
        <!-- Show full grade form -->
        <form method="post" class="border p-4 rounded shadow bg-light">
            <div class="mb-3">
                <label for="subject" class="form-label">Subject</label>
                <input type="text" id="subject" name="subject" class="form-control" value="<?php echo $selectedSubject; ?>" readonly onchange="updateAssignments()">
            </div>

            <div class="mb-3">
                <label for="assignment" class="form-label">Assignment</label>
                <select name="assignment" id="assignment" class="form-control" onchange="updateStudents()" required>
                    <option value="">Select Assignment</option>
                </select>
            </div>

            <div class="mb-3">
                <label for="student" class="form-label">Student</label>
                <select name="student" id="student" class="form-control" required>
                    <option value="">Select Student</option>
                </select>
            </div>

            <div class="mb-3">
                <label for="grade" class="form-label">Grade</label>
                <select name="grade" id="grade" class="form-control" required>
                    <option value="">Select Grade</option>
                    <option value="A">A</option>
                    <option value="B">B</option>
                    <option value="C">C</option>
                    <option value="D">D</option>
                </select>
            </div>

            <div class="text-center">
                <button class="btn btn-success" type="submit">Assign Grade</button>
            </div>
        </form>
    <?php endif; ?>

    <?php
    if (!empty($selectedSubject) && isset($assignmentMap[$selectedSubject])) {
        echo "<h2 class='text-center mt-5 mb-3'>Grade Status Overview</h2>";

        foreach ($assignmentMap[$selectedSubject] as $assignment => $students) {
            echo "<h4 class='text-primary mt-4'>Assignment: $assignment</h4>";

            echo "<div class='row'>";
            echo "<div class='col-md-6'>";
            echo "<div class='card border-success mb-4'>";
            echo "<div class='card-header bg-success text-white'>✅ Assigned Grades</div>";
            echo "<ul class='list-group list-group-flush'>";
            $hasAssigned = false;
            foreach ($students as $student) {
                if (isset($grades[$selectedSubject][$assignment][$student])) {
                    $grade = $grades[$selectedSubject][$assignment][$student];
                    echo "<li class='list-group-item'>" . htmlspecialchars(str_replace('_', ' ', $student)) . " - <strong>$grade</strong></li>";
                    $hasAssigned = true;
                }
            }
            if (!$hasAssigned) {
                echo "<li class='list-group-item text-muted'>No grades assigned yet.</li>";
            }
            echo "</ul></div></div>";

            echo "<div class='col-md-6'>";
            echo "<div class='card border-danger mb-4'>";
            echo "<div class='card-header bg-danger text-white'>❌ Not Yet Assigned</div>";
            echo "<ul class='list-group list-group-flush'>";
            $hasUnassigned = false;
            foreach ($students as $student) {
                if (!isset($grades[$selectedSubject][$assignment][$student])) {
                    echo "<li class='list-group-item'>" . htmlspecialchars(str_replace('_', ' ', $student)) . "</li>";
                    $hasUnassigned = true;
                }
            }
            if (!$hasUnassigned) {
                echo "<li class='list-group-item text-muted'>All students graded.</li>";
            }
            echo "</ul></div></div>";
            echo "</div>";
        }
    }
    ?>

</div>
</body>
</html>
