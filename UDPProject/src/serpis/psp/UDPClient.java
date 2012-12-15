package serpis.psp;

import java.io.IOException;
import java.net.*;
import java.util.Date;

public class UDPClient {
	

public static void FillByteArray(byte[] buf, String data){
	byte[] bufData = data.getBytes();
	for (int index=0; index < bufData.length; index++)
	buf[index] = bufData[index];
}
	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
		int port = 10001;
		byte[] buf = new byte[2048];
		
		DatagramSocket datagramSocket = new DatagramSocket();
		DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length, inetAddress, port);

		while( true ) {
			String texto = "Hola desde UDPClient " + new Date();
			FillByteArray(buf, texto);
			datagramPacket.setLength(texto.getBytes().length);
			datagramSocket.send(datagramPacket);
	
			datagramPacket.setLength(buf.length);
			datagramSocket.receive(datagramPacket);
	
			String data = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
			System.out.printf("Receive Data='%s'\n", data);
	
			Thread.sleep(5000); //milisegundos
		}

	}

}
