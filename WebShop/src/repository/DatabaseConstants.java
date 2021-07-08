package repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

public class DatabaseConstants {
	public static String RESTAURANTS_LOGO_PATH = File.separator + "DatabasePictures" + File.separator + "restaurants" + File.separator;
	public static String ITEM_LOGO_PATH = File.separator + "DatabasePictures" + File.separator + "items" + File.separator;
	
	public static String encodeBase64(File file) {
		String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = Base64.getEncoder().encodeToString(bytes);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return encodedfile;
	}
	
	public static void writeEncodedBase64(File file, String content) {
		try {
			
			FileOutputStream os = new FileOutputStream(file);
			
			byte[] bytes = Base64.getDecoder().decode(content);
			
			os.write(bytes);
			os.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
