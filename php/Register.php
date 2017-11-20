<?php
    $con = mysqli_connect("localhost", "id2410211_medlineuser", "uvg2017user", "id2410211_medlinenetwork");

    $name = $_POST["name"];
    $username = $_POST["username"];
    $type =  $_POST["type"];
    $email = $_POST["email"];
    $password = $_POST["password"];

    $statement = mysqli_prepare($con, "INSERT INTO user (user_id, name, username, type, email, password) VALUES (NULL, ?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "ssiss", $name, $username, $type, $email, $password);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;

    echo json_encode($response);
?>
<?php
    $con = mysqli_connect("mysql10.000webhost.com", "a3288368_user", "abcd1234", "a3288368_data");

    $name = $_POST["name"];
    $age = $_POST["age"];
    $username = $_POST["username"];
    $password = $_POST["password"];

    $statement = mysqli_prepare($con, "INSERT INTO user (name, username, age, password) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "siss", $name, $username, $age, $password);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;

    echo json_encode($response);
?>
