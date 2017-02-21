package com.imooc;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

/*ʵ�������ַ���(�ı�����ַ)���ɶ�Ӧ�Ķ�ά��
 * ��ά��ʵ����01���룬����4���ȼ����ݴ�����
 * ��ά������ݴ��ܣ�����ά��ͼƬ���ڵ�һ���ֺ��Կ���ɨ�������
 *�ݴ��ԭ���Ƕ�ά���ڱ�������н��������࣬������123�������123123������ֻҪɨ�赽һ���ֶ�ά��ͼƬ��
 *��ά�����ݻ��ǿ��Ա�ȫ��������
 *��ά���ݴ��ʼ���ָ��ά��ͼ�걻�ڵ����ٺ��Կ��Ա�ɨ��������������ݴ���Խ�ߣ����ά��ͼƬ�ܱ��ڵ��Ĳ���Խ�ࡣ
 *��ά���ݴ�������ĸ��ʾ���ݴ������ȼ���Ϊ��L��M��Q��H�ļ�
 * */
public class CreateQrcode {
	public static void main(String[] args) throws Exception {
		// �������ɶ�ά��Ķ���
		Qrcode x = new Qrcode();
		// ���ö�ά����ݴ������ȼ�
		x.setQrcodeErrorCorrect('M');
		// N����������֣�A�������a-z,B������������ַ�
		x.setQrcodeEncodeMode('B');
		// �汾
		x.setQrcodeVersion(7);
		// ������֤������ ����������
		Scanner scanner = new Scanner(System.in);
		// String str = scanner.nextLine(); //ֻ������һ��
		System.out.println("��������Ҫ���ɶ�ά������ݣ�");
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			// ������֤��Ĵ�С
			int width = 67 + 12 * (7 - 1);
			int height = 67 + 12 * (7 - 1);
			// ���建����ͼƬ
			BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			// ���û�ͼ����
			Graphics2D gs = bufferedImage.createGraphics();
			// ���ö�ά�뱳����ɫ
			gs.setBackground(Color.lightGray);//lightGray
			// ������ɫ
			gs.setColor(Color.cyan);//cyan,green,red,black,pink
			// �����������
			gs.clearRect(0, 0, width, height);
			// ����ƫ����
			int pixoff = 2;

			// ��������ת��Ϊ�ֽ���
			byte[] d = str.getBytes("utf-8"); // ���ñ��뷽ʽ
			if (d.length > 0 && d.length < 120) {
				boolean[][] s = x.calQrcode(d);
				for (int i = 0; i < s.length; i++) {
					for (int j = 0; j < s.length; j++) {
						if (s[j][i]) {
							// ��֤��ͼƬ�������
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			}
			// ����д��
			gs.dispose();
			// �����ڴ�ͼƬ
			bufferedImage.flush();
			/*
			 * �������ɵĶ�ά��ͼƬ ��һ������4λ�����ĸ�ַ��������ڸ����ɵĶ�ά������ �ڶ��������ɵĶ�ά��
			 * (char)(int)(Math.random()*26+65) �������һ����д��ĸ
			 * (char)(int)(Math.random()*26+97) �������һ��Сд��ĸ
			 */
			System.out.println("���ɵ�4λ����ַ���Ϊ��");
			for (int i = 0; i < 4; i++) {
				char c = (char) (int) (Math.random() * 26 + 65);
				System.out.print(c);
			}
			ImageIO.write(bufferedImage, "png", new File("F:/code2/" + (char) (int) (Math.random() * 26 + 65)
					+ (char) (int) (Math.random() * 26 + 97) + ".png"));
			System.out.println("\n��ά��ͼƬ���ɳɹ���");
		}
	}
}
