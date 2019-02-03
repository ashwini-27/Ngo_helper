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
$price=$_POST["price"];
$provider=$_POST["provider"];
$contribution=$_POST["contribution"];
$brief=$_POST["brief"];




$sql_query="insert into product (name,price,provider,contribution,brief) values('$name','$price','$provider','$contribution','$brief')";
echo "$sql_query";
if(mysqli_query($con,$sql_query))
 {
   echo "true";
 }
 else
 {
  echo "false".mysqli_connect_error();

 }
 ?>
