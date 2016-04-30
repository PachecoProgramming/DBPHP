<?php

session_start();

$server_name = "myhost";
$username = "myusername";
$password = "mypassword";
$DB_Name = "mydatabase";
$connect = mysqli_connect($server_name, $username, $password) or die("MySQL Error: " . mysql_error());
$dB = mysql_select_db($DB_Name) or die("MySQL Error: " . mysql_error());

if($connect)
{echo " connection success "; }else{ echo " connection failure "; }
?>
