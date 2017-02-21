package com.imooc;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

/*实现输入字符串(文本，网址)生成对应的二维码
 * 二维码实质是01代码，具有4个等级的容错能力
 * 二维码具有容错功能，当二维码图片被遮挡一部分后，仍可以扫描出来。
 *容错的原理是二维码在编码过程中进行了冗余，就像是123被编码成123123，这样只要扫描到一部分二维码图片，
 *二维码内容还是可以被全部读到。
 *二维码容错率即是指二维码图标被遮挡多少后，仍可以被扫描出来的能力。容错率越高，则二维码图片能被遮挡的部分越多。
 *二维码容错率用字母表示，容错能力等级分为：L、M、Q、H四级
 * */
public class CreateQrcode {
	public static void main(String[] args) throws Exception {
		// 创建生成二维码的对象
		Qrcode x = new Qrcode();
		// 设置二维码的容错能力等级
		x.setQrcodeErrorCorrect('M');
		// N代表的是数字，A代表的是a-z,B代表的是其他字符
		x.setQrcodeEncodeMode('B');
		// 版本
		x.setQrcodeVersion(7);
		// 设置验证码内容 可以输入多次
		Scanner scanner = new Scanner(System.in);
		// String str = scanner.nextLine(); //只能输入一次
		System.out.println("请输入你要生成二维码的内容：");
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			// 设置验证码的大小
			int width = 67 + 12 * (7 - 1);
			int height = 67 + 12 * (7 - 1);
			// 定义缓冲区图片
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			// 设置画图工具
			Graphics2D gs = bufferedImage.createGraphics();
			// 设置二维码背景颜色
			gs.setBackground(Color.lightGray);//lightGray
			// 设置颜色
			gs.setColor(Color.cyan);//cyan,green,red,black,pink
			// 清除画板内容
			gs.clearRect(0, 0, width, height);
			// 定义偏移量
			int pixoff = 2;

			// 填充的内容转化为字节数
			byte[] d = str.getBytes("utf-8"); // 设置编码方式
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
			// 结束写入
			gs.dispose();
			// 结束内存图片
			bufferedImage.flush();
			/*
			 * 保存生成的二维码图片 第一先生成4位随机字母字符串，用于给生成的二维码命名 第二保存生成的二维码
			 * (char)(int)(Math.random()*26+65) 随机生成一个大写字母
			 * (char)(int)(Math.random()*26+97) 随机生成一个小写字母
			 */
			System.out.println("生成的4位随机字符串为：");
			for (int i = 0; i < 4; i++) {
				char c = (char) (int) (Math.random() * 26 + 65);
				System.out.print(c);
			}
			ImageIO.write(bufferedImage, "png", new File("F:/code2/" + (char) (int) (Math.random() * 26 + 65)
					+ (char) (int) (Math.random() * 26 + 97) + ".png"));
			System.out.println("\n二维码图片生成成功！");
		}
	}
}
