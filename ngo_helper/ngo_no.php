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

$sql = "SELECT * from ngo_profile";
$result = $con->query($sql);
if ($result->num_rows == 0) {
echo"no records";
}
else{
while($row = $result->fetch_assoc()){


$amount=$row["name"];
echo"$amount";
echo "$$";
$amount=$row["vision"];
echo $amount;
echo "$$";
$amount=$row["reg_id"];
echo $amount;
echo "$$";


}}

 ?>
