<?php
	
	include("connection.php");
	
	//$Per_id=$_REQUEST['id'];
	$Per_id="60";
	
	$q = "select * from achievement where Per_ID = '$Per_id'";;
	echo $q;
	$exe = mysqli_query($con,$q);
	$rows = mysqli_num_rows($exe);
	
	class AllAchievementData
	{
		public $A_Achievement;
		public $A_Year;
		public $A_Rank;
		public $A_Image;
	}

	$ary = array();
	$i = 0;
	
	if($row>0)
	{
		while($data = mysqli_fetch_assoc($exe))
		{
			$x = new AllAchievementData();
			$x->A_Achievement = $data['Achievement'];
			$x->A_Year = $data['Year'];
			$x->A_Rank= $data['Rank'];
			$x->A_Image = $data['Image'];
			$ary[$i] = $x;
			$i++;
		}
		$j = json_encode($ary);
		echo $j;
	}
?>