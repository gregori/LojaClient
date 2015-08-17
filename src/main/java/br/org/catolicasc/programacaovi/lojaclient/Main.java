/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.catolicasc.programacaovi.lojaclient;

import br.org.catolicasc.programacaovi.lojaclient.clients.LojaClient;
import br.org.catolicasc.programacaovi.lojaclient.entities.Cliente;
import java.util.List;
import javax.ws.rs.core.Response;


/**
 *
 * @author rodrigo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LojaClient client = new LojaClient();
        Cliente c = new Cliente();
        
        c.setNome("Ricardo");
        c.setSobrenome("Magalhaes");
        c.setCidade("Xique Xique");
        c.setEstado("BA");
        c.setCep("47400-000");
        c.setRua("Praça Dom Máximo, 384");
        c.setPais("Brasil");
        Response r = client.postClientes(c);
        System.out.println("Post status: " + r.getStatus());
        List<Cliente> clientes = client.getClientes();
        
        for (Cliente cliente : clientes) 
            System.out.println(cliente.toString());
        
    }
    
}
