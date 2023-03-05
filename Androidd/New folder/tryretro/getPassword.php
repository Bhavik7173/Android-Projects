<?php

	class ProfileResponse
	{
		public $status;
	}
	
	include_once('connection.php');
	
	$email = $_REQUEST['email'];
	//$email = 'riya@gmail.com';
	
	$q = 'select Per_Password from personal_profile where Per_Email="'.$email.'"';

	$exe = mysqli_query($con,$q);
	
	$x = new ProfileResponse();
	
	if(!$exe)
	{
		echo mysqli_error();
	}
	else
	{	
		$row = mysqli_num_rows($exe);
		
		if($row>0)
		{
			$data = mysqli_fetch_assoc($exe);
			$x->status =  $data['Per_Password'];
		}
		else
		{
			$x->status = "fail";
		}
		$j = json_encode($x);
		echo $j;
	}
?> 