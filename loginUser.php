<?php 

include "dbconnect.php";

if(!empty($_SESSION['LoggedIn']) && !empty($_SESSION['Username']))
{
    echo "<h1User already logged in</h1>";
}
elseif(!empty($_POST['Username']) && !empty($_POST['Password']))
{
    $username = mysql_real_escape_string($_POST['Username']);
    $password = md5(mysql_real_escape_string($_POST['Password']));
     
    $checklogin = mysql_query("SELECT * FROM Users WHERE Username = '".$username."' AND Password = '".$password."'");
     
    if(mysql_num_rows($checklogin) == 1)
    {
        $row = mysql_fetch_array($checklogin);
        $fname = $row['FirstName'];
         
        $_SESSION['Username'] = $username;
        $_SESSION['Password'] = $password;
        $_SESSION['LoggedIn'] = "Success";
         
        echo "<h1>Success</h1>";
    }
    else
    {
        echo "<h1>Error</h1>";
        echo "<p>Sorry, your account could not be found.</p>";
    }
}
?>
