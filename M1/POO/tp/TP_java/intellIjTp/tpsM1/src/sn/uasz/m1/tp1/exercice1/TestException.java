package sn.uasz.m1.tp1.exercice1;

//class Exception1 extends Exception {
//    Exception1(String s) {super(s);}
//}
//class Exception2 extends Exception {
//    Exception2(String s) {super(s);}
//}
//class Exception3 extends Exception {
//    Exception3(String s) {super(s);}
//}

public class  TestException {
    static void testLevee(int i) throws Exception {
        switch (i) {
            case 1:throw new Exception1 ( " exception de type Exception1" ) ;
            case 2:throw new Exception2 ( " exception de type Exception2" ) ;
            case 3:throw new Exception3 ( " exception de type Exception3" ) ;
            default:throw new Exception ( " exception de type Exception" ) ;
        }
    }
    public static void main(String [] args){
        for (int i = 1; i < 5; i++) {
            try { testLevee(i); }
            catch (Exception3 e){System.out.println("Capture d'"+e.getMessage());}
            catch (Exception1 e){System.out.println("Capture d'"+e.getMessage());}
            catch (Exception e){System.out.println("Capture d'"+e.getMessage());}
            finally {System.out.println("Exécution du bloc Finally!");}
        }
    }
}
