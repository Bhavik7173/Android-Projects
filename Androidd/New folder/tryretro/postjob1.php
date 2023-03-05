<?php
	
	include("connection.php");
	
	
	$j_title=$_REQUEST['JT'];
	$j_post=$_REQUEST['JRE'];
	//$j_company_logo=$_REQUEST['yow'];
	$j_Details=$_REQUEST['JD'];
	
	$j_Salary=$_REQUEST['JS'];
	$j_opening=$_REQUEST['JSD'];
	$j_closing=$_REQUEST['JED'];
	$j_location=$_REQUEST['JL'];
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
	$j_title="XYZ";
	$j_post="Manager";
	$j_Details="ABC";
	$j_Salary="15000";
	$j_opening="2021-3-31";
	$j_closing="2021-4-25";
	$j_location="Surat";
	$Per_id="62";
	$pdf_name="pdfs/XYZ.pdf";
	$img_name="images/abc.pdf";*/
	
	
	$sql_per_prof="INSERT INTO `post_job` (`Per_ID`, `J_Title`, `J_Post`, `J_CompanyLogo`, `J_Details`, `J_UploadFile`, `J_Salary`, `J_Opening`, `J_Closing`, `J_Location`) VALUES 
	('$Per_id', '$j_title', '$j_post', '$img_name', '$j_Details', '$pdf_name', '$j_Salary', '$j_opening', '$j_closing', '$j_location')";	
	
	//echo $sql_per_prof;			
				
	$exe = mysqli_query($con,$sql_per_prof);
		
	
	class PostJobsResponse
	{
		public $status;
	}
	
	$x = new PostJobsResponse();
		
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