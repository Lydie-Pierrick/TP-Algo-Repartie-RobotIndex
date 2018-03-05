import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Download {

	private URL url;
	private byte[] array;
	private InputStream iS;
	private int size;
	private int size_total;

	protected void trace(String ad, int buffer) {
		try {
			long time_start;
			long time_now;

			url = new URL(ad);
			array = new byte[buffer];
			iS = url.openStream();

			System.out.println("Start flux: ");
			System.out.println((double) toS(System.nanoTime()));
			size = 0;
			time_start = System.nanoTime();
			do {

				size = iS.read(array);
				// System.out.println("Size: " + size);

				size_total += size;

			} while (size != -1);
			iS.close();

			time_now = System.nanoTime();

			System.out.println("Speed: "
					+ toMbs(size_total, time_now - time_start) + "Mb/s");

			System.out.println("End flux: ");
			System.out.println((double) toS(System.nanoTime()));

		} catch (Exception e) {
		}

		System.out.println("Size total: " + size_total);

	}

	// Conversion method

	/** From byte / ns to Mbits.s. */
	protected double toMbs(long tsize, long dt) {
		return toMb(tsize) / toS(dt);
	}

	/** From byte to Mbits. */
	protected double toMb(long tsize) {
		return ((double) tsize * 8) / (1024 * 1024);
	}

	/** From nano seconds to seconds. */
	protected double toS(long dt) {
		return ((double) dt) / 1000000000;
	}

	// Read list

	public static ArrayList<String> sNames = new ArrayList<String>();
	private static BufferedReader flowR;
	private static String line;

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

	// MAIN

	
//	public static void main(String[] args) {
//
//		ParallelHTTP runnable = new ParallelHTTP();
//
//		// d.trace("http://www.cosy.sbg.ac.at/~pmeerw/Watermarking/lena_color.gif",
//		// 227335);
//
//		// Download.printList();
//
//		for (String s : Download.sNames) {
//			System.out.println("\nURL :" + s + "\n");
//			runnable.run();
//		}
//	}
}
