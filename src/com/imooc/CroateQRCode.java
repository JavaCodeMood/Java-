package com.imooc;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CroateQRCode {

	public static void main(String[] args) throws Exception {

		Qrcode x = new Qrcode();
		// 设置纠错等级
		x.setQrcodeErrorCorrect('M');
		// N代表的是数字，A代表的是a-z,B代表的是其他字符
		x.setQrcodeEncodeMode('B');
		// 版本
		x.setQrcodeVersion(7);
		// 设置验证码内容
		//String qrData = "www.imooc.com";
		String qrData = "哇，罗秋锋锋美美哒！";
		// 设置验证码图片大小
		int width = 67 + 12 * (7-1);
		int height = 67 + 12 * (7-1);
		// 定义缓冲区图片
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 设置画图工具
		Graphics2D gs = bufferedImage.createGraphics();
		// 设置背景颜色为白色
		gs.setBackground(Color.WHITE);
		// 设置颜色
		//gs.setColor(Color.blue);
		gs.setColor(Color.red);
		// 清除画板内容
		gs.clearRect(0, 0, width, height);

		// 定义偏移量
		int pixoff = 2;

		// 填充的内容转换为字节数
		byte[] d = qrData.getBytes("utf-8");
		if (d.length > 0 && d.length < 120) {
			boolean[][] s = x.calQrcode(d);

			for (int i = 0; i < s.length; i++) {
				for (int j = 0; j < s.length; j++) {
					if (s[j][i]) {
						// 验证码图片填充内容
						gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
					}
				}
			}
		}
		gs.dispose();  //结束写入
        bufferedImage.flush();  //结束内存图片
        ImageIO.write(bufferedImage, "png", new File("E:/code/qrcode1.png"));
	}

}
