import java.util.Random;

public class Pessoa{
    private String nome;
    private int idade;
    private Kartodromo kartodromo;
    private int capacete;
    private int kart;
    private int tempo;
    private int id;

    public Pessoa(String nome, Kartodromo recursos, int idade, int id){
        this.tempo = this.getRandomInt(10, 30);
        this.nome = nome;
        this.idade = idade;
        this.kartodromo = recursos;
        this.capacete = -1;
        this.kart = -1;
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
        if(this.kartodromo.getCapacetes() > 0){
            this.capacete = this.kartodromo.useCapacete();;
        }
        else{
            this.capacete = this.kartodromo.releaseCapacete(this.kart);
        }
    }

    private void useKart(){
        if(this.kartodromo.getKarts() > 0){    
            this.kart = this.kartodromo.useKart();;
        }
        else{
            this.kart = this.kartodromo.releaseKart(this.kart);
        }
    }

    public int getTime(){
        return this.tempo;
    }

    public void finishRun(){
        this.capacete = this.kartodromo.releaseCapacete(this.kart);
        this.kart = this.kartodromo.releaseKart(this.kart);
    }

    public void startRun(){
        while(this.capacete == -1 && this.kart == -1){
            
            if (this.idade < 18){
                useCapacete();
                useKart();
            }
            else{
                useKart();
                useCapacete();    
            }
        }
        this.kartodromo.addPilot(this.id);
    }

    private int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

}
