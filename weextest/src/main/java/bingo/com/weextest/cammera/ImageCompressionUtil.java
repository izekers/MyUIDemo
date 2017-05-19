package bingo.com.weextest.cammera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Bitmap所占用的内存 = 图片长度 x 图片宽度 x 一个像素点占用的字节数
 * Created by Zoker on 2017/5/19.
 */
public class ImageCompressionUtil {
    /**
     * 质量压缩
     * 改变了图片的质量
     * 该方式下bitmap不改变在内存中占用大小，而改变了图片再存储时的占用大小
     * 该方式无法压缩PNG图
     * @param quality 图片质量随该值下降而下降
     */
    public static Bitmap QualityCompressed(Bitmap bitmap,int quality){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        byte[] bytes = baos.toByteArray();
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     * RGB_565法
     * @return
     */
    public static Bitmap rgb_565Compression(String pathName){
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(pathName, options2);
    }


    /**
     * 采样率压缩
     * 会减少bitmap尺寸
     * @param sampleSize 数值为2的时候降低宽高为1/2
     * @return
     */
    public static Bitmap SamplingCompression(String pathName,int sampleSize){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = sampleSize;
        return BitmapFactory.decodeFile(pathName, options);
    }

    /**
     * 缩放法压缩
     * 按比例缩小
     * @param scanleX 例如0.5f
     * @return
     */
    public static Bitmap martixCompression(Bitmap bitmap,float scanleX,float scanleY){
        Matrix matrix = new Matrix();
        matrix.setScale(scanleX, scanleY);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);
    }

    /**
     * 强制设置为某种尺寸
     * @param bitmap
     * @return
     */
    public static Bitmap createScaledBitmap(Bitmap bitmap){
        return Bitmap.createScaledBitmap(bitmap, 150, 150, true);
    }
}
