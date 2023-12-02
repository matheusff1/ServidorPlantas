package com.dijonz.smartplants;

import com.mongodb.*;
import com.mongodb.client.*;
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

    public static void salvarProduto(Produto produto) {
        Document documentoVendedor = new Document()
                .append("nome", produto.getNome())
                .append("preço",produto.getPreco());

        produtos.insertOne(documentoVendedor);
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

        try (MongoCursor<Document> cursor = produtos.find().iterator()) {
            while (cursor.hasNext()) {
                Document elemento = cursor.next();

                listaElementos.add(new Produto(elemento.getString("nome")));
            }
        }

        return listaElementos;
    }

    public static ArrayList<Produto> buscarProdutosVendedor(String email) {
        ArrayList<Produto> listaVendedores = new ArrayList<>();

        BasicDBObject query = new BasicDBObject("vendedor", email);

        try (MongoCursor<Document> cursor = produtos.find(query).iterator()) {
            while (cursor.hasNext()) {
                Document documentoProduto = cursor.next();

                Produto produto = new Produto(
                        documentoProduto.getString("nome")
                );
                listaVendedores.add(produto);
            }
        }

        return listaVendedores;
    }

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
                        documentoVendedor.getString("local"),
                        documentoVendedor.getString("fotoUri")
                );

            }
        }

        return vendedor;
    }



}
