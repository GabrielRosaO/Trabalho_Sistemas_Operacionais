import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Kartodromo{
    final private int day;
    private Map<Integer, Tempo> pilotos;
    private ArrayList<Pessoa> pessoas;
    private ArrayList<Integer> criancas14;
    private ArrayList<Integer> outros;
    private ArrayList<Integer> running;
    private ArrayList<Integer> capacetesLivres;
    private ArrayList<Integer> kartsLivres;
    private Map<Integer, Integer> capacetes;
    private Map<Integer, Integer> karts;
    private int tempo;

    public Kartodromo(int capacetes, int karts, int day){
        this.karts = new HashMap<Integer, Integer>();
        this.capacetes = new HashMap<Integer, Integer>();
        this.capacetesLivres = new ArrayList<Integer>();
        this.kartsLivres = new ArrayList<Integer>();
        for(int i = 0; i < capacetes; i++){
            this.capacetesLivres.add(i);
            this.capacetes.put(i, 0);
        }
        for(int i = 0; i < karts; i++){
            this.kartsLivres.add(i);
            this.karts.put(i, 0);
        }
        this.pessoas = new ArrayList<Pessoa>();
        this.pilotos = new HashMap<Integer, Tempo>();
        this.criancas14 = new ArrayList<Integer>();
        this.outros = new ArrayList<Integer>();
        this.day = day;
        this.tempo = 0;
        this.running = new ArrayList<Integer>();
    }
    
    public int useCapacete(){
        int id = capacetesLivres.get(0);
        int temp = this.capacetes.get(id);
        this.capacetes.replace(id, temp + 1);
        this.capacetesLivres.remove(0);
        return id;
    }

    public int useKart(){
        int id = kartsLivres.get(0);
        int temp = this.karts.get(id);
        this.karts.replace(id, temp + 1);
        this.kartsLivres.remove(0);
        return id;
    }

    public int releaseKart(int id){
        this.kartsLivres.add(id);
        return -1;
    }

    public int releaseCapacete(int id){
        this.capacetesLivres.add(id);
        return -1;
    }

    public void print() throws IOException{
        System.getProperty("user.home");
        
        FileWriter arq = new FileWriter("C:\\Users\\Usuario\\Documents\\SO\\Trabalho_final_SO\\Relatório.txt");
        try (PrintWriter gravarArq = new PrintWriter(arq)) {
            int totalClientes = pilotos.size();
            int clientesNaoAtendidos = totalClientsNotServed();
            int tempoMedio = 0;

            gravarArq.println("------------------RELATÓRIO DO DIA--------------------");
            gravarArq.println("TOTAL DE CLIENTES ATENDIDOS:   "  + totalClientes);
            gravarArq.println("  ");
            gravarArq.println("CLIENTES ATENDIDOS: ");
            int i = 0;
            for(i = 0; i < pilotos.size()-clientesNaoAtendidos; ++i){
                tempoMedio += pilotos.get(i).getTempoDeEspera();
                gravarArq.println("Nome: " + pessoas.get(i).getNome() + "  " + "Idade: " + pessoas.get(i).getIdade() + "-> Tempo que ficou na pista:  " + pessoas.get(i).geTime() + " minutos");
                //System.out.println("Piloto " + i + " de " + pessoas.get(i).getIdade() + " anos: " + pilotos.get(i) + " tempo comprado (" + pessoas.get(i).getTime() + ")");
            }
            tempoMedio = tempoMedio / pilotos.size();

            gravarArq.println(" ");
            gravarArq.println("TOTAL DE CLIENTE QUE NÃO FORAM ATENDIDOS:   " + clientesNaoAtendidos);
            gravarArq.println(" ");
            gravarArq.println("CLIENTES NÂO ATENDIDOS: ");

            for(int j = i; j < pilotos.size(); ++j){
                gravarArq.println("Nome: " + pessoas.get(j).getNome() + "  " + "Idade: " + pessoas.get(j).getIdade() + "-> Tempo que iria ficar na pista:  " + pessoas.get(j).geTime() + " minutos");

            }
            gravarArq.println(" ");
            //System.out.println("Total de clientes: " + totalClientes);
            //System.out.println("Clientes nao atendidos: " + clientesNaoAtendidos);
            gravarArq.println("TEMPO MÉDIO DE ESPERA : " + tempoMedio + " minutos");
            //System.out.println("Tempo medio de espera: " + tempoMedio);

            gravarArq.println("   ");
            gravarArq.println("QUANTIDADE QUE CADA CAPACETE FOI UTILIZADO: ");
            capacetes.forEach((id, time) -> {
                gravarArq.println("Capacete " + id + " usado " + time);
                //System.out.println("Capacete " + id + " usado " + time);
            });

            gravarArq.println("   ");
            gravarArq.println("QUANTIDADE QUE CADA KART FOI UTILIZADO: ");
            karts.forEach((id, time) -> {
                gravarArq.println("Kart " + id + " usado " + time);
                //System.out.println("Kart " + id + " usado " + time);
            });


            gravarArq.println("--------------------FIM RELATÓRIO------------------");
            gravarArq.close();
        }

        arq.close();
    }

    public int getCapacetes(){
        return this.capacetesLivres.size();
    }
    
    public int getKarts(){
        return this.kartsLivres.size();
    }

    public int totalClientsNotServed(){
        int clientesNaoAtendidos = 0;
        for (int i = 0; i < pilotos.size(); ++i){
            if (pilotos.get(i).getTempoDeCorrida() < pessoas.get(i).geTime()){
                ++clientesNaoAtendidos;
            }
        }
        return clientesNaoAtendidos;
    }

    public void addPilot(int id){
        running.add(id);
        Tempo temp = pilotos.get(id);
        temp.setTempoDeEspera(tempo);
        pilotos.replace(id, temp);
    }

    private void generatePeople(){
        for (int i = 0; i < getRandomInt(2, 5); ++i){
            int idade = getRandomInt(10, 60);
            int id = pessoas.size();
            Pessoa temp = new Pessoa("" + id, this, idade, id);
            pessoas.add(temp);
            pilotos.put(id, new Tempo(tempo));
            if (idade <= 14){
                criancas14.add(id);
            }
            else {
                outros.add(id);
            }
        }
    }
    
    public void run(){
        calculateDay();
        try{
            print();
        }catch (IOException error){
            System.out.println("Não foi possível criar ou escrever no arquivo. Erro: " + error);;
        }
    }

    private void calculateDay(){
        for(tempo = 0; tempo <= day; ++tempo){
            System.out.println(tempo);
            if(tempo % 5 == 0){
                generatePeople();
            }
            try{
            addPilots();
            }catch(InterruptedException error){
                System.out.println("Deu merda mlk: " + error);
            }
            countTime();
            removePilots();
        }
    }

    private void addPilots() throws InterruptedException {
        for(int j = running.size(); j < 10; j++){
            if(criancas14.size() > 0){
                int temp = criancas14.get(0);
                Thread pessoaThread = new Thread(){
                    @Override
                    public void run(){
                        pessoas.get(temp).startRun();
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
                        pessoas.get(temp).startRun();
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
            if(pilotos.get(running.get(j)).getTempoDeCorrida() >= pessoas.get(running.get(j)).getTime()){
                int temp = running.get(j);
                removeList.add(j);
                pessoas.get(temp).finishRun();
            }
        }
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
