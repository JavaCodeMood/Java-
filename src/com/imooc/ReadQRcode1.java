package com.imooc;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;

public class ReadQRcode1 {

	public static void main(String[] args) throws Exception {
		//ָ���ļ�·��
		File file=new File("E:/code/qrcode.png");
		//��ȡ��֤��ͼƬ
		BufferedImage bufferedImage=ImageIO.read(file);
        //���÷���
		QRCodeDecoder codeDecoder=new QRCodeDecoder();
		String result=new String(codeDecoder.decode(new MyQRCodeImage(bufferedImage)),"gb2312");
		
		System.out.println("��ά�������Ϊ��"+result);
		
	}

}
