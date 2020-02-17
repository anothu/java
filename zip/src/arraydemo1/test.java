package arraydemo1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class test {
	public void zip(String zipFileName,File inputFile) throws Exception{
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
		zip(out,inputFile,"");
		System.out.println("compressing...");
		out.close();
	}
	public void zip(ZipOutputStream out,File f,String base)throws Exception {
		if (f.isDirectory()) {
			File[] fl =f.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length()==0 ?"":base+"/";
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i],base+fl[i]);
			}
		}
		else {
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b;
			System.out.println(base);
			while((b = in.read())!= -1) {
				out.write(b);
			}
			in.close();
		}
	}
	public static void main(String[] args) {
		test book = new test();
		try {
			book.zip("/Users/.../Desktop/pythonwork/demo.zip", new File("/Users/.../Desktop/pythonwork/download.py"));
			System.out.println("compress fished!");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}