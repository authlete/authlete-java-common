/*
 * Copyright (C) 2019 Authlete, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.
 */
package com.authlete.common.assurance;


import static org.junit.Assert.*;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Test;
import com.google.gson.Gson;
import com.neovisionaries.security.Digest;


public class VerifiedClaimsTest
{
    private static final String VALID_TIME   = "2019-12-31T01:23:45+0900";
    private static final String INVALID_TIME = "2019-12-31T01:23:45+09000";
    private static final String VALID_DATE_1 = "2019-12-31";
    private static final String VALID_DATE_2 = "2020-01-01";
    private static final String INVALID_DATE = "2020-01-011";


    private static Map<?,?> toMap(String json)
    {
        return new Gson().fromJson(json, Map.class);
    }


    private static VerifiedClaims toVerifiedClaims(String json)
    {
        return VerifiedClaims.extract(toMap(json), "verified_claims");
    }


    private String digestJson(String json)
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


    @Test
    public void testVerifiedClaimsMissing()
    {
        String json = String.join("\n",
                "{",
                "}");

        VerifiedClaims vc = toVerifiedClaims(json);

        assertNull(vc);
    }


    @Test
    public void testVerifiedClaimsNull()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":null",
                "}");

        VerifiedClaims vc = toVerifiedClaims(json);

        assertNull(vc);
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testVerifiedClaimsEmpty()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test
    public void testMinimum()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\"",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        VerifiedClaims vc = toVerifiedClaims(json);

        assertEquals("de_aml", vc.getVerification().getTrustFramework());
        assertEquals("Taka", vc.getClaims().get("nickname"));
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testVerificationMissing()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testVerificationNull()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":null,",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testTrustFrameworkMissing()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testTrustFrameworkNull()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":null",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test
    public void testVerificationTime()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"time\":\"" + VALID_TIME + "\"",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        VerifiedClaims vc = toVerifiedClaims(json);

        assertEquals(VALID_TIME, vc.getVerification().getTime());
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testVerificationTimeInvalid()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"time\":\"" + INVALID_TIME + "\"",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test
    public void testVerificationProcess()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"verification_process\":\"abc\"",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        VerifiedClaims vc = toVerifiedClaims(json);

        assertEquals("abc", vc.getVerification().getVerificationProcess());
    }


    @Test
    public void testEvidenceNull()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":null",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        VerifiedClaims vc = toVerifiedClaims(json);

        assertNull(vc.getVerification().getEvidence());
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testEvidenceEmpty()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testEvidenceTypeMissing()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testEvidenceTypeNull()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":null",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testEvidenceTypeUnknown()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":\"unknown\"",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test
    public void testIDDocument()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":\"id_document\",",
                "          \"method\":\"pipp\",",
                "          \"verifier\":{",
                "            \"organization\":\"org\",",
                "            \"txn\":\"t\"",
                "          },",
                "          \"time\":\"" + VALID_TIME + "\",",
                "          \"document\":{",
                "            \"type\":\"idcard\",",
                "            \"number\":\"num\",",
                "            \"issuer\":{",
                "              \"name\":\"n\",",
                "              \"country\":\"c\"",
                "            },",
                "            \"date_of_issuance\":\"" + VALID_DATE_1 + "\",",
                "            \"date_of_expiry\":\"" + VALID_DATE_2 + "\"",
                "          }",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        VerifiedClaims vc = toVerifiedClaims(json);
        EvidenceArray  ea = vc.getVerification().getEvidence();

        assertNotNull(ea);
        assertEquals(1, ea.size());

        Evidence evidence = ea.get(0);
        assertTrue((evidence instanceof IDDocument));

        IDDocument iddoc = (IDDocument)evidence;

        // type
        assertEquals("id_document", iddoc.getType());

        // method
        assertEquals("pipp", iddoc.getMethod());

        // verifier/organization
        assertEquals("org", iddoc.getVerifier().getOrganization());

        // verifier/txn
        assertEquals("t", iddoc.getVerifier().getTxn());

        // time
        assertEquals(VALID_TIME, iddoc.getTime());

        // document
        Document doc = iddoc.getDocument();

        // document/type
        assertEquals("idcard", doc.getType());

        // document/number
        assertEquals("num", doc.getNumber());

        // document/issuer
        Issuer issuer = doc.getIssuer();

        // document/issuer/name
        assertEquals("n", issuer.getName());

        // document/issuer/country
        assertEquals("c", issuer.getCountry());

        // document/date_of_issuance
        assertEquals(VALID_DATE_1, doc.getDateOfIssuance());

        // document/date_of_expiry
        assertEquals(VALID_DATE_2, doc.getDateOfExpiry());
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testIDDocumentMethodMissing()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":\"id_document\",",
                "          \"document\":{",
                "          }",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testIDDocumentMethodNull()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":\"id_document\",",
                "          \"method\":null,",
                "          \"document\":{",
                "          }",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testIDDocumentDocumentMissing()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":\"id_document\",",
                "          \"method\":\"pipp\"",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testIDDocumentDocumentNull()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":\"id_document\",",
                "          \"method\":\"pipp\",",
                "          \"document\":null",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test
    public void testQES()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":\"qes\",",
                "          \"issuer\":\"i\",",
                "          \"serial_number\":\"s\",",
                "          \"created_at\":\"" + VALID_TIME + "\"",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        VerifiedClaims vc = toVerifiedClaims(json);
        EvidenceArray  ea = vc.getVerification().getEvidence();

        assertNotNull(ea);
        assertEquals(1, ea.size());

        Evidence evidence = ea.get(0);
        assertTrue((evidence instanceof QES));

        QES qes = (QES)evidence;

        // type
        assertEquals("qes", qes.getType());

        // issuer
        assertEquals("i", qes.getIssuer());

        // serial_number
        assertEquals("s", qes.getSerialNumber());

        // created_at
        assertEquals(VALID_TIME, qes.getCreatedAt());
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testQESIssuerMissing()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":\"qes\",",
                "          \"serial_number\":\"s\",",
                "          \"created_at\":\"" + VALID_TIME + "\"",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testQESIssuerNull()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":\"qes\",",
                "          \"issuer\":null,",
                "          \"serial_number\":\"s\",",
                "          \"created_at\":\"" + VALID_TIME + "\"",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testQESSerialNumberMissing()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":\"qes\",",
                "          \"issuer\":\"i\",",
                "          \"created_at\":\"" + VALID_TIME + "\"",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testQESSerialNumberNull()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":\"qes\",",
                "          \"issuer\":\"i\",",
                "          \"serial_number\":null,",
                "          \"created_at\":\"" + VALID_TIME + "\"",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testQESCreatedAtMissing()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":\"qes\",",
                "          \"issuer\":\"i\",",
                "          \"serial_number\":\"s\"",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testQESCreatedAtNull()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":\"qes\",",
                "          \"issuer\":\"i\",",
                "          \"serial_number\":\"s\",",
                "          \"created_at\":null",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testQESCreatedAtInvalid()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":\"qes\",",
                "          \"issuer\":\"i\",",
                "          \"serial_number\":\"s\",",
                "          \"created_at\":\"" + INVALID_TIME + "\"",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test
    public void testUtilityBill()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":\"utility_bill\",",
                "          \"provider\":{",
                "            \"name\":\"n\",",
                "            \"country\":\"c\",",
                "            \"region\":\"r\",",
                "            \"street_address\":\"s\"",
                "          },",
                "          \"date\":\"" + VALID_DATE_1 + "\"",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        VerifiedClaims vc = toVerifiedClaims(json);
        EvidenceArray  ea = vc.getVerification().getEvidence();

        assertNotNull(ea);
        assertEquals(1, ea.size());

        Evidence evidence = ea.get(0);
        assertTrue((evidence instanceof UtilityBill));

        UtilityBill ub = (UtilityBill)evidence;

        // type
        assertEquals("utility_bill", ub.getType());

        // provider
        Provider provider = ub.getProvider();

        // provider/name
        assertEquals("n", provider.getName());

        // provider/country
        assertEquals("c", provider.getCountry());

        // provider/region
        assertEquals("r", provider.getRegion());

        // provider/street_address
        assertEquals("s", provider.getStreetAddress());

        // date
        assertEquals(VALID_DATE_1, ub.getDate());
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testUtilityBillProviderMissing()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":\"utility_bill\",",
                "          \"date\":\"" + VALID_DATE_1 + "\"",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testUtilityBillProviderNull()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":\"utility_bill\",",
                "          \"provider\":null,",
                "          \"date\":\"" + VALID_DATE_1 + "\"",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test
    public void testUtilityBillProviderEmpty()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":\"utility_bill\",",
                "          \"provider\":{",
                "          },",
                "          \"date\":\"" + VALID_DATE_1 + "\"",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testUtilityBillDateMissing()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":\"utility_bill\",",
                "          \"provider\":{",
                "          }",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testUtilityBillDateNull()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":\"utility_bill\",",
                "          \"provider\":{",
                "          },",
                "          \"date\":null",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test(expected = IdentityAssuranceException.class)
    public void testUtilityBillDateInvalid()
    {
        String json = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":\"utility_bill\",",
                "          \"provider\":{",
                "          },",
                "          \"date\":\"" + INVALID_DATE + "\"",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"nickname\":\"Taka\"",
                "    }",
                "  }",
                "}");

        toVerifiedClaims(json);
    }


    @Test
    public void testJson()
    {
        String expectedJson = String.join("\n",
                "{",
                "  \"verified_claims\":{",
                "    \"verification\":{",
                "      \"trust_framework\":\"de_aml\",",
                "      \"time\":\"" + VALID_TIME + "\",",
                "      \"verification_process\":\"vp\",",
                "      \"evidence\":[",
                "        {",
                "          \"type\":\"id_document\",",
                "          \"method\":\"pipp\",",
                "          \"verifier\":{",
                "            \"organization\":\"org\",",
                "            \"txn\":\"t\"",
                "          },",
                "          \"time\":\"" + VALID_TIME + "\",",
                "          \"document\":{",
                "            \"type\":\"idcard\",",
                "            \"number\":\"num\",",
                "            \"issuer\":{",
                "              \"name\":\"n\",",
                "              \"country\":\"c\"",
                "            },",
                "            \"date_of_issuance\":\"" + VALID_DATE_1 + "\",",
                "            \"date_of_expiry\":\"" + VALID_DATE_2 + "\"",
                "          }",
                "        },",
                "        {",
                "          \"type\":\"qes\",",
                "          \"issuer\":\"i\",",
                "          \"serial_number\":\"s\",",
                "          \"created_at\":\"" + VALID_TIME + "\"",
                "        },",
                "        {",
                "          \"type\":\"utility_bill\",",
                "          \"provider\":{",
                "            \"name\":\"n\",",
                "            \"country\":\"c\",",
                "            \"region\":\"r\",",
                "            \"street_address\":\"s\"",
                "          },",
                "          \"date\":\"" + VALID_DATE_1 + "\"",
                "        }",
                "      ]",
                "    },",
                "    \"claims\":{",
                "      \"given_name\":\"Takahiko\",",
                "      \"family_name\":\"Kawasaki\",",
                "      \"nickname\":\"Taka\",",
                "      \"email\":\"taka@authlete.com\"",
                "    }",
                "  },",
                "  \"txn\":\"t\"",
                "}");

        EvidenceArray ea = new EvidenceArray();

        ea.add(
            new IDDocument()
                .setMethod("pipp")
                .setVerifier(
                    new Verifier()
                        .setOrganization("org")
                        .setTxn("t")
                )
                .setTime(VALID_TIME)
                .setDocument(
                    new Document()
                        .setType("idcard")
                        .setNumber("num")
                        .setIssuer(
                            new Issuer()
                                .setName("n")
                                .setCountry("c")
                        )
                        .setDateOfIssuance(VALID_DATE_1)
                        .setDateOfExpiry(VALID_DATE_2)
                )
        );

        ea.add(
            new QES()
                .setIssuer("i")
                .setSerialNumber("s")
                .setCreatedAt(VALID_TIME)
        );

        ea.add(
            new UtilityBill()
                .setProvider(
                    new Provider()
                        .setName("n")
                        .setCountry("c")
                        .setRegion("r")
                        .setStreetAddress("s")
                )
                .setDate(VALID_DATE_1)
        );

        Verification verification =
            new Verification()
                .setTrustFramework("de_aml")
                .setTime(VALID_TIME)
                .setVerificationProcess("vp")
                .setEvidence(ea)
        ;

        Claims claims = new Claims();

        claims.put("given_name", "Takahiko");
        claims.put("family_name", "Kawasaki");
        claims.put("nickname", "Taka");
        claims.put("email", "taka@authlete.com");

        VerifiedClaims vc =
            new VerifiedClaims()
                .setVerification(verification)
                .setClaims(claims);
        ;

        Map<String, Object> data = new LinkedHashMap<String, Object>();
        data.put("verified_claims", vc);
        data.put("txn", "t");

        String actualJson = new Gson().toJson(data);

        String expectedDigest = digestJson(expectedJson);
        String actualDigest   = digestJson(actualJson);

        assertEquals(expectedDigest, actualDigest);
    }
}
