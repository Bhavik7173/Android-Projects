<?php
	class ProfileResponse
	{
		public $status;
	}
	try
	{
		include("connection.php");
		
		$name=$_REQUEST['u_name'];
		$dob=$_REQUEST['u_dob'];
		$gen=$_REQUEST['u_gen'];
		$cn1=$_REQUEST['u_cn1'];
		$cn2=$_REQUEST['u_cn2'];
		$status=$_REQUEST['u_status'];
		//$image=$_REQUEST['u_image'];
		$addr=$_REQUEST['u_address'];
		$hobby=$_REQUEST['u_hobby'];
		$interest=$_REQUEST['u_interest'];
		$email=$_REQUEST['u_email'];
		$password=$_REQUEST['u_password'];
		
		$folder="images/";
		//$firstimage= 'abcd.jpg';
	
		$firstimage= $_FILES['file']['name'];
		$tempName1= $_FILES['file']['tmp_name'];
	
		move_uploaded_file($tempName1,$folder.$firstimage);
		$img_name = $folder.$firstimage;
		
	
		/*$name="Mahesh";
		$dob="1998/04/07";
		$gen="Male";
		$cn1="1234567890";
		$cn2="0987654321";
		$status="Student";
		$image="image1.jpeg";
		$addr="Bhatar";
		$hobby="Cricket";
		$interest="Football";
		$email="mahesh@gmail.com";
		$password="mahesh123";*/
		
		$sql_per_prof="insert into Personal_Profile(Per_Name,Per_Date_Of_Birth,Per_Gender,Per_Contact_No_1,Per_Contact_No_2,Per_Status,Per_Image,
					Per_Address,Per_Hobby,Per_Interest,Per_Email,Per_Password) values ('$name','$dob','$gen','$cn1','$cn2','$status','$img_name',
					'$addr','$hobby','$interest','$email','$password')";
					
		//echo $sql_per_prof;
					
		$exe = mysqli_query($con,$sql_per_prof);
		
		
		$x = new ProfileResponse();
		
		if($exe)
		{
			$x->status="success";
		}
		else
		{
			$x->status=$sql_per_prof;
		}
		$j = json_encode($x);
		echo $j;
	}
	catch(Exception $e)
	{
		$x->status=$e;
		$j = json_encode($x);
		echo $j;
	}
?>