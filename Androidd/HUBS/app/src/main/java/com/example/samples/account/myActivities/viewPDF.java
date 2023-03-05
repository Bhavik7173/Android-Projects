package com.example.samples.account.myActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.samples.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.krishna.fileloader.FileLoader;
import com.krishna.fileloader.listener.FileRequestListener;
import com.krishna.fileloader.pojo.FileResponse;
import com.krishna.fileloader.request.FileLoadRequest;

import java.io.File;

public class viewPDF extends AppCompatActivity {

    PDFView pdfView;
    String foldername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_p_d_f);
        pdfView=findViewById(R.id.pdfview);
        Intent intent=getIntent();
        String Type=intent.getStringExtra("Type");
        String EF=intent.getStringExtra("PDF");
        if(Type.equals("Job"))
        {
            foldername="";
        }
        else
        {
            foldername="PDF_Events/";
        }
        String IP=getString(R.string.IP);
        String url=IP+foldername+EF;
        Log.d("url",url);
        FileLoader.with(this)
                .load(url)
                .fromDirectory("PDF Files",FileLoader.DIR_EXTERNAL_PUBLIC)
                .asFile(new FileRequestListener<File>() {
                    @Override
                    public void onLoad(FileLoadRequest request, FileResponse<File> response) {

                            File pdfile=response.getBody();

                            pdfView.fromFile(pdfile)
                                    .password(null)
                                    .defaultPage(0)
                                    .enableSwipe(true)
                                    .swipeHorizontal(false)
                                    .enableDoubletap(true)
                                    .onDraw(new OnDrawListener() {
                                        @Override
                                        public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                                        }
                                    })
                                    .onDrawAll(new OnDrawListener() {
                                        @Override
                                        public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                                        }
                                    })
                                    .onPageError(new OnPageErrorListener() {
                                        @Override
                                        public void onPageError(int page, Throwable t) {
                                            Toast.makeText(viewPDF.this,"Error",Toast.LENGTH_LONG).show();
                                        }
                                    })
                                    .onTap(new OnTapListener() {
                                        @Override
                                        public boolean onTap(MotionEvent e) {
                                            return false;
                                        }
                                    })
                                    .onRender(new OnRenderListener() {
                                        @Override
                                        public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {

                                        }
                                    })
                                    .enableAnnotationRendering(true)
                                    .invalidPageColor(Color.WHITE)
                                    .load();



                    }

                    @Override
                    public void onError(FileLoadRequest request, Throwable t) {
                        Toast.makeText(viewPDF.this,"Unable to load PDF",Toast.LENGTH_LONG).show();
                    }
                });
    }
}