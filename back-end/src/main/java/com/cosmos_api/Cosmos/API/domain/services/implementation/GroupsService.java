package com.cosmos_api.Cosmos.API.domain.services.implementation;

import com.cosmos_api.Cosmos.API.aplication.dto.dtoGroups;
import com.cosmos_api.Cosmos.API.domain.entities.Groups;
import com.cosmos_api.Cosmos.API.domain.entities.products.Products;
import com.cosmos_api.Cosmos.API.domain.repository.RepositoryGroups;
import com.cosmos_api.Cosmos.API.domain.repository.RepositoryProducts;
import com.cosmos_api.Cosmos.API.domain.services.interfaces.IGroupsService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupsService implements IGroupsService {

    @Autowired
    private final RepositoryProducts productsRepository;

    @Autowired
    private final RepositoryGroups groupsRepository;

    @Override
    public List<Groups> listAlllGroups() {
        return this.groupsRepository.findAll();
    }

    @Override
    public Groups getOneGroups(Long idGroups) {
        return groupsRepository.findByIdGroups(idGroups)
                .orElseThrow(() -> new RuntimeException("groups with that ID dont exist"));
    }

    @Override
    public Groups saveGroups(dtoGroups dtogroups) {

        Groups groups = Groups.builder()
                .name(dtogroups.getName())
                .description(dtogroups.getDescription())
                .build();

        return groupsRepository.save(groups);
    }

    @Override
    public boolean deleteGroups(Long idGroups) {
        Optional<Groups> groupsDelete = this.groupsRepository.findById(idGroups);
        if (groupsDelete.isPresent()) {
            this.productsRepository.deleteById(idGroups);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean existsByIdGroups(Long idGroups) {
        return groupsRepository.existsById(idGroups);
    }

    @Override
    public Optional<Groups> getByNameGroups(String Name) {
        return groupsRepository.findByName(Name);
    }

    @Override
    public Groups editGroups(Long idGroups, dtoGroups dtogroups) {

        Groups groups = this.getOneGroups(idGroups);

        groups.setName(dtogroups.getName());
        groups.setDescription(dtogroups.getDescription());
        
        List<Products> newProductsList = new ArrayList<>();
        
        for (Products dtoproduct : dtogroups.getProducts()) {
        // Crear un nuevo producto a partir del DTO y asignar el grupo
        Products product = Products.builder()
                .name(dtoproduct.getName())
                .description(dtoproduct.getDescription())
                .brand(dtoproduct.getBrand())
                .category(dtoproduct.getCategory())
                .code(dtoproduct.getCode())
                .price(dtoproduct.getPrice())
                .stock(dtoproduct.getStock())
                .color(dtoproduct.getColor())
                .discount(dtoproduct.getDiscount())
                .tag(dtoproduct.getTag())
                .groups(groups) // Asignar el grupo
                .build();
        newProductsList.add(product);
    }
        
        
      
        groups.setProducts(newProductsList);
        return  groupsRepository.save(groups);
    }

    @Override
    public boolean existsByNameGroups(String Name) {
        return groupsRepository.existsByName(Name);
    }

}
