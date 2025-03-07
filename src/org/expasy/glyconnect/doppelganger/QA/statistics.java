package org.expasy.glyconnect.doppelganger.QA;

/**
 * Statistics class contains functions necessary to perform a quality analysis.
 */
public class statistics {
    public static boolean admitValues(int val1, int val2) {
        if ( val1 == 0 && val2 == 0 ) return false;
        if ( val1 < 0 || val2 < 0 ) {
            System.out.println("Unacceptable negative value:"+
                    "\nValue1: " + val1 +
                    "\nValue2: " + val2 );
            System.exit(1);
        }
        return true;
    }

    public static void statistics(int TP, int TN, int FP, int FN, boolean verbose) {
        double TPR = truePositiveRate(TP,FN);
        double TNR = trueNegativeRate(TN,FP);
        double PPV = positivePredictiveValue(TP,FP);
        double NPV = negativePredictiveValue(TN,FN);
        double FDR = falseDiscoveryRate(FP,TP);
        double FNR = falseNegativeRate(FN,TP);
        double FPR = falsePositiveRate(FP,TN);
        double FOR = falseOmissionRate(FN,TN);

        double MCC1 = matthewsCorrelationCoefficient(TP,TN,FP,FN);
        double MCC2 = matthewsCorrelationCoefficient(PPV,TPR,TNR,NPV,FDR,FNR,FPR,FOR);

        if ( verbose ) {
            System.out.println("\nTrue Positive Rate:          " + TPR +
                    "\nTrue Negative Rate:          " + TNR +
                    "\nPositive Predictive Value:   " + PPV +
                    "\nNegative Predictive Value:   " + NPV +
                    "\nFalse Discovery Rate:        " + FDR +
                    "\nFalse Negative Rate:         " + FNR +
                    "\nFalse Positive Rate:         " + FPR +
                    "\nFalse Omission Rate:         " + FOR +
                    "\nMatthews Correlation Coefficient (MCC) with int:    " + MCC1 +
                    "\nMatthews Correlation Coefficient (MCC) with double: " + MCC2);
        } else {
            System.out.println("True Positive Rate" + "\t" + "True Negative Rate" + "\t" +
                    "Positive Predictive Value" + "\t" + "Negative Predictive Value" +  "\t" +
                    "False Discovery Rate" + "\t" + "False Negative Rate" + "\t" +
                    "False Positive Rate" + "\t" + "False Omission Rate" + "\t" +
                    "Matthews Correlation Coefficient (MCC)");

            System.out.println(TPR + "\t" + TNR + "\t" +
                    PPV + "\t" + NPV + "\t" +
                    FDR + "\t" + FNR + "\t" +
                    FPR + "\t" + FOR + "\t" +
                    MCC2);
        }
    }

    // Senitivity:
    public static double truePositiveRate(int truePositives, int falseNegatives) {
        if ( !(admitValues(truePositives,falseNegatives)) ) return 0.0;
        return ( (double) truePositives / ((double) truePositives + (double) falseNegatives) );
    }

    // Specificity:
    public static double trueNegativeRate(int trueNegatives, int falsePositives) {
        if ( !(admitValues(trueNegatives,falsePositives)) ) return 0.0;
        return ( (double) trueNegatives / ((double) trueNegatives + (double) falsePositives) );
    }

    // Precision:
    public static double positivePredictiveValue(int truePositives, int falsePositives) {
        if ( !(admitValues(truePositives,falsePositives)) ) return 0.0;
        return ( (double) truePositives / ((double) truePositives + (double) falsePositives) );
    }

    public static double negativePredictiveValue(int trueNegatives, int falseNegatives) {
        if ( !(admitValues(trueNegatives,falseNegatives)) ) return 0.0;
        return ( (double) trueNegatives / ((double) trueNegatives + (double) falseNegatives) );
    }

    public static double falseDiscoveryRate(int falsePositives, int truePositives) {
        if ( !(admitValues(falsePositives,truePositives)) ) return 0.0;
        return ( (double) falsePositives / ((double) falsePositives + (double) truePositives) );
    }

    // Miss rate
    public static double falseNegativeRate(int falseNegatives, int truePositives) {
        if ( !(admitValues(falseNegatives,truePositives)) ) return 0.0;
        return ( (double) falseNegatives / ((double) falseNegatives + (double) truePositives) );
    }

    // Fall-out
    public static double falsePositiveRate(int falsePositives, int trueNegatives) {
        if ( !(admitValues(falsePositives,trueNegatives)) ) return 0.0;
        return ( (double) falsePositives / ((double) falsePositives + (double) trueNegatives) );
    }

    public static double falseOmissionRate(int falseNegatives, int trueNegatives) {
        if ( !(admitValues(falseNegatives,trueNegatives)) ) return 0.0;
        return ( (double) falseNegatives / ((double) falseNegatives + (double) trueNegatives) );
    }

    public static double matthewsCorrelationCoefficient(int truePositives, int trueNegatives, int falsePositives, int falseNegatives) {
        int numerator = (truePositives * trueNegatives) - (falsePositives * falseNegatives);

        int TPFP = truePositives + falsePositives;
        int TPFN = truePositives + falseNegatives;
        int TNFP = trueNegatives + falsePositives;
        int TNFN = trueNegatives + falseNegatives;

        int denominator;

        if ( TPFP == 0 || TPFN == 0 || TNFP == 0 || TNFN == 0 ) denominator = 1;
        else denominator = TPFP * TPFN * TNFP * TNFN;

        return ( (double) numerator / Math.sqrt(denominator) );
    }

    public static double matthewsCorrelationCoefficient(double PPV, double TPR, double TNR, double NPV,
                                                        double FDR, double FNR, double FPR, double FOR) {
        double term1 = PPV * TPR * TNR * NPV;
        double term2 = FDR * FNR * FPR * FOR;

        return Math.sqrt(term1) - Math.sqrt(term2);
    }
}
