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
$v1=$_POST["pid"];
$sql = "SELECT * from product where product_id='$v1'";
$result = $con->query($sql);
if ($result->num_rows == 0) {
echo"no records";
}
else{
while($row = $result->fetch_assoc()){


$amount=$row["name"];
echo"$amount";
echo ".";
$amount=$row["provider"];
echo"$amount";
echo ".";
$amount=$row["price"];
echo"$amount";
echo ".";
$amount=$row["contribution"];
echo"$amount";
echo ".";
$amount=$row["brief"];
echo"$amount";
echo ".";


}}

 ?>
