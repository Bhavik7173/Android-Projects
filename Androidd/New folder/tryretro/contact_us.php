<?php
	
	include("connection.php");
	
	
	$Email1=$_REQUEST['Email'];
	$Per_ID=$_REQUEST['Id'];
	$CName=$_REQUEST['Name'];
	$CTime=$_REQUEST['Time'];
	$CProblem=$_REQUEST['Problem'];
	$CContctno=$_REQUEST['CN'];
	
	/*$Email1="riya@gmail.com";
	$Per_ID="62";
	$CName="Riya Patel";
	$CTime="12:00PM - 03:00PM";
	$CProblem="ABCDEFGHXYZ";
	$CContctno="6989552122";
	*/
	
	$sql_per_prof="INSERT INTO `contactus` (`Per_ID`, `Per_Email`,`C_Name`, `C_ContactNo`, `C_Time`, `C_Problem`) VALUES ('$Per_ID', '$Email1','$Name', '$CContctno', '$CTime','$CProblem')";
				
	echo $sql_per_prof;
	
	$exe = mysqli_query($con,$sql_per_prof);
		
	
	class ContactUsResponse
	{
		public $status;
	}
	
	$x = new ContactUsResponse();
		
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