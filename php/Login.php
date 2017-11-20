<?php
    $con = mysqli_connect("santiagopaiz.000webhostapp.com", "id2410211_medlineuser", "uvg2017user", "id2410211_medlinenetwork");

    $username = $_POST["username"];
    $password = $_POST["password"];

    $statement = mysqli_prepare($con, "SELECT * FROM user WHERE username = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $name, $username, $type, $email, $password);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["name"] = $name;
        $response["username"] = $username;
        $response["type"] = $type;
        $response["email"] = $email;
        $response["password"] = $password;
    }

    echo json_encode($response);
?>
