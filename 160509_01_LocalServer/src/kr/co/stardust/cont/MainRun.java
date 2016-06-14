package kr.co.stardust.cont;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

class ServerThread extends Thread {
	Socket _socket;
	BufferedReader _br;
	
	public ServerThread(Socket socket) {
		_socket = socket;
	}
	
	@Override
	public void run() {
		try {
			InetAddress ia = _socket.getInetAddress();
			_br = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
			String str = null;
			while((str = _br.readLine()) != null) {
				if(str.equals("quit")) {
					System.out.println("클라이언트와의 연결이 종료되었습니다. 계속하려면 아무 키나 눌러주세요.");
					break;
				}
				System.out.println("[" + ia.getHostAddress() + "] " + str);
			}
		} catch (SocketException e) {
			System.out.println("[" + _socket.getInetAddress().getHostAddress() + "] 와의 연결이 종료되었습니다.");
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
		Scanner sc = new Scanner(System.in);
		InetAddress _ia = null;
		try {
			_ia = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		System.out.println("Server On");
		System.out.println(_ia.getHostName());
		System.out.println(_ia.getHostAddress());
		
		
		int _userPort = 60606;
		
		ServerSocket _userSS = null;
		Socket _socket = null;
		BufferedWriter _bw = null;
		
		try {
			_userSS = new ServerSocket();
			
			_userSS.bind(new InetSocketAddress(_ia.getHostAddress(), _userPort));
			
			boolean a = true;
			
			
			while(a) {
				_socket = _userSS.accept();
				System.out.println("[" + _socket.getInetAddress().getHostAddress() + "] 가 접속했습니다.");

				// ServerThread 객체는 클라이언트의 메시지를 listen하는 역할만 수행
				new ServerThread(_socket).start();

				_bw = new BufferedWriter(new OutputStreamWriter(_socket.getOutputStream()));
				String str = null;
				try {
					while(true) {
						str = sc.nextLine();
						_bw.write(str + "\n");
						_bw.flush();
						if(str.equals("quit"))
							break;
					}
				} catch (SocketException e) {
					System.out.println("[" + _socket.getInetAddress().getHostAddress() + "] 와의 연결이 종료되었습니다.");
				}
				

				_bw.close();
				_socket.close();
			}
			
			sc.close();
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
					e.printStackTrace();
				}
			}
			
			if(_userSS != null) {
				try {
					_userSS.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
