package com.klid.demodockercompose.search.typesense;

import com.klid.demodockercompose.entity.Student;
import com.klid.demodockercompose.search.StudentSearchIndexer;
import com.klid.demodockercompose.search.typesense.TypesenseCollectionModel.TypesenseCollectionFieldModel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        if (StringUtils.isBlank(q)) {
            q = "*";
        }

        String url = String.format(
            "%s%s/documents/search?q={q}&query_by=firstname,lastname,email,school&page={page}&per_page={perPage}&num_typos={typos}",
            baseUrl,
            name
        );
        System.out.println(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-TYPESENSE-API-KEY", key);
        HttpEntity<TypesenseCollectionModel> entity = new HttpEntity<>(headers);

        Map<String, Object> params = new HashMap<>();
        params.put("q", q);
        params.put("page", page);
        params.put("perPage", size);
        params.put("typos", 5);

        Pageable pageable = PageRequest.of(page, size);

        try {
            ResponseEntity<TypesenseStudentSearchResult> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                TypesenseStudentSearchResult.class,
                params
            );

            System.out.println(response);

            TypesenseStudentSearchResult resultBody = response.getBody();
            if (resultBody != null) {
                System.out.printf("Search time %dms%n", resultBody.getSearch_time_ms());
                List<Student> students = resultBody.getHits()
                    .stream()
                    .map(hitItem -> normalizer.normalize(hitItem.getDocument()))
                    .collect(Collectors.toList());
                return new PageImpl<>(students, pageable, resultBody.getFound());
            }
        } catch (HttpClientErrorException ex) {
            System.out.println(ex.getStatusCode());
            System.out.println(ex.getStatusText());
            System.out.println(ex.getMessage());
        }

        return Page.empty(pageable);
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
