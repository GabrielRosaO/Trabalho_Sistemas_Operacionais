import java.util.*;

public class Recursos{
    private int capacetes;
    private int karts;
    final private int day;
    private Map<Integer, Tempo> pilotos;
    private int nPessoas;
    private Pessoa[] pessoas;
    private ArrayList<Integer> criancas14;
    private ArrayList<Integer> outros;
    private ArrayList<Integer> running;
    private int tempo;

    public Recursos(int capacetes, int karts, int day){
        this.capacetes = capacetes;
        this.karts = karts;
        this.nPessoas= getRandomInt(25,50);
        this.pessoas = new Pessoa[nPessoas];
        this.pilotos = new HashMap<Integer, Tempo>();
        this.criancas14 = new ArrayList<Integer>();
        this.outros = new ArrayList<Integer>();
        this.day = day;
        this.tempo = 0;
        this.running = new ArrayList<Integer>();
    }
    
    public void useCapacete(){
        --this.capacetes;
    }

    public void useKart(){
        --this.karts;
    }

    public void releaseKart(){
        ++this.karts;
    }

    public void releaseCapacete(){
        ++this.capacetes;
    }

    public void print(){
        pilotos.forEach((id, time) -> {
            System.out.println("Piloto " + id + " de " + pessoas[id].getIdade() + " anos: " + time);
        });
    }

    public int getCapacetes(){
        return this.capacetes;
    }

    public int getKarts(){
        return this.karts;
    }

    public void addPilot(int id){
        running.add(id);
        Tempo temp = pilotos.get(id);
        temp.setTempoDeEntrada(tempo);
        pilotos.replace(id, temp);
    }

    public void removePilot(int id){
        running.remove(running.indexOf(id));
    }

    public void run(){
        for (int i = 0; i < nPessoas; ++i){
            int idade = getRandomInt(10, 60);
            pessoas[i] = new Pessoa(""+i, this, idade, i);
            pilotos.put(i, new Tempo(0));
            if (idade <= 14){
                criancas14.add(i);
            }
            else {
                outros.add(i);
            }
        }
        
        calculateDay();

        print();
    }

    private void calculateDay(){
        for(tempo = 0; tempo <= day; ++tempo){
            System.out.println(tempo);
            synchronized (addPilots()){
                try{
                    wait();
                }
                catch(InterruptedException e)
            }
            countTime();

            removePilots();
        }
    }

    private void addPilots(){
        for(int j = running.size(); j < 10; ++j){
            if(criancas14.size() > 0){
                pessoas[(criancas14.get(0))].start();
                criancas14.remove(0);
            }
            else if (outros.size() > 0){
                pessoas[(outros.get(0))].start();
                outros.remove(0);
            }
        }
    }

    private void countTime(){
        for (int j = 0; j < running.size(); ++j){
            Tempo temp = pilotos.get(running.get(j));
            temp.setTempoDeCorrida(temp.getTempoDeCorrida()+1);
            pilotos.replace(running.get(j), temp);
        }
    }

    private void removePilots(){
        for (int j = running.size()-1; j > -1; --j){
            if(pilotos.get(running.get(j)).getTempoDeCorrida() >= pessoas[running.get(j)].getTime()){
                System.out.println("A");
                pessoas[running.get(j)].finishRun();
                running.remove(j);
            }
        }
    }

    private static int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
