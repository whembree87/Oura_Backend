package com.oura.backend.controller;

import com.oura.backend.entity.KedHeavyEntity;
import com.oura.backend.json_presenter.KedHeavyJsonPresenter;
import com.oura.backend.repo_manager.IKedHeavyRepoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class KedController {
    @Autowired
    IKedHeavyRepoManager kedHeavyRepoManager;

    @GetMapping(value = "/getkedheavy", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<KedHeavyJsonPresenter> getKedHeavy() {
        List<KedHeavyEntity> entities = kedHeavyRepoManager.getKedHeavy();

        return KedHeavyJsonPresenter.from(entities);
    }
}
