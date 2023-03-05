<?php

	class ProProfileResponse
	{
		public $status;
	}
	
	$x = new ProProfileResponse();
	
	include_once('connection.php');
	
	$email = $_REQUEST['email'];
	$pass = $_REQUEST['password'];
	
	//$email = 'riya@gmail.com';
	//$pass = 'riya123';
	
	$q = 'update personal_profile set Per_Password ="'.$pass.'" where Per_Email="'.$email.'"';

	$exe = mysqli_query($con,$q);
	
	if($exe)
	{
		$x->status="success";
	}
	else
	{
		$x->status="fail";
	}
	$j = json_encode($x);
	echo $j;
?> 