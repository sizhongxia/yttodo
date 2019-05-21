package com.company.project.common.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ChartGraphics {
	private BufferedImage image;
	private int imageWidth = 280; // 图片的宽度
	private int imageHeight = 280; // 图片的高度
	// 生成图片文件

	@SuppressWarnings("restriction")
	public void createImage(String fileLocation) {
		BufferedOutputStream bos = null;
		if (image != null) {
			try {
				FileOutputStream fos = new FileOutputStream(fileLocation);
				bos = new BufferedOutputStream(fos);

				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
				encoder.encode(image);
				bos.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bos != null) {// 关闭输出流
					try {
						bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void graphicsGeneration(String name) {

		image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		// 设置图片的背景色
		Graphics2D main = image.createGraphics();
		main.setColor(Color.white);
		main.fillRect(0, 0, imageWidth, imageHeight);

		Graphics word1 = image.createGraphics();
		// 设置区域颜色
		// word1.setColor(new Color(143, 0, 0));
		// 填充区域并确定区域大小位置
		// word1.fillRect(0, 0, imageWidth, H_title);
		// 设置字体颜色，先设置颜色，再填充内容
		word1.setColor(Color.black);
		// 设置字体
		int size = 100;
		Font font = getSelfDefinedFont("d:\\yeetong.ttf", size);
		if (font == null) {
			font = new Font(Font.SANS_SERIF, Font.BOLD, 42);
		}

		sun.font.FontDesignMetrics metrics = sun.font.FontDesignMetrics.getMetrics(font);

		System.out.println(metrics.getAscent());
		System.out.println(metrics.getDescent());
		System.out.println(metrics.stringWidth("化"));
		System.out.println(metrics.stringWidth("宇"));
		System.out.println(metrics.stringWidth("科"));
		System.out.println(metrics.stringWidth("技"));
		System.out.println(metrics.getHeight());
		System.out.println(124 - 97);
		// 100, 124

		word1.setFont(font);
		word1.drawString("化", (140 - size) / 2 + 10, 97);
		// word1.drawString("化", 40, 100);
		// word1.drawString("一", 30, 30);
//		word1.drawString("宇", (320 - size) / 2 + 10, size + 25);
//		word1.drawString("科", (140 - size) / 2 + 10, 200 + (size / 4));
		// word1.drawString("技", 200 - (size / 2) + 20, 200 + (size / 4));

		Graphics2D btn1 = image.createGraphics();
		btn1.setColor(new Color(141, 120, 22));// #29C65A
		btn1.drawLine(0, 140, 280, 140);
		btn1.drawLine(140, 0, 140, 280);
		//
		// // ***********************插入中间广告图
		// Graphics mainPic = image.getGraphics();
		// BufferedImage bimg = null;
		// try {
		// bimg = javax.imageio.ImageIO.read(new java.io.File(imgurl));
		// } catch (Exception e) {
		// }
		//
		// if (bimg != null) {
		// mainPic.drawImage(bimg, 0, H_title, imageWidth, H_mainPic, null);
		// mainPic.dispose();
		// }
		// // ***********************设置下面的提示框
		//
		// Graphics2D tip = image.createGraphics();
		// // 设置区域颜色
		// tip.setColor(new Color(255, 120, 89));
		// // 填充区域并确定区域大小位置
		// tip.fillRect(0, tip_2_top, imageWidth, H_tip);
		// // 设置字体颜色，先设置颜色，再填充内容
		// tip.setColor(Color.white);
		// // 设置字体
		// Font tipFont = new Font("宋体", Font.PLAIN, 14);
		// tip.setFont(tipFont);
		// tip.drawString("登录成功，本次认证时间1小时", 60, tip_2_top + (H_tip) / 2 - 10);
		// tip.drawString("正在返回商家主页", 100, tip_2_top + (H_tip) / 2 + 10);
		//
		// // ***********************设置下面的按钮块
		// // 设置字体颜色，先设置颜色，再填充内容
		// tip.setColor(Color.black);
		// tip.drawString("您可以选择的操作：", 20, btns_2_top);
		// tip.drawString("下面的小图标：", 20, shops_2_top);
		// // ***********************按钮
		// Font btnFont = new Font("宋体", Font.BOLD, 14);
		// Graphics2D btn1 = image.createGraphics();
		// btn1.setColor(new Color(41, 192, 50));// #29C65A
		// btn1.fillRect(10, btn1_2_top, W_btn, H_btn);
		// btn1.setColor(Color.BLACK);
		// btn1.drawRect(10, btn1_2_top, W_btn, H_btn);
		// // btn1 文本
		// btn1.setColor(Color.white);
		// btn1.setFont(btnFont);
		// btn1.drawString("单击我啊", 120, btn1_2_top + (H_btn / 2) + 5);
		//
		// Graphics2D btn2 = image.createGraphics();
		// btn2.setColor(new Color(141, 120, 22));// #29C65A
		// btn2.fillRect(10, btn2_2_top, W_btn, H_btn);
		// btn2.setColor(Color.BLACK);
		// btn2.drawRect(10, btn2_2_top, W_btn, H_btn);
		// // btn2文本
		// btn2.setColor(Color.white);
		// btn2.setFont(btnFont);
		// btn2.drawString("单击我啊", 120, btn2_2_top + (H_btn / 2) + 5);

		createImage("d:\\hehe.jpg");

	}

	private static java.awt.Font getSelfDefinedFont(String filepath, float size) {
		java.awt.Font font = null;
		File file = new File(filepath);
		try {
			font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, file);
			font = font.deriveFont(java.awt.Font.PLAIN, size);
		} catch (FontFormatException e) {
			return null;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		return font;
	}

	public static void main(String[] args) {
		ChartGraphics cg = new ChartGraphics();
		try {
			cg.graphicsGeneration("ewew");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}