package com.imooc;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;

public class ReadQRcode1 {

	public static void main(String[] args) throws Exception {
		//指定文件路径
		File file=new File("E:/code/qrcode.png");
		//读取验证码图片
		BufferedImage bufferedImage=ImageIO.read(file);
        //调用方法
		QRCodeDecoder codeDecoder=new QRCodeDecoder();
		String result=new String(codeDecoder.decode(new MyQRCodeImage(bufferedImage)),"gb2312");
		
		System.out.println("二维码的内容为："+result);
		
	}

}
