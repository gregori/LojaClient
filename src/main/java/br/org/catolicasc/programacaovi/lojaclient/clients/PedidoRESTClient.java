/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.catolicasc.programacaovi.lojaclient.clients;

import br.org.catolicasc.programacaovi.lojaclient.entities.Pedido;
import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import static javax.ws.rs.client.Entity.entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Jersey REST client generated for REST resource:PedidoResource [/pedidos]<br>
 * USAGE:
 * <pre>
 *        PedidoRESTClient client = new PedidoRESTClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author rodrigo
 */
public class PedidoRESTClient {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/Loja/api";

    public PedidoRESTClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("pedidos");
    }

    public Response postPedidos(Pedido pedido) throws ClientErrorException {
        Response response = webTarget.request(MediaType.APPLICATION_JSON)
                                .post(entity(pedido, MediaType.APPLICATION_JSON) 
                                    , Response.class);
        if (response.getStatus() != 201)
            throw new ClientErrorException("Falha ao criar Cliente (POST)! Erro: " 
                    + response.getStatus(),response);
        
        return response;
    }

    public Response atualizaPedidos(Pedido pedido) throws ClientErrorException {
        Response response = webTarget.request(MediaType.APPLICATION_JSON)
                            .put(entity(pedido, MediaType.APPLICATION_JSON)
                                , Response.class);
        
        if (response.getStatus() != 200)
            throw new ClientErrorException("PUT Pedido Falho! HTTP Error code " 
                + response.getStatus(), response);
        
        return response; 
    }

    public Response deletePedido(String id) throws ClientErrorException {
        Response response = webTarget.path(id)
                .request().delete(Response.class);
        
        if (response.getStatus() != 200)
            throw new ClientErrorException("DELETE Pedido Falho! HTTP Error code " 
                + response.getStatus(), response);
        
        return response;
    }

    public List<Pedido> getPedidos() throws ClientErrorException {
        WebTarget resource = webTarget;
        
        // Retorna a lista de pedidos. Atentar para o metodo "GET" aqui.
        Response response = resource.request(MediaType.APPLICATION_JSON)
                .get(Response.class);
        
        if (response.getStatus() != 200)
            throw new ClientErrorException("GET Pedidos Falho! HTTP Error code " 
                + response.getStatus(), response);
        
        GenericType<List<Pedido>> gt = new GenericType<List<Pedido>>() {};
        return response.readEntity(gt);
    }

    public Pedido getPedido(String id) throws ClientErrorException {
        WebTarget resource = webTarget.path(id);
        // Retorna o pedido de ID id. Atentar para o metodo "GET" aqui.
        Response response = resource.request(MediaType.APPLICATION_JSON)
                .get(Response.class);
        return response.readEntity(Pedido.class);
    }

    public void close() {
        client.close();
    }
    
}
