package com.ovs.model;


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class MyLog extends Formatter { 
	
	public static Logger Log(String name,String path){
		Logger log = Logger.getLogger(name); //����һ��log����
        log.setLevel(Level.INFO); //���õȼ�
		try {
			FileHandler fileHandler = new FileHandler(path);//ָ���ļ�����·��
	        fileHandler.setLevel(Level.INFO); 
	        fileHandler.setFormatter(new MyLog()); //ָ���ļ������ʽ
	        log.addHandler(fileHandler); 
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return log;
	}
	public String format(LogRecord record) {//�ļ��Զ����ʽ
		return record.getMessage()+"\t\n"; 
	} 
}