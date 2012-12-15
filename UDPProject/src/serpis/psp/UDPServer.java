package serpis.psp;

import java.io.IOException;
import java.net.*;

public class UDPServer {
	
	public static void FillByteArray(byte[] buf, String data){
		byte[] bufData = data.getBytes();
		for (int index=0; index < bufData.length; index++)
		buf[index] = bufData[index];
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {	
		InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
		int port = 10001;
		byte[] buf = new byte[2048];

		DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);


		DatagramSocket datagramSocket = new DatagramSocket(port, inetAddress);

		while (true) {
			datagramPacket.setLength(buf.length);
			datagramSocket.receive(datagramPacket);
	
			String data = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
			System.out.printf("Data='%s' InetAddress=%s Port=%d",
			data, datagramPacket.getAddress(), datagramPacket.getPort());
			System.out.printf("length=%d\n", datagramPacket.getLength());
	
			data = data + data.toLowerCase();
	
			FillByteArray(buf, data);
			datagramPacket.setLength(data.getBytes().length);
	
			datagramSocket.send(datagramPacket);
		}
	}
}


