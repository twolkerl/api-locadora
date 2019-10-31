package com.twl.apilocadora.service.impl;

import com.twl.apilocadora.dto.LocacaoDto;
import com.twl.apilocadora.model.InventarioFilme;
import com.twl.apilocadora.repository.InventarioFilmeRepository;
import com.twl.apilocadora.service.InventarioFilmeService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class InventarioFilmeServiceImpl extends CrudServiceImpl<InventarioFilme, Long> implements InventarioFilmeService {

    public InventarioFilmeServiceImpl(InventarioFilmeRepository repository) {
        super(repository);
    }

    @Override
    protected InventarioFilmeRepository getRepository() {
        return (InventarioFilmeRepository) super.getRepository();
    }

    @Override
    public Integer countByIdFilme(Long idFilme) {
        return getRepository().countByIdFilme(idFilme);
    }

    @Override
    public Integer countAvailableByIdFilme(Long idFilme) {
        return getRepository().countByIdFilmeAndIdUsuarioIsNull(idFilme);
    }

    @Override
    public List<InventarioFilme> findAllByIdFilme(Long idFilme) {
        return getRepository().findAllByIdFilme(idFilme);
    }

    @Override
    public List<InventarioFilme> rent(LocacaoDto locacaoDto) {

        List<InventarioFilme> inventarioFilmeList = new ArrayList<>();

        locacaoDto.getIdsFilme()
                .forEach(id -> inventarioFilmeList.add(getRepository().findFirstByIdFilmeAndIdUsuarioIsNull(id)));

        if (!CollectionUtils.isEmpty(inventarioFilmeList)) {

            inventarioFilmeList.forEach(inventarioFilme -> inventarioFilme.setIdUsuario(locacaoDto.getIdUsuario()));

            return getRepository().saveAll(inventarioFilmeList);
        } else {
            // TODO jogar exceção quando não encontrar filme em estoque
        }

        return null;
    }

    @Override
    public void receiveAll(Long idUsuario) {

        List<InventarioFilme> inventarioFilmeList = getRepository().findAllByIdUsuario(idUsuario);

        if (!CollectionUtils.isEmpty(inventarioFilmeList)) {

            inventarioFilmeList.forEach(inventarioFilme -> inventarioFilme.setIdUsuario(null));
            getRepository().saveAll(inventarioFilmeList);
        } else {
            // TODO jogar exceção quando não encontrar locação
        }
    }

    @Override
    public void receive(Long idUsuario, Long idFilme) {

        InventarioFilme inventarioFilme = getRepository().findByIdUsuarioAndIdFilme(idUsuario, idFilme);

        if (Objects.nonNull(inventarioFilme)) {

            inventarioFilme.setIdUsuario(null);
            getRepository().save(inventarioFilme);
        } else {
            // TODO jogar exceção quando não encontrar locação
        }
    }
}
