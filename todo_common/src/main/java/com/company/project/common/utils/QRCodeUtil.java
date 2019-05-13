package com.company.project.common.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码生成和读的工具类
 *
 */
public class QRCodeUtil {

	/**
	 * 生成包含字符串信息的二维码图片
	 * 
	 * @param content
	 *            二维码携带信息
	 * @param qrCodeSize
	 *            二维码图片大小
	 * @throws WriterException
	 * @throws IOException
	 */
	public static byte[] initQrBytes(String content, int qrCodeSize) throws WriterException, IOException {
		// 设置二维码纠错级别map
		Hashtable<EncodeHintType, Object> hintMap = new Hashtable<EncodeHintType, Object>();
		hintMap.put(EncodeHintType.MARGIN, 1);
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); // 矫错级别
		hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		// 创建比特矩阵(位矩阵)的QR码编码的字符串
		BitMatrix matrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);
		// int crunchifyWidth = matrix.getWidth();
		// // BufferedImage的主要作用就是将一副图片加载到内存中
		// BufferedImage image = new BufferedImage(crunchifyWidth, crunchifyWidth,
		// BufferedImage.TYPE_INT_RGB);
		// image.createGraphics();
		// Graphics2D graphics = (Graphics2D) image.getGraphics();
		// graphics.setColor(Color.WHITE);
		// graphics.fillRect(0, 0, crunchifyWidth, crunchifyWidth);
		// for (int i = 0; i < crunchifyWidth; i++) {
		// for (int j = 0; j < crunchifyWidth; j++) {
		// if (byteMatrix.get(i, j)) {
		// Random random = new Random();
		// int r = random.nextInt(256);
		// int g = random.nextInt(256);
		// int b = random.nextInt(256);
		// graphics.setColor(new Color(r, g, b));
		// graphics.fillRect(i, j, 1, 1);
		// }
		// }
		// }
		// 二维矩阵转为一维像素数组
		int[] pixels = new int[qrCodeSize * qrCodeSize];
		// Set<String> colors = new HashSet<>();
		for (int y = 0; y < matrix.getHeight(); y++) {
			for (int x = 0; x < matrix.getWidth(); x++) {
				int r = (int) (105 - 25.0 / matrix.getHeight() * (y + 1));
				int g = (int) (105 - 25.0 / matrix.getHeight() * (y + 1));
				int b = (int) (105 - 25.0 / matrix.getHeight() * (y + 1));
				// colors.add("rgb(" + r + ", " + g + ", " + b + ")");
				Color color = new Color(r, g, b);
				int colorInt = color.getRGB();
				pixels[y * qrCodeSize + x] = matrix.get(x, y) ? colorInt : 16777215;
			}
		}
		BufferedImage image = new BufferedImage(qrCodeSize, qrCodeSize, BufferedImage.TYPE_INT_RGB);
		image.getRaster().setDataElements(0, 0, qrCodeSize, qrCodeSize, pixels);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ImageIO.write(image, "gif", os);
		return os.toByteArray();
	}

//	public static void main(String[] args) {
//		try {
//			getFile(createQrCodeBytes("sizhongxia", 200), "d:\\", "ddd.gif");
//		} catch (WriterException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

//	public static void getFile(byte[] bfile, String filePath, String fileName) {
//		BufferedOutputStream bos = null;
//		FileOutputStream fos = null;
//		File file = null;
//		try {
//			File dir = new File(filePath);
//			if (!dir.exists() && dir.isDirectory()) {// 判断文件目录是否存在
//				dir.mkdirs();
//			}
//			file = new File(filePath + "\\" + fileName);
//			fos = new FileOutputStream(file);
//			bos = new BufferedOutputStream(fos);
//			bos.write(bfile);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (bos != null) {
//				try {
//					bos.close();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//			}
//			if (fos != null) {
//				try {
//					fos.close();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//			}
//		}
//	}

}
