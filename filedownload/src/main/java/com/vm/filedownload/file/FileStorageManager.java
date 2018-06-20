package com.vm.filedownload.file;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

public class FileStorageManager {
    private Context mContext;

    private FileStorageManager() {
    }

    /**
     * 静态单例模式
     *
     * @return
     */
    public static FileStorageManager getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        static FileStorageManager INSTANCE = new FileStorageManager();
    }

    /**
     * init
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
    }

    /**
     * 根据path获取file
     *
     * @param path
     * @return
     */
    public File getFileByName(String path) {
        File parent = null;
        if (Environment.isExternalStorageEmulated()) {
            parent = mContext.getExternalCacheDir();
        } else {
            parent = mContext.getCacheDir();
        }
        File file = new File(parent, path);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    return file;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
