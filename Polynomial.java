public class Polynomial {
	double coefficients[];
	int powers[];
	public Polynomial() {
		this.coefficients = null;
		this.powers = null;
	}
	public Polynomial(double coefficients[], int powers[]){
		this.coefficients = coefficients;
		this.powers = powers;
	}
	
	public Polynomial(File file) throws FileNotFoundException{
		Scanner myScanner = new Scanner(file);
		
		if(!myScanner.hasNextLine()){
			this.coeffficeints = null;
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
				coefficients[i] = Doube.parseDouble(subArray[0]);
				
				if (subArray.length > 1){
					powers[i] = Integer.parseInt(subArray[i]);
				}
				else {
					powers[i] = 0;
				}
			}
		}
		
	}
	
	public void saveToFile(String myFile){
		if(this.coefficients == null || this.powers == null) return; //also check for unequal lengths
		String writeString = "";
		
		for (int i = 0; i < this.coefficients.length; i++){
			writeString += cofficeints[i];
			if (powers[i] != 0){
				writeString += "x" + powers[i];
			}
			writeString += "+";
		}
		if(writeString.endsWith("+"){
			writeString = writeString.substring(0, writeString.length-1));	
		}
		FileWriter myWriter = new FileWriter(new File(myFile);
		myWriter.write(writeString);
		myWriter.close();
	}
	
	public Polynomial add(Polynomial p1) {
		int co1len = p1.coefficients.length;
		int co2len = coefficients.length;
		int minlen = Math.min(co1len, co2len);
		int maxlen = Math.max(co1len, co2len);
		double result[];
		result = new double[maxlen];
		for(int j = 0; j < minlen; j++){
			result[j] = p1.coefficients[j] + coefficients[j];
		}
		for (int i = minlen; i < maxlen; i++) {
			if (co1len > co2len) {
			result[i] = p1.coefficients[i];
			}
			else {
			result[i] = coefficients[i];
			}
		}
		return new Polynomial(result);
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
