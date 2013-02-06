package serpis.psp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import org.omg.CORBA.portable.InputStream;

public class ThreadServer implements Runnable{

	private static final String newLine = "\r\n";
	private final String defaultFileName = "index.html";
	private final String response200 = "HTTP/1.0 200 OK";
	private final String response404 = "HTTP/1.0 404 Not Found";
	private final String fileNameError404 = "fileError404.html";
	
	private Socket socket;
	private InputStream inputStream;
	private OutputStream outputStream;
	private String fileName;
	private Boolean fileExists;
	
	public ThreadServer(Socket socket){
		this.socket=socket;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//Para comprobar por pantalla el funcionamiento de la clase
		System.out.println(Thread.currentThread().getName() + " inicio.");
		try {
			String fileName= getFileName(socket.getInputStream());		
			writeHeader(socket.getOutputStream(),fileName);		
			writeFile(socket.getOutputStream(),fileName);		
			socket.close();
		}catch(IOException ex){
		}catch(InterruptedException ex){			
		}//fin try/catch
		
		System.out.println(Thread.currentThread().getName() + " fin.");
	}
	
	private void getFileExists() {
			File file = new File(fileName);
			fileExists = file.exists();
	}
	
	private String getFileName(java.io.InputStream inputStream){
		Scanner scanner = new Scanner( inputStream );

		//String fileName = "index.html";
		String fileName = "";

		while (true) {
			String line = scanner.nextLine();
			System.out.println(line);
			if (line.startsWith("GET")) { //GET /index.html HTTP/1.1
			/*int index = 5;
				while (line.charAt(index) != ' ')
					fileName += line.charAt(index++);*/
				fileName = line.substring(5, line.indexOf(" ", 5));
				
			System.out.println("fileName="+fileName);
		}
		if (line.equals(""))
			break;
		}
		return fileName;
	
	}
	
	
	private void writeHeader(OutputStream outputStream, String fileName) throws IOException{
		String response= fileExists ? response200 : response404;
		
		
		String header = response+ newLine + newLine;
		byte[] headerBuffer = header.getBytes();
		outputStream.write(headerBuffer);
	}
	
	private void writeFile(OutputStream outputStream, String fileName) throws IOException, InterruptedException{
		String responseFileName= fileExists ? fileName : fileNameError404;
		
		final int bufferSize= 2048;
		byte[] buffer = new byte[bufferSize];
		
		FileInputStream fileInputStream= new FileInputStream(fileName);
		int count;
		while((count = fileInputStream.read(buffer)) != -1){ 
			Thread.sleep(10000);//Retardo de 10 seg
			outputStream.write(buffer,0 , count);
		}
		
		
		fileInputStream.close();
	}


}
