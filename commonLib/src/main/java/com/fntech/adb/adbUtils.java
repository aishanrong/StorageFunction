package com.fntech.adb;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class adbUtils {
	 public static void SendMessageCode(final String key){
//	    	new Thread(new Runnable() {
//				@Override
//				public void run() {
					 try {
						 execCommand("input keyevent "+key);				 
				        } catch (IOException e) {
				            // TODO Auto-generated catch block
				            e.printStackTrace();
				        }
				}
//			}).start();
//	    }
	 
	public static void execCommand(String command) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec(command);
        try {
//            if (proc.waitFor() != 0) {
//                System.err.println("exit value = " + proc.exitValue());
//            }
//            BufferedReader in = new BufferedReader(new InputStreamReader(
//                    proc.getInputStream()));
//            StringBuffer stringBuffer = new StringBuffer();
//            String line = null;
//            while ((line = in.readLine()) != null) {
//                stringBuffer.append(line+"-");
//            }
//            System.out.println(stringBuffer.toString());
//            editText.setText(stringBuffer.toString());
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
