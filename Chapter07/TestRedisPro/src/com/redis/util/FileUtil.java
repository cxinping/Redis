package com.redis.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class FileUtil {

	public static void saveImage(String descFile, byte[] inBytes){
		  try
	        {
	            FileOutputStream fos = new FileOutputStream(descFile  );
	            fos.write(inBytes);
	            fos.flush();
	            fos.close();
	        } catch (FileNotFoundException e)
	        {
	            e.printStackTrace();
	        } catch (UnsupportedEncodingException e)
	        {
	            e.printStackTrace();
	        } catch (IOException e)
	        {
	            e.printStackTrace();
	        }

	}
}
