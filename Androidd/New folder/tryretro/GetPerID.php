<?php
	
	include("connection.php");
	
	
	$email=$_REQUEST['email'];
	

	//$email="punit@gmail.com";
	
	$sql_per_prof="select Per_ID from personal_profile where Per_Email = '$email'";
				
	//echo $sql_per_prof;			
				
	$exe = mysqli_query($con,$sql_per_prof);
	$row = mysqli_num_rows($exe);
	
	class GetDetails
	{
		public $id;
		public $status;
	}
	
	$x = new GetDetails();
	
	if($row>0)
	{
		$data = mysqli_fetch_assoc($exe);
		
		$x->id=$data['Per_ID'];
		$x->status=$data['Per_Status'];
	}
	else
	{
		$x->id='null';
		$x->status='null';
	}
	$j = json_encode($x);
	echo $j;
?>