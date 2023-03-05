<?php
	include_once("connection.php");
	
	$originalImgName = $_FILES['filename']['name'];
	$tempName = $_FILES['filename']['tmp_name'];
	$folder="uploaderFiles/";
	$url = "https://192.168.43.85/tryretro/".$originalImgName;
	
	if(move_uploaded_file($tempName,$folder.$originalImgName))
	{
		$query = "INSERT INTO  upload_image_video (pathToFile) VALUES ('$URL')";
		if(mysqli_query($con,$query))
		{
			$query = "SELECT * FROM upload_image_video where pathToFIle='$url'";
			$result = mysql_query($con,$query);
			$emparray = array();
			if(mysqli_num_rows($result)>0)
			{
				while($row = mysqli_fetch_assoc($result))
				{
					$emparray[] = $row;
				}
				echo json_encode(array("status"=>"true","message"=>"Successfully files added!" , "data"=> $emparray));
			}
			else
			{
				echo json_encode(array("status"=>"false","message"=>"Failed!"));
			}
		}
		
	}
	else
	{
		echo json_encode(array("status"=>"false","message"=>"Failed!"));
	}
?>