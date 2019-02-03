<?php

$db_name="dotslash";
$mysql_user="root";
$mysql_pass="";
$server_name="localhost";

echo "in sql";
$con=mysqli_connect($server_name,$mysql_user,$mysql_pass,$db_name);
if(!$con)
{

 echo "false".mysqli_connect_error();

 }



$reg_id=$_POST["reg_id"];
$password=$_POST["password"];



$sql = "SELECT * from ngo_profile where reg_id='$reg_id' and password='$password'";
//echo $sql;

if(mysqli_query($con,$sql))
 {
   $a="1";
   $sql2 = "SELECT * from ngo_profile where reg_id='$reg_id' and password='$password' and verified='$a'";
   if(mysqli_query($con,$sql2))
   echo "true";
   else {
     echo "NGO not verified";
   }
 }
 else
 {
  echo "false".mysqli_connect_error();

 }
 ?>
