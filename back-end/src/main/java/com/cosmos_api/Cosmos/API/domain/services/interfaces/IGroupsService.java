package com.cosmos_api.Cosmos.API.domain.services.interfaces;

import com.cosmos_api.Cosmos.API.aplication.dto.dtoGroups;
import com.cosmos_api.Cosmos.API.domain.entities.Groups;
import com.cosmos_api.Cosmos.API.domain.entities.products.Products;
import java.util.List;
import java.util.Optional;

public interface IGroupsService {

    public List<Groups> listAlllGroups();

    public Groups getOneGroups(Long idGroups);

    public Groups saveGroups(dtoGroups dtogroups);

    public boolean deleteGroups(Long idGroups);

    public boolean existsByIdGroups(Long idGroups);

    public Optional<Groups> getByNameGroups(String Name);

    public Groups editGroups(Long idGroups, dtoGroups dtogroups);

    public boolean existsByNameGroups(String Name);
}
