public class Tarjeta {
    private String clave;
    private Boolean bloqueada;
    
    public Tarjeta(String i){
        this.clave = "123"+i;
        this.bloqueada = false;
    }

    public String getClave(){
      return this.clave;
    }

    public Boolean getBloqueada(){
      return this.bloqueada;
    }

    public void cambiarClave(String num){
      this.clave = num;
    }

    public void bloquear(){
      this.bloqueada = true;
    } 
}
//519
//431