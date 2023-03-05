<?php
	
	include("connection.php");
	

	$skill=$_REQUEST['skill'];
	$time=$_REQUEST['time'];

	$Per_id=$_REQUEST['id'];
	
	

	/*
	//$skill=$_REQUEST['skill'];
	//$time=$_REQUEST['time'];
	$Per_id="60";
*/
	$sql_per_prof="delete  from skills where Per_ID = '$Per_id'";

				
	//echo $sql_per_prof;			
				
	$exe = mysqli_query($con,$sql_per_prof);
		
	
	class SkillResponse
	{
		public $status;
	}
	
	$x = new SkillResponse();
		
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