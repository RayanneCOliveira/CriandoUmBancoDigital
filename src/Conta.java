import lombok.Getter;

@Getter
public abstract class Conta implements iConta {

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numeroDaConta;
    protected double saldo;
    protected Cliente cliente;
    protected Extrato extrato;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numeroDaConta = SEQUENCIAL++;
        this.cliente = cliente;
        this.extrato = new Extrato();
    }

    @Override
    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo && ((valor % 10 == 0) || (valor % 20 == 0) || (valor % 50 == 0) || (valor % 100 == 0))) {
            saldo -= valor;
            extrato.adicionarRegistro(String.format("Saque: R$ %.2f", valor));
        } else if (valor <= saldo && ((valor % 10 != 0) || (valor % 20 != 0) || (valor % 50 != 0) || (valor % 100 != 0))) {
            throw new RuntimeException("Valor inválido. Operação não realizada.");
        } else {
            throw new RuntimeException("Saldo insuficiente. Operação não realizada.");
        }
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            extrato.adicionarRegistro(String.format("Depósito: R$ %.2f", valor));
        } else {
            throw new RuntimeException("Valor inválido. Operação não realizada.");
        }
    }

    @Override
    public void transferir(double valor, iConta contaDestino) {
        if (valor <= saldo) {
            realizarSaqueTransferencia(valor);
            contaDestino.receberValorTransferencia(valor);
            extrato.adicionarRegistro(String.format("Transferência enviada: R$ %.2f para conta de destino", valor));
        } else {
            throw new RuntimeException("Saldo insuficiente. Operação não realizada.");
        }
    }

    public void realizarSaqueTransferencia(double valor) {
        saldo -= valor;
    }

    public void receberValorTransferencia(double valor) {
        saldo += valor;
        extrato.adicionarRegistro(String.format("Transferência recebida: R$ %.2f", valor));
    }

    public void imprimirExtrato() {
        imprimirInfosComuns();
        extrato.imprimirExtrato();
    }

    protected void imprimirInfosComuns() {
        System.out.printf("Titular: %s ", this.cliente.getNome());
        System.out.printf("Agência: %d ", this.agencia);
        System.out.printf("Conta: %d\n", this.numeroDaConta);
        System.out.printf("Saldo: %.2f\n", this.saldo);
    }
}
