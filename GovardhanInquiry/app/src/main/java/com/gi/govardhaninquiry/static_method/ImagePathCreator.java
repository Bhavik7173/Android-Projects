package com.gi.govardhaninquiry.static_method;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ImagePathCreator {
    private static File sfile;
    public static MultipartBody.Part createMultiPartImg(String path, String spdfname,String pPdf)
    {
        sfile = new File(path);// Create a file using the absolute path of the image
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-file"), sfile);
        MultipartBody.Part spdfupload = MultipartBody.Part.createFormData(pPdf, sfile.getName(), requestBody1);
        RequestBody spdfile_request = RequestBody.create(MediaType.parse("text/plain"), spdfname);
        return spdfupload;
    }
}
