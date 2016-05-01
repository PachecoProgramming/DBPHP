<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
require 'dbconnect.php';
registerUser();
}
?>
<?php
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
	    $response["Success"] = false;

        	if(!empty($_POST["FirstName"]) && !empty($_POST["LastName"]) && !empty($_POST["Username"]) && !empty($_POST["Password"]))
        	{
		    $sql = "SELECT * FROM Users WHERE username='$username'";
		    $check = mysqli_fetch_array(mysqli_query($connect,$sql));
		    if(isset($check))
                	{
			        echo json_encode('username already exist');
			        return $response["Success"];
		        }
                	else
                	{				
		        	$query = " Insert into Users(FirstName,LastName,Username,Password) values ('$FirstName','$LastName','$Username','$Password');";
			        if(mysqli_query($connect,$sql))
                        	{
                        		echo json_encode('successfully registered');
                                	$response["success"] = true;
                                	return $response["Success"];
                        	}
                        	else
                        	{
                        		echo json_encode('oops! Please try again!');
                                	$response["success"] = false;
                                	return $response["Success"];
                        		
                        	}
		        }
				mysqli_close($connect);
	   	}
	   	else
	   	{
	   		echo json_encode('Bad request');
	   	}
    	}
    	else
    	{
    		echo json_encode('connection to database failed');
    	}
}
?>
