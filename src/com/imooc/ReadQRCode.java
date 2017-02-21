package com.imooc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

//解析读取二维码图片信息
public class ReadQRCode {

	public static void main(String[] args) {
		try {
			MultiFormatReader formatReader = new MultiFormatReader();
			// 二维码图片位置
			File file = new File("f:/code2/Eo.png");
			// 读取图片
			BufferedImage image = ImageIO.read(file);
			BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));

			// 定义二维码的参数
			HashMap hints = new HashMap();
			//设置编码字符集
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

			//处理读取结果
			Result result = formatReader.decode(binaryBitmap, hints);

			System.out.println("解析结果：" + result.toString());
			System.out.println("二维码格式类型：" + result.getBarcodeFormat());
			System.out.println("二维码文本内容：" + result.getText());
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
