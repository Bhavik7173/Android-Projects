<?php
	
	class achive
	{
		public $id;
		public $ac_name;
		.
		.
		.
	}
	
	$ary = array();
	$i = 0;
	
	$q = "select * from tabl_name";;
	
	$exe = mysqli_query($con,$q);
	$rows = mysqli_num_rows($exe);
	
	if($row>0)
	{
		while($data = mysqli_fetch_assoc($exe))
		{
			$x = new achive();
			$x->id = $data['ac_id'];
			$x->name = $data['ac_name'];
			.
			.
			
			$ary[$i] = $x;
			$i++;
		}
		$j = json_encode($ary);
		echo $j;
	}
?>

class achive
{
	String id;
	String ac_name;
	.
	.
	.
}

ArrayList<achive> ar_achive;

Call<ArrayList<achive>> call = ri.getAchivment();

call.enque()
{

	respone()
		ar_achive = response.body.toString();
		Adapter ar = new Adpater(ar_achive);
		r_view.setAdapter(ar);
	
	failur()
	
}





