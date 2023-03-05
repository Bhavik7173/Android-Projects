<?php
	
	include("connection.php");
	

	$Skill=$_REQUEST['skill'];
	$Time=$_REQUEST['time'];
	
	$Per_id=$_REQUEST['id'];
	
	
/*	
	$Skill="Coding";
	$Time="09:00";
	$Per_id="60";
*/
	
	$sql_per_prof="INSERT INTO `skills` (`Per_ID`, `Sk_Skill`, `Sk_Time`) VALUES ('$Per_id', '$Skill', '$Time')";				
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