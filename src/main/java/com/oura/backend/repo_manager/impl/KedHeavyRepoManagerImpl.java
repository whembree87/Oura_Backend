package com.oura.backend.repo_manager.impl;

import com.oura.backend.entity.KedHeavyEntity;
import com.oura.backend.repo.IKedHeavyRepo;
import com.oura.backend.repo_manager.IKedHeavyRepoManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class KedHeavyRepoManagerImpl implements IKedHeavyRepoManager {
    @Autowired
    private IKedHeavyRepo kedHeavyRepo;

    @Override
    public List<KedHeavyEntity> getKedHeavy() {
        return null;
    }
}
