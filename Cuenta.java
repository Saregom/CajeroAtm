public class Cuenta {
    private String numCuenta;
    private float saldo, cantRetiros;
    private Tarjeta tarjeta;
    
    public Cuenta(String j, String i){
        this.numCuenta = "12345678"+j+i;
        this.saldo = (int)(Math.random()*1000000)+1;
        this.tarjeta = new Tarjeta(i); 
    }

    public String getNumCuenta(){
        return this.numCuenta;
    }

    public float getSaldo(){
        return this.saldo;
    }
    
    public Tarjeta getTarjeta(){
        return this.tarjeta;
    }

    public void retirar(float cant){
        this.saldo-=cant;
        this.cantRetiros+=cant;
    }

    public boolean verificarSaldo(float cant){
        if(cant <= this.saldo){
            return true;
        }else{
            return false;
        }
    }

    public void consignar(float cant){
        this.saldo+=cant;
    }

    public boolean maxRetirosSuperada(float cant){
        if(this.cantRetiros+cant > 1000000){
            return true;
        }
        return false;
    }
}