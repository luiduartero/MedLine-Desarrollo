<?php
    $con = mysqli_connect("localhost", "id2402700_josejoescobar", "Jj56934200", "medlinenetwork.000webhostapp.com");
    
    $usuario = $_POST["usuario"];
    $contrasena = $_POST["contrasena"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM user WHERE usuario = ? AND contrasena = ?");
    mysqli_stmt_bind_param($statement, "ss", $usuario, $contrasena);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $id_usuario, $nombre, $usuario, $correo, $contrasena);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["nombre"] = $nombre;
        $response["correo"] = $correo;
        $response["usuario"] = $usuario;
        $response["contrasena"] = $contrasena;
    }
    
    echo json_encode($response);
?>