<?php
    $con = mysqli_connect("localhost", "id2410211_medlineuser", "uvg2017user", "id2410211_medlinenetwork");
    $medicina = $_POST["medicina"];

        $statement = mysqli_prepare($con, "SELECT * FROM user WHERE username = ? AND password = ?");
        mysqli_stmt_bind_param($statement, "ss", $username, $password);
	    mysqli_stmt_execute($statement);

	    mysqli_stmt_store_result($statement);
	        mysqli_stmt_bind_result($statement, $userID, $name, $username, $type, $email, $password, $medicina);

	        $response = array();
		    $response["success"] = true;

		    while(mysqli_stmt_fetch($statement)){
			            $response["success"] = true;
				            $response["name"] = $name;
				            $response["username"] = $username;
					            $response["type"] = $type;
					            $response["email"] = $email;
						            $response["password"] = $password;
						            $response["password"] = $medicina;
							        }

		        echo json_encode($response);
?>
