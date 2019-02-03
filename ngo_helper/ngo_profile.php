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

$v1="abcd1234";
$sql = "SELECT * from ngo_profile where reg_id='$v1'";
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
echo"$amount";
echo "$$";
$amount=$row["bio"];
echo"$amount";
echo "$$";
$sql1 = "SELECT * from ngo_crowd_funding where org_id='$v1'";
$result1 = $con->query($sql1);
if ($result1->num_rows == 0) {
echo"no records";
}
else {
  
  while ($row1=$result1->fetch_assoc()) {

    $amount=$row1["org_id"];
    echo"$amount";
    echo "$$";

    $amount=$row1["name"];
    echo"$amount";
    echo "$$";
    $amount=$row1["amount"];
    echo"$amount";
    echo "$$";
    $amount=$row1["amount_received"];
    echo"$amount";
    echo "$$";
    $amount=$row1["start_date"];
    echo"$amount";
    echo "$$";
    $amount=$row1["end_date"];
    echo"$amount";
    echo "$$";
    $amount=$row1["bio"];
    echo "$amount";
    echo "$$";
  }
}



}}

 ?>
