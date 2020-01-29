package edu.ucsb.cs56.ratcalc.model;
/** A class to represent a rational number
    with a numerator and denominator

    @author Joe Z, Dylan Lin

    */

public class Rational {

    private int num;
    private int denom;

    /** 
	greatest common divisor of a and b
	@param a first number
	@param b second number
	@return gcd of a and b
    */
    public static int gcd(int a, int b) {
	if (a==0)
	    return b;
	else if (b==0)
	    return a;
	else
	    return gcd(b%a, a);
    }
    
    public Rational() {
	this.num = 1;
	this.denom = 1;
    }

    public Rational(int num, int denom) {
	if (denom == 0) {
	    throw new IllegalArgumentException("denominator may not be zero");
	}
	this.num = num;
	this.denom = denom;
	if (num != 0) {
	    int gcd = Rational.gcd(num,denom);
	    this.num /= gcd;
	    this.denom /= gcd;
        if(this.denom < 0)
        {
            this.num = -this.num;
            this.denom = -this.denom;
        }
	}
    }

    public String toString() {
	if (denom == 1 || num == 0)
	    return "" + num;
	return num + "/" + denom;
    }

    public int getNumerator() { return this.num; }
    public int getDenominator() { return this.denom; }

    public Rational times(Rational r) {
	return new Rational(this.num * r.num,
			    this.denom * r.denom);
    }

    public static Rational product(Rational a, Rational b) {
	return new Rational(a.num * b.num,
			    a.denom * b.denom);
    }


    /** 
	least common multiple of a and b
	@param a first number
	@param b second number
	@return lcm of a and b
    */
    public static int lcm(int a, int b){
        return a * (b / gcd(a,b));
    }

    /** 
	sum of this number plus r
	@param r rational number
	@return sum of this number plus r
    */    
    public Rational plus(Rational r){
        return new Rational(lcm(this.denom, r.denom)/this.denom*this.num + lcm(this.denom, r.denom)/r.denom*r.num, lcm(this.denom, r.denom));
    }

    /**
     returns a+b
     */
    public static Rational sum(Rational a, Rational b){
        return new Rational(lcm(a.denom, b.denom)/a.denom*a.num + lcm(a.denom, b.denom)/b.denom*b.num, lcm(a.denom, b.denom));
    }
    /**
     returns this number minus r
     */
    public Rational minus(Rational r){
        return new Rational(lcm(this.denom, r.denom)/this.denom*this.num - lcm(this.denom, r.denom)/r.denom*r.num, lcm(this.denom, r.denom));
    }
    
    /**
     return a-b
     */
    public static Rational difference(Rational a, Rational b){
        return new Rational(lcm(a.denom, b.denom)/a.denom*a.num - lcm(a.denom, b.denom)/b.denom*b.num, lcm(a.denom, b.denom));
    }

    /**
     *returns reciprocal (swap numerator and demoninator
     *if numerator is zero, throws an instance of exception
     */
    public Rational ReciprocalOf(){
        if(this.num == 0)
        {
            throw new ArithmeticException();
        }
        return new Rational(this.denom, this.num);
    }

    /**
     returns this number divided by r
     */
    public Rational dividedBy(Rational r){
        if(r.num == 0)
        {
            throw new ArithmeticException();
        }
        return new Rational(this.num * r.denom, this.denom * r.num);
    }

    /**
     returns a divided by b
     */
    public static Rational quotient(Rational a, Rational b){
        if(b.num == 0)
        {
            throw new ArithmeticException();
        }
        return new Rational(a.num * b.denom, a.denom * b.num);
    }

    /** 
	For testing getters.  
	@param args unused
     */

    public static void main (String [] args) {
	Rational r = new Rational(5,7);
	System.out.println("r.getNumerator()=" + r.getNumerator());
	System.out.println("r.getDenominator()=" + r.getDenominator());
    }

    
}
