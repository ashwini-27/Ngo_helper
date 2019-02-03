<?php

$db_name="dotslash";
$mysql_user="root";
$mysql_pass="";
$server_name="localhost";

$con=mysqli_connect($server_name,$mysql_user,$mysql_pass,$db_name);
if(!$con)
{

 echo "false".mysqli_connect_error();

 }

$name=$_POST["name"];
$password=$_POST["password"];
$email=$_POST["email"];
$mob_no=$_POST["mob_no"];
$address=$_POST["address"];
$pin_code=$_POST["pin_code"];




$sql_query="insert into user (name,password,email,mob_no,address,pincode) values('$name','$password','$email','$mob_no','$address','$pin_code')";
//echo "$sql_query";
if(mysqli_query($con,$sql_query))
 {
   echo "true";
 }
 else
 {
  echo "false".mysqli_connect_error();

 }
 ?>
