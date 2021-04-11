package com.klid.demodockercompose.search.typesense;

import com.klid.demodockercompose.entity.Student;
import com.klid.demodockercompose.search.StudentSearchIndexer;
import com.klid.demodockercompose.search.typesense.TypesenseCollectionModel.TypesenseCollectionFieldModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ivan Kaptue
 */
@RequiredArgsConstructor
@Service
public class StudentTypesenseIndexer implements StudentSearchIndexer {

    private static final String baseUrl = "http://localhost:8108/collections/";
    private static final String name = "students";
    private static final String key = "typesensekey";

    private final RestTemplate restTemplate;
    private final TypesenseStudentNormalizer normalizer;

    @Override
    public void indexe(Student student) {
        TypesenseStudentDocument document = normalizer.normalize(student);

        String url = String.format("%s%s/documents?action=upsert", baseUrl, name);
        System.out.println(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-TYPESENSE-API-KEY", key);
        HttpEntity<TypesenseStudentDocument> entity = new HttpEntity<>(document, headers);

        createCollection();

        try {
            ResponseEntity<TypesenseStudentDocument> response =
                restTemplate.postForEntity(url, entity, TypesenseStudentDocument.class);

            System.out.println(response);
        } catch (HttpClientErrorException ex) {
            System.out.println(ex.getStatusCode());
            System.out.println(ex.getStatusText());
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void remove(long id) {
        String url = String.format("%s%s/documents/%s", baseUrl, name, id);
        System.out.println(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-TYPESENSE-API-KEY", key);
        HttpEntity<TypesenseStudentDocument> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<TypesenseStudentDocument> response =
                restTemplate.exchange(url, HttpMethod.DELETE, entity, TypesenseStudentDocument.class);

            System.out.println(response);
        } catch (HttpClientErrorException ex) {
            System.out.println(ex.getStatusCode());
            System.out.println(ex.getStatusText());
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Page<Student> search(String q, int page, int size) {
        return null;
    }

    private void createCollection() {
        TypesenseCollectionModel collectionModel = new TypesenseCollectionModel();
        collectionModel.setName(name);
        collectionModel.getFields().add(new TypesenseCollectionFieldModel("id", "string", false));
        collectionModel.getFields().add(new TypesenseCollectionFieldModel("firstname", "string", false));
        collectionModel.getFields().add(new TypesenseCollectionFieldModel("lastname", "string", false));
        collectionModel.getFields().add(new TypesenseCollectionFieldModel("email", "string", false));
        collectionModel.getFields().add(new TypesenseCollectionFieldModel("school", "string", false));
        collectionModel.getFields().add(new TypesenseCollectionFieldModel("id_value", "int32", true));
        collectionModel.setDefault_sorting_field("id_value");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-TYPESENSE-API-KEY", key);
        HttpEntity<TypesenseCollectionModel> entity = new HttpEntity<>(collectionModel, headers);

        try {
            ResponseEntity<TypesenseCollectionModel> response =
                restTemplate.postForEntity(baseUrl, entity, TypesenseCollectionModel.class);

            System.out.println(response);
        } catch (HttpClientErrorException ex) {
            System.out.println(ex.getStatusCode());
            System.out.println(ex.getStatusText());
            System.out.println(ex.getMessage());
        }
    }

}
