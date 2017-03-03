package com.example.mindrate.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mindrate.R;
import com.example.mindrate.util.HttpUtil;
import com.example.mindrate.util.PreferenceUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class WelcomeFragment extends Fragment {


    private ImageView iv_bingPicImg;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

//        if (Build.VERSION.SDK_INT >= 21) {
//            View decorView = getActivity().getWindow().getDecorView();
//            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            iv_bingPicImg = (ImageView) view.findViewById(R.id.bing_pic_img);
//            String bingPic = PreferenceUtil.getString("bing_pic", null);
//            if (bingPic != null) {
//                Glide.with(this).load(bingPic).into(iv_bingPicImg);
//            } else {
//                loadBingPic();
//            }
//        } else {
//            RelativeLayout rr_mindRate = (RelativeLayout) view.findViewById(R.id.mind_rate_logo_white);
//            rr_mindRate.setVisibility(View.VISIBLE);
//        }

        return view;
    }

    private void loadBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendRequestWithOkHttp(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                PreferenceUtil.commitString("bing_pic", bingPic);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(getActivity()).load(bingPic).into(iv_bingPicImg);
                    }
                });
            }
        });
    }
}
