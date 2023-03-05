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
	$CPost="Employee";
	$CSalary="25000";
	$Per_id="1";
	*/
	
	$sql_per_prof="update company set C_Name='$CName',C_DOJ='$CDOJ',C_DOL='$CDOL',C_POST='$CPost',C_Salary='$CSalary' where Al_ID = '$Per_id'";
				
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