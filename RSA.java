//Jesus Riquelmer Gaxiola Higuera A01740223
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

 

public class RSA {
	public BigInteger p,
	   				   q,
	   				   n,
	   				   phi,
	   				   e,
	   				   d;
    private Random     r;

 

    public RSA() {
        r = new Random();
        p = BigInteger.probablePrime(1024, r);
        q = BigInteger.probablePrime(1024, r);
        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(1023, r);
        	//e.gcd(phi)
        	//MCD(e,phi)
        while (MCD(e.intValue(),phi.intValue())>1 && e.compareTo(phi) < 0)
        {
            e.add(BigInteger.ONE);
        }

        d = inverse(e, phi);

    }

    public int MCD(int a, int b) {
		while(a!=b)
			if(a>b)
				a-=b;
			else
				b-=a;
		return a;
    }

    
    public BigInteger inverse(BigInteger a,BigInteger m)
    {
		
        BigInteger c1 = BigInteger.valueOf(1);
        BigInteger c2 = (m.divide(a)).negate(); //coeficiente de a y b respectivamente
        BigInteger t1 = BigInteger.valueOf(0);
        BigInteger t2 = BigInteger.valueOf(1);
        BigInteger r = m.remainder(a); //residuo, asignamos 1 como condicion de entrada 
        BigInteger x=a,
        		   y=r,
        		   c;
        while(!r.equals(BigInteger.valueOf(0)))
        {
        c = x.divide(y);//cociente
        r = x.remainder(y);//residuo
        //guardamos valores temporales de los coeficientes
        //multiplicamos los coeficiente por -1*cociente de la division
        c1=c1.multiply(c.negate());
        c2=c2.multiply(c.negate());
        //sumamos la corrida anterior
        c1=c1.add(t1);
        c2=c2.add(t2);
        //actualizamos corrida anterior
        t1=(c1.subtract(t1).negate()).divide(c);
        t2=(c2.subtract(t2).negate()).divide(c);
        x=y;
        y=r;
        }
      if(x.equals(BigInteger.valueOf(1)))//residuo anterior es 1 , son primos relativos y el inverso existe
            return t2;
      else
            return BigInteger.valueOf(-1);
    }

    public RSA(BigInteger e, BigInteger d, BigInteger N) {
        this.e = e;
        this.d = d;
        this.n = N;

    }

    private static String bytesToString(byte[] encrypted) {
        String test = "";
        for (byte b : encrypted)
        {
            test += Byte.toString(b);
        }
        return test;
    }
    // Encrypt message
    public byte[] encrypt(byte[] message){
        return (new BigInteger(message)).modPow(e, n).toByteArray();
    }

    // Decrypt message
    public byte[] decrypt(byte[] message){
        return (new BigInteger(message)).modPow(d, n).toByteArray();
    }
    
    public static void main(String[] args) throws IOException

    {

        RSA rsa = new RSA();
        Scanner sc=new Scanner(System.in);

        System.out.println("Ingrese el mensaje:");

        String mensaje=sc.nextLine();

        System.out.println("Mensaje a Enviar: " + mensaje);

        System.out.println("Mensaje Encriptado: " + (String)bytesToString(mensaje.getBytes()));

        // encrypt

        byte[] encrypted = rsa.encrypt(mensaje.getBytes());

        // decrypt

        byte[] decrypted = rsa.decrypt(encrypted);

        System.out.println("Mensaje a decifrar: " + (String)bytesToString(decrypted));

        System.out.println("Mensaje Decifrado: " + new String(decrypted));

    }

 

    

}
