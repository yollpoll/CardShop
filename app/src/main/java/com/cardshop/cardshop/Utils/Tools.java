package com.cardshop.cardshop.Utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.cardshop.cardshop.BuildConfig;
import com.cardshop.cardshop.R;

import java.io.File;
import java.io.IOException;

public class Tools {
    public static final int PIC_FROM_PHOTO = 11;
    public static final int PIC_FROM_CAMERA = 12;
    public static final int CROP_PHOTO = 13;
    public static Uri imgUri, filrUri;//一个是from provider,一个是from file

    public static void showChoosePicDialog(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View view = LayoutInflater.from(activity).inflate(R.layout.alert_choose_photo, null);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        RelativeLayout rlPhoto = (RelativeLayout) view.findViewById(R.id.rl_photo);
        RelativeLayout rlCamera = (RelativeLayout) view.findViewById(R.id.rl_camera);
        rlCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                File file = activity.getExternalCacheDir();
                if (!file.exists()) {
                    file.mkdirs();
                }
                File imgFile = new File(file, "temp.jpg");
                if (!imgFile.exists()) {
                    try {
                        imgFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                imgUri = FileProvider.getUriForFile(activity, BuildConfig.APPLICATION_ID + ".fileProvider",
                        imgFile);
                filrUri = Uri.fromFile(imgFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
                activity.startActivityForResult(intent, PIC_FROM_CAMERA);

            }
        });
        rlPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                activity.startActivityForResult(intent, PIC_FROM_PHOTO);
            }
        });
    }
}
