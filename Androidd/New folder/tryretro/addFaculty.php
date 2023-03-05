<?php
	
		include("connection.php");
		
		$per_id=$_REQUEST['per_id'];
		$pro_quali=$_REQUEST['u_qualification'];
		$pro_YOP=$_REQUEST['u_yop'];
		$pro_exp=$_REQUEST['u_experience'];
		$pro_link=$_REQUEST['u_link'];

		/*$per_id=50;
		$pro_quali="Bechalor Engineer";
		$pro_YOP="2017";
		$pro_exp="4th Year";
		$pro_link="XYZ";*/
	
	
	class ProfileResponse
		{
			public $status;
		}
					
		
		$sql_per_prof="insert into professional_profile(Per_ID,Pro_Qualification,Pro_YOP,Pro_Experience,Pro_Link) values($per_id,'$pro_quali','$pro_YOP','$pro_exp','$pro_link')";
					
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