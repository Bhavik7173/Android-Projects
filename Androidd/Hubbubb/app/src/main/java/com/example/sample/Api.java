package com.example.sample;

//import com.example.samples.home_task.AllEventData;
//import com.example.samples.home_task.AllJobData;

import com.example.sample.Frags.AllAchievementData;
import com.example.sample.Frags.AllAlumniData;
import com.example.sample.Frags.AllCompanyData;
import com.example.sample.Frags.AllEventData;
import com.example.sample.Frags.AllFacultyData;
import com.example.sample.Frags.AllJobData;
import com.example.sample.Frags.AllSkillData;
import com.example.sample.Frags.HomeResponse;
import com.example.sample.Frags.StudentInfo;
import com.example.sample.NotificationSender.MyResponse;
import com.example.sample.NotificationSender.NotificationSender;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Api
{

    @GET("LoginResponse.php")
    Call <LoginResponse> userLogin(@Query("email") String Email, @Query("password") String password);

    @FormUrlEncoded
    @POST("signupResponse.php")
    Call <SignupResponse> check(@Field("email") String Email);


    @GET("change_password.php")
    Call<ProfileResponse> change_password(@Query("email") String email, @Query("password") String password);


    @GET("getPassword.php")
    Call<ProfileResponse> getPassword(@Query("email") String email);
/*
    @FormUrlEncoded
    @POST("signup.php")
    Call <ProfileResponse> signup(@Field("per_prof") String Per_Prof, @Field("stu_prof") String Stu_Prof, @Field("pro_prof") String Pro_Prof, @Field("email") String Email, @Field("status") String Status, @Field("image") String image);
*/
    @Multipart
    @POST("signup1.php")
    Call <ProfileResponse> signup1(@Query("u_name") String name,@Query("u_dob") String dob,@Query("u_gen") String gen,@Query("u_cn1") String cn1,@Query("u_cn2") String cn2,@Query("u_status") String status,@Part MultipartBody.Part file,@Query("u_address") String addr,@Query("u_hobby") String hobby,@Query("u_interest") String intrest,@Query("u_email") String email,@Query("u_password") String password);
/*
    @Multipart
    @FormUrlEncoded
    @POST("signup1.php")
    Call <ProfileResponse> signup1(@Field("u_name") String name,@Field("u_dob") String dob,@Field("u_gen") String gen,@Field("u_cn1") String cn1,@Field("u_cn2") String cn2,@Field("u_status") String status,@Field("u_image") String image,@Field("u_address") String addr,@Field("u_hobby") String hobby,@Field("u_interest") String intrest,@Field("u_email") String email,@Field("u_password") String password);
*/
    @FormUrlEncoded
    @POST("confirmOTP.php")
    Call<OTP> confirmOTP(@Field("email") String email);

    @FormUrlEncoded
    @POST("signupverify.php")
    Call<OTP> signupverify (@Field("email") String email);
/*
    @FormUrlEncoded
    @POST("company.php")
    Call <CompanyResponse> company(@Field("com_prof") String Com_Prof, @Field("email") String Email);
*/
    @FormUrlEncoded
    @POST("company_ud.php")
    Call <CompanyResponse> company_ud(@Field("cname") String cname, @Field("cdoj") String cdoj, @Field("cdol") String cdol, @Field("cp") String cp, @Field("cs") String cs, @Field("id") String id, @Field("tbl") String tbl);

    @FormUrlEncoded
    @POST("company.php")
    Call <CompanyResponse> company(@Field("cname") String CN, @Field("cdoj") String CDOJ, @Field("cdol") String CDOL, @Field("cp") String CP, @Field("cs") String CS, @Field("id") String id);

    @FormUrlEncoded
    @POST("proprofile_u.php")
    Call <ProProfileResponse> proprofile_u(@Field("Qua") String Qua, @Field("YOP") String YOP, @Field("Exp") String Exp, @Field("Link") String Link, @Field("id") String id);

    @FormUrlEncoded
    @POST("studentprofile_u.php")
    Call <ProProfileResponse> studentprofile_u(@Field("C") String C, @Field("Div") String Div, @Field("Mentor") String Mentor, @Field("id") String id);

    @Multipart
    @POST("perprofile_u.php")
    Call <ProfileResponse> perprofile_u(@Field("name") String name, @Field("dob") String dob, @Field("phone") String phone, @Field("address") String address,@Part MultipartBody.Part file, @Field("email") String Email);

    @Multipart
    @POST("achievement_ud.php")
    Call <AchievementResponse> achievement_ud(@Field("ach") String ach, @Field("rank") String rank, @Field("year") String year, @Part MultipartBody.Part file, @Field("id") String id);

    @Multipart
    @POST("achievement_de.php")
    Call <AchievementResponse> achievement_de(@Field("ach") String ach, @Field("rank") String rank, @Field("year") String year, @Part MultipartBody.Part file, @Field("id") String id);

    @FormUrlEncoded
    @POST("like.php")
    Call <LikeResponse> like(@Field("like") int like, @Field("dislike") int dislike, @Field("EID") int EID, @Field("email") String Email, @Field("tbl") String tbl);

    @FormUrlEncoded
    @POST("interest.php")
    Call <InterestResponse> interest(@Field("interest") int interest, @Field("EID") int EID, @Field("email") String Email, @Field("tbl") String tbl);

    @FormUrlEncoded
    @POST("skill.php")
    Call <SkillResponse> skill(@Field("skill") String Skill,@Field("time") String time, @Field("id") String id);

    @FormUrlEncoded
    @POST("skill_ud.php")
    Call <SkillResponse> skill_ud(@Field("skill") String Skill, @Field("time") String Time, @Field("id") String id);

    @FormUrlEncoded
    @POST("skill_de.php")
    Call <SkillResponse> skill_de(@Field("skill") String Skill, @Field("time") String Time, @Field("id") String id);

    @Multipart
    @POST("achievement.php")
    Call<AchievementResponse> achievement(@Part MultipartBody.Part file, @Query("ach") String Ach, @Query("yow") String yow, @Query("rank") String rank, @Query("id") String id);
    //Call <AchievementResponse> achievement(@Field("ach") String Ach, @Field("email") String Email, @Field("image") String base64, @Field("path") String path);

    @Multipart
    @POST("postevent.php")
    Call <String> postevents(@Query("post_event") String Post_Event, @Query("email") String Email, @Query("image") String base64, @Query("path") String path, @Part MultipartBody.Part file, @Part("filename") RequestBody name);

    @Multipart
    @POST("postevent1.php")
    Call <PostEventsResponse> postevents1(@Part MultipartBody.Part file,  @Part MultipartBody.Part image,@Query("EN") String EN, @Query("ED") String ED, @Query("ESD") String ESD, @Query("EED") String EED, @Query("SST") String SST, @Query("SET") String SET, @Query("EL") String EL, @Query("id") String id);
    //Call <PostEventsResponse> postevents1(@Query("EN") String EN, @Query("ED") String ED, @Query("ESD") String ESD, @Query("EED") String EED, @Query("SST") String SST, @Query("SET") String SET, @Query("EL") String EL, @Query("path") String path, @Query("email") String email, @Part MultipartBody.Part file, @Part("pdffile") RequestBody name, @Part MultipartBody.Part image, @Part("imagefile") RequestBody images);


    /*@FormUrlEncoded
    @POST("postjob.php")
    Call <PostJobsResponse> postjobs(@Field("post_job") String post_job, @Field("email") String Email, @Field("image") String base64, @Field("path") String path);
*/
    @FormUrlEncoded
    @POST("jobapplication_in.php")
    Call <JobApplicationResponse> jobapplication_insert(@Query("jid") String JID,@Query("date") String date,@Query("time") String time, @Field("id") String id);

    @Multipart
    @POST("postjob1.php")
    Call <PostJobsResponse> postjobs1(@Query("JT") String JT, @Query("JRE") String JRE, @Query("JD") String JD, @Query("JSD") String JSD, @Query("JED") String JED, @Query("JS") String JS, @Query("JL") String JL, @Part MultipartBody.Part file, @Part MultipartBody.Part image, @Query("id") String id);
    //Call <PostJobsResponse> postjobs1(@Query("JT") String JT, @Query("JRE") String JRE, @Query("JD") String JD, @Query("JSD") String JSD, @Query("JED") String JED, @Query("JS") String JS, @Query("JL") String JL, @Part MultipartBody.Part file, @Part("pdffile") RequestBody name, @Part MultipartBody.Part image, @Part("companylogo") RequestBody images, @Query("pdfpath") String pdfpath, @Query("imagepath") String imagepath, @Query("id") String id);


    @FormUrlEncoded
    @POST("rateus.php")
    Call <RateUsResponse> rateus(@Field("email") String email,@Field("rating") float rating,@Field("RD") String RD,@Field("id") String id);

    @FormUrlEncoded
    @POST("feedback.php")
    Call <FeedbackResponse> feedback(@Field("ID") String id,@Field("sub") String sub,@Field("YF") String YF);

    @FormUrlEncoded
    @POST("userhome.php")
    Call <Userinfo> userinfo(@Field("email") String email);


    @GET("proffinfo.php")
    Call <ProffesionalInfo> proffinfo(@Query("id") String id);

    @FormUrlEncoded
    @POST("proinfo.php")
    Call <ProInfo> proinfo(@Field("email") String email);

    @FormUrlEncoded
    @POST("studentinfo.php")
    Call <StudentInfo> studentinfo(@Field("id") String id);

    @FormUrlEncoded
    @POST("something.php")
    Call <List<AllJobData>> getDataJob(@Field("tbl") String tbl, @Field("id") String id);

    @GET("search.php")
    Call<List<AlumniNames>> getContact(@Query("key") String str);

    @FormUrlEncoded
    @POST("search_something_alumni.php")
    Call <AllAlumniData> getDataSearchAlumni(@Field("email") String email);


    @FormUrlEncoded
    @POST("something_events.php")
    Call <ArrayList<AllEventData>> getDataEvent(@Field("tbl") String tbl, @Field("id") String id);

    @POST("getHomeData.php")
    Call <ArrayList<HomeResponse>> getHomeData();

    @POST("email_send.php")
    Call<OTP> matchOTP(@Query("email") String Email);

    @POST("forgotPassword.php")
    Call<ForgotPassword> forgotPassword(@Query("email") String Email, @Query("password") String Password);

    @FormUrlEncoded
    @POST("companyprofile.php")
    Call <List<AllCompanyData>> getDataCompany(@Field("tbl") String tbl);
/*
    @FormUrlEncoded
    @POST("skillinfo.php")
    Call <List<AllSkillData>> getDataSkill(@Field("tbl") String tbl);

    @FormUrlEncoded
    @POST("achinfo.php")
    Call <List<AllAchievementData>> getDataAchievement(@Field("tbl") String tbl);
*/
    @FormUrlEncoded
    @POST("something_alumni.php")
    Call <List<AllAlumniData>> getDataAlumni(@Field("tbl") String tbl);

    @FormUrlEncoded
    @POST("something_faculty.php")
    Call <List<AllFacultyData>> getDataFaculty(@Field("tbl") String tbl);

    /*@FormUrlEncoded
    @POST("contact_us.php")
    Call<String> contact_us(@Field("contact_us") String contact_us);
*/

    @POST("notification.php")
    Call<String> insertKey(@Query("fcm") String token);


    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAzeIYEPY:APA91bELAlbOk2pI8lsaBtRzaBjBJ3ByJej-V7b0aRn6Ff5NrK5fjXWq0bsdflCFmv7TApAs2rRHkv-YHVVTKoKA4R10uYP1ilz00QR-0wYDdGbxzPKIzof0EykwxsRIuHn0pBqoOI-g"// Your server key refer to video for finding your server key
            }
    )
    @POST("fcm/send")
    Call<MyResponse> sendNotifcation(@Body NotificationSender body);

    //to read token from database
    @Headers("Content-Type: application/json")
    @GET("get_key.php")
    Call<String> getKey();

    Call<String> getPdf(@Query("EID") String eid);

    @GET("getDetails.php")
    Call<GetDetails> getDetails(@Query("u_email") String email);

    @FormUrlEncoded
    @POST("addStudent.php")
    Call<ProfileResponse> addStudent(@Field("per_id") String get_id,@Field("class") String clas,@Field("div") String div,@Field("mentor") String mentor,@Field("status") String status);

    @FormUrlEncoded
    @POST("addFaculty.php")
    Call<ProfileResponse> addFaculty(@Field("per_id") String get_id, @Field("u_qualification") String qualification,@Field("u_yop") String yop1,@Field("u_experience") String exp1,@Field("u_link") String link1);

    @GET("GetPerID.php")
    Object getPerId(@Query("email") String email);

    @FormUrlEncoded
    @POST("contact_us.php")
    Call<ContactUsResponse> contact_us(@Field("Email") String email,@Field("Name") String name1,@Field("CN") String cn1,@Field("Time") String time,@Field("Problem") String problem1,@Field("Id") String id);

    @FormUrlEncoded
    @POST("AllSkillData.php")
    Call<ArrayList<AllSkillData>> getAllSkillData(@Field("id") String id);

    @FormUrlEncoded
    @POST("AllAchievementData.php")
    Call<ArrayList<AllAchievementData>> getDataAchievement(@Field("id") String id);

    @Multipart
    @POST("postjob.php")
    Call<PostJobsResponse> postjobs(MultipartBody.Part fileupload,@Query("job_title") String JT,@Query("job_post") String JRE, @Query("job_detail") String JD,@Query("application_OD") String JSD,@Query("application_CD") String JED, @Query("job_location") String JS,@Query("job_location") String JL,@Query("id") String id);

    @FormUrlEncoded
    @POST("jobapplication_de.php")
    Call<JobApplicationResponse> jobapplication_de(@Query("jid") String jid,@Query("id") String id);
}



