package com.iokays.onjava.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

/**
 * <a href="https://0o0.me/java/java-terminal-output-qrcode.html">JAVA 控制台输出二维码</a>
 */
public class QRCodeUtils {

    public static void main(String[] args) {
        final var text = "https://www.baidu.com";
        printQr(text);
        System.out.println("====================================");

    }


    public static void printQr(String text) {
        String s = "生成二维码失败";
        int width = 5;  //随便，足够小即可，反正最后不管设置多小，控制台输出的二维码都不会变小了
        int height = 5;
        Hashtable<EncodeHintType, Object> qrParam = new Hashtable<>();
        qrParam.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        qrParam.put(EncodeHintType.CHARACTER_SET, "utf-8");
        qrParam.put(EncodeHintType.MARGIN, 1);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, qrParam);
            s = toAscii(bitMatrix);
        } catch (WriterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(s);
    }

    /**
     * 30 到 37 用于设置文本颜色，例如：
     * 30m：黑色, 31m：红色, 32m：绿色, 33m：黄色, 34m：蓝色, 35m：品红（洋红）, 36m：青色, 37m：白色,
     * <br>
     * 40 到 47 用于设置背景颜色，例如：
     * , 40m：黑色背景, 41m：红色背景, 42m：绿色背景, 43m：黄色背景, 44m：蓝色背景, 45m：品红（洋红）背景, 46m：青色背景, 47m：白色背景
     *
     * @param bitMatrix
     * @return
     */
    public static String toAscii(BitMatrix bitMatrix) {
        StringBuilder sb = new StringBuilder();
        for (int rows = 0; rows < bitMatrix.getHeight(); rows++) {
            for (int cols = 0; cols < bitMatrix.getWidth(); cols++) {
                boolean x = bitMatrix.get(rows, cols);
                if (!x) {
                    sb.append("\033[47m   \033[0m");
                } else {
                    sb.append("\033[30m   \033[0m");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
