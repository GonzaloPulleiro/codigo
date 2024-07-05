package Exercicio4;


public class CarreiraTartarugaLebre {
	
    public static void main(String[] args) {
    	
        Carreira carreira = new Carreira();
        try {
            carreira.simularCarreira();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
