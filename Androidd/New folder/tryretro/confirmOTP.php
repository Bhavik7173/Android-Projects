<?php

	include_once('connection.php');
	
	$email = $_REQUEST['email'];
	
	$num = rand(100000,999999);
	
	class OTP
	{
		public $email;
		public $otp;
	}
	
	$x = new OTP();
	
	$x->email = $email;
	$x->otp = $num;
	
	$j = json_encode($x);
	echo $j;

?>