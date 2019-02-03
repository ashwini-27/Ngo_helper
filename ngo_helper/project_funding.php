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
$start_date=$_POST["start_date"];
$end_date=$_POST["end_date"];
$amount=$_POST["amount"];
$org_id=$_POST["org_id"];
$bio=$_POST["bio"];



$sql_query="insert into ngo_crowd_funding (name,start_date,end_date,amount,org_id,bio) values('$name','$start_date','$end_date','$amount','$org_id','$bio')";
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
