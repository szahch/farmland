package com.szahch.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

/***
 * 
 * 生成验证码图片工具类
 * 
 * @author AlexZHOU 2017.9.19
 *
 */
public class ValidationCodeUtil {

	private static String mCodeChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String VALIDATION_CODE = "ValidationCode";

	public static String IMAGE = "Image";

	@Test
	public void test() {
		Map<String, Object> vc = ValidationCodeUtil.createImage();
		System.out.println((String) vc.get(VALIDATION_CODE));
		System.out.println((BufferedImage) vc.get(IMAGE));
	}

	public static Map<String, Object> createImage() {

		// 获得验证码集合的长度

		int charsLength = mCodeChars.length();

		// 设置图形验证码的长和宽
		int width = 90, height = 30;

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 获得用于输出文字的Graphics对象
		Graphics g = image.getGraphics();

		Random random = new Random();

		g.setColor(getRandomColor(180, 250));

		g.fillRect(0, 0, width, height);

		g.setFont(new Font("Times New Roman", Font.ITALIC, height));

		g.setColor(getRandomColor(120, 180));

		// 用户保存最后随机生成的验证码

		StringBuilder validationCode = new StringBuilder();

		// 验证码的随机字体

		String[] fontNames = { "Times New Roman", "Book antiqua", "Arial" };

		// 随机生成4个验证码

		for (int i = 0; i < 4; i++) {

			// 随机设置当前验证码的字符的字体

			g.setFont(new Font(fontNames[random.nextInt(3)], Font.ITALIC, height));

			// 随机获得当前验证码的字符

			char codeChar = mCodeChars.charAt(random.nextInt(charsLength));

			validationCode.append(codeChar);

			// 随机设置当前验证码字符的颜色

			g.setColor(getRandomColor(10, 100));

			// 在图形上输出验证码字符，x和y都是随机生成的

			g.drawString(String.valueOf(codeChar), 16 * i + random.nextInt(7), height - random.nextInt(6));

		}

		// 关闭Graphics对象

		g.dispose();

		Map<String, Object> returnMap = new HashMap<String, Object>();

		returnMap.put(VALIDATION_CODE, validationCode.toString());

		returnMap.put(IMAGE, image);

		return returnMap;

	}

	// 返回一个随机颜色

	private static Color getRandomColor(int minColor, int maxColor) {

		Random random = new Random();

		if (minColor > 255) {

			minColor = 255;

		}

		if (maxColor > 255) {

			maxColor = 255;

		}

		// 获得r的随机颜色值

		int red = minColor + random.nextInt(maxColor - minColor);

		// g

		int green = minColor + random.nextInt(maxColor - minColor);

		// b

		int blue = minColor + random.nextInt(maxColor - minColor);

		return new Color(red, green, blue);

	}

}
