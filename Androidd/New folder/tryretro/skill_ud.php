<?php
	
	include("connection.php");
	

	$skill=$_REQUEST['skill'];
	$time=$_REQUEST['time'];

	$Per_id=$_REQUEST['id'];
	
	
	
	
	/*$skill="Reading";
	$time="13:00PM";
	$Per_id="60";
*/
	
	$sql_per_prof="update skills set Sk_Skill='$skill',Sk_Time='$time' where Per_ID = '$Per_id'";
				
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