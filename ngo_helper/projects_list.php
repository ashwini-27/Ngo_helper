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
$pid=$_POST['pid'];
$sql = "SELECT * from ngo_crowd_funding where project_id='$pid'";
$result = $con->query($sql);
if ($result->num_rows == 0) {
echo"no records";
}
else{
while($row = $result->fetch_assoc()){

$amount=$row["org_id"];
echo"$amount";
echo ".";

$amount=$row["name"];
echo"$amount";
echo ".";
$amount=$row["amount"];
echo"$amount";
echo ".";
$amount=$row["amount_received"];
echo"$amount";
echo ".";
$amount=$row["start_date"];
echo"$amount";
echo ".";
$amount=$row["end_date"];
echo"$amount";
echo ".";
$amount=$row["bio"];
echo "$amount";
echo ".";

}}

 ?>
