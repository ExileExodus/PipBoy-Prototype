
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class LinePrnt3 extends Thread {
	
	private String threadName;
	
	private Stack messages;
	
	public LinePrnt3(String threadName) {
		this.threadName = threadName;
		
		this.messages = new Stack(14);
		
		//this.messages.push(System.get);
		this.printUsage();
		
		this.messages.push("Current Java Version: " + System.getProperty("java.version"));
		this.messages.push("OS Arch: " + System.getProperty("os.arch"));
		this.messages.push("Commencing System Check...");
		this.messages.push("Roblox Industries are here for you !");
		this.messages.push("Welcome !");
		this.messages.push("*****PIP-OS(R) V7.0.0.7*****");
		
		this.start();
	}
	
	public String[] convert(Stack m) {
			
			String message = m.pop();
			
			String[] array = message.split("");
			
			return array;
			
		}
	
	
	
	private void printUsage() {
		OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
		for(Method method: operatingSystemMXBean.getClass().getDeclaredMethods()) {
			method.setAccessible(true);
			if(method.getName().startsWith("get") && Modifier.isPublic(method.getModifiers())) {
				Object value;
				try {
					value = method.invoke(operatingSystemMXBean);
				} catch(Exception e) {
					value = e;
				} //try and catch
				//System.out.println(method.getName() + " = " + value);
				String k = method.getName() + " = " + value;
				messages.push(k);
				
			}//if
		}//for
		//return threadName;
		
	}//method
	
	public void run() {
		System.out.println("Currently running " + threadName);
		
		for(int i = 0; i <messages.getLength(); i++ ) {
			String[] toprint = this.convert(messages);
			try {
				for(int a = 0; a < toprint.length; a++) {
					System.out.print(toprint[a]);
					Thread.sleep(125);  //125 or 150
				}
			} catch(Exception e) {
				System.out.println(e);
			}
			System.out.println();
		}
		System.out.println();
	}
	






	public static void main(String[] args) throws IOException {
		//LinePrnt3 test3 = new LinePrnt3("Test3");
		/*
		test3.printUsage();
		test3.messages.push("Roblox Industries are here for you !");
		test3.messages.push("Welcome !");
		test3.messages.push("*****PIP-OS(R) V7.0.0.7*****");
		
		test3.start();
		*/
		
		//open a link
		/*
		try {
			URI link = new java.net.URI("www.google.com");
			java.awt.Desktop.getDesktop().browse(link);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		*/
		
		//open a link
		
		//System.getProperties().list(System.out);
		
		//System.out.println(System.getProperty("java.version"));
		
		Scanner reader = new Scanner(System.in);
		LinePrnt3 starter = new LinePrnt3("Booting Sequence");
		//Thread.sleep(50000);
		System.out.println("Enter");
		String command = reader.nextLine();

	}

}

