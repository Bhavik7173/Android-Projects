<?php
include("connection.php");

$post_event = $_REQUEST['post_job'];
$email = $_REQUEST['email'];

$sql = "select Per_ID from Personal_Profile where Per_Email='$email'";

class signup{
	public $status;
}

$res = new signup();
$result = mysqli_query($cn,$sql);
if(mysqli_num_rows($result)>0)
{
	while($row = mysqli_fetch_assoc($result))
	{
		$Per_ID = $row["Per_ID"];
	}
	$sql = "insert into job_profile(Per_ID,J_Required_Experience,J_Dtails,J_Salary,J_Status) values ($Per_ID,$post_event,'P')";
	if(mysqli_query($cn,$sql_job))
	{
		$res->status="Success";
	}
	else
	{
		$res->status="Failure";
	}
}
else
{
	$res->status="F1";
}

$res=json_encode($res);
echo $res;

?>