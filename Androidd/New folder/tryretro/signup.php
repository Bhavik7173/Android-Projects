<?php
	
	include("connection.php");
	
	$per_prof=$_REQUEST['per_prof'];
	//$per_prof="'Punit','12-02-2021','Male','9724968745','81412156987','Faculty','null','Surat','Coding','Travelling','punit@gmail.com','punit123'";
	//$email='punit@gmail.com';
	$pro_prof=$_REQUEST['pro_prof'];
	$emil=$_REQUEST['email'];
	$status1=$_REQUEST['status'];
	$achievements=$_REQUEST['achievements'];
	$skills=$_REQUEST['skills'];
	$imageString=$_POST['b_img'];
	$at=$email;
	$pos=strpos($at,'@');
	$path=substr($at,0,$pos);
	$upload_path="Images/$path.jpg";
	$status2='Alumini';*/
	
	$sql_per_prof="insert into Personal_Profile(Per_Name,Per_Date_Of_Birth,Per_Gender,Per_Contact_No_1,Per_Contact_No_2,Per_Status,Per_Image,
				Per_Address,Per_Hobby,Per_Interest,Per_Email,Per_Password) values(".$per_prof.")";
	$update="update Personal_Profile set Per_Image='upload_path' where Per_Email='".$email."'";
	
	echo $sql_per_prof;
	
	$sql="select Per_ID from Personal_Profile where Per_Email='$email'";
	
	class signup{
		public $status;
	}
	$res=new signup();
	
	if(mysqli_query($cn,$sql_per_prof))
	{
		if(mysqli_query($cn,$update))
		{
			file_put_contents($upload_path,base64_decode($imageString));
			$res->status="T3";
		}
		$result = mysqli_query($cn,$sql);
		if(mysqli_num_rows($result)>0)
		{
			while($row=myswli_fetch_assoc($result))
			{
				$Per_ID = $row["Per_ID"];
			}
			$res->status="T1";
		}
		else
		{
			$res->status="F1";
		}
		$sql_achievements="insert into achievement(Per_ID,A_Achievement,A_Year,A_Rank) values ($Per_ID,$achievements)";
		$sql_skills="insert into Skills(Per_ID,Sk_Skill,Sk_Time) values ($Per_ID,$skills)";
		
		if(mysqli_query($cn,$sql_achievement))
		{
			if(mysqli_query($cn,$sql_skills))
			{
				$res->status="T";
				if((strcmp($status1,$status2))==0)
				{
					$res->status="T5";
					if((strcmp($status1,$status2))==0)
					{
						$res->status="T5";
						$sql_pro_prof="insert into Professional_Profile(Per_ID,Pro_Qualification,Pro_Experience_Pro_Link) values ($Per_ID,$pro_prof)";
						
						if(mysqli_query($cn,$sql_pro_prof))
						{
							$res->status="T6";
							$sql_alumini="insert into Alumini(Per_ID) values($Per_ID)";
							if(mysqli_query($cn,$sql_alumini))
							{
								$res->status="Success";
							}
							else
							{
								$res->status="Failure";
							}
						}
						else
						{
							$res->status="Professional Profile";
						}
					}
					else
					{
						$res->status="F5";
						$sql_student="insert into Student(Per_ID,S_Class,S_Div,S_Mentor,S_Status) values ($Per_ID,$pro-prof,'Student')";
						if(mysqli_query($cn,$sql_student))
						{
							$res->status="Success";
						}
						else
						{
							$res->status="Failure";
						}
					}
				}
			}
			else
			{
				$res->status="Skill fail";
			}
		}
		else
		{
			$res->status="Ach fail";
		}
	}
$res=json_encode($res);
echo $res;
?>