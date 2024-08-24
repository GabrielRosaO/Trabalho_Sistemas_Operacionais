public class Recursos {
    private int capacetes;
    private int karts;

    public Recursos(int capacetes, int karts){
        this.capacetes = capacetes;
        this.karts = karts;
    }
    
    public void useCapacete(){
        this.capacetes = 9;
    }

    public void useKart(){
        this.capacetes = 9;
    }

    public int getCapacetes(){
        return this.capacetes;
    }

    public int getKarts(){
        return this.karts;
    }
}
