/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.catolicasc.programacaovi.lojaclient.clients;

import br.org.catolicasc.programacaovi.lojaclient.entities.Cliente;
import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import static javax.ws.rs.client.Entity.entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Jersey REST client generated for REST resource:ClienteResource
 * [/clientes]<br>
 * USAGE:
 * <pre>
 *        ClienteRESTClient client = new ClienteRESTClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author rodrigo
 */
public class ClienteRESTClient {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/Loja/api";

    public ClienteRESTClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("clientes");
    }

    public List<Cliente> getClientes() throws javax.ws.rs.ClientErrorException {
        WebTarget resource = webTarget;
        
        // Retorna a lista de clientes. Atentar para o metodo "GET" aqui.
        Response response = resource.request(MediaType.APPLICATION_JSON)
                .get(Response.class);
        
        if (response.getStatus() != 200)
            throw new ClientErrorException("Falha ao obter Clientes (GET)! Erro: " 
                + response.getStatus(), response);
        
        GenericType<List<Cliente>> gt = new GenericType<List<Cliente>>() {};
        
        return response.readEntity(gt);
        
    }
    
    public Cliente postClientes(Cliente cliente) throws ClientErrorException {
        // Faz um post do cliente aqui.
        Response response = webTarget.request(MediaType.APPLICATION_JSON)
                .post(entity(cliente,MediaType.APPLICATION_JSON), Response.class);
        if (response.getStatus() != 201)
            throw new ClientErrorException("Falha ao criar Cliente (POST)! Erro: " 
                    + response.getStatus(),response);
        
        return response.readEntity(Cliente.class);
    }

    public Response deleteCliente(String id) throws ClientErrorException {
        Response response = webTarget.path(id)
                .request().delete(Response.class);
        
        if (response.getStatus() != 200)
            throw new ClientErrorException("Falha ao remover Cliente (DELETE)! Erro: " 
                + response.getStatus(), response);
        
        return response;
    }

    public Cliente atualizaClientes(Cliente cliente) throws ClientErrorException {
        Response response = webTarget.request(MediaType.APPLICATION_JSON)
                            .put(entity(cliente, MediaType.APPLICATION_JSON)
                                , Response.class);
        
        if (response.getStatus() != 200)
            throw new ClientErrorException("Falha ao Atualizar Cliente (PUT)! Erro: " 
                + response.getStatus(), response);
        
        return response.readEntity(Cliente.class); 
    }

    public Cliente getCliente(String id) throws ClientErrorException {
        WebTarget resource = webTarget.path(id);
        Response response = resource.request(MediaType.APPLICATION_JSON)
                .get(Response.class);
        
        if (response.getStatus() != 200)
            throw new ClientErrorException("Falha ao obter Cliente (GET)! Erro: " 
                + response.getStatus(), response);
        
        return response.readEntity(Cliente.class);
    }

    public void close() {
        client.close();
    }
    
}
