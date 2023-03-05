<?php
	
	include("connection.php");
	
	$Per_id=$_REQUEST['id'];
	//$Per_id="60";
	
	$q = "select * from skills where Per_ID = '$Per_id'";;
	//echo $q;
	$exe = mysqli_query($con,$q);
	$rows = mysqli_num_rows($exe);
	class AllSkillData
	{
		public $Skill;
		public $Time1;
	}

	$ary = array();
	$i = 0;
	
	if($row>0)
	{
		while($data = mysqli_fetch_assoc($exe))
		{
			$x = new AllSkillData();
			$x->Skill = $data['Skill'];
			$x->Time1 = $data['Time'];
			$ary[$i] = $x;
			$i++;
		}
		$j = json_encode($ary);
		echo $j;
	}
?>