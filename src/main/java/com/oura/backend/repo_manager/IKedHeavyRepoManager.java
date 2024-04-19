package com.oura.backend.repo_manager;

import com.oura.backend.entity.KedHeavyEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IKedHeavyRepoManager {
    List<KedHeavyEntity> getKedHeavy();

    Page<KedHeavyEntity> getPagedKedHeavy(int page, int size);
}
