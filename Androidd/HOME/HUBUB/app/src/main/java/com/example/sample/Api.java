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

    @FormUrlEncoded

    @POST("login.php")
    Call <LoginResponse> userLogin(@Field("email") String Email, @Field("password") String password);

    @FormUrlEncoded
    @POST("signupResponse.php")
    Call <com.example.sample.SignupResponse> check(@Field("email") String Email);

    @FormUrlEncoded
    @POST("change_password.php")
    Call<String> change_password(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("getPassword.php")
    Call<String> getPassword(@Field("email") String email);

    @FormUrlEncoded
    @POST("signup.php")
    Call <ProfileResponse> signup(@Field("per_prof") String Per_Prof, @Field("stu_prof") String Stu_Prof, @Field("pro_prof") String Pro_Prof, @Field("email") String Email, @Field("status") String Status, @Field("image") String image);

    @FormUrlEncoded
    @POST("confirmOTP.php")
    Call<OTP> confirmOTP(@Field("email") String email);

    @FormUrlEncoded
    @POST("signupverify.php")
    Call<OTP> signupverify (@Field("email") String email);

    @FormUrlEncoded
    @POST("company.php")
    Call <CompanyResponse> company(@Field("com_prof") String Com_Prof, @Field("email") String Email);

    @FormUrlEncoded
    @POST("company_ud.php")
    Call <CompanyResponse> company_ud(@Field("cname") String cname, @Field("cdoj") String cdoj, @Field("cdol") String cdol, @Field("cp") String cp, @Field("cs") String cs, @Field("email") String Email, @Field("tbl") String tbl);

    @FormUrlEncoded
    @POST("proprofile_u.php")
    Call <com.example.sample.ProProfileResponse> proprofile_u(@Field("Qua") String Qua, @Field("YOP") String YOP, @Field("Exp") String Exp, @Field("Link") String Link, @Field("email") String Email);

    @FormUrlEncoded
    @POST("studentprofile_u.php")
    Call <com.example.sample.ProProfileResponse> studentprofile_u(@Field("C") String C, @Field("Div") String Div, @Field("Mentor") String Mentor, @Field("email") String Email);

    @FormUrlEncoded
    @POST("perprofile_u.php")
    Call <com.example.sample.ProfileResponse> perprofile_u(@Field("name") String name, @Field("dob") String dob, @Field("phone") String phone, @Field("address") String address, @Field("image") String image, @Field("email") String Email);

    @FormUrlEncoded
    @POST("achievement_ud.php")
    Call <com.example.sample.AchievementResponse> achievement_ud(@Field("ach") String ach, @Field("rank") String rank, @Field("year") String year, @Field("image") String image, @Field("email") String Email, @Field("tbl") String tbl);

    @FormUrlEncoded
    @POST("like.php")
    Call <com.example.sample.LikeResponse> like(@Field("like") int like, @Field("dislike") int dislike, @Field("EID") int EID, @Field("email") String Email, @Field("tbl") String tbl);

    @FormUrlEncoded
    @POST("interest.php")
    Call <InterestResponse> interest(@Field("interest") int interest, @Field("EID") int EID, @Field("email") String Email, @Field("tbl") String tbl);

    @FormUrlEncoded
    @POST("skill.php")
    Call <com.example.sample.SkillResponse> skill(@Field("skill") String Skill, @Field("email") String Email);

    @FormUrlEncoded
    @POST("skill_ud.php")
    Call <com.example.sample.SkillResponse> skill_ud(@Field("skill") String Skill, @Field("time") String Time, @Field("email") String Email, @Field("tbl") String tbl);

    @FormUrlEncoded
    @POST("achievement.php")
    Call <com.example.sample.AchievementResponse> achievement(@Field("ach") String Ach, @Field("email") String Email, @Field("image") String base64, @Field("path") String path);

    @Multipart
    @POST("postevent.php")
    Call <String> postevents(@Query("post_event") String Post_Event, @Query("email") String Email, @Query("image") String base64, @Query("path") String path, @Part MultipartBody.Part file, @Part("filename") RequestBody name);

    @Multipart
    @POST("postevent1.php")
    Call <com.example.sample.PostEventsResponse> postevents1(@Query("EN") String EN, @Query("ED") String ED, @Query("ESD") String ESD, @Query("EED") String EED, @Query("SST") String SST, @Query("SET") String SET, @Query("EL") String EL, @Query("path") String path, @Query("email") String email, @Part MultipartBody.Part file, @Part("pdffile") RequestBody name, @Part MultipartBody.Part image, @Part("imagefile") RequestBody images);


    @FormUrlEncoded
    @POST("postjob.php")
    Call <com.example.sample.PostJobsResponse> postjobs(@Field("post_job") String post_job, @Field("email") String Email, @Field("image") String base64, @Field("path") String path);

    @FormUrlEncoded
    @POST("jobapplication.php")
    Call <com.example.sample.JobApplicationResponse> jobapplication(@Field("job_application") String job_application, @Field("email") String Email, @Field("tbl") String tbl);

    @Multipart
    @POST("postjob1.php")
    Call <com.example.sample.PostJobsResponse> postjobs1(@Query("JT") String JT, @Query("JRE") String JRE, @Query("JD") String JD, @Query("JSD") String JSD, @Query("JED") String JED, @Query("JS") String JS, @Query("JL") String JL, @Part MultipartBody.Part file, @Part("pdffile") RequestBody name, @Part MultipartBody.Part image, @Part("companylogo") RequestBody images, @Query("pdfpath") String pdfpath, @Query("imagepath") String imagepath, @Query("email") String email);


    @FormUrlEncoded
    @POST("rateus.php")
    Call <String> rateus(@Field("rate_us") String rate_us);

    @FormUrlEncoded
    @POST("feedback.php")
    Call <FeedbackResponse> feedback(@Field("feedback") String feedback);

    @FormUrlEncoded
    @POST("userhome.php")
    Call <Userinfo> userinfo(@Field("email") String email);

    @FormUrlEncoded
    @POST("proinfo.php")
    Call <com.example.sample.ProInfo> proinfo(@Field("email") String email);

    @FormUrlEncoded
    @POST("studentinfo.php")
    Call <StudentInfo> studentinfo(@Field("email") String email);

    @FormUrlEncoded
    @POST("something.php")
    Call <List<AllJobData>> getDataJob(@Field("tbl") String tbl, @Field("email") String email);

    @GET("search.php")
    Call<List<AlumniNames>> getContact(@Query("key") String str);

    @FormUrlEncoded
    @POST("search_something_alumni.php")
    Call <AllAlumniData> getDataSearchAlumni(@Field("email") String email);


    @FormUrlEncoded
    @POST("something_events.php")
    Call <List<AllEventData>> getDataEvent(@Field("tbl") String tbl, @Field("email") String email);

    @POST("getHomeData.php")
    Call <List<HomeResponse>> getHomeData();

    @POST("email_send.php")
    Call<com.example.sample.OTP> matchOTP(@Query("email") String Email);

    @POST("forgotPassword.php")
    Call<ForgotPassword> forgotPassword(@Query("email") String Email, @Query("password") String Password);

    @FormUrlEncoded
    @POST("companyprofile.php")
    Call <List<AllCompanyData>> getDataCompany(@Field("tbl") String tbl);

    @FormUrlEncoded
    @POST("skillinfo.php")
    Call <List<AllSkillData>> getDataSkill(@Field("tbl") String tbl);

    @FormUrlEncoded
    @POST("achinfo.php")
    Call <List<AllAchievementData>> getDataAchievement(@Field("tbl") String tbl);

    @FormUrlEncoded
    @POST("something_alumni.php")
    Call <List<AllAlumniData>> getDataAlumni(@Field("tbl") String tbl);

    @FormUrlEncoded
    @POST("something_faculty.php")
    Call <List<AllFacultyData>> getDataFaculty(@Field("tbl") String tbl);

    @FormUrlEncoded
    @POST("contact_us.php")
    Call<String> contact_us(@Field("contact_us") String contact_us);


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
}



