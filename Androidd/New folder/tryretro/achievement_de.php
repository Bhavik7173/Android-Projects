<?php
	
	include("connection.php");
	
	
	$Achievement=$_REQUEST['ach'];
	$Rank=$_REQUEST['rank'];
	$Year=$_REQUEST['year'];
	$Per_id=$_REQUEST['id'];
	
	$folder="images/";
	//$firstimage= 'abcd.jpg';

	$firstimage= $_FILES['file']['name'];
	$tempName1= $_FILES['file']['tmp_name'];

	move_uploaded_file($tempName1,$folder.$firstimage);
	$img_name = $folder.$firstimage;
	
	/*
	$img_name= 'abcd.jpg';
	$Year="2018";
	$Rank="5";
	$Achievement="Degree";
	$Per_id="62";*/
	
	//$sql_per_prof="delete  A_Achievement='$Achievement',A_Year='$Year',A_Rank='$Rank',A_Image='$img_name' achievement where Per_ID = '$Per_id'";
	$sql_per_prof="delete  from achievement where Per_ID = '$Per_id'";
				
	echo $sql_per_prof;			
				
	$exe = mysqli_query($con,$sql_per_prof);
		
	
	class AchievementResponse
	{
		public $status;
	}
	
	$x = new AchievementResponse();
		
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