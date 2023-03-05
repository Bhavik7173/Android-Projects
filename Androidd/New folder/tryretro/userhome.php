<?php
	
	include("connection.php");
	
	
	$email=$_REQUEST['email'];
	

	//$email="riya@gmail.com";
	
	$sql_per_prof="select Per_Name,Per_Date_Of_Birth,Per_Contact_No_1,Per_Address,Per_Image,Per_Email from personal_profile where Per_Email = '$email'";
				
	//echo $sql_per_prof;			
				
	$exe = mysqli_query($con,$sql_per_prof);
	$row = mysqli_num_rows($exe);
	
	class GetDetails
	{
		public $PE;
		public $PN;
		public $PI1;
		public $PC;
		public $PA;
		public $DOB;
	}
	
	$x = new GetDetails();
	
	if($row>0)
	{
		$data = mysqli_fetch_assoc($exe);
		
		$x->PN=$data['Per_Name'];
		$x->PC=$data['Per_Contact_No_1'];
		$x->PA=$data['Per_Address'];
		$x->DOB=$data['Per_Date_Of_Birth'];
		$x->PE=$data['Per_Email'];
		$x->PI1=$data['Per_Image'];
		
	}
	else
	{
		$x->PN='null';
		$x->PC='null';
		$x->PA='null';
		$x->DOB='null';
		$x->PE='null';
		$x->PI1='null';
	}
	$j = json_encode($x);
	echo $j;
?>