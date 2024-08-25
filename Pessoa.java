import java.util.Random;

public class Pessoa extends Thread{
    private String nome;
    private int idade;
    private Recursos recursos;
    private boolean capacete;
    private boolean kart;
    private int tempo;
    private int id;

    public Pessoa(String nome, Recursos recursos, int idade, int id){
        this.tempo = this.getRandomInt(10, 60);
        this.nome = nome;
        this.idade = idade;
        this.recursos = recursos;
        this.capacete = false;
        this.kart = false;
        this.id = id;
    }

    public String getNome(){
        return this.nome;
    }

    public int getIdade(){
        return this.idade;
    }

    private void useCapacete(){
        this.recursos.useCapacete();
        if(this.recursos.getCapacetes() >= 0){
            if(this.recursos.getCapacetes() < 0){
                this.recursos.releaseCapacete();
                return;
            }
            this.capacete = true;
        }
    }

    private void useKart(){
        this.recursos.useKart();
        if(this.recursos.getKarts() >= 0){
            if(this.recursos.getKarts() < 0){
                this.recursos.releaseKart();
                return;
            }
            this.capacete = true;
        }
    }

    public int getTime(){
        return this.tempo;
    }

    public void finishRun(){
        this.recursos.releaseCapacete();
        this.recursos.releaseKart();
    }

    @Override
    public void run(){
        while(this.capacete == false && this.kart == false){
            if (this.idade < 18){
                useCapacete();
                useKart();
            }
            else{
                useKart();
                useCapacete();    
            }
        }
        this.recursos.addPilot(this.id);
    }

    private int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
