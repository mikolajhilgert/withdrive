package nl.fontys.withdrive.services;

import nl.fontys.withdrive.dto.ClientDTO;
import nl.fontys.withdrive.interfaces.ClientStorage;
import nl.fontys.withdrive.interfaces.ClientSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientManager implements ClientSupplier {

    @Autowired
    private ClientStorage saved;

    @Override
    public boolean Add(ClientDTO client) {
        return this.saved.Create(client);
    }

    @Override
    public List<ClientDTO> RetrieveAll() {
        return this.saved.RetrieveAll();
    }

    @Override
    public ClientDTO RetrieveByNumber(int number) {
        return this.saved.RetrieveByNumber(number);
    }

    @Override
    public boolean Update(ClientDTO client) {
        return this.saved.Update(client);
    }

    @Override
    public boolean Delete(int number) {
        return this.saved.Delete(number);
    }
}
