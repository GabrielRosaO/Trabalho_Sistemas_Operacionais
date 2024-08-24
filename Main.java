public class Main{
    public static void main(String[] args) {
        System.out.println("Hello");
        Recursos recursos = new Recursos(10, 10);
        Pessoa a = new Pessoa("a", 2, recursos);
        a.start();
        Pessoa b = new Pessoa("b", 2, recursos);
        b.start();
        recursos.countTime();
        recursos.print();
        for(int i = 0; i < 10*10*10*10*10*10*10; i++){

        }
        System.out.println(recursos.getCapacetes());
        recursos.print();
    }
}