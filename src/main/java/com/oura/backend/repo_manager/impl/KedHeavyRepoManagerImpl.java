package com.oura.backend.repo_manager.impl;

import com.oura.backend.entity.KedHeavyEntity;
import com.oura.backend.repo.IKedHeavyRepo;
import com.oura.backend.repo_manager.IKedHeavyRepoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class KedHeavyRepoManagerImpl implements IKedHeavyRepoManager {
    @Autowired
    private IKedHeavyRepo kedHeavyRepo;

    @Override
    public List<KedHeavyEntity> getKedHeavy() {
        return kedHeavyRepo.findAll();
    }

    public Page<KedHeavyEntity> getPagedKedHeavy(int page, int size) {
        return kedHeavyRepo.findAll(PageRequest.of(page, size));
    }

    public long getKedHeavyCount() {
        return kedHeavyRepo.count();
    }
}
