package nl.fontys.withdrive.interfaces;

import nl.fontys.withdrive.dto.ClientDTO;

import java.util.List;

public interface ClientSupplier{
    boolean Add(ClientDTO client);

    List<ClientDTO> RetrieveAll();

    ClientDTO RetrieveByNumber(int number);

    boolean Update(ClientDTO client);

    boolean Delete(int number);

}
