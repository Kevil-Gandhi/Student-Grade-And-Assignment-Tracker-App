<?php
// ==== PIE CHART LOGIC ====
$studentRaw = $_GET['student'] ?? '';
$semester = $_GET['semester'] ?? '';
$studentName = trim($studentRaw);
$studentNameUnderscore = str_replace(' ', '_', $studentName);

$totalAssignments = 0;
$submittedAssignments = 0;
$submittedList = [];
$notSubmittedList = [];

$assignmentDir = 'uploads/';
$submissionDir = 'student_submissions/';

$assignmentFiles = [];

if (is_dir($assignmentDir)) {
    $files = scandir($assignmentDir);
    foreach ($files as $file) {
        if (pathinfo($file, PATHINFO_EXTENSION) === 'pdf') {
            $filenameWithoutExt = pathinfo($file, PATHINFO_FILENAME);

            if (preg_match("/^Sem$semester:[^_]+_Assignment\d+$/", $filenameWithoutExt)) {
                $totalAssignments++;
                $assignmentFiles[] = $filenameWithoutExt;

                $expectedSubmission = $filenameWithoutExt . '_' . $studentNameUnderscore . '.pdf';
                if (file_exists($submissionDir . $expectedSubmission)) {
                    $submittedAssignments++;
                    $submittedList[] = [
                        'title' => $filenameWithoutExt,
                        'file' => $expectedSubmission
                    ];
                } else {
                    $notSubmittedList[] = [
                        'title' => $filenameWithoutExt
                    ];
                }
            }
        }
    }
}

$notSubmittedAssignments = $totalAssignments - $submittedAssignments;

// ==== GRADE DISPLAY LOGIC ====
$gradesFile = 'grades.json';
$grades = file_exists($gradesFile) ? json_decode(file_get_contents($gradesFile), true) : [];

$studentGrades = [];

if (!empty($studentNameUnderscore)) {
    foreach ($grades as $subject => $assignments) {
        foreach ($assignments as $assignment => $students) {
            foreach ($students as $student => $grade) {
                if ($student === $studentNameUnderscore) {
                    $studentGrades[] = [
                        'subject' => $subject,
                        'assignment' => $assignment,
                        'grade' => $grade
                    ];
                }
            }
        }
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Student Stats & Grades</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>

<div class="container mt-4">
  <h2 class="text-center mb-4">Assignment Submission Stats for <?php echo htmlspecialchars($studentName); ?></h2>

  <?php if ($totalAssignments === 0): ?>
    <div class="alert alert-warning text-center">
      No assignments found for Semester <?php echo htmlspecialchars($semester); ?>.
    </div>
  <?php else: ?>
    <div class="text-center mb-4">
      <canvas id="submissionChart" width="300" height="300"></canvas>
    </div>

    <script>
      const ctx = document.getElementById('submissionChart').getContext('2d');
      const submissionChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
          labels: ['Submitted', 'Not Submitted'],
          datasets: [{
            data: [<?php echo $submittedAssignments; ?>, <?php echo $notSubmittedAssignments; ?>],
            backgroundColor: ['#4CAF50', '#F44336']
          }]
        },
        options: {
          responsive: true,
          plugins: {
            legend: { position: 'bottom' }
          }
        }
      });
    </script>

    <!-- Submitted Assignments List -->
    <h4 class="text-success text-center mt-5">Submitted Assignments</h4>
    <?php if (empty($submittedList)): ?>
      <div class="alert alert-secondary text-center">No assignments submitted.</div>
    <?php else: ?>
      <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4 my-3">
        <?php foreach ($submittedList as $item): ?>
          <div class="col">
            <div class="card shadow-sm h-100">
              <div class="card-body text-center">
                <p class="card-text"><strong><?php echo htmlspecialchars($item['title']); ?></strong></p>
                <a href="<?php echo $submissionDir . $item['file']; ?>" target="_blank" class="btn btn-sm btn-outline-success">View Submission</a>
              </div>
            </div>
          </div>
        <?php endforeach; ?>
      </div>
    <?php endif; ?>

    <!-- Not Submitted Assignments List -->
    <h4 class="text-danger text-center mt-5">Not Submitted Assignments</h4>
    <?php if (empty($notSubmittedList)): ?>
      <div class="alert alert-success text-center">All assignments have been submitted.</div>
    <?php else: ?>
      <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4 my-3">
        <?php foreach ($notSubmittedList as $item): ?>
          <div class="col">
            <div class="card shadow-sm h-100">
              <div class="card-body text-center">
                <p class="card-text"><strong><?php echo htmlspecialchars($item['title']); ?></strong></p>
                <span class="text-muted">Submission pending</span>
              </div>
            </div>
          </div>
        <?php endforeach; ?>
      </div>
    <?php endif; ?>
  <?php endif; ?>

  <hr class="my-5">

  <h2 class="text-center mb-4">Grades for <?php echo htmlspecialchars($studentName); ?></h2>

  <?php if (empty($studentNameUnderscore)): ?>
    <div class="alert alert-danger text-center">No student name provided in URL.</div>
  <?php elseif (empty($studentGrades)): ?>
    <div class="alert alert-warning text-center">No grades found for <?php echo htmlspecialchars($studentName); ?>.</div>
  <?php else: ?>
    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
      <?php foreach ($studentGrades as $row): 
        $filename = "{$row['subject']}_{$row['assignment']}_{$studentNameUnderscore}.pdf";
        $filepath = "student_submissions/" . $filename;
      ?>
        <div class="col">
          <div class="card shadow-sm h-100">
            <div class="card-body text-center">
              <h6 class="card-title mb-2 text-primary"><?php echo htmlspecialchars($row['subject']); ?></h6>
              <p class="mb-1"><strong>Assignment:</strong> <?php echo htmlspecialchars($row['assignment']); ?></p>
              <p class="mb-2"><strong>Grade:</strong> <?php echo htmlspecialchars($row['grade']); ?></p>
              <?php if (file_exists($filepath)): ?>
                <a href="<?php echo $filepath; ?>" target="_blank" class="btn btn-sm btn-outline-primary">View Submission</a>
              <?php else: ?>
                <span class="text-muted small">Submission not found.</span>
              <?php endif; ?>
            </div>
          </div>
        </div>
      <?php endforeach; ?>
    </div>
  <?php endif; ?>

</div>
<br><br>
</body>
</html>
