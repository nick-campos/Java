import java.util.Scanner;

public class EntradaEstoque {
    public static void main (String[] args) {
        Scanner leitor = new Scanner(System.in);
        int quantidadeEstoque = lerInteiroNaoNegativo(leitor, "Informe a quantidade: ");
        System.out.println("Estoque atualizado! Quantidade: " + quantidadeEstoque);
        leitor.close();
    }

    public static int lerInteiroNaoNegativo(Scanner leitor, String mensagemUsuario) {
        //Continua solicitando um valor até receber uma entrada válida.
        while (true) {
            System.out.print(mensagemUsuario);
            String textoDigitado = leitor.nextLine().trim();
            try {
                //Tenta converter o texto para um número inteiro.
                int numeroInformado = Integer.parseInt(textoDigitado);
                if (numeroInformado < 0) {
                    //Se for negativo, avisa e pede novamente.
                    System.out.println("Quantidade inválida. Informe zero ou um numero positivo.");
                    continue;
                }
                //Se for válido, devolve o número e encerra o método.
                return numeroInformado;
            } catch (NumberFormatException erro) {
                //Acontece se a entrada não puder ser convertida em inteiro.
                System.out.println("Texto digitado inválido. Digite somente números inteiros.");
            }
        }
    }
}