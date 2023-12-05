package com.dijonz.smartplants;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;

// Classe responsável por realizar operações de conexão e manipulação de dados no MongoDB
public class ConectaMongo {
    // URL de conexão ao banco de dados MongoDB
    private static final String URL_CONEXAO = "mongodb+srv://juliano:juliano777@clustera.p1mjddc.mongodb.net/?retryWrites=true&w=majority";
    private static final String NOME_BANCO_DADOS = "app";

    // Configurações do cliente MongoDB
    private static final ServerApi serverApi = ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build();
    private static final MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(URL_CONEXAO))
            .serverApi(serverApi)
            .build();

    // Cliente MongoDB
    private static final MongoClient mongoClient = MongoClients.create(settings);

    // Banco de dados MongoDB
    private static final MongoDatabase appDb = mongoClient.getDatabase(NOME_BANCO_DADOS);

    // Coleções MongoDB
    private static  final MongoCollection<Document> produtos  = appDb.getCollection("produtos");
    private static  final MongoCollection<Document> vendedores  = appDb.getCollection("vendedores");


    // Método para salvar um vendedor no MongoDB
    public static void salvarVendedor(Vendedor vendedor) {
        Document documentoVendedor = new Document()
                .append("nome", vendedor.getNome())
                .append("email", vendedor.getEmail())
                .append("senha", vendedor.getSenha())
                .append("telefone", vendedor.getTelefone())
                .append("local", vendedor.getLocal());
                //.append("fotoUri", vendedor.getFotoUri()

        vendedores.insertOne(documentoVendedor);
    }

    // Método para salvar um produto no MongoDB
    public static void salvarProduto(Produto produto) {
        Document documentoVendedor = new Document()
                .append("nome", produto.getNome())
                .append("preço",produto.getPreco());

        produtos.insertOne(documentoVendedor);
    }

    // Método para buscar todos os vendedores no MongoDB
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
                        documentoVendedor.getString("local")
                        //documentoVendedor.getString("fotoUri")
                );
                listaVendedores.add(vendedor);
            }
        }

        return listaVendedores;
    }

    // Método para buscar todos os produtos no MongoDB
    public static ArrayList<Produto> buscarProdutos( ) {
        ArrayList<Produto> listaElementos = new ArrayList<>();

        try (MongoCursor<Document> cursor = produtos.find().iterator()) {
            while (cursor.hasNext()) {
                Document elemento = cursor.next();

                listaElementos.add(new Produto(elemento.getString("nome"),elemento.getString("preço")));
            }
        }

        return listaElementos;
    }

    // Método para buscar todos os produtos de um vendedor específico no MongoDB
    public static ArrayList<Produto> buscarProdutosVendedor(String email) {
        ArrayList<Produto> listaVendedores = new ArrayList<>();

        BasicDBObject query = new BasicDBObject("vendedor", email);

        try (MongoCursor<Document> cursor = produtos.find(query).iterator()) {
            while (cursor.hasNext()) {
                Document documentoProduto = cursor.next();

                Produto produto = new Produto(
                        documentoProduto.getString("nome"), documentoProduto.getString("preço")
                );
                listaVendedores.add(produto);
            }
        }

        return listaVendedores;
    }

    // Método para buscar um vendedor pelo email no MongoDB
    public static Vendedor buscarUsuarioVendedor(String email) {
        Vendedor vendedor =null;

        BasicDBObject query = new BasicDBObject("email", email);

        try (MongoCursor<Document> cursor = produtos.find(query).iterator()) {
            while (cursor.hasNext()) {
                Document documentoVendedor = cursor.next();

                vendedor= new Vendedor(
                        documentoVendedor.getString("nome"),
                        documentoVendedor.getString("email"),
                        documentoVendedor.getString("senha"),
                        documentoVendedor.getString("telefone"),
                        documentoVendedor.getString("local")
                        //documentoVendedor.getString("fotoUri")
                );

            }
        }

        return vendedor;
    }



}
