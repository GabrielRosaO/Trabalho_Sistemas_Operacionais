


public class Main{
    public static void main(String[] args){
        //Registra o objeto kartodromo com 10 capacetes e 10 karts.
        //Todos os calculos de tempo foram feitos na base de minutos então 8 horas = 480 minutos
        Kartodromo r = new Kartodromo(10, 10, 480);
        //executa o código
        r.run();
    }
}