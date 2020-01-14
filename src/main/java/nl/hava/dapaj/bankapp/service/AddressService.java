package nl.hava.dapaj.bankapp.service;

import nl.hava.dapaj.bankapp.model.Address;
import nl.hava.dapaj.bankapp.model.dao.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressDao addressDao;

    public void save(Address address) {
        addressDao.save(address);
    }

    public Address getAddressByStreetandNumber(String companyStreet, int number) {
        return addressDao.findAddressByStreetAndHousenumber(companyStreet, number);
    }
}
