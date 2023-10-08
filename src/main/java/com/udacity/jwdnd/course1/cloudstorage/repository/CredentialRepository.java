package com.udacity.jwdnd.course1.cloudstorage.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialModel;
import com.udacity.jwdnd.course1.cloudstorage.repository.generic_repository.T_Repository;

@Repository
public class CredentialRepository extends T_Repository<CredentialModel> {

    private List<CredentialModel> credentialModels = new ArrayList<>();

    @Override
    public List<CredentialModel> getAll() {
        return credentialModels;
    }

    @Override
    public CredentialModel get(int id) {
        return credentialModels.get(id);
    }

    @Override
    public void add(CredentialModel t) {
        credentialModels.add(t);

    }

    @Override
    public void update(CredentialModel t) {
        credentialModels.stream().map(i -> {
            if (i.getCredentialId() == t.getCredentialId()) {
                i.setKey(t.getKey());
                i.setPassword(t.getPassword());
                i.setUrl(t.getUrl());
                i.setUsername(t.getUsername());
            }
            return i;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteByID(int id) {
        CredentialModel note = credentialModels.stream().filter(i -> i.getCredentialId() == id).findFirst()
                .orElse(null);
        credentialModels.remove(note);

    }

    @Override
    public void delete(CredentialModel t) {
        credentialModels.remove(t);

    }

}
