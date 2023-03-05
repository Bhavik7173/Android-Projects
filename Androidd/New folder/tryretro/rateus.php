<?php
	
	include("connection.php");
	
	
	$Email=$_REQUEST['email'];
	$Per_ID=$_REQUEST['id'];
	$RRate=$_REQUEST['rating'];
	$RDescription=$_REQUEST['RD'];
	/*
	$Email="riya@gmail.com";
	$Per_ID="62";
	$RRate="3.5";
	$RDescription="ABCDEFG";
	*/
	$sql_per_prof="INSERT INTO `rateus` (`Per_ID`, `Per_Email`, `R_Rate`, `R_Description`) VALUES ('$Per_ID', '$Email', '$RRate', '$RDescription')";
				
	//echo $sql_per_prof;
	
	$exe = mysqli_query($con,$sql_per_prof);
		
	
	class RateUsResponse
	{
		public $status;
	}
	
	$x = new RateUsResponse();
		
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