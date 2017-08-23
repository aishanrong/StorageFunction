package com.fine.asr.storagefunction.utils.fntech.save.service;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaveService {

	private Context context;
	 public FileSaveService(Context context) {
		  this.context = context;
	}
	 /**
	  * 将数据保存到手机rom的文件里面
	  * @param filename
	  * @param saveStr
	  */
	 public int saveToRomFile(String fileName, String saveStr) {
		 File file = new File(fileName+ ".txt");
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter writer = new FileWriter(file, true);
			writer.write(saveStr);	
			writer.flush();
			writer.close();
//			FileOutputStream fos = context.openFileOutput(fileName +".txt", Context.MODE_WORLD_READABLE|Context.MODE_WORLD_WRITEABLE|Context.MODE_APPEND);
//		    fos.write(saveStr.getBytes());
//			fos.flush();
//			fos.close();
			return 1;
		} catch (FileNotFoundException e) {
			return 2;
		}catch (IOException e) {
			return 3;
		}
	}
	 
	 
	 /**
	  * 把数据保存到手机的外存储设备上 SD卡
	  * @param fileName 文件名
	  * @param saveStr 保存的字符串
	  */
	 public int saveToSDcard(String fileName, String saveStr ) {
		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			File file = new File(Environment.getExternalStorageDirectory(), fileName +".txt");
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter writer = new FileWriter(file, true);
				writer.write(saveStr);
				writer.flush();
				writer.close();
//				FileOutputStream fos = new FileOutputStream(file);
//			    fos.write(saveStr.getBytes());
//			    fos.flush();
//			    fos.close();
			    return 1;
			} catch (FileNotFoundException e) {
				return 2;
			} catch (IOException e) {
				return 3;
			}
		}else {
			return saveToRomFile(fileName, saveStr);
		}
	}
}
