<?php
	
	include("connection.php");

	

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