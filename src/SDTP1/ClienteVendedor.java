/**
 * Esta classe implementa funcionalidades de consulta de informações específicas
 * para o vendedor. Assim como permite que o vendedor manipule os filmes e venda ao
 * consumidor final.
 *
 * @author Adriana, Daniel e Fernando
 * @version 1.0
 */
package SDTP1;

import static java.lang.System.exit;
import java.time.LocalDate;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.*;
import java.rmi.RemoteException;

public class ClienteVendedor extends java.rmi.server.UnicastRemoteObject implements ClienteInterface {

    public ClienteVendedor() throws RemoteException {
        super();
    }

    public void printOnClient(String s) throws java.rmi.RemoteException {
        System.out.println("AVISO: " + s);
    }

    //Função Callback que serve para avisar o Fornecedor caso o Stock seja inferior ao Stock mínimo.
    public static String verificaStock(Filme ff) {
        String aviso = "";
        int conta;
        if ((ff.getQuantidade() - ff.getVendidos()) < ff.getStockMin()) {
            conta = ff.getStockMin() - (ff.getQuantidade() - ff.getVendidos());
            aviso = "O filme " + ff.getNome() + " necessita de reposição de Stock. Para alcançar o número mínimo, necessita de adicionar " + Integer.toString(conta) + " exemplares.\n";
        }
        return aviso;

    }

    public static void printMenuFilme1() {
        System.out.println("----------\nMENU--VENDEDOR\n----------\n1-Consultar Vendas\n2-Consultar Produto\n3-Apagar Produto\n4-Vender Produto\n5-Sair\n----------");
    }

    public static void printSubMenu() {
        System.out.println("----------\nMENU\n----------\n1-Por Nome\n2-Por Realizador\n3-Por ID\n4-Sair\n----------");
    }

    //Função para início de sessão de Vendedor -- O LOGIN NÂO ESTÁ CORRETO, COMENTÁMOS PARA PODER funcionar// 
    public static boolean verificarLogin(ArrayList<Utilizador> utilizadores, String username, String password) {
        for (int i = 0; i < utilizadores.size(); i++) {
            if (utilizadores.get(i).getTipo().equals(Utilizador.TIPO.VENDEDOR)) {// && utilizadores.get(i).getUsername().equals(username) && utilizadores.get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());

        try {
            ServidorInterface s = (ServidorInterface) Naming.lookup(Constants.RMI_ID);
            ClienteFornecedor c = new ClienteFornecedor();

            ArrayList<Filme> recetor = s.consultarFilmes(); //Funções do Servidor para acesso à consulta da BD de filmes, assim como dos utilizadores e filmes vendidos.
            ArrayList<Transacao> vendas = s.consultarVendas();
            
            boolean login = false;

            if (s.consultarUtilizadores().size() == 0) {
                System.out.println("Nenhum utilizador registado!");
                exit(1);
            } else {

                do { //Início de Sessão de utilizador
                    System.out.println("VENDEDOR\n----------\nLOGIN\n----------\nUsername:");
                    String username = Ler.umaString();
                    System.out.println("Password:");
                    String password = Ler.umaString();

                    System.out.println(s.consultarUtilizadores());
                    login = verificarLogin(s.consultarUtilizadores(), username, password);

                    System.out.println(login);
                } while (login == false);
                
                //Parte do exercício realizador durante a apresentação
                System.out.println(s.produtoCaro());
                //-------------------------------------------
                
                int op = 0;
                while (op != 5) {
                    //para ler d
                    recetor = s.consultarFilmes();
                    vendas = s.consultarVendas();
                    printMenuFilme1();
                    op = Ler.umInt();
                    switch (op) {
                        //Função para consultar as vendas dependendo de vários critérios.
                        case 1:
                            System.out.println("Consultar vendas ao consumidor final por:");
                            System.out.println("\n1 - Filme mais vendido\n2-Preço\n3-Sair\n");
                            int escolha = Ler.umInt();

                            switch (escolha) {
                                //lista das vendas ordenada por produto mais vendido
                                case 1:
                                    
                                    System.out.println("Lista de Vendas por Produto mais vendido:\n");
                                    if (vendas.size() == 0) {
                                        System.out.println("Lista vazia");
                                    } else {
                                        int[] numeros = new int[recetor.size()];
                                        int i, m, q = 0;
                                        for (m = 0; m < recetor.size(); m++) {
                                            numeros[q] = recetor.get(m).getVendidos();//no vetor numeros serão armazenados os valores de exemplares vendidos de cada filme
                                            q = q + 1;
                                        }
                                        //O número de objetos vendidos será organizado por ordem crescente
                                        Arrays.sort(numeros);

                                        for (i = recetor.size() - 1; i > -1; i--) {
                                            for (int l = 0; l < recetor.size(); l++) {
                                                if (recetor.get(l).getVendidos() == numeros[i]) {//Caso o número de objetos vendidos seja semelhante ao filme, mostra-se-lo.
                                                    System.out.println(recetor.get(l).getNome() + "\nNúmero de vendas: " + recetor.get(l).getVendidos());
                                                    break;
                                                }
                                            }

                                        }

                                    }
                                    break;
                                    
                                //Mesma lógica em relação ao caso anterior mas pelo preço.
                                case 2:
                                    System.out.println("Lista de Vendas por Preço de Produto ao consumidor mais elevado:\n");
                                    if (vendas.size() == 0) {
                                        System.out.println("Lista vazia");
                                    } else {
                                        double[] numeros1 = new double[recetor.size()];
                                        int i, m, q = 0;
                                        for (m = 0; m < recetor.size(); m++) {

                                            numeros1[q] = recetor.get(m).getPrecoVenda();
                                            q = q + 1;
                                        }

                                        Arrays.sort(numeros1);

                                        for (i = recetor.size() - 1; i > -1; i--) {
                                            for (int l = 0; l < recetor.size(); l++) {
                                                if (recetor.get(l).getPrecoVenda() == numeros1[i]) {
                                                    System.out.println(recetor.get(l).getNome() + "\nPreço do produto: " + recetor.get(l).getPrecoVenda() + "\nNúmero de vendas: " + recetor.get(l).getVendidos());
                                                    break;
                                                }
                                            }

                                        }
                                    }

                                    break;
                                //sair
                                case 3:
                                    break;

                                default:
                                    System.out.println("Opção inválida!");

                            }
                            break;
                        
                        //Pesquisar os filmes para saber informações relativas aos exemplares disponíveis assim como suas vendas efetuadas.
                        //As pesquisas podem ser efetuadas por título, realizador e ID.
                        case 2:
                            
                            System.out.println("Pesquisar por:\n");
                            int op1 = 0;
                            boolean aviso = false;
                            printSubMenu();
                            
                            
                            op1 = Ler.umInt();
                            switch (op1) {
                                case 1:
                                    System.out.println("Escreva o título do filme:");
                                    String titulo = Ler.umaString();

                                    for (int i = 0; i < recetor.size(); i++) { //ciclo que verifica se o filme existe na base de dados.
                                        if (recetor.get(i).getNome().equals(titulo)) //Ver quantidade, o Daniel tem de atualizar.
                                        {
                                            System.out.println("Realizador: " + recetor.get(i).getRealizador());
                                            System.out.println("Número de exemplares disponíveis/Número de exemplares vendidos: \n" + (recetor.get(i).getQuantidade() - recetor.get(i).getVendidos()) + "/" + recetor.get(i).getVendidos());
                                            aviso = true;
                                        }
                                    }
                                    if (aviso == false) {
                                        System.out.println("Não existem exemplares correspondentes à sua pesquisa.\n");
                                    }
                                    aviso = false;
                                    break;
                                case 2:
                                    System.out.println("Escreva o nome do realizador: \n");
                                    String realizador = Ler.umaString();

                                    for (int i = 0; i < recetor.size(); i++) {
                                        if (recetor.get(i).getRealizador().equals(realizador)) //Ver quantidade, o Daniel tem de atualizar.
                                        {
                                            System.out.println(recetor.get(i).getNome());
                                            System.out.println("Número de exemplares disponíveis/Número de exemplares vendidos: \n" + (recetor.get(i).getQuantidade() - recetor.get(i).getVendidos()) + "/" + recetor.get(i).getVendidos());
                                            aviso = true;
                                        }
                                    }
                                    if (aviso == false) {
                                        System.out.println("Não existem exemplares correspondentes à sua pesquisa.\n");
                                    }
                                    aviso = false;
                                    break;
                                case 3:
                                    System.out.println("Escreva o número de identificação do filme: \n");
                                    int ID = Ler.umInt();

                                    for (int i = 0; i < recetor.size(); i++) {
                                        if (recetor.get(i).getId() == ID) //Ver quantidade, o Daniel tem de atualizar.
                                        {
                                            System.out.println(recetor.get(i).getNome());
                                            System.out.println("Número de exemplares disponíveis/Número de exemplares vendidos: \n" + (recetor.get(i).getQuantidade() - recetor.get(i).getVendidos()) + "/" + recetor.get(i).getVendidos());
                                            aviso = true;
                                        }
                                    }
                                    if (aviso == false) {
                                        System.out.println("Não existem exemplares correspondentes à sua pesquisa.\n");
                                    }
                                    aviso = false;
                                    break;
                                case 4:
                                    break;
                                default:
                                    System.out.println("Opção inválida!");
                            }
                            break;
                        
                        //Função que apaga um determinado filme da lista. Os métodos de procura são igual aos dos casos anteriores.
                        case 3:
                            System.out.println("Pesquisar Produto para apagá-lo da base de dados por:\n");
                            int op2 = 0;
                            boolean aviso1 = false;
                            printSubMenu();
                            op2 = Ler.umInt();
                            
                            switch (op2) {
                                case 1:
                                    System.out.println("Escreva o título do filme: \n");
                                    String titulo = Ler.umaString();

                                    for (int i = 0; i < recetor.size(); i++) {
                                        if (recetor.get(i).getNome().equals(titulo)) //Atualizar SERVIDOR
                                        {
                                            s.eliminarFilme(recetor.get(i));
                                            System.out.println("Filme apagado com sucesso.");
                                            aviso1 = true;
                                        }
                                    }
                                    if (aviso1 == false) {
                                        System.out.println("Não existem exemplares correspondentes à sua pesquisa.\n");
                                    }
                                    aviso1 = false;
                                    break;
                                case 2:
                                    System.out.println("Escreva o nome do realizador: \n");
                                    String realizador = Ler.umaString();

                                    for (int i = 0; i < recetor.size(); i++) {
                                        if (recetor.get(i).getRealizador().equals(realizador)) //Ver quantidade, o Daniel tem de atualizar.
                                        {
                                            s.eliminarFilme(recetor.get(i));
                                            System.out.println("Filme apagado com sucesso.");
                                            aviso1 = true;
                                        }
                                    }
                                    if (aviso1 == false) {
                                        System.out.println("Não existem exemplares correspondentes à sua pesquisa.\n");
                                    }
                                    aviso1 = false;
                                    break;

                                case 3:
                                    System.out.println("Escreva o número de identificação do filme: \n");
                                    int ID = Ler.umInt();

                                    for (int i = 0; i < recetor.size(); i++) {
                                        if (recetor.get(i).getId() == ID) //Ver quantidade, o Daniel tem de atualizar.
                                        {
                                            s.eliminarFilme(recetor.get(i));
                                            System.out.println("Filme apagado com sucesso.");
                                            aviso1 = true;
                                        }
                                    }
                                    if (aviso1 == false) {
                                        System.out.println("Não existem exemplares correspondentes à sua pesquisa.\n");
                                    }
                                    aviso1 = false;
                                    break;

                                case 4:
                                    break;
                                default:
                                    System.out.println("Opção inválida!");
                            }
                            break;
                            
                        //Vender Produto
                        case 4:
                            //filmesDestaCompra --> funciona como um carrinho de compras 
                            ArrayList<Filme> filmesDestaVenda = new ArrayList<Filme>();
                            ArrayList<Integer> quantidade = new ArrayList<Integer>();
                            int d = 1, qq;
                            String nome;
                            boolean t = false;

                            while (d == 1) {

                                System.out.println("---Nova Venda---\n");
                                System.out.println("Insira o nome do filme a vender:");
                                nome = Ler.umaString();
                                int i;
                                //verifica se o filme já foi previamente registado
                                for (i = 0; i < recetor.size(); i++) {
                                    if (recetor.get(i).getNome().equals(nome)) {//Só poderá vender um filme caso esteja na lista geral de filmes.
                                        t = true;
                                        break;
                                    }
                                }
                                if (t == true) {
                                    do {
                                        System.out.println("Quantidade a vender: ");
                                        //guarda na posição respetiva o número de filmes
                                        qq = Ler.umInt();

                                        if (qq > (recetor.get(i).getQuantidade() - recetor.get(i).getVendidos())) {
                                            System.out.println("Quantidade indisponível para venda, por favor escreva um valor menor.");
                                        }

                                    } while (qq > (recetor.get(i).getQuantidade() - recetor.get(i).getVendidos()));
                                    //adiciona o filme escolhido ao carrinho
                                    quantidade.add(qq);

                                    filmesDestaVenda.add(recetor.get(i));
                                    System.out.println("Filme " + recetor.get(i).getNome() + " adicionado ao carrinho.");
                                    //define a lista de compras

                                } else {
                                    System.out.println("O filme ->" + nome + " <- não está disponível para venda...");
                                }
                                System.out.println("Deseja vender outro filme? (1-Sim 2-Não)");
                                d = Ler.umInt();
                            }

                            //System.out.println("ola");
                            LocalDate data1 = LocalDate.now();
                            for (int j = 0; j < filmesDestaVenda.size(); j++) {
                                //System.out.println("ola1");
                                s.adicionarVendidos(filmesDestaVenda.get(j), quantidade.get(j));
                                //System.out.println("ola2");
                                Transacao venda1 = new Transacao(filmesDestaVenda.get(j), quantidade.get(j), data1);
                                //System.out.println("ola3");
                                s.registarVenda(venda1);
                                //System.out.println("ola4");
                                if (verificaStock(filmesDestaVenda.get(j)).equals("")) {
                                    continue;
                                } else {
                                    //s.chamada(verificaStock(filmesDestaVenda.get(j)));
                                    //subscreve um clienteFornecedor
                                    s.subscribe("Cliente vendedor", (ClienteInterface) c);
                                }
                            }

                            break;

                        case 5:
                            break;

                        default:
                            System.out.println("Opção inválida!");
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("ClienteFornecedor exception: " + ex.getMessage());
        }
        System.out.println("Acabou de terminar a sessão...");
    }
}
