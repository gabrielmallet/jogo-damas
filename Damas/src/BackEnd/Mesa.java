/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package BackEnd;
import console.Console;
import console.Tecla;
import cores.StringColorida;
import java.io.IOException;
import java.util.Arrays;
import mecanicas.Carta;
import mecanicas.Tabuleiro;
/**
 *
 * @author Gabriel
 */


public class Mesa extends Tabuleiro{
    
    //StringColorida Fundo
    static final StringColorida cinza = new StringColorida("⏹", "CINZA");
    static final StringColorida branco = new StringColorida("⏹", "BRANCO");
    static final StringColorida vazio = new StringColorida (" ", "BRANCO");
    
    //StringColorida Numeros
    static final StringColorida um = new StringColorida("1", "BRANCO");
    static final StringColorida dois = new StringColorida("2", "BRANCO");
    static final StringColorida tres = new StringColorida("3", "BRANCO");
    static final StringColorida quatro = new StringColorida("4", "BRANCO");
    static final StringColorida cinco = new StringColorida("5", "BRANCO");
    static final StringColorida seis = new StringColorida("6", "BRANCO");
    static final StringColorida sete = new StringColorida("7", "BRANCO");
    static final StringColorida oito = new StringColorida("8", "BRANCO");
    
    //Cartas
    static final Carta fundo = new Bonecos (cinza);
    static final Carta jogador1 = new PecaAzul();
    static final Carta jogador2 = new PecaVermelha();
    
    //Metodo Tabuleiro
    public Mesa (){
        super(10, 10, fundo);
        
        //Fundo Pintado
        setFundo(0, 0, new Bonecos (vazio));
        
        setFundo(9, 0, new Bonecos (vazio));
        setFundo(9, 1, new Bonecos (vazio));
        setFundo(9, 2, new Bonecos (vazio));
        setFundo(9, 3, new Bonecos (vazio));
        setFundo(9, 4, new Bonecos (vazio));
        setFundo(9, 5, new Bonecos (vazio));
        setFundo(9, 6, new Bonecos (vazio));
        setFundo(9, 7, new Bonecos (vazio));
        setFundo(9, 8, new Bonecos (vazio));
        setFundo(9, 9, new Bonecos (vazio));
        
        setFundo(0, 9, new Bonecos (vazio));
        setFundo(1, 9, new Bonecos (vazio));
        setFundo(2, 9, new Bonecos (vazio));
        setFundo(3, 9, new Bonecos (vazio));
        setFundo(4, 9, new Bonecos (vazio));
        setFundo(5, 9, new Bonecos (vazio));
        setFundo(6, 9, new Bonecos (vazio));
        setFundo(7, 9, new Bonecos (vazio));
        setFundo(8, 9, new Bonecos (vazio));
        setFundo(9, 9, new Bonecos (vazio));
        
        setFundo(1, 1, new Bonecos (branco));
        setFundo(1, 3, new Bonecos (branco));
        setFundo(1, 5, new Bonecos (branco));
        setFundo(1, 7, new Bonecos (branco));
        
        setFundo(2, 2, new Bonecos (branco));
        setFundo(2, 4, new Bonecos (branco));
        setFundo(2, 6, new Bonecos (branco));
        setFundo(2, 8, new Bonecos (branco));
        
        setFundo(3, 1, new Bonecos (branco));
        setFundo(3, 3, new Bonecos (branco));
        setFundo(3, 5, new Bonecos (branco));
        setFundo(3, 7, new Bonecos (branco));
        
        setFundo(4, 2, new Bonecos (branco));
        setFundo(4, 4, new Bonecos (branco));
        setFundo(4, 6, new Bonecos (branco));
        setFundo(4, 8, new Bonecos (branco));
        
        setFundo(5, 1, new Bonecos (branco));
        setFundo(5, 3, new Bonecos (branco));
        setFundo(5, 5, new Bonecos (branco));
        setFundo(5, 7, new Bonecos (branco));
        
        setFundo(6, 2, new Bonecos (branco));
        setFundo(6, 4, new Bonecos (branco));
        setFundo(6, 6, new Bonecos (branco));
        setFundo(6, 8, new Bonecos (branco));
        
        setFundo(7, 1, new Bonecos (branco));
        setFundo(7, 3, new Bonecos (branco));
        setFundo(7, 5, new Bonecos (branco));
        setFundo(7, 7, new Bonecos (branco));
        
        setFundo(8, 2, new Bonecos (branco));
        setFundo(8, 4, new Bonecos (branco));
        setFundo(8, 6, new Bonecos (branco));
        setFundo(8, 8, new Bonecos (branco));
        
        //Numeros no tabuleiro
        setFundo(0, 1, new Bonecos (um));
        setFundo(0, 2, new Bonecos (dois));
        setFundo(0, 3, new Bonecos (tres));
        setFundo(0, 4, new Bonecos (quatro));
        setFundo(0, 5, new Bonecos (cinco));
        setFundo(0, 6, new Bonecos (seis));
        setFundo(0, 7, new Bonecos (sete));
        setFundo(0, 8, new Bonecos (oito));

        setFundo(1, 0, new Bonecos (um));
        setFundo(2, 0, new Bonecos (dois));
        setFundo(3, 0, new Bonecos (tres));
        setFundo(4, 0, new Bonecos (quatro));
        setFundo(5, 0, new Bonecos (cinco));
        setFundo(6, 0, new Bonecos (seis));
        setFundo(7, 0, new Bonecos (sete));
        setFundo(8, 0, new Bonecos (oito));
        
        //Peças JogadoresAzul
        colocaCarta(1,1, jogador1);
        colocaCarta(1,3, jogador1);
        colocaCarta(1,5, jogador1);
        colocaCarta(1,7, jogador1);
        colocaCarta(2,2, jogador1);
        colocaCarta(2,4, jogador1);
        colocaCarta(2,6, jogador1);
        colocaCarta(2,8, jogador1);
        colocaCarta(3,1, jogador1);
        colocaCarta(3,3, jogador1);
        colocaCarta(3,5, jogador1);
        colocaCarta(3,7, jogador1);
        
        //Peças JogadoresVermelho
        colocaCarta(8,2, jogador2);
        colocaCarta(8,4, jogador2);
        colocaCarta(8,6, jogador2);
        colocaCarta(8,8, jogador2);
        colocaCarta(7,1, jogador2);
        colocaCarta(7,3, jogador2);
        colocaCarta(7,5, jogador2);
        colocaCarta(7,7, jogador2);
        colocaCarta(6,2, jogador2);
        colocaCarta(6,4, jogador2);
        colocaCarta(6,6, jogador2);
        colocaCarta(6,8, jogador2);
    }
    
    
}
