public class Main {
    public static void main(String[] args) {
        Cliente cl = new Cliente("tutu", "059.256.895-97", "07/07/2004", "salvador", 18);
        System.out.println(cl.getNome());
        System.out.println(cl.validarCPF());
    }
}
