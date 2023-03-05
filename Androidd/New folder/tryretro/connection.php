<?php
$con = new mysqli("localhost","root","","hubbub");

// Check connection
if ($con -> connect_errno) {
  echo "Failed to connect to MySQL: " . $con -> connect_error;
  exit();
}
else{
	//echo "Coonection is Successfully..";
}
?> 