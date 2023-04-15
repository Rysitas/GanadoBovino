/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
/**
 *
 * @author aula8
 */
public class Ganado {
     private int ccodigo;
    private String tipo;
    private float peso;
    private int edad;

    public int getCcodigo() {
        return ccodigo;
    }

    public void setCcodigo(int ccodigo) {
        this.ccodigo = ccodigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
}
