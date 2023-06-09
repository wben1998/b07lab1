import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Polynomial {
	public double coefficients[];
	public int powers[];
	public Polynomial() {
		this.coefficients = null;
		this.powers = null;
	}
	public Polynomial(double coefficients[], int powers[]){
		this.coefficients = coefficients;
		this.powers = powers;
	}
	
	public Polynomial(File file) throws Exception{
		Scanner myScanner = new Scanner(file);
		
		if(!myScanner.hasNextLine()){
			this.coefficients = null;
			this.powers = null;
		}
		
		else{
			String line = myScanner.nextLine();
			line = line.replace("-","+-");
			String[] poly_arr = line.split("\\+");
			this.powers = new int[poly_arr.length];
			this.coefficients = new double[poly_arr.length];
			for(int i = 0; i < poly_arr.length; i++){
				String[] subArray = poly_arr[i].split("x");
				coefficients[i] = Double.parseDouble(subArray[0]);
				
				if (subArray.length > 1){
					powers[i] = Integer.parseInt(subArray[1]);
				}
				else {
					powers[i] = 0;
				}
			}
		}
		
	}
	
	public void saveToFile(String myFile) throws Exception{
		if(this.coefficients == null || this.powers == null || this.coefficients.length != this.powers.length) return; //check to see it valid lengths and non-void
		String writeString = "";
		
		for (int i = 0; i < this.coefficients.length; i++){
			writeString += coefficients[i]; 
			if (powers[i] != 0){
				writeString += "x" + powers[i];
			}
			writeString += "+";
		}
		if(writeString.endsWith("+")){
			writeString = writeString.substring(0, (writeString.length())-1);	
		}
		FileWriter myWriter = new FileWriter(new File(myFile));
		myWriter.write(writeString);
		myWriter.close();
	}

	public Polynomial add(Polynomial p1) {
		double[] p1Coefficients = p1.coefficients;
        int[] p1powers = p1.powers;

        int len1 = coefficients.length;
        int len2 = p1Coefficients.length;

        int resultLen = len1 + len2;
        double[] resultcoefficients = new double[resultLen];
        int[] resultpowers = new int[resultLen];

        int i = 0;
		int j = 0;
		int k = 0;

        while (i < len1 && j < len2) {
            if (powers[i] == p1powers[j]) { //equal power
                double sum = coefficients[i] + p1Coefficients[j];
                if (sum != 0) {
                    resultcoefficients[k] = sum;
                    resultpowers[k] = powers[i];
                    k++;
                }
                i++;
                j++;
            } 
			else if (powers[i] < p1powers[j]) { //power less in calling object
                resultcoefficients[k] = coefficients[i];
                resultpowers[k] = powers[i];
                k++;
                i++;
            } 
			else { //power more in calling object
                resultcoefficients[k] = p1Coefficients[j];
                resultpowers[k] = p1powers[j];
                k++;
                j++;
            }
        }

        while (i < len1) { //finish going though calling object coefficients
            resultcoefficients[k] = coefficients[i];
            resultpowers[k] = powers[i];
            k++;
            i++;
        }

        while (j < len2) { //finish going through input coefficients
            resultcoefficients[k] = p1Coefficients[j];
            resultpowers[k] = p1powers[j];
            k++;
            j++;
        }

        double[] finalcoefficients = new double[k];
        int[] finalpowers = new int[k];
        System.arraycopy(resultcoefficients, 0, finalcoefficients, 0, k);
        System.arraycopy(resultpowers, 0, finalpowers, 0, k);

        return new Polynomial(finalcoefficients, finalpowers);
    }


	public double evaluate(double x){
		double result = 0.0;
		for (int i = 1; i < coefficients.length; i++){
			result = result + coefficients[i]*Math.pow(x, powers[i]);
		}
		return result + coefficients[0];
	}
	public boolean hasRoot(double x) {
		return evaluate(x) == 0;
	}
}
