<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
require 'dbconnect.php';
loginUser();
}
?>
<?php
function loginUser()
{
	global $connect;
    if($connect)
    {
	    $Username = $_POST["Username"];
	    $Password = $_POST["Password"];
	    $response = array();
	    $response["Success"] = false;

        if(!empty($_POST["Username"]) || !empty($_POST["Username"]))
        {
	        $sqlUN = "SELECT * FROM Users WHERE Username= '$Username'";
	        $checkUN = mysqli_fetch_array(mysqli_query($connect,$sqlUN));
        	if(isset($checkUN))
        	{
                $sqlPWD = "SELECT * FROM Users WHERE Password= '$Password'";
                $checkPWD = mysqli_fetch_array(mysqli_query($connect,$sqlPWD));
	            if(isset($checkPWD))
	            {
            		$response["Success"] = true;
		            echo json_encode('Successful Login');
		            return $response["Success"];
		            mysqli_close($connect);

	            }
	            else
	            {
	                echo json_encode('Bad Password');
	                return $response["Success"];
	            }
        	}
            else
            {
                echo json_encode('Bad Username');
                return $response["Success"];
                
            }
        }
        else
        {
            echo json_encode('bad request');
            return $response["Success"];
        }
    }
    else
    {
        echo json_encode('failed to connect to database');
        return $response["Success"];
    }
}
?>
