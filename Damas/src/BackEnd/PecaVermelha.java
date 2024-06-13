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
public class PecaVermelha extends Carta{
    
    static final StringColorida vermelho = new StringColorida("⏺", "VERMELHO");
    static final StringColorida rainhavermelho = new StringColorida("⏏", "AZUL");
    
    //Peça do Jogador Vermelho
    public PecaVermelha(){
        super(vermelho, rainhavermelho);
    }
    
}
