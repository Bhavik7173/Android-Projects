<?php
	$con = mysqli_connect("localhost","root","","image");
	
	$title = $_POST['title'];
	$path = $_POST['path'];
	
	$upload_path="upload/$title.jpg";
	
	$sql = "insert into imge values('$title','$upload_path');";
	
	if(mysqli_query($cn,$sql))
	{
		file_put_content($upload_path,base64_decode($path));
		echo json_encode(array('response'=>"Image Upload Successfully"));
	}
	else
	{
		echo json_encode(array('response'=>"Image Upload Failed"));
	}
?>