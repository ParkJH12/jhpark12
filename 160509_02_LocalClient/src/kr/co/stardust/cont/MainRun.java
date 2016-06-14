package kr.co.stardust.cont;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;


class ClientThread extends Thread {
	Socket _socket;
	BufferedReader _br;
	
	public ClientThread(Socket socket) {
		_socket = socket;
	}
	
	@Override
	public void run() {
		try {
			_br = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
			String str = null;
			while((str = _br.readLine()) != null) {
				if(str.equals("quit")) {
					System.out.println("서버와의 연결이 종료되었습니다. 계속하려면 아무 키나 눌러주세요.");
					break;
				}
				System.out.println("[서버로 부터 온 메시지] " + str);
			}
		} catch (SocketException e) {
			System.out.println("서버와 연결이 종료되었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(_br != null) {
				try {
					_br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(_socket != null) {
				try {
					_socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}

public class MainRun {

	public static void main(String[] args) {
		InetAddress _ia = null;
		Scanner sc = new Scanner(System.in);
		
	
		try {
			_ia = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		System.out.println("Client On");
		System.out.println(_ia.getHostName());
		System.out.println(_ia.getHostAddress());
		
		int _serverPort = 60606;
		
		Socket _socket = null;
		BufferedWriter _bw = null;
		
		try {
			_socket = new Socket();
			_socket.connect(new InetSocketAddress(_ia.getHostAddress(), _serverPort));
			System.out.println("서버접속성공");
			
			// ClientThread 객체는 서버의 메시지를 listen하는 역할만 수행
			new ClientThread(_socket).start();
			
			_bw = new BufferedWriter(new OutputStreamWriter(_socket.getOutputStream()));
			
			String str = null;
			while(true) {
				str = sc.nextLine();
				_bw.write(str + "\n");
				_bw.flush();
				if(str.equals("quit"))
					break;
			}
			
			sc.close();
		} catch (SocketException e) {
			System.out.println("서버와 연결이 종료되었습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(_bw != null) {
				try {
					_bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(_socket != null) {
				try {
					_socket.close();
				} catch (IOException e) {
					
				}
			}
		}
		
		
	}

}
