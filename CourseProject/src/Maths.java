public class Maths {

    public static double a1, b1;
    public static double a2, b2, c2;
    public static double lnA3, b3;

    public static void mnk(double[] x, double[] y) {
        int n = x.length;

        double sumX = 0;
        double sumXSquare = 0;
        double sumY = 0;
        double sumXY = 0;
        for (int i = 0; i < x.length; i++) {
            sumX += x[i];
            sumY += y[i];
            sumXSquare += Math.pow(x[i], 2);
            sumXY += x[i] * y[i];
        }
        a1 = (sumXY - sumX * sumY / n) / (sumXSquare - sumX * sumX / n);
        b1 = sumY / n - a1 * sumX / n;

    }

    public static double linearFun(double x) {
        return a1 * x + b1;
    }

    public static void logfunc(double[] x, double[] y) {
        int n = x.length;
        double det;
        double sumY = 0, sumYlnX = 0, sumlnX = 0, sumlnXsquare = 0;

        for (int i = 0; i < n; i++) {
            sumY += y[i];
            sumYlnX += y[i] * Math.log(x[i]);
            sumlnX += Math.log(x[i]);
            sumlnXsquare += Math.pow(Math.log(x[i]), 2);
        }

        a2 = (sumY * sumlnXsquare - sumYlnX * sumlnX) / (n * sumlnXsquare - sumlnX * sumlnX);
        b2 = (n * sumYlnX - sumY * sumlnX) / (n * sumlnXsquare - sumlnX * sumlnX);
    }

    public static double nonlinearFun(double x) {
        return a2 + b2 * Math.log(x) ;
    }

    public static void exponential(double[] x, double[] y) {
        int n = x.length;
        double sumLnY = 0, sumX = 0, sumLnYX = 0, sumX2 = 0;

        for (int i = 0; i < n; i++) {
            sumLnY += Math.log(y[i]);
            sumX += x[i];
            sumX2 += Math.pow(x[i], 2);
            sumLnYX += Math.log(y[i]) * x[i];
        }

        lnA3 = (sumLnY * sumX2 - sumLnYX * sumX) / (n * sumX2 - sumX * sumX);
        b3 = (sumLnYX * n - sumLnY * sumX) / (n * sumX2 - sumX * sumX);
    }

    public static double expFun(double x) {
        return Math.exp(lnA3) * Math.exp(b3 * x);
    }
}