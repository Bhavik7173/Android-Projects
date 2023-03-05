<?php

	include_once('connection.php');
	
	$email = $_REQUEST['email'];
	
	$q = 'insert into personal_profile (Per_Email) values("'.$email.'")'; 

	$exe = mysqli_query($con,$q);

	class SignupResponse
	{
		public $response;
	}

	$x = new SignupResponse();
	
	if($exe)
	{
		$x->response = 'true';
	}
	else
	{
		$x->response = 'false';
	}
	
	$j = json_encode($x);
	
	echo $j;
?>