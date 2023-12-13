<?php
$conn = mysqli_connect("localhost", "root", "", "mydatabase");

if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

$stmt = $conn->prepare("SELECT `question`, `option1`, `option2`, `option3`, `option4`, `correct` FROM `math_table`");
$stmt->execute();

$stmt->bind_result($question, $option1, $option2, $option3, $option4, $correct);

$questions_array = array();

while ($stmt->fetch()) {
    $temp = array(
        'question' => $question,
        'option1' => $option1,
        'option2' => $option2,
        'option3' => $option3,
        'option4' => $option4,
        'correct_option' => $correct
    );

    array_push($questions_array, $temp);
}

$stmt->close();

$conn->close();

header('Content-Type: application/json');
echo json_encode($questions_array);
?>
