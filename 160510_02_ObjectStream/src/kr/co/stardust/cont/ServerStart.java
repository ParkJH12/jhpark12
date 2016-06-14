package kr.co.stardust.cont;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import kr.co.stardust.domain.EmpBean;
import kr.co.stardust.model.EmpDao;

public class ServerStart {
	public void startServer(EmpBean eb) {
		ServerSocket ss = null;
		Socket s = null;
		ObjectOutputStream oos = null;
		try {
			ss = new ServerSocket();
			ss.bind(new InetSocketAddress("192.168.14.163", 50000));
			System.out.println("서버 시작");
			s = ss.accept();
			
			oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(eb);
			oos.flush();
			System.out.println("oos 세팅");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	
	public void startServer(List<EmpBean> list) {
		ServerSocket ss = null;
		Socket s = null;
		ObjectOutputStream oos = null;
		try {
			ss = new ServerSocket();
			ss.bind(new InetSocketAddress("192.168.14.163", 50000));
			System.out.println("서버 시작");
			s = ss.accept();
			
			oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(list);
			oos.flush();
			System.out.println("전송 완료");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public EmpBean conServer(InetSocketAddress isa) {
		// TODO Auto-generated method stub
		Socket socket = new Socket();
		ObjectInputStream ois = null;
		EmpBean eb = null;
		try {
			socket.connect(isa);
			ois = new ObjectInputStream(socket.getInputStream());
			eb = (EmpBean) ois.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return eb;
	}
	
	public List<EmpBean> conServerGetList(InetSocketAddress isa) {
		List<EmpBean> list = null;
		Socket socket = new Socket();
		ObjectInputStream ois = null;
		try {
			socket.connect(isa);
			ois = new ObjectInputStream(socket.getInputStream());
			list = (List<EmpBean>) ois.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	
	
	
////////////////////////// DB연결 //////////////////////////////////
	
	public void startServerDB() {
		List<EmpBean> list = new ArrayList<EmpBean>();
		EmpDao ed = null;
		ServerSocket ss = null;
		Socket s = null;
		ObjectOutputStream oos = null;
		try {
			ss = new ServerSocket();
			ss.bind(new InetSocketAddress("192.168.14.163", 50000));
			System.out.println("서버 시작");
			s = ss.accept();
			ed = new EmpDao();
			list = ed.getEmpAll();
			
			oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(list);
			oos.flush();
			System.out.println("전송 완료");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(s != null) {
				try {
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
