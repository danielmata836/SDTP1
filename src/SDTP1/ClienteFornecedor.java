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
            while (op != 3) {
                printMenuFilme();
                op = Ler.umInt();

                //operações de consulta colocadas aqui dentro do ciclo para que estejam constanetemente a serem atualizadas
                ArrayList<Filme> receivedFilmes = s.consultarFilmes();
                ArrayList<Transacao> receivedCompras = s.consultarCompras();

                switch (op) {
                    //registo de novo produto na loja
                    //TODO: (Acabar de pedir as restantes informações)
                    case 1:
                        Filme filme = new Filme();
                        System.out.println("Introduza o nome: ");
                        filme.setNome(Ler.umaString());
                        System.out.println("Selecione a categoria das seguintes:");
                        System.out.println(java.util.Arrays.asList(Filme.CATEGORIA.values()));
                        filme.setCategoria(Ler.umaString());
                        System.out.println("Stock mínimo: ");
                        filme.setStockMin(Ler.umInt());
                        filme.setDataCompraForn(LocalDate.now());

                        s.registarFilme(filme);
                        break;

                    //listar todos os produtos
                    case 2:
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
                     * REALIZAR COMPRA - Adicionar stock a um produuto existente
                     * - Vai procurar os diferemtes produtos disponíveis e vai
                     * permitir escolher um deles para adicionar stock
                     */
                    case 4:
                        Transacao compra = new Transacao();
                        //filmesDestaCompra --> funciona como um carrinho de compras 
                        ArrayList<Filme> filmesDestaCompra = new ArrayList<Filme>();
                        int d = 1;
                        while (d == 1) {
                            Filme f = new Filme();
                            System.out.println("Insira o nome do filme comprado:");
                            f.setNome(Ler.umaString());
                            System.out.println("Preço de compra: ");
                            f.setPrecoCompra(Ler.umDouble());
                            System.out.println("Quantidade a comprar: ");
                            compra.setQuantidade(Ler.umInt());
                            
                            //adiciona o filme escolhido ao carrinho
                            filmesDestaCompra.add(f);
                            System.out.println("Filme "+f.getNome()+" adicionado ao carrinho.");
                            //define a lista de compras
                            compra.setFilmes(filmesDestaCompra);
                                                        
                            System.out.println("Deseja comprar outro filme? (1-Sim 2-Não)");
                            d = Ler.umInt();
                        }
                        System.out.println("Deseja confirmar a compra? (1-Sim 2-Não)");
                        if(Ler.umInt() == 1){
                           //TODO; adicionar stock
                        }
                            
                        break;
                        
                     //Eliminar um filme da lista de filmes
                    case 5:
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
