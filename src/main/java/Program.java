import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {

    public static void main(String [] args) {

        Locale.setDefault(Locale.US);
        Scanner ler = new Scanner(System.in);

        System.out.println("Informe o caminho do arquivo de texto: ");
        String arquivo = ler.nextLine();

        try {
            FileReader arq = new FileReader(arquivo);
            BufferedReader lerArquivo = new BufferedReader(arq);

            String linha = lerArquivo.readLine();

            List<Employee> employees = new ArrayList<>();

            while (linha != null) {
                System.out.printf("%s\n", linha);
                String [] fields = linha.split(",");
                employees.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                linha = lerArquivo.readLine();
            }

            System.out.println("Informe o valor do salário que deseja pesquisar");
            Double salario = ler.nextDouble();

            List<String> nomes = employees.stream()
                    .filter(e -> e.getSalary() > salario)
                    .map(Employee::getName)
                    .collect(Collectors.toList());

            System.out.println("Funcionários com salários maior do que " + salario.toString() + ": " + nomes.toString());

            Double soma = employees.stream()
                    .filter(e -> e.getName().startsWith("M"))
                    .map(Employee::getSalary)
                    .reduce(0.0, Double::sum);

            System.out.println("Soma dos salários de funcionários que começam com a letra M: " + soma);

        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
    }
}
