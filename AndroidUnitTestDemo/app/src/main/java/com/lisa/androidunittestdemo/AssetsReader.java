package com.lisa.androidunittestdemo;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

public class AssetsReader {

    private final AssetManager mAssetManager;

    public AssetsReader(Context context) {
        mAssetManager = context.getAssets();
    }

    public String read(String fileName) {
        try {
            InputStream inputStream = mAssetManager.open(fileName);
            StringBuilder stringBuilder = new StringBuilder();
            final byte[] buffer = new byte[1024];
            int hasRead;
            while ((hasRead = inputStream.read(buffer, 0, buffer.length)) > -1) {
                stringBuilder.append(new String(buffer, 0, hasRead));
            }
            inputStream.close();
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
