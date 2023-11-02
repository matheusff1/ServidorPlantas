package servidor;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import netscape.javascript.JSObject;
import org.bson.Document;

import java.util.ArrayList;

public class ConectaMongo {
    private static final String URL_CONEXAO = "mongodb+srv://juliano:juliano777@clustera.p1mjddc.mongodb.net/?retryWrites=true&w=majority";
    private static final String NOME_BANCO_DADOS = "app";

    private static final ServerApi serverApi = ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build();
    private static final MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(URL_CONEXAO))
            .serverApi(serverApi)
            .build();

    private static final MongoClient mongoClient = MongoClients.create(settings);

    private static final MongoDatabase appDb = mongoClient.getDatabase(NOME_BANCO_DADOS);

    public static void salvarProduto(Produto obj){
        MongoCollection<Document> produtos = appDb.getCollection("produtos");
        produtos.insertOne(new Document(obj.toString(),obj.hashCode()));
    }

    public static void salvarVendedor(Vendedor obj){
        MongoCollection<Document> vendedores = appDb.getCollection("vendedores");
        vendedores.insertOne(new Document(obj.toString(),obj.hashCode()));
    }


}
