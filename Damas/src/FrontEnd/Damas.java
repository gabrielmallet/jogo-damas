/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package FrontEnd;
import console.Console;
import console.Tecla;
import cores.StringColorida;
import BackEnd.Jogo;
import BackEnd.Mesa;
import java.io.IOException;
import java.util.Arrays;
import mecanicas.Carta;
import mecanicas.Tabuleiro;

/**
 *
 * @author Gabriel
 */
public class Damas {
/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu();
    } 
    
    public static void Menu (){
        //Variaveis
        int cont_opc = 0;
        boolean running = true;
        //Instanciando Classe Jogo
        Jogo jogo = new Jogo();
        
        //While Rodando o Menu Principal
        while (running){
            Console.limpaTela();
            //Variaveis
            Tecla atual = null;
            int pontuacao1 = 0;
            int pontuacao2 = 0;
            int estadoAtual = 0;
            Tabuleiro tab = new Mesa();
            switch (cont_opc){
                
                //Opção Iniciar Novo Jogo
                case 0: 
                    Console.println("*******************************************");
                    Console.println("*******************************************");
                    Console.println("██████╗  █████╗ ███╗   ███╗ █████╗ ███████╗");
                    Console.println("██╔══██╗██╔══██╗████╗ ████║██╔══██╗██╔════╝");
                    Console.println("██║  ██║███████║██╔████╔██║███████║███████╗");
                    Console.println("██║  ██║██╔══██║██║╚██╔╝██║██╔══██║╚════██║");
                    Console.println("██████╔╝██║  ██║██║ ╚═╝ ██║██║  ██║███████║");
                    Console.println("╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚═╝  ╚═╝╚══════╝");
                    
                    Console.println("*************══>Novo Jogo<══***************"
                                + "\n****************Carregar Jogo**************"
                                + "\n****************Tutorial*******************"
                                + "\n****************Sair Jogo******************");
                    Console.println("*******************************************");
                    Console.println("                     Espaço para confirmar!");
                atual = Console.getTecla();
                    if (atual == Tecla.DOWN)
                        cont_opc = (cont_opc + 1) %4;
                        else if (atual == Tecla.UP)
                                cont_opc = (cont_opc - 1 + 4) %4;
                        else if (atual == Tecla.ESPACO){
                            jogo.JogoRodando(tab, pontuacao1, pontuacao2, estadoAtual);
                            running = false;
                        }
                    break;
                    
                //Opção Carregar Jogo
                case 1: 
                    Console.println("*******************************************");
                    Console.println("*******************************************");
                    Console.println("██████╗  █████╗ ███╗   ███╗ █████╗ ███████╗");
                    Console.println("██╔══██╗██╔══██╗████╗ ████║██╔══██╗██╔════╝");
                    Console.println("██║  ██║███████║██╔████╔██║███████║███████╗");
                    Console.println("██║  ██║██╔══██║██║╚██╔╝██║██╔══██║╚════██║");
                    Console.println("██████╔╝██║  ██║██║ ╚═╝ ██║██║  ██║███████║");
                    Console.println("╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚═╝  ╚═╝╚══════╝");
                    
                    Console.println("****************Novo Jogo******************"
                                + "\n*************══>Carregar Jogo<══***********"
                                + "\n****************Tutorial*******************"
                                + "\n****************Sair Jogo******************");
                    Console.println("*******************************************");
                    Console.println("                     Espaço para confirmar!");
                atual = Console.getTecla();
                    if (atual == Tecla.DOWN)
                        cont_opc = (cont_opc + 1) %4;
                        else if (atual == Tecla.UP)
                                cont_opc = (cont_opc - 1 + 4) %4;
                    break;
                    
                case 2: 
                    Console.println("*******************************************");
                    Console.println("*******************************************");
                    Console.println("██████╗  █████╗ ███╗   ███╗ █████╗ ███████╗");
                    Console.println("██╔══██╗██╔══██╗████╗ ████║██╔══██╗██╔════╝");
                    Console.println("██║  ██║███████║██╔████╔██║███████║███████╗");
                    Console.println("██║  ██║██╔══██║██║╚██╔╝██║██╔══██║╚════██║");
                    Console.println("██████╔╝██║  ██║██║ ╚═╝ ██║██║  ██║███████║");
                    Console.println("╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚═╝  ╚═╝╚══════╝");
                    
                    Console.println("****************Novo Jogo******************"
                                + "\n****************Carregar Jogo**************"
                                + "\n*************══>Tutorial<══****************"
                                + "\n****************Sair Jogo******************");
                    Console.println("*******************************************");
                    Console.println("                     Espaço para confirmar!");
                atual = Console.getTecla();
                    if (atual == Tecla.DOWN)
                        cont_opc = (cont_opc + 1) %4;
                        else if (atual == Tecla.UP)
                                cont_opc = (cont_opc - 1 + 4) %4;
                        else if (atual == Tecla.ESPACO){
                            Tutorial();
                        }
                    break;
                    
                //Opção Sair do Jogo
                case 3: 
                    Console.println("*******************************************");
                    Console.println("*******************************************");
                    Console.println("██████╗  █████╗ ███╗   ███╗ █████╗ ███████╗");
                    Console.println("██╔══██╗██╔══██╗████╗ ████║██╔══██╗██╔════╝");
                    Console.println("██║  ██║███████║██╔████╔██║███████║███████╗");
                    Console.println("██║  ██║██╔══██║██║╚██╔╝██║██╔══██║╚════██║");
                    Console.println("██████╔╝██║  ██║██║ ╚═╝ ██║██║  ██║███████║");
                    Console.println("╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚═╝  ╚═╝╚══════╝");
                    
                    Console.println("****************Novo Jogo******************"
                                + "\n****************Carregar Jogo**************"
                                + "\n****************Tutorial*******************"
                                + "\n*************══>Sair Jogo<══***************");
                    Console.println("*******************************************");
                    Console.println("                     Espaço para confirmar!");
                atual = Console.getTecla();
                    if (atual == Tecla.DOWN)
                        cont_opc = (cont_opc + 1) %4;
                        else if (atual == Tecla.UP)
                                cont_opc = (cont_opc - 1 + 4) %4;
                        else if (atual == Tecla.ESPACO){
                            running = false;
                            Console.saiDoPrograma();
                        }
                    break;
            }
        }
    }
    
    public static void Tutorial(){
        
        Console.println("");
        Console.println("");
        
        Console.println("           -------REGRAS-------");
        Console.println("As regras deste jogo de damas funcionam basicamente como um jogo de damas comum, com algumas exceções:\n" +
"\n" +
"- Assim como em um jogo de damas, o jogo termina quando um jogador conseguir remover todas as peças do adversário do\n"
                + "tabuleiro, comendo-as.\n" +
"- Uma peça pode se mover apenas uma casa, porém, neste jogo é permitido mover-se para trás.\n" +
"- O jogador pode comer apenas uma peça por vez, mesmo que seja possível comer uma próxima.\n" +
"- Neste jogo, mesmo que você chegue na ponta do tabuleiro inimigo, não é possível transformar uma peça em Rainha.");
        Console.println("");
        Console.println("");
        Console.println("           ------COMANDOS------");
        Console.println("");
        Console.println("ESPAÇO = Confirma"
                + "\nSETAS = Movimento pelo tabuleiro"
                + "\nCTRL = Cancela opção"
                + "\nP = Menu de Pause"
                + "\nEsc = Sai do jogo enquanto esta no Tabuleiro"
                + "\nAperte qualquer tecla para voltar ao menu.");
        
        Tecla atual = Console.getTecla();
    }

}