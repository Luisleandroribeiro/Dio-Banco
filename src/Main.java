import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Conta> contas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = 0;
        do {
            System.out.println("\n=== Banco Digital ===");
            System.out.println("1. Criar Conta Corrente");
            System.out.println("2. Criar Conta Poupança");
            System.out.println("3. Depositar");
            System.out.println("4. Sacar");
            System.out.println("5. Transferir");
            System.out.println("6. Visualizar Extrato");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a nova linha

            switch (opcao) {
                case 1:
                    criarContaCorrente();
                    break;
                case 2:
                    criarContaPoupanca();
                    break;
                case 3:
                    depositar();
                    break;
                case 4:
                    sacar();
                    break;
                case 5:
                    transferir();
                    break;
                case 6:
                    visualizarExtrato();
                    break;
                case 7:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 7);
    }

    private static void criarContaCorrente() {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        Conta conta = new ContaCorrente(cliente);
        contas.add(conta);
        System.out.println("Conta Corrente criada com sucesso! Número da conta: " + conta.getNumero());
    }

    private static void criarContaPoupanca() {
        System.out.print("Nome do cliente: ");
        String nome = scanner.nextLine();
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        Conta conta = new ContaPoupanca(cliente);
        contas.add(conta);
        System.out.println("Conta Poupança criada com sucesso! Número da conta: " + conta.getNumero());
    }

    private static void depositar() {
        Conta conta = buscarConta();
        if (conta != null) {
            System.out.print("Valor para depositar: ");
            double valor = scanner.nextDouble();
            conta.depositar(valor);
            System.out.println("Depósito realizado com sucesso!");
        }
    }

    private static void sacar() {
        Conta conta = buscarConta();
        if (conta != null) {
            System.out.print("Valor para sacar: ");
            double valor = scanner.nextDouble();
            conta.sacar(valor);
            System.out.println("Saque realizado com sucesso!");
        }
    }

    private static void transferir() {
        System.out.println("Conta de origem:");
        Conta contaOrigem = buscarConta();
        if (contaOrigem != null) {
            System.out.println("Conta de destino:");
            Conta contaDestino = buscarConta();
            if (contaDestino != null) {
                System.out.print("Valor para transferir: ");
                double valor = scanner.nextDouble();
                contaOrigem.transferir(valor, contaDestino);
                System.out.println("Transferência realizada com sucesso!");
            }
        }
    }

    private static void visualizarExtrato() {
        Conta conta = buscarConta();
        if (conta != null) {
            conta.imprimirExtrato();
        }
    }

    private static Conta buscarConta() {
        System.out.print("Número da conta: ");
        int numero = scanner.nextInt();
        for (Conta conta : contas) {
            if (conta.getNumero() == numero) {
                return conta;
            }
        }
        System.out.println("Conta não encontrada.");
        return null;
    }
}
