<?php

$db_name="dotslash";
$mysql_user="root";
$mysql_pass="";
$server_name="localhost";

//echo "in sql";
$con=mysqli_connect($server_name,$mysql_user,$mysql_pass,$db_name);
if(!$con)
{

 echo "false".mysqli_connect_error();

 }



$email=$_POST["email"];
$password=$_POST["password"];



$sql = "SELECT * from user where email='$email' and password='$password'";
//echo $sql;

if(mysqli_query($con,$sql))
 {
   
   echo "true";

 }
 else
 {
  echo "false".mysqli_connect_error();

 }
 ?>
