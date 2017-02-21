package com.ovs.model;


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class MyLog extends Formatter { 
	
	public static Logger Log(String name,String path){
		Logger log = Logger.getLogger(name); //定义一个log对象
        log.setLevel(Level.INFO); //设置等级
		try {
			FileHandler fileHandler = new FileHandler(path);//指定文件保存路径
	        fileHandler.setLevel(Level.INFO); 
	        fileHandler.setFormatter(new MyLog()); //指定文件保存格式
	        log.addHandler(fileHandler); 
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return log;
	}
	public String format(LogRecord record) {//文件自定义格式
		return record.getMessage()+"\t\n"; 
	} 
}