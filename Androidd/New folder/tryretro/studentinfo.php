<?php
	
	include("connection.php");
	
	
	$Per_Id=$_REQUEST['id'];
	

	//$Per_Id=63;
	
	$sql_per_prof="select S_Class,S_Div,S_Mentor from student where Per_ID = '$Per_Id'";
				
	//echo $sql_per_prof;			
				
	$exe = mysqli_query($con,$sql_per_prof);
	$row = mysqli_num_rows($exe);
	
	class StudentInfo
	{
		public $Class1;
		public $Div;
		public $Mentor;
	}
	
	$x = new StudentInfo();
	
	if($row>0)
	{
		$data = mysqli_fetch_assoc($exe);
		
		$x->Class1=$data['S_Class'];
		$x->Div=$data['S_Div'];
		$x->Mentor=$data['S_Mentor'];
		
	}
	else
	{
		$x->Class1="null";
		$x->Div="null";
		$x->Mentor="null";
		
	}
	$j = json_encode($x);
	echo $j;
?>