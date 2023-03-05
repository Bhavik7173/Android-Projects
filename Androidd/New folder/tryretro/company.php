<?php
	
	include("connection.php");
	
	
	$CName=$_REQUEST['cname'];
	$CDOJ=$_REQUEST['cdoj'];
	$CDOL=$_REQUEST['cdol'];
	$CPost=$_REQUEST['cp'];
	$CSalary=$_REQUEST['cs'];
	$Per_id=$_REQUEST['id'];
	
	/*
	$CName="Riya U. Patel";
	$CDOJ="2015-5-6";
	$CDOL="2020-6-8";
	$CPost="Manager";
	$CSalary="25000";
	$Per_id="1";
	*/
	
	$sql_per_prof="INSERT INTO `company` (`Al_ID`, `C_Name`, `C_DOJ`, `C_DOL`, `C_POST`, `C_Salary`) VALUES ('$Per_id', '$CName', '$CDOJ', '$CDOL', '$CPost', '$CSalary')";
				
	//echo $sql_per_prof;			
				
	$exe = mysqli_query($con,$sql_per_prof);
		
	
	class CompanyResponse
	{
		public $status;
	}
	
	$x = new CompanyResponse();
		
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