package com.orange.voice.util;

public class AudioUtil {

    public static void getStringToWAV(String verify, int userid) {
        String outUrl = "./src/main/resources/static";
        WAVUtil wavTest = new WAVUtil();

        try {
            char[] vs = verify.toCharArray();
            String fileFirst = outUrl + "/" + vs[0] + ".wav";
            String fileFinal = outUrl + "/" + userid + ".wav";
            for (int i = 1; i < 32; i++) {
                String fileMiddle = outUrl + "/" + vs[i] + ".wav";
                wavTest.addWav(fileFirst, fileMiddle, fileFinal);
                wavTest.updateFileHead(fileFirst, false);
                wavTest.updateFileHead(fileMiddle, false);
                wavTest.updateFileHead(fileFinal, true);
                fileFirst = fileFinal;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
