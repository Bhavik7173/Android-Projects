<?php
	
	include("connection.php");
	
	
	$e_title=$_REQUEST['EN'];
	$e_Details=$_REQUEST['ED'];
	$e_starttime=$_REQUEST['ESD'];
	$e_endtime=$_REQUEST['EED'];
	$e_enddate=$_REQUEST['SET'];
	$e_startdate=$_REQUEST['SST'];
	$e_location=$_REQUEST['EL'];
	$Per_id=$_REQUEST['id'];
	
	$folder1="pdfs/";
	
	$firstimage1= $_FILES['file']['name'];
	$tempName1= $_FILES['file']['tmp_name'];

	move_uploaded_file($tempName1,$folder1.$firstimage1);
	$pdf_name = $folder1.$firstimage1;

	$folder2="images/";
	
	$firstimage2= $_FILES['image']['name'];
	$tempName2= $_FILES['image']['tmp_name'];

	move_uploaded_file($tempName2,$folder2.$firstimage2);
	$img_name = $folder2.$firstimage2;
	
	/*
	$e_title="XYZ";
	$e_Details="ABC";
	$e_starttime="03:15:00";
	$e_endtime="05:15:00";
	$e_enddate="2021-03-22";
	$e_startdate="2021-3-22";
	$e_location="Bhatar";
	$Per_id="62";
	$pdf_name="pdfs/XYZ.pdf";
	$img_name="images/abc.pdf";
	*/
	
	$sql_per_prof="INSERT INTO `post_event` (`Per_ID`, `E_TItle`, `E_Image`, `E_Details`, `E_File`, `E_Starting`, `E_Closing`, `E_StartTime`, `E_EndTime`, `E_Location`) 
	VALUES ('$Per_id', '$e_title', '$img_name', '$e_Details', '$pdf_name', '$e_startdate', '$e_enddate', '$e_starttime', '$e_endtime', '$e_location')";	
	echo $sql_per_prof;			
				
	$exe = mysqli_query($con,$sql_per_prof);
		
	
	class PostEventsResponse
	{
		public $status;
	}
	
	$x = new PostEventsResponse();
		
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