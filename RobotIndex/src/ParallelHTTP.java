import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ParallelHTTP implements Runnable {

	public static ArrayList<String> sNames = new ArrayList<String>();
	private static BufferedReader flowR;
	private static String line;
	private String url;
	private int buffer;
	private Download d;

	public ParallelHTTP(int index, int buffer) {
		// TODO Auto-generated constructor stub
		Download.listRead("servers.txt");
		this.url = Download.sNames.get(index);
		this.buffer = buffer;
		d = new Download();
	}

	/** To read the server list from a text file. */
	public static void listRead(String fName) {
		if (fName != null) {
			sNames.clear();
			try {
				flowR = new BufferedReader(new FileReader(fName));
				while ((line = flowR.readLine()) != null) {
					sNames.add(line);
				}
			} catch (IOException e) {
			}
		}
	}

	/** To print the server list. */
	public static void printList() {
		for (int i = 0; i < sNames.size(); i++)
			System.out.println(sNames.get(i));
	}

	// Runnable

	public void run() {
		
		d.trace(url, buffer);
	}

	// MAIN

	public static void main(String[] args) {
		int n = 112;
		ParallelHTTP.listRead("servers.txt");
		for (int i = 0; i < n; i++)
			new Thread(new ParallelHTTP(i, 1024)).start();
		/** 1024 is the buffer size. */
	}
}
