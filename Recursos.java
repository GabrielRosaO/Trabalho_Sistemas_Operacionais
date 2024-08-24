import java.util.*;

public class Recursos {
    private int capacetes;
    private int nPessoas = 0;
    private int karts;
    final private int day = 480;
    private Map<Integer, Integer> pilotos = new HashMap<Integer, Integer>();

    public Recursos(int capacetes, int karts){
        this.capacetes = capacetes;
        this.karts = karts;
    }
    
    public void useCapacete(){
        --this.capacetes;
    }

    public void useKart(){
        --this.karts;
    }

    public void increasePessoa(){
        ++this.nPessoas;
    }

    public int getPessoa(){
        return this.nPessoas;
    }

    public void putPessoa(int id){
        pilotos.put(id, 0);
    }

    public void countTime(){
        pilotos.forEach((id, time) -> {
            ++time;
        });
    }

    public void print(){
        pilotos.forEach((id, time) -> {
            System.out.println("Piloto " + id + ":" +time);
        });
    }

    public int getCapacetes(){
        return this.capacetes;
    }

    public int getKarts(){
        return this.karts;
    }
}
