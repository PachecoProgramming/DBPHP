<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
require 'dbconnect.php';
registerUser();
}

function registerUser()
{
	global $connect;
    if($connect)
    {
	    $FirstName = $_POST["FirstName"];
	    $LastName = $_POST["LastName"];
	    $Username = $_POST["Username"];
	    $Password = $_POST["Password"];
        $response = array();

        if(!empty($_POST["FirstName"]) && !empty($_POST["LastName"]) && !empty($_POST["Username"]) && !empty($_POST["Password"]))
        {

		    $sql = "SELECT * FROM Users WHERE username='$username'";
		    $check = mysqli_fetch_array(mysqli_query($connect,$sql));
		    if(isset($check))
                {
			        echo 'username already exist';
		        }
                else
                {				
		        	$query = " Insert into Users(FirstName,LastName,Username,Password) values ('$FirstName','$LastName','$Username','$Password');";
			        if(mysqli_query($connect,$sql))
                        {
			                	echo 'successfully registered';
                                $response["success"] = true;
			            }
                        else
                        {
				                echo 'oops! Please try again!';
                                $response["success"] = false;
			            }
		        }
		mysqli_close($connect);
	   }
    }
}
else
{
    echo 'connection to database failed';
}
?>
