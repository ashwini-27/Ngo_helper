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


$name=$_POST["user"];
$phone=$_POST["phone"];
$name2=$_POST["number1"];


echo $name;
$sql_query="insert into ngo_profile (vision,email,reg_id) values('$name','$phone','$name2')";
echo $sql_query;
if(mysqli_query($con,$sql_query))
 {
   echo "true";
 }
 else
 {
  echo "false".mysqli_connect_error();

 }
 ?>
