package nl.fontys.withdrive.repositories;

import nl.fontys.withdrive.dto.ClientDTO;
import nl.fontys.withdrive.interfaces.ClientStorage;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FakeClientStorage implements ClientStorage {
    private final List<ClientDTO> clients;

    public FakeClientStorage(){
        clients = new ArrayList<>();
        clients.add(new ClientDTO(0,"JohnDoe@example.com","John","Doe","10-05-2002","Male","+42060605797","qwerty"));
    }

    @Override
    public boolean Create(ClientDTO client) {
        ClientDTO temp = RetrieveClientByNumber(client.getClientNumber());

        if(temp == null){
            this.clients.add(client);
            return true;
        }
        return  false;
    }

    @Override
    public List<ClientDTO> RetrieveAll() {
        return this.clients;
    }

    @Override
    public ClientDTO RetrieveByNumber(int number) {
        return RetrieveClientByNumber(number);
    }

    @Override
    public boolean Update(ClientDTO client) {
        ClientDTO temp = RetrieveClientByNumber(client.getClientNumber());

        if(temp != null){
            //Potentially could delete old and create new object but ensure the id will be the same?
            temp.setEmail(client.getEmail());
            temp.setFirstName(client.getFirstName());
            temp.setLastName(client.getLastName());
            temp.setDateOfBirth(client.getDateOfBirth());
            temp.setGender(client.getGender());
            temp.setPhoneNumber(client.getPhoneNumber());
            temp.setPassword(client.getPassword());
            return true;
        }
        return  false;
    }

    @Override
    public boolean Delete(int number) {
        ClientDTO temp = RetrieveClientByNumber(number);

        if(temp != null){
            this.clients.remove(temp);
            return true;
        }
        return false;
    }

    private ClientDTO RetrieveClientByNumber(int number){
        for (ClientDTO client : clients) {
            if (client.getClientNumber() == number)
                return client;
        }
        return  null;
    }
}


