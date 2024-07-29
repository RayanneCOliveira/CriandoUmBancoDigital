public class Main {

    public static void main(String[] args) {

        Cliente rayanne = new Cliente();
        rayanne.setNome("Rayanne");

        Conta cc = new ContaCorrente(rayanne);
        Conta poupanca = new ContaPoupanca(rayanne);

        cc.imprimirExtrato();
        System.out.println("---");
        poupanca.imprimirExtrato();

        System.out.println("---");

        cc.depositar(100);
        cc.depositar(400);
        cc.transferir(200, poupanca);
        poupanca.sacar(100);

        cc.imprimirExtrato();
        System.out.println("---");
        poupanca.imprimirExtrato();
    }
}
