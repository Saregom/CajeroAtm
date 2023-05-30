public class Cliente{
    private String nombre;
    private Cuenta[] cuentas;
    
    public Cliente(String j){
        this.nombre = "Dan*** Mau***** Gai***";
		this.cuentas = new Cuenta[3];
        for(int i=0; i<3; i++){
            this.cuentas[i] = new Cuenta(j, String.valueOf(i)); 
        }
    }
    
    public String getNombre() {
    	return this.nombre;
    }

    public Cuenta[] getCuentas() {
    	return this.cuentas;
    }
}