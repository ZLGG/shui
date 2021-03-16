package com.gs.lshly.common.utils;

import cn.hutool.core.io.FileUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码工具类
 * @author lxus
 * @since 2020-12-12
 */
public class QRCodeUtil {

    static final String tmpPath = "tmp/pay/";

    private static final int CODE_WIDTH = 400;
    private static final int CODE_HEIGHT = 400;
    private static final int FRONT_COLOR = 0x000000;
    private static final int BACKGROUND_COLOR = 0xFFFFFF;

    /**
     * 生成二维码并保持到指定文件中
     *
     * @param content 二维码内容
     * @param width   二维码宽度
     * @param height  二维码高度
     * @param fileName  文件名
     * @throws IOException     i/o 异常
     */
    public static void genAndSaveFile(String content, int width, int height, String fileName) throws IOException {
        String path = System.getProperty("java.io.tmpdir") + File.separator + tmpPath;
        FileUtil.mkdir(path);
        File file = QRCode.from(content).withSize(width, height).to(ImageType.PNG).file();
        FileUtil.writeToStream(file, new FileOutputStream(path + fileName));
    }

    public static void writeToResponse(HttpServletResponse response, String fileName) throws IOException {
        String path = System.getProperty("java.io.tmpdir") + File.separator + tmpPath;
        FileInputStream hFile = new FileInputStream(path + fileName);
        int size = hFile.available();
        byte[] data = new byte[size];
        hFile.read(data);
        hFile.close();
        response.setContentType("image/*");
        ServletOutputStream toClient = response.getOutputStream();
        toClient.write(data);
        toClient.close();
    }

    /**
     * 二维码输出到前端流
     * @param codeContent
     * @param outputStream
     */
    public static void createCodeToOutputStream(String codeContent,Integer width,Integer height ,OutputStream outputStream) {
        try {
            if (codeContent == null || "".equals(codeContent.trim())) {
                return;
            }
            codeContent = codeContent.trim();
            BufferedImage bufferedImage = getBufferedImage(codeContent,width,height);
            /*
             * 区别就是以一句，输出到输出流中，如果第三个参数是 File，则输出到文件中
             */
            ImageIO.write(bufferedImage, "png", outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage getBufferedImage(String codeContent,Integer width,Integer height ) throws WriterException {

        Integer trueW  = width == null ? CODE_WIDTH : width;
        Integer trueH = height == null ? CODE_HEIGHT: height;

        /*
         * com.google.zxing.EncodeHintType：编码提示类型,枚举类型
         * EncodeHintType.CHARACTER_SET：设置字符编码类型
         * EncodeHintType.ERROR_CORRECTION：设置误差校正
         * ErrorCorrectionLevel：误差校正等级，L = ~7% correction、M = ~15% correction、Q = ~25% correction、H = ~30% correction
         *   不设置时，默认为 L 等级，等级不一样，生成的图案不同，但扫描的结果是一样的
         * EncodeHintType.MARGIN：设置二维码边距，单位像素，值越小，二维码距离四周越近
         */
        Map<EncodeHintType, Object> hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 1);

        /*
         * MultiFormatWriter:多格式写入，这是一个工厂类，里面重载了两个 encode 方法，用于写入条形码或二维码
         *      encode(String contents,BarcodeFormat format,int width, int height,Map<EncodeHintType,?> hints)
         *      contents:条形码/二维码内容
         *      format：编码类型，如 条形码，二维码 等
         *      width：码的宽度
         *      height：码的高度
         *      hints：码内容的编码类型
         * BarcodeFormat：枚举该程序包已知的条形码格式，即创建何种码，如 1 维的条形码，2 维的二维码 等
         * BitMatrix：位(比特)矩阵或叫2D矩阵，也就是需要的二维码
         */
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix = multiFormatWriter.encode(codeContent, BarcodeFormat.QR_CODE, trueW, trueH, hints);

        /*
         * java.awt.image.BufferedImage：具有图像数据的可访问缓冲图像，实现了 RenderedImage 接口
         * BitMatrix 的 get(int x, int y) 获取比特矩阵内容，指定位置有值，则返回true，将其设置为前景色，否则设置为背景色
         * BufferedImage 的 setRGB(int x, int y, int rgb) 方法设置图像像素
         *      x：像素位置的横坐标，即列
         *      y：像素位置的纵坐标，即行
         *      rgb：像素的值，采用 16 进制,如 0xFFFFFF 白色
         */
        BufferedImage bufferedImage = new BufferedImage(trueW, trueH, BufferedImage.TYPE_INT_BGR);
        for (int x = 0; x < CODE_WIDTH; x++) {
            for (int y = 0; y < CODE_HEIGHT; y++) {
                bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? FRONT_COLOR : BACKGROUND_COLOR);
            }
        }
        return bufferedImage;
    }


    public static void main(String[] args) throws IOException, WriterException {
        File file =QRCode.from("http://www.baidu.com").withSize(300, 300).to(ImageType.PNG).file();
        FileUtil.writeToStream(file, new FileOutputStream("E:/newqrcode.png"));
    }

}
