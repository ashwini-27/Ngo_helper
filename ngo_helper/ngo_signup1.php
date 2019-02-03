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
$vision=$_POST["vision"];
$reg_id=$_POST["reg_id"];
$password=$_POST["password"];
$limit_project_no=$_POST["limit_project_no"];
$limit_project_amt=$_POST["limit_project_amt"];
$email=$_POST["email"];
$mob_no=$_POST["mob_no"];
$website=$_POST["website"];
$bio=$_POST["bio"];
$logo=$_POST["logo"];


$sql_query="insert into ngo_profile (name,password,vision,reg_id,limit_project_no,limit_project_amt,email,mob_no,website,bio,logo) values('$name','$password','$vision','$reg_id','$limit_project_no','$limit_project_amt','$email','$mob_no','$website','$bio','$logo')";
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
