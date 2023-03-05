<?php
	
	include("connection.php");
	
	
	$Qualification=$_REQUEST['Qua'];
	$YOP=$_REQUEST['YOP'];
	$Experience=$_REQUEST['Exp'];
	$Link=$_REQUEST['Link'];
	$ID=$_REQUEST['id'];
	
	/*$Qualification="BSC";
	$YOP="2015";
	$Experience="5th Year";
	$Link="www.ABC.com";
	$ID="riya@gmail.com";
	*/

	//$email="riya@gmail.com";
	
	$sql_per_prof="update professional_profile set Pro_Qualification='$Qualification',Pro_YOP='$YOP',Pro_Experience='$Experience',Pro_Link='$Link' where Per_ID = '$ID'";
				
	//echo $sql_per_prof;			
				
	$exe = mysqli_query($con,$sql_per_prof);
		
	
	class ProProfileResponse
	{
		public $status;
	}
	
	$x = new ProProfileResponse();
		
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