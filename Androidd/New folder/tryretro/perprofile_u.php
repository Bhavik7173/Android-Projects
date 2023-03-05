<?php
	
	include("connection.php");
	
	
	$Name=$_REQUEST['name'];
	$DOB=$_REQUEST['dob'];
	$Phone=$_REQUEST['phone'];
	$Address=$_REQUEST['address'];
	
	$email=$_REQUEST['email'];
	
	$folder="images/";
	//$firstimage= 'abcd.jpg';

	$firstimage= $_FILES['file']['name'];
	$tempName1= $_FILES['file']['tmp_name'];

	move_uploaded_file($tempName1,$folder.$firstimage);
	$img_name = $folder.$firstimage;

	/*
	$Name="Riya U. Patel";
	$DOB="2005-5-6";
	$Phone="+916958452265";
	$Address="Althan";
	$Image="HII";
	$email="riya@gmail.com";
	*/
	$sql_per_prof="update personal_profile set Per_Name='$Name',Per_Date_Of_Birth='$DOB',Per_Contact_No_1='$Phone',Per_Address='$Address',Per_Image='$img_name' 
				   where Per_Email = '$email'";
				
	//echo $sql_per_prof;			
				
	$exe = mysqli_query($con,$sql_per_prof);
		
	
	class ProfileResponse
	{
		public $status;
	}
	
	$x = new ProfileResponse();
		
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