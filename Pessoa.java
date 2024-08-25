import java.util.Random;

public class Pessoa {
    private String nome;
    private int idade;
    private Recursos recursos;
    private boolean capacete;
    private boolean kart;
    private int tempo;
    private int id;

    public Pessoa(String nome, Recursos recursos, int idade, int id){
        this.tempo = this.getRandomInt(30, 60);
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

    public int geTime(){
        return this.tempo;
    }

    public int getIdade(){
        return this.idade;
    }

    private void useCapacete(){
        if(this.recursos.getCapacetes() > 0){
            this.recursos.useCapacete();
            this.capacete = true;
        }
        else{
            this.recursos.releaseCapacete();
        }
    }

    private void useKart(){
        if(this.recursos.getKarts() > 0){
            this.recursos.useKart();
            this.capacete = true;
        }
        else{
            this.recursos.releaseKart();
        }
    }

    public int getTime(){
        return this.tempo;
    }

    public void finishRun(){
        this.recursos.releaseCapacete();
        this.recursos.releaseKart();
        this.recursos.b();
    }

    public void startRun(){
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
        this.recursos.b();
    }

    private int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
