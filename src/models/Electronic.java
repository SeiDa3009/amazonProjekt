package models;

public abstract class Electronic extends Article {

    // fields
    private String _powerSupply;
    private String _cpu;
    private int _countCPUs;

    // getter/setter
    public String getPowerSupply() {
        return _powerSupply;
    }
    public void setPowerSupply(String powerSupply) {
        this._powerSupply = powerSupply;
    }
    public String getCPU() {
        return _cpu;
    }
    public void setCPU(String cpu) {
        this._cpu = cpu;
    }
    public int getCountCPUs() {
        return _countCPUs;
    }
    public void setCountCPUs(int countCPUs) {
        this._countCPUs = countCPUs;
    }

    // ctors
    public Electronic(){
        this(0, "", 0.0, "", "", "", 0);
    }
    public Electronic(int articleID, String name, double price, String description, String powerSupply, String cpu, int countCPUs){
        super(articleID, name, price, description);

        this.setPowerSupply(powerSupply);
        this.setCPU(cpu);
        this.setCountCPUs(countCPUs);
    }

    // other methods
    @Override
    public String toString(){
        return super.toString() + " " + this.getPowerSupply() + " " + this.getCPU() + " " + this.getCountCPUs();
    }
}
