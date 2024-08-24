public class Pessoa extends Thread{
    private String nome;
    private int idade;
    private Recursos recursos;
    private boolean capacete;
    private boolean kart;

    public Pessoa(String nome, int idade, Recursos recursos){
        this.nome = nome;
        this.idade = idade;
        this.recursos = recursos;
        this.capacete = false;
        this.kart = false;
    }

    public String getNome(){
        return this.nome;
    }

    public int getIdade(){
        return this.idade;
    }

    private void useCapacete(){
        this.capacete = true;
        recursos.useCapacete();
    }

    private void useKart(){
        this.capacete = true;
        recursos.useKart();
    }

    @Override
    public void run(){
        useCapacete();
        useKart();
    }
}
