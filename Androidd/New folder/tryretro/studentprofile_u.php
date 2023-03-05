<?php
	
	include("connection.php");
	
	
	$Class1=$_REQUEST['C'];
	$Div=$_REQUEST['Div'];
	$Mentor=$_REQUEST['Mentor'];
	$ID=$_REQUEST['id'];
	
	

	//$email="riya@gmail.com";
	
	$sql_per_prof="update student set S_Class='$Class1',S_Div='$Div',S_Mentor='$Mentor' where Per_ID = '$ID'";
				
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