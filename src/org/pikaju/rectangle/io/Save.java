package org.pikaju.rectangle.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Save {
	
	private static DataOutputStream dos;
	private static DataInputStream dis;
	
	public static void write() {
		try {
			File f = new File("C://The Regular Rectangle/save.dat");
			if(!f.exists()) {
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
			dos = new DataOutputStream(new FileOutputStream(f));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void read() {
		try {
			File f = new File("C://The Regular Rectangle/save.dat");
			if(!f.exists()) {
				return;
			}
			dis = new DataInputStream(new FileInputStream(f));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close() {
		try {
			if(dos != null) {
				dos.close();
				dos = null;
			}
			if(dis != null) {
				dis.close();
				dis = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static DataOutputStream getDOS() {
		return dos;
	}
	
	public static DataInputStream getDIS() {
		return dis;
	}
}
