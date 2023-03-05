<?php

	include_once('connection.php');
	
	//$email = "abc@gmail.com";
	//$pass = "Abc@123456";
	$email = $_REQUEST['email'];
	$pass = $_REQUEST['password'];
	
	$q = 'select Per_Status from personal_profile where Per_Email="'.$email.'" and Per_Password="'.$pass.'"';

//echo $q;
	$exe = mysqli_query($con,$q);
	$row = mysqli_num_rows($exe);


	class loginResponse
	{
		public $status;
	}
	
		$x = new loginResponse();
	
	if($row>0)
	{
		$data = mysqli_fetch_assoc($exe);
		if($data['Per_Status']=='FACULTY')
		{
			$x->status = 'F';
		}
		else if($data['Per_Status']=='STUDENT')
		{
			$x->status = 'S';
		}
	}	
	$j = json_encode($x);
	
	echo $j;
?>