public class Polynomial {
double coefficients[]= new double[5];
public Polynomial() {
	int length = coefficients.length;
}
public Polynomial(double array[]){
	for(int i = 0; i < array.length; i++){
		coefficients[i] = array[i];
	}
}
public Polynomial add(Polynomial p1) {
	int co1len = p1.coefficients.length;
	int co2len = coefficients.length;
	int len = Math.min(co1len, co2len);
	int maxlen = Math.max(co1len, co2len);
	double result[];
	result = new double[maxlen];
	for(int j = 0; j < len; j++){
		result[j] = p1.coefficients[j] + coefficients[j];
	}
	for (int i = len; i < maxlen; i++) {
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
	for(int i = 1; i < coefficients.length; i++){
		result = result + coefficients[i]*Math.pow(x, i);
	}
	return result + coefficients[0];
}
public boolean hasRoot(double x) {
        return evaluate(x) == 0;
}
}
