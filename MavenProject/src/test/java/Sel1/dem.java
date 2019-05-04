package Sel1;

public class dem {

	public static void main(String[] args) {
		
		String Demo = "pooja";
		String Demo1 = Demo.substring(0).toUpperCase() + Demo.substring(1).toLowerCase();
		
		String Demo2 = new StringBuilder(Demo).reverse().toString();
		System.out.println("---> " + Demo1);
		System.out.println("---> " + Demo2);
		
		
		String a1 = "25032018";
		String GetInvoiceDate = a1.substring(0, 2)+"/"+a1.substring(2, 4)+"/"+a1.substring(4);
		System.out.println("GetInvoiceDate-->"+GetInvoiceDate);

	}

}
