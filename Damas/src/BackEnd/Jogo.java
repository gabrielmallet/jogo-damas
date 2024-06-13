/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd;
import FrontEnd.Damas;
import console.Console;
import console.Tecla;
import cores.StringColorida;
import java.io.IOException;
import java.util.Arrays;
import mecanicas.Carta;
import mecanicas.Tabuleiro;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
/**
 *
 * @author Gabriel
 */
public class Jogo {
    //Variaveis
    int JOGADOR_1 = 1, JOGADOR_2 = 2;
    int estadoAtual = 1;
    
    int pontuacao1 = 0;
    int pontuacao2 = 0;
    
    int linha = 1, coluna = 1;
    int cont_opc = 0;
    
    //Instanciando Metodos externos
    Tabuleiro tab = new Mesa();
    Carta jogador1 = new PecaAzul();
    Carta jogador2 = new PecaVermelha();
    
    //Metodo do JogoFuncionando
    public void JogoRodando(Tabuleiro tab, int pontuacao1, int pontuacao2, int estadoAtual){
        //Instanciando Jogo
        Jogo jogo = new Jogo();
        
        //Variaveis JogoRodando
        boolean running = true;
        Carta fundo = null;
        
        //While movendo pelo tabuleiro
        while(running){
            //Verifica e defini o jogador inicial
            if(estadoAtual == 0)estadoAtual = 1;
            
            //Verifica se o jogo terminou
            jogo.Vencedor(pontuacao1, pontuacao2);
            
            //Escritas na tela
            Console.limpaTela();
            Console.println(tab);
            Console.println("Voce esta na posição ("+coluna+","+linha+")");
            Console.println("");
            Console.println("");
            Console.println("");
            if(estadoAtual == 1)Console.println("Vez do jogador Azul!");
            if(estadoAtual == 2)Console.println("Vez do jogador Vermelho!");
            Tecla atual = Console.getTecla();
            
            //Sair do Programa
            if (atual == Tecla.ESC) Console.saiDoPrograma();
            
            //Comandos de movimento pelo tabuleiro
            if (atual == Tecla.RIGHT) coluna = (coluna + 1) %tab.getTotalColunas();
            if (atual == Tecla.LEFT) coluna = (coluna - 1 + tab.getTotalColunas()) %tab.getTotalColunas();
            if (atual == Tecla.DOWN) linha = (linha + 1) %tab.getTotalLinhas();
            if (atual == Tecla.UP) linha = (linha - 1 + tab.getTotalLinhas()) %tab.getTotalLinhas();
            if (coluna == 0) coluna =1;
            if (linha == 0) linha =1;
            if (coluna == 9) coluna =8;
            if (linha == 9) linha =8;
            
            if (atual == Tecla.P) jogo.Pausa(linha, coluna, linha, coluna, fundo, tab, jogador1, jogador2, pontuacao1, pontuacao2, estadoAtual);
            
            //Instancia movimento de peça azul e vermelha
            if (atual == Tecla.ESPACO) fundo = tab.getPilha(linha, coluna).verTopo();
            
            if(estadoAtual == 1){
                if (fundo instanceof PecaAzul){
                    jogo.MoverPecaAzul(linha, coluna, tab, pontuacao1, pontuacao2, estadoAtual);
                }
            }
            if(estadoAtual == 2){
                if (fundo instanceof PecaVermelha){
                    jogo.MoverPecaVermelha(linha, coluna, tab, pontuacao1, pontuacao2, estadoAtual);
                }
            }
        }
    }
    
    //Metodo para mover a peça azul
    public void MoverPecaAzul(int linha, int coluna, Tabuleiro tab, int pontuacao1, int pontuacao2, int estadoAtual){
        boolean movendo = true;
        
        while(movendo){
                    //Instanciando Metodos
                    Jogo jogo = new Jogo();
                    
                    //Variaveis
                    int movlinha;
                    int movcoluna;
                    Tecla atual = null;
                    Carta fundo = null;
                    
                    //Desenhos na tela
                    Console.limpaTela();
                    Console.println(tab);
                    
                    //SwitchCase com opções para movimento
                    switch(cont_opc){
                        case 0:
                            movlinha = linha + 1; movcoluna = coluna + 1;
                            Console.println("Move se para a coordenada ("+movcoluna+","+movlinha+")");
                            atual = Console.getTecla();
                            
                            //Cancela movimento de peça
                            if (atual == Tecla.CTRL){
                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                            }
                            //Muda opção de movimento e seleciona o movimento
                                if (atual == Tecla.DOWN) cont_opc = (cont_opc + 1) %4;
                                    else if (atual == Tecla.UP) cont_opc = (cont_opc - 1 + 4) %4;
                                        //Confirma a posição que o jogador deseja se mover
                                        else if (atual == Tecla.ESPACO){
                                            fundo = tab.getPilha(movlinha, movcoluna).verTopo();
                                            if (fundo instanceof PecaAzul || movlinha == 0 || movcoluna == 0 || movlinha == 9 || movcoluna == 9) 
                                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                            //Verifica se a proxima posição é uma peça inimiga e pula para a proxima posição removendo a peça anterior
                                                if (fundo instanceof PecaVermelha){
                                                    movlinha = linha + 2; movcoluna = coluna + 2;
                                                    //Verifica se e realmente possivel comer a peça ou se na proxima posição existe outra peça inimiga
                                                    fundo = tab.getPilha(movlinha, movcoluna).verTopo();
                                                        if(fundo instanceof PecaVermelha || fundo instanceof PecaAzul || movlinha == 0 || movcoluna == 0 || movlinha == 9 || movcoluna == 9)
                                                            jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                                                else{
                                                                    tab.pegaPilha(linha + 1, coluna + 1);
                                                                    pontuacao1 = pontuacao1 + 1;
                                                                    jogo.ColocaPeca(linha, coluna, movlinha, movcoluna, jogador1, tab);
                                                                    estadoAtual = 2;
                                                                    jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                                                }
                                                                //Move a peça para a posição desejada
                                                    }else{
                                                        jogo.ColocaPeca(linha, coluna, movlinha, movcoluna, jogador1, tab);
                                                        estadoAtual = 2;
                                                        jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                                    }
                                        }
                            break;
                        case 1:
                            movlinha = linha + 1; movcoluna = coluna - 1;
                            Console.println("Move se para a coordenada ("+movcoluna+","+movlinha+")");
                            atual = Console.getTecla();
                            
                            //Cancela movimento de peça
                            if (atual == Tecla.CTRL){
                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);   
                            }
                            //Muda opção de movimento e seleciona o movimento
                                if (atual == Tecla.DOWN) cont_opc = (cont_opc + 1) %4;
                                    else if (atual == Tecla.UP) cont_opc = (cont_opc - 1 + 4) %4;
                                    //Confirma a posição que o jogador deseja se mover
                                        else if (atual == Tecla.ESPACO){
                                            fundo = tab.getPilha(movlinha, movcoluna).verTopo();
                                            if (fundo instanceof PecaAzul || movlinha == 0 || movcoluna == 0 || movlinha == 9 || movcoluna == 9) 
                                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                            //Verifica se a proxima posição é uma peça inimiga e pula para a proxima posição removendo a peça anterior
                                                if (fundo instanceof PecaVermelha){
                                                    movlinha = linha + 2; movcoluna = coluna - 2;
                                                    //Verifica se e realmente possivel comer a peça ou se na proxima posição existe outra peça inimiga
                                                    fundo = tab.getPilha(movlinha, movcoluna).verTopo();
                                                        if(fundo instanceof PecaVermelha || fundo instanceof PecaAzul || movlinha == 0 || movcoluna == 0 || movlinha == 9 || movcoluna == 9)
                                                            jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                                                else{
                                                                    tab.pegaPilha(linha + 1, coluna - 1);
                                                                    pontuacao1 = pontuacao1 + 1;
                                                                    jogo.ColocaPeca(linha, coluna, movlinha, movcoluna, jogador1, tab);
                                                                    estadoAtual = 2;
                                                                    jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                                                }
                                                            //Move a peça para a posição desejada
                                                }else{
                                                    jogo.ColocaPeca(linha, coluna, movlinha, movcoluna, jogador1, tab);
                                                    estadoAtual = 2;
                                                    jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                                }
                                        }
                            break;
                        case 2:
                            movlinha = linha - 1; movcoluna = coluna -1;
                            Console.println("Move se para a coordenada ("+movlinha+","+movcoluna+")");
                            atual = Console.getTecla();
                            
                            //Cancela movimento de peça
                            if (atual == Tecla.CTRL){
                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);   
                            }
                            //Muda opção de movimento e seleciona o movimento
                                if (atual == Tecla.DOWN) cont_opc = (cont_opc + 1) %4;
                                    else if (atual == Tecla.UP) cont_opc = (cont_opc - 1 + 4) %4;
                                    //Confirma a posição que o jogador deseja se mover
                                        else if (atual == Tecla.ESPACO){
                                            fundo = tab.getPilha(movlinha, movcoluna).verTopo();
                                                if (fundo instanceof PecaAzul || movlinha == 0 || movcoluna == 0 || movlinha == 9 || movcoluna == 9) 
                                                    jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                                //Verifica se a proxima posição é uma peça inimiga e pula para a proxima posição removendo a peça anterior
                                                    if (fundo instanceof PecaVermelha){
                                                        movlinha = linha - 2; movcoluna = coluna - 2;
                                                        //Verifica se e realmente possivel comer a peça ou se na proxima posição existe outra peça inimiga
                                                        fundo = tab.getPilha(movlinha, movcoluna).verTopo();
                                                            if(fundo instanceof PecaVermelha || fundo instanceof PecaAzul || movlinha == 0 || movcoluna == 0 || movlinha == 9 || movcoluna == 9)
                                                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                                                    else{
                                                                        tab.pegaPilha(linha - 1, coluna - 1);
                                                                        pontuacao1 = pontuacao1 + 1;
                                                                        jogo.ColocaPeca(linha, coluna, movlinha, movcoluna, jogador1, tab);
                                                                        estadoAtual = 2;
                                                                        jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                                                    }
                                                    //Move a peça para a posição desejada 
                                                    }else{
                                                        jogo.ColocaPeca(linha, coluna, movlinha, movcoluna, jogador1, tab);
                                                        estadoAtual = 2;
                                                        jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                                    }
                                        }
                            break;
                        case 3:
                            movlinha = linha - 1; movcoluna = coluna + 1;
                            Console.println("Move se para a coordenada ("+movcoluna+","+movlinha+")");
                            atual = Console.getTecla();
                            
                            //Cancela movimento de peça
                            if (atual == Tecla.CTRL){
                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                            }
                            //Muda opção de movimento e seleciona o movimento
                                if (atual == Tecla.DOWN) cont_opc = (cont_opc + 1) %4;
                                    else if (atual == Tecla.UP) cont_opc = (cont_opc - 1 + 4) %4;
                                    //Confirma a posição que o jogador deseja se mover
                                        else if (atual == Tecla.ESPACO){
                                            fundo = tab.getPilha(movlinha, movcoluna).verTopo();
                                            if (fundo instanceof PecaAzul || movlinha == 0 || movcoluna == 0 || movlinha == 9 || movcoluna == 9) 
                                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                            //Verifica se a proxima posição é uma peça inimiga e pula para a proxima posição removendo a peça anterior
                                            if (fundo instanceof PecaVermelha){
                                                movlinha = linha - 2; movcoluna = coluna + 2;
                                                //Verifica se e realmente possivel comer a peça ou se na proxima posição existe outra peça inimiga
                                                fundo = tab.getPilha(movlinha, movcoluna).verTopo();
                                                    if(fundo instanceof PecaVermelha || fundo instanceof PecaAzul || movlinha == 0 || movcoluna == 0 || movlinha == 9 || movcoluna == 9)
                                                        jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                                            else{
                                                                tab.pegaPilha(linha - 1, coluna + 1);
                                                                pontuacao1 = pontuacao1 + 1;
                                                                jogo.ColocaPeca(linha, coluna, movlinha, movcoluna, jogador1, tab);
                                                                estadoAtual = 2;
                                                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                                            }
                                            //Move a peça para a posição desejada
                                            }else{
                                                jogo.ColocaPeca(linha, coluna, movlinha, movcoluna, jogador1, tab);
                                                estadoAtual = 2;
                                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                            }
                                        }
                            break;    
                    }
                }
    }
    
    //Metodo para mover peça azul
    public void MoverPecaVermelha(int linha, int coluna, Tabuleiro tab, int pontuacao1, int pontuacao2, int estadoAtual){
        boolean movendo = true;
        
        while(movendo){
                    //Instanciando Metodos
                    Jogo jogo = new Jogo();
                    
                    //Variaveis
                    int movlinha;
                    int movcoluna;
                    Tecla atual = null;                    
                    Carta fundo = null;
                    
                    //Desenhos na tela
                    Console.limpaTela();
                    Console.println(tab);
                    
                    //SwitchCase com opções para movimento
                    switch(cont_opc){
                        case 0:
                            movlinha = linha - 1; movcoluna = coluna - 1;
                            Console.println("Move se para a coordenada ("+movcoluna+","+movlinha+")");
                            atual = Console.getTecla();
                            
                            //Cancela movimento de peça
                            if (atual == Tecla.CTRL){
                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                            }
                            //Muda opção de movimento e seleciona o movimento
                                if (atual == Tecla.DOWN) cont_opc = (cont_opc + 1) %4;
                                    else if (atual == Tecla.UP) cont_opc = (cont_opc - 1 + 4) %4;
                                        else if (atual == Tecla.ESPACO){
                                            fundo = tab.getPilha(movlinha, movcoluna).verTopo();
                                            if (fundo instanceof PecaVermelha || movlinha == 0 || movcoluna == 0 || movlinha == 9 || movcoluna == 9)
                                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                            //Verifica se a proxima posição é uma peça inimiga e pula para a proxima posição removendo a peça anterior
                                            if (fundo instanceof PecaAzul){
                                                movlinha = linha - 2; movcoluna = coluna - 2;
                                                //Verifica se e realmente possivel comer a peça ou se na proxima posição existe outra peça inimiga
                                                fundo = tab.getPilha(movlinha, movcoluna).verTopo();
                                                    if(fundo instanceof PecaVermelha || fundo instanceof PecaAzul || movlinha == 0 || movcoluna == 0 || movlinha == 9 || movcoluna == 9)
                                                        jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                                            else{
                                                                tab.pegaPilha(linha - 1, coluna - 1);
                                                                pontuacao2 = pontuacao2 +1;
                                                                jogo.ColocaPeca(linha, coluna, movlinha, movcoluna, jogador2, tab);
                                                                estadoAtual = 1;
                                                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                                            }
                                            //Move a peça para a posição desejada
                                            }else{
                                                jogo.ColocaPeca(linha, coluna, movlinha, movcoluna, jogador2, tab);
                                                estadoAtual = 1;
                                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                            }
                                        }
                            break;
                        case 1:
                            movlinha = linha - 1; movcoluna = coluna + 1;
                            Console.println("Move se para a coordenada ("+movcoluna+","+movlinha+")");
                            atual = Console.getTecla();
                            
                            //Cancela movimento de peça
                            if (atual == Tecla.CTRL){
                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);    
                            }
                            //Muda opção de movimento e seleciona o movimento
                                if (atual == Tecla.DOWN) cont_opc = (cont_opc + 1) %4;
                                    else if (atual == Tecla.UP) cont_opc = (cont_opc - 1 + 4) %4;
                                        else if (atual == Tecla.ESPACO){
                                            fundo = tab.getPilha(movlinha, movcoluna).verTopo();
                                            if (fundo instanceof PecaVermelha || movlinha == 0 || movcoluna == 0 || movlinha == 9 || movcoluna == 9)
                                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                            //Verifica se a proxima posição é uma peça inimiga e pula para a proxima posição removendo a peça anterior
                                            if (fundo instanceof PecaAzul){
                                                movlinha = linha - 2; movcoluna = coluna + 2;
                                                //Verifica se e realmente possivel comer a peça ou se na proxima posição existe outra peça inimiga
                                                fundo = tab.getPilha(movlinha, movcoluna).verTopo();
                                                    if(fundo instanceof PecaVermelha || fundo instanceof PecaAzul || movlinha == 0 || movcoluna == 0 || movlinha == 9 || movcoluna == 9)
                                                        jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                                            else{
                                                                tab.pegaPilha(linha - 1, coluna + 1);
                                                                pontuacao2 = pontuacao2 +1;
                                                                jogo.ColocaPeca(linha, coluna, movlinha, movcoluna, jogador2, tab);
                                                                estadoAtual = 1;
                                                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                                            }
                                            //Move a peça para a posição desejada
                                            }else{
                                                jogo.ColocaPeca(linha, coluna, movlinha, movcoluna, jogador2, tab);
                                                estadoAtual = 1;
                                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                            }
                                        }
                            break;
                        case 2:
                            movlinha = linha + 1; movcoluna = coluna + 1;
                            Console.println("Move se para a coordenada ("+movcoluna+","+movlinha+")");
                            atual = Console.getTecla();
                            
                            //Cancela movimento de peça
                            if (atual == Tecla.CTRL){
                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);  
                            }
                            //Muda opção de movimento e seleciona o movimento
                                if (atual == Tecla.DOWN) cont_opc = (cont_opc + 1) %4;
                                    else if (atual == Tecla.UP) cont_opc = (cont_opc - 1 + 4) %4;
                                        else if (atual == Tecla.ESPACO){
                                            fundo = tab.getPilha(movlinha, movcoluna).verTopo();
                                            if (fundo instanceof PecaVermelha || movlinha == 0 || movcoluna == 0 || movlinha == 9 || movcoluna == 9)
                                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                            //Verifica se a proxima posição é uma peça inimiga e pula para a proxima posição removendo a peça anterior
                                            if (fundo instanceof PecaAzul){
                                                movlinha = linha + 2; movcoluna = coluna + 2;
                                                //Verifica se e realmente possivel comer a peça ou se na proxima posição existe outra peça inimiga
                                                fundo = tab.getPilha(movlinha, movcoluna).verTopo();
                                                    if(fundo instanceof PecaVermelha || fundo instanceof PecaAzul || movlinha == 0 || movcoluna == 0 || movlinha == 9 || movcoluna == 9)
                                                        jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                                            else{
                                                                tab.pegaPilha(linha + 1, coluna + 1);
                                                                pontuacao2 = pontuacao2 +1;
                                                                jogo.ColocaPeca(linha, coluna, movlinha, movcoluna, jogador2, tab);
                                                                estadoAtual = 1;
                                                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                                            }
                                            //Move a peça para a posição desejada
                                            }else{
                                                jogo.ColocaPeca(linha, coluna, movlinha, movcoluna, jogador2, tab);
                                                estadoAtual = 1;
                                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                            }
                                        }
                            break;
                        case 3:
                            movlinha = linha + 1; movcoluna = coluna - 1;
                            Console.println("Move se para a coordenada ("+movcoluna+","+movlinha+")");
                            atual = Console.getTecla();
                            
                            //Cancela movimento de peça
                            if (atual == Tecla.CTRL){
                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);    
                            }
                            //Muda opção de movimento e seleciona o movimento
                                if (atual == Tecla.DOWN) cont_opc = (cont_opc + 1) %4;
                                    else if (atual == Tecla.UP) cont_opc = (cont_opc - 1 + 4) %4;
                                        else if (atual == Tecla.ESPACO){
                                            fundo = tab.getPilha(movlinha, movcoluna).verTopo();
                                            if (fundo instanceof PecaVermelha || movlinha == 0 || movcoluna == 0 || movlinha == 9 || movcoluna == 9)
                                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                            //Verifica se a proxima posição é uma peça inimiga e pula para a proxima posição removendo a peça anterior
                                            if (fundo instanceof PecaAzul){
                                                movlinha = linha + 2; movcoluna = coluna - 2;
                                                //Verifica se e realmente possivel comer a peça ou se na proxima posição existe outra peça inimiga
                                                fundo = tab.getPilha(movlinha, movcoluna).verTopo();
                                                    if(fundo instanceof PecaVermelha || fundo instanceof PecaAzul || movlinha == 0 || movcoluna == 0 || movlinha == 9 || movcoluna == 9)
                                                        jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                                            else{
                                                                tab.pegaPilha(linha + 1, coluna - 1);
                                                                pontuacao2 = pontuacao2 +1;
                                                                jogo.ColocaPeca(linha, coluna, movlinha, movcoluna, jogador2, tab);
                                                                estadoAtual =1;
                                                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                                            }
                                            //Move a peça para a posição desejada
                                            }else{
                                                jogo.ColocaPeca(linha, coluna, movlinha, movcoluna, jogador2, tab);
                                                estadoAtual = 1;
                                                jogo.RetomarJogo(tab, movendo, pontuacao1, pontuacao2, estadoAtual);
                                            }
                                        }
                            break;    
                    }
                }
    }
    
    //Retoma o movimento inicial de tabuleiro finalizando quaisquer ação
    public void RetomarJogo(Tabuleiro tab, boolean movendo, int pontuacao1, int pontuacao2, int estadoAtual){
        Jogo jogo = new Jogo();
        jogo.JogoRodando(tab, pontuacao1, pontuacao2, estadoAtual);
        movendo = false;
    }
    
    //Remove a peça e coloca na proxima posição
    public void ColocaPeca(int linha, int coluna, int movlinha, int movcoluna, Carta carta, Tabuleiro tab){      
        tab.pegaCarta(linha, coluna);
        tab.colocaCarta(movlinha, movcoluna, carta);
    }
    
    //Verifica vencedor
    public void Vencedor(int pontuacao1, int pontuacao2){
        if(pontuacao1 >= 12){
            Console.limpaTela();
            Console.println("____________________________________________________________________________________________\n" +
"\n" +
"                                  JOGADOR AZUL VENCEDOR!!!\n" +
"____________________________________________________________________________________________");
            Console.saiDoPrograma();
        }
        else if(pontuacao2>=12){
           Console.limpaTela();
           Console.println("____________________________________________________________________________________________\n" +
"\n" +
"                                  JOGADOR VERMELHO VENCEDOR!!!\n" +
"____________________________________________________________________________________________");
           Console.saiDoPrograma(); 
        }
    }
    
    //Menu de Pausa durante o jogo
    public void Pausa(int linha, int coluna, int movlinha, int movcoluna, Carta carta, Tabuleiro tab, Carta jogador1, Carta jogador2, int pontuacao1, int pontuacao2, int estadoAtual){
        //Variaveis
        boolean running = true;
        //Instanciando Classe Jogo
        Jogo jogo = new Jogo();
        
        //While Rodando o Menu Principal
        while (running){
            Console.limpaTela();
            Damas dama = new Damas();
            Tecla atual = null;
            switch (cont_opc){
                
                //Opção Retoma para o jogo atual
                case 0: 
                    Console.println("███╗ ███╗  ██████╗  █████╗ ██╗  ██╗█████╗  ███████╗  ");
                    Console.println("███║ ███║  ██║  ██║██║  ██║██║  ██║██╔═══╝ ██╔════╝");
                    Console.println("███║ ███║  ██║  ██║███████║██║  ██║██████╗ ███████╗");
                    Console.println("███║ ███║  ██████╔╝██║  ██║██║  ██║╚═══██╗ ██╔════╝");
                    Console.println("███║ ███║  ██╔══╝  ██║  ██║╚█████╔╝██████╔╝███████╗");
                    Console.println("╚══╝ ╚══╝   ═╝     ╚═╝  ╚═╝ ╚════╝ ╚═════╝ ╚══════╝");
                    
                    Console.println("               -->Retomar Jogo"
                                + "\n               Carregar Jogo"
                                + "\n               Salvar Jogo"
                                + "\n               Novo Jogo"
                                + "\n               Menu Principal"
                                + "\n               Tutorial"
                                + "\n               Sair Jogo");
                atual = Console.getTecla();
                    if (atual == Tecla.DOWN)
                        cont_opc = (cont_opc + 1) %7;
                        else if (atual == Tecla.UP)
                                cont_opc = (cont_opc - 1 + 7) %7;
                        else if (atual == Tecla.ESPACO){
                            jogo.JogoRodando(tab, pontuacao1, pontuacao2, estadoAtual);
                            running = false;
                        }
                    break;  
                    
                //Opção Carregar Jogo Salvo
                case 1: 
                    Console.println("███╗ ███╗  ██████╗  █████╗ ██╗  ██╗█████╗  ███████╗  ");
                    Console.println("███║ ███║  ██║  ██║██║  ██║██║  ██║██╔═══╝ ██╔════╝");
                    Console.println("███║ ███║  ██║  ██║███████║██║  ██║██████╗ ███████╗");
                    Console.println("███║ ███║  ██████╔╝██║  ██║██║  ██║╚═══██╗ ██╔════╝");
                    Console.println("███║ ███║  ██╔══╝  ██║  ██║╚█████╔╝██████╔╝███████╗");
                    Console.println("╚══╝ ╚══╝   ═╝     ╚═╝  ╚═╝ ╚════╝ ╚═════╝ ╚══════╝");
                    
                    Console.println("               Retomar Jogo"
                                + "\n               -->Carregar Jogo"
                                + "\n               Salvar Jogo"
                                + "\n               Novo Jogo"
                                + "\n               Menu Principal"
                                + "\n               Tutorial"
                                + "\n               Sair Jogo");
                atual = Console.getTecla();
                    if (atual == Tecla.DOWN)
                        cont_opc = (cont_opc + 1) %7;
                        else if (atual == Tecla.UP)
                                cont_opc = (cont_opc - 1 + 7) %7;
                    break;
                    
                //Opção Salva Jogo atual
                case 2:
                    Console.println("███╗ ███╗  ██████╗  █████╗ ██╗  ██╗█████╗  ███████╗  ");
                    Console.println("███║ ███║  ██║  ██║██║  ██║██║  ██║██╔═══╝ ██╔════╝");
                    Console.println("███║ ███║  ██║  ██║███████║██║  ██║██████╗ ███████╗");
                    Console.println("███║ ███║  ██████╔╝██║  ██║██║  ██║╚═══██╗ ██╔════╝");
                    Console.println("███║ ███║  ██╔══╝  ██║  ██║╚█████╔╝██████╔╝███████╗");
                    Console.println("╚══╝ ╚══╝   ═╝     ╚═╝  ╚═╝ ╚════╝ ╚═════╝ ╚══════╝");
                    
                    Console.println("               Retomar Jogo"
                                + "\n               Carregar Jogo"
                                + "\n               -->Salvar Jogo"
                                + "\n               Novo Jogo"
                                + "\n               Menu Principal"
                                + "\n               Tutorial"
                                + "\n               Sair Jogo");
                atual = Console.getTecla();
                    if (atual == Tecla.DOWN)
                        cont_opc = (cont_opc + 1) %7;
                        else if (atual == Tecla.UP)
                                cont_opc = (cont_opc - 1 + 7) %7;
                    break;
                    
                   //Opção Iniciar novo jogo 
                case 3:
                    Console.println("███╗ ███╗  ██████╗  █████╗ ██╗  ██╗█████╗  ███████╗  ");
                    Console.println("███║ ███║  ██║  ██║██║  ██║██║  ██║██╔═══╝ ██╔════╝");
                    Console.println("███║ ███║  ██║  ██║███████║██║  ██║██████╗ ███████╗");
                    Console.println("███║ ███║  ██████╔╝██║  ██║██║  ██║╚═══██╗ ██╔════╝");
                    Console.println("███║ ███║  ██╔══╝  ██║  ██║╚█████╔╝██████╔╝███████╗");
                    Console.println("╚══╝ ╚══╝   ═╝     ╚═╝  ╚═╝ ╚════╝ ╚═════╝ ╚══════╝");
                    
                    Console.println("               Retomar Jogo"
                                + "\n               Carregar Jogo"
                                + "\n               Salvar Jogo"
                                + "\n               -->Novo Jogo"
                                + "\n               Menu Principal"
                                + "\n               Tutorial"
                                + "\n               Sair Jogo");
                atual = Console.getTecla();
                    if (atual == Tecla.DOWN)
                        cont_opc = (cont_opc + 1) %7;
                        else if (atual == Tecla.UP)
                                cont_opc = (cont_opc - 1 + 7) %7;
                        else if (atual == Tecla.ESPACO){
                            Console.limpaTela();
                            Console.println("\n\n\n\n       O progresso atual sera perdido!!!"
                                        + "\n              Tem certeza???"
                                        + "\n                 Y     N");
                            atual = Console.getTecla();
                            if(atual == Tecla.Y){
                                Tabuleiro mesa = new Mesa();
                                jogo.JogoRodando(mesa, pontuacao1=0,pontuacao2=0, estadoAtual = 0);
                            }else jogo.Pausa(linha, coluna, movlinha, movcoluna, carta, tab, jogador1, jogador2, pontuacao1, pontuacao2, estadoAtual);
                        }
                    break;
                
                //Opção Volta para o menu principal
                case 4: 
                    Console.println("███╗ ███╗  ██████╗  █████╗ ██╗  ██╗█████╗  ███████╗  ");
                    Console.println("███║ ███║  ██║  ██║██║  ██║██║  ██║██╔═══╝ ██╔════╝");
                    Console.println("███║ ███║  ██║  ██║███████║██║  ██║██████╗ ███████╗");
                    Console.println("███║ ███║  ██████╔╝██║  ██║██║  ██║╚═══██╗ ██╔════╝");
                    Console.println("███║ ███║  ██╔══╝  ██║  ██║╚█████╔╝██████╔╝███████╗");
                    Console.println("╚══╝ ╚══╝   ═╝     ╚═╝  ╚═╝ ╚════╝ ╚═════╝ ╚══════╝");
                    
                    Console.println("               Retomar Jogo"
                                + "\n               Carregar Jogo"
                                + "\n               Salvar Jogo"
                                + "\n               Novo Jogo"
                                + "\n               -->Menu Principal"
                                + "\n               Tutorial"
                                + "\n               Sair Jogo");
                atual = Console.getTecla();
                    if (atual == Tecla.DOWN)
                        cont_opc = (cont_opc + 1) %7;
                        else if (atual == Tecla.UP)
                                cont_opc = (cont_opc - 1 + 7) %7;
                        else if (atual == Tecla.ESPACO){
                            Console.limpaTela();
                            Console.println("\n\n\n\n       Voltar para o Menu fara com que o progresso atual seja perdido!!!"
                                       + "\n                           Deseja Continuar????"
                                       + "\n                               Y       N");
                            atual = Console.getTecla();
                            if(atual == Tecla.Y)dama.Menu();
                            else jogo.Pausa(linha, coluna, movlinha, movcoluna, carta, tab, jogador1, jogador2, pontuacao1, pontuacao2, estadoAtual);
                        }
                    break;
                    
                //Opção Mostra tutorial    
                case 5: 
                    Console.println("███╗ ███╗  ██████╗  █████╗ ██╗  ██╗█████╗  ███████╗  ");
                    Console.println("███║ ███║  ██║  ██║██║  ██║██║  ██║██╔═══╝ ██╔════╝");
                    Console.println("███║ ███║  ██║  ██║███████║██║  ██║██████╗ ███████╗");
                    Console.println("███║ ███║  ██████╔╝██║  ██║██║  ██║╚═══██╗ ██╔════╝");
                    Console.println("███║ ███║  ██╔══╝  ██║  ██║╚█████╔╝██████╔╝███████╗");
                    Console.println("╚══╝ ╚══╝   ═╝     ╚═╝  ╚═╝ ╚════╝ ╚═════╝ ╚══════╝");
                    
                    Console.println("               Retomar Jogo"
                                + "\n               Carregar Jogo"
                                + "\n               Salvar Jogo"
                                + "\n               Novo Jogo"
                                + "\n               Menu Principal"
                                + "\n               -->Tutorial"
                                + "\n               Sair Jogo");
                atual = Console.getTecla();
                    if (atual == Tecla.DOWN)
                        cont_opc = (cont_opc + 1) %7;
                        else if (atual == Tecla.UP)
                                cont_opc = (cont_opc - 1 + 7) %7;
                        else if (atual == Tecla.ESPACO){
                            dama.Tutorial();
                        }
                    break;    
                    
                //Opção Sair do Jogo
                case 6: 
                    Console.println("███╗ ███╗  ██████╗  █████╗ ██╗  ██╗█████╗  ███████╗  ");
                    Console.println("███║ ███║  ██║  ██║██║  ██║██║  ██║██╔═══╝ ██╔════╝");
                    Console.println("███║ ███║  ██║  ██║███████║██║  ██║██████╗ ███████╗");
                    Console.println("███║ ███║  ██████╔╝██║  ██║██║  ██║╚═══██╗ ██╔════╝");
                    Console.println("███║ ███║  ██╔══╝  ██║  ██║╚█████╔╝██████╔╝███████╗");
                    Console.println("╚══╝ ╚══╝   ═╝     ╚═╝  ╚═╝ ╚════╝ ╚═════╝ ╚══════╝");
                    
                    Console.println("               Retomar Jogo"
                                + "\n               Carregar Jogo"
                                + "\n               Salvar Jogo"
                                + "\n               Novo Jogo"
                                + "\n               Menu Principal"
                                + "\n               Tutorial"
                                + "\n               -->Sair Jogo");
                atual = Console.getTecla();
                    if (atual == Tecla.DOWN)
                        cont_opc = (cont_opc + 1) %7;
                        else if (atual == Tecla.UP)
                                cont_opc = (cont_opc - 1 + 7) %7;
                        else if (atual == Tecla.ESPACO){
                            running = false;
                            Console.saiDoPrograma();
                        }
                    break;
            }
        }
    }
}