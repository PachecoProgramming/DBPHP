<?php 

include "base.php";

if(!empty($_POST['FirstName']) && !empty($_POST['LastName'] && !empty($_POST['Username']) && !empty($_POST['Password']))
{
    $fname = mysql_real_escape_string($_POST['FirstName']);
    $lanem = mysql_real_escape_string($_POST['LastName']);
    $username = mysql_real_escape_string($_POST['Username']);
    $password = md5(mysql_real_escape_string($_POST['Password']));
     
     $checkusername = mysql_query("SELECT * FROM Users WHERE Username = '".$username."'");
      
     if(mysql_num_rows($checkusername) == 1)
     {
        echo "<h1>Error</h1>";
        echo "<p>Sorry, that username is taken. Please go back and try again.</p>";
     }
     else
     {
        $registerquery = mysql_query("INSERT INTO Users (FirstName, LastName, Username, Password) VALUES('".$fname."','".$lname."','".$username."', '".$password."')");
        if($registerquery)
        {
            echo "<h1>Success</h1>";
            echo "<p>Your account was successfully created.</p>";
        }
        else
        {
            echo "<h1>Error</h1>";
            echo "<p>Sorry, your registration failed. Please go back and try again.</p>";    
        }       
     }
}
?>
