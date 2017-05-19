package bingo.com.weextest.cammera;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 *
 * Created by Zoker on 2017/5/19.
 */

public class CameraControl {
    private static final String TAG = CameraControl.class.getSimpleName();
    private static final boolean isN= Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;//是否7.0以上系统

    //结果回调的一些方法
    //直接获取照片，不缓存到本地
    public static Bitmap getPicture(Intent data) {
        Bundle bundle = data.getExtras();
        return (Bitmap) bundle.get("data");
    }

    //获取缓存文件
    public static File getCacheFile(Context context,String picName) {
        //先验证手机是否有Sdcard
        String status = Environment.getExternalStorageState();
        String filePath = null;
        if (!status.equals(Environment.MEDIA_MOUNTED)) {
            filePath = context.getFilesDir().getPath();
        } else {
            filePath = Environment.getExternalStorageDirectory().getPath() + "/temp";
        }
        if (filePath == null)
            return null;

        File fileDir = new File(filePath);
        if (!fileDir.exists())
            Log.e(TAG, "create cache dir " + filePath + " is success ->" + fileDir.mkdirs());

        File file = new File(filePath, picName);
        if (!file.exists()) {
            try {
                Log.e(TAG, "create file " + picName + " is success->" + file.createNewFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "this file path is " + file.getAbsolutePath());
        return file;
    }

    //通过缓存文件获取图片
    public static Bitmap getPicture(File file) {
        FileInputStream fis = null;
        Bitmap bitmap;

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (fis == null)
            return null;

        //把流转为图片
        return BitmapFactory.decodeStream(fis);
    }

    //通过uri获取图片绝对路径
    public static String getAbsolutePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }


    //摄像头使用相关
    //打开摄像头
    public static void openCamera(Activity activity, Map<String, String> params , int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (params != null) {
            for (String key : params.keySet()) {
                intent.putExtra(key, params.get(key));
            }
        }
        activity.startActivityForResult(intent, requestCode);
    }

    //先存储照片到本地，然后获取到原图，自己控制压缩幅度
    public static void openCamera(Activity activity, String picName, int requestCode) {
        Uri imageUri=Uri.fromFile(getCacheFile(activity,picName));
        Log.d(TAG,"uri="+imageUri);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intent, requestCode);
    }

    //7.0以上使用方式,暂时放一边
    /**
     * 7.0需要提供一个用于共享的文件夹
     * @param activity
     */
    public static void openCameraInN(Activity activity,String picName,int requestCode){
        File file=getCacheFile(activity,picName);
        Uri imageUri=null;
//      imageUri = FileProvider.getUriForFile(activity, "com.jph.takephoto.fileprovider", file);//通过FileProvider创建一个content类型的Uri
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
        //在下面需要处理好该imageUri应该存储在什么地方
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
        activity.startActivityForResult(intent,requestCode);
    }


    //裁剪相关
    //打开裁剪页面,包含所有可设置参数
    public static void openCROP(Activity activity,File file,int requestCode){
        Intent intent=new Intent("com.android.camera.action.CROP");
        //file 裁剪的数据源
        //可选参数
        Boolean isCrop=true;                                                     //是否裁剪
        int aspectX=1,aspectY=1;                                                  //裁剪框宽高比例，如果不设置则自由设置
        boolean isScan=true;                                                     //去黑边
        int outputX=800,outputY=800;                                             //裁剪后生成的图片的宽高
        boolean isScaleUpIfNeeded=true;                                          //去黑边
        final String BITMAP_FORMAT_JPEG=Bitmap.CompressFormat.JPEG.toString();
        final String BITMAP_FORMAT_PNG=Bitmap.CompressFormat.PNG.toString();
        final String BITMAP_FORMAT_WEBP=Bitmap.CompressFormat.WEBP.toString();
        String outputFormat=BITMAP_FORMAT_JPEG;                                  //生成图片的格式
        Boolean isNoFaceDetection=true;                                          //人脸识别


        //接下来是列举一些参数
        intent.setDataAndType(Uri.fromFile(file), "image/*");//要裁切的图片源
        intent.putExtra("crop",String.valueOf(isCrop));//是否裁剪
        intent.putExtra("aspectX",aspectX);
        intent.putExtra("aspectX",aspectX);
        intent.putExtra("scale", isScan);// 去黑边
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scaleUpIfNeeded", isScaleUpIfNeeded);// 去黑边
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//返回图片的格式
        intent.putExtra("noFaceDetection", isNoFaceDetection);
        // return-data为true时,会直接返回bitmap数据,推荐下面为false时的方式,同上面的
        // return-data为false时,不会返回bitmap,但需要指定一个MediaStore.EXTRA_OUTPUT保存图片uri
        intent.putExtra("return-data", false);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputFile));  //设置输出路径

        activity.startActivityForResult(intent, requestCode);
    }

    //7.0以上使用方式,暂时放一边
    public static void openCROPInN(Activity activity,File file,int requestCode){
        Intent intent=new Intent("com.android.camera.action.CROP");

        //file裁剪的数据源
        //可选参数
        Boolean isCrop=true;                                                     //是否裁剪
        int aspectX=1,aspectY=1;                                                  //裁剪框宽高比例，如果不设置则自由设置
        boolean isScan=true;                                                     //去黑边
        int outputX=800,outputY=800;                                             //裁剪后生成的图片的宽高
        boolean isScaleUpIfNeeded=true;                                          //去黑边
        final String BITMAP_FORMAT_JPEG=Bitmap.CompressFormat.JPEG.toString();
        final String BITMAP_FORMAT_PNG=Bitmap.CompressFormat.PNG.toString();
        final String BITMAP_FORMAT_WEBP=Bitmap.CompressFormat.WEBP.toString();
        String outputFormat=BITMAP_FORMAT_JPEG;                                  //生成图片的格式
        Boolean isNoFaceDetection=true;                                          //人脸识别

        Uri imageUri=null;
        // imageUri=FileProvider.getUriForFile(context, "com.jph.takephoto.fileprovider", file);//通过FileProvider创建一个content类型的Uri

        Uri outputUri=null;
//        outputUri=FileProvider.getUriForFile(context, "com.jph.takephoto.fileprovider", file);//通过FileProvider创建一个content类型的Uri

        //接下来是列举一些参数
        intent.setDataAndType(imageUri, "image/*");//要裁切的图片源
        intent.putExtra("crop",String.valueOf(isCrop));//是否裁剪
        intent.putExtra("aspectX",aspectX);
        intent.putExtra("aspectX",aspectX);
        intent.putExtra("scale", isScan);// 去黑边
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scaleUpIfNeeded", isScaleUpIfNeeded);// 去黑边
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//返回图片的格式
        intent.putExtra("noFaceDetection", isNoFaceDetection);
        // return-data为true时,会直接返回bitmap数据,推荐下面为false时的方式,同上面的
        // return-data为false时,不会返回bitmap,但需要指定一个MediaStore.EXTRA_OUTPUT保存图片uri
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);

        activity.startActivityForResult(intent, requestCode);
    }


    //相册相关
    public static void openPick(Activity activity,int requestCode){
        //调用相册
        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        activity.startActivityForResult(intent, requestCode);
    }
}
