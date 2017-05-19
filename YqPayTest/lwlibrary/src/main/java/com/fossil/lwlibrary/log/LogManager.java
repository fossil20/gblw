package com.fossil.lwlibrary.log;

import android.app.Activity;

import com.fossil.lwlibrary.utils.CommonUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.Calendar;

public class LogManager {

	private File fileName;
	private static final String FOLDERNAME = "com.fossil.lwlibrary";

	public LogManager(File fileName) {
		this.fileName = fileName;
		try {
			createFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建文件
	 * 
	 * @return
	 */
	public boolean createFile() throws Exception {
		boolean flag = false;
		try {
			if (!fileName.exists()) {
				fileName.createNewFile();
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 读TXT文件内容
	 * 
	 * @return
	 */
	public String readTxtFile() throws Exception {
		String result = null;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
			try {
				String read = null;
				while ((read = bufferedReader.readLine()) != null) {
					result = result + read + "\r\n";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (fileReader != null) {
				fileReader.close();
			}
		}
		System.out.println("读取出来的文件内容是：" + "\r\n" + result);
		return result;
	}

	/**
	 * 
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public boolean writeTxtFile(String content) throws Exception {
		RandomAccessFile mm = null;
		boolean flag = false;
		FileOutputStream o = null;
		try {
			o = new FileOutputStream(fileName);
			o.write(content.getBytes("GBK"));
			o.close();
			// mm=new RandomAccessFile(fileName,"rw");
			// mm.writeBytes(content);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (mm != null) {
				mm.close();
			}
		}
		return flag;
	}

	/**
	 * 获取当前时间字符串
	 * 
	 * @return
	 */
	private static String getNowTime() {
		String nowTime;
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		String month;
		if (c.get(Calendar.MONTH) + 1 < 10)
			month = "0" + String.valueOf(c.get(Calendar.MONTH) + 1);
		else
			month = String.valueOf(c.get(Calendar.MONTH) + 1);

		String date;
		if (c.get(Calendar.DATE) < 10)
			date = "0" + c.get(Calendar.DATE);
		else
			date = String.valueOf(c.get(Calendar.DATE));
		String hour;
		if (c.get(Calendar.HOUR_OF_DAY) < 10)
			hour = "0" + String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		else
			hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		String minute;
		if (c.get(Calendar.MINUTE) < 10)
			minute = "0" + c.get(Calendar.MINUTE);
		else
			minute = String.valueOf(c.get(Calendar.MINUTE));
		String second;
		if (c.get(Calendar.SECOND) < 10)
			second = "0" + String.valueOf(c.get(Calendar.SECOND));
		else
			second = String.valueOf(c.get(Calendar.SECOND));
		nowTime = year + "/" + month + "/" + date + "\t" + hour + ":" + minute
				+ ":" + second;
		return nowTime;
	}

	/**
	 * 
	 * @return
	 */
	private static String getLogName() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		String month;
		if (c.get(Calendar.MONTH) + 1 > 10)
			month = String.valueOf(c.get(Calendar.MONTH) + 1);
		else
			month = "0" + String.valueOf(c.get(Calendar.MONTH) + 1);
		String date;
		if (c.get(Calendar.DATE) > 10)
			date = String.valueOf(c.get(Calendar.DATE));
		else
			date = "0" + String.valueOf(c.get(Calendar.DATE));
		return year + month + date;
	}

	/**
	 * 写入log日志
	 * 
	 * @param content
	 */
	public static void writeInlog(String content) {
		content = getNowTime() + "\n" + content;
		String str; // 原有txt内容
		String s1 = new String();// 内容更新
		try {
			File file = new File(getLogPath(FOLDERNAME));
			if (!file.exists() && !file.isDirectory())
				file.mkdirs();

			File f = new File(getLogPath(FOLDERNAME) + "/" + getLogName() + ".txt");
			if (f.exists()) {
				//System.out.print("文件存在");
			} else {
				//System.out.print("文件不存在");
				f.createNewFile();
			}
			BufferedReader input = new BufferedReader(new FileReader(f));

			while ((str = input.readLine()) != null) {
				s1 += str + "\r\n";
			}
			input.close();
			s1 += content;
			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			output.write(s1);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getLogPath(String packageName){
		return String.format(CommonUtil.LOG_PATH,packageName);
	}

	/**
	 * 写入log日志
	 * 
	 * @param content
	 */
	public static void writeInlog(Activity activity,Throwable ex, String content) {
		content = getNowTime() + "\n" + content;
		String str; // 原有txt内容
		String s1 = null;// 内容更新
		try {
			File file = new File(getLogPath(activity.getPackageName()));
			if (!file.exists() && !file.isDirectory())
				file.mkdir();

			File f = new File(getLogPath(activity.getPackageName()) + "/" + getLogName() + ".txt");
			if (f.exists()) {
				System.out.print("文件存在");
			} else {
				System.out.print("文件不存在");
				f.createNewFile();// 不存在则重新创建
			}
			BufferedReader input = new BufferedReader(new FileReader(f));

			while ((str = input.readLine()) != null) {
				s1 += str + "\r\n";
			}
			input.close();
			s1 += content;
			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			output.write(s1);
			output.close();
			
			PrintStream ps = new PrintStream(new FileOutputStream(f));
			ex.printStackTrace(ps);
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
