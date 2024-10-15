package com.demo.restuarant.service;

import com.demo.restuarant.DTO.AddressDTO;
import com.demo.restuarant.Model.Address;
import com.demo.restuarant.Repository.AddressRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepo addressRepo;
    @Autowired
    private ModelMapper mapper;

    public List<AddressDTO> fetchAddress() {
        List<AddressDTO> addressDTOList = new ArrayList<>();
        List<Address> addressList = addressRepo.findAll();
        for(Address address:addressList){
            AddressDTO builder = new AddressDTO();
            builder.setId(address.getId());
            builder.setCountry(address.getCountry());
            builder.setState(address.getState());
            builder.setCity((address.getCity()));
            builder.setPincode(address.getPincode());
            builder.setLandMark(address.getLandMark());
            builder.setStreet(address.getStreet());
            addressDTOList.add(builder);
        }
        return addressDTOList;
    }

    public AddressDTO getAddressById(Long id){
       Optional<Address> address = addressRepo.findById(id);
       if(address.isPresent()){
           Address address1 = address.get();
           return mapper.map(address1,AddressDTO.class);
       }else{
           return null;
       }
    }

    public Address createAddress(Address address){
        return addressRepo.save(address);
    }

    public Address updateAddress(Long id, Address address){
      Address address1 =  addressRepo.findById(id).orElseThrow(()-> new
              RuntimeException("Record not found in db with id : "+id));
      address1.setCountry(address.getCountry());
      address1.setState(address.getState());
      address1.setCity(address.getCity());
      address1.setStreet(address.getStreet());
      address1.setLandMark(address.getLandMark());
      address1.setPincode(address.getPincode());
      Address res = addressRepo.save(address1);
      return res;
    }

    public Address deleteAddress(Long id){
        Optional<Address> res = addressRepo.findById(id);
        if(res.isPresent()) {
            addressRepo.deleteById(id);
            return res.get();
        } else {
            throw new RuntimeException("Record Not found with the id");
        }

    }
}
