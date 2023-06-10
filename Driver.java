import java.io.File;
public class Driver {
	public static void main(String [] args) {
		File file = new File("./load.txt");
		try{
			Polynomial filePoly = new Polynomial(file);
			System.out.println("Coefficients:");
			for (double coeff: filePoly.coefficients){
				System.out.println(coeff);
			}
			System.out.println("Exponents:");
			for (int power: filePoly.powers){
				System.out.println(power);
		}
		filePoly.saveToFile("save.txt");
		}
		catch(Exception e){
			System.out.println(e);
		}

		//tests
		System.out.println("Testing p1 = 1 + 2x + 3x^2 + 4x^3:\n");
		double p1_c[] = {1, 2, 3, 4};
		int p1_p[] = {0, 1, 2, 3};
		Polynomial p1 = new Polynomial(p1_c, p1_p);

		System.out.println("Returned value p1(1.0): " + p1.evaluate(1.0));
		System.out.println("Expected value: 10.0\n");

		System.out.println("Returned value p1(-1.0): " + p1.evaluate(-1.0));
		System.out.println("Expected value: -2.0\n");

		System.out.println("p1 has root of 0.0?: " + p1.hasRoot(0.0));
		System.out.println("Expected value: false\n");

		double p2_c[] = {0.0};
		int p2_p[] = {69};
		Polynomial p2 = new Polynomial(p2_c, p2_p);

		System.out.println("p2 has root of 0.0?: " + p2.hasRoot(0.0));
		System.out.println("Expected value: true\n");

		double p3_c[] = {-2, -3, -4};
		int p3_p[] = {1, 2, 3};
		Polynomial p3 = new Polynomial(p3_c, p3_p);
		Polynomial sum1 = p1.add(p3);
		try{
			sum1.saveToFile("sum1.txt");
		}
		catch(Exception e){
			System.out.println(e);
		}

		double p4_c[] = {-1,-2, -3, -4,-5};
		int p4_p[] = {0, 1, 2, 3, 4};
		Polynomial p4 = new Polynomial(p4_c, p4_p);
		Polynomial sum2 = p1.add(p4);
		try{
			sum2.saveToFile("sum2.txt");
		}
		catch(Exception e){
			System.out.println(e);
		}
		double p5_c[] = {2,2};
		int p5_p[] = {1,0};
		Polynomial p5 = new Polynomial(p5_c, p5_p);

		double p6_c[] = {1,2};
		int p6_p[] = {0, 1};
		Polynomial p6 = new Polynomial(p6_c, p6_p);

		Polynomial prod1 = p5.multiply(p6);
		try{
			prod1.saveToFile("prod1.txt");
		}
		catch(Exception e){
			System.out.println(e);
		}

		double p7_c[] = {1,-2};
		int p7_p[] = {0, 1};
		Polynomial p7 = new Polynomial(p7_c, p7_p);

		Polynomial prod2 = p7.multiply(p6);
		try{
			prod2.saveToFile("prod2.txt");
		}
		catch(Exception e){
			System.out.println(e);
		}


	}
}
