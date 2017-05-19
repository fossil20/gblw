package com.yq.yqpaytest;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Kam Wong Division on 17/5/11.
 */

public class CommonUtil {

    private static InputStream getSourceStream(Context context) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open("lwHub");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    public static void decryptSource(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream inputStream = getSourceStream(context);
                if (inputStream == null) {
                    return;
                }
                try {
                    decrypt(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void decrypt(InputStream in) throws IOException {
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/bbai/";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dirPath + "target.zip");
        FileOutputStream outSteam = new FileOutputStream(file);
        byte[] buffer = new byte[1024 * 1024];
        int i = 0;
        while (in.read(buffer, i, 1) != -1) {
            buffer[i] -= i;
            outSteam.write(buffer, i++, 1);
        }
        outSteam.close();
        in.close();

        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        int count = 0;
        while (zipEntry != null) {
            if (zipEntry.isDirectory()) {
                file = new File(dirPath + zipEntry.getName());
                if (!file.exists()) {
                    file.mkdir();
                }
            } else {
                file = new File(dirPath + zipEntry.getName());
                if (!file.exists()) {
                    file.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    while ((count = zipInputStream.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, count);
                    }
                    fileOutputStream.close();
                }
            }
            zipEntry = zipInputStream.getNextEntry();
        }
        zipInputStream.close();
    }
}
