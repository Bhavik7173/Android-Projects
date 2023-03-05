package com.example.samples.Frags;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.samples.Api;
import com.example.samples.InterestResponse;
import com.example.samples.MyRetrofit;
import com.example.samples.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FragmentHome extends Fragment {

    Context context;
    LinearLayout ll_out,ll_l,ll_r,rl_l,rl_r;
    CardView cardview;
    TextView textview;
    View pb,home;
    NestedScrollView scrollView;
    ProgressBar progressBar;
    LinearLayout.LayoutParams layoutparams,layoutparams1;
    ImageView imageView;
    CardView Jobs,Events,RecentJobs,RecentEvents,PopularJobs,PopularEvents,Alumni,Faculty;
    int size=0;
    int mt=0;
    int mb=0;
    int ml=0;
    int mr=0;
    List<String> title;
    List<String> icon;
    ArrayList<HomeResponse> ar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home,null);

        rl_l = view.findViewById(R.id.rl_l);
        rl_r = view.findViewById(R.id.rl_r);
        ll_l = view.findViewById(R.id.ll_l);
        ll_r = view.findViewById(R.id.ll_r);
        ll_out = view.findViewById(R.id.ll_out);
        scrollView = view.findViewById(R.id.scroll_View);
        progressBar = view.findViewById(R.id.progressbar);
        pb = view.findViewById(R.id.pb);
        home = view.findViewById(R.id.home);
        progressBar.setVisibility(View.VISIBLE);
        pb.setVisibility(View.VISIBLE);
        home.setVisibility(View.GONE);

        title = new ArrayList<>();
        icon = new ArrayList<>();
        ar = new ArrayList<>();

        Retrofit r = MyRetrofit.getRetrofit(context.getString(R.string.IP));
        Call<List<HomeResponse>> call;
        Api api = r.create(Api.class);
        call = api.getHomeData();
        call.enqueue(new Callback<List<HomeResponse>>() {
            @Override
            public void onResponse(Call<List<HomeResponse>> call, Response<List<HomeResponse>> response) {
                if (!response.body().isEmpty()) {
                    ar = (ArrayList<HomeResponse>) response.body();

                    Log.d("myhubs",ar.toString());
                    for (HomeResponse d : ar) {

                        title.add(d.getTitle());
                        icon.add(d.getIcon());
                    }

                    size=title.size();
                    if(size%2==0)
                        size=size/2;
                    else
                        size=(size/2)+1;
                    for(int i =0;i<size;i++) {
                        mt=10;
                        mb=80;
                        ml=0;
                        mr=0;
                        CreateLeftCardViewProgrammatically(title.get(i),icon.get(i),rl_l);
                    }
                    for(int i = size;i<title.size();i++) {
                        mt=210;
                        mb=-120;
                        ml=0;
                        mr=0;
                        if(i==title.size()-1)
                            mb=0;
                        CreateLeftCardViewProgrammatically(title.get(i),icon.get(i),rl_r);
                    }
                    progressBar.setVisibility(View.GONE);
                    pb.setVisibility(View.GONE);
                    home.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<HomeResponse>> call, Throwable t) {
                Log.d("myhubs",t.toString());
            }
        });

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void CreateLeftCardViewProgrammatically(final String text, final String url, LinearLayout layout) {

        cardview = new CardView(context);

        layoutparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 350
        );

        layoutparams.setMargins(ml, mt, mr, mb);

        cardview.setLayoutParams(layoutparams);

        cardview.setRadius(30);

        cardview.setPreventCornerOverlap(true);

        cardview.setElevation(10f);

        cardview.setBackground(context.getResources().getDrawable(R.drawable.home_gradient_maths));

        cardview.setMaxCardElevation(30);


        imageView = new ImageView(context);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setMinimumHeight(100);
        linearLayout.setPadding(0, 10, 10, 0);
        textview = new TextView(context);
        textview.setText(text);

        textview.setPadding(20, 30, 0, 0);
        imageView.setPadding(90, 0, 0, 0);

        textview.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

        textview.setAllCaps(true);

        textview.setTypeface(textview.getTypeface(), Typeface.NORMAL);

        textview.setTextColor(Color.WHITE);
        //imageView.setImageDrawable(d);
        Log.d("myhubs",this.getString(R.string.IP)+url);
        Glide.with(linearLayout.getContext()).load(this.getString(R.string.IP)+url).error(R.drawable.home_static_image).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }
        }).into(imageView);
        linearLayout.addView(textview);
        linearLayout.addView(imageView);
        cardview.addView(linearLayout);
        layout.addView(cardview);

        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Class c_name = null;
                    String c =  text.substring(text.lastIndexOf(" ")+1);
                    Log.d("myhubsclasses",c+":"+text.toUpperCase());
                    c_name = Class.forName("com.example.samples."+c+"Activity");


                    Intent intent = new Intent(context, c_name);
                    intent.putExtra("data",text.toUpperCase());
                    startActivity(intent);
                }
                catch (Exception e)
                {
                    Toast.makeText(context, "class not found problem", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public FragmentHome(Context context)
    {
        this.context=context;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=getActivity();
    }
}
