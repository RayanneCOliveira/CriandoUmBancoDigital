public interface iConta {

    void sacar(double valor);
    void depositar(double valor);
    void transferir(double valor, iConta contaDestino);
    void receberValorTransferencia(double valor);
    void realizarSaqueTransferencia(double valor);
}
