package kr.co.stardust.cont;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainRun {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String str = "";
			while((str = br.readLine()) != null) {
				System.out.println(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
