public class App {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        System.out.println("clientID = " + clientService.create("Jon Bon Jovi"));
        System.out.println("clientName = " + clientService.getById(1));
        clientService.setName(6, "Piter Parker");
        clientService.deleteById(5);
        System.out.println("clientList = " + clientService.listAll());
    }
}
