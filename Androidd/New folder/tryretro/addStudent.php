<?php

		include("connection.php");
		
		$per_id=$_REQUEST['per_id'];
		$clas=$_REQUEST['class'];
		$div=$_REQUEST['div'];
		$mentor=$_REQUEST['mentor'];
		$status=$_REQUEST['status'];
		
		
	/*	$per_id=3;
		$clas="B";
		$div="D";
		$mentor="sam";
		$status="Faculty";*/

		class ProfileResponse
		{
			public $status;
		}			
		
		$sql_per_prof="insert into student(Per_ID,S_Class,S_Div,S_Mentor,S_Status) values($per_id,'$clas','$div','$mentor','$status')";
					
		//echo $sql_per_prof;			
					
		$exe = mysqli_query($con,$sql_per_prof);
		
			
		
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