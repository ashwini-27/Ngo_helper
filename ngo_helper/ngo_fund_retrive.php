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

$sql = "SELECT * from ngo_crowd_funding";
$result = $con->query($sql);
if ($result->num_rows == 0) {
echo"no records";
}
else{
while($row = $result->fetch_assoc()){

  $org_id=$row['org_id'];
  $project_id=$row['project_id'];
  $name=$row['name'];
  $amount=$row['amount'];
  $amount_rec=$row['amount_recived'];
  $start_date=$row['start_date'];
  $end_date=$row['end_date'];
  echo "$org_id" +"." +"$project_id"+"." + $name+"."+$amount+"."+$amount_rec+"."+"$start_date"+"."+"$end_date";
}}

 ?>
