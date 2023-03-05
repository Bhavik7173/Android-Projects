<?php	
	
	$servername = "localhost";
	$username = "root";
	$password = "";
	$db = 'upload_download';
	// Create connection
	$conn = new mysqli($servername, $username, $password,$db);

	// Check connection
	if ($conn->connect_error) {
	  die("Connection failed: " . $conn->connect_error);
	}
	else{
	
	class response
	{
		public $name;
		public $desc;
		public $price;
		public $image_name;	
	}
	
	$q = "select * from details";

	$result=mysqli_query($conn,$q);
	$arr = array();
	$i = 0;
	
	$row = mysqli_num_rows($result);
	
	//echo "data".$result;
	  while($row = mysqli_fetch_assoc($result)) {
		 $r = new response();
		 $r->name = $row['name'];
		 $r->desc = $row['desc'];
		 $r->price = $row['price'];
		 $r->image_name = $row['path'];
		 $arr[$i] = $r;
		 $i = $i + 1;
	  }
	  echo json_encode($arr);
	}
?> 