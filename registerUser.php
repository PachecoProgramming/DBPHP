<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
require 'dbconnect.php';
regUser();
}

function regUser()
{
	global $connect;
	$firstname = $_POST["firstname"];
	$lastname = $_POST["lastname"];
	$username = $_POST["username"];
	$password = $_POST["password"];
  $response = array();
	
	if($firstname == '' || $lastname == '' || $username == '' || $password == '')
  {
		echo 'please fill all values';
	}
	else
	{
		$sql = "SELECT * FROM Users WHERE username='$username'";
		$check = mysqli_fetch_array(mysqli_query($connect,$sql));
		if(isset($check))
		{
			echo 'username already exist';
		}
		else
		{				
			$sql = " INSERT into Users(firstname,lastname,username,password) values ('$firstname','$lastname','$username','$password');";
			if(mysqli_query($connect,$sql))
			{
			        $response["success"] = true;
			        echo 'Successfully Registered';
			}
			else
			{
			  $response["success"] = false;
				echo 'oops! Please try again!';
			}
		}
		mysqli_close($connect);
	}
}

?>
