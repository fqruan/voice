package com.orange.voice.test;

import com.orange.voice.util.SecurityUtil;
import javafx.scene.media.AudioTrack;
import org.springframework.util.ResourceUtils;

import javax.sound.sampled.AudioFormat;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    private File tempFile = null;

    public static void main(String[] args) {
        String s = "123A";
        System.out.println(SecurityUtil.getMD5(s));
        //AudioTrack audioTrack = new AudioTrack()
        try {
            //String outUrl = ResourceUtils.getURL("classpath:static").getPath().replace("%20", " ").substring(1);

            String outUrl = "./src/main/resources/static";
            System.out.println(outUrl);
            //md5 -> base64 -> .mp3
            SecurityUtil.decoderBase64File(SecurityUtil.getMD5(s), outUrl + "/" + "1.mp3");

            //.mp3 -> base64
            String out = SecurityUtil.encodeBase64File(outUrl + "/1.mp3");
            System.out.println(out);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public void start(int rate){
//        if(rate > 0){
//            int Hz = rate;
//            int waveLen = 44100/ Hz;
//            int length = waveLen * Hz;
//            AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
//                    AudioFormat.CHANNEL_CONFIGURATION_STEREO, // CHANNEL_CONFIGURATION_MONO,
//                    AudioFormat.ENCODING_PCM_8BIT,length,AudioTrack.MODE_STREAM);
//            //生成正弦波
//            wave = SinWave.sin(wave, waveLen, length);
//            if(audioTrack!=null){
//                audioTrack.play();
//            }
//        }else{
//            return;
//        }
//    }

    public static void func() {
        AtomicInteger i = new AtomicInteger(2000000);
        funcAdd(i);
        funcAdd(i);
        System.out.println(i);
    }

    public static void funcAdd(AtomicInteger a) {
        a.getAndIncrement();
    }

    /**
     * 将base64字符转换成临时音乐文件
     */
//    private File base64ToFile(String base64Str) {
//        FileOutputStream outputStream = null;
//        if (tempFile == null || !tempFile.exists())
//            try {
//                tempFile = File.createTempFile("temp", ".mp3");
//                byte[] audioByte = Base64.decode(base64Str, Base64.DEFAULT);
//                if (tempFile != null) {
//                    outputStream = new FileOutputStream(tempFile);
//                    outputStream.write(audioByte, 0, audioByte.length);
//                    outputStream.flush();
//                    outputStream.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    if (outputStream != null) {
//                        outputStream.flush();
//                        outputStream.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        return tempFile;
//    }

    /**
     * 将本地audio文件转为base64字符串
     * @param context
     * @return
     * @throws Exception
     */
//    public String encodeBase64File(Context context) throws Exception {
//        //R.raw.test：在raw资源文件夹下放入测试声音文件
//        InputStream inputStream = context.getResources().openRawResource(R.raw.test);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        byte[] buffer = new byte[1024];
//        int len = 0;
//        while ((len = inputStream.read(buffer)) != -1) {
//            outputStream.write(buffer, 0, len);
//        }
//        outputStream.close();
//        inputStream.close();
//        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
//    }
}
