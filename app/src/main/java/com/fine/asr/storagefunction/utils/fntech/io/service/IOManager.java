package com.fine.asr.storagefunction.utils.fntech.io.service;

import android.os.Handler;
import android.os.Message;

import com.fntech.Loger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;

import android_serialport_api.SerialPort;

public class IOManager {
	int flg=0; //0代表没有运行 1代表运行  2 代表停止

	public static IOManager INSTANCE = new IOManager();
	Handler handler = null;

	public void InitComm(Handler handler) {
		initComm();
		this.handler = handler;
	}
	public void InitCommA(Handler handler){
		initCommA();
		this.handler=handler;
	}

	public void DeInitComm() {
		deInitComm();
	}
	
	public void DeInitCommA() {
		deInitCommA();
	}
	protected SerialPort mSerialPort = null;
	protected OutputStream mOutputStream = null;
	public InputStream mInputStream = null;

	byte[] mBuffer = null;
	int mBufferLength = 0;
	int num=0;
	private void initComm() {
		if (mSerialPort == null) {
			try {
				
				mSerialPort = new SerialPort(new File("/dev/ttyHSL1"),
						115200, 8);// 9600   115200
				mOutputStream = mSerialPort.getOutputStream();
				mInputStream = mSerialPort.getInputStream();
				Thread mCommT = new Thread(mCommDameo);
				if(flg==0||flg==2){
					   mCommT.start();
					   flg=1;
					}
			} catch (SecurityException e) {
			} catch (IOException e) {
			} catch (InvalidParameterException e) {
			}
		}
	}
	private void initCommA() {
		if (mSerialPort == null) {
			try {
				mSerialPort = new SerialPort(new File("/dev/ttyHSL1"),
						115200, 8);// 9600 BD  115200 BH
				mOutputStream = mSerialPort.getOutputStream();
				mInputStream = mSerialPort.getInputStream();
				Thread mCommT = new Thread(mCommDameo);
					if(flg==0||flg==2){
					   mCommT.start();
					   flg=1;
					}
			} catch (SecurityException e) {
			} catch (IOException e) {
			} catch (InvalidParameterException e) {
			}
		}
	}

	private void deInitComm() {
		if(flg==1){
			flg=2;
		}
		if (mOutputStream != null) {
			try {
				mOutputStream.close();
			} catch (IOException ioe) {
			}
			mOutputStream = null;
		}

		if (mInputStream != null) {
			try {
				mInputStream.close();
			} catch (IOException ioe) {
			}
			mInputStream = null;
		}

		if (mSerialPort != null) {
			mSerialPort.close();
			mSerialPort = null;
		}
	}
	private void deInitCommA() {
			if(flg==1){
				flg=2;
			}
		if (mOutputStream != null) {
			try {
				mOutputStream.close();
			} catch (IOException ioe) {
			}
			mOutputStream = null;
		}

		if (mInputStream != null) {
			try {
				mInputStream.close();
			} catch (IOException ioe) {
			}
			mInputStream = null;
		}

		if (mSerialPort != null) {
			mSerialPort.close();
			mSerialPort = null;
		}
	}
	public void SendCmd(byte[] bData, int len) throws FnIOException {

		if ((mSerialPort == null) || (mInputStream == null)
				|| (mOutputStream == null)) {
			throw new FnIOException("comm not open");
		}

		try {
			mOutputStream.write(bData, 0, len);
			Loger.disk_log("write", bData, "M10_1D");
		} catch (IOException ioe) {
		}
	}

	private Boolean IsFailure(byte[] data) {
		for (byte b : data) {
			if (b < 0) {
				return false;
			}
		}
		return true;
	}
	private Runnable mCommDameo = new Runnable() {
		public void run() {
			int iAvailable = 0;
			try {
				while (flg==1) {
					iAvailable = mInputStream.available();
					if (iAvailable > 0) {
						try {
							Thread.sleep(25);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						iAvailable = mInputStream.available();
						byte[] mBuffer = new byte[iAvailable];

						mBufferLength = mInputStream.read(mBuffer, 0,
								iAvailable);
						Loger.disk_log("read", mBuffer, "M10_1D");
						if (mBufferLength > 0) {
							if (!IsFailure(mBuffer)) {
								continue;
							}
							Message msg = new Message();
							msg.what = 0;
							msg.obj = mBuffer;
							handler.sendMessage(msg);
						}
					}

				}
			} catch (IOException ioe) {
			}
		}
	};
}
