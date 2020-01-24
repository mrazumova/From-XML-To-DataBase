package by.iba.connection;

public class RepositoryFactory {

    public static Repository getRepository(String type) throws Exception{
        Repository repository;
        switch (type) {
            case "mysql":
                repository = new MySQLRepository();
                break;
            case "msserver":
                repository = new MSServerRepository();
                break;
            default:
                throw new IllegalArgumentException("Wrong database type: " + type + ".\n");
        }
        return repository;
    }
}
