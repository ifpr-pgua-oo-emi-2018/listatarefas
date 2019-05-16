import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();

        String titulo,descricao;
        int op;

        do{
            System.out.println("1 - Adicionar");
            System.out.println("2 - Listar");
            System.out.println("Digite?");
            op = scanner.nextInt();
            scanner.nextLine();

            switch (op){
                case 1:
                    System.out.println("Titulo:");
                    titulo = scanner.nextLine();
                    System.out.println("Descricao:");
                    descricao = scanner.nextLine();

                    Tarefa t = new Tarefa(titulo,descricao);

                    agenda.adicionar(t);
                    break;
                case 2:
                    System.out.println(agenda.lista());
                    break;

            }


        }while (op != 0);


        op = scanner.nextInt();


    }
}
