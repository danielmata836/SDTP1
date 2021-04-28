package SDTP1;

import java.rmi.Naming;
import java.util.ArrayList;

public class ClienteVendedor {

    public static void printMenuFilme1() {
        System.out.println("----------\nMENU\n----------\n1-Consultar Vendas\n2-Consultar Produto\n3-Atualizar Produto\n4-Sair\n----------");
    }

    public static void printSubMenu() {
        System.out.println("----------\nMENU\n----------\n1-Por Nome\n2-Por Realizador\n3-Por ID\n4-Sair\n----------");
    }

    //TODO: VERIFICAR LOGIN
    
    public static void main(String[] args) {
        System.setSecurityManager(new SecurityManager());
        try {
            ServidorInterface s = (ServidorInterface) Naming.lookup(Constants.RMI_ID);
            ClienteVendedor c = new ClienteVendedor();
            ArrayList<Filme> recetor = s.consultarFilmes();
            //todo: login

            int op = 0;
            while (op != 3) {
                printMenuFilme1();
                op = Ler.umInt();
                switch (op) {
                    case 1:
                        //Consultar Vendas

                        break;
                    case 2:
                        //

                        System.out.println("Pesquisar por:\n");
                        int op1 = 0;
                        boolean aviso = false;
                        printSubMenu();
                        op1 = Ler.umInt();
                        switch (op1) {
                            case 1:
                                System.out.println("Escreva o título do filme: \n");
                                String titulo = Ler.umaString();

                                for (int i = 0; i < recetor.size(); i++) {
                                    if (recetor.get(i).getNome().equals(titulo)) //Ver quantidade, o Daniel tem de atualizar.
                                    {
                                        System.out.println("Número de exemplares disponíveis/Número de exemplares vendidos: \n");
                                        aviso = true;
                                    }
                                }
                                if (aviso == false) {
                                    System.out.println("Não existem exemplares correspondentes à sua pesquisa.\n");
                                }
                                aviso = false;
                            case 2:
                                System.out.println("Escreva o nome do realizador: \n");
                                String realizador = Ler.umaString();

                                for (int i = 0; i < recetor.size(); i++) {
                                    if (recetor.get(i).getRealizador().equals(realizador)) //Ver quantidade, o Daniel tem de atualizar.
                                    {
                                        System.out.println("Título, exemplares disponíveis, exemplares vendidos: \n");
                                        System.out.println(recetor.get(i).getNome());
                                        aviso = true;
                                    }
                                }
                                if (aviso == false) {
                                    System.out.println("Não existem exemplares correspondentes à sua pesquisa.\n");
                                }
                                aviso = false;
                            case 3:
                                System.out.println("Escreva o número de identificação do filme: \n");
                                int ID = Ler.umInt();

                                for (int i = 0; i < recetor.size(); i++) {
                                    if (recetor.get(i).getId() == ID) //Ver quantidade, o Daniel tem de atualizar.
                                    {
                                        System.out.println("Título, exemplares disponíveis, exemplares vendidos: \n");
                                        System.out.println(recetor.get(i).getNome());
                                        aviso = true;
                                    }
                                }
                                if (aviso == false) {
                                    System.out.println("Não existem exemplares correspondentes à sua pesquisa.\n");
                                }
                                aviso = false;
                            case 4:
                                break;
                            default:
                                System.out.println("Opção inválida!");
                        }
                        break;

                    case 3:
                        //atualizar produto

                        System.out.println("Pesquisar Produto para atualizar por:\n");
                        int op2 = 0;
                        boolean aviso1 = false;
                        printSubMenu();
                        op1 = Ler.umInt();
                        switch (op2) {
                            case 1:
                                System.out.println("Escreva o título do filme: \n");
                                String titulo = Ler.umaString();

                                for (int i = 0; i < recetor.size(); i++) {
                                    if (recetor.get(i).getNome().equals(titulo)) //Atualizar SERVIDOR
                                    {
                                        System.out.println("Número de exemplares disponíveis/Número de exemplares vendidos: \n");
                                        System.out.println("Filme apagado com sucesso.");
                                        aviso1 = true;
                                    }
                                }
                                if (aviso1 == false) {
                                    System.out.println("Não existem exemplares correspondentes à sua pesquisa.\n");
                                }
                                aviso1 = false;
                            case 2:
                                System.out.println("Escreva o nome do realizador: \n");
                                String realizador = Ler.umaString();

                                for (int i = 0; i < recetor.size(); i++) {
                                    if (recetor.get(i).getRealizador().equals(realizador)) //Ver quantidade, o Daniel tem de atualizar.
                                    {
                                        System.out.println("Título, exemplares disponíveis, exemplares vendidos: \n");
                                        System.out.println(recetor.get(i).getNome());
                                        aviso1 = true;
                                    }
                                }
                                if (aviso1 == false) {
                                    System.out.println("Não existem exemplares correspondentes à sua pesquisa.\n");
                                }
                                aviso1 = false;
                            case 3:
                                System.out.println("Escreva o número de identificação do filme: \n");
                                int ID = Ler.umInt();

                                for (int i = 0; i < recetor.size(); i++) {
                                    if (recetor.get(i).getId() == ID) //Ver quantidade, o Daniel tem de atualizar.
                                    {
                                        System.out.println("Título, exemplares disponíveis, exemplares vendidos: \n");
                                        System.out.println(recetor.get(i).getNome());
                                        aviso1 = true;
                                    }
                                }
                                if (aviso1 == false) {
                                    System.out.println("Não existem exemplares correspondentes à sua pesquisa.\n");
                                }
                                aviso1 = false;
                            case 4:
                                break;
                            default:
                                System.out.println("Opção inválida!");
                        }
                        break;

                    case 4:
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
