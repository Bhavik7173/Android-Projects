<?php
	
	include("connection.php");
	
	
	$Achievement=$_REQUEST['ach'];
	$Rank=$_REQUEST['rank'];
	$Year=$_REQUEST['yow'];
	$Per_id=$_REQUEST['id'];
	
	$folder="images/";
	

	$firstimage= $_FILES['file']['name'];
	$tempName1= $_FILES['file']['tmp_name'];

	move_uploaded_file($tempName1,$folder.$firstimage);
	$img_name = $folder.$firstimage;
	
	/*
	$img_name= 'abcd.jpg';
	$Year="2017";
	$Rank="4";
	$Achievement="Degree";
	$Per_id="60";
*/
	
	$sql_per_prof="INSERT INTO `achievement` (`Per_ID`, `A_Achievement`, `A_Year`, `A_Rank`, `A_Image`) VALUES ('$Per_id', '$Achievement', '$Year', '$Rank', '$img_name')";				
	//echo $sql_per_prof;			
				
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