package servidor;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import netscape.javascript.JSObject;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

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

    private static  final MongoCollection<Document> produtos  = appDb.getCollection("produtos");
    private static  final MongoCollection<Document> vendedores  = appDb.getCollection("vendedores");


    public static void salvarVendedor(Vendedor vendedor) {
        Document documentoVendedor = new Document()
                .append("nome", vendedor.getNome())
                .append("email", vendedor.getEmail())
                .append("senha", vendedor.getSenha())
                .append("telefone", vendedor.getTelefone())
                .append("local", vendedor.getLocal())
                .append("fotoUri", vendedor.getFotoUri());

        vendedores.insertOne(documentoVendedor);
    }

    public static ArrayList<Vendedor> buscarVendedores( ) {
        ArrayList<Vendedor> listaVendedores = new ArrayList<>();

        try (MongoCursor<Document> cursor = vendedores.find().iterator()) {
            while (cursor.hasNext()) {
                Document documentoVendedor = cursor.next();

                Vendedor vendedor = new Vendedor(
                        documentoVendedor.getString("nome"),
                        documentoVendedor.getString("email"),
                        documentoVendedor.getString("senha"),
                        documentoVendedor.getString("telefone"),
                        documentoVendedor.getString("local"),
                        documentoVendedor.getString("fotoUri")
                );
                listaVendedores.add(vendedor);
            }
        }

        return listaVendedores;
    }

    public static ArrayList<Produto> buscarProdutos( ) {
        ArrayList<Produto> listaElementos = new ArrayList<>();

        try (MongoCursor<Document> cursor = vendedores.find().iterator()) {
            while (cursor.hasNext()) {
                Document elemento = cursor.next();

                listaElementos.add(new Produto(elemento.getString("nome")));
            }
        }

        return listaElementos;
    }


}
