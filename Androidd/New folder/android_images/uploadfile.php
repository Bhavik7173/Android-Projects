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
		
		echo json_encode("true");
	$name = $_REQUEST['name'];
	$desc = $_REQUEST['desc'];
	$price= $_REQUEST['price'];
	/*$name = 'mahesh';
	$desc = 'desc';
	$price= 'price';
	*/
    $folder="images/";
	$firstimage= 'abcd.jpg';
	
	$firstimage= $_FILES['file']['name'];
	$tempName1= $_FILES['file']['tmp_name'];
	
	move_uploaded_file($tempName1,$folder.$firstimage);
	$img_name = $folder.$firstimage;
	$q = "insert into details values ('$name','$desc',$price,'$img_name')";
	//echo $q;
	
	if ($conn->query($q) === TRUE) {
		  echo json_encode("success");
		} else {
		  echo json_encode("fail");
		}
	}
?> 