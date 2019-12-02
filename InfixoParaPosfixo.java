import java.io.IOException;

public class InfixoParaPosfixo {
   private Pilha pilha;
   private String input;
   private String output = "";
   public InfixoParaPosfixo(String in) {
      input = in;
      int stackSize = input.length();
      pilha = new Pilha(stackSize);
   }
   public String converter() {
      for (int j = 0; j < input.length(); j++) {
         char caractere = input.charAt(j);
         switch (caractere) {
            case '+': 
            case '-':
               ehOperador(caractere, 1); 
               break; 
            case '*': 
            case '/':
               ehOperador(caractere, 2); 
               break; 
            case '^':
                  ehOperador(caractere, 3); 
                  break; 
            case '(': 
               pilha.push(caractere);
               break;
            case ')': 
               ehParenteses(caractere); 
               break;
            default: 
               output = output + caractere; 
               break;
         }
      }
      while (!pilha.isEmpty()) {
         output = output + pilha.pop();
      }
      System.out.println(output);
      return output; 
   }
   public void ehOperador(char operador, int prec1) {
      while (!pilha.isEmpty()) {
         char opTop = pilha.pop();
         if (opTop == '(') {
            pilha.push(opTop);
            break;
         } else {
            int prec2;
            if (opTop == '+' || opTop == '-')
            prec2 = 1;
            else
            prec2 = 2;
            if (prec2 < prec1) { 
               pilha.push(opTop);
               break;
            } 
            else output = output + opTop;
         }
      }
      pilha.push(operador);
   }
   public void ehParenteses(char ch) { 
      while (!pilha.isEmpty()) {
         char chx = pilha.pop();
         if (chx == '(') 
         break; 
         else output = output + chx; 
      }
   }
   public static void main(String[] args) throws IOException {
      System.out.println("Digite a expressão: ");
      String input = System.console().readLine();
      
      String output;
      InfixoParaPosfixo theTrans = new InfixoParaPosfixo(input);
      output = theTrans.converter(); 
      System.out.println("Notação infixa é " + input + '\n');
      System.out.println("A notação pós-fixa é " + output + '\n');
   }
   class Pilha {
      private int tamanhoMaximo;
      private char[] vetorPilha;
      private int topo;
      
      public Pilha(int max) {
         tamanhoMaximo = max;
         vetorPilha = new char[tamanhoMaximo];
         topo = -1;
      }
      public void push(char j) {
         vetorPilha[++topo] = j;
      }
      public char pop() {
         return vetorPilha[topo--];
      }
      public char peek() {
         return vetorPilha[topo];
      }
      public boolean isEmpty() {
         return (topo == -1);
      }
   }
}
