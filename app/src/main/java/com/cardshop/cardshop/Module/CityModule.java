package com.cardshop.cardshop.Module;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.cardshop.cardshop.R;
import com.cardshop.cardshop.Utils.LogUtil;
import com.yiguo.adressselectorlib.CityInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CityModule implements CityInterface {
    public static final String TABLE_NAME = "com_wjy_db_Adress";
    public static final String CHINA_CODE = "china";


    private String code;
    private String name;
    private String pCode;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    @Override
    public String getCityName() {
        return name;
    }

    public static SQLiteDatabase loadDataFromDb(Context context) {
        String dbDir = "/data"
                + Environment.getDataDirectory().getAbsolutePath() + "/com.cardshop.cardshop/";
        String dbPath = dbDir + "smartdot.db ";// 要把你Raw文件的db保存到sdcard中
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            if (!(new File(dbPath).exists())) {
                new File(dbDir).mkdirs();
                is = context.getResources().openRawResource(R.raw.adressdb); // 你Raw的那个db索引
                fos = new FileOutputStream(dbPath);
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
            }
        } catch (Exception e) {
            LogUtil.Log("数据文件读写失败");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    LogUtil.Log("数据文件读写失败");
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    LogUtil.Log("数据文件读写失败");
                }
            }
        }
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbPath, null);// 这里可以执行你的操作了
        return db;
    }

    public static ArrayList<CityModule> getAllProvince(Context context) {
        return getCity(context, CHINA_CODE);
    }

    public static ArrayList<CityModule> getCity(Context context, String pCode) {
        ArrayList<CityModule> result = new ArrayList<>();
        SQLiteDatabase db = loadDataFromDb(context);
        LogUtil.Log(db.toString());
        //查询部分数据
        Cursor cursor1 = db.rawQuery("select * from " + TABLE_NAME + " where pcode=?", new String[]{pCode});
        cursor1.moveToFirst();
        //循环读取数据
        while (!cursor1.isAfterLast()) {
            //获得当前行的标签
            int nameIndex = cursor1.getColumnIndex("name");
            //获得对应的数据
            String name = cursor1.getString(nameIndex);

            int pcodeIndex = cursor1.getColumnIndex("pcode");
            String pcode = cursor1.getString(pcodeIndex);

            int codeIndex = cursor1.getColumnIndex("code");
            String code = cursor1.getString(codeIndex);

            LogUtil.Log("name:" + name + "pcode: " + pcode);
            //游标移到下一行
            CityModule cityModule = new CityModule();
            cityModule.setName(name);
            cityModule.setCode(code);
            cityModule.setpCode(pcode);
            result.add(cityModule);
            cursor1.moveToNext();
        }
        db.close();
        return result;
    }
}
