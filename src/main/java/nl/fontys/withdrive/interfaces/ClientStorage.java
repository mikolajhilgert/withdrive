package nl.fontys.withdrive.interfaces;

import nl.fontys.withdrive.dto.ClientDTO;

import java.util.List;

public interface ClientStorage {
    boolean Create(ClientDTO client);

    List<ClientDTO> RetrieveAll();

    ClientDTO RetrieveByNumber(int number);

    boolean Update(ClientDTO client);

    boolean Delete(int number);
}
