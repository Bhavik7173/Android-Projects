<?php

	include_once('connection.php');
	
	$Per_id=$_REQUEST['id'];
	//$Per_id="60";
	
	$q = "select * from events where Per_ID = '$Per_id'";
	//echo $q;
	$exe = mysqli_query($con,$q);
	$rows = mysqli_num_rows($exe);
	
	class AllEventData
	{
		public $EID;
    public $EN;
    public $ED;
    public $ESD;
    public $EED;
    public $EI;
    public $EF;
    public $EST;
    public $EET;
    public $EL;
    public $like;
    public $dislike;
    public $interest;
    public $ldl;
    public $INI;
		
	}

	$ary = array();
	$i = 0;
	
	if($row>0)
	{
		while($data = mysqli_fetch_assoc($exe))
		{
			$x = new AllEventData();
			$x->EN = $data['E_Title'];
			$x->ED = $data['E_Details'];
			$x->ESD= $data['E_Starting'];
			$x->EED = $data['E_Closing'];
			$x->EI = $data['E_Image'];
			$x->EF = $data['E_File'];
			$x->EST = $data['E_StartTime'];
			$x->EET = $data['E_EndTime'];
			$x->EL = $data['E_Location'];
			$x->like = $data['E_Like'];
			$x->dislike = $data['E_Dislike'];
			$x->interest = $data['E_Interested'];
			$x->ldl = $data['LDL'];
			$x->INI = $data['INI'];
			$ary[$i] = $x;
			$i++;
		}
		$j = json_encode($ary);
		echo $j;
	}
?>