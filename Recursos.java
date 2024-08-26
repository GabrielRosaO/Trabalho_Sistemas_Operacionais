import java.util.*;
import java.util.concurrent.Semaphore;

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
    //private int signal;
    Semaphore semaforo = new Semaphore(10);

    public Recursos(int capacetes, int karts, int day){
        this.capacetes = capacetes;
        this.karts = karts;
        this.nPessoas= getRandomInt(100, 150);
        this.pessoas = new Pessoa[nPessoas];
        this.pilotos = new HashMap<Integer, Tempo>();
        this.criancas14 = new ArrayList<Integer>();
        this.outros = new ArrayList<Integer>();
        this.day = day;
        this.tempo = 0;
        //this.signal = 0;
        this.running = new ArrayList<Integer>();
    }
    
    public void useCapacete(){
        this.capacetes--;
    }

    public void useKart(){
        this.karts--;
    }

    public void releaseKart(){
        this.karts++;
    }

    public void releaseCapacete(){
        this.capacetes++;
    }

    public void print(){
        pilotos.forEach((id, time) -> {
            System.out.println("Piloto " + id + " de " + pessoas[id].getIdade() + " anos: " + time + " tempo comprado (" + pessoas[id].getTime() + ")");
        });
    }

    public int getCapacetes(){
        return this.capacetes;
    }
    
    /*public void a(){
        this.signal++;
    }

    public void b(){
        this.signal--;
    }*/

    public int getKarts(){
        return this.karts;
    }

    public void addPilot(int id){
        running.add(id);
        Tempo temp = pilotos.get(id);
        temp.setTempoDeEntrada(tempo);
        pilotos.replace(id, temp);
    }

    public void run(){
        for (int i = 0; i < nPessoas; ++i){
            int idade = getRandomInt(10, 60);
            pessoas[i] = new Pessoa("" + i, this, idade, i);
            pilotos.put(i, new Tempo(-1));
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
            try{
            addPilots();
            }catch(InterruptedException error){
                System.out.println("Deu merda mlk: " + error);
            }
            /*while(signal != 0){

                //System.out.println("tempo: " + tempo);
                //System.out.println("running size: " + running.size());
                //System.out.println("create: " + signal);
            }*/
            countTime();
            removePilots();
        }
    }

    private void addPilots() throws InterruptedException {
        for(int j = running.size(); j < 10; j++){
            //a();
            if(criancas14.size() > 0){
                int temp = criancas14.get(0);
                Thread pessoaThread = new Thread(){
                    @Override
                    public void run(){
                        pessoas[temp].startRun();
                    }};
                pessoaThread.start();
                pessoaThread.join();
                
                criancas14.remove(0);

            }
            else if (outros.size() > 0){
                int temp = outros.get(0);
                Thread pessoaThread = new Thread(){
                    @Override
                    public void run(){
                        pessoas[temp].startRun();
                    }};
                pessoaThread.start();
                pessoaThread.join();
                
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
        ArrayList<Integer> removeList = new ArrayList<>();
        for (int j = running.size()-1; j > -1; --j){
            if(pilotos.get(running.get(j)).getTempoDeCorrida() >= pessoas[running.get(j)].getTime()){
                //a();
                int temp = running.get(j);
                removeList.add(j);
                pessoas[temp].finishRun();
                
            }
        }
        /*while(signal != 0){
            //System.out.println("running size: " + running.size());
            //System.out.println("delete: " + signal);
        }*/
        for(int i = 0; i < removeList.size(); ++i){
            int temp = removeList.get(i);
            running.remove(temp);
        }
    }

    private static int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
