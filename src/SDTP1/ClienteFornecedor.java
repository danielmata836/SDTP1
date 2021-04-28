package SDTP1;

import static java.lang.System.exit;
import java.rmi.Naming;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class ClienteFornecedor {

    //Método local
    public static void printMenuFilme() {
        System.out.println("----------\nMENU\n----------\n1-Registar\n2-Listar produtos\n3-Listar compras\n4-Realizar compra\n5-Eliminar Produto\n5-Sair\n----------");
    }

    //Método local
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
        try {
            ServidorInterface s = (ServidorInterface) Naming.lookup(Constants.RMI_ID);
            ClienteFornecedor c = new ClienteFornecedor();

            ArrayList<Utilizador> utilizadores = s.consultarUtilizadores();

            boolean login = false;

            if (utilizadores.size() == 0) {
                System.out.println("Nenhum utilizador registado!");
                exit(1);
            }

            do {
                System.out.println("----------\nLOGIN----------\nUsername:");
                String username = Ler.umaString();
                System.out.println("Password:");
                String password = Ler.umaString();

                login = verificarLogin(utilizadores, username, password);

            } while (login == false);

            int op = 0;
            int id = 1;
            while (op != 3) {
                printMenuFilme();
                op = Ler.umInt();

                //operações de consulta colocadas aqui dentro do ciclo para que estejam constanetemente a serem atualizadas
                ArrayList<Filme> receivedFilmes = s.consultarFilmes();
                ArrayList<Transacao> receivedCompras = s.consultarCompras();

                switch (op) {
                    //registo de novo produto na loja
                    case 1:
                        Filme filme = new Filme();
                        filme.setId(id);
                        id++;
                        System.out.println("Introduza o nome: ");
                        filme.setNome(Ler.umaString());
                        System.out.println("Selecione uma categoria das seguintes:");
                        System.out.println(java.util.Arrays.asList(Filme.CATEGORIA.values()));
                        filme.setCategoria(Ler.umaString());
                        System.out.println("Stock mínimo: ");
                        filme.setStockMin(Ler.umInt());
                        filme.setDataCompraForn(LocalDate.now());

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
                        //TODO--ADRIANA: registar o filme recém criado no ficheiro de Filmes
                        break;

                    //listar todos os produtos
                    case 2:
                        //TODO: igual ao utilozadores
                        String st = "Lista de Filmes:\n";
                        for (int i = 0; i < receivedFilmes.size(); i++) {
                            st += receivedFilmes.get(i).toString() + "\n";
                        }
                        System.out.println(st);
                        break;

                    //listar compras
                    case 3:
                        String stC = "Lista de Compras:\n";
                        for (int i = 0; i < receivedCompras.size(); i++) {
                            stC += receivedCompras.get(i).toString() + "\n";
                        }
                        System.out.println(stC);
                        break;

                    /**
                     * REALIZAR COMPRA - Adicionar stock a um produuto
                     * existente: - Só é possível comprar um produto que já
                     * tenha sido registado previamente; - Vai procurar os
                     * diferemtes produtos disponíveis e vai permitir escolher
                     * um deles para adicionar stock;
                     */
                    case 4:
                        Transacao compra = new Transacao();
                        //filmesDestaCompra --> funciona como um carrinho de compras 
                        ArrayList<Filme> filmesDestaCompra = new ArrayList<Filme>();
                        ArrayList<Integer> quantidade = new ArrayList<Integer>();
                        int d = 1;
                        String nome;
                        boolean t = false;
                        while (d == 1) {
                            Filme f = new Filme();
                            System.out.println("---Nova compra---\n");
                            System.out.println("Insira o nome do filme a comprar:");
                            nome = Ler.umaString();
                            f.setNome(nome);
                            //verifica se o filme já foi previamente registado
                            for (int i = 0; i < receivedFilmes.size(); i++) {
                                if (receivedFilmes.get(i).getNome().equals(nome)) {
                                    t = true;
                                    break;
                                }
                            }
                            if (t == true) {
                                System.out.println("Preço de compra: ");
                                f.setPrecoCompra(Ler.umDouble());
                                System.out.println("Quantidade a comprar: ");
                                //guarda na posição respetiva o número de filmes
                                quantidade.add((Integer) Ler.umInt());
                                compra.setQuantidade(quantidade);

                                //adiciona o filme escolhido ao carrinho
                                filmesDestaCompra.add(f);
                                System.out.println("Filme " + f.getNome() + " adicionado ao carrinho.");
                                //define a lista de compras
                                compra.setFilmes(filmesDestaCompra);
                            } else {
                                System.out.println("O filme " + nome + "não está disponível para compra...");
                            }
                            System.out.println("Deseja comprar outro filme? (1-Sim 2-Não)");
                            d = Ler.umInt();

                        }
                        
                        System.out.println("Deseja confirmar a compra? (1-Sim 2-Não)");
                        //se o utilizador confirmar, então vai adicionar esta quantidade ao respetivo filme na lista de filmes (principal)
                        if (Ler.umInt() == 1) {
                            for (int i = 0; i < filmesDestaCompra.size(); i++) {
                                s.adicionarStock(filmesDestaCompra.get(i), quantidade.get(i));
                            }
                            //adiciona a transação à lista de compras
                            receivedCompras.add(compra);
                            //TODO-ADRIANA: registo no ficheiro
                        } else {
                            System.out.println("Compra cancelada pelo utilizador.");
                        }

                        break;

                    //Eliminar um filme da lista de filmes
                    case 5:
                        System.out.println("Introduza o nome do filme: ");
                        Filme f = new Filme();
                        f.setNome(Ler.umaString());
                        s.eliminarFilme(f);
                        break;

                    //sair
                    case 6:
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        } catch (Exception ex) {
            System.out.println("ClienteFornecedor exception: " + ex.getMessage());
        }

    }
}
