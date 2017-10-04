package com.alex.other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * 正则表达式测试
 * 
 * @author AlexZHOU 2017.9.28
 *
 */
public class RegularExpression {

	/**
	 * 只能输入英文字符和数字
	 */
	@Test
	public void _1() {

		Pattern p = Pattern.compile("[a-zA-Z0-9]{1,16}");

		System.out.println("RegularExpression : [a-zA-Z0-9]{1,16}");

		Matcher m = p.matcher("a111 \"\"11");

		System.out.println("a111 \\\"\\\"11 : " + m.matches());

		m = p.matcher("a1112211");

		System.out.println("a1112211 : " + m.matches());

	}

}
