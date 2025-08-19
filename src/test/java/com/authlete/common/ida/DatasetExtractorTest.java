/*
 * Copyright (C) 2022-2024 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.authlete.common.ida;


import com.authlete.common.util.digest.Digest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import static org.junit.Assert.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Tests for {@link DatasetExtractor}.
 */
public class DatasetExtractorTest
{
    /**
     * The time which is passed to the constructor of {@link DatasetExtractor}.
     * To get the same results on tests of {@code "max_age"}, a fixed value
     * must be given to the constructor.
     */
    private static final OffsetDateTime CURRENT_TIME =
            OffsetDateTime.of(2022, 4, 1, 0, 0, 0, 0, ZoneOffset.UTC);

    /**
     * The source of the original dataset.
     */
    private static final String EVIDENCE_WITH_ASSURANCE_DETAILS
        = "/ekyc-ida/examples/response/evidence_with_assurance_details.json";

    /**
     * Cache of original datasets.
     */
    private static final Map<String, Object> DATASET_REPOSITORY = new HashMap<>();

    /**
     * Flag for logging of {@link DatasetExtractor}. Set {@code true} if you
     * want to view details logs.
     */
    private static final boolean LOGGING_ENABLED = false;


    private static Object getDataset(String resource)
    {
        Object dataset = DATASET_REPOSITORY.get(resource);

        if (dataset == null)
        {
            dataset = loadDataset(resource);
            DATASET_REPOSITORY.put(resource, dataset);
        }

        return dataset;
    }


    @SuppressWarnings("unchecked")
    private static Object loadDataset(String resource)
    {
        try (Reader reader = createReader(resource))
        {
            Map<String, Object> map = new Gson().fromJson(reader, Map.class);

            return map.get("verified_claims");
        }
        catch (IOException e)
        {
            e.printStackTrace();

            return Collections.emptyMap();
        }
    }


    private static Reader createReader(String resource)
    {
        return new InputStreamReader(
                DatasetExtractorTest.class.getResourceAsStream(resource),
                StandardCharsets.UTF_8);
    }


    @SuppressWarnings("unchecked")
    private static Map<String, Object> fromJson(String json)
    {
        return new Gson().fromJson(json, Map.class);
    }


    private static String digestJson(String json)
    {
        try
        {
            return Digest.getInstanceSHA256().updateJson(json).digestAsString();
        }
        catch (IOException e)
        {
            return null;
        }
    }


    private static Map<String, Object> extract(
            Map<String, Object> request, Object original)
    {
        return extract((DatasetExtractor)null, request, original);
    }


    @SuppressWarnings("unchecked")
    private static Map<String, Object> extract(
            DatasetExtractor extractor, Map<String, Object> request, Object original)
    {
        if (extractor == null)
        {
            extractor = createDatasetExtractor();
        }

        if (original instanceof Map)
        {
            return extractor.extract(request, (Map<String, Object>)original);
        }
        else if (original instanceof Map)
        {
            return extractor.extract(request, (List<Map<String, Object>>)original);
        }
        else
        {
            return null;
        }
    }


    private static DatasetExtractor createDatasetExtractor()
    {
        DatasetExtractor extractor = new DatasetExtractor(CURRENT_TIME);

        if (LOGGING_ENABLED)
        {
            extractor.setLogger(createLogger());
        }

        return extractor;
    }


    private static Logger createLogger()
    {
        System.setProperty("org.slf4j.simpleLogger.log.com.authlete.common.ida.DatasetExtractorTest","TRACE");

        return LoggerFactory.getLogger(DatasetExtractorTest.class);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testBasicClaims()
    {
        Object original = getDataset(EVIDENCE_WITH_ASSURANCE_DETAILS);
        Map<String, Object> request = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":null" +
                  "}," +
                  "\"claims\":{" +
                    "\"given_name\":null" +
                  "}" +
                "}"
        );

        Map<String, Object> dataset = extract(request, original);
        assertNotNull("dataset is null.", dataset);

        Map<String, Object> claims = (Map<String, Object>)dataset.get("claims");
        assertNotNull("'claims' is null.", claims);

        String givenName = (String)claims.get("given_name");
        assertEquals("'given_name' does not match.", "Sarah", givenName);

        // "family_name" should be null because it was not requested.
        String familyName = (String)claims.get("family_name");
        assertNull("'familiy_name' is not null.", familyName);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testBasicClaimsValueNotMatch()
    {
        Object original = getDataset(EVIDENCE_WITH_ASSURANCE_DETAILS);
        Map<String, Object> request = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":null" +
                  "}," +
                  "\"claims\":{" +
                    "\"given_name\":{" +
                      "\"value\":\"Unknown\"" +
                    "}," +
                    "\"family_name\":null" +
                  "}" +
                "}"
        );

        Map<String, Object> dataset = extract(request, original);
        assertNotNull("dataset is null.", dataset);

        Map<String, Object> claims = (Map<String, Object>)dataset.get("claims");
        assertNotNull("'claims' is null.", claims);

        // "given_name" should be omitted because its value does not match
        // the value specified by "value".
        String givenName = (String)claims.get("given_name");
        assertNull("'given_name' is not omitted.", givenName);

        // "family_name" should be available.
        String familyName = (String)claims.get("family_name");
        assertEquals("'family_name' does not match.", "Meredyth", familyName);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testBasicClaimsValuesNotMatch()
    {
        Object original = getDataset(EVIDENCE_WITH_ASSURANCE_DETAILS);
        Map<String, Object> request = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":null" +
                  "}," +
                  "\"claims\":{" +
                    "\"given_name\":{" +
                      "\"values\":[\"Unknown0\",\"Unknown1\"]" +
                    "}," +
                    "\"family_name\":null" +
                  "}" +
                "}"
        );

        Map<String, Object> dataset = extract(request, original);
        assertNotNull("dataset is null.", dataset);

        Map<String, Object> claims = (Map<String, Object>)dataset.get("claims");
        assertNotNull("'claims' is null.", claims);

        // "given_name" should be omitted because its value does not match
        // the value specified by "value".
        String givenName = (String)claims.get("given_name");
        assertNull("'given_name' is not omitted.", givenName);

        // "family_name" should be available.
        String familyName = (String)claims.get("family_name");
        assertEquals("'family_name' does not match.", "Meredyth", familyName);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testBasicClaimsValuesMatch()
    {
        Object original = getDataset(EVIDENCE_WITH_ASSURANCE_DETAILS);
        Map<String, Object> request = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":null" +
                  "}," +
                  "\"claims\":{" +
                    "\"given_name\":{" +
                      "\"values\":[\"Unknown0\",\"Sarah\"]" +
                    "}," +
                    "\"family_name\":null" +
                  "}" +
                "}"
        );

        Map<String, Object> dataset = extract(request, original);
        assertNotNull("dataset is null.", dataset);

        Map<String, Object> claims = (Map<String, Object>)dataset.get("claims");
        assertNotNull("'claims' is null.", claims);

        String givenName = (String)claims.get("given_name");
        assertEquals("'given_name' does not match.", "Sarah", givenName);

        // "family_name" should be available.
        String familyName = (String)claims.get("family_name");
        assertEquals("'family_name' does not match.", "Meredyth", familyName);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testBasicClaimsMaxAgeNotSatisfied()
    {
        Object original = getDataset(EVIDENCE_WITH_ASSURANCE_DETAILS);
        Map<String, Object> request = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":null" +
                  "}," +
                  "\"claims\":{" +
                    "\"birthdate\":{" + // The actual value of birthdate is 1976-03-11".
                      "\"max_age\": 100" +
                    "}," +
                    "\"family_name\":null" +
                  "}" +
                "}"
        );

        Map<String, Object> dataset = extract(request, original);
        assertNotNull("dataset is null.", dataset);

        Map<String, Object> claims = (Map<String, Object>)dataset.get("claims");
        assertNotNull("'claims' is null.", claims);

        String birthdate = (String)claims.get("birthdate");
        assertNull("'birthdate' is not omitted.", birthdate);

        // "family_name" should be available.
        String familyName = (String)claims.get("family_name");
        assertEquals("'family_name' does not match.", "Meredyth", familyName);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testBasicClaimsMaxAgeSatisfied()
    {
        Object original = getDataset(EVIDENCE_WITH_ASSURANCE_DETAILS);
        Map<String, Object> request = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":null" +
                  "}," +
                  "\"claims\":{" +
                    "\"birthdate\":{" + // The actual value of birthdate is 1976-03-11".
                      "\"max_age\": 2000000000" +
                    "}," +
                    "\"family_name\":null" +
                  "}" +
                "}"
        );

        Map<String, Object> dataset = extract(request, original);
        assertNotNull("dataset is null.", dataset);

        Map<String, Object> claims = (Map<String, Object>)dataset.get("claims");
        assertNotNull("'claims' is null.", claims);

        String birthdate = (String)claims.get("birthdate");
        assertEquals("'birthdate' does not satisfy the 'max_age' requirement", "1976-03-11", birthdate);

        // "family_name" should be available.
        String familyName = (String)claims.get("family_name");
        assertEquals("'family_name' does not match.", "Meredyth", familyName);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testDateTime()
    {
        Map<String, Object> original = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":\"my_framework\"" +
                  "}," +
                  "\"claims\":{" +
                    "\"datetime_0\":\"2022-04-22T12:34:56\"," +
                    "\"datetime_1\":\"2022-04-22T12:34:56+0900\"," +
                    "\"datetime_2\":\"2022-04-22T12:34:56Z\"," +
                    "\"datetime_3\":\"2022-04-22T12:34\"," +
                    "\"datetime_4\":\"2022-04-22T12:34+0900\"," +
                    "\"datetime_5\":\"2022-04-22T12:34Z\"" +
                  "}" +
                "}"
        );

        String[] expectedValues = {
                "2022-04-22T12:34:56",
                "2022-04-22T12:34:56+0900",
                "2022-04-22T12:34:56Z",
                "2022-04-22T12:34",
                "2022-04-22T12:34+0900",
                "2022-04-22T12:34Z",
        };

        Map<String, Object> request = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":null" +
                  "}," +
                  "\"claims\":{" +
                    "\"datetime_0\":{\"max_age\":2000000000}," +
                    "\"datetime_1\":{\"max_age\":2000000000}," +
                    "\"datetime_2\":{\"max_age\":2000000000}," +
                    "\"datetime_3\":{\"max_age\":2000000000}," +
                    "\"datetime_4\":{\"max_age\":2000000000}," +
                    "\"datetime_5\":{\"max_age\":2000000000}" +
                  "}" +
                "}"
        );

        Map<String, Object> dataset = extract(request, original);
        assertNotNull("dataset is null.", dataset);

        Map<String, Object> claims = (Map<String, Object>)dataset.get("claims");
        assertNotNull("'claims' is null.", claims);

        for (int i = 0; i < expectedValues.length; i++)
        {
            String key     = String.format("datetime_%d", i);
            String value   = (String)claims.get(key);
            String message = String.format("'%s' does not satisfy the 'max_age' constraint.", key);

            assertEquals(message, expectedValues[i], value);
        }
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testBasicClaimsNested()
    {
        Object original = getDataset(EVIDENCE_WITH_ASSURANCE_DETAILS);
        Map<String, Object> request = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":null" +
                  "}," +
                  "\"claims\":{" +
                    "\"address\":{" +
                      "\"locality\":null" +
                    "}" +
                  "}" +
                "}"
        );

        Map<String, Object> dataset = extract(request, original);
        assertNotNull("dataset is null.", dataset);

        Map<String, Object> claims = (Map<String, Object>)dataset.get("claims");
        assertNotNull("'claims' is null.", claims);

        Map<String, Object> address = (Map<String, Object>)claims.get("address");
        assertNotNull("'address' is not null.", address);

        // "locality" should be available.
        String locality = (String)address.get("locality");
        assertEquals("'locality' does not match", "Edinburgh", locality);

        // "country" should not be available because it was not requested.
        String country = (String)address.get("country");
        assertNull("'country' is not null.", country);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testTransformedClaims()
    {
        Object original = getDataset(EVIDENCE_WITH_ASSURANCE_DETAILS);
        Map<String, Object> request = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":null" +
                  "}," +
                  "\"claims\":{" +
                    "\"given_name\":null," +
                    "\":tc\": null," +
                    "\"::predefined_tc\":{}" +
                  "}" +
                "}"
        );

        Map<String, Object> dataset = extract(request, original);
        assertNotNull("dataset is null.", dataset);

        Map<String, Object> claims = (Map<String, Object>)dataset.get("claims");
        assertNotNull("'claims' is null.", claims);

        // "given_name" should be available.
        String givenName = (String)claims.get("given_name");
        assertEquals("'given_name' does not match.", "Sarah", givenName);

        // Transformed claims should not be omitted.
        // Note that this behavior is for Authlete.

        boolean tcContained = claims.containsKey(":tc");
        assertTrue("':tc' is not contained.", tcContained);

        Object tc = claims.get(":tc");
        assertNull("':tc' is not null.", tc);

        boolean predefinedTcContained = claims.containsKey("::predefined_tc");
        assertTrue("'::predefined_tc' is not contained", predefinedTcContained);

        // Values of transformed claims should be put without modification.
        // Note again that this behavior is for Authlete.

        Object predefinedTc = claims.get("::predefined_tc");
        assertNotNull("'::predefined_tc' is null.", predefinedTc);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testTransformedClaimsUnaware()
    {
        Object original = getDataset(EVIDENCE_WITH_ASSURANCE_DETAILS);
        Map<String, Object> request = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":null" +
                  "}," +
                  "\"claims\":{" +
                    "\"given_name\":null," +
                    "\":tc\": null," +
                    "\"::predefined_tc\":{}" +
                  "}" +
                "}"
        );

        // Unaware of transformed claims.
        DatasetExtractor extractor = createDatasetExtractor().setTransformedClaimAware(false);

        Map<String, Object> dataset = extract(extractor, request, original);
        assertNotNull("dataset is null.", dataset);

        Map<String, Object> claims = (Map<String, Object>)dataset.get("claims");
        assertNotNull("'claims' is null.", claims);

        // "given_name" should be available.
        String givenName = (String)claims.get("given_name");
        assertEquals("'given_name' does not match.", "Sarah", givenName);

        boolean tcContained = claims.containsKey(":tc");
        assertFalse("':tc' is contained.", tcContained);

        boolean predefinedTcContained = claims.containsKey("::predefined_tc");
        assertFalse("'::predefined_tc' is contained", predefinedTcContained);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testEmptyMap()
    {
        Object original = getDataset(EVIDENCE_WITH_ASSURANCE_DETAILS);
        Map<String, Object> request = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":{}" +
                  "}," +
                  "\"claims\":{" +
                    "\"given_name\":null" +
                  "}" +
                "}"
        );

        Map<String, Object> dataset = extract(request, original);
        assertNotNull("dataset is null.", dataset);

        Map<String, Object> verification = (Map<String, Object>)dataset.get("verification");
        assertNotNull("'verification' is null.", verification);

        String trustFramework = (String)verification.get("trust_framework");
        assertEquals("'trust_framework' does not match", "uk_tfida", trustFramework);
    }


    @Test
    public void testAllOmitted()
    {
        Object original = getDataset(EVIDENCE_WITH_ASSURANCE_DETAILS);
        Map<String, Object> request = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":{" +
                      "\"value\":\"unknown\"" +
                    "}" +
                  "}," +
                  "\"claims\":{" +
                    "\"given_name\":null" +
                  "}" +
                "}"
        );

        // OpenID Connect for Identity Assurance 1.0
        // Data not Matching Requirements
        //
        //   When the available data does not fulfill the requirements of
        //   the RP expressed through "value", "values", or "max_age", the
        //   following logic applies:
        //
        //     * If the respective requirement was expressed for a Claim
        //       within "verified_claims/verification", the whole
        //       "verified_claims" element MUST be omitted.
        //
        //     * Otherwise, the respective Claim MUST be omitted from
        //       the response.

        // Because "trust_framework" does not match the specified value,
        // the entire dataset should be omitted.
        //
        Map<String, Object> dataset = extract(request, original);
        assertNull("dataset is not null.", dataset);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testArraySingle()
    {
        Object original = getDataset(EVIDENCE_WITH_ASSURANCE_DETAILS);
        Map<String, Object> request = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":null," +
                    "\"evidence\":[" +
                      "{" +
                        "\"type\":{" +
                          "\"value\":\"electronic_record\"" +
                        "}," +
                        "\"check_details\":[" +
                          "{" +
                            "\"check_method\":null," +
                            "\"organization\":{" +
                              "\"value\":\"TheCreditBureau\"" +
                            "}," +
                            "\"txn\":null" +
                          "}" +
                        "]" +
                      "}" +
                    "]" +
                  "}," +
                  "\"claims\":{" +
                    "\"given_name\":null" +
                  "}" +
                "}"
        );

        Map<String, Object> dataset = extract(request, original);
        assertNotNull("dataset is null.", dataset);

        Map<String, Object> verification = (Map<String, Object>)dataset.get("verification");
        assertNotNull("'verification' is null.", verification);

        List<?> evidence = (List<?>)verification.get("evidence");
        assertNotNull("'evidence' is null.", evidence);
        assertEquals("The number of elements in 'evidence' is wrong.", 1, evidence.size());

        Map<String, Object> evidence0 = (Map<String, Object>)evidence.get(0);
        List<?> checkDetails = (List<?>)evidence0.get("check_details");
        Map<String, Object> checkDetails0 = (Map<String, Object>)checkDetails.get(0);
        String txn = (String)checkDetails0.get("txn");

        assertEquals("'txn' does not match.", "kbv1-hf934hn09234ng03jj3", txn);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testArrayMultiple()
    {
        Object original = getDataset(EVIDENCE_WITH_ASSURANCE_DETAILS);
        Map<String, Object> request = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":null," +
                    "\"evidence\":[" +
                      "{" +
                        "\"type\":{" +
                          "\"value\":\"electronic_record\"" +
                        "}," +
                        "\"check_details\":[" +
                          "{" +
                            "\"check_method\":null," +
                            "\"organization\":{" +
                              "\"value\":\"TheCreditBureau\"" +
                            "}," +
                            "\"txn\":null" +
                          "}" +
                        "]" +
                      "}," +
                      "{" +
                        "\"type\":{" +
                          "\"value\":\"document\"" +
                        "}" +
                      "}" +
                    "]" +
                  "}," +
                  "\"claims\":{" +
                    "\"given_name\":null" +
                  "}" +
                "}"
        );

        Map<String, Object> dataset = extract(request, original);
        assertNotNull("dataset is null.", dataset);

        Map<String, Object> verification = (Map<String, Object>)dataset.get("verification");
        assertNotNull("'verification' is null.", verification);

        List<?> evidence = (List<?>)verification.get("evidence");
        assertNotNull("'evidence' is null.", evidence);
        assertEquals("The number of elements in 'evidence' is wrong.", 2, evidence.size());

        boolean electronicRecordFound = false;
        boolean documentFound = false;

        for (Object evidenceElement : evidence)
        {
            String type = (String)((Map<String, Object>)evidenceElement).get("type");

            if (type.equals("electronic_record"))
            {
                electronicRecordFound = true;
            }
            else if (type.equals("document"))
            {
                documentFound = true;
            }
        }

        assertTrue("'electronic_record' is not found.", electronicRecordFound);
        assertTrue("'document' is not found.", documentFound);
    }


    @Test
    public void testExampleInJavadoc()
    {
        Map<String, Object> original = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":\"uk_tfida\"," +
                    "\"evidence\":[" +
                      "{" +
                        "\"type\": \"electronic_record\"," +
                        "\"check_details\": [" +
                          "{" +
                            "\"check_method\": \"kbv\"," +
                            "\"organization\": \"TheCreditBureau\"," +
                            "\"txn\": \"kbv1-hf934hn09234ng03jj3\"" +
                          "}" +
                        "]," +
                        "\"time\": \"2021-04-09T14:12Z\"," +
                        "\"record\": {" +
                          "\"type\": \"mortgage_account\"," +
                          "\"source\": {" +
                            "\"name\": \"TheCreditBureau\"" +
                          "}" +
                        "}" +
                      "}," +
                      "{" +
                        "\"type\": \"electronic_record\"," +
                        "\"check_details\": [" +
                          "{" +
                            "\"check_method\": \"kbv\"," +
                            "\"organization\": \"OpenBankingTPP\"," +
                            "\"txn\": \"kbv2-nm0f23u9459fj38u5j6\"" +
                          "}" +
                        "]," +
                        "\"time\": \"2021-04-09T14:12Z\"," +
                        "\"record\": {" +
                          "\"type\": \"bank_account\"," +
                          "\"source\": {" +
                            "\"name\": \"TheBank\"" +
                          "}" +
                        "}" +
                      "}" +
                    "]" +
                  "}," +
                  "\"claims\":{" +
                    "\"given_name\": \"Sarah\"," +
                    "\"family_name\": \"Meredyth\"," +
                    "\"birthdate\": \"1976-03-11\"," +
                    "\"place_of_birth\": {" +
                      "\"country\": \"UK\"" +
                    "}," +
                    "\"address\": {" +
                      "\"locality\": \"Edinburgh\"," +
                      "\"postal_code\": \"EH1 9GP\"," +
                      "\"country\": \"UK\"," +
                      "\"street_address\": \"122 Burns Crescent\"" +
                    "}" +
                  "}" +
                "}"
        );

        Map<String, Object> request = fromJson(
                "{" +
                  "\"verification\": {" +
                    "\"trust_framework\": null," +
                    "\"evidence\": [" +
                      "{" +
                        "\"type\": {" +
                          "\"value\": \"electronic_record\"" +
                        "}," +
                        "\"check_details\": [" +
                          "{" +
                            "\"check_method\": null," +
                            "\"organization\": {" +
                              "\"value\": \"OpenBankingTPP\"" +
                            "}," +
                            "\"txn\": null" +
                          "}" +
                        "]" +
                      "}" +
                    "]" +
                  "}," +
                  "\"claims\": {" +
                    "\"given_name\": null," +
                    "\"family_name\": {" +
                      "\"value\": \"Unknown\"" +
                    "}," +
                    "\"address\": {" +
                      "\"locality\": null" +
                    "}" +
                  "}" +
                "}"
        );

        String expected =
                "{" +
                  "\"verification\": {" +
                    "\"trust_framework\": \"uk_tfida\"," +
                    "\"evidence\": [" +
                      "{" +
                        "\"type\": \"electronic_record\"," +
                        "\"check_details\": [" +
                          "{" +
                            "\"check_method\": \"kbv\"," +
                            "\"organization\": \"OpenBankingTPP\"," +
                            "\"txn\": \"kbv2-nm0f23u9459fj38u5j6\"" +
                          "}" +
                        "]" +
                      "}" +
                    "]" +
                  "}," +
                  "\"claims\": {" +
                    "\"given_name\": \"Sarah\"," +
                    "\"address\": {" +
                      "\"locality\": \"Edinburgh\"" +
                    "}" +
                  "}" +
                "}";

        Map<String, Object> dataset = extract(request, original);
        assertNotNull("dataset is null.", dataset);

        String expectedDigest = digestJson(expected);
        String actualDigest   = digestJson(new Gson().toJson(dataset, Map.class));

        assertEquals("The dataset is different from the expected one.", expectedDigest, actualDigest);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testAssuranceDetails()
    {
        Object original = getDataset(EVIDENCE_WITH_ASSURANCE_DETAILS);
        Map<String, Object> request = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":null," +
                    "\"assurance_process\":{" +
                      "\"assurance_details\":null" +
                    "}" +
                  "}," +
                  "\"claims\":{" +
                    "\"given_name\":null" +
                  "}" +
                "}"
        );

        Map<String, Object> dataset = extract(request, original);
        assertNotNull("dataset is null.", dataset);

        Map<String, Object> verification = (Map<String, Object>)dataset.get("verification");
        assertNotNull("'verification' is null.", verification);

        Map<String, Object> assuranceProcess = (Map<String, Object>)verification.get("assurance_process");
        assertNotNull("'assurance_process' is null.", assuranceProcess);

        // "assurance_details" has a special rule. When it is requested,
        // all available sub-elements must be returned.

        List<?> assuranceDetails = (List<?>)assuranceProcess.get("assurance_details");
        assertNotNull("'assurance_details' is null.", assuranceDetails);

        // Check the number of the "assurance_details" array.
        assertEquals("The number of elements in 'assurance_details' is wrong.", 3, assuranceDetails.size());

        // The first element in the "assurance_details" array.
        Map<String, Object> first = (Map<String, Object>)assuranceDetails.get(0);
        assertNotNull("The first element in the 'assurance_details' array is null.", first);

        // Check the properties of the element.
        assertEquals("'assurance_type' is wrong.", "evidence_validation", (String)first.get("assurance_type"));
        assertEquals("'assurance_classification' is wrong.", "score_2", (String)first.get("assurance_classification"));
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testEssentialFalse()
    {
        Object original = getDataset(EVIDENCE_WITH_ASSURANCE_DETAILS);
        Map<String, Object> request = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":null," +
                    "\"assurance_process\":{" +
                      "\"assurance_details\":null" +
                    "}" +
                  "}," +
                  "\"claims\":{" +
                    "\"given_name\":{" +
                      "\"essential\":false" +
                    "}" +
                  "}" +
                "}"
        );

        Map<String, Object> dataset = extract(request, original);
        assertNotNull("dataset is null.", dataset);

        Map<String, Object> claims = (Map<String, Object>)dataset.get("claims");
        assertNotNull("'claims' is null.", claims);

        String givenName = (String)claims.get("given_name");
        assertEquals("'given_name' does not match.", "Sarah", givenName);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testEssentialTrue()
    {
        Object original = getDataset(EVIDENCE_WITH_ASSURANCE_DETAILS);
        Map<String, Object> request = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":null," +
                    "\"assurance_process\":{" +
                      "\"assurance_details\":null" +
                    "}" +
                  "}," +
                  "\"claims\":{" +
                    "\"given_name\":{" +
                      "\"essential\":true" +
                    "}" +
                  "}" +
                "}"
        );

        Map<String, Object> dataset = extract(request, original);
        assertNotNull("dataset is null.", dataset);

        Map<String, Object> claims = (Map<String, Object>)dataset.get("claims");
        assertNotNull("'claims' is null.", claims);

        String givenName = (String)claims.get("given_name");
        assertEquals("'given_name' does not match.", "Sarah", givenName);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testEssentialTrueAndValue()
    {
        Object original = getDataset(EVIDENCE_WITH_ASSURANCE_DETAILS);
        Map<String, Object> request = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":null," +
                    "\"assurance_process\":{" +
                      "\"assurance_details\":null" +
                    "}" +
                  "}," +
                  "\"claims\":{" +
                    "\"given_name\":{" +
                      "\"essential\":true," +
                      "\"value\":\"Sarah\"" +
                    "}" +
                  "}" +
                "}"
        );

        Map<String, Object> dataset = extract(request, original);
        assertNotNull("dataset is null.", dataset);

        Map<String, Object> claims = (Map<String, Object>)dataset.get("claims");
        assertNotNull("'claims' is null.", claims);

        String givenName = (String)claims.get("given_name");
        assertEquals("'given_name' does not match.", "Sarah", givenName);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testEssentialTrueForUnavailableClaim()
    {
        String UNAVAILABLE_CLAIM_NAME = "unavailable_claim";

        Object original = getDataset(EVIDENCE_WITH_ASSURANCE_DETAILS);
        Map<String, Object> request = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":null," +
                    "\"assurance_process\":{" +
                      "\"assurance_details\":null" +
                    "}" +
                  "}," +
                  "\"claims\":{" +
                    "\"given_name\":null," +
                    "\"" + UNAVAILABLE_CLAIM_NAME + "\":{" +
                      "\"essential\":true" +
                    "}" +
                  "}" +
                "}"
        );

        // Even if "essential":true is specified and the said claim is unavailable,
        // the authorization server should not treat the case as an error. Refer to
        // the description about "essential" written in OIDC Core 1.0, 5.5.1.
        // Individual Claims Requests.

        Map<String, Object> dataset = extract(request, original);
        assertNotNull("dataset is null.", dataset);

        Map<String, Object> claims = (Map<String, Object>)dataset.get("claims");
        assertNotNull("'claims' is null.", claims);

        String givenName = (String)claims.get("given_name");
        assertEquals("'given_name' does not match.", "Sarah", givenName);

        boolean available = claims.containsKey(UNAVAILABLE_CLAIM_NAME);
        assertFalse("'" + UNAVAILABLE_CLAIM_NAME + "' is available.", available);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testPurpose()
    {
        Object original = getDataset(EVIDENCE_WITH_ASSURANCE_DETAILS);
        Map<String, Object> request = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":null," +
                    "\"assurance_process\":{" +
                      "\"assurance_details\":null" +
                    "}" +
                  "}," +
                  "\"claims\":{" +
                    "\"given_name\":{" +
                      "\"purpose\":\"the purpose for requesting given_name\"" +
                    "}" +
                  "}" +
                "}"
        );

        Map<String, Object> dataset = extract(request, original);
        assertNotNull("dataset is null.", dataset);

        Map<String, Object> claims = (Map<String, Object>)dataset.get("claims");
        assertNotNull("'claims' is null.", claims);

        String givenName = (String)claims.get("given_name");
        assertEquals("'given_name' does not match.", "Sarah", givenName);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testPurposeAndValue()
    {
        Object original = getDataset(EVIDENCE_WITH_ASSURANCE_DETAILS);
        Map<String, Object> request = fromJson(
                "{" +
                  "\"verification\":{" +
                    "\"trust_framework\":null," +
                    "\"assurance_process\":{" +
                      "\"assurance_details\":null" +
                    "}" +
                  "}," +
                  "\"claims\":{" +
                    "\"given_name\":{" +
                      "\"purpose\":\"the purpose for requesting given_name\"," +
                      "\"value\":\"Sarah\"" +
                    "}" +
                  "}" +
                "}"
        );

        Map<String, Object> dataset = extract(request, original);
        assertNotNull("dataset is null.", dataset);

        Map<String, Object> claims = (Map<String, Object>)dataset.get("claims");
        assertNotNull("'claims' is null.", claims);

        String givenName = (String)claims.get("given_name");
        assertEquals("'given_name' does not match.", "Sarah", givenName);
    }
}
