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

//������ȡ��ά��ͼƬ��Ϣ
public class ReadQRCode {

	public static void main(String[] args) {
		try {
			MultiFormatReader formatReader = new MultiFormatReader();
			// ��ά��ͼƬλ��
			File file = new File("f:/code2/Eo.png");
			// ��ȡͼƬ
			BufferedImage image = ImageIO.read(file);
			BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));

			// �����ά��Ĳ���
			HashMap hints = new HashMap();
			//���ñ����ַ���
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

			//�����ȡ���
			Result result = formatReader.decode(binaryBitmap, hints);

			System.out.println("���������" + result.toString());
			System.out.println("��ά���ʽ���ͣ�" + result.getBarcodeFormat());
			System.out.println("��ά���ı����ݣ�" + result.getText());
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
