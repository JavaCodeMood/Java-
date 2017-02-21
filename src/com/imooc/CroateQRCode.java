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
		// ���þ���ȼ�
		x.setQrcodeErrorCorrect('M');
		// N����������֣�A�������a-z,B������������ַ�
		x.setQrcodeEncodeMode('B');
		// �汾
		x.setQrcodeVersion(7);
		// ������֤������
		//String qrData = "www.imooc.com";
		String qrData = "�ۣ������������գ�";
		// ������֤��ͼƬ��С
		int width = 67 + 12 * (7-1);
		int height = 67 + 12 * (7-1);
		// ���建����ͼƬ
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// ���û�ͼ����
		Graphics2D gs = bufferedImage.createGraphics();
		// ���ñ�����ɫΪ��ɫ
		gs.setBackground(Color.WHITE);
		// ������ɫ
		//gs.setColor(Color.blue);
		gs.setColor(Color.red);
		// �����������
		gs.clearRect(0, 0, width, height);

		// ����ƫ����
		int pixoff = 2;

		// ��������ת��Ϊ�ֽ���
		byte[] d = qrData.getBytes("utf-8");
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
		gs.dispose();  //����д��
        bufferedImage.flush();  //�����ڴ�ͼƬ
        ImageIO.write(bufferedImage, "png", new File("E:/code/qrcode1.png"));
	}

}
