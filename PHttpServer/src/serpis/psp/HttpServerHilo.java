package serpis.psp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import org.omg.CORBA.portable.InputStream;

public class HttpServerHilo implements Runnable  {
		
		private static final String newLine="\r\n";
		private Socket socket;
		public HttpServerHilo(Socket socket){
			this.socket=socket;
		}
		public void run(){
			try {
				run(socket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		public void run(Socket socket) throws IOException, InterruptedException{
			
			String fileName= getFileName(socket.getInputStream());
			writeHeader(socket.getOutputStream(),fileName);
			writeFile(socket.getOutputStream(),fileName);
			
			socket.close();
			
			//serverSocket.close();//hay que excluirla
			
			//CAMBIOS: que el servidor se quede escuchando infinitamente
			// - 
		}
		
		//Implementar
		private String getFileName(java.io.InputStream inputStream){
			final String fileName="index.html";
			Scanner scanner = new Scanner (inputStream);
			
			while (true){//buscar el GET y extraer la cadena del archivo
				String line = scanner.nextLine();
				System.out.println(line);
				if(line.equals("")) break;//la linea en blanco es la final de la peticion
			}
			return fileName;
		
		}
		
		private static void writeHeader(OutputStream outputStream, String fileName) throws IOException{
			final String response200="HTTP/1.0 200 OK";
			final String response404="HTTP/1.0 404 NOT FOUND";
			File file = new File(fileName);
			String response= file.exists() ? response200 : response404;
			
			
			String header = response+ newLine + newLine;
			byte[] headerBuffer = header.getBytes();
			outputStream.write(headerBuffer);
		}
		
		private static void writeFile(OutputStream outputStream, String fileName) throws IOException, InterruptedException{
			final String fileError404 ="fileError404.html";

			File file=new File(fileName);
			
			String responseFileName= file.exists() ? fileName : fileError404;
			
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
