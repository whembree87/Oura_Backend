package com.oura.backend.controller;

import com.oura.backend.entity.KedHeavyEntity;
import com.oura.backend.exceptions.HttpException;
import com.oura.backend.json_presenter.KedHeavyCountJsonPresenter;
import com.oura.backend.json_presenter.KedHeavyJsonPresenter;
import com.oura.backend.repo_manager.IKedHeavyRepoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping(value="/getpagedkedheavy", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<KedHeavyJsonPresenter> getPagedKedHeavy(@RequestParam int page, @RequestParam int size) {
        List<KedHeavyEntity> entities = kedHeavyRepoManager.getPagedKedHeavy(page, size).getContent();

        return KedHeavyJsonPresenter.from(entities);
    }

    @GetMapping(value="/getkedheavycount")
    public KedHeavyCountJsonPresenter getKedHeavyCount() {
        long count = kedHeavyRepoManager.getKedHeavyCount();

        if (count == 0) {
            throw new HttpException.HttpNoContentException();
        }

        return KedHeavyCountJsonPresenter.from(count);
    }
}
