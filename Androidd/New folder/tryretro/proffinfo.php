<?php
	
	include("connection.php");
	
	
	$Per_Id=$_REQUEST['id'];
	

	//$Per_Id=62;
	
	$sql_per_prof="select Pro_Qualification,Pro_YOP,Pro_Experience,Pro_Link from professional_profile where Per_ID = '$Per_Id'";
				
	//echo $sql_per_prof;			
				
	$exe = mysqli_query($con,$sql_per_prof);
	$row = mysqli_num_rows($exe);
	
	class ProffesionalInfo
	{
		public $Qualification;
		public $YOP;
		public $Experience;
		public $Link1;
		//public $Image;
	}
	
	$x = new ProffesionalInfo();
	
	if($row>0)
	{
		$data = mysqli_fetch_assoc($exe);
		
		$x->Qualification=$data['Pro_Qualification'];
		$x->YOP=$data['Pro_YOP'];
		$x->Experience=$data['Pro_Experience'];
		$x->Link1=$data['Pro_Link'];
		
	}
	else
	{
		$x->Qualification='null';
		$x->YOP='null';
		$x->Experience='null';
		$x->Link1='null';
	}
	$j = json_encode($x);
	echo $j;
?>