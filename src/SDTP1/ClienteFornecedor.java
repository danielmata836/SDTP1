package SDTP1;

import SDTP1.Filme;
import java.rmi.Naming;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;

/**
 * A classe ClienteFornecedor implementa um programa que permite gerir compras e filmes.
 * A partir da nossa interpretação do enunciado do projeto, o Forncedor é o funcionário da 
 * loja que compra filmes a um armazém, por exemplo. Ou seja, este utilizador vai adicionar 
 * produtos ao stock da loja.
 * 
 * @author Adriana, Daniel e Fernando
 * @version 1.0
 */
public class ClienteFornecedor extends java.rmi.server.UnicastRemoteObject {

    public ClienteFornecedor() throws RemoteException {
        super();
    }
        
    //Método local
    public static void printMenuFilme() {
        System.out.println("----------\nMENU--FORNECEDOR\n----------\n1-Registar Filme\n2-Listar Filmes\n3-Listar Compras\n4-Realizar Compra\n5-Eliminar Filme\n6-Sair\n----------");
    }

    /*
    * Método local para veirificar o login
    * Percorre a lista de utilizadores à procura de um objeto do TIPO Fornecedor à procura de uma correspondência com 
    */
    public static boolean verificarLogin(ArrayList<Utilizador> utilizadores, String username, String password) {
        for (int i = 0; i < utilizadores.size(); i++) {
            if (utilizadores.get(i).getTipo().equals(Utilizador.TIPO.FORNECEDOR) && utilizadores.get(i).getUsername().equals(username) && utilizadores.get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        System.setSecurityManager(new SecurityManager());

        ArrayList<Filme> lista_filmes = new ArrayList<Filme>();
        
        
        try {
            ServidorInterface s = (ServidorInterface) Naming.lookup(Constants.RMI_ID);
            ClienteFornecedor c = new ClienteFornecedor();
            //s.subscribe("Nome da máquina cliente ...", (ClienteInterface) c);  //PARA CHAMADA AVISO
            //Ficheiros -- são só necessários os ficheiros que se se vão fazer alterações
            //Filmes
            FileOutputStream fosF = new FileOutputStream(new File("./SDTP1/Filmes.txt"));
            ObjectOutputStream oosF = new ObjectOutputStream(fosF);
            
            //Compras
            FileOutputStream fosC = new FileOutputStream(new File("./SDTP1/Filmes.txt"));
            ObjectOutputStream oosC = new ObjectOutputStream(fosC);

            boolean login = false;

            if (s.consultarUtilizadores().size() == 0) {
                System.out.println("Nenhum utilizador registado!");
            } else {
                //Permite várias tentativas de login
                do {
                    System.out.println("FORNECEDOR\n----------\nLOGIN\n----------\nUsername:");
                    String username = Ler.umaString();
                    System.out.println("Password:");
                    String password = Ler.umaString();

                    System.out.println(s.consultarUtilizadores());
                    login = verificarLogin(s.consultarUtilizadores(), username, password);

                } while (login == false);

                int op = 0;
                int id = 1;
                
                boolean bool = false;
                               
                while (op != 6) {
                    printMenuFilme();
                    op = Ler.umInt();
                    
                    //Esta instrução está dentro do ciclo while para que seja lido a cada iteração
                    ArrayList<Filme> receivedFilmes = s.consultarFilmes();
                    
                    switch (op) {
                        //registo de novo filme na loja
                        case 1:
                            Filme filme = new Filme();
                            filme.setId(id);
                            id++;
                            System.out.println("Introduza o nome: ");
                            filme.setNome(Ler.umaString());
                            System.out.println("Selecione uma categoria das seguintes: ");
                            System.out.println("AVISO: -- Caso insira uma categoria inválida, este campo será definido como: DEFAULT");
                            int ite = 0;
                            filme.setCategoria(Ler.umaString());
                            
                            System.out.println("Quantidade:");
                            filme.setQuantidade(Ler.umInt());
                            System.out.println("Stock mínimo: ");
                            filme.setStockMin(Ler.umInt());

                            System.out.println("Preço de venda:");
                            filme.setPrecoVenda(Ler.umDouble());                          

                            System.out.println("Deseja continuar a preencher informação? (1-Sim 2-Não)");
                            if (Ler.umInt() == 1) {
                                System.out.println("Realizador: ");
                                filme.setRealizador(Ler.umaString());
                                System.out.println("Ano: ");
                                filme.setAno(Ler.umInt());
                                System.out.println("País:");
                                filme.setPais(Ler.umaString());
                            }
                            System.out.println("Filme registado com sucesso!");
                            s.registarFilme(filme);

                            //guardar no ficheiro a nova lista que contém o novo filme
                            oosF.writeObject(s.consultarFilmes());
                            oosF.flush();
                            break;

                        //listar todos os filmes
                        case 2:
                            /**
                             * Para listar basta ir à lista de filmes do
                             * servidor que é constantemente atualizada sempre
                             * que é adicionado um elemento na execução atual
                             * (assim como adicionado ao ficheiro). Os elementos
                             * de execuções antigas foram lidos para o servidor
                             * no seu arranque.
                             */
                            System.out.println("Lista de Filmes:\n" + s.consultarFilmes());

                            break;

                        //listar compras
                        case 3:
                            //Como se precisa de usar muitas vezes (3) a lista, assim 
                            //não se faz tantas chamadas ao objeto remoto
                            ArrayList<Transacao> receivedCompras = s.consultarCompras();
                            String stC = "Lista de Compras:\n";
                            if (receivedCompras.size() == 0) {
                                System.out.println("Lista vazia");
                            } else {
                                for (int i = 0; i < receivedCompras.size(); i++) {
                                    stC += receivedCompras.get(i).getFilme().toString() + "\n" + receivedCompras.get(i).getQuantidade() + "\n";
                                }
                                System.out.println("Filmes e quantidade adquirida: "+stC);
                            }
                            break;

                        /**
                         * REALIZAR COMPRA - Adicionar stock a um produto
                         * existente: - Só é possível comprar um produto que já
                         * tenha sido registado previamente; - Vai procurar os
                         * diferemtes produtos disponíveis e vai permitir
                         * escolher um deles para adicionar stock;
                         */
                       case 4:                        
                        //filmesDestaCompra --> funciona como um carrinho de compras 
                        ArrayList<Filme> filmesDestaCompra = new ArrayList<Filme>();
                        ArrayList<Integer> quantidade = new ArrayList<Integer>();
                        int d = 1;
                        String nome;
                        boolean t = false;
                        while (d == 1) {
                            
                            System.out.println("---Nova compra---\n");
                            System.out.println("Insira o nome do filme a comprar:");
                            nome = Ler.umaString();
                            int i;
                            //verifica se o filme já foi previamente registado
                            for (i = 0; i < receivedFilmes.size(); i++) {
                                if (receivedFilmes.get(i).getNome().equals(nome)) {
                                    t = true;
                                    break;
                                }
                            }
                            if (t == true) {
                                //System.out.println("Preço de compra: ");
                                //receivedFilmes.get(i).setPrecoCompra(Ler.umDouble());
                                System.out.println("Quantidade a comprar: ");
                                //guarda na posição respetiva o número de filmes
                                quantidade.add((Integer) Ler.umInt());
                                

                                //adiciona o filme escolhido ao carrinho
                                filmesDestaCompra.add(receivedFilmes.get(i));
                                System.out.println("Filme " + receivedFilmes.get(i).getNome() + " adicionado ao carrinho.");
                                //define a lista de compras
                                
                            } else {
                                System.out.println("O filme " + nome + "não está disponível para compra...");
                            }
                            //se o utilizador escolher 1 continua a adicionar filmes ao carrinho de compras
                            System.out.println("Deseja comprar outro filme? (1-Sim 2-Não)");
                            d = Ler.umInt();

                        }
                        LocalDate data1 = LocalDate.now();
                        for (int j = 0; j < filmesDestaCompra.size(); j++) {
                            s.adicionarStock(filmesDestaCompra.get(j), quantidade.get(j));
                            Transacao compra1 = new Transacao(filmesDestaCompra.get(j), quantidade.get(j), data1);
                            s.registarCompra(compra1);

                        }
                        break;


                        //Eliminar um filme da lista de filmes
                        case 5:
                            System.out.println("Introduza o nome do filme: ");
                            Filme f = new Filme();
                            f.setNome(Ler.umaString());
                            
                            //s.eleminarFilme --> apaga da lista do servidor
                            //apaga da lista local --> apaga da lista local
                            if (s.eliminarFilme(f) && lista_filmes.remove(f)) {
                                System.out.println("Filme eliminado!");
                            } else {
                                System.out.println("Filme não existe na lista.");
                            }
                            //remover do ficheiro --> define o conteudo do ficheiro 
                            //para a lista do servidor já sem o utilizador eliminado
                            oosF.writeObject(s.consultarFilmes());
                            oosF.flush();
                            break;

                        //sair
                        case 6:
                            break;
                            
                        default:
                            System.out.println("Opção inválida!");
                    }
                }
                fosF.close();
                oosF.close();
                
                fosC.close();
                oosC.close();
            }
        } catch (Exception ex) {
            System.out.println("ClienteFornecedor exception: " + ex.getMessage());
        }
        System.out.println("Acabou de terminar a sessão...");
    }
}
