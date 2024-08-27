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

    public int getTime(){
        return this.tempo;
    }

    public int getIdade(){
        return this.idade;
    }

    //Utiliza um capacete e, se ja estiver sendo usado ao chamar novamente o método, o capacete é liberado
    private void useCapacete(){
        if(this.kartodromo.getCapacetes() > 0){
            this.capacete = this.kartodromo.useCapacete();;
        }
        else{
            this.capacete = this.kartodromo.releaseCapacete(this.kart);
        }
    }
    //Utiliza um kart e, se ja estiver sendo usado ao chamar novamente o método, o kart é liberado
    private void useKart(){
        if(this.kartodromo.getKarts() > 0){    
            this.kart = this.kartodromo.useKart();;
        }
        else{
            this.kart = this.kartodromo.releaseKart(this.kart);
        }
    }

    //Indica a finalização da corrida da pessoa(thread) e libera o capacete e kart que está sendo utilizado
    public void finishRun(){
        this.capacete = this.kartodromo.releaseCapacete(this.kart);
        this.kart = this.kartodromo.releaseKart(this.kart);
    }

    //Indica o inico da corrida. A pessoa(thread) só sai da fila e começa a corrida se existirem pelo menos 1 kart E um capacete livres
    //Após a pessoa pegar os dois recursos ela é adicionada na pista do kartodromo
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

    //Pega um número aleatório
    private int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

}
