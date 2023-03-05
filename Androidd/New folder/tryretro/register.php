<?php

	include_once('connection.php');
	
	//$email = $_REQUEST['email'];
	$email = "bhavik@gmail.com";
	
	$q = 'insert into personal_profile Per_Email values "'.$email.'"';

	echo $q;
	$exe = mysqli_query($con,$q);
	
	if($exe=="True")
	{
		echo "Inserted Email.";
	}
	else
	{
		echo "Not Inserted Email.";
	}
	/*$j = json_encode($x);
	
	echo $j;*/
?>