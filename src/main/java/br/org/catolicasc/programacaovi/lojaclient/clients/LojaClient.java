/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.catolicasc.programacaovi.lojaclient.clients;

import br.org.catolicasc.programacaovi.lojaclient.entities.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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
 *        LojaClient client = new LojaClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author rodrigo
 */
public class LojaClient {
    // Nessa variavel armazenaremos o recurso que essa classe trataral
    private WebTarget webTarget;
    // Essa variavel e' o cliente REST
    private Client client;
    // URI base da aplicacao
    private static final String BASE_URI = "http://localhost:8080/Loja/api";

    public LojaClient() {
        // Instanciamos um cliente novo
        client = ClientBuilder.newClient();
        // Apontamos para o recurso 'clientes'
        webTarget = client.target(BASE_URI).path("clientes");
    }

    public List<Cliente> getClientes() throws javax.ws.rs.ClientErrorException {
        WebTarget resource = webTarget;
        
        // Retorna a lista de clientes. Atentar para o metodo "GET" aqui.
        Response response = resource.request(MediaType.APPLICATION_JSON)
                .get(Response.class);
        
        if (response.getStatus() != 200)
            throw new ClientErrorException("Failed! HTTP Error code " 
                + response.getStatus(), response);
        
        GenericType<List<Cliente>> gt = new GenericType<List<Cliente>>() {};
        
        return response.readEntity(gt);
        
    }

    public Response postClientes(Cliente cliente) throws ClientErrorException {
        // Faz um post do cliente aqui.
        Response response = webTarget.request(MediaType.APPLICATION_JSON)
                .post(entity(cliente,MediaType.APPLICATION_JSON), Response.class);
        if (response.getStatus() != 201)
            throw new ClientErrorException("POST falho! Erro: " + response.getStatus(),response);
        
        return response;
    }
    
    

    public void close() {
        client.close();
    }
    
}
