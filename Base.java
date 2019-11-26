
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class Base {
	
	private static File logFile;
	
	private static Date date;
	
	public void playMenuSound() {
		 
	}
	
	
	public void play(File file) 
	{
	    try
	    {
	        final Clip clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));

	        clip.addLineListener(new LineListener()
	        {
	            @Override
	            public void update(LineEvent event)
	            {
	                if (event.getType() == LineEvent.Type.STOP)
	                    clip.close();
	            }
	        });

	        clip.open(AudioSystem.getAudioInputStream(file));
	        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        gainControl.setValue(-30.0f);
	        clip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
	}
	
	
	
	public static String convertDate() {
		
		LocalDate localDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int year  = localDate.getYear();
		int month = localDate.getMonthValue();
		int day   = localDate.getDayOfMonth();
		/*
		System.out.println(year);
		System.out.print(month);
		System.out.print(year);
		*/
		
		String syear = String.valueOf(year);
		String smonth = String.valueOf(month);
		String sday = String.valueOf(day);
		String newDate = sday + smonth + syear;
		return newDate;
	}
	public static String getHours() {
		int hour = LocalDateTime.now().getHour();
		int minute = LocalDateTime.now().getMinute();
		int second = LocalDateTime.now().getSecond();
		
		String shour = String.valueOf(hour);
		String sminute = String.valueOf(minute);
		String ssecond = String.valueOf(second);
		
		String time = shour + ":" + sminute + ":" + ssecond;
		
		return time;
	}
	
	public static File createLogFile() {
		try {
		String today = convertDate();
		logFile = new File("C:\\Users\\acer\\Desktop\\record" + today + ".txt");
		
		} catch(Exception e) {
			System.out.println(e);
		}
		return logFile;
		
	}
	
	public static void writeToFile(File f, String text) throws IOException {
		String today = convertDate();
		File fnew=new File("C:\\Users\\acer\\Desktop\\record" + today + ".txt");
		FileWriter tes = new FileWriter(fnew, true);
		
		tes.write(text);
		tes.close();
	}
	
	
	public void openGoogle() throws IOException {
		try {
			URI link = new java.net.URI("www.google.com");
			java.awt.Desktop.getDesktop().browse(link);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			writeToFile(logFile, " google was opened at " + getHours() + System.lineSeparator());
		}
		
	}
	
	public void openYoutube() throws IOException {
		try {
			URI link = new java.net.URI("www.youtube.com");
			java.awt.Desktop.getDesktop().browse(link);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			writeToFile(logFile, " youtube was opened at " + getHours() + System.lineSeparator());
		}
	}
	
	public void openTwitter() throws IOException {
		try {
			URI link = new java.net.URI("www.twitter.com");
			java.awt.Desktop.getDesktop().browse(link);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			writeToFile(logFile, " twitter was opened at " + getHours() + System.lineSeparator());
		}
	}
	
	
	
	

	public static void main(String[] args) throws IOException, InterruptedException {
		
		Base a = new Base();
		logFile = createLogFile();
		
		File menuSound = new File("C:\\Users\\User\\workspace\\PipBoy\\Sound\\nier_menu.wav");
		File menuCloseSound = new File("C:\\Users\\User\\workspace\\PipBoy\\Sound\\nier_menu_close.wav");
		
		date = new Date();
		
		Scanner reader = new Scanner(System.in); //Scanner created
		writeToFile(logFile, "Booting started at " + getHours() + System.lineSeparator()); //log written
		LinePrnt3 starter = new LinePrnt3("Booting Sequence");
		Thread.sleep(51000); //61000 if speed is 150
		System.out.println("Booting has finished successfully.");
		System.out.println("Terminal is ready.");
		writeToFile(logFile, "Terminal is ready, time: " + getHours() + System.lineSeparator());
		a.play(menuSound);
		while(true) { //loop for terminal usage
		String command = reader.nextLine();
		
		
		System.out.println("Command taken ");
		if(command.equals("help")) {
			System.out.println("Will help");
		}
		else if(command.equals("exit")){
			System.out.println("Are you sure you want to exit ? [Y/N]");
			command = reader.nextLine();
			if(command.equalsIgnoreCase("Y") || command.equalsIgnoreCase("yes")) {
				a.play(menuCloseSound);
				Thread.sleep(3000);
				System.exit(0);
			}
			
		}
		else if(command.equalsIgnoreCase("google")) {
			a.openGoogle();
		}
		else if(command.equalsIgnoreCase("youtube")) {
			a.openYoutube();
		}
		else if(command.equalsIgnoreCase("twitter")) {
			a.openTwitter();
		}
		
		
		Runtime.getRuntime().addShutdownHook(new Thread() {

		    @Override
		    public void run() {
		        try {
					writeToFile(logFile, "Program is closed at: " + getHours() + System.lineSeparator());
					
					
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }

		});
		
		}
	}

}

