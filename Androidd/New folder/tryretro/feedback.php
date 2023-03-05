<?php
	
	include("connection.php");
	
	
	$Per_ID=$_REQUEST['ID'];
	$FSubject=$_REQUEST['sub'];
	$FYF=$_REQUEST['YF'];
	
	$sql_per_prof="INSERT INTO `feedback` (`Per_ID`, `F_Subject`, `F_Your_Feedback`) VALUES ('$Per_ID', '$FSubject', '$FYF')";
				
	//echo $sql_per_prof;			
				
	$exe = mysqli_query($con,$sql_per_prof);
		
	
	class FeedbackResponse
	{
		public $status;
	}
	
	$x = new FeedbackResponse();
		
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